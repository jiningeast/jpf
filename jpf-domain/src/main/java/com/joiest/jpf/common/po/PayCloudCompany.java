package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayCloudCompany implements Serializable {
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

    private static final long serialVersionUID = 1L;

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

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName == null ? null : merchName.trim();
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

    public String getPhoneemail() {
        return phoneemail;
    }

    public void setPhoneemail(String phoneemail) {
        this.phoneemail = phoneemail == null ? null : phoneemail.trim();
    }

    public String getTaxpayertype() {
        return taxpayertype;
    }

    public void setTaxpayertype(String taxpayertype) {
        this.taxpayertype = taxpayertype == null ? null : taxpayertype.trim();
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin == null ? null : tin.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
        sb.append(", name=").append(name);
        sb.append(", merchName=").append(merchName);
        sb.append(", phonename=").append(phonename);
        sb.append(", phone=").append(phone);
        sb.append(", bslicense=").append(bslicense);
        sb.append(", aptitude=").append(aptitude);
        sb.append(", attestation=").append(attestation);
        sb.append(", issms=").append(issms);
        sb.append(", tipstype=").append(tipstype);
        sb.append(", serviclinkuser=").append(serviclinkuser);
        sb.append(", linkphone=").append(linkphone);
        sb.append(", linkemail=").append(linkemail);
        sb.append(", privateKey=").append(privateKey);
        sb.append(", addadminid=").append(addadminid);
        sb.append(", editadminid=").append(editadminid);
        sb.append(", isshow=").append(isshow);
        sb.append(", created=").append(created);
        sb.append(", cloudmoney=").append(cloudmoney);
        sb.append(", cloudcode=").append(cloudcode);
        sb.append(", cloudpaypwd=").append(cloudpaypwd);
        sb.append(", updated=").append(updated);
        sb.append(", certificate=").append(certificate);
        sb.append(", phoneemail=").append(phoneemail);
        sb.append(", taxpayertype=").append(taxpayertype);
        sb.append(", tin=").append(tin);
        sb.append(", address=").append(address);
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
        PayCloudCompany other = (PayCloudCompany) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMerchName() == null ? other.getMerchName() == null : this.getMerchName().equals(other.getMerchName()))
            && (this.getPhonename() == null ? other.getPhonename() == null : this.getPhonename().equals(other.getPhonename()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getBslicense() == null ? other.getBslicense() == null : this.getBslicense().equals(other.getBslicense()))
            && (this.getAptitude() == null ? other.getAptitude() == null : this.getAptitude().equals(other.getAptitude()))
            && (this.getAttestation() == null ? other.getAttestation() == null : this.getAttestation().equals(other.getAttestation()))
            && (this.getIssms() == null ? other.getIssms() == null : this.getIssms().equals(other.getIssms()))
            && (this.getTipstype() == null ? other.getTipstype() == null : this.getTipstype().equals(other.getTipstype()))
            && (this.getServiclinkuser() == null ? other.getServiclinkuser() == null : this.getServiclinkuser().equals(other.getServiclinkuser()))
            && (this.getLinkphone() == null ? other.getLinkphone() == null : this.getLinkphone().equals(other.getLinkphone()))
            && (this.getLinkemail() == null ? other.getLinkemail() == null : this.getLinkemail().equals(other.getLinkemail()))
            && (this.getPrivateKey() == null ? other.getPrivateKey() == null : this.getPrivateKey().equals(other.getPrivateKey()))
            && (this.getAddadminid() == null ? other.getAddadminid() == null : this.getAddadminid().equals(other.getAddadminid()))
            && (this.getEditadminid() == null ? other.getEditadminid() == null : this.getEditadminid().equals(other.getEditadminid()))
            && (this.getIsshow() == null ? other.getIsshow() == null : this.getIsshow().equals(other.getIsshow()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getCloudmoney() == null ? other.getCloudmoney() == null : this.getCloudmoney().equals(other.getCloudmoney()))
            && (this.getCloudcode() == null ? other.getCloudcode() == null : this.getCloudcode().equals(other.getCloudcode()))
            && (this.getCloudpaypwd() == null ? other.getCloudpaypwd() == null : this.getCloudpaypwd().equals(other.getCloudpaypwd()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()))
            && (this.getCertificate() == null ? other.getCertificate() == null : this.getCertificate().equals(other.getCertificate()))
            && (this.getPhoneemail() == null ? other.getPhoneemail() == null : this.getPhoneemail().equals(other.getPhoneemail()))
            && (this.getTaxpayertype() == null ? other.getTaxpayertype() == null : this.getTaxpayertype().equals(other.getTaxpayertype()))
            && (this.getTin() == null ? other.getTin() == null : this.getTin().equals(other.getTin()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()));
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
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMerchName() == null) ? 0 : getMerchName().hashCode());
        result = prime * result + ((getPhonename() == null) ? 0 : getPhonename().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getBslicense() == null) ? 0 : getBslicense().hashCode());
        result = prime * result + ((getAptitude() == null) ? 0 : getAptitude().hashCode());
        result = prime * result + ((getAttestation() == null) ? 0 : getAttestation().hashCode());
        result = prime * result + ((getIssms() == null) ? 0 : getIssms().hashCode());
        result = prime * result + ((getTipstype() == null) ? 0 : getTipstype().hashCode());
        result = prime * result + ((getServiclinkuser() == null) ? 0 : getServiclinkuser().hashCode());
        result = prime * result + ((getLinkphone() == null) ? 0 : getLinkphone().hashCode());
        result = prime * result + ((getLinkemail() == null) ? 0 : getLinkemail().hashCode());
        result = prime * result + ((getPrivateKey() == null) ? 0 : getPrivateKey().hashCode());
        result = prime * result + ((getAddadminid() == null) ? 0 : getAddadminid().hashCode());
        result = prime * result + ((getEditadminid() == null) ? 0 : getEditadminid().hashCode());
        result = prime * result + ((getIsshow() == null) ? 0 : getIsshow().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getCloudmoney() == null) ? 0 : getCloudmoney().hashCode());
        result = prime * result + ((getCloudcode() == null) ? 0 : getCloudcode().hashCode());
        result = prime * result + ((getCloudpaypwd() == null) ? 0 : getCloudpaypwd().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        result = prime * result + ((getCertificate() == null) ? 0 : getCertificate().hashCode());
        result = prime * result + ((getPhoneemail() == null) ? 0 : getPhoneemail().hashCode());
        result = prime * result + ((getTaxpayertype() == null) ? 0 : getTaxpayertype().hashCode());
        result = prime * result + ((getTin() == null) ? 0 : getTin().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        return result;
    }
}