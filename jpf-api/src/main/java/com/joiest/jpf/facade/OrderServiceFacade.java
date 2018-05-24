package com.joiest.jpf.facade;

import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.entity.OrderInfo;

import java.util.Map;

public interface OrderServiceFacade {

    public Long getOrdersCount();

    public OrderResponse getOrders(OrderRequest orderRequest);

    public Map<String, Object> getStatistics(OrderRequest orderRequest);

    public int insOrder(OrderInfo orderInfo);
}
