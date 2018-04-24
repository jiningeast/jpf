package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderCpsingleMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayTdorderMapper;
import com.joiest.jpf.dto.TdorderRequest;
import com.joiest.jpf.entity.TdorderInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.TdorderServiceFacade;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;

public class TdorderServiceFacadeImpl implements TdorderServiceFacade {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private PayTdorderMapper payTdorderMapper;

    @Autowired
    private PayOrderCpsingleMapper payOrderCpsingleMapper;

    @Override
    public int getTdordersCount(){
        PayTdorderExample e = new PayTdorderExample();
        return payTdorderMapper.countByExample(e);
    }

    @Override
    public List<TdorderInfo> getTdorders(long page, long rows){
        PayTdorderExample e = new PayTdorderExample();
        e.setPageNo(page);
        e.setPageSize(rows);
        List<PayTdorder> list = payTdorderMapper.selectByExample(e);
        List<TdorderInfo> infos = new ArrayList<>();
        for (PayTdorder payTdorder:list){
            TdorderInfo tdorderInfo = new TdorderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayTdorder.class, TdorderInfo.class, false);
            beanCopier.copy(payTdorder, tdorderInfo, null);
            infos.add(tdorderInfo);
        }

        return infos;
    }

    @Override
    public JpfResponseDto checkOk(TdorderRequest tdorderRequest, UserInfo userInfo){

        // 查询此单的原operate_content审核内容字段
        PayTdorder ordinaryRec = payTdorderMapper.selectByPrimaryKey(tdorderRequest.getId());
        String json = ordinaryRec.getOperateContent();

        // 根据请求构建新的json记录
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userInfo.getId());
        map.put("username", userInfo.getUserName());
        map.put("content", "<span style='color:blue'>审核通过</span>");
        map.put("date", new Date());

        JsonUtils jsonUtils = new JsonUtils();
        List<Object> list = new ArrayList<>();
        if ( org.apache.commons.lang3.StringUtils.isBlank(json) ){
            // 建立一个空的list用来存储本次驳回操作的json记录
            list.add(map);
        }else{
            // 把原JSON转换成list
            list = jsonUtils.toObject(json, List.class);

            // 把新的json记录添加进原json
            list.add(map);
        }

        // 得到新拼接的JSON数据
        String newJson = jsonUtils.toJson(list);

        // 更新退单表tdorder->singlestatus字段为1：运营审核通过
        //           tdorder->operateContent字段为newJson
        PayTdorder newRec = new PayTdorder();
        newRec.setId(tdorderRequest.getId());
        newRec.setSinglestatus((byte)1);
        newRec.setOperateContent(newJson);

        int res_tdorder = payTdorderMapper.updateByPrimaryKeySelective(newRec);
        if ( res_tdorder == 1 ){
            // 先查询退单记录表有没有此orderid的记录
            PayOrderCpsingleExample payOrderCpsingleExample = new PayOrderCpsingleExample();
            PayOrderCpsingleExample.Criteria payOrderCpsingleExampleC = payOrderCpsingleExample.createCriteria();
            payOrderCpsingleExampleC.andTdorderidEqualTo(tdorderRequest.getTdorderid());
            payOrderCpsingleExampleC.andOrderidEqualTo(tdorderRequest.getOrderid());
            List<PayOrderCpsingle> cpList = payOrderCpsingleMapper.selectByExample(payOrderCpsingleExample);
            if ( cpList == null || cpList.isEmpty() ){
                // 增加退单记录表order_cpsingle一条记录，待财务审核
                PayOrderCpsingle payOrderCpsingle = new PayOrderCpsingle();
                payOrderCpsingle.setTdorderid(tdorderRequest.getTdorderid());
                payOrderCpsingle.setOrderid(tdorderRequest.getOrderid());
                payOrderCpsingle.setTdorderprice(tdorderRequest.getTdorderprice());
                payOrderCpsingle.setMtsid(tdorderRequest.getMtsid());
                payOrderCpsingle.setSingletype(tdorderRequest.getSingletype());
                payOrderCpsingle.setSinglestatus(tdorderRequest.getSinglestatus());
                payOrderCpsingle.setAddtime(new Date());

                int cpIns = payOrderCpsingleMapper.insertSelective(payOrderCpsingle);
                if ( cpIns != 1 ){
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "插入退单记录表失败，请联系管理员");
                }
            }else{
                // 退单记录表已经有此orderid的记录则更新它的审核状态为待审核，等待财务再次审核
                PayOrderCpsingle cpRec = new PayOrderCpsingle();
                cpRec.setSinglestatus((byte)0);
                cpRec.setId(cpList.get(0).getId());
                int cpUpd = payOrderCpsingleMapper.updateByPrimaryKeySelective(cpRec);
                if ( cpUpd != 1 ){
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单记录表失败，请联系管理员");
                }
            }

            // 更新订单表order退款状态为4：退单受理中（待财务审核）
            PayOrderExample payOrderExample = new PayOrderExample();
            PayOrderExample.Criteria payOrderC = payOrderExample.createCriteria();
            payOrderC.andOrderidEqualTo(tdorderRequest.getOrderid());

            PayOrder payOrder = new PayOrder();
            payOrder.setSinglestatus((byte)4);
            int res_order = payOrderMapper.updateByExampleSelective(payOrder, payOrderExample);
            if ( res_order == 1 ){
                return new JpfResponseDto();
            }else{
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新订单表失败，请联系管理员");
            }
        }else{
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单表失败，请联系管理员");
        }
    }

    @Override
    public JpfResponseDto checkNo(TdorderRequest tdorderRequest, UserInfo userInfo){

        // 查询该退单记录原先的operate_content
        PayTdorder payTdorder = payTdorderMapper.selectByPrimaryKey(tdorderRequest.getId());
        String json = payTdorder.getOperateContent();

        // 根据请求构建新的json记录
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userInfo.getId());
        map.put("username", userInfo.getUserName());
        map.put("content", "<span style='color:red'>"+tdorderRequest.getOperateContent()+"</span>");
        map.put("date", new Date());

        JsonUtils jsonUtils = new JsonUtils();
        List<Object> list = new ArrayList<>();

        if ( StringUtils.isEmptyOrWhitespaceOnly( json ) ){
            list.add(map);
        }else{
            // 把原JSON转换成list
            list = jsonUtils.toObject(json, List.class);

            // 把新的json记录添加进原json
            list.add(map);

        }

        // 得到新拼接的JSON数据
        String newJson = jsonUtils.toJson(list);
        PayTdorder rec = new PayTdorder();
        rec.setId(tdorderRequest.getId());
        rec.setOperateContent(newJson);
        rec.setSinglestatus((byte)2);

        int res_tdorder = payTdorderMapper.updateByPrimaryKeySelective(rec);
        if ( res_tdorder != 1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单表失败，请联系管理员");
        }

        // 更新订单表状态
        PayOrderExample payOrderExample = new PayOrderExample();
        PayOrderExample.Criteria payOrderC = payOrderExample.createCriteria();
        payOrderC.andOrderidEqualTo(tdorderRequest.getOrderid());

        PayOrder payOrder = new PayOrder();
        payOrder.setSinglestatus((byte)6);
        int res_order = payOrderMapper.updateByExampleSelective(payOrder, payOrderExample);
        if ( res_order != 1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新订单表失败，请联系管理员");
        }

        return new JpfResponseDto();
    }
}
