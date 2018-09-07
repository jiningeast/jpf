package com.joiest.jpf.entity;

import java.util.Date;

public class ShopBatchCouponInfo {

    /**
     * id
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
     * 激活者手机号
     */
    private String activePhone;

    /**
     * 激活者姓名
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
     * 发送时间
     */
    private Date sendTime;

    /**
     * 发送方式 0=email发给接收人 1=群发给个人
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
     * 添加时间格式化
     */
    private String addtimeFormat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
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

    public String getActivePhone() {
        return activePhone;
    }

    public void setActivePhone(String activePhone) {
        this.activePhone = activePhone;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
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

    public String getAddtimeFormat() {
        return addtimeFormat;
    }

    public void setAddtimeFormat(String addtimeFormat) {
        this.addtimeFormat = addtimeFormat;
    }
}
