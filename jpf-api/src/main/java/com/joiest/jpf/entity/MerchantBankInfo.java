package com.joiest.jpf.entity;

import java.util.Date;

public class MerchantBankInfo{
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 省份
     */
    private Long province;

    /**
     * 城市
     */
    private Long city;

    /**
     * 银行ID
     */
    private Long bankid;

    /**
     * 银行名称
     */
    private String bankname;

    /**
     * 开户银行类型
     */
    private Long banktype;

    /**
     * 企业对公账户
     */
    private String bankno;

    /**
     * 开户行全称
     */
    private String banksubname;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 银行code
     */
    private String chinacode;

    /**
     * user 表uid
     */
    private Long muserid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public Long getBanktype() {
        return banktype;
    }

    public void setBanktype(Long banktype) {
        this.banktype = banktype;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno == null ? null : bankno.trim();
    }

    public String getBanksubname() {
        return banksubname;
    }

    public void setBanksubname(String banksubname) {
        this.banksubname = banksubname == null ? null : banksubname.trim();
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getChinacode() {
        return chinacode;
    }

    public void setChinacode(String chinacode) {
        this.chinacode = chinacode == null ? null : chinacode.trim();
    }

    public Long getMuserid() {
        return muserid;
    }

    public void setMuserid(Long muserid) {
        this.muserid = muserid;
    }
}
