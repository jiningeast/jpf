package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ModifyBrangainRechargeorderInfo {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 订单号：OD+3位随机数+毫秒时间戳+3位随机数
     */
    private String orderNo;

    /**
     * bargain_order.id
     */
    private Long boid;

    /**
     * 订单类型 1:中国石化; 2中国石油; 3话费充值
     */
    private Integer orderType;

    /**
     * 第三方订单号
     */
    private String foreignOrderNo;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 面额
     */
    private BigDecimal facePrice;

    /**
     * 数量
     */
    private Integer amt;

    /**
     * 订单总金额
     */
    private BigDecimal amount;

    /**
     * 充值号，手机号
     */
    private String chargeNo;

    /**
     * 订单状态 0=待支付 1=已支付 2=支付失败 3=已取消
     */
    private Integer status;

    /**
     * 充值状态
     */
    private String rechargeStatus;

    /**
     * 充值时间
     */
    private Date rechargeTime;

    /**
     * 下单时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 1:未绑定; 2:已绑定
     */
    private Integer infoStatus;

    /**
     * 订单信息
     */
    private String module;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getBoid() {
        return boid;
    }

    public void setBoid(Long boid) {
        this.boid = boid;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getForeignOrderNo() {
        return foreignOrderNo;
    }

    public void setForeignOrderNo(String foreignOrderNo) {
        this.foreignOrderNo = foreignOrderNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getFacePrice() {
        return facePrice;
    }

    public void setFacePrice(BigDecimal facePrice) {
        this.facePrice = facePrice;
    }

    public Integer getAmt() {
        return amt;
    }

    public void setAmt(Integer amt) {
        this.amt = amt;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
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

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }
}
