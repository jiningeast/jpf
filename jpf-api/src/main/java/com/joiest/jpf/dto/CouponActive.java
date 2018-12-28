package com.joiest.jpf.dto;

import org.springframework.stereotype.Controller;

/**
 * @Auther: admin
 * @Date: 2018/12/25 16:32
 * @Description:
 */
public class CouponActive {
    private String id;
    private String totalSaleDouNo;
    private String totalSaleDouYes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
