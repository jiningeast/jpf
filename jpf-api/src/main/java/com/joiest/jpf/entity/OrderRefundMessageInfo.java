package com.joiest.jpf.entity;

import java.math.BigInteger;
import java.util.Date;

public class OrderRefundMessageInfo {
    /**
     *
     */
    private BigInteger id;

    /**
     *
     */
    private String orderid;

    /**
     * 0:退款通知 1:退款异步返回
     */
    private Byte type;

    /**
     *
     */
    private String requestContent;

    /**
     *
     */
    private String tranno;

    /**
     *
     */
    private String responseContent;

    /**
     *
     */
    private Date addtime;

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
        this.orderid = orderid;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}
