package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrderYinjiaApiInfo;

public interface OrderYinjiaApiServiceFacade {

    public int insOrder(OrderYinjiaApiInfo orderYinjiaApiInfo);

    public OrderYinjiaApiInfo getOrderByOrderidAndForeignOrderid(String orderid, String platformOrderid, boolean forInterface);

    public OrderYinjiaApiInfo getOrderByOrderid(String orderid, boolean forInterface);

    public int updataSignOrderid(OrderYinjiaApiInfo orderYinjiaApiInfo);

    public int updateColumnByOrderid(OrderYinjiaApiInfo orderYinjiaApiInfo);

    public int updateOrdername(OrderYinjiaApiInfo orderYinjiaApiInfo, boolean forInterface);
}
