package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudDfOrderInterfaceInfo {

    /**
     *
     */
    private Long id;

    /**
     * 打款orderid
     */
    private String orderid;

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
     * 外来orderid
     */
    private String requestOrderid;

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
     * 开户行名称
     */
    private String bankname;

    /**
     * 开户行省
     */
    private String bankprovince;

    /**
     * 开户行市
     */
    private String bankcity;

    /**
     * 交易号
     */
    private String tranno;

    /**
     * 订单状态 0未处理 1处理中
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

    /**
     * 查询次数
     */
    private Integer querycount;

    /**
     * 最后一次查询响应
     */
    private String lastrespose;

    /**
     * 添加时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

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

    public String getRequestOrderid() {
        return requestOrderid;
    }

    public void setRequestOrderid(String requestOrderid) {
        this.requestOrderid = requestOrderid;
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

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankprovince() {
        return bankprovince;
    }

    public void setBankprovince(String bankprovince) {
        this.bankprovince = bankprovince;
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity;
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

    public Integer getQuerycount() {
        return querycount;
    }

    public void setQuerycount(Integer querycount) {
        this.querycount = querycount;
    }

    public String getLastrespose() {
        return lastrespose;
    }

    public void setLastrespose(String lastrespose) {
        this.lastrespose = lastrespose;
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
}
