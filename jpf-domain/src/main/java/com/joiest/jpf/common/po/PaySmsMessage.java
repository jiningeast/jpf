package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PaySmsMessage implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 订单id
     */
    private String orderid;

    /**
     * 聚合流水号
     */
    private String tranno;

    /**
     * 商户ID
     */
    private String mtsid;

    /**
     * 产品类型  0:旅游分期; 1:保险公司 2:后台操作
     */
    private Byte ptype;

    /**
     * 产品类型描述
     */
    private String ptypeCn;

    /**
     * 发送类型 1:支付 2:退款 3:后台商户密钥发送
     */
    private Byte sendtype;

    /**
     * 发送类型描述
     */
    private String sendtypeCn;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 同步回调信息
     */
    private String returnContent;

    /**
     * 
     */
    private Date addtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno == null ? null : tranno.trim();
    }

    public String getMtsid() {
        return mtsid;
    }

    public void setMtsid(String mtsid) {
        this.mtsid = mtsid == null ? null : mtsid.trim();
    }

    public Byte getPtype() {
        return ptype;
    }

    public void setPtype(Byte ptype) {
        this.ptype = ptype;
    }

    public String getPtypeCn() {
        return ptypeCn;
    }

    public void setPtypeCn(String ptypeCn) {
        this.ptypeCn = ptypeCn == null ? null : ptypeCn.trim();
    }

    public Byte getSendtype() {
        return sendtype;
    }

    public void setSendtype(Byte sendtype) {
        this.sendtype = sendtype;
    }

    public String getSendtypeCn() {
        return sendtypeCn;
    }

    public void setSendtypeCn(String sendtypeCn) {
        this.sendtypeCn = sendtypeCn == null ? null : sendtypeCn.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent == null ? null : returnContent.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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
        sb.append(", tranno=").append(tranno);
        sb.append(", mtsid=").append(mtsid);
        sb.append(", ptype=").append(ptype);
        sb.append(", ptypeCn=").append(ptypeCn);
        sb.append(", sendtype=").append(sendtype);
        sb.append(", sendtypeCn=").append(sendtypeCn);
        sb.append(", content=").append(content);
        sb.append(", returnContent=").append(returnContent);
        sb.append(", addtime=").append(addtime);
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
        PaySmsMessage other = (PaySmsMessage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getTranno() == null ? other.getTranno() == null : this.getTranno().equals(other.getTranno()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getPtype() == null ? other.getPtype() == null : this.getPtype().equals(other.getPtype()))
            && (this.getPtypeCn() == null ? other.getPtypeCn() == null : this.getPtypeCn().equals(other.getPtypeCn()))
            && (this.getSendtype() == null ? other.getSendtype() == null : this.getSendtype().equals(other.getSendtype()))
            && (this.getSendtypeCn() == null ? other.getSendtypeCn() == null : this.getSendtypeCn().equals(other.getSendtypeCn()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getReturnContent() == null ? other.getReturnContent() == null : this.getReturnContent().equals(other.getReturnContent()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
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
        result = prime * result + ((getTranno() == null) ? 0 : getTranno().hashCode());
        result = prime * result + ((getMtsid() == null) ? 0 : getMtsid().hashCode());
        result = prime * result + ((getPtype() == null) ? 0 : getPtype().hashCode());
        result = prime * result + ((getPtypeCn() == null) ? 0 : getPtypeCn().hashCode());
        result = prime * result + ((getSendtype() == null) ? 0 : getSendtype().hashCode());
        result = prime * result + ((getSendtypeCn() == null) ? 0 : getSendtypeCn().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getReturnContent() == null) ? 0 : getReturnContent().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        return result;
    }
}