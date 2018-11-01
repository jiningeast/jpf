package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayChargeCompanyMoneyStream implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 流水号 MS+时间戳+3位随机数
     */
    private String streamNo;

    /**
     * 商户id
     */
    private String companyId;

    /**
     * 商户名称
     */
    private String companyName;

    /**
     * 商户号
     */
    private String merchNo;

    /**
     * 订单id 可能是消费订单、充值订单、退款订单
     */
    private String orderId;

    /**
     * 订单号 可能是消费订单、充值订单、退款订单
     */
    private String orderNo;

    /**
     * 产品id 消费或退款时的产品id
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品面值
     */
    private BigDecimal productValue;

    /**
     * 产品成本价
     */
    private BigDecimal productBidPrice;

    /**
     * 产品标准售价
     */
    private BigDecimal productSalePrice;

    /**
     * 产品接口价
     */
    private BigDecimal productInterfacePrice;

    /**
     * 产品数量
     */
    private Integer productAmount;

    /**
     * 总价
     */
    private BigDecimal totalMoney;

    /**
     * 接口类型 0=欧非 1=威能
     */
    private Byte interfaceType;

    /**
     * 接口订单号
     */
    private String interfaceOrderNo;

    /**
     * 流水类型 1=充值 2=下单 3=退款
     */
    private Byte status;

    /**
     * 流水类型 0=收入 1=支出
     */
    private Byte streamType;

    /**
     * 变动后的余额
     */
    private BigDecimal newMoney;

    /**
     * 流水备注
     */
    private String memo;

    /**
     * 删除标记 0=未删除 1=已删除
     */
    private Byte isDel;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStreamNo() {
        return streamNo;
    }

    public void setStreamNo(String streamNo) {
        this.streamNo = streamNo == null ? null : streamNo.trim();
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

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo == null ? null : merchNo.trim();
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public BigDecimal getProductValue() {
        return productValue;
    }

    public void setProductValue(BigDecimal productValue) {
        this.productValue = productValue;
    }

    public BigDecimal getProductBidPrice() {
        return productBidPrice;
    }

    public void setProductBidPrice(BigDecimal productBidPrice) {
        this.productBidPrice = productBidPrice;
    }

    public BigDecimal getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(BigDecimal productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public BigDecimal getProductInterfacePrice() {
        return productInterfacePrice;
    }

    public void setProductInterfacePrice(BigDecimal productInterfacePrice) {
        this.productInterfacePrice = productInterfacePrice;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Byte getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(Byte interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getInterfaceOrderNo() {
        return interfaceOrderNo;
    }

    public void setInterfaceOrderNo(String interfaceOrderNo) {
        this.interfaceOrderNo = interfaceOrderNo == null ? null : interfaceOrderNo.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getStreamType() {
        return streamType;
    }

    public void setStreamType(Byte streamType) {
        this.streamType = streamType;
    }

    public BigDecimal getNewMoney() {
        return newMoney;
    }

    public void setNewMoney(BigDecimal newMoney) {
        this.newMoney = newMoney;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
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
        sb.append(", streamNo=").append(streamNo);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", merchNo=").append(merchNo);
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productValue=").append(productValue);
        sb.append(", productBidPrice=").append(productBidPrice);
        sb.append(", productSalePrice=").append(productSalePrice);
        sb.append(", productInterfacePrice=").append(productInterfacePrice);
        sb.append(", productAmount=").append(productAmount);
        sb.append(", totalMoney=").append(totalMoney);
        sb.append(", interfaceType=").append(interfaceType);
        sb.append(", interfaceOrderNo=").append(interfaceOrderNo);
        sb.append(", status=").append(status);
        sb.append(", streamType=").append(streamType);
        sb.append(", newMoney=").append(newMoney);
        sb.append(", memo=").append(memo);
        sb.append(", isDel=").append(isDel);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
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
        PayChargeCompanyMoneyStream other = (PayChargeCompanyMoneyStream) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStreamNo() == null ? other.getStreamNo() == null : this.getStreamNo().equals(other.getStreamNo()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductValue() == null ? other.getProductValue() == null : this.getProductValue().equals(other.getProductValue()))
            && (this.getProductBidPrice() == null ? other.getProductBidPrice() == null : this.getProductBidPrice().equals(other.getProductBidPrice()))
            && (this.getProductSalePrice() == null ? other.getProductSalePrice() == null : this.getProductSalePrice().equals(other.getProductSalePrice()))
            && (this.getProductInterfacePrice() == null ? other.getProductInterfacePrice() == null : this.getProductInterfacePrice().equals(other.getProductInterfacePrice()))
            && (this.getProductAmount() == null ? other.getProductAmount() == null : this.getProductAmount().equals(other.getProductAmount()))
            && (this.getTotalMoney() == null ? other.getTotalMoney() == null : this.getTotalMoney().equals(other.getTotalMoney()))
            && (this.getInterfaceType() == null ? other.getInterfaceType() == null : this.getInterfaceType().equals(other.getInterfaceType()))
            && (this.getInterfaceOrderNo() == null ? other.getInterfaceOrderNo() == null : this.getInterfaceOrderNo().equals(other.getInterfaceOrderNo()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getStreamType() == null ? other.getStreamType() == null : this.getStreamType().equals(other.getStreamType()))
            && (this.getNewMoney() == null ? other.getNewMoney() == null : this.getNewMoney().equals(other.getNewMoney()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getIsDel() == null ? other.getIsDel() == null : this.getIsDel().equals(other.getIsDel()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStreamNo() == null) ? 0 : getStreamNo().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getMerchNo() == null) ? 0 : getMerchNo().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductValue() == null) ? 0 : getProductValue().hashCode());
        result = prime * result + ((getProductBidPrice() == null) ? 0 : getProductBidPrice().hashCode());
        result = prime * result + ((getProductSalePrice() == null) ? 0 : getProductSalePrice().hashCode());
        result = prime * result + ((getProductInterfacePrice() == null) ? 0 : getProductInterfacePrice().hashCode());
        result = prime * result + ((getProductAmount() == null) ? 0 : getProductAmount().hashCode());
        result = prime * result + ((getTotalMoney() == null) ? 0 : getTotalMoney().hashCode());
        result = prime * result + ((getInterfaceType() == null) ? 0 : getInterfaceType().hashCode());
        result = prime * result + ((getInterfaceOrderNo() == null) ? 0 : getInterfaceOrderNo().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStreamType() == null) ? 0 : getStreamType().hashCode());
        result = prime * result + ((getNewMoney() == null) ? 0 : getNewMoney().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}