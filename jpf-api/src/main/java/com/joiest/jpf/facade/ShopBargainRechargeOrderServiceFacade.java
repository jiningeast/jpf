package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetShopBargainRechargeOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderResponse;

public interface ShopBargainRechargeOrderServiceFacade {

    /**
     * 获取敬恒订单
     */
    public GetShopBargainRechargeOrderResponse getRecords(GetShopBargainRechargeOrderRequest request);
}
