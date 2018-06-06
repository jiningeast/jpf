package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrdersInfo;

public interface OrdersServiceFacade {

    /**
     * 插入一条记录
     */
    public int insRecord(OrdersInfo ordersInfo);
}
