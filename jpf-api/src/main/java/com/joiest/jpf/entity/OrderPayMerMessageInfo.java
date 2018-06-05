package com.joiest.jpf.entity;

import java.util.Date;

public class OrderPayMerMessageInfo {
    /**
     *
     */
    private Long id;

    /**
     * 订单id
     */
    private String orderid;

    /**
     * 商户订单号
     */
    private String foreignOrderid;

    /**
     * 异常发送给商户的信息
     */
    private String notifyContent;

    /**
     * 商户接收异步回调返回的信息
     */
    private String notifyResult;

    /**
     *
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getNotifyContent() {
        return notifyContent;
    }

    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent == null ? null : notifyContent.trim();
    }

    public String getNotifyResult() {
        return notifyResult;
    }

    public void setNotifyResult(String notifyResult) {
        this.notifyResult = notifyResult == null ? null : notifyResult.trim();
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
        sb.append(", notifyContent=").append(notifyContent);
        sb.append(", notifyResult=").append(notifyResult);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }

}
