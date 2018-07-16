package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AddCloudDfOrderRequest {

    /**
     *
     */
    private Long id;

    /**
     * 打款orderid
     */
    private Long orderid;

    /**
     * 打款批次id
     */
    private String batchid;

    /**
     * 外来批次编号
     */
    private String requestBatchno;

    /**
     * 单独打款id df_money.id
     */
    private String requestDfId;

    /**
     * 交易金额
     */
    private BigDecimal applyamt;

    /**
     * 订单标准金额
     */
    private BigDecimal orderStdPrice;

    /**
     * 请求信息
     */
    private String requeststr;

    /**
     * 银行编码
     */
    private String finacode;

    /**
     * 收款人银行账号
     */
    private String payeeacct;

    /**
     * 收款人银行户名
     */
    private String payeename;

    /**
     * 收款人账号属性
     */
    private String payeeacctattr;

    /**
     * 用户手机号
     */
    private String phoneno;

    /**
     * 身份证号
     */
    private String certno;

    /**
     * 添加时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 交易号
     */
    private String tranno;

    /**
     * 订单状态 1处理中
     */
    private Integer status;

    /**
     * 代付状态 00提交申请 01审核通过 02申请被拒绝 03已打批次 04提交到渠道 05代付成功 06代付失败
     */
    private String dfstatus;

    /**
     * 同步返回信息
     */
    private String returncontent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getRequestBatchno() {
        return requestBatchno;
    }

    public void setRequestBatchno(String requestBatchno) {
        this.requestBatchno = requestBatchno;
    }

    public String getRequestDfId() {
        return requestDfId;
    }

    public void setRequestDfId(String requestDfId) {
        this.requestDfId = requestDfId;
    }

    public BigDecimal getApplyamt() {
        return applyamt;
    }

    public void setApplyamt(BigDecimal applyamt) {
        this.applyamt = applyamt;
    }

    public BigDecimal getOrderStdPrice() {
        return orderStdPrice;
    }

    public void setOrderStdPrice(BigDecimal orderStdPrice) {
        this.orderStdPrice = orderStdPrice;
    }

    public String getRequeststr() {
        return requeststr;
    }

    public void setRequeststr(String requeststr) {
        this.requeststr = requeststr;
    }

    public String getFinacode() {
        return finacode;
    }

    public void setFinacode(String finacode) {
        this.finacode = finacode;
    }

    public String getPayeeacct() {
        return payeeacct;
    }

    public void setPayeeacct(String payeeacct) {
        this.payeeacct = payeeacct;
    }

    public String getPayeename() {
        return payeename;
    }

    public void setPayeename(String payeename) {
        this.payeename = payeename;
    }

    public String getPayeeacctattr() {
        return payeeacctattr;
    }

    public void setPayeeacctattr(String payeeacctattr) {
        this.payeeacctattr = payeeacctattr;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getCertno() {
        return certno;
    }

    public void setCertno(String certno) {
        this.certno = certno;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDfstatus() {
        return dfstatus;
    }

    public void setDfstatus(String dfstatus) {
        this.dfstatus = dfstatus;
    }

    public String getReturncontent() {
        return returncontent;
    }

    public void setReturncontent(String returncontent) {
        this.returncontent = returncontent;
    }
}
