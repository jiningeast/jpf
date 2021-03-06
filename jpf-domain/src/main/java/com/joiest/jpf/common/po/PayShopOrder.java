package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopOrder implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 订单号：OD+3位随机数+毫秒时间戳+3位随机数
     */
    private String orderNo;

    /**
     * 充值类型 0=直充 1=代充 2=卡密 3=混合  4=实体商品(中欣卡)
     */
    private Byte chargeType;

    /**
     * 充值类型是卡密的情况下对应的stock_card表id
     */
    private String stockCardId;

    /**
     * 订单类型 1:中国石化; 2中国石油; 3话费充值 4=携程商品 5.中欣卡订单
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
     * 产品豆数量
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
     * 订单总豆
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
     * 订单状态 0=待支付 1=已支付 2=支付失败 3=已取消 4=充值成功 5=充值失败
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
     * 接口类型 0=欧非 1=威能 2=中欣
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
     * 券转额度消费详情，json存激活id，豆数量
     */
    private String couponDetailSale;

    /**
     * 商品类型
     */
    private Integer productType;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 合同号
     */
    private String contractNo;

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
        this.stockCardId = stockCardId == null ? null : stockCardId.trim();
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
        this.foreignOrderNo = foreignOrderNo == null ? null : foreignOrderNo.trim();
    }

    public String getRequestedContent() {
        return requestedContent;
    }

    public void setRequestedContent(String requestedContent) {
        this.requestedContent = requestedContent == null ? null : requestedContent.trim();
    }

    public String getForeignRequestContent() {
        return foreignRequestContent;
    }

    public void setForeignRequestContent(String foreignRequestContent) {
        this.foreignRequestContent = foreignRequestContent == null ? null : foreignRequestContent.trim();
    }

    public String getForeignResponseContent() {
        return foreignResponseContent;
    }

    public void setForeignResponseContent(String foreignResponseContent) {
        this.foreignResponseContent = foreignResponseContent == null ? null : foreignResponseContent.trim();
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
        this.chargeNo = chargeNo == null ? null : chargeNo.trim();
    }

    public String getCouponDetail() {
        return couponDetail;
    }

    public void setCouponDetail(String couponDetail) {
        this.couponDetail = couponDetail == null ? null : couponDetail.trim();
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
        this.rechargeStatus = rechargeStatus == null ? null : rechargeStatus.trim();
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
        this.couponActiveId = couponActiveId == null ? null : couponActiveId.trim();
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
        this.receiveValue = receiveValue == null ? null : receiveValue.trim();
    }

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl == null ? null : ossUrl.trim();
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
        this.bargainOrderId = bargainOrderId == null ? null : bargainOrderId.trim();
    }

    public String getBargainOrderNo() {
        return bargainOrderNo;
    }

    public void setBargainOrderNo(String bargainOrderNo) {
        this.bargainOrderNo = bargainOrderNo == null ? null : bargainOrderNo.trim();
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

    public String getCouponDetailSale() {
        return couponDetailSale;
    }

    public void setCouponDetailSale(String couponDetailSale) {
        this.couponDetailSale = couponDetailSale == null ? null : couponDetailSale.trim();
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
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
        sb.append(", chargeType=").append(chargeType);
        sb.append(", stockCardId=").append(stockCardId);
        sb.append(", orderType=").append(orderType);
        sb.append(", foreignOrderNo=").append(foreignOrderNo);
        sb.append(", requestedContent=").append(requestedContent);
        sb.append(", foreignRequestContent=").append(foreignRequestContent);
        sb.append(", foreignResponseContent=").append(foreignResponseContent);
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productMoney=").append(productMoney);
        sb.append(", productDou=").append(productDou);
        sb.append(", productInfoId=").append(productInfoId);
        sb.append(", wnProductId=").append(wnProductId);
        sb.append(", amount=").append(amount);
        sb.append(", totalMoney=").append(totalMoney);
        sb.append(", totalDou=").append(totalDou);
        sb.append(", payWay=").append(payWay);
        sb.append(", chargeNo=").append(chargeNo);
        sb.append(", couponDetail=").append(couponDetail);
        sb.append(", status=").append(status);
        sb.append(", rechargeStatus=").append(rechargeStatus);
        sb.append(", rechargeTime=").append(rechargeTime);
        sb.append(", couponActiveId=").append(couponActiveId);
        sb.append(", receiveType=").append(receiveType);
        sb.append(", receiveValue=").append(receiveValue);
        sb.append(", ossUrl=").append(ossUrl);
        sb.append(", source=").append(source);
        sb.append(", interfaceType=").append(interfaceType);
        sb.append(", bargainOrderId=").append(bargainOrderId);
        sb.append(", bargainOrderNo=").append(bargainOrderNo);
        sb.append(", addtime=").append(addtime);
        sb.append(", paytime=").append(paytime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", couponDetailSale=").append(couponDetailSale);
        sb.append(", productType=").append(productType);
        sb.append(", companyName=").append(companyName);
        sb.append(", contractNo=").append(contractNo);
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
        PayShopOrder other = (PayShopOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getChargeType() == null ? other.getChargeType() == null : this.getChargeType().equals(other.getChargeType()))
            && (this.getStockCardId() == null ? other.getStockCardId() == null : this.getStockCardId().equals(other.getStockCardId()))
            && (this.getOrderType() == null ? other.getOrderType() == null : this.getOrderType().equals(other.getOrderType()))
            && (this.getForeignOrderNo() == null ? other.getForeignOrderNo() == null : this.getForeignOrderNo().equals(other.getForeignOrderNo()))
            && (this.getRequestedContent() == null ? other.getRequestedContent() == null : this.getRequestedContent().equals(other.getRequestedContent()))
            && (this.getForeignRequestContent() == null ? other.getForeignRequestContent() == null : this.getForeignRequestContent().equals(other.getForeignRequestContent()))
            && (this.getForeignResponseContent() == null ? other.getForeignResponseContent() == null : this.getForeignResponseContent().equals(other.getForeignResponseContent()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductMoney() == null ? other.getProductMoney() == null : this.getProductMoney().equals(other.getProductMoney()))
            && (this.getProductDou() == null ? other.getProductDou() == null : this.getProductDou().equals(other.getProductDou()))
            && (this.getProductInfoId() == null ? other.getProductInfoId() == null : this.getProductInfoId().equals(other.getProductInfoId()))
            && (this.getWnProductId() == null ? other.getWnProductId() == null : this.getWnProductId().equals(other.getWnProductId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getTotalMoney() == null ? other.getTotalMoney() == null : this.getTotalMoney().equals(other.getTotalMoney()))
            && (this.getTotalDou() == null ? other.getTotalDou() == null : this.getTotalDou().equals(other.getTotalDou()))
            && (this.getPayWay() == null ? other.getPayWay() == null : this.getPayWay().equals(other.getPayWay()))
            && (this.getChargeNo() == null ? other.getChargeNo() == null : this.getChargeNo().equals(other.getChargeNo()))
            && (this.getCouponDetail() == null ? other.getCouponDetail() == null : this.getCouponDetail().equals(other.getCouponDetail()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRechargeStatus() == null ? other.getRechargeStatus() == null : this.getRechargeStatus().equals(other.getRechargeStatus()))
            && (this.getRechargeTime() == null ? other.getRechargeTime() == null : this.getRechargeTime().equals(other.getRechargeTime()))
            && (this.getCouponActiveId() == null ? other.getCouponActiveId() == null : this.getCouponActiveId().equals(other.getCouponActiveId()))
            && (this.getReceiveType() == null ? other.getReceiveType() == null : this.getReceiveType().equals(other.getReceiveType()))
            && (this.getReceiveValue() == null ? other.getReceiveValue() == null : this.getReceiveValue().equals(other.getReceiveValue()))
            && (this.getOssUrl() == null ? other.getOssUrl() == null : this.getOssUrl().equals(other.getOssUrl()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getInterfaceType() == null ? other.getInterfaceType() == null : this.getInterfaceType().equals(other.getInterfaceType()))
            && (this.getBargainOrderId() == null ? other.getBargainOrderId() == null : this.getBargainOrderId().equals(other.getBargainOrderId()))
            && (this.getBargainOrderNo() == null ? other.getBargainOrderNo() == null : this.getBargainOrderNo().equals(other.getBargainOrderNo()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getPaytime() == null ? other.getPaytime() == null : this.getPaytime().equals(other.getPaytime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getCouponDetailSale() == null ? other.getCouponDetailSale() == null : this.getCouponDetailSale().equals(other.getCouponDetailSale()))
            && (this.getProductType() == null ? other.getProductType() == null : this.getProductType().equals(other.getProductType()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getContractNo() == null ? other.getContractNo() == null : this.getContractNo().equals(other.getContractNo()));
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
        result = prime * result + ((getChargeType() == null) ? 0 : getChargeType().hashCode());
        result = prime * result + ((getStockCardId() == null) ? 0 : getStockCardId().hashCode());
        result = prime * result + ((getOrderType() == null) ? 0 : getOrderType().hashCode());
        result = prime * result + ((getForeignOrderNo() == null) ? 0 : getForeignOrderNo().hashCode());
        result = prime * result + ((getRequestedContent() == null) ? 0 : getRequestedContent().hashCode());
        result = prime * result + ((getForeignRequestContent() == null) ? 0 : getForeignRequestContent().hashCode());
        result = prime * result + ((getForeignResponseContent() == null) ? 0 : getForeignResponseContent().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductMoney() == null) ? 0 : getProductMoney().hashCode());
        result = prime * result + ((getProductDou() == null) ? 0 : getProductDou().hashCode());
        result = prime * result + ((getProductInfoId() == null) ? 0 : getProductInfoId().hashCode());
        result = prime * result + ((getWnProductId() == null) ? 0 : getWnProductId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getTotalMoney() == null) ? 0 : getTotalMoney().hashCode());
        result = prime * result + ((getTotalDou() == null) ? 0 : getTotalDou().hashCode());
        result = prime * result + ((getPayWay() == null) ? 0 : getPayWay().hashCode());
        result = prime * result + ((getChargeNo() == null) ? 0 : getChargeNo().hashCode());
        result = prime * result + ((getCouponDetail() == null) ? 0 : getCouponDetail().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRechargeStatus() == null) ? 0 : getRechargeStatus().hashCode());
        result = prime * result + ((getRechargeTime() == null) ? 0 : getRechargeTime().hashCode());
        result = prime * result + ((getCouponActiveId() == null) ? 0 : getCouponActiveId().hashCode());
        result = prime * result + ((getReceiveType() == null) ? 0 : getReceiveType().hashCode());
        result = prime * result + ((getReceiveValue() == null) ? 0 : getReceiveValue().hashCode());
        result = prime * result + ((getOssUrl() == null) ? 0 : getOssUrl().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getInterfaceType() == null) ? 0 : getInterfaceType().hashCode());
        result = prime * result + ((getBargainOrderId() == null) ? 0 : getBargainOrderId().hashCode());
        result = prime * result + ((getBargainOrderNo() == null) ? 0 : getBargainOrderNo().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getPaytime() == null) ? 0 : getPaytime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getCouponDetailSale() == null) ? 0 : getCouponDetailSale().hashCode());
        result = prime * result + ((getProductType() == null) ? 0 : getProductType().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getContractNo() == null) ? 0 : getContractNo().hashCode());
        return result;
    }
}