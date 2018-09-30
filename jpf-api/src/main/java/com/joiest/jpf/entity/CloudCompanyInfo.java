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
     * 商户别名
     */
    private String merchName;

    /**
     * 联系人姓名
     */
    private String phonename;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 营业执照地址
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
     * 客户经理手机号
     */
    private String linkphone;

    /**
     * 客户经理邮箱
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
     * 预付款金额
     */
    private BigDecimal advanceMoney;

    /**
     * 预付款校验码
     */
    private String advanceCode;

    /**
     * 冻结金额
     */
    private BigDecimal freezeMoney;

    /**
     * 冻结校验码
     */
    private String freezeCode;

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
     * 联系人邮箱
     */
    private String phoneemail;

    /**
     * 纳税人类型：01:一般纳税人 02:小规模纳税人
     */
    private String taxpayertype;

    /**
     * 纳税人识别号
     */
    private String tin;

    /**
     * 单位注册地址
     */
    private String address;

    /**
     * 联系人地址
     */
    private String addressPerson;
    /**
     * 商户类型：0=业务商户 1=代理商户
     */
    private byte type;

    /**
     * 代理平台费率，0.00:不收取费率
     */
    private BigDecimal agentRate;

    /**
     * 服务费费率
     */
    private BigDecimal salesRate;
    /**
     * 登录名称
     */
    private String userName;

    /**
     * 状态：-1:禁闭。1:正常
     */
    private Byte status;
    /**
     * 业务商户号
     */
    private String salesNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonename() {
        return phonename;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        this.serviclinkuser = serviclinkuser;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone;
    }

    public String getLinkemail() {
        return linkemail;
    }

    public void setLinkemail(String linkemail) {
        this.linkemail = linkemail;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getAddadminid() {
        return addadminid;
    }

    public void setAddadminid(String addadminid) {
        this.addadminid = addadminid;
    }

    public String getEditadminid() {
        return editadminid;
    }

    public void setEditadminid(String editadminid) {
        this.editadminid = editadminid;
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
        this.cloudcode = cloudcode;
    }

    public BigDecimal getAdvanceMoney() {
        return advanceMoney;
    }

    public void setAdvanceMoney(BigDecimal advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    public String getAdvanceCode() {
        return advanceCode;
    }

    public void setAdvanceCode(String advanceCode) {
        this.advanceCode = advanceCode;
    }

    public BigDecimal getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(BigDecimal freezeMoney) {
        this.freezeMoney = freezeMoney;
    }

    public String getFreezeCode() {
        return freezeCode;
    }

    public void setFreezeCode(String freezeCode) {
        this.freezeCode = freezeCode;
    }

    public String getCloudpaypwd() {
        return cloudpaypwd;
    }

    public void setCloudpaypwd(String cloudpaypwd) {
        this.cloudpaypwd = cloudpaypwd;
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
        this.certificate = certificate;
    }

    public String getPhoneemail() {
        return phoneemail;
    }

    public void setPhoneemail(String phoneemail) {
        this.phoneemail = phoneemail;
    }

    public String getTaxpayertype() {
        return taxpayertype;
    }

    public void setTaxpayertype(String taxpayertype) {
        this.taxpayertype = taxpayertype;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getSalesNo() {
        return salesNo;
    }

    public void setSalesNo(String salesNo) {
        this.salesNo = salesNo;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getAddressPerson() {
        return addressPerson;
    }

    public void setAddressPerson(String addressPerson) {
        this.addressPerson = addressPerson;
    }
}
