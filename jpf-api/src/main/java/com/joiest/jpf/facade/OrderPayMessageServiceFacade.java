package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrderPayMessageInfo;

import java.util.List;

//支付回调流水
public interface OrderPayMessageServiceFacade {

    /**
     * 根据 orderid 获取订单支付流水
     */
    List<OrderPayMessageInfo> getOrderPayMessageListByOrderId(String orderid);

}
