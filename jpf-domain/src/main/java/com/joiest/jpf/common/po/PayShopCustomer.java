package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopCustomer implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 微信昵称
     */
    private String nickname;

    /**
     * 所属公司id
     */
    private Long companyId;

    /**
     * 所属公司名称
     */
    private String companyName;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 身份证号
     */
    private String idno;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 油卡卡号
     */
    private String oilCardNo;

    /**
     * 是否实名认证 0=未认证 1=已认证
     */
    private Byte isVerify;

    /**
     * 是否是欣豆交易的买家 0=不是 1=是
     */
    private Byte isBargainBuyer;

    /**
     * 用户类型 0=普通用户 1=特殊用户
     */
    private Byte type;

    /**
     * 客户状态 0=冻结 1=正常
     */
    private Byte status;

    /**
     * 总豆
     */
    private BigDecimal dou;

    /**
     * 冻结豆
     */
    private BigDecimal freezeDou;

    /**
     * 可转让豆
     */
    private BigDecimal saleDou;

    /**
     * 充值校验码
     */
    private String code;

    /**
     * 
     */
    private Date addtime;

    /**
     * 
     */
    private Date updatetime;

    /**
     * 隶属公众号id
     */
    private String mpid;

    /**
     * 用户类型 0 正常用户 1销售用户
     */
    private Byte userType;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno == null ? null : idno.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOilCardNo() {
        return oilCardNo;
    }

    public void setOilCardNo(String oilCardNo) {
        this.oilCardNo = oilCardNo == null ? null : oilCardNo.trim();
    }

    public Byte getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Byte isVerify) {
        this.isVerify = isVerify;
    }

    public Byte getIsBargainBuyer() {
        return isBargainBuyer;
    }

    public void setIsBargainBuyer(Byte isBargainBuyer) {
        this.isBargainBuyer = isBargainBuyer;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getDou() {
        return dou;
    }

    public void setDou(BigDecimal dou) {
        this.dou = dou;
    }

    public BigDecimal getFreezeDou() {
        return freezeDou;
    }

    public void setFreezeDou(BigDecimal freezeDou) {
        this.freezeDou = freezeDou;
    }

    public BigDecimal getSaleDou() {
        return saleDou;
    }

    public void setSaleDou(BigDecimal saleDou) {
        this.saleDou = saleDou;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid == null ? null : mpid.trim();
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
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
        sb.append(", name=").append(name);
        sb.append(", nickname=").append(nickname);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", openid=").append(openid);
        sb.append(", idno=").append(idno);
        sb.append(", phone=").append(phone);
        sb.append(", oilCardNo=").append(oilCardNo);
        sb.append(", isVerify=").append(isVerify);
        sb.append(", isBargainBuyer=").append(isBargainBuyer);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", dou=").append(dou);
        sb.append(", freezeDou=").append(freezeDou);
        sb.append(", saleDou=").append(saleDou);
        sb.append(", code=").append(code);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", mpid=").append(mpid);
        sb.append(", userType=").append(userType);
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
        PayShopCustomer other = (PayShopCustomer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getOpenid() == null ? other.getOpenid() == null : this.getOpenid().equals(other.getOpenid()))
            && (this.getIdno() == null ? other.getIdno() == null : this.getIdno().equals(other.getIdno()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getOilCardNo() == null ? other.getOilCardNo() == null : this.getOilCardNo().equals(other.getOilCardNo()))
            && (this.getIsVerify() == null ? other.getIsVerify() == null : this.getIsVerify().equals(other.getIsVerify()))
            && (this.getIsBargainBuyer() == null ? other.getIsBargainBuyer() == null : this.getIsBargainBuyer().equals(other.getIsBargainBuyer()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDou() == null ? other.getDou() == null : this.getDou().equals(other.getDou()))
            && (this.getFreezeDou() == null ? other.getFreezeDou() == null : this.getFreezeDou().equals(other.getFreezeDou()))
            && (this.getSaleDou() == null ? other.getSaleDou() == null : this.getSaleDou().equals(other.getSaleDou()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getMpid() == null ? other.getMpid() == null : this.getMpid().equals(other.getMpid()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getOpenid() == null) ? 0 : getOpenid().hashCode());
        result = prime * result + ((getIdno() == null) ? 0 : getIdno().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getOilCardNo() == null) ? 0 : getOilCardNo().hashCode());
        result = prime * result + ((getIsVerify() == null) ? 0 : getIsVerify().hashCode());
        result = prime * result + ((getIsBargainBuyer() == null) ? 0 : getIsBargainBuyer().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDou() == null) ? 0 : getDou().hashCode());
        result = prime * result + ((getFreezeDou() == null) ? 0 : getFreezeDou().hashCode());
        result = prime * result + ((getSaleDou() == null) ? 0 : getSaleDou().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getMpid() == null) ? 0 : getMpid().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        return result;
    }
}