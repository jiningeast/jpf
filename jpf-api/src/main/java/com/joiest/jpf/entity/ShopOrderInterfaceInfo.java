package com.joiest.jpf.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopOrderInterfaceInfo implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 订单号：OD+3位随机数+毫秒时间戳+3位随机数
     */
    private String orderNo;

    /**
     * 充值类型 0=直充 1=代充 2=卡密
     */
    private Byte chargeType;

    /**
     * 充值类型是卡密的情况下对应的stock_card表id
     */
    private String stockCardId;

    /**
     * 订单类型 1:中国石化; 2中国石油; 3话费充值
     */
    private Byte orderType;

    /**
     * 第三方订单号
     */
    private String foreignOrderNo;

    /**
     * 前端创建订单的请求参数
     */
    private String requestedContent;

    /**
     * 第三方接口请求参数
     */
    private String foreignRequestContent;

    /**
     * 第三方接口返回内容
     */
    private String foreignResponseContent;

    /**
     * 用户id
     */
    private String customerId;

    /**
     * 用户姓名
     */
    private String customerName;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品金额
     */
    private BigDecimal productMoney;

    /**
     * 产品豆价格
     */
    private BigDecimal productDou;

    /**
     * 商品基础信息id
     */
    private Integer productInfoId;

    /**
     * 威能端产品id
     */
    private String wnProductId;

    /**
     * 商品数量
     */
    private Integer amount;

    /**
     * 订单总金额
     */
    private BigDecimal totalMoney;

    /**
     * 订单总豆数量
     */
    private BigDecimal totalDou;

    /**
     * 支付方式 0=豆支付 1=微信支付
     */
    private Byte payWay;

    /**
     * 充值号，可以是手机号或油卡卡号
     */
    private String chargeNo;

    /**
     * 券消费详情，json存激活id，豆数量
     */
    private String couponDetail;

    /**
     * 订单状态 0=待支付 1=已支付 2=支付失败 3=已取消
     */
    private Byte status;

    /**
     * 充值状态
     */
    private String rechargeStatus;

    /**
     * 充值时间
     */
    private Date rechargeTime;

    /**
     * 消费时关联券使用记录表的id
     */
    private String couponActiveId;

    /**
     * 临时买卡密页面所需字段 接收方式 1=短信 2=邮箱
     */
    private Byte receiveType;

    /**
     * 临时买卡密页面所需字段 接收值 手机号或邮箱地址
     */
    private String receiveValue;

    /**
     * 发送方式为卡密时，excel文件的保存地址
     */
    private String ossUrl;

    /**
     * 订单来源 0=自平台 1=敬恒
     */
    private Byte source;

    /**
     * 接口类型 0=欧非 1=威能
     */
    private Byte interfaceType;

    /**
     * 转让订单表的主id
     */
    private String bargainOrderId;

    /**
     * 转让订单表的订单号
     */
    private String bargainOrderNo;

    /**
     * 下单时间
     */
    private Date addtime;

    /**
     * 支付时间
     */
    private Date paytime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 单个订单总金额
     */
    private Integer rechargeMoney;

    /**
     * 用户豆余额
     */
    private BigDecimal userDou;

    /**
     * 标题
     */
    private String typeName;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 券转额度消费详情，json存激活id，豆数量
     */
    private String couponDetailSale;

    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 商品类型
     */
    private String productTypeName;

    /**
     * 当前系统时间
     */
    private Date currentSystemTime;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 合同号
     */
    private String contractNo;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Date getCurrentSystemTime() {
        return currentSystemTime;
    }

    public void setCurrentSystemTime(Date currentSystemTime) {
        this.currentSystemTime = currentSystemTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Byte getChargeType() {
        return chargeType;
    }

    public void setChargeType(Byte chargeType) {
        this.chargeType = chargeType;
    }

    public String getStockCardId() {
        return stockCardId;
    }

    public void setStockCardId(String stockCardId) {
        this.stockCardId = stockCardId;
    }

    public Byte getOrderType() {
        return orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    public String getForeignOrderNo() {
        return foreignOrderNo;
    }

    public void setForeignOrderNo(String foreignOrderNo) {
        this.foreignOrderNo = foreignOrderNo;
    }

    public String getRequestedContent() {
        return requestedContent;
    }

    public void setRequestedContent(String requestedContent) {
        this.requestedContent = requestedContent;
    }

    public String getForeignRequestContent() {
        return foreignRequestContent;
    }

    public void setForeignRequestContent(String foreignRequestContent) {
        this.foreignRequestContent = foreignRequestContent;
    }

    public String getForeignResponseContent() {
        return foreignResponseContent;
    }

    public void setForeignResponseContent(String foreignResponseContent) {
        this.foreignResponseContent = foreignResponseContent;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public BigDecimal getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(BigDecimal productMoney) {
        this.productMoney = productMoney;
    }

    public BigDecimal getProductDou() {
        return productDou;
    }

    public void setProductDou(BigDecimal productDou) {
        this.productDou = productDou;
    }

    public Integer getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(Integer productInfoId) {
        this.productInfoId = productInfoId;
    }

    public String getWnProductId() {
        return wnProductId;
    }

    public void setWnProductId(String wnProductId) {
        this.wnProductId = wnProductId == null ? null : wnProductId.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getTotalDou() {
        return totalDou;
    }

    public void setTotalDou(BigDecimal totalDou) {
        this.totalDou = totalDou;
    }

    public Byte getPayWay() {
        return payWay;
    }

    public void setPayWay(Byte payWay) {
        this.payWay = payWay;
    }

    public String getChargeNo() {
        return chargeNo;
    }

    public void setChargeNo(String chargeNo) {
        this.chargeNo = chargeNo;
    }

    public String getCouponDetail() {
        return couponDetail;
    }

    public void setCouponDetail(String couponDetail) {
        this.couponDetail = couponDetail;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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

    public String getCouponActiveId() {
        return couponActiveId;
    }

    public void setCouponActiveId(String couponActiveId) {
        this.couponActiveId = couponActiveId;
    }

    public Byte getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Byte receiveType) {
        this.receiveType = receiveType;
    }

    public String getReceiveValue() {
        return receiveValue;
    }

    public void setReceiveValue(String receiveValue) {
        this.receiveValue = receiveValue;
    }

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getRechargeMoney() {
        return rechargeMoney;
    }


    public void setRechargeMoney(Integer rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public BigDecimal getUserDou() {
        return userDou;
    }

    public void setUserDou(BigDecimal userDou) {
        this.userDou = userDou;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }


    public Byte getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(Byte interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getBargainOrderId() {
        return bargainOrderId;
    }

    public void setBargainOrderId(String bargainOrderId) {
        this.bargainOrderId = bargainOrderId;
    }

    public String getBargainOrderNo() {
        return bargainOrderNo;
    }

    public void setBargainOrderNo(String bargainOrderNo) {
        this.bargainOrderNo = bargainOrderNo;
    }

    public String getCouponDetailSale() {
        return couponDetailSale;
    }

    public void setCouponDetailSale(String couponDetailSale) {
        this.couponDetailSale = couponDetailSale;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}