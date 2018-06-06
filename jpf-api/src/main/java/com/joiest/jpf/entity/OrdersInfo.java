package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrdersInfo {
    /**
     * id
     */
    private String id;

    /**
     * 订单id
     */
    private String orderid;

    /**
     * 商户ID
     */
    private String mtsid;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 支付方式
     */
    private Integer paytype;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品数量
     */
    private String productAmount;

    /**
     * 商品单价
     */
    private BigDecimal productUnitPrice;

    /**
     * 业务来源 0：自平台业务 1：第三方
     */
    private Integer selfBusiness;

    /**
     * 创建时间
     */
    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMtsid() {
        return mtsid;
    }

    public void setMtsid(String mtsid) {
        this.mtsid = mtsid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(BigDecimal productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public Integer getSelfBusiness() {
        return selfBusiness;
    }

    public void setSelfBusiness(Integer selfBusiness) {
        this.selfBusiness = selfBusiness;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
