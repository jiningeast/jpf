package com.joiest.jpf.facade;

import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;

public interface ShopBatchServiceFacade {

    /**
     * 获取批量欣券批次
     */
    public ShopBatchResponse getBatches(ShopBatchRequest shopBatchRequest);

    /**
     * 新增批次及券
     */
    public int addBatchCoupon(ShopBatchRequest shopBatchRequest);
}
