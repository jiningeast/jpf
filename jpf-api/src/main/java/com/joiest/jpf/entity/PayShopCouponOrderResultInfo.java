package com.joiest.jpf.entity;


import com.joiest.jpf.common.po.PayShopCouponOrder;

import java.util.List;

/**
 * @Auther: admin
 * @Date: 2018/12/5 09:27
 * @Description:
 */
public class PayShopCouponOrderResultInfo {

    private List<PayShopCouponOrder> payShopCouponOrderResults;
    private Integer total;

    public List<PayShopCouponOrder> getPayShopCouponOrderResults() {
        return payShopCouponOrderResults;
    }

    public void setPayShopCouponOrderResults(List<PayShopCouponOrder> payShopCouponOrderResults) {
        this.payShopCouponOrderResults = payShopCouponOrderResults;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
