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
     * 区县
     */
    private Long region;

    /**
     * 具体地址
     */
    private String address;

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

    /**
     * 审核备注
     */
    private String content;

    /**
     * 法人姓名
     */
    private String legalname;

    /**
     * 法人身份证号
     */
    private String legalidcard;

    /**
     * 法人身份证正面地址
     */
    private String legalface;

    /**
     * 法人身份证反面地址
     */
    private String legalback;

    /**
     * 身份证有效期开始日期
     */
    private String idstartdate;

    /**
     * 身份证有效期结束日期
     */
    private String idenddate;

    /**
     * 法人手持身份证照片
     */
    private String lefalhand;

    /**
     * 营业执照号码
     */
    private String certificate;

    /**
     * 商户公钥
     */
    private String privateKey;

    /**
     * 费率
     */
    private String rate;

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

    public Long getRegion() {
        return region;
    }

    public void setRegion(Long region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getLegalname() {
        return legalname;
    }

    public void setLegalname(String legalname) {
        this.legalname = legalname == null ? null : legalname.trim();
    }

    public String getLegalidcard() {
        return legalidcard;
    }

    public void setLegalidcard(String legalidcard) {
        this.legalidcard = legalidcard == null ? null : legalidcard.trim();
    }

    public String getLegalface() {
        return legalface;
    }

    public void setLegalface(String legalface) {
        this.legalface = legalface == null ? null : legalface.trim();
    }

    public String getLegalback() {
        return legalback;
    }

    public void setLegalback(String legalback) {
        this.legalback = legalback == null ? null : legalback.trim();
    }

    public String getIdstartdate() {
        return idstartdate;
    }

    public void setIdstartdate(String idstartdate) {
        this.idstartdate = idstartdate == null ? null : idstartdate.trim();
    }

    public String getIdenddate() {
        return idenddate;
    }

    public void setIdenddate(String idenddate) {
        this.idenddate = idenddate == null ? null : idenddate.trim();
    }

    public String getLefalhand() {
        return lefalhand;
    }

    public void setLefalhand(String lefalhand) {
        this.lefalhand = lefalhand == null ? null : lefalhand.trim();
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
