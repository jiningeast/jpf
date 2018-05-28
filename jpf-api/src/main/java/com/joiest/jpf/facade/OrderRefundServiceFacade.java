package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrderRefundInfo;

public interface OrderRefundServiceFacade {

    /*
    * 新增退单记录
    * */
    public int insOrderRefund(OrderRefundInfo orderRefundInfo);

    /**
     * 根据退单号获取退单信息
     */
    public OrderRefundInfo getOrderRefund(String refundOrderid);

    /**
     * 根据退单号更新退单信息
     */
    public int upOrderRefundByRefundOrder(OrderRefundInfo orderRefundInfo);


}
