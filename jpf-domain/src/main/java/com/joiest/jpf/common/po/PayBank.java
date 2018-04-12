package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayBank implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 银行名称
     */
    private String paybankname;

    /**
     * 银行类型
     */
    private String tpid;

    /**
     * 银行编码
     */
    private String bankcode;

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

    public String getPaybankname() {
        return paybankname;
    }

    public void setPaybankname(String paybankname) {
        this.paybankname = paybankname == null ? null : paybankname.trim();
    }

    public String getTpid() {
        return tpid;
    }

    public void setTpid(String tpid) {
        this.tpid = tpid == null ? null : tpid.trim();
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode == null ? null : bankcode.trim();
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
        sb.append(", paybankname=").append(paybankname);
        sb.append(", tpid=").append(tpid);
        sb.append(", bankcode=").append(bankcode);
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
        PayBank other = (PayBank) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPaybankname() == null ? other.getPaybankname() == null : this.getPaybankname().equals(other.getPaybankname()))
            && (this.getTpid() == null ? other.getTpid() == null : this.getTpid().equals(other.getTpid()))
            && (this.getBankcode() == null ? other.getBankcode() == null : this.getBankcode().equals(other.getBankcode()))
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
        result = prime * result + ((getPaybankname() == null) ? 0 : getPaybankname().hashCode());
        result = prime * result + ((getTpid() == null) ? 0 : getTpid().hashCode());
        result = prime * result + ((getBankcode() == null) ? 0 : getBankcode().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }
}