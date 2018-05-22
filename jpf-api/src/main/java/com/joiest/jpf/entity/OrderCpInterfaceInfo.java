package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderCpInterfaceInfo {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 聚合商户号
     */
    private String merchNo;

    /**
     * 订单ID
     */
    private Long orderid;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 付息模式。01：商户贴息
     */
    private Long interestmode;

    /**
     * 银联商户代码
     */
    private String submerid;

    /**
     * 银联商户名称
     */
    private String submername;

    /**
     * 银联商户简称
     */
    private String submerabbr;

    /**
     * 签约人姓名
     */
    private String signedname;

    /**
     * 签约人证件类型，01：身份证
     */
    private Byte idtype;

    /**
     * 签约人身份证号
     */
    private String idno;

    /**
     * 签约人手机号
     */
    private String mobileno;

    /**
     * 渠道编码
     */
    private Long chncode;

    /**
     * 渠道账户编号
     */
    private Long chnacctid;

    /**
     * 银行编码
     */
    private String selectfinacode;

    /**
     * 银行卡类型:1:DEBIT借记卡:2:CREDIT 贷记卡
     */
    private Byte bankaccounttype;

    /**
     * 银行卡号
     */
    private String bankaccountnumber;

    /**
     * cvn2
     */
    private Long cvn2;

    /**
     * 信用卡有效期
     */
    private Date validitycard;

    /**
     * 有效期默认一年
     */
    private Date validityyear;

    /**
     * 客户端浏览器
     */
    private String clientip;

    /**
     * 交易流水号
     */
    private String tranno;

    /**
     * 签约状态。1：未完成；2：成功；3：失败；6：关闭
     */
    private String signstatus;

    /**
     * 系统签约号
     */
    private String sysagreeno;

    /**
     * 创建时间
     */
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Long getInterestmode() {
        return interestmode;
    }

    public void setInterestmode(Long interestmode) {
        this.interestmode = interestmode;
    }

    public String getSubmerid() {
        return submerid;
    }

    public void setSubmerid(String submerid) {
        this.submerid = submerid;
    }

    public String getSubmername() {
        return submername;
    }

    public void setSubmername(String submername) {
        this.submername = submername;
    }

    public String getSubmerabbr() {
        return submerabbr;
    }

    public void setSubmerabbr(String submerabbr) {
        this.submerabbr = submerabbr;
    }

    public String getSignedname() {
        return signedname;
    }

    public void setSignedname(String signedname) {
        this.signedname = signedname;
    }

    public Byte getIdtype() {
        return idtype;
    }

    public void setIdtype(Byte idtype) {
        this.idtype = idtype;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public Long getChncode() {
        return chncode;
    }

    public void setChncode(Long chncode) {
        this.chncode = chncode;
    }

    public Long getChnacctid() {
        return chnacctid;
    }

    public void setChnacctid(Long chnacctid) {
        this.chnacctid = chnacctid;
    }

    public String getSelectfinacode() {
        return selectfinacode;
    }

    public void setSelectfinacode(String selectfinacode) {
        this.selectfinacode = selectfinacode;
    }

    public Byte getBankaccounttype() {
        return bankaccounttype;
    }

    public void setBankaccounttype(Byte bankaccounttype) {
        this.bankaccounttype = bankaccounttype;
    }

    public String getBankaccountnumber() {
        return bankaccountnumber;
    }

    public void setBankaccountnumber(String bankaccountnumber) {
        this.bankaccountnumber = bankaccountnumber;
    }

    public Long getCvn2() {
        return cvn2;
    }

    public void setCvn2(Long cvn2) {
        this.cvn2 = cvn2;
    }

    public Date getValiditycard() {
        return validitycard;
    }

    public void setValiditycard(Date validitycard) {
        this.validitycard = validitycard;
    }

    public Date getValidityyear() {
        return validityyear;
    }

    public void setValidityyear(Date validityyear) {
        this.validityyear = validityyear;
    }

    public String getClientip() {
        return clientip;
    }

    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno;
    }

    public String getSignstatus() {
        return signstatus;
    }

    public void setSignstatus(String signstatus) {
        this.signstatus = signstatus;
    }

    public String getSysagreeno() {
        return sysagreeno;
    }

    public void setSysagreeno(String sysagreeno) {
        this.sysagreeno = sysagreeno;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
