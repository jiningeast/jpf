package com.joiest.jpf.entity;


import java.util.List;

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
    private List<CouponNoList> couponNoList;
    private List<CouponYesList> couponYesList;


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

    public List<CouponNoList> getCouponNoList() {
        return couponNoList;
    }

    public void setCouponNoList(List<CouponNoList> couponNoList) {
        this.couponNoList = couponNoList;
    }

    public List<CouponYesList> getCouponYesList() {
        return couponYesList;
    }

    public void setCouponYesList(List<CouponYesList> couponYesList) {
        this.couponYesList = couponYesList;
    }
}
