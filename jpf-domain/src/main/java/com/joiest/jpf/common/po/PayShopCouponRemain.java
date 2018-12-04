package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopCouponRemain implements Serializable {
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
     * 此券是否已用完 0=没用完 1=消费用完 2=过期清零 3=服务转让没用完 4=服务转让用完
     */
    private Byte status;

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

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getSalestatus() {
        return salestatus;
    }

    public void setSalestatus(Byte salestatus) {
        this.salestatus = salestatus;
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", couponId=").append(couponId);
        sb.append(", couponNo=").append(couponNo);
        sb.append(", couponActiveCode=").append(couponActiveCode);
        sb.append(", customerId=").append(customerId);
        sb.append(", couponDou=").append(couponDou);
        sb.append(", saleDouNo=").append(saleDouNo);
        sb.append(", couponDouLeft=").append(couponDouLeft);
        sb.append(", saleDouYes=").append(saleDouYes);
        sb.append(", saleDouLeft=").append(saleDouLeft);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", percent=").append(percent);
        sb.append(", status=").append(status);
        sb.append(", salestatus=").append(salestatus);
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     * @param that
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PayShopCouponRemain other = (PayShopCouponRemain) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCouponId() == null ? other.getCouponId() == null : this.getCouponId().equals(other.getCouponId()))
            && (this.getCouponNo() == null ? other.getCouponNo() == null : this.getCouponNo().equals(other.getCouponNo()))
            && (this.getCouponActiveCode() == null ? other.getCouponActiveCode() == null : this.getCouponActiveCode().equals(other.getCouponActiveCode()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCouponDou() == null ? other.getCouponDou() == null : this.getCouponDou().equals(other.getCouponDou()))
            && (this.getSaleDouNo() == null ? other.getSaleDouNo() == null : this.getSaleDouNo().equals(other.getSaleDouNo()))
            && (this.getCouponDouLeft() == null ? other.getCouponDouLeft() == null : this.getCouponDouLeft().equals(other.getCouponDouLeft()))
            && (this.getSaleDouYes() == null ? other.getSaleDouYes() == null : this.getSaleDouYes().equals(other.getSaleDouYes()))
            && (this.getSaleDouLeft() == null ? other.getSaleDouLeft() == null : this.getSaleDouLeft().equals(other.getSaleDouLeft()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getPercent() == null ? other.getPercent() == null : this.getPercent().equals(other.getPercent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getSalestatus() == null ? other.getSalestatus() == null : this.getSalestatus().equals(other.getSalestatus()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCouponId() == null) ? 0 : getCouponId().hashCode());
        result = prime * result + ((getCouponNo() == null) ? 0 : getCouponNo().hashCode());
        result = prime * result + ((getCouponActiveCode() == null) ? 0 : getCouponActiveCode().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCouponDou() == null) ? 0 : getCouponDou().hashCode());
        result = prime * result + ((getSaleDouNo() == null) ? 0 : getSaleDouNo().hashCode());
        result = prime * result + ((getCouponDouLeft() == null) ? 0 : getCouponDouLeft().hashCode());
        result = prime * result + ((getSaleDouYes() == null) ? 0 : getSaleDouYes().hashCode());
        result = prime * result + ((getSaleDouLeft() == null) ? 0 : getSaleDouLeft().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getPercent() == null) ? 0 : getPercent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getSalestatus() == null) ? 0 : getSalestatus().hashCode());
        return result;
    }
}