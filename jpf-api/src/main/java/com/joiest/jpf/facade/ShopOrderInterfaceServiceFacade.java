package com.joiest.jpf.facade;

import com.joiest.jpf.entity.ShopOrderInterfaceInfo;

import java.util.Map;

public interface ShopOrderInterfaceServiceFacade {

    /**
     * 添加订单
     */
    public int addOrder(ShopOrderInterfaceInfo info);

    /**
     * 获取单条订单信息 by uid
     */
    public ShopOrderInterfaceInfo getOrderOne(String orderNo, String uid);

    /**
     * 获取单条订单信息 by uid
     */
    public ShopOrderInterfaceInfo getOrder(String orderNo);


    /**
     * 获取单条订单信息 by uid
     */
    public ShopOrderInterfaceInfo getOrderByOrderNo(String orderNo);

    /**
     * 获取用户订单数量，已取消的不统计
     */
    public int getOrdersCount(String customerId);

    public int updateOrder(ShopOrderInterfaceInfo info);

    /**
     * 取消订单消费的豆
     */
    public Map<String,String> cancelOrderDou(String orderNo);
    /**
     * 取消订单消费转让比例的豆
     */
    public Map<String,String> cancelOrderDouSale(String orderNo);
}
