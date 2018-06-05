package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrderPayMerMessageInfo;
import java.util.List;

//支付回调流水
public interface OrderPayMerMessageServiceFacade {

    /**
     * 根据 orderid 获取订单支付流水
     */
    List<OrderPayMerMessageInfo> getOrderPayMerMessageListByOrderId(String orderid);

}
