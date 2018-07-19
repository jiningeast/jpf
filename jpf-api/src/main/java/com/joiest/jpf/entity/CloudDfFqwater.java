package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudDfFqwater {
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

}
