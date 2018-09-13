package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayShopBargainPaylog implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 服务转让订单id
     */
    private String bargainOrderId;

    /**
     * 
     */
    private String bargainOrderNo;

    /**
     * 支付日志类型 0=请求支付 1=查询结果
     */
    private Byte type;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求内容
     */
    private String requestContent;

    /**
     * 返回内容
     */
    private String responseContent;

    /**
     * 添加时间
     */
    private Date addtime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBargainOrderId() {
        return bargainOrderId;
    }

    public void setBargainOrderId(String bargainOrderId) {
        this.bargainOrderId = bargainOrderId == null ? null : bargainOrderId.trim();
    }

    public String getBargainOrderNo() {
        return bargainOrderNo;
    }

    public void setBargainOrderNo(String bargainOrderNo) {
        this.bargainOrderNo = bargainOrderNo == null ? null : bargainOrderNo.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent == null ? null : requestContent.trim();
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent == null ? null : responseContent.trim();
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
        sb.append(", bargainOrderId=").append(bargainOrderId);
        sb.append(", bargainOrderNo=").append(bargainOrderNo);
        sb.append(", type=").append(type);
        sb.append(", requestUrl=").append(requestUrl);
        sb.append(", requestContent=").append(requestContent);
        sb.append(", responseContent=").append(responseContent);
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
        PayShopBargainPaylog other = (PayShopBargainPaylog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBargainOrderId() == null ? other.getBargainOrderId() == null : this.getBargainOrderId().equals(other.getBargainOrderId()))
            && (this.getBargainOrderNo() == null ? other.getBargainOrderNo() == null : this.getBargainOrderNo().equals(other.getBargainOrderNo()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getRequestUrl() == null ? other.getRequestUrl() == null : this.getRequestUrl().equals(other.getRequestUrl()))
            && (this.getRequestContent() == null ? other.getRequestContent() == null : this.getRequestContent().equals(other.getRequestContent()))
            && (this.getResponseContent() == null ? other.getResponseContent() == null : this.getResponseContent().equals(other.getResponseContent()))
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
        result = prime * result + ((getBargainOrderId() == null) ? 0 : getBargainOrderId().hashCode());
        result = prime * result + ((getBargainOrderNo() == null) ? 0 : getBargainOrderNo().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getRequestUrl() == null) ? 0 : getRequestUrl().hashCode());
        result = prime * result + ((getRequestContent() == null) ? 0 : getRequestContent().hashCode());
        result = prime * result + ((getResponseContent() == null) ? 0 : getResponseContent().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        return result;
    }
}