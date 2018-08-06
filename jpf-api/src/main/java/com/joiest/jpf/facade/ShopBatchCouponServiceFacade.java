package com.joiest.jpf.facade;

import com.joiest.jpf.entity.ShopBatchCouponInfo;

import java.util.List;

public interface ShopBatchCouponServiceFacade {

    /**
     * 根据批次号获取券
     */
    public List<ShopBatchCouponInfo> getCouponByBatchId(String batchId);
}
