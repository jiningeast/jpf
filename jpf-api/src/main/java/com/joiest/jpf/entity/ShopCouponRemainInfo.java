package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ShopCouponRemainInfo {
    /**
     *
     */
    private String id;

    /**
     * 券id
     */
    private String couponId;

    /**
     * 券号
     */
    private String couponNo;

    /**
     * 券激活码
     */
    private String couponActiveCode;

    /**
     * 用户id
     */
    private String customerId;

    /**
     * 券本身值多少个豆
     */
    private BigDecimal couponDou;

    /**
     * 非转让豆
     */
    private BigDecimal saleDouNo;

    /**
     * 非转让券豆余额
     */
    private BigDecimal couponDouLeft;

    /**
     * 可转让豆
     */
    private BigDecimal saleDouYes;

    /**
     * 可转让豆剩余
     */
    private BigDecimal saleDouLeft;

    /**
     * 此券是否已用完 0=没用完 1=消费用完 2=过期清零
     */
    private Byte status;

    /**
     * 到期时间
     */
    private Date expireTime;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;



    /**
     * 转让比例
     */
    private BigDecimal percent;

    /**
     * 转让状态:0没用完;1已用完;2过期清零
     */
    private Byte salestatus;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId == null ? null : couponId.trim();
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo == null ? null : couponNo.trim();
    }

    public String getCouponActiveCode() {
        return couponActiveCode;
    }

    public void setCouponActiveCode(String couponActiveCode) {
        this.couponActiveCode = couponActiveCode == null ? null : couponActiveCode.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }



    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public BigDecimal getCouponDou() {
        return couponDou;
    }

    public void setCouponDou(BigDecimal couponDou) {
        this.couponDou = couponDou;
    }

    public BigDecimal getSaleDouNo() {
        return saleDouNo;
    }

    public void setSaleDouNo(BigDecimal saleDouNo) {
        this.saleDouNo = saleDouNo;
    }

    public BigDecimal getCouponDouLeft() {
        return couponDouLeft;
    }

    public void setCouponDouLeft(BigDecimal couponDouLeft) {
        this.couponDouLeft = couponDouLeft;
    }

    public BigDecimal getSaleDouYes() {
        return saleDouYes;
    }

    public void setSaleDouYes(BigDecimal saleDouYes) {
        this.saleDouYes = saleDouYes;
    }

    public BigDecimal getSaleDouLeft() {
        return saleDouLeft;
    }

    public void setSaleDouLeft(BigDecimal saleDouLeft) {
        this.saleDouLeft = saleDouLeft;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Byte getSalestatus() {
        return salestatus;
    }

    public void setSalestatus(Byte salestatus) {
        this.salestatus = salestatus;
    }
}
