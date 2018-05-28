package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrderRefundInfo;

public interface OrderRefundServiceFacade {

    public int insOrderRefund(OrderRefundInfo orderRefundInfo);

    /**
     * 根据 orderid 获取商户信息&产品信息&订单支付方式
     */
    public OrderRefundInfo getOrderRefund(String refundOrderid);


}
