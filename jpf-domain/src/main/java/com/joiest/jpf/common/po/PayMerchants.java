package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayMerchants implements Serializable {
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

    /**
     * 审核备注
     */
    private String content;

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

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName == null ? null : merchName.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname == null ? null : companyname.trim();
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
        this.linkname = linkname == null ? null : linkname.trim();
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone == null ? null : linkphone.trim();
    }

    public String getSalerphone() {
        return salerphone;
    }

    public void setSalerphone(String salerphone) {
        this.salerphone = salerphone == null ? null : salerphone.trim();
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
        this.registerip = registerip == null ? null : registerip.trim();
    }

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip == null ? null : lastloginip.trim();
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
        this.bslicense = bslicense == null ? null : bslicense.trim();
    }

    public String getAptitude() {
        return aptitude;
    }

    public void setAptitude(String aptitude) {
        this.aptitude = aptitude == null ? null : aptitude.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
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
        this.content = content == null ? null : content.trim();
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
        sb.append(", merchName=").append(merchName);
        sb.append(", username=").append(username);
        sb.append(", userpwd=").append(userpwd);
        sb.append(", companyname=").append(companyname);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", linkname=").append(linkname);
        sb.append(", linkphone=").append(linkphone);
        sb.append(", salerphone=").append(salerphone);
        sb.append(", addtime=").append(addtime);
        sb.append(", registerip=").append(registerip);
        sb.append(", lastloginip=").append(lastloginip);
        sb.append(", status=").append(status);
        sb.append(", bslicense=").append(bslicense);
        sb.append(", aptitude=").append(aptitude);
        sb.append(", logo=").append(logo);
        sb.append(", attestation=").append(attestation);
        sb.append(", muserid=").append(muserid);
        sb.append(", content=").append(content);
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
        PayMerchants other = (PayMerchants) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getMerchName() == null ? other.getMerchName() == null : this.getMerchName().equals(other.getMerchName()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getUserpwd() == null ? other.getUserpwd() == null : this.getUserpwd().equals(other.getUserpwd()))
            && (this.getCompanyname() == null ? other.getCompanyname() == null : this.getCompanyname().equals(other.getCompanyname()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getLinkname() == null ? other.getLinkname() == null : this.getLinkname().equals(other.getLinkname()))
            && (this.getLinkphone() == null ? other.getLinkphone() == null : this.getLinkphone().equals(other.getLinkphone()))
            && (this.getSalerphone() == null ? other.getSalerphone() == null : this.getSalerphone().equals(other.getSalerphone()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getRegisterip() == null ? other.getRegisterip() == null : this.getRegisterip().equals(other.getRegisterip()))
            && (this.getLastloginip() == null ? other.getLastloginip() == null : this.getLastloginip().equals(other.getLastloginip()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBslicense() == null ? other.getBslicense() == null : this.getBslicense().equals(other.getBslicense()))
            && (this.getAptitude() == null ? other.getAptitude() == null : this.getAptitude().equals(other.getAptitude()))
            && (this.getLogo() == null ? other.getLogo() == null : this.getLogo().equals(other.getLogo()))
            && (this.getAttestation() == null ? other.getAttestation() == null : this.getAttestation().equals(other.getAttestation()))
            && (this.getMuserid() == null ? other.getMuserid() == null : this.getMuserid().equals(other.getMuserid()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
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
        result = prime * result + ((getMerchName() == null) ? 0 : getMerchName().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getUserpwd() == null) ? 0 : getUserpwd().hashCode());
        result = prime * result + ((getCompanyname() == null) ? 0 : getCompanyname().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getLinkname() == null) ? 0 : getLinkname().hashCode());
        result = prime * result + ((getLinkphone() == null) ? 0 : getLinkphone().hashCode());
        result = prime * result + ((getSalerphone() == null) ? 0 : getSalerphone().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getRegisterip() == null) ? 0 : getRegisterip().hashCode());
        result = prime * result + ((getLastloginip() == null) ? 0 : getLastloginip().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBslicense() == null) ? 0 : getBslicense().hashCode());
        result = prime * result + ((getAptitude() == null) ? 0 : getAptitude().hashCode());
        result = prime * result + ((getLogo() == null) ? 0 : getLogo().hashCode());
        result = prime * result + ((getAttestation() == null) ? 0 : getAttestation().hashCode());
        result = prime * result + ((getMuserid() == null) ? 0 : getMuserid().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
}