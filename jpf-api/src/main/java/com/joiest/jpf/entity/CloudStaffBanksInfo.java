package com.joiest.jpf.entity;

import java.math.BigInteger;

public class CloudStaffBanksInfo {

    /**
     * 自增ID
     */
    private BigInteger id;

    /**
     * 公司员工ID
     */
    private Long staffid;

    /**
     * 银行ID
     */
    private Long bankid;

    /**
     * 银行卡号
     */
    private String bankno;

    /**
     * 收款人
     */
    private String banknickname;

    /**
     * 银行预留手机号
     */
    private String bankphone;

    /**
     * 开户行
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
     * 用户状态  0 银行卡未激活  1  银行卡已激活
     */
    private Boolean bankActive;

    /**
     * 收款人账号属性 0=PRIVATE对私,1=PUBLIC对公
     */
    private Byte bankacctattr;

    private static final long serialVersionUID = 1L;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Long getStaffid() {
        return staffid;
    }

    public void setStaffid(Long staffid) {
        this.staffid = staffid;
    }

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno == null ? null : bankno.trim();
    }

    public String getBanknickname() {
        return banknickname;
    }

    public void setBanknickname(String banknickname) {
        this.banknickname = banknickname == null ? null : banknickname.trim();
    }

    public String getBankphone() {
        return bankphone;
    }

    public void setBankphone(String bankphone) {
        this.bankphone = bankphone == null ? null : bankphone.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankprovince() {
        return bankprovince;
    }

    public void setBankprovince(String bankprovince) {
        this.bankprovince = bankprovince == null ? null : bankprovince.trim();
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity == null ? null : bankcity.trim();
    }

    public Boolean getBankActive() {
        return bankActive;
    }

    public void setBankActive(Boolean bankActive) {
        this.bankActive = bankActive;
    }

    public Byte getBankacctattr() {
        return bankacctattr;
    }

    public void setBankacctattr(Byte bankacctattr) {
        this.bankacctattr = bankacctattr;
    }

}
