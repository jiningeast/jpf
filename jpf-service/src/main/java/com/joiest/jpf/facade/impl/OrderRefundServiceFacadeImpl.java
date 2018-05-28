package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.PayOrderRefund;
import com.joiest.jpf.common.po.PayOrderRefundExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderRefundMapper;
import com.joiest.jpf.entity.OrderRefundInfo;
import com.joiest.jpf.facade.OrderRefundServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class OrderRefundServiceFacadeImpl implements OrderRefundServiceFacade {

    @Autowired
    private PayOrderRefundMapper payOrderRefundMapper;

    @Override
    public int insOrderRefund(OrderRefundInfo orderRefundInfo)
    {

        PayOrderRefund orderRe = new PayOrderRefund();

        orderRe.setOrderid(orderRefundInfo.getOrderid());
        orderRe.setRefundOrderid(orderRefundInfo.getRefundOrderid());
        orderRe.setMoney(orderRefundInfo.getMoney());
        orderRe.setStatus(orderRefundInfo.getStatus());
        orderRe.setBackurl(orderRefundInfo.getBackurl());
        orderRe.setCreated(orderRefundInfo.getCreated());

        return payOrderRefundMapper.insertSelective(orderRe);
    }
    @Override
    public OrderRefundInfo getOrderRefund(String refundOrderid){


        PayOrderRefundExample example = new PayOrderRefundExample();
        PayOrderRefundExample.Criteria c = example.createCriteria();

        List<PayOrderRefund> orderRefundlist = payOrderRefundMapper.selectByExample(example);
        if ( orderRefundlist == null || orderRefundlist.isEmpty())
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.RECORD_NOT_EXIST.getCode(), "退单信息不存在");
        }
        PayOrderRefund payOrderRefund = orderRefundlist.get(0);

        OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
        BeanCopier beanCopier = BeanCopier.create( PayOrderRefund.class, OrderRefundInfo.class, false);
        beanCopier.copy(payOrderRefund, orderRefundInfo, null);

        return orderRefundInfo;
    }
   /* @Override
    public OrderInfo getOrderByOrderid(String orderid, boolean forInterface){
        PayOrderExample e = new PayOrderExample();
        PayOrderExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(orderid);

        List<PayOrder> list = payOrderMapper.selectByExample(e);

        if ( forInterface && list == null){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(), JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());
        }

        OrderInfo orderInfo = new OrderInfo();
        BeanCopier beanCopier = BeanCopier.create(PayOrder.class, OrderInfo.class, false);
        beanCopier.copy(list.get(0), orderInfo, null);

        return orderInfo;
    }*/
}
