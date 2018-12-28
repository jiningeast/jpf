package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayChargeOrder implements Serializable {
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
     * 产品类型是手机则表示手机号，油卡充值表示油卡号
     */
    private String chargePhone;

    /**
     * 产品类型 0=欧飞话费充值 1=微能话费充值 2=中石化油卡充值 3=中石油油卡充值 4飞翰（敬恒）
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
     * 产品售价，即产品表的sale_price
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
     * 订单状态 0=平台下单成功 1=充值中 2=上游充值成功 3=上游充值失败 4=申请退款 5=退款成功 6=拒绝退款 7=退款失败
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
     * 是否删除 0=不删除 1=删除
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
     * 备注
     */
    private String remark;

    /**
     * pay_charge_consumer_order表中的订单号
     */
    private String consumerOrderNo;

    /**
     * 敬恒记录id
     */
    private String rechargeOrderId;

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

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId == null ? null : checkId.trim();
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName == null ? null : checkName.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getConsumerOrderNo() {
        return consumerOrderNo;
    }

    public void setConsumerOrderNo(String consumerOrderNo) {
        this.consumerOrderNo = consumerOrderNo == null ? null : consumerOrderNo.trim();
    }

    public String getRechargeOrderId() {
        return rechargeOrderId;
    }

    public void setRechargeOrderId(String rechargeOrderId) {
        this.rechargeOrderId = rechargeOrderId == null ? null : rechargeOrderId.trim();
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
        sb.append(", foreignOrderNo=").append(foreignOrderNo);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", merchNo=").append(merchNo);
        sb.append(", chargePhone=").append(chargePhone);
        sb.append(", productType=").append(productType);
        sb.append(", productId=").append(productId);
        sb.append(", productName=").append(productName);
        sb.append(", productValue=").append(productValue);
        sb.append(", productBidPrice=").append(productBidPrice);
        sb.append(", productPrice=").append(productPrice);
        sb.append(", productAmount=").append(productAmount);
        sb.append(", totalMoney=").append(totalMoney);
        sb.append(", interfaceType=").append(interfaceType);
        sb.append(", interfaceOrderNo=").append(interfaceOrderNo);
        sb.append(", requestParams=").append(requestParams);
        sb.append(", notifyUrl=").append(notifyUrl);
        sb.append(", notifyParams=").append(notifyParams);
        sb.append(", notifyTime=").append(notifyTime);
        sb.append(", status=").append(status);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", checkId=").append(checkId);
        sb.append(", checkName=").append(checkName);
        sb.append(", isDel=").append(isDel);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", remark=").append(remark);
        sb.append(", consumerOrderNo=").append(consumerOrderNo);
        sb.append(", rechargeOrderId=").append(rechargeOrderId);
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
        PayChargeOrder other = (PayChargeOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getForeignOrderNo() == null ? other.getForeignOrderNo() == null : this.getForeignOrderNo().equals(other.getForeignOrderNo()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getChargePhone() == null ? other.getChargePhone() == null : this.getChargePhone().equals(other.getChargePhone()))
            && (this.getProductType() == null ? other.getProductType() == null : this.getProductType().equals(other.getProductType()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductValue() == null ? other.getProductValue() == null : this.getProductValue().equals(other.getProductValue()))
            && (this.getProductBidPrice() == null ? other.getProductBidPrice() == null : this.getProductBidPrice().equals(other.getProductBidPrice()))
            && (this.getProductPrice() == null ? other.getProductPrice() == null : this.getProductPrice().equals(other.getProductPrice()))
            && (this.getProductAmount() == null ? other.getProductAmount() == null : this.getProductAmount().equals(other.getProductAmount()))
            && (this.getTotalMoney() == null ? other.getTotalMoney() == null : this.getTotalMoney().equals(other.getTotalMoney()))
            && (this.getInterfaceType() == null ? other.getInterfaceType() == null : this.getInterfaceType().equals(other.getInterfaceType()))
            && (this.getInterfaceOrderNo() == null ? other.getInterfaceOrderNo() == null : this.getInterfaceOrderNo().equals(other.getInterfaceOrderNo()))
            && (this.getRequestParams() == null ? other.getRequestParams() == null : this.getRequestParams().equals(other.getRequestParams()))
            && (this.getNotifyUrl() == null ? other.getNotifyUrl() == null : this.getNotifyUrl().equals(other.getNotifyUrl()))
            && (this.getNotifyParams() == null ? other.getNotifyParams() == null : this.getNotifyParams().equals(other.getNotifyParams()))
            && (this.getNotifyTime() == null ? other.getNotifyTime() == null : this.getNotifyTime().equals(other.getNotifyTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getCheckId() == null ? other.getCheckId() == null : this.getCheckId().equals(other.getCheckId()))
            && (this.getCheckName() == null ? other.getCheckName() == null : this.getCheckName().equals(other.getCheckName()))
            && (this.getIsDel() == null ? other.getIsDel() == null : this.getIsDel().equals(other.getIsDel()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getConsumerOrderNo() == null ? other.getConsumerOrderNo() == null : this.getConsumerOrderNo().equals(other.getConsumerOrderNo()))
            && (this.getRechargeOrderId() == null ? other.getRechargeOrderId() == null : this.getRechargeOrderId().equals(other.getRechargeOrderId()));
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
        result = prime * result + ((getForeignOrderNo() == null) ? 0 : getForeignOrderNo().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getMerchNo() == null) ? 0 : getMerchNo().hashCode());
        result = prime * result + ((getChargePhone() == null) ? 0 : getChargePhone().hashCode());
        result = prime * result + ((getProductType() == null) ? 0 : getProductType().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductValue() == null) ? 0 : getProductValue().hashCode());
        result = prime * result + ((getProductBidPrice() == null) ? 0 : getProductBidPrice().hashCode());
        result = prime * result + ((getProductPrice() == null) ? 0 : getProductPrice().hashCode());
        result = prime * result + ((getProductAmount() == null) ? 0 : getProductAmount().hashCode());
        result = prime * result + ((getTotalMoney() == null) ? 0 : getTotalMoney().hashCode());
        result = prime * result + ((getInterfaceType() == null) ? 0 : getInterfaceType().hashCode());
        result = prime * result + ((getInterfaceOrderNo() == null) ? 0 : getInterfaceOrderNo().hashCode());
        result = prime * result + ((getRequestParams() == null) ? 0 : getRequestParams().hashCode());
        result = prime * result + ((getNotifyUrl() == null) ? 0 : getNotifyUrl().hashCode());
        result = prime * result + ((getNotifyParams() == null) ? 0 : getNotifyParams().hashCode());
        result = prime * result + ((getNotifyTime() == null) ? 0 : getNotifyTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getCheckId() == null) ? 0 : getCheckId().hashCode());
        result = prime * result + ((getCheckName() == null) ? 0 : getCheckName().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getConsumerOrderNo() == null) ? 0 : getConsumerOrderNo().hashCode());
        result = prime * result + ((getRechargeOrderId() == null) ? 0 : getRechargeOrderId().hashCode());
        return result;
    }
}