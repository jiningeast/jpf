package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class PayOrderYinjiaApi implements Serializable {
    /**
     * 自增ID
     */
    private BigInteger id;

    /**
     * 订单ID
     */
    private String orderid;

    /**
     * 外来订单号
     */
    private String foreignOrderid;

    /**
     * 签约订单号，对应pay_order_cp的orderid字段
     */
    private Long signOrderid;

    /**
     * 外来请求字符串
     */
    private String foreignRequest;

    /**
     * 
     */
    private String returnUrl;

    /**
     * 异步回调
     */
    private String notifyUrl;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 支付方式：pay_merchants_type 
     */
    private Integer paytype;

    /**
     * 订单实际支付金额(用户实付)
     */
    private BigDecimal orderPayPrice;

    /**
     * 订单标准金额(未折扣订单原价)
     */
    private BigDecimal orderStdPrice;

    /**
     * 订单商品数量
     */
    private Integer productAccount;

    /**
     * 分期付款序列（json格式）
     */
    private String payDetail;

    /**
     * 支付时间
     */
    private Date paytime;

    /**
     * 0:未支付；1:支付成功；2:支付失败
     */
    private Byte payStatus;

    /**
     * 1:正常订单；2:退单处理；3:退款撤销；4:运营已审核,待财务审核，5:财务已审核，银联退款中, 6:审核驳回,7:银联退款成功,8:银联退款失败
     */
    private Byte refundStatus;

    /**
     * 用户操作状态 0:订单生成,未选分期;1:订单生成,已选分期,待签约;2:签约通知成功,待返回签约成功;3:签约通知失败;4:签约返回成功,待获取支付短信;5:签约返回失败;6:用户曾已签约,待获取支付短信;7:短信发送成功,待支付;8:短信发送失败;9:支付通知成功,待返回成功;10:支付通知失败;11:支付返回成功,支付结束;12:支付返回失败
     */
    private Byte userOperateStatus;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getForeignOrderid() {
        return foreignOrderid;
    }

    public void setForeignOrderid(String foreignOrderid) {
        this.foreignOrderid = foreignOrderid == null ? null : foreignOrderid.trim();
    }

    public Long getSignOrderid() {
        return signOrderid;
    }

    public void setSignOrderid(Long signOrderid) {
        this.signOrderid = signOrderid;
    }

    public String getForeignRequest() {
        return foreignRequest;
    }

    public void setForeignRequest(String foreignRequest) {
        this.foreignRequest = foreignRequest == null ? null : foreignRequest.trim();
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl == null ? null : returnUrl.trim();
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl == null ? null : notifyUrl.trim();
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public BigDecimal getOrderPayPrice() {
        return orderPayPrice;
    }

    public void setOrderPayPrice(BigDecimal orderPayPrice) {
        this.orderPayPrice = orderPayPrice;
    }

    public BigDecimal getOrderStdPrice() {
        return orderStdPrice;
    }

    public void setOrderStdPrice(BigDecimal orderStdPrice) {
        this.orderStdPrice = orderStdPrice;
    }

    public Integer getProductAccount() {
        return productAccount;
    }

    public void setProductAccount(Integer productAccount) {
        this.productAccount = productAccount;
    }

    public String getPayDetail() {
        return payDetail;
    }

    public void setPayDetail(String payDetail) {
        this.payDetail = payDetail == null ? null : payDetail.trim();
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Byte getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Byte payStatus) {
        this.payStatus = payStatus;
    }

    public Byte getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Byte refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Byte getUserOperateStatus() {
        return userOperateStatus;
    }

    public void setUserOperateStatus(Byte userOperateStatus) {
        this.userOperateStatus = userOperateStatus;
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
        sb.append(", orderid=").append(orderid);
        sb.append(", foreignOrderid=").append(foreignOrderid);
        sb.append(", signOrderid=").append(signOrderid);
        sb.append(", foreignRequest=").append(foreignRequest);
        sb.append(", returnUrl=").append(returnUrl);
        sb.append(", notifyUrl=").append(notifyUrl);
        sb.append(", mtsid=").append(mtsid);
        sb.append(", paytype=").append(paytype);
        sb.append(", orderPayPrice=").append(orderPayPrice);
        sb.append(", orderStdPrice=").append(orderStdPrice);
        sb.append(", productAccount=").append(productAccount);
        sb.append(", payDetail=").append(payDetail);
        sb.append(", paytime=").append(paytime);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", refundStatus=").append(refundStatus);
        sb.append(", userOperateStatus=").append(userOperateStatus);
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
        PayOrderYinjiaApi other = (PayOrderYinjiaApi) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getForeignOrderid() == null ? other.getForeignOrderid() == null : this.getForeignOrderid().equals(other.getForeignOrderid()))
            && (this.getSignOrderid() == null ? other.getSignOrderid() == null : this.getSignOrderid().equals(other.getSignOrderid()))
            && (this.getForeignRequest() == null ? other.getForeignRequest() == null : this.getForeignRequest().equals(other.getForeignRequest()))
            && (this.getReturnUrl() == null ? other.getReturnUrl() == null : this.getReturnUrl().equals(other.getReturnUrl()))
            && (this.getNotifyUrl() == null ? other.getNotifyUrl() == null : this.getNotifyUrl().equals(other.getNotifyUrl()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getPaytype() == null ? other.getPaytype() == null : this.getPaytype().equals(other.getPaytype()))
            && (this.getOrderPayPrice() == null ? other.getOrderPayPrice() == null : this.getOrderPayPrice().equals(other.getOrderPayPrice()))
            && (this.getOrderStdPrice() == null ? other.getOrderStdPrice() == null : this.getOrderStdPrice().equals(other.getOrderStdPrice()))
            && (this.getProductAccount() == null ? other.getProductAccount() == null : this.getProductAccount().equals(other.getProductAccount()))
            && (this.getPayDetail() == null ? other.getPayDetail() == null : this.getPayDetail().equals(other.getPayDetail()))
            && (this.getPaytime() == null ? other.getPaytime() == null : this.getPaytime().equals(other.getPaytime()))
            && (this.getPayStatus() == null ? other.getPayStatus() == null : this.getPayStatus().equals(other.getPayStatus()))
            && (this.getRefundStatus() == null ? other.getRefundStatus() == null : this.getRefundStatus().equals(other.getRefundStatus()))
            && (this.getUserOperateStatus() == null ? other.getUserOperateStatus() == null : this.getUserOperateStatus().equals(other.getUserOperateStatus()))
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
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getForeignOrderid() == null) ? 0 : getForeignOrderid().hashCode());
        result = prime * result + ((getSignOrderid() == null) ? 0 : getSignOrderid().hashCode());
        result = prime * result + ((getForeignRequest() == null) ? 0 : getForeignRequest().hashCode());
        result = prime * result + ((getReturnUrl() == null) ? 0 : getReturnUrl().hashCode());
        result = prime * result + ((getNotifyUrl() == null) ? 0 : getNotifyUrl().hashCode());
        result = prime * result + ((getMtsid() == null) ? 0 : getMtsid().hashCode());
        result = prime * result + ((getPaytype() == null) ? 0 : getPaytype().hashCode());
        result = prime * result + ((getOrderPayPrice() == null) ? 0 : getOrderPayPrice().hashCode());
        result = prime * result + ((getOrderStdPrice() == null) ? 0 : getOrderStdPrice().hashCode());
        result = prime * result + ((getProductAccount() == null) ? 0 : getProductAccount().hashCode());
        result = prime * result + ((getPayDetail() == null) ? 0 : getPayDetail().hashCode());
        result = prime * result + ((getPaytime() == null) ? 0 : getPaytime().hashCode());
        result = prime * result + ((getPayStatus() == null) ? 0 : getPayStatus().hashCode());
        result = prime * result + ((getRefundStatus() == null) ? 0 : getRefundStatus().hashCode());
        result = prime * result + ((getUserOperateStatus() == null) ? 0 : getUserOperateStatus().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}