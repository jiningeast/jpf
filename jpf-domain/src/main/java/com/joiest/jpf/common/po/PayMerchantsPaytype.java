package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayMerchantsPaytype implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 银行ID
     */
    private Long bankid;

    /**
     * 支付类型
     */
    private Integer tpid;

    /**
     * 支付类型catpath
     */
    private String catpath;

    /**
     * 银行分期ID集合 :type表
     */
    private String bankcatid;

    /**
     * 属性配置
     */
    private String param;

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

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public String getCatpath() {
        return catpath;
    }

    public void setCatpath(String catpath) {
        this.catpath = catpath == null ? null : catpath.trim();
    }

    public String getBankcatid() {
        return bankcatid;
    }

    public void setBankcatid(String bankcatid) {
        this.bankcatid = bankcatid == null ? null : bankcatid.trim();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
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
        sb.append(", bankid=").append(bankid);
        sb.append(", tpid=").append(tpid);
        sb.append(", catpath=").append(catpath);
        sb.append(", bankcatid=").append(bankcatid);
        sb.append(", param=").append(param);
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
        PayMerchantsPaytype other = (PayMerchantsPaytype) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getBankid() == null ? other.getBankid() == null : this.getBankid().equals(other.getBankid()))
            && (this.getTpid() == null ? other.getTpid() == null : this.getTpid().equals(other.getTpid()))
            && (this.getCatpath() == null ? other.getCatpath() == null : this.getCatpath().equals(other.getCatpath()))
            && (this.getBankcatid() == null ? other.getBankcatid() == null : this.getBankcatid().equals(other.getBankcatid()))
            && (this.getParam() == null ? other.getParam() == null : this.getParam().equals(other.getParam()))
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
        result = prime * result + ((getBankid() == null) ? 0 : getBankid().hashCode());
        result = prime * result + ((getTpid() == null) ? 0 : getTpid().hashCode());
        result = prime * result + ((getCatpath() == null) ? 0 : getCatpath().hashCode());
        result = prime * result + ((getBankcatid() == null) ? 0 : getBankcatid().hashCode());
        result = prime * result + ((getParam() == null) ? 0 : getParam().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }
}