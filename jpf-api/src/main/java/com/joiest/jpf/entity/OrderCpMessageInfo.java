package com.joiest.jpf.entity;

import java.util.Date;

public class OrderCpMessageInfo {

    private Long id;

    private String returnTranno;

    private String notifyTranno;

    private String orderid;

    private String signOrderid;

    private String requestContent;

    private String returnContent;

    private String notifyContent;

    private Date addtime;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public String getReturnTranno() {
        return returnTranno;
    }

    public void setReturnTranno(String returnTranno) {
        this.returnTranno = returnTranno;
    }

    public String getNotifyTranno() {
        return notifyTranno;
    }

    public void setNotifyTranno(String notifyTranno) {
        this.notifyTranno = notifyTranno;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getSignOrderid() {
        return signOrderid;
    }

    public void setSignOrderid(String signOrderid) {
        this.signOrderid = signOrderid;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }

    public String getNotifyContent() {
        return notifyContent;
    }

    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent;
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
}
