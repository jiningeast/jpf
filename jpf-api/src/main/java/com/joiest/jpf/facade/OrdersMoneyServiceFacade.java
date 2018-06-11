package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrdersMoneyInfo;

public interface OrdersMoneyServiceFacade {

    /**
     * 根据orderid 获取订单费率详情
     */
    public OrdersMoneyInfo getOrdersMoneyInfo(Long orderid);
}
