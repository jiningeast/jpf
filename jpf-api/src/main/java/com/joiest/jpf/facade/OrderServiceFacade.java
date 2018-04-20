package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrderInfo;

import java.math.BigInteger;
import java.util.List;

public interface OrderServiceFacade {

    public Long getOrdersCount();

    public List<OrderInfo> getOrders(long page, long rows);

    public Boolean refund(BigInteger oid);
}
