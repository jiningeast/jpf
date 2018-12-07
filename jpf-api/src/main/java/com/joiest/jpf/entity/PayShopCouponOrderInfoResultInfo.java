package com.joiest.jpf.entity;


import com.joiest.jpf.common.po.PayShopCouponOrderInfo;

import java.util.List;

/**
 * @Auther: admin
 * @Date: 2018/12/5 09:22
 * @Description:
 */
public class PayShopCouponOrderInfoResultInfo {
    private List<PayShopCouponOrderInfo> payShopCouponOrderInfos;
    private Integer total;

    public List<PayShopCouponOrderInfo> getPayShopCouponOrderInfos() {
        return payShopCouponOrderInfos;
    }

    public void setPayShopCouponOrderInfos(List<PayShopCouponOrderInfo> payShopCouponOrderInfos) {
        this.payShopCouponOrderInfos = payShopCouponOrderInfos;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
