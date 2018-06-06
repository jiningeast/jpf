package com.joiest.jpf.common.custom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class PayOrdersCustom implements Serializable {
    /**
     *
     */
    private String id;

    /**
     *
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
     *
     */
    private Date created;
    //=========商户信息 Begin==============

    /**
     * 聚合商户名称
     */
    private String merchName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 企业名称
     */
    private String companyname;

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    //=========商户信息 End==============
    //================= 支付方式 Begin =====================
    private String cat;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    //================= 支付方式 End =====================

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getMtsid() {
        return mtsid;
    }

    public void setMtsid(String mtsid) {
        this.mtsid = mtsid == null ? null : mtsid.trim();
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
        this.productId = productId == null ? null : productId.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount == null ? null : productAmount.trim();
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
        sb.append(", orderid=").append(orderid);
        sb.append(", mtsid=").append(mtsid);
        sb.append(", money=").append(money);
        sb.append(", paytype=").append(paytype);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productAmount=").append(productAmount);
        sb.append(", productUnitPrice=").append(productUnitPrice);
        sb.append(", selfBusiness=").append(selfBusiness);
        sb.append(", created=").append(created);
        sb.append(", cat=").append(cat);
        sb.append("]");
        return sb.toString();
    }

}