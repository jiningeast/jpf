package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;

public class PayShopOrderInfo implements Serializable {
    /**
     * 订单详情的id
     */
    private String id;

    /**
     * 订单的id
     */
    private String orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品单价
     */
    private BigDecimal productPrice;

    /**
     * 购买数量
     */
    private Integer productNum;

    /**
     * 购买总价
     */
    private BigDecimal productTotalMoney;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public BigDecimal getProductTotalMoney() {
        return productTotalMoney;
    }

    public void setProductTotalMoney(BigDecimal productTotalMoney) {
        this.productTotalMoney = productTotalMoney;
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
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", productId=").append(productId);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productNum=").append(productNum);
        sb.append(", productTotalMoney=").append(productTotalMoney);
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
        PayShopOrderInfo other = (PayShopOrderInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
            && (this.getProductNum() == null ? other.getProductNum() == null : this.getProductNum().equals(other.getProductNum()))
            && (this.getProductTotalMoney() == null ? other.getProductTotalMoney() == null : this.getProductTotalMoney().equals(other.getProductTotalMoney()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
        result = prime * result + ((getProductNum() == null) ? 0 : getProductNum().hashCode());
        result = prime * result + ((getProductTotalMoney() == null) ? 0 : getProductTotalMoney().hashCode());
        return result;
    }
}