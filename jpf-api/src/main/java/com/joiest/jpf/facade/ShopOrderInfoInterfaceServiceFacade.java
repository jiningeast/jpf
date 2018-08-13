package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceRequest;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.entity.ShopOrderInfoInterface;

public interface ShopOrderInfoInterfaceServiceFacade {

    /**
     * 订单列表
     */
    public ShopOrderInfoInterfaceResponse getList(ShopOrderInfoInterfaceRequest request);

    /**
     * 获取单条订单信息
     */
    public ShopOrderInfoInterface getOne(ShopOrderInfoInterfaceRequest request);

    /**
     * 取消订单信息
     */
    public JpfResponseDto shopOrderCancel(ShopOrderInfoInterfaceRequest request);

}
