package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopOrder;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;

/**
 * 中欣卡业务Service
 * @author admin
 */
public interface ShopJoiestCardServiceFacade {

    /**
     * 中欣卡订单审核操作
     * @param request
     * @return
     */
    JpfResponseDto audit(PayShopOrder request) throws Exception;

    /**
     * 获取中欣卡订单列表
     * @param request
     * @return
     */
    GetShopOrderResponse getJoiestCardList(GetShopOrderRequest request);
}
