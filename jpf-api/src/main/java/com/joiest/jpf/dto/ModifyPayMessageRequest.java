package com.joiest.jpf.dto;

import java.util.Date;

public class ModifyPayMessageRequest {
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
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}
