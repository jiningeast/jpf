package com.joiest.jpf.facade;

public interface ShopCouponRemainServiceFacade {

    /**
     * 处理过期的券
     */
    public int dealAllExpiredCoupon();

    /**
     * 处理个人的过期的券
     */
    public int dealCustomerExpiredCoupon(String customerId);
}
