package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayOrderCp implements Serializable {
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
    private String orderid;

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
     * 银行编码
     */
    private String selectfinacode;

    /**
     * 银行卡类型:1:DEBIT借记卡:2:CREDIT 贷记卡
     */
    private Byte bankaccounttype;

    /**
     * 银行名称
     */
    private String bankname;

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
    private String validitycard;

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
     * 签约同步返回参数
     */
    private String returnContent;

    /**
     * 签约异步返回参数
     */
    private String notifyContent;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    private static final long serialVersionUID = 1L;

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
        this.merchNo = merchNo == null ? null : merchNo.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
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
        this.submerid = submerid == null ? null : submerid.trim();
    }

    public String getSubmername() {
        return submername;
    }

    public void setSubmername(String submername) {
        this.submername = submername == null ? null : submername.trim();
    }

    public String getSubmerabbr() {
        return submerabbr;
    }

    public void setSubmerabbr(String submerabbr) {
        this.submerabbr = submerabbr == null ? null : submerabbr.trim();
    }

    public String getSignedname() {
        return signedname;
    }

    public void setSignedname(String signedname) {
        this.signedname = signedname == null ? null : signedname.trim();
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
        this.idno = idno == null ? null : idno.trim();
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno == null ? null : mobileno.trim();
    }

    public String getSelectfinacode() {
        return selectfinacode;
    }

    public void setSelectfinacode(String selectfinacode) {
        this.selectfinacode = selectfinacode == null ? null : selectfinacode.trim();
    }

    public Byte getBankaccounttype() {
        return bankaccounttype;
    }

    public void setBankaccounttype(Byte bankaccounttype) {
        this.bankaccounttype = bankaccounttype;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankaccountnumber() {
        return bankaccountnumber;
    }

    public void setBankaccountnumber(String bankaccountnumber) {
        this.bankaccountnumber = bankaccountnumber == null ? null : bankaccountnumber.trim();
    }

    public Long getCvn2() {
        return cvn2;
    }

    public void setCvn2(Long cvn2) {
        this.cvn2 = cvn2;
    }

    public String getValiditycard() {
        return validitycard;
    }

    public void setValiditycard(String validitycard) {
        this.validitycard = validitycard == null ? null : validitycard.trim();
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
        this.clientip = clientip == null ? null : clientip.trim();
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno == null ? null : tranno.trim();
    }

    public String getSignstatus() {
        return signstatus;
    }

    public void setSignstatus(String signstatus) {
        this.signstatus = signstatus == null ? null : signstatus.trim();
    }

    public String getSysagreeno() {
        return sysagreeno;
    }

    public void setSysagreeno(String sysagreeno) {
        this.sysagreeno = sysagreeno == null ? null : sysagreeno.trim();
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
        sb.append(", merchNo=").append(merchNo);
        sb.append(", orderid=").append(orderid);
        sb.append(", mtsid=").append(mtsid);
        sb.append(", interestmode=").append(interestmode);
        sb.append(", submerid=").append(submerid);
        sb.append(", submername=").append(submername);
        sb.append(", submerabbr=").append(submerabbr);
        sb.append(", signedname=").append(signedname);
        sb.append(", idtype=").append(idtype);
        sb.append(", idno=").append(idno);
        sb.append(", mobileno=").append(mobileno);
        sb.append(", selectfinacode=").append(selectfinacode);
        sb.append(", bankaccounttype=").append(bankaccounttype);
        sb.append(", bankname=").append(bankname);
        sb.append(", bankaccountnumber=").append(bankaccountnumber);
        sb.append(", cvn2=").append(cvn2);
        sb.append(", validitycard=").append(validitycard);
        sb.append(", validityyear=").append(validityyear);
        sb.append(", clientip=").append(clientip);
        sb.append(", tranno=").append(tranno);
        sb.append(", signstatus=").append(signstatus);
        sb.append(", sysagreeno=").append(sysagreeno);
        sb.append(", returnContent=").append(returnContent);
        sb.append(", notifyContent=").append(notifyContent);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
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
        PayOrderCp other = (PayOrderCp) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getInterestmode() == null ? other.getInterestmode() == null : this.getInterestmode().equals(other.getInterestmode()))
            && (this.getSubmerid() == null ? other.getSubmerid() == null : this.getSubmerid().equals(other.getSubmerid()))
            && (this.getSubmername() == null ? other.getSubmername() == null : this.getSubmername().equals(other.getSubmername()))
            && (this.getSubmerabbr() == null ? other.getSubmerabbr() == null : this.getSubmerabbr().equals(other.getSubmerabbr()))
            && (this.getSignedname() == null ? other.getSignedname() == null : this.getSignedname().equals(other.getSignedname()))
            && (this.getIdtype() == null ? other.getIdtype() == null : this.getIdtype().equals(other.getIdtype()))
            && (this.getIdno() == null ? other.getIdno() == null : this.getIdno().equals(other.getIdno()))
            && (this.getMobileno() == null ? other.getMobileno() == null : this.getMobileno().equals(other.getMobileno()))
            && (this.getSelectfinacode() == null ? other.getSelectfinacode() == null : this.getSelectfinacode().equals(other.getSelectfinacode()))
            && (this.getBankaccounttype() == null ? other.getBankaccounttype() == null : this.getBankaccounttype().equals(other.getBankaccounttype()))
            && (this.getBankname() == null ? other.getBankname() == null : this.getBankname().equals(other.getBankname()))
            && (this.getBankaccountnumber() == null ? other.getBankaccountnumber() == null : this.getBankaccountnumber().equals(other.getBankaccountnumber()))
            && (this.getCvn2() == null ? other.getCvn2() == null : this.getCvn2().equals(other.getCvn2()))
            && (this.getValiditycard() == null ? other.getValiditycard() == null : this.getValiditycard().equals(other.getValiditycard()))
            && (this.getValidityyear() == null ? other.getValidityyear() == null : this.getValidityyear().equals(other.getValidityyear()))
            && (this.getClientip() == null ? other.getClientip() == null : this.getClientip().equals(other.getClientip()))
            && (this.getTranno() == null ? other.getTranno() == null : this.getTranno().equals(other.getTranno()))
            && (this.getSignstatus() == null ? other.getSignstatus() == null : this.getSignstatus().equals(other.getSignstatus()))
            && (this.getSysagreeno() == null ? other.getSysagreeno() == null : this.getSysagreeno().equals(other.getSysagreeno()))
            && (this.getReturnContent() == null ? other.getReturnContent() == null : this.getReturnContent().equals(other.getReturnContent()))
            && (this.getNotifyContent() == null ? other.getNotifyContent() == null : this.getNotifyContent().equals(other.getNotifyContent()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMerchNo() == null) ? 0 : getMerchNo().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getMtsid() == null) ? 0 : getMtsid().hashCode());
        result = prime * result + ((getInterestmode() == null) ? 0 : getInterestmode().hashCode());
        result = prime * result + ((getSubmerid() == null) ? 0 : getSubmerid().hashCode());
        result = prime * result + ((getSubmername() == null) ? 0 : getSubmername().hashCode());
        result = prime * result + ((getSubmerabbr() == null) ? 0 : getSubmerabbr().hashCode());
        result = prime * result + ((getSignedname() == null) ? 0 : getSignedname().hashCode());
        result = prime * result + ((getIdtype() == null) ? 0 : getIdtype().hashCode());
        result = prime * result + ((getIdno() == null) ? 0 : getIdno().hashCode());
        result = prime * result + ((getMobileno() == null) ? 0 : getMobileno().hashCode());
        result = prime * result + ((getSelectfinacode() == null) ? 0 : getSelectfinacode().hashCode());
        result = prime * result + ((getBankaccounttype() == null) ? 0 : getBankaccounttype().hashCode());
        result = prime * result + ((getBankname() == null) ? 0 : getBankname().hashCode());
        result = prime * result + ((getBankaccountnumber() == null) ? 0 : getBankaccountnumber().hashCode());
        result = prime * result + ((getCvn2() == null) ? 0 : getCvn2().hashCode());
        result = prime * result + ((getValiditycard() == null) ? 0 : getValiditycard().hashCode());
        result = prime * result + ((getValidityyear() == null) ? 0 : getValidityyear().hashCode());
        result = prime * result + ((getClientip() == null) ? 0 : getClientip().hashCode());
        result = prime * result + ((getTranno() == null) ? 0 : getTranno().hashCode());
        result = prime * result + ((getSignstatus() == null) ? 0 : getSignstatus().hashCode());
        result = prime * result + ((getSysagreeno() == null) ? 0 : getSysagreeno().hashCode());
        result = prime * result + ((getReturnContent() == null) ? 0 : getReturnContent().hashCode());
        result = prime * result + ((getNotifyContent() == null) ? 0 : getNotifyContent().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }
}