package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayOrderPayMessage implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 订单id
     */
    private String orderid;

    /**
     * 请求参数
     */
    private String content;

    /**
     * 同步流水号
     */
    private String returnTranno;

    /**
     * 异步流水号
     */
    private String notifyTranno;

    /**
     * 同步回调信息
     */
    private String returnContent;

    /**
     * 异步回调信息
     */
    private String notifyContent;

    /**
     * 类型: 1:同步; 2:异步;
     */
    private Byte type;

    /**
     * 发送给商户的异步回调信息ID
     */
    private Long mermessageId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Long getMermessageId() {
        return mermessageId;
    }

    public void setMermessageId(Long mermessageId) {
        this.mermessageId = mermessageId;
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
        sb.append(", content=").append(content);
        sb.append(", returnTranno=").append(returnTranno);
        sb.append(", notifyTranno=").append(notifyTranno);
        sb.append(", returnContent=").append(returnContent);
        sb.append(", notifyContent=").append(notifyContent);
        sb.append(", type=").append(type);
        sb.append(", mermessageId=").append(mermessageId);
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
        PayOrderPayMessage other = (PayOrderPayMessage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getReturnTranno() == null ? other.getReturnTranno() == null : this.getReturnTranno().equals(other.getReturnTranno()))
            && (this.getNotifyTranno() == null ? other.getNotifyTranno() == null : this.getNotifyTranno().equals(other.getNotifyTranno()))
            && (this.getReturnContent() == null ? other.getReturnContent() == null : this.getReturnContent().equals(other.getReturnContent()))
            && (this.getNotifyContent() == null ? other.getNotifyContent() == null : this.getNotifyContent().equals(other.getNotifyContent()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getMermessageId() == null ? other.getMermessageId() == null : this.getMermessageId().equals(other.getMermessageId()))
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
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getReturnTranno() == null) ? 0 : getReturnTranno().hashCode());
        result = prime * result + ((getNotifyTranno() == null) ? 0 : getNotifyTranno().hashCode());
        result = prime * result + ((getReturnContent() == null) ? 0 : getReturnContent().hashCode());
        result = prime * result + ((getNotifyContent() == null) ? 0 : getNotifyContent().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getMermessageId() == null) ? 0 : getMermessageId().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}