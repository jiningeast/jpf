package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetOrdersRequest;
import com.joiest.jpf.dto.GetOrdersResponse;
import com.joiest.jpf.entity.OrdersInfo;


public interface OrdersServiceFacade {

    /**
     * 插入一条记录
     */
    public int insRecord(OrdersInfo ordersInfo);

    /**
     * 列表---后台
     */
    public GetOrdersResponse getOrdersList(GetOrdersRequest request);

}
