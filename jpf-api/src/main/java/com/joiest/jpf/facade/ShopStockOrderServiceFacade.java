package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.dto.GetShopStockOrderRequest;
import com.joiest.jpf.dto.GetShopStockOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.entity.ShopStockOrderInfo;

public interface ShopStockOrderServiceFacade {

    /**
     * 公司列表---后台
     */
    public GetShopStockOrderResponse getList(GetShopStockOrderRequest request);

    /**
     * 公司列表---后台
     */
    public ShopStockOrderInfo getOne(String order_no);


}
