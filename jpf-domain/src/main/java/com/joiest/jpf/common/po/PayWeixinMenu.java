package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayWeixinMenu implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 公众号id
     */
    private Integer mpid;

    /**
     * 菜单信息
     */
    private String menu;

    /**
     * 最后操作人
     */
    private Integer opid;

    /**
     * 
     */
    private Date created;

    /**
     * 
     */
    private Date updated;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMpid() {
        return mpid;
    }

    public void setMpid(Integer mpid) {
        this.mpid = mpid;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu == null ? null : menu.trim();
    }

    public Integer getOpid() {
        return opid;
    }

    public void setOpid(Integer opid) {
        this.opid = opid;
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
        sb.append(", mpid=").append(mpid);
        sb.append(", menu=").append(menu);
        sb.append(", opid=").append(opid);
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
        PayWeixinMenu other = (PayWeixinMenu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMpid() == null ? other.getMpid() == null : this.getMpid().equals(other.getMpid()))
            && (this.getMenu() == null ? other.getMenu() == null : this.getMenu().equals(other.getMenu()))
            && (this.getOpid() == null ? other.getOpid() == null : this.getOpid().equals(other.getOpid()))
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
        result = prime * result + ((getMpid() == null) ? 0 : getMpid().hashCode());
        result = prime * result + ((getMenu() == null) ? 0 : getMenu().hashCode());
        result = prime * result + ((getOpid() == null) ? 0 : getOpid().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }
}