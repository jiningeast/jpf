package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GetOrdersRequest {
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

    private long rows;

    private long page;

    //============== 搜索 Begin ==================
    private String addtimeStart;

    private String addtimeEnd;

    public String getAddtimeStart() {
        return addtimeStart;
    }

    public void setAddtimeStart(String addtimeStart) {
        this.addtimeStart = addtimeStart;
    }

    public String getAddtimeEnd() {
        return addtimeEnd;
    }

    public void setAddtimeEnd(String addtimeEnd) {
        this.addtimeEnd = addtimeEnd;
    }

    //============== 搜索 End ==================
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


    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
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
        sb.append(", created=").append(created);
        sb.append(", rows=").append(rows);
        sb.append(", page=").append(page);
        sb.append("]");
        return sb.toString();
    }

}
