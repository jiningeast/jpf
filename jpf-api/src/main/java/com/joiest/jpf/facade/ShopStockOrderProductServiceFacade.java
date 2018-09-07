package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetShopStockOrderProductResponse;

public interface ShopStockOrderProductServiceFacade {


    /**
     * 采购订单详情---后台
     */
    public GetShopStockOrderProductResponse getProduct(String OrderNo);


}
