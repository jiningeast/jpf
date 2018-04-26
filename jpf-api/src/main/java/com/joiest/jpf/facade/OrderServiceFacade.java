package com.joiest.jpf.facade;

import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.entity.OrderInfo;

import java.math.BigInteger;
import java.util.List;

public interface OrderServiceFacade {

    public Long getOrdersCount();

    public OrderResponse getOrders(OrderRequest orderRequest);

    public Boolean refund(BigInteger oid);
}
