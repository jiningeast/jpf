package com.joiest.jpf.entity;

import java.util.Date;

public class MerchantInfo {

    /**
     * 自增ID
     */
    private Long id;

    /**
     * 聚合商户号
     */
    private String merchNo;

    /**
     * 聚合商户名称
     */
    private String merchName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String userpwd;

    /**
     * 企业名称
     */
    private String companyname;

    /**
     * 省份
     */
    private Long province;

    /**
     * 城市
     */
    private Long city;

    /**
     * 联系人
     */
    private String linkname;

    /**
     * 联系电话
     */
    private String linkphone;

    /**
     * 激活码（销售电话）
     */
    private String salerphone;

    /**
     * 注册时间
     */
    private Date addtime;

    /**
     * 注册IP
     */
    private String registerip;

    /**
     * 最后登录IP
     */
    private String lastloginip;

    /**
     * 用户状态0正常，1冻结余额
     */
    private Byte status;

    /**
     * 营业执照
     */
    private String bslicense;

    /**
     * 企业资质
     */
    private String aptitude;

    /**
     * 企业logo
     */
    private String logo;

    /**
     * 企业认证：0:未认证；1:已认证
     */
    private Boolean attestation;

    /**
     * 对应yifuwang库中user表
     */
    private Integer muserid;

    private String content;

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

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
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

    public String getLinkname() {
        return linkname;
    }

    public void setLinkname(String linkname) {
        this.linkname = linkname;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getSalerphone() {
        return salerphone;
    }

    public void setSalerphone(String salerphone) {
        this.salerphone = salerphone;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getRegisterip() {
        return registerip;
    }

    public void setRegisterip(String registerip) {
        this.registerip = registerip;
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getBslicense() {
        return bslicense;
    }

    public void setBslicense(String bslicense) {
        this.bslicense = bslicense;
    }

    public String getAptitude() {
        return aptitude;
    }

    public void setAptitude(String aptitude) {
        this.aptitude = aptitude;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getAttestation() {
        return attestation;
    }

    public void setAttestation(Boolean attestation) {
        this.attestation = attestation;
    }

    public Integer getMuserid() {
        return muserid;
    }

    public void setMuserid(Integer muserid) {
        this.muserid = muserid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
