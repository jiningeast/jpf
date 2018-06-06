package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayOrders;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrdersMapper;
import com.joiest.jpf.entity.OrdersInfo;
import com.joiest.jpf.facade.OrdersServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class OrdersServiceFacadeImpl implements OrdersServiceFacade {

    @Autowired
    private PayOrdersMapper payOrdersMapper;

    /**
     * 插入一条记录
     */
    @Override
    public int insRecord(OrdersInfo ordersInfo){
        PayOrders payOrders = new PayOrders();
        BeanCopier beanCopier = BeanCopier.create(OrdersInfo.class, PayOrders.class, false);
        beanCopier.copy(ordersInfo, payOrders, null);

        return payOrdersMapper.insertSelective(payOrders);
    }
}
