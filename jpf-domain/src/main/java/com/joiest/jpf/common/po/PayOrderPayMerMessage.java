package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayOrderPayMerMessage implements Serializable {
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
        PayOrderPayMerMessage other = (PayOrderPayMerMessage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getForeignOrderid() == null ? other.getForeignOrderid() == null : this.getForeignOrderid().equals(other.getForeignOrderid()))
            && (this.getNotifyContent() == null ? other.getNotifyContent() == null : this.getNotifyContent().equals(other.getNotifyContent()))
            && (this.getNotifyResult() == null ? other.getNotifyResult() == null : this.getNotifyResult().equals(other.getNotifyResult()))
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
        result = prime * result + ((getNotifyContent() == null) ? 0 : getNotifyContent().hashCode());
        result = prime * result + ((getNotifyResult() == null) ? 0 : getNotifyResult().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}