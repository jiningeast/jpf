package com.joiest.jpf.common.custom;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopCompanyCustom implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 商户号：SP+时间戳+4位随机数
     */
    private String merchNo;

    /**
     * 
     */
    private String companyName;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 
     */
    private String receiveName;

    /**
     * 
     */
    private String receivePhone;

    /**
     * 接收人邮箱
     */
    private String receiveEmail;

    /**
     * 销售姓名
     */
    private String saleName;

    /**
     * 销售电话
     */
    private String salePhone;

    /**
     * 商户状态 0=停用 1=启用
     */
    private Byte status;

    /**
     * 余额
     */
    private BigDecimal money;

    /**
     * 余额校验码
     */
    private String moneyCode;

    /**
     * 
     */
    private Date addtime;

    /**
     * 转让百分比
     */
    private BigDecimal percent;

    /**
     * 欣享用户的登录名称
     */
    private String loginName;

    /**
     * 欣享用户的登录密码
     */
    private String loginPwd;

    /**
     * 是否是第一次登录
     */
    private Byte isFirstLogin;

    /**
     * 账户是否开通，0未开通，1已开通
     */
    private Byte accountStatus;

    private static final long serialVersionUID = 1L;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Byte getIsFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(Byte isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public Byte getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Byte accountStatus) {
        this.accountStatus = accountStatus;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail == null ? null : receiveEmail.trim();
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName == null ? null : saleName.trim();
    }

    public String getSalePhone() {
        return salePhone;
    }

    public void setSalePhone(String salePhone) {
        this.salePhone = salePhone == null ? null : salePhone.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMoneyCode() {
        return moneyCode;
    }

    public void setMoneyCode(String moneyCode) {
        this.moneyCode = moneyCode == null ? null : moneyCode.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
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
        sb.append(", companyName=").append(companyName);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", receiveName=").append(receiveName);
        sb.append(", receivePhone=").append(receivePhone);
        sb.append(", receiveEmail=").append(receiveEmail);
        sb.append(", saleName=").append(saleName);
        sb.append(", salePhone=").append(salePhone);
        sb.append(", status=").append(status);
        sb.append(", money=").append(money);
        sb.append(", moneyCode=").append(moneyCode);
        sb.append(", addtime=").append(addtime);
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
        PayShopCompanyCustom other = (PayShopCompanyCustom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getReceiveName() == null ? other.getReceiveName() == null : this.getReceiveName().equals(other.getReceiveName()))
            && (this.getReceivePhone() == null ? other.getReceivePhone() == null : this.getReceivePhone().equals(other.getReceivePhone()))
            && (this.getReceiveEmail() == null ? other.getReceiveEmail() == null : this.getReceiveEmail().equals(other.getReceiveEmail()))
            && (this.getSaleName() == null ? other.getSaleName() == null : this.getSaleName().equals(other.getSaleName()))
            && (this.getSalePhone() == null ? other.getSalePhone() == null : this.getSalePhone().equals(other.getSalePhone()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getMoneyCode() == null ? other.getMoneyCode() == null : this.getMoneyCode().equals(other.getMoneyCode()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
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
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getReceiveName() == null) ? 0 : getReceiveName().hashCode());
        result = prime * result + ((getReceivePhone() == null) ? 0 : getReceivePhone().hashCode());
        result = prime * result + ((getReceiveEmail() == null) ? 0 : getReceiveEmail().hashCode());
        result = prime * result + ((getSaleName() == null) ? 0 : getSaleName().hashCode());
        result = prime * result + ((getSalePhone() == null) ? 0 : getSalePhone().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getMoneyCode() == null) ? 0 : getMoneyCode().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        return result;
    }
}