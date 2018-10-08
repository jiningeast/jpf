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
    private Integer productDou;

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
    private Integer totalDou;

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

    public Integer getProductDou() {
        return productDou;
    }

    public void setProductDou(Integer productDou) {
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

    public Integer getTotalDou() {
        return totalDou;
    }

    public void setTotalDou(Integer totalDou) {
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
        sb.append(", orderNo=").append(orderNo);
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productMoney=").append(productMoney);
        sb.append(", productDou=").append(productDou);
        sb.append(", productInfoId=").append(productInfoId);
        sb.append(", amount=").append(amount);
        sb.append(", totalMoney=").append(totalMoney);
        sb.append(", totalDou=").append(totalDou);
        sb.append(", payWay=").append(payWay);
        sb.append(", chargeNo=").append(chargeNo);
        sb.append(", couponDetail=").append(couponDetail);
        sb.append(", status=").append(status);
        sb.append(", couponActiveId=").append(couponActiveId);
        sb.append(", addtime=").append(addtime);
        sb.append(", paytime=").append(paytime);
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
        PayShopOrderCustom other = (PayShopOrderCustom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductMoney() == null ? other.getProductMoney() == null : this.getProductMoney().equals(other.getProductMoney()))
            && (this.getProductDou() == null ? other.getProductDou() == null : this.getProductDou().equals(other.getProductDou()))
            && (this.getProductInfoId() == null ? other.getProductInfoId() == null : this.getProductInfoId().equals(other.getProductInfoId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getTotalMoney() == null ? other.getTotalMoney() == null : this.getTotalMoney().equals(other.getTotalMoney()))
            && (this.getTotalDou() == null ? other.getTotalDou() == null : this.getTotalDou().equals(other.getTotalDou()))
            && (this.getPayWay() == null ? other.getPayWay() == null : this.getPayWay().equals(other.getPayWay()))
            && (this.getChargeNo() == null ? other.getChargeNo() == null : this.getChargeNo().equals(other.getChargeNo()))
            && (this.getCouponDetail() == null ? other.getCouponDetail() == null : this.getCouponDetail().equals(other.getCouponDetail()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCouponActiveId() == null ? other.getCouponActiveId() == null : this.getCouponActiveId().equals(other.getCouponActiveId()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getPaytime() == null ? other.getPaytime() == null : this.getPaytime().equals(other.getPaytime()))
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
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductMoney() == null) ? 0 : getProductMoney().hashCode());
        result = prime * result + ((getProductDou() == null) ? 0 : getProductDou().hashCode());
        result = prime * result + ((getProductInfoId() == null) ? 0 : getProductInfoId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getTotalMoney() == null) ? 0 : getTotalMoney().hashCode());
        result = prime * result + ((getTotalDou() == null) ? 0 : getTotalDou().hashCode());
        result = prime * result + ((getPayWay() == null) ? 0 : getPayWay().hashCode());
        result = prime * result + ((getChargeNo() == null) ? 0 : getChargeNo().hashCode());
        result = prime * result + ((getCouponDetail() == null) ? 0 : getCouponDetail().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCouponActiveId() == null) ? 0 : getCouponActiveId().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getPaytime() == null) ? 0 : getPaytime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
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
}