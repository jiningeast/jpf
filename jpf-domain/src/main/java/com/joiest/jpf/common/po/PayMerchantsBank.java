package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayMerchantsBank implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 对应yifuwang库中user表
     */
    private Long muserid;

    /**
     * 省份
     */
    private Long province;

    /**
     * 城市
     */
    private Long city;

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
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Long getMuserid() {
        return muserid;
    }

    public void setMuserid(Long muserid) {
        this.muserid = muserid;
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

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
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
        this.bankno = bankno == null ? null : bankno.trim();
    }

    public String getBanksubname() {
        return banksubname;
    }

    public void setBanksubname(String banksubname) {
        this.banksubname = banksubname == null ? null : banksubname.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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
        sb.append(", mtsid=").append(mtsid);
        sb.append(", muserid=").append(muserid);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", bankname=").append(bankname);
        sb.append(", banktype=").append(banktype);
        sb.append(", bankno=").append(bankno);
        sb.append(", banksubname=").append(banksubname);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
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
        PayMerchantsBank other = (PayMerchantsBank) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getMuserid() == null ? other.getMuserid() == null : this.getMuserid().equals(other.getMuserid()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getBankname() == null ? other.getBankname() == null : this.getBankname().equals(other.getBankname()))
            && (this.getBanktype() == null ? other.getBanktype() == null : this.getBanktype().equals(other.getBanktype()))
            && (this.getBankno() == null ? other.getBankno() == null : this.getBankno().equals(other.getBankno()))
            && (this.getBanksubname() == null ? other.getBanksubname() == null : this.getBanksubname().equals(other.getBanksubname()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMtsid() == null) ? 0 : getMtsid().hashCode());
        result = prime * result + ((getMuserid() == null) ? 0 : getMuserid().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getBankname() == null) ? 0 : getBankname().hashCode());
        result = prime * result + ((getBanktype() == null) ? 0 : getBanktype().hashCode());
        result = prime * result + ((getBankno() == null) ? 0 : getBankno().hashCode());
        result = prime * result + ((getBanksubname() == null) ? 0 : getBanksubname().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }
}