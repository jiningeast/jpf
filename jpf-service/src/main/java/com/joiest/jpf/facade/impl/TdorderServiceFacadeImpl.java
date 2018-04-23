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
    public JpfResponseDto checkOk(TdorderRequest tdorderRequest){
        // 更新退单表tdorder->singlestatus字段为1：运营审核通过
        PayTdorderExample payTdorderExample = new PayTdorderExample();
        PayTdorderExample.Criteria payTdorderC = payTdorderExample.createCriteria();
        payTdorderC.andTdorderidEqualTo(tdorderRequest.getTdorderid());
        payTdorderC.andOrderidEqualTo(tdorderRequest.getOrderid());

        PayTdorder payTdorder = new PayTdorder();
        payTdorder.setSinglestatus((byte)1);

        int res_tdorder = payTdorderMapper.updateByExampleSelective(payTdorder, payTdorderExample);
        if ( res_tdorder == 1 ){
            // 增加退单记录表order_cpsingle一条记录，待财务审核
            PayOrderCpsingle payOrderCpsingle = new PayOrderCpsingle();
            payOrderCpsingle.setTdorderid(tdorderRequest.getTdorderid());
            payOrderCpsingle.setOrderid(tdorderRequest.getOrderid());
            payOrderCpsingle.setTdorderprice(tdorderRequest.getTdorderprice());
            payOrderCpsingle.setMtsid(tdorderRequest.getMtsid());
            payOrderCpsingle.setSingletype(tdorderRequest.getSingletype());
            payOrderCpsingle.setSinglestatus(tdorderRequest.getSinglestatus());

            int res_cp = payOrderCpsingleMapper.insertSelective(payOrderCpsingle);

            if ( res_cp == 1 ){
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
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单记录表失败，请联系管理员");
                }
            }else{
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单记录表失败，请联系管理员");
            }
        }else{
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单表失败，请联系管理员");
        }
    }

    @Override
    public JpfResponseDto checkNo(TdorderRequest tdorderRequest, UserInfo userInfo){

        // 查询该退单记录原先的operate_content
        PayTdorderExample payTdorderExample = new PayTdorderExample();
        PayTdorderExample.Criteria payTdorderC = payTdorderExample.createCriteria();
        payTdorderC.andTdorderidEqualTo(tdorderRequest.getTdorderid());
        payTdorderC.andOrderidEqualTo(tdorderRequest.getOrderid());

        PayTdorder payTdorder = payTdorderMapper.selectByPrimaryKey(tdorderRequest.getId());
        JsonUtils jsonUtils = new JsonUtils();
        List<Object> list = jsonUtils.toObject(payTdorder.getOperateContent(),List.class);  // 把原JSON转换成list

        // 构造本次请求的数据追加至list
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userInfo.getId());
        map.put("username", userInfo.getUserName());
        map.put("content", tdorderRequest.getOperateContent());
        map.put("date", new Date());
        list.add(map);
        // 追加完毕后再存成JSON
        String opCon = jsonUtils.toJson(list);

        // 更新退单表
        PayTdorder newPayTdorder = new PayTdorder();
        newPayTdorder.setOperateContent(opCon);
        newPayTdorder.setSinglestatus((byte)2);

        int res_tdorder = payTdorderMapper.updateByExampleSelective(newPayTdorder, payTdorderExample);
        if ( res_tdorder != 1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单表失败，请联系管理员");
        }

        return new JpfResponseDto();
    }
}
