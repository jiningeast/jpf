package com.joiest.jpf.facade;

import com.joiest.jpf.entity.ShopBatchCouponInterfaceInfo;

import java.util.List;

public interface ShopBatchCouponInterfaceServiceFacade {


    /**
     * 根据激活码获取券
     */
    public int getCouponByCouponNo(String couponNo,String uid );
}
