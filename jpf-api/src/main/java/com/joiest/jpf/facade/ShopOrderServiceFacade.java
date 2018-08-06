package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;

public interface ShopOrderServiceFacade {

    /**
     * 公司列表---后台
     */
    public GetShopOrderResponse getList(GetShopOrderRequest request);


}
