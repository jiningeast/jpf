package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayShopBatchCoupon implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 
     */
    private String batchId;

    /**
     * 
     */
    private String companyId;

    /**
     * 
     */
    private String companyName;

    /**
     * 券号
     */
    private String couponNo;

    /**
     * 激活码
     */
    private String activeCode;

    /**
     * 面值
     */
    private Integer money;

    /**
     * 对应豆数量
     */
    private Integer dou;

    /**
     * 是否激活 0=未激活 1=已激活
     */
    private Byte isActive;

    /**
     * 激活时间
     */
    private Date activeTime;

    /**
     * 有效期，单位月
     */
    private Integer expireMonth;

    /**
     * 到期时间
     */
    private Date expireTime;

    /**
     * 
     */
    private Date addtime;

    /**
     * 
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo == null ? null : couponNo.trim();
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode == null ? null : activeCode.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getDou() {
        return dou;
    }

    public void setDou(Integer dou) {
        this.dou = dou;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Integer getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(Integer expireMonth) {
        this.expireMonth = expireMonth;
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
        sb.append(", batchId=").append(batchId);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", couponNo=").append(couponNo);
        sb.append(", activeCode=").append(activeCode);
        sb.append(", money=").append(money);
        sb.append(", dou=").append(dou);
        sb.append(", isActive=").append(isActive);
        sb.append(", activeTime=").append(activeTime);
        sb.append(", expireMonth=").append(expireMonth);
        sb.append(", expireTime=").append(expireTime);
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
        PayShopBatchCoupon other = (PayShopBatchCoupon) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBatchId() == null ? other.getBatchId() == null : this.getBatchId().equals(other.getBatchId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getCouponNo() == null ? other.getCouponNo() == null : this.getCouponNo().equals(other.getCouponNo()))
            && (this.getActiveCode() == null ? other.getActiveCode() == null : this.getActiveCode().equals(other.getActiveCode()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getDou() == null ? other.getDou() == null : this.getDou().equals(other.getDou()))
            && (this.getIsActive() == null ? other.getIsActive() == null : this.getIsActive().equals(other.getIsActive()))
            && (this.getActiveTime() == null ? other.getActiveTime() == null : this.getActiveTime().equals(other.getActiveTime()))
            && (this.getExpireMonth() == null ? other.getExpireMonth() == null : this.getExpireMonth().equals(other.getExpireMonth()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
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
        result = prime * result + ((getBatchId() == null) ? 0 : getBatchId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCouponNo() == null) ? 0 : getCouponNo().hashCode());
        result = prime * result + ((getActiveCode() == null) ? 0 : getActiveCode().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getDou() == null) ? 0 : getDou().hashCode());
        result = prime * result + ((getIsActive() == null) ? 0 : getIsActive().hashCode());
        result = prime * result + ((getActiveTime() == null) ? 0 : getActiveTime().hashCode());
        result = prime * result + ((getExpireMonth() == null) ? 0 : getExpireMonth().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}