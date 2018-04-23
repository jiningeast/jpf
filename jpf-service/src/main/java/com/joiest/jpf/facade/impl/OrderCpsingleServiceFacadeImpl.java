package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayOrder;
import com.joiest.jpf.common.po.PayOrderCpsingle;
import com.joiest.jpf.common.po.PayOrderCpsingleExample;
import com.joiest.jpf.common.po.PayOrderExample;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderCpsingleMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderMapper;
import com.joiest.jpf.dto.OrderCpsingleRequest;
import com.joiest.jpf.entity.OrderCpsingleInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.OrderCpsingleServiceFacade;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;

public class OrderCpsingleServiceFacadeImpl implements OrderCpsingleServiceFacade {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private PayOrderCpsingleMapper payOrderCpsingleMapper;

    @Override
    public int getCpsCount(){
        PayOrderCpsingleExample e = new PayOrderCpsingleExample();
        return payOrderCpsingleMapper.countByExample(e);
    }

    @Override
    public List<OrderCpsingleInfo> getCps(long page, long rows){
        PayOrderCpsingleExample e = new PayOrderCpsingleExample();
        e.setPageNo(page);
        e.setPageSize(rows);
        List<PayOrderCpsingle> list = payOrderCpsingleMapper.selectByExample(e);
        List<OrderCpsingleInfo> infos = new ArrayList<>();
        for (PayOrderCpsingle payOrderCpsingle:list){
            OrderCpsingleInfo orderCpsingleInfo = new OrderCpsingleInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrderCpsingle.class, OrderCpsingleInfo.class, false);
            beanCopier.copy(payOrderCpsingle, orderCpsingleInfo, null);
            infos.add(orderCpsingleInfo);
        }

        return infos;
    }

    @Override
    public int insRecord(OrderCpsingleInfo info){
        PayOrderCpsingle payOrderCpsingle = new PayOrderCpsingle();

        payOrderCpsingle.setTdorderid(info.getTdorderid());
        payOrderCpsingle.setOrderid(info.getOrderid());
        payOrderCpsingle.setTdorderprice(info.getTdorderprice());
        payOrderCpsingle.setMtsid(info.getMtsid());
        payOrderCpsingle.setSingletype(info.getSingletype());
        payOrderCpsingle.setSinglestatus(info.getSinglestatus());
        payOrderCpsingle.setAddtime(new Date());

        int res = payOrderCpsingleMapper.insertSelective(payOrderCpsingle);
        return res;
    }

    @Override
    public JpfResponseDto checkOk(long tdorderid, long orderid){
        // 更新退款记录表order_cpsingle->singlestatus字段为1：退款处理成功
        PayOrderCpsingleExample payOrderCpsingleExample = new PayOrderCpsingleExample();
        PayOrderCpsingleExample.Criteria payOrderCpsingleC = payOrderCpsingleExample.createCriteria();
        payOrderCpsingleC.andTdorderidEqualTo(tdorderid);
        payOrderCpsingleC.andOrderidEqualTo(orderid);

        PayOrderCpsingle payOrderCpsingle = new PayOrderCpsingle();
        payOrderCpsingle.setSinglestatus((byte)1);
        int res_orderCpsingle = payOrderCpsingleMapper.updateByExampleSelective(payOrderCpsingle, payOrderCpsingleExample);
        if ( res_orderCpsingle == 1 ){
            // 更新订单表order->singlestatus字段为5：退款处理完成
            PayOrderExample payOrderExample = new PayOrderExample();
            PayOrderExample.Criteria payOrderC = payOrderExample.createCriteria();
            payOrderC.andOrderidEqualTo(orderid);

            PayOrder payOrder = new PayOrder();
            payOrder.setSinglestatus((byte)5);
            int res_order = payOrderMapper.updateByExampleSelective(payOrder, payOrderExample);
            if ( res_order == 1 ){
                return new JpfResponseDto();
            }else{
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新订单表失败，请联系管理员");
            }
        }else{
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单记录表失败，请联系管理员");
        }
    }

    @Override
    public JpfResponseDto checkNo(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo){
        // 查询此单的原operate_content审核内容字段
        PayOrderCpsingle payOrderCpsingle = payOrderCpsingleMapper.selectByPrimaryKey(orderCpsingleRequest.getId());
        String json = payOrderCpsingle.getOperateContent();

        // 根据请求构建新的json记录
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userInfo.getId());
        map.put("username", userInfo.getUserName());
        map.put("content", orderCpsingleRequest.getOperateContent());
        map.put("date", new Date());

        JsonUtils jsonUtils = new JsonUtils();
        List<Object> list = new ArrayList<>();

        if ( StringUtils.isEmptyOrWhitespaceOnly( json )){
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
        PayOrderCpsingle rec = new PayOrderCpsingle();
        rec.setId(orderCpsingleRequest.getId());
        rec.setOperateContent(newJson);
        rec.setSinglestatus((byte)2);
        int res = payOrderCpsingleMapper.updateByPrimaryKeySelective(rec);
        if ( res != 1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新退单表失败，请联系管理员");
        }

        return new JpfResponseDto();
    }
}
