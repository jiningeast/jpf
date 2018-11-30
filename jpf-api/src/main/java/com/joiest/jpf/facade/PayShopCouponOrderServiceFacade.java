package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CouponOrderList;

/**
 * @Auther: admin
 * @Date: 2018/11/30 10:04
 * @Description:
 */

public interface PayShopCouponOrderServiceFacade {
    /**
     * 保存前台提交的订单
     * @param couponOrderList
     */
    void saveCouponOrder(CouponOrderList couponOrderList);
}
