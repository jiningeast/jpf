package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayOrderCpMessage implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String returnTranno;

    /**
     * 
     */
    private String notifyTranno;

    /**
     * 
     */
    private String orderid;

    /**
     * 
     */
    private String signOrderid;

    /**
     * 
     */
    private String requestContent;

    /**
     * 
     */
    private String returnContent;

    /**
     * 
     */
    private String notifyContent;

    /**
     * 
     */
    private Date addtime;

    /**
     * 
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReturnTranno() {
        return returnTranno;
    }

    public void setReturnTranno(String returnTranno) {
        this.returnTranno = returnTranno == null ? null : returnTranno.trim();
    }

    public String getNotifyTranno() {
        return notifyTranno;
    }

    public void setNotifyTranno(String notifyTranno) {
        this.notifyTranno = notifyTranno == null ? null : notifyTranno.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getSignOrderid() {
        return signOrderid;
    }

    public void setSignOrderid(String signOrderid) {
        this.signOrderid = signOrderid == null ? null : signOrderid.trim();
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent == null ? null : requestContent.trim();
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent == null ? null : returnContent.trim();
    }

    public String getNotifyContent() {
        return notifyContent;
    }

    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent == null ? null : notifyContent.trim();
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
        sb.append(", returnTranno=").append(returnTranno);
        sb.append(", notifyTranno=").append(notifyTranno);
        sb.append(", orderid=").append(orderid);
        sb.append(", signOrderid=").append(signOrderid);
        sb.append(", requestContent=").append(requestContent);
        sb.append(", returnContent=").append(returnContent);
        sb.append(", notifyContent=").append(notifyContent);
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
        PayOrderCpMessage other = (PayOrderCpMessage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getReturnTranno() == null ? other.getReturnTranno() == null : this.getReturnTranno().equals(other.getReturnTranno()))
            && (this.getNotifyTranno() == null ? other.getNotifyTranno() == null : this.getNotifyTranno().equals(other.getNotifyTranno()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getSignOrderid() == null ? other.getSignOrderid() == null : this.getSignOrderid().equals(other.getSignOrderid()))
            && (this.getRequestContent() == null ? other.getRequestContent() == null : this.getRequestContent().equals(other.getRequestContent()))
            && (this.getReturnContent() == null ? other.getReturnContent() == null : this.getReturnContent().equals(other.getReturnContent()))
            && (this.getNotifyContent() == null ? other.getNotifyContent() == null : this.getNotifyContent().equals(other.getNotifyContent()))
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
        result = prime * result + ((getReturnTranno() == null) ? 0 : getReturnTranno().hashCode());
        result = prime * result + ((getNotifyTranno() == null) ? 0 : getNotifyTranno().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getSignOrderid() == null) ? 0 : getSignOrderid().hashCode());
        result = prime * result + ((getRequestContent() == null) ? 0 : getRequestContent().hashCode());
        result = prime * result + ((getReturnContent() == null) ? 0 : getReturnContent().hashCode());
        result = prime * result + ((getNotifyContent() == null) ? 0 : getNotifyContent().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}