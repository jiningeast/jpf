package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GetCloudCompanyRequest {
    /**
     * 自增ID
     */
    private String id;

    /**
     * 银行名称
     */
    private String bankid;
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
     * 公司类别
     */
    private String type;
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
     * 状态：-1:禁闭。1:正常
     */
    private Byte status;

    private long rows;

    private long page;

    /**
     * 服务费费率
     */
    private BigDecimal salesRate;

    /**
     * 注册时间
     */
    private String addtimeStart;

    private String addtimeEnd;

    /**
     * 联系人地址
     */
    private String addressPerson;

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
     * 联系人邮箱
     */
    private String phoneemail;


    /**
     * 省份
     */
    private Long bankProvince;

    /**
     * 城市
     */
    private Long bankCity;


    /**
     * 企业对公账户
     */
    private String bankno;

    /**
     * 开户行全称
     */
    private String banksubname;

    /**
     * 开户银行类型
     */
    private Long banktype;


    /**
     * 开户名你
     */
    private String accountName;

    /**
     * 银行名称
     */
    private String bankname;


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
    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getTipstype() {
        return tipstype;
    }

    public void setTipstype(Byte tipstype) {
        this.tipstype = tipstype;
    }

    public String getAddtimeStart() {
        return addtimeStart;
    }

    public void setAddtimeStart(String addtimeStart) {
        this.addtimeStart = addtimeStart;
    }

    public String getAddtimeEnd() {
        return addtimeEnd;
    }

    public void setAddtimeEnd(String addtimeEnd) {
        this.addtimeEnd = addtimeEnd;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getPhoneemail() {
        return phoneemail;
    }

    public void setPhoneemail(String phoneemail) {
        this.phoneemail = phoneemail;
    }

    public String getBankid() {
        return bankid;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public Long getBanktype() {
        return banktype;
    }

    public void setBanktype(Long banktype) {
        this.banktype = banktype;
    }
}
