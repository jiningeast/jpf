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
     * 券面值
     */
    private BigDecimal couponMoney;

    /**
     * 券余额
     */
    private BigDecimal couponMoneyLeft;

    /**
     * 此券是否已用完 0=没用完 1=已用完
     */
    private Byte status;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

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

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public BigDecimal getCouponMoneyLeft() {
        return couponMoneyLeft;
    }

    public void setCouponMoneyLeft(BigDecimal couponMoneyLeft) {
        this.couponMoneyLeft = couponMoneyLeft;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        sb.append(", couponMoney=").append(couponMoney);
        sb.append(", couponMoneyLeft=").append(couponMoneyLeft);
        sb.append(", status=").append(status);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
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
            && (this.getCouponMoney() == null ? other.getCouponMoney() == null : this.getCouponMoney().equals(other.getCouponMoney()))
            && (this.getCouponMoneyLeft() == null ? other.getCouponMoneyLeft() == null : this.getCouponMoneyLeft().equals(other.getCouponMoneyLeft()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
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
        result = prime * result + ((getCouponMoney() == null) ? 0 : getCouponMoney().hashCode());
        result = prime * result + ((getCouponMoneyLeft() == null) ? 0 : getCouponMoneyLeft().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}