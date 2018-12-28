package com.joiest.jpf.common.custom;

import java.math.BigDecimal;

/**
 * 欣豆市场确认收入订单详情响应实体
 * @author admin 
 */
public class ShopIncomeConfirmationDetailResponse {

    /**
     * 欣豆
     */
    private BigDecimal dou;

    /**
     * 欣券号
     */
    private String couponNo;

    /**
     * 欣券余额
     */
    private BigDecimal couponBanlance;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 商户端欣券订单号
     */
    private String merchCouponOrderNo;

    /**
     * 商户端欣券订单内余额
     */
    private BigDecimal merchCouponOrderBanlance;

    /**
     * 商户名称(公司名称)
     */
    private String companyName;
    
    /**
     * 费率
     */
    private BigDecimal rate;

    public ShopIncomeConfirmationDetailResponse() {
    }

    public BigDecimal getDou() {
        return dou;
    }

    public void setDou(BigDecimal dou) {
        this.dou = dou;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public BigDecimal getCouponBanlance() {
        return couponBanlance;
    }

    public void setCouponBanlance(BigDecimal couponBanlance) {
        this.couponBanlance = couponBanlance;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getMerchCouponOrderNo() {
        return merchCouponOrderNo;
    }

    public void setMerchCouponOrderNo(String merchCouponOrderNo) {
        this.merchCouponOrderNo = merchCouponOrderNo;
    }

    public BigDecimal getMerchCouponOrderBanlance() {
        return merchCouponOrderBanlance;
    }

    public void setMerchCouponOrderBanlance(BigDecimal merchCouponOrderBanlance) {
        this.merchCouponOrderBanlance = merchCouponOrderBanlance;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
}
