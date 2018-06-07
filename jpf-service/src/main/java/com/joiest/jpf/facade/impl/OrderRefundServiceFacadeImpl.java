package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.PayOrderRefund;
import com.joiest.jpf.common.po.PayOrderRefundExample;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderRefundMapper;
import com.joiest.jpf.entity.OrderRefundInfo;
import com.joiest.jpf.facade.OrderRefundServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;
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
        orderRe.setReturnContent(orderRefundInfo.getReturnContent());

        return payOrderRefundMapper.insertSelective(orderRe);
    }
    @Override
    public OrderRefundInfo getOrderRefund(String refundOrderid){

        PayOrderRefundExample example = new PayOrderRefundExample();
        PayOrderRefundExample.Criteria c = example.createCriteria();
        c.andRefundOrderidEqualTo(refundOrderid);
        List<PayOrderRefund> orderRefundlist = payOrderRefundMapper.selectByExample(example);
        if ( orderRefundlist == null || orderRefundlist.isEmpty())
        {
            return null;
            //throw new JpfInterfaceException(JpfInterfaceErrorInfo.RECORD_NOT_EXIST.getCode(), "退单信息不存在");
        }
        PayOrderRefund payOrderRefund = orderRefundlist.get(0);

        OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
        BeanCopier beanCopier = BeanCopier.create( PayOrderRefund.class, OrderRefundInfo.class, false);
        beanCopier.copy(payOrderRefund, orderRefundInfo, null);

        return orderRefundInfo;
    }
    public int upOrderRefundByRefundOrder(OrderRefundInfo orderRefundInfo){

        PayOrderRefundExample example = new PayOrderRefundExample();
        PayOrderRefundExample.Criteria c = example.createCriteria();
        c.andRefundOrderidEqualTo(orderRefundInfo.getRefundOrderid());

        PayOrderRefund orderRe = new PayOrderRefund();

        orderRe.setTranno(orderRefundInfo.getTranno());
        orderRe.setTrantype(orderRefundInfo.getTrantype());
        orderRe.setNotifyTime(orderRefundInfo.getNotifyTime());
        orderRe.setResponsParam(orderRefundInfo.getResponsParam());
        //orderRefundInfo.setRefundOrderid(orderRefundInfo.getRefundOrderid());
        orderRe.setStatus(orderRefundInfo.getStatus());

        return payOrderRefundMapper.updateByExampleSelective(orderRe, example);
    }

}
