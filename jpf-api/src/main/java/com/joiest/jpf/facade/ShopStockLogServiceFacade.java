package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetShopStockLogRequest;
import com.joiest.jpf.dto.GetShopStockLogResponse;

public interface ShopStockLogServiceFacade {

    /**
     * 进销存列表---后台
     */
    public GetShopStockLogResponse getList(GetShopStockLogRequest request);



}
