package com.joiest.jpf.dto;

import java.util.Date;

public class AddSmsMessageRequest {
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
     * 产品类型  0:旅游分期; 1:保险公司
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
        this.orderid = orderid;
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno;
    }

    public String getMtsid() {
        return mtsid;
    }

    public void setMtsid(String mtsid) {
        this.mtsid = mtsid;
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
        this.ptypeCn = ptypeCn;
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
        this.sendtypeCn = sendtypeCn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AddSmsMessageRequest{");
        sb.append("id=").append(id);
        sb.append(", orderid='").append(orderid).append('\'');
        sb.append(", tranno='").append(tranno).append('\'');
        sb.append(", mtsid='").append(mtsid).append('\'');
        sb.append(", ptype=").append(ptype);
        sb.append(", ptypeCn='").append(ptypeCn).append('\'');
        sb.append(", sendtype=").append(sendtype);
        sb.append(", sendtypeCn='").append(sendtypeCn).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", returnContent='").append(returnContent).append('\'');
        sb.append(", addtime=").append(addtime);
        sb.append('}');
        return sb.toString();
    }
}
