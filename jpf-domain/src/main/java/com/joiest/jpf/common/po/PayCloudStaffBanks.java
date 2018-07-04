package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigInteger;

public class PayCloudStaffBanks implements Serializable {
    /**
     * 自增ID
     */
    private BigInteger id;

    /**
     * 公司员工ID
     */
    private Long staffid;

    /**
     * 银行ID
     */
    private Long bankid;

    /**
     * 银行卡号
     */
    private String bankno;

    /**
     * 收款人
     */
    private String banknickname;

    /**
     * 银行预留手机号
     */
    private String bankphone;

    /**
     * 开户行
     */
    private String bankname;

    /**
     * 开户行省
     */
    private String bankprovince;

    /**
     * 开户行市
     */
    private String bankcity;

    /**
     * 用户状态  0 银行卡未激活  1  银行卡已激活
     */
    private String bankActive;

    /**
     * 收款人账号属性 0=PRIVATE对私,1=PUBLIC对公
     */
    private String bankacctattr;

    private static final long serialVersionUID = 1L;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Long getStaffid() {
        return staffid;
    }

    public void setStaffid(Long staffid) {
        this.staffid = staffid;
    }

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno == null ? null : bankno.trim();
    }

    public String getBanknickname() {
        return banknickname;
    }

    public void setBanknickname(String banknickname) {
        this.banknickname = banknickname == null ? null : banknickname.trim();
    }

    public String getBankphone() {
        return bankphone;
    }

    public void setBankphone(String bankphone) {
        this.bankphone = bankphone == null ? null : bankphone.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankprovince() {
        return bankprovince;
    }

    public void setBankprovince(String bankprovince) {
        this.bankprovince = bankprovince == null ? null : bankprovince.trim();
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity == null ? null : bankcity.trim();
    }

    public String getBankActive() {
        return bankActive;
    }

    public void setBankActive(String bankActive) {
        this.bankActive = bankActive == null ? null : bankActive.trim();
    }

    public String getBankacctattr() {
        return bankacctattr;
    }

    public void setBankacctattr(String bankacctattr) {
        this.bankacctattr = bankacctattr == null ? null : bankacctattr.trim();
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
        sb.append(", staffid=").append(staffid);
        sb.append(", bankid=").append(bankid);
        sb.append(", bankno=").append(bankno);
        sb.append(", banknickname=").append(banknickname);
        sb.append(", bankphone=").append(bankphone);
        sb.append(", bankname=").append(bankname);
        sb.append(", bankprovince=").append(bankprovince);
        sb.append(", bankcity=").append(bankcity);
        sb.append(", bankActive=").append(bankActive);
        sb.append(", bankacctattr=").append(bankacctattr);
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
        PayCloudStaffBanks other = (PayCloudStaffBanks) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStaffid() == null ? other.getStaffid() == null : this.getStaffid().equals(other.getStaffid()))
            && (this.getBankid() == null ? other.getBankid() == null : this.getBankid().equals(other.getBankid()))
            && (this.getBankno() == null ? other.getBankno() == null : this.getBankno().equals(other.getBankno()))
            && (this.getBanknickname() == null ? other.getBanknickname() == null : this.getBanknickname().equals(other.getBanknickname()))
            && (this.getBankphone() == null ? other.getBankphone() == null : this.getBankphone().equals(other.getBankphone()))
            && (this.getBankname() == null ? other.getBankname() == null : this.getBankname().equals(other.getBankname()))
            && (this.getBankprovince() == null ? other.getBankprovince() == null : this.getBankprovince().equals(other.getBankprovince()))
            && (this.getBankcity() == null ? other.getBankcity() == null : this.getBankcity().equals(other.getBankcity()))
            && (this.getBankActive() == null ? other.getBankActive() == null : this.getBankActive().equals(other.getBankActive()))
            && (this.getBankacctattr() == null ? other.getBankacctattr() == null : this.getBankacctattr().equals(other.getBankacctattr()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStaffid() == null) ? 0 : getStaffid().hashCode());
        result = prime * result + ((getBankid() == null) ? 0 : getBankid().hashCode());
        result = prime * result + ((getBankno() == null) ? 0 : getBankno().hashCode());
        result = prime * result + ((getBanknickname() == null) ? 0 : getBanknickname().hashCode());
        result = prime * result + ((getBankphone() == null) ? 0 : getBankphone().hashCode());
        result = prime * result + ((getBankname() == null) ? 0 : getBankname().hashCode());
        result = prime * result + ((getBankprovince() == null) ? 0 : getBankprovince().hashCode());
        result = prime * result + ((getBankcity() == null) ? 0 : getBankcity().hashCode());
        result = prime * result + ((getBankActive() == null) ? 0 : getBankActive().hashCode());
        result = prime * result + ((getBankacctattr() == null) ? 0 : getBankacctattr().hashCode());
        return result;
    }
}