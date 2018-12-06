package com.joiest.jpf.entity;

import com.joiest.jpf.common.po.PayShopBatchCoupon;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: admin
 * @Date: 2018/12/5 18:33
 * @Description:
 */

public class PayShopBatchCouponResult {

    private String useName;

    private String phone;

    private String idcard;
    /**
     *
     */
    private String id;

    /**
     *
     */
    private String batchId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     *
     */
    private String companyId;

    /**
     *
     */
    private String companyName;

    /**
     * 券号：CP+3位随机数+毫秒时间戳+3位随机数
     */
    private String couponNo;

    /**
     * 券激活码：32位数字和英文组合，去掉
     小写字母o 大写字母O 数字0
     小写字母s 大写字母S 数字5
     小写字母i 大写字母I 数字1
     小写字符z 大写字母Z 数字2
     */
    private String activeCode;

    /**
     * 面值
     */
    private Integer money;

    /**
     * 对应豆数量
     */
    private BigDecimal dou;

    /**
     * 是否激活 0=未激活 1=已激活
     */
    private Byte isActive;

    /**
     * 激活人id
     */
    private String activeCustomerId;

    /**
     * 激活者手机号
     */
    private String activePhone;

    /**
     * 激活人姓名
     */
    private String activeName;

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
     * 券状态 0=未过期 1=已过期
     */
    private Byte isExpired;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 发送方式 0=email发给接收人 1=群发给个人并激活 2=群发给个人不激活
     */
    private Byte sendType;

    /**
     *
     */
    private Date addtime;

    /**
     *
     */
    private Date updatetime;

    /**
     * 当前券的状态 0 申请中  1申请完成
     */
    private Byte status;

    /**
     * 订单号
     */
    private String orderId;

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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
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

    public BigDecimal getDou() {
        return dou;
    }

    public void setDou(BigDecimal dou) {
        this.dou = dou;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public String getActiveCustomerId() {
        return activeCustomerId;
    }

    public void setActiveCustomerId(String activeCustomerId) {
        this.activeCustomerId = activeCustomerId == null ? null : activeCustomerId.trim();
    }

    public String getActivePhone() {
        return activePhone;
    }

    public void setActivePhone(String activePhone) {
        this.activePhone = activePhone == null ? null : activePhone.trim();
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName == null ? null : activeName.trim();
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

    public Byte getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(Byte isExpired) {
        this.isExpired = isExpired;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Byte getSendType() {
        return sendType;
    }

    public void setSendType(Byte sendType) {
        this.sendType = sendType;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
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
        sb.append(", batchNo=").append(batchNo);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", couponNo=").append(couponNo);
        sb.append(", activeCode=").append(activeCode);
        sb.append(", money=").append(money);
        sb.append(", dou=").append(dou);
        sb.append(", isActive=").append(isActive);
        sb.append(", activeCustomerId=").append(activeCustomerId);
        sb.append(", activePhone=").append(activePhone);
        sb.append(", activeName=").append(activeName);
        sb.append(", activeTime=").append(activeTime);
        sb.append(", expireMonth=").append(expireMonth);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", isExpired=").append(isExpired);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", sendType=").append(sendType);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", status=").append(status);
        sb.append(", orderId=").append(orderId);
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
                && (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
                && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
                && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
                && (this.getCouponNo() == null ? other.getCouponNo() == null : this.getCouponNo().equals(other.getCouponNo()))
                && (this.getActiveCode() == null ? other.getActiveCode() == null : this.getActiveCode().equals(other.getActiveCode()))
                && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
                && (this.getDou() == null ? other.getDou() == null : this.getDou().equals(other.getDou()))
                && (this.getIsActive() == null ? other.getIsActive() == null : this.getIsActive().equals(other.getIsActive()))
                && (this.getActiveCustomerId() == null ? other.getActiveCustomerId() == null : this.getActiveCustomerId().equals(other.getActiveCustomerId()))
                && (this.getActivePhone() == null ? other.getActivePhone() == null : this.getActivePhone().equals(other.getActivePhone()))
                && (this.getActiveName() == null ? other.getActiveName() == null : this.getActiveName().equals(other.getActiveName()))
                && (this.getActiveTime() == null ? other.getActiveTime() == null : this.getActiveTime().equals(other.getActiveTime()))
                && (this.getExpireMonth() == null ? other.getExpireMonth() == null : this.getExpireMonth().equals(other.getExpireMonth()))
                && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
                && (this.getIsExpired() == null ? other.getIsExpired() == null : this.getIsExpired().equals(other.getIsExpired()))
                && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
                && (this.getSendType() == null ? other.getSendType() == null : this.getSendType().equals(other.getSendType()))
                && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
                && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()));
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
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCouponNo() == null) ? 0 : getCouponNo().hashCode());
        result = prime * result + ((getActiveCode() == null) ? 0 : getActiveCode().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getDou() == null) ? 0 : getDou().hashCode());
        result = prime * result + ((getIsActive() == null) ? 0 : getIsActive().hashCode());
        result = prime * result + ((getActiveCustomerId() == null) ? 0 : getActiveCustomerId().hashCode());
        result = prime * result + ((getActivePhone() == null) ? 0 : getActivePhone().hashCode());
        result = prime * result + ((getActiveName() == null) ? 0 : getActiveName().hashCode());
        result = prime * result + ((getActiveTime() == null) ? 0 : getActiveTime().hashCode());
        result = prime * result + ((getExpireMonth() == null) ? 0 : getExpireMonth().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getIsExpired() == null) ? 0 : getIsExpired().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getSendType() == null) ? 0 : getSendType().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        return result;
    }
}
