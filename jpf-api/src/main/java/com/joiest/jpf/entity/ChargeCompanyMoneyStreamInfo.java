package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeCompanyMoneyStreamInfo {
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

    /**
     * 订单文字
     */
    private String statusCn;

    /**
     * 收支类型转换
     */
    private String streamReturn;

    /**
     * 接口类型转换
     */
    private String interfaceReturn;

    /**
     * 流水类型转换
     */
    private String statusReturn;

    /**
     * 删除标记 0=未删除 1=已删除
     */
    private String isDelCn;


    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreamNo() {
        return streamNo;
    }

    public void setStreamNo(String streamNo) {
        this.streamNo = streamNo;
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

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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
        this.interfaceOrderNo = interfaceOrderNo;
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
        this.memo = memo;
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

    public String getStatusCn() {
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

    public String getStreamReturn() {
        return streamReturn;
    }

    public void setStreamReturn(String streamReturn) {
        this.streamReturn = streamReturn;
    }

    public String getInterfaceReturn() {
        return interfaceReturn;
    }

    public void setInterfaceReturn(String interfaceReturn) {
        this.interfaceReturn = interfaceReturn;
    }

    public String getStatusReturn() {
        return statusReturn;
    }

    public void setStatusReturn(String statusReturn) {
        this.statusReturn = statusReturn;
    }

    public String getIsDelCn() {
        return isDelCn;
    }

    public void setIsDelCn(String isDelCn) {
        this.isDelCn = isDelCn;
    }
}
