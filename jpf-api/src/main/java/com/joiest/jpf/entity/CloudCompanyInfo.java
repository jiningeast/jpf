package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudCompanyInfo {
    /**
     * 自增ID
     */
    private String id;

    /**
     * 聚合商户编号
     */
    private String merchNo;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 联系人姓名
     */
    private String phonename;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 营业执照
     */
    private String bslicense;

    /**
     * 企业资质
     */
    private String aptitude;

    /**
     * 企业认证：0:未认证；1:已认证
     */
    private Byte attestation;

    /**
     * 是否需要发送短信 1发送 0不发送
     */
    private Byte issms;

    /**
     * 企业支付密码通知方式 1=邮件2=短信
     */
    private Byte tipstype;

    /**
     * 客户经理
     */
    private String serviclinkuser;

    /**
     * 手机号
     */
    private String linkphone;

    /**
     * 邮箱
     */
    private String linkemail;

    /**
     * 商户私钥，32位字符串
     */
    private String privateKey;

    /**
     * 录入管理员编号
     */
    private String addadminid;

    /**
     * 修改管理员编号
     */
    private String editadminid;

    /**
     * 是否显示
     */
    private Byte isshow;

    /**
     * 添加时间
     */
    private Date created;

    /**
     * 云帐户余额
     */
    private BigDecimal cloudmoney;

    /**
     * 云账户金额校验
     */
    private String cloudcode;

    /**
     * 云账户支付密码
     */
    private String cloudpaypwd;

    /**
     * 添加时间
     */
    private Date updated;

    /**
     * 营业执照编号
     */
    private String certificate;

    /**
     * 代理平台费率，0.00:不收取费率
     */
    private BigDecimal agentRate;

    /**
     * 服务费费率
     */
    private BigDecimal salesRate;

    public BigDecimal getAgentRate() {
        return agentRate;
    }

    public void setAgentRate(BigDecimal agentRate) {
        this.agentRate = agentRate;
    }

    public BigDecimal getSalesRate() {
        return salesRate;
    }

    public void setSalesRate(BigDecimal salesRate) {
        this.salesRate = salesRate;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo == null ? null : merchNo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhonename() {
        return phonename;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename == null ? null : phonename.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBslicense() {
        return bslicense;
    }

    public void setBslicense(String bslicense) {
        this.bslicense = bslicense == null ? null : bslicense.trim();
    }

    public String getAptitude() {
        return aptitude;
    }

    public void setAptitude(String aptitude) {
        this.aptitude = aptitude == null ? null : aptitude.trim();
    }

    public Byte getAttestation() {
        return attestation;
    }

    public void setAttestation(Byte attestation) {
        this.attestation = attestation;
    }

    public Byte getIssms() {
        return issms;
    }

    public void setIssms(Byte issms) {
        this.issms = issms;
    }

    public Byte getTipstype() {
        return tipstype;
    }

    public void setTipstype(Byte tipstype) {
        this.tipstype = tipstype;
    }

    public String getServiclinkuser() {
        return serviclinkuser;
    }

    public void setServiclinkuser(String serviclinkuser) {
        this.serviclinkuser = serviclinkuser == null ? null : serviclinkuser.trim();
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone == null ? null : linkphone.trim();
    }

    public String getLinkemail() {
        return linkemail;
    }

    public void setLinkemail(String linkemail) {
        this.linkemail = linkemail == null ? null : linkemail.trim();
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }

    public String getAddadminid() {
        return addadminid;
    }

    public void setAddadminid(String addadminid) {
        this.addadminid = addadminid == null ? null : addadminid.trim();
    }

    public String getEditadminid() {
        return editadminid;
    }

    public void setEditadminid(String editadminid) {
        this.editadminid = editadminid == null ? null : editadminid.trim();
    }

    public Byte getIsshow() {
        return isshow;
    }

    public void setIsshow(Byte isshow) {
        this.isshow = isshow;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public BigDecimal getCloudmoney() {
        return cloudmoney;
    }

    public void setCloudmoney(BigDecimal cloudmoney) {
        this.cloudmoney = cloudmoney;
    }

    public String getCloudcode() {
        return cloudcode;
    }

    public void setCloudcode(String cloudcode) {
        this.cloudcode = cloudcode == null ? null : cloudcode.trim();
    }

    public String getCloudpaypwd() {
        return cloudpaypwd;
    }

    public void setCloudpaypwd(String cloudpaypwd) {
        this.cloudpaypwd = cloudpaypwd == null ? null : cloudpaypwd.trim();
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate == null ? null : certificate.trim();
    }
}
