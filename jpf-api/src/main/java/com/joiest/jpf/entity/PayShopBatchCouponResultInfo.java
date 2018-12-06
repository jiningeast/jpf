package com.joiest.jpf.entity;


import com.joiest.jpf.common.po.PayShopBatchCoupon;

import java.util.List;

/**
 * @Auther: admin
 * @Date: 2018/12/5 09:12
 * @Description:
 */
public class PayShopBatchCouponResultInfo {
    private Integer total;
    private List<PayShopBatchCouponResult> payShopBatchCoupons;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PayShopBatchCouponResult> getPayShopBatchCoupons() {
        return payShopBatchCoupons;
    }

    public void setPayShopBatchCoupons(List<PayShopBatchCouponResult> payShopBatchCoupons) {
        this.payShopBatchCoupons = payShopBatchCoupons;
    }
}
