package com.joiest.jpf.facade;

import com.joiest.jpf.dto.ShopBatchCouponResponse;

public interface ShopBatchCouponServiceFacade {

    /**
     * 根据批次号获取券
     */
    public ShopBatchCouponResponse getCouponByBatchId(String batchId, int page, int rows);
}
