package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderRefundInfo {

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
     * 退款成功通知时间
     */
    private Date notifyTime;

    /**
     * 创建时间
     */
    private Date created;

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

}
