package com.joiest.jpf.entity;

import com.joiest.jpf.common.po.PayChargeOrder;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeOrderInfo {
    /**
     * 主键id
     */
    private String id;

    /**
     * 订单号，规则：CH+3位随机数+毫秒级时间戳+3位随机数
     */
    private String orderNo;

    /**
     * 外来订单号
     */
    private String foreignOrderNo;

    /**
     * 企业id
     */
    private String companyId;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 商户号
     */
    private String merchNo;

    /**
     * 充值手机号
     */
    private String chargePhone;

    /**
     * 产品类型 0=话费充值 1=油卡充值
     */
    private Integer productType;

    /**
     * 产品id
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
     * 进价，取接口的进价，即产品表的ofProductPrice或wnProductPrice
     */
    private BigDecimal productBidPrice;

    /**
     * 产品单价
     */
    private BigDecimal productPrice;

    /**
     * 数量
     */
    private Integer productAmount;

    /**
     * 订单总金额
     */
    private BigDecimal totalMoney;

    /**
     * 接口类型 0=欧非 1=威能
     */
    private Byte interfaceType;

    /**
     * 上游订单号
     */
    private String interfaceOrderNo;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 异步回调地址
     */
    private String notifyUrl;

    /**
     * 异步回调参数
     */
    private String notifyParams;

    /**
     * 异步回调时间
     */
    private Date notifyTime;

    /**
     * 订单状态 1=订单生成成功 2=充值成功 3=充值失败
     */
    private Byte status;

    /**
     * 申请退款人id
     */
    private String operatorId;

    /**
     * 申请退款人姓名
     */
    private String operatorName;

    /**
     * 财务审核人id
     */
    private String checkId;

    /**
     * 财务审核人姓名
     */
    private String checkName;

    /**
     * 是否删除
     */
    private Byte isDel;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     *
     */
    private Date updatetime;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getForeignOrderNo() {
        return foreignOrderNo;
    }

    public void setForeignOrderNo(String foreignOrderNo) {
        this.foreignOrderNo = foreignOrderNo == null ? null : foreignOrderNo.trim();
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

    public String getChargePhone() {
        return chargePhone;
    }

    public void setChargePhone(String chargePhone) {
        this.chargePhone = chargePhone == null ? null : chargePhone.trim();
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
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

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
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

    public String getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(String requestParams) {
        this.requestParams = requestParams == null ? null : requestParams.trim();
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public String getNotifyParams() {
        return notifyParams;
    }

    public void setNotifyParams(String notifyParams) {
        this.notifyParams = notifyParams == null ? null : notifyParams.trim();
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
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

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

}