package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrderPayMessageInfo;

import java.util.List;

//支付回调流水
public interface OrderPayMessageServiceFacade {

    /**
     * 根据 orderid 获取订单支付同步流水 同步信息
     */
    List<OrderPayMessageInfo> getOrderPayMessageReturnListByOrderId(String orderid);
    /**
     * 根据 orderid 获取订单支付回调流水 异步信息
     */
    List<OrderPayMessageInfo> getOrderPayMessageNotifyListByOrderId(String orderid);

}
