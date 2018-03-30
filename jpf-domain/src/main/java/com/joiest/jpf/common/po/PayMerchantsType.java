package com.joiest.jpf.common.po;

import java.io.Serializable;

public class PayMerchantsType implements Serializable {
    /**
     * 
     */
    private Integer catid;

    /**
     * 
     */
    private String pid;

    /**
     * 
     */
    private String catpath;

    /**
     * 
     */
    private String cat;

    private static final long serialVersionUID = 1L;

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getCatpath() {
        return catpath;
    }

    public void setCatpath(String catpath) {
        this.catpath = catpath == null ? null : catpath.trim();
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat == null ? null : cat.trim();
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
        sb.append(", catid=").append(catid);
        sb.append(", pid=").append(pid);
        sb.append(", catpath=").append(catpath);
        sb.append(", cat=").append(cat);
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
        PayMerchantsType other = (PayMerchantsType) that;
        return (this.getCatid() == null ? other.getCatid() == null : this.getCatid().equals(other.getCatid()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCatpath() == null ? other.getCatpath() == null : this.getCatpath().equals(other.getCatpath()))
            && (this.getCat() == null ? other.getCat() == null : this.getCat().equals(other.getCat()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCatid() == null) ? 0 : getCatid().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCatpath() == null) ? 0 : getCatpath().hashCode());
        result = prime * result + ((getCat() == null) ? 0 : getCat().hashCode());
        return result;
    }
}