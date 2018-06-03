package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayOrderCpMessage;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderCpMessageMapper;
import com.joiest.jpf.entity.OrderCpMessageInfo;
import com.joiest.jpf.facade.OrderCpMessageServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;

public class OrderCpMessageServiceFacadeImpl implements OrderCpMessageServiceFacade {

    @Autowired
    private PayOrderCpMessageMapper payOrderCpMessageMapper;

    @Override
    public int insRecord(OrderCpMessageInfo orderCpMessageInfo){
        orderCpMessageInfo.setAddtime(new Date());

        PayOrderCpMessage payOrderCpMessage = new PayOrderCpMessage();

        BeanCopier beanCopier = BeanCopier.create( OrderCpMessageInfo.class, PayOrderCpMessage.class, false);
        beanCopier.copy(orderCpMessageInfo, payOrderCpMessage, null);

        return payOrderCpMessageMapper.insertSelective(payOrderCpMessage);
    }
}
