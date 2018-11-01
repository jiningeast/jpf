package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class ChargeCompanyMoneyStreamRequest {

    /**
     * 主键id
     */
    private String id;

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
     * 产品价格
     */
    private BigDecimal productBidPrice;

    /**
     * 产品标准售价
     */
    private Long productSalePrice;

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

    private String addtimeStart;

    private String addtimeEnd;

    private String statusType;

    /**
     * 更新时间
     */
    private Date updatetime;

    private long page;

    private long rows;

    private Map<String, String> statusCnArr;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Long getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(Long productSalePrice) {
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

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

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

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public Map<String, String> getStatusCnArr() {
        return statusCnArr;
    }

    public void setStatusCnArr(Map<String, String> statusCnArr) {
        this.statusCnArr = statusCnArr;
    }
}