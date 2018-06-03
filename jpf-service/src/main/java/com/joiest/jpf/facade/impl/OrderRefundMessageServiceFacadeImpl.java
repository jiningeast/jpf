package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayOrderRefundMessage;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderRefundMessageMapper;
import com.joiest.jpf.entity.OrderRefundMessageInfo;
import com.joiest.jpf.facade.OrderRefundMessageServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;

public class OrderRefundMessageServiceFacadeImpl implements OrderRefundMessageServiceFacade {

    @Autowired
    private PayOrderRefundMessageMapper payOrderRefundMessageMapper;

    @Override
    public int insRecord(OrderRefundMessageInfo orderRefundMessageInfo){
        orderRefundMessageInfo.setAddtime(new Date());
        PayOrderRefundMessage payOrderRefundMessage = new PayOrderRefundMessage();
        BeanCopier beanCopier = BeanCopier.create( OrderRefundMessageInfo.class, PayOrderRefundMessage.class, false);
        beanCopier.copy(orderRefundMessageInfo, payOrderRefundMessage, null);

        return payOrderRefundMessageMapper.insertSelective(payOrderRefundMessage);
    }
}
