package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayMerchantsAgent implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 代理类型
     */
    private String tpid;

    /**
     * 商户上线id集合，一级id:二级ID:三级ID
     */
    private String superiorid;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    /**
     * 父商户ID
     */
    private Long pid;

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

    public String getTpid() {
        return tpid;
    }

    public void setTpid(String tpid) {
        this.tpid = tpid == null ? null : tpid.trim();
    }

    public String getSuperiorid() {
        return superiorid;
    }

    public void setSuperiorid(String superiorid) {
        this.superiorid = superiorid == null ? null : superiorid.trim();
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

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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
        sb.append(", tpid=").append(tpid);
        sb.append(", superiorid=").append(superiorid);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", pid=").append(pid);
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
        PayMerchantsAgent other = (PayMerchantsAgent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getTpid() == null ? other.getTpid() == null : this.getTpid().equals(other.getTpid()))
            && (this.getSuperiorid() == null ? other.getSuperiorid() == null : this.getSuperiorid().equals(other.getSuperiorid()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()));
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
        result = prime * result + ((getTpid() == null) ? 0 : getTpid().hashCode());
        result = prime * result + ((getSuperiorid() == null) ? 0 : getSuperiorid().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        return result;
    }
}