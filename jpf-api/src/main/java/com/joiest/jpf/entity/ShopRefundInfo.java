package com.joiest.jpf.entity;

import java.util.List;
import java.util.Map;

/**
 * @Author zhangmeng
 * @Create 2018/12/25
 * @Description 退款参数
 */
public class ShopRefundInfo {
    private String customerId;
    private String orderId;
    private String totalSaleDouNo;
    private String totalSaleDouYes;
    private String source;
    private String subDate;
    private List<CouponListInfo> CouponList;


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTotalSaleDouNo() {
        return totalSaleDouNo;
    }

    public void setTotalSaleDouNo(String totalSaleDouNo) {
        this.totalSaleDouNo = totalSaleDouNo;
    }

    public String getTotalSaleDouYes() {
        return totalSaleDouYes;
    }

    public void setTotalSaleDouYes(String totalSaleDouYes) {
        this.totalSaleDouYes = totalSaleDouYes;
    }

    public String getSubDate() {
        return subDate;
    }

    public void setSubDate(String subDate) {
        this.subDate = subDate;
    }

    public List<CouponListInfo> getCouponList() {
        return CouponList;
    }

    public void setCouponList(List<CouponListInfo> couponList) {
        CouponList = couponList;
    }
}
