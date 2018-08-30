package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ShopStockCardInfo {

    /**
     *
     */
    private String id;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 卡密
     */
    private String cardPass;

    /**
     * 有效期，单位月
     */
    private Integer expireMonth;

    /**
     * 到期时间
     */
    private Date expireDate;

    /**
     * 进价
     */
    private BigDecimal bid;

    /**
     * 采购订单id
     */
    private String stockOrderId;

    /**
     * 采购订单单号
     */
    private String stockOrderNo;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 客户手机号
     */
    private String customerPhone;

    /**
     * 1=中国石化 2=中国石油 3=话费充值
     */
    private Byte type;

    /**
     * 0=正常 1=已出售
     */
    private Byte status;

    /**
     * 添加时间
     */
    private Date addtime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getCardPass() {
        return cardPass;
    }

    public void setCardPass(String cardPass) {
        this.cardPass = cardPass == null ? null : cardPass.trim();
    }

    public Integer getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(Integer expireMonth) {
        this.expireMonth = expireMonth;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public String getStockOrderId() {
        return stockOrderId;
    }

    public void setStockOrderId(String stockOrderId) {
        this.stockOrderId = stockOrderId == null ? null : stockOrderId.trim();
    }

    public String getStockOrderNo() {
        return stockOrderNo;
    }

    public void setStockOrderNo(String stockOrderNo) {
        this.stockOrderNo = stockOrderNo == null ? null : stockOrderNo.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

}
