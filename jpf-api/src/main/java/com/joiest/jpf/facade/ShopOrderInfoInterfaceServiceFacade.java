package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceRequest;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceResponse;
import com.joiest.jpf.entity.ShopOrderInterfaceInfo;

import java.util.Date;
import java.util.List;

public interface ShopOrderInfoInterfaceServiceFacade {

    /**
     * 订单列表
     */
    public ShopOrderInfoInterfaceResponse getList(ShopOrderInfoInterfaceRequest request);

    /**
     * 获取单条订单信息
     */
    public ShopOrderInterfaceInfo getOne(ShopOrderInfoInterfaceRequest request);

    /**
     * 取消订单信息(适用于取消指定人的指定订单，需提供用户Id和订单号)
     */
    public JpfResponseDto shopOrderCancel(ShopOrderInfoInterfaceRequest request);

    /**
     * 检测订单并取消超时的订单(适用于由定时器监测到的超时订单)
     */
    void timerDetectShopOrderAndCancel(Date time);

    /**
     * 订单列表
     */
    public List<ShopOrderInterfaceInfo> getAbnormalOrders(ShopOrderInterfaceInfo request);

}
