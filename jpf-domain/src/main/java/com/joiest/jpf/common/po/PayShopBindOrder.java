package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayShopBindOrder implements Serializable {
    /**
     * 主键自增id
     */
    private String id;

    /**
     * 用户id
     */
    private String customerId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String name;

    /**
     * 证件类型 1=身份证
     */
    private Byte licenseType;

    /**
     * 证件号
     */
    private String licenseNo;

    /**
     * 油卡号
     */
    private String oilCardNo;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String valideCode;

    /**
     * 绑定状态 0=未成功 1=成功
     */
    private Byte status;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(Byte licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo == null ? null : licenseNo.trim();
    }

    public String getOilCardNo() {
        return oilCardNo;
    }

    public void setOilCardNo(String oilCardNo) {
        this.oilCardNo = oilCardNo == null ? null : oilCardNo.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getValideCode() {
        return valideCode;
    }

    public void setValideCode(String valideCode) {
        this.valideCode = valideCode == null ? null : valideCode.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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
        sb.append(", customerId=").append(customerId);
        sb.append(", nickname=").append(nickname);
        sb.append(", name=").append(name);
        sb.append(", licenseType=").append(licenseType);
        sb.append(", licenseNo=").append(licenseNo);
        sb.append(", oilCardNo=").append(oilCardNo);
        sb.append(", phone=").append(phone);
        sb.append(", valideCode=").append(valideCode);
        sb.append(", status=").append(status);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
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
        PayShopBindOrder other = (PayShopBindOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLicenseType() == null ? other.getLicenseType() == null : this.getLicenseType().equals(other.getLicenseType()))
            && (this.getLicenseNo() == null ? other.getLicenseNo() == null : this.getLicenseNo().equals(other.getLicenseNo()))
            && (this.getOilCardNo() == null ? other.getOilCardNo() == null : this.getOilCardNo().equals(other.getOilCardNo()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getValideCode() == null ? other.getValideCode() == null : this.getValideCode().equals(other.getValideCode()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLicenseType() == null) ? 0 : getLicenseType().hashCode());
        result = prime * result + ((getLicenseNo() == null) ? 0 : getLicenseNo().hashCode());
        result = prime * result + ((getOilCardNo() == null) ? 0 : getOilCardNo().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getValideCode() == null) ? 0 : getValideCode().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}