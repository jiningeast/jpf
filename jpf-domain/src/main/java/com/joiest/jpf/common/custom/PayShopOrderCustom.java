package com.joiest.jpf.common.custom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopOrderCustom implements Serializable {
    /**
     *
     */
    private String id;

    /**
     *
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
     *
     */
    private String customerId;

    /**
     *
     */
    private String customerName;

    /**
     *
     */
    private String productId;

    /**
     *
     */
    private String productName;

    /**
     *
     */
    private BigDecimal productMoney;

    /**
     *
     */
    private BigDecimal productDou;

    /**
     * 商品基础信息id
     */
    private Integer productInfoId;

    /**
     * 商品基础信息id
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
     * 充值状态 0充值中 1充值成功 9充值失败
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
     *
     */
    private Date updatetime;


    /**
     * 商品分类
     */
    private String typeName;

    /**
     * 供应商
     */
    private String supplierName;


    /**
     * 品牌
     */
    private String brandName;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     *联系人手机号
     */

    private String contactPhone;

    /**
     *联系人email
     */
    private String contactEmail;


    /**
     * 客户手机号
     */
    private String phone;

    /**
     * 产品金额
     */
    private BigDecimal money;

    /**
     * 产品充值金额
     */
    private Integer rechargeMoney;


    /**
     * 商品进价
     */
    private BigDecimal bid;


    /**
     * 库存数量
     */
    private Integer stored;

    /**
     * 商品图片
     */
    private String image;

    private static final long serialVersionUID = 1L;

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
        this.wnProductId = wnProductId;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public Integer getStored() {
        return stored;
    }

    public void setStored(Integer stored) {
        this.stored = stored;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Integer rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public BigDecimal getProductDou() {
        return productDou;
    }

    public void setProductDou(BigDecimal productDou) {
        this.productDou = productDou;
    }

    public BigDecimal getTotalDou() {
        return totalDou;
    }

    public void setTotalDou(BigDecimal totalDou) {
        this.totalDou = totalDou;
    }
}