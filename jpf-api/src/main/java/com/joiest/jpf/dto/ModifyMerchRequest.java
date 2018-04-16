package com.joiest.jpf.dto;

public class ModifyMerchRequest {

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
     * 注册IP
     */
    private String registerip;

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
     * 省份
     */
    private Long bankProvince;

    /**
     * 城市
     */
    private Long bankCity;

    /**
     * 区县
     */
    private Long region;

    /**
     * 具体地址
     */
    private String address;

    /**
     * 银行开户名称
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

    public String getRegisterip() {
        return registerip;
    }

    public void setRegisterip(String registerip) {
        this.registerip = registerip;
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

    public Long getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(Long bankProvince) {
        this.bankProvince = bankProvince;
    }

    public Long getBankCity() {
        return bankCity;
    }

    public void setBankCity(Long bankCity) {
        this.bankCity = bankCity;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
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
        this.bankno = bankno;
    }

    public String getBanksubname() {
        return banksubname;
    }

    public void setBanksubname(String banksubname) {
        this.banksubname = banksubname;
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

}
