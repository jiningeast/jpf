package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayCloudDfQorderStream implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 外来orderid
     */
    private String requestOrderid;

    /**
     * 打款订单号
     */
    private String orderid;

    /**
     * 流水号
     */
    private String tranno;

    /**
     * 订单金额
     */
    private BigDecimal tranamt;

    /**
     * 代付状态 00 提交申请，01 审核通过，02 
申请被拒绝，03 已打批次，04 提交到渠道， 
05 代付成功，06 代付失败
     */
    private String orderstatus;

    /**
     * 请求接口参数
     */
    private String requestparam;

    /**
     * 响应参数
     */
    private String responseparam;

    /**
     * 
     */
    private Date created;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestOrderid() {
        return requestOrderid;
    }

    public void setRequestOrderid(String requestOrderid) {
        this.requestOrderid = requestOrderid == null ? null : requestOrderid.trim();
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

    public BigDecimal getTranamt() {
        return tranamt;
    }

    public void setTranamt(BigDecimal tranamt) {
        this.tranamt = tranamt;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus == null ? null : orderstatus.trim();
    }

    public String getRequestparam() {
        return requestparam;
    }

    public void setRequestparam(String requestparam) {
        this.requestparam = requestparam == null ? null : requestparam.trim();
    }

    public String getResponseparam() {
        return responseparam;
    }

    public void setResponseparam(String responseparam) {
        this.responseparam = responseparam == null ? null : responseparam.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
        sb.append(", requestOrderid=").append(requestOrderid);
        sb.append(", orderid=").append(orderid);
        sb.append(", tranno=").append(tranno);
        sb.append(", tranamt=").append(tranamt);
        sb.append(", orderstatus=").append(orderstatus);
        sb.append(", requestparam=").append(requestparam);
        sb.append(", responseparam=").append(responseparam);
        sb.append(", created=").append(created);
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
        PayCloudDfQorderStream other = (PayCloudDfQorderStream) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRequestOrderid() == null ? other.getRequestOrderid() == null : this.getRequestOrderid().equals(other.getRequestOrderid()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getTranno() == null ? other.getTranno() == null : this.getTranno().equals(other.getTranno()))
            && (this.getTranamt() == null ? other.getTranamt() == null : this.getTranamt().equals(other.getTranamt()))
            && (this.getOrderstatus() == null ? other.getOrderstatus() == null : this.getOrderstatus().equals(other.getOrderstatus()))
            && (this.getRequestparam() == null ? other.getRequestparam() == null : this.getRequestparam().equals(other.getRequestparam()))
            && (this.getResponseparam() == null ? other.getResponseparam() == null : this.getResponseparam().equals(other.getResponseparam()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRequestOrderid() == null) ? 0 : getRequestOrderid().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getTranno() == null) ? 0 : getTranno().hashCode());
        result = prime * result + ((getTranamt() == null) ? 0 : getTranamt().hashCode());
        result = prime * result + ((getOrderstatus() == null) ? 0 : getOrderstatus().hashCode());
        result = prime * result + ((getRequestparam() == null) ? 0 : getRequestparam().hashCode());
        result = prime * result + ((getResponseparam() == null) ? 0 : getResponseparam().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        return result;
    }
}