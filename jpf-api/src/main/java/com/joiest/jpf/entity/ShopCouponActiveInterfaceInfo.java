package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ShopCouponActiveInterfaceInfo {
    /**
     *
     */
    private String id;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 顾客姓名
     */
    private String customerName;

    /**
     * 企业id
     */
    private Integer companyId;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 批次id
     */
    private Integer batchId;

    /**
     * 批次号
     */
    private Integer batchNo;

    /**
     * 券号
     */
    private String couponNo;

    /**
     * 激活码
     */
    private String activeCode;

    /**
     * 支付方式 0=欣豆 1=微信
     */
    private Byte payWay;

    /**
     * 面值
     */
    private BigDecimal money;

    /**
     * 豆数量
     */
    private BigDecimal dou;

    /**
     * 根据行为不同显示的内容不同
     */
    private String content;

    /**
     * 行为 0=激活 1=消费 2=退豆 3=过期
     */
    private String type;

    /**
     * 到期时间
     */
    private Date expireTime;

    /**
     * 消费时对应订单表的id
     */
    private String orderId;

    /**
     *
     */
    private Date addtime;

    /**
     *
     */
    private Date updatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Integer batchNo) {
        this.batchNo = batchNo;
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

    public Byte getPayWay() {
        return payWay;
    }

    public void setPayWay(Byte payWay) {
        this.payWay = payWay;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getDou() {
        return dou;
    }

    public void setDou(BigDecimal dou) {
        this.dou = dou;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}
