package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayOrderRefund implements Serializable {
    /**
     * 订单号
     */
    private String orderid;

    /**
     * 退款单号(外来退单号)
     */
    private String refundOrderid;

    /**
     * 退款金额
     */
    private BigDecimal money;

    /**
     * 1退款处理中，2退款成功，3退款失败
     */
    private String status;

    /**
     * 后台回调通知地址
     */
    private String backurl;

    /**
     * 交易类型13：消费分期撤销；14：消费分期退款
     */
    private Byte trantype;

    /**
     * 交易流水号
     */
    private String tranno;

    /**
     * 退款成功通知时间
     */
    private Date notifyTime;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 回调响应参数
     */
    private String responsParam;

    private static final long serialVersionUID = 1L;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getRefundOrderid() {
        return refundOrderid;
    }

    public void setRefundOrderid(String refundOrderid) {
        this.refundOrderid = refundOrderid == null ? null : refundOrderid.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBackurl() {
        return backurl;
    }

    public void setBackurl(String backurl) {
        this.backurl = backurl == null ? null : backurl.trim();
    }

    public Byte getTrantype() {
        return trantype;
    }

    public void setTrantype(Byte trantype) {
        this.trantype = trantype;
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno == null ? null : tranno.trim();
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getResponsParam() {
        return responsParam;
    }

    public void setResponsParam(String responsParam) {
        this.responsParam = responsParam == null ? null : responsParam.trim();
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
        sb.append(", orderid=").append(orderid);
        sb.append(", refundOrderid=").append(refundOrderid);
        sb.append(", money=").append(money);
        sb.append(", status=").append(status);
        sb.append(", backurl=").append(backurl);
        sb.append(", trantype=").append(trantype);
        sb.append(", tranno=").append(tranno);
        sb.append(", notifyTime=").append(notifyTime);
        sb.append(", created=").append(created);
        sb.append(", responsParam=").append(responsParam);
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
        PayOrderRefund other = (PayOrderRefund) that;
        return (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getRefundOrderid() == null ? other.getRefundOrderid() == null : this.getRefundOrderid().equals(other.getRefundOrderid()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBackurl() == null ? other.getBackurl() == null : this.getBackurl().equals(other.getBackurl()))
            && (this.getTrantype() == null ? other.getTrantype() == null : this.getTrantype().equals(other.getTrantype()))
            && (this.getTranno() == null ? other.getTranno() == null : this.getTranno().equals(other.getTranno()))
            && (this.getNotifyTime() == null ? other.getNotifyTime() == null : this.getNotifyTime().equals(other.getNotifyTime()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getResponsParam() == null ? other.getResponsParam() == null : this.getResponsParam().equals(other.getResponsParam()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getRefundOrderid() == null) ? 0 : getRefundOrderid().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBackurl() == null) ? 0 : getBackurl().hashCode());
        result = prime * result + ((getTrantype() == null) ? 0 : getTrantype().hashCode());
        result = prime * result + ((getTranno() == null) ? 0 : getTranno().hashCode());
        result = prime * result + ((getNotifyTime() == null) ? 0 : getNotifyTime().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getResponsParam() == null) ? 0 : getResponsParam().hashCode());
        return result;
    }
}