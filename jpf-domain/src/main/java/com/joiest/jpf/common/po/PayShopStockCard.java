package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopStockCard implements Serializable {
    /**
     *
     */
    private String id;

    /**
     * 商品id
     */
    private String productId;

    /**
     * 
     */
    private String productName;

    /**
     * 供应商id
     */
    private String supplierId;

    /**
     * 供应商id
     */
    private String supplierName;

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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
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
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", supplierName=").append(supplierName);
        sb.append(", cardNo=").append(cardNo);
        sb.append(", cardPass=").append(cardPass);
        sb.append(", expireMonth=").append(expireMonth);
        sb.append(", expireDate=").append(expireDate);
        sb.append(", bid=").append(bid);
        sb.append(", stockOrderId=").append(stockOrderId);
        sb.append(", stockOrderNo=").append(stockOrderNo);
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", customerPhone=").append(customerPhone);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", addtime=").append(addtime);
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
        PayShopStockCard other = (PayShopStockCard) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getSupplierName() == null ? other.getSupplierName() == null : this.getSupplierName().equals(other.getSupplierName()))
            && (this.getCardNo() == null ? other.getCardNo() == null : this.getCardNo().equals(other.getCardNo()))
            && (this.getCardPass() == null ? other.getCardPass() == null : this.getCardPass().equals(other.getCardPass()))
            && (this.getExpireMonth() == null ? other.getExpireMonth() == null : this.getExpireMonth().equals(other.getExpireMonth()))
            && (this.getExpireDate() == null ? other.getExpireDate() == null : this.getExpireDate().equals(other.getExpireDate()))
            && (this.getBid() == null ? other.getBid() == null : this.getBid().equals(other.getBid()))
            && (this.getStockOrderId() == null ? other.getStockOrderId() == null : this.getStockOrderId().equals(other.getStockOrderId()))
            && (this.getStockOrderNo() == null ? other.getStockOrderNo() == null : this.getStockOrderNo().equals(other.getStockOrderNo()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCustomerPhone() == null ? other.getCustomerPhone() == null : this.getCustomerPhone().equals(other.getCustomerPhone()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getSupplierName() == null) ? 0 : getSupplierName().hashCode());
        result = prime * result + ((getCardNo() == null) ? 0 : getCardNo().hashCode());
        result = prime * result + ((getCardPass() == null) ? 0 : getCardPass().hashCode());
        result = prime * result + ((getExpireMonth() == null) ? 0 : getExpireMonth().hashCode());
        result = prime * result + ((getExpireDate() == null) ? 0 : getExpireDate().hashCode());
        result = prime * result + ((getBid() == null) ? 0 : getBid().hashCode());
        result = prime * result + ((getStockOrderId() == null) ? 0 : getStockOrderId().hashCode());
        result = prime * result + ((getStockOrderNo() == null) ? 0 : getStockOrderNo().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCustomerPhone() == null) ? 0 : getCustomerPhone().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        return result;
    }
}