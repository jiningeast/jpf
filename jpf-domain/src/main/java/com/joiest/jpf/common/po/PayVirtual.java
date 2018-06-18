package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayVirtual implements Serializable {
    /**
     * 
     */
    private Byte catid;

    /**
     * 
     */
    private Byte pid;

    /**
     * 关联ID
     */
    private Integer relateId;

    /**
     * 
     */
    private String cat;

    /**
     * 
     */
    private String catpath;

    /**
     * 
     */
    private Integer xuhao;

    /**
     * 
     */
    private String intro;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Byte getCatid() {
        return catid;
    }

    public void setCatid(Byte catid) {
        this.catid = catid;
    }

    public Byte getPid() {
        return pid;
    }

    public void setPid(Byte pid) {
        this.pid = pid;
    }

    public Integer getRelateId() {
        return relateId;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat == null ? null : cat.trim();
    }

    public String getCatpath() {
        return catpath;
    }

    public void setCatpath(String catpath) {
        this.catpath = catpath == null ? null : catpath.trim();
    }

    public Integer getXuhao() {
        return xuhao;
    }

    public void setXuhao(Integer xuhao) {
        this.xuhao = xuhao;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
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
        sb.append(", catid=").append(catid);
        sb.append(", pid=").append(pid);
        sb.append(", relateId=").append(relateId);
        sb.append(", cat=").append(cat);
        sb.append(", catpath=").append(catpath);
        sb.append(", xuhao=").append(xuhao);
        sb.append(", intro=").append(intro);
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
        PayVirtual other = (PayVirtual) that;
        return (this.getCatid() == null ? other.getCatid() == null : this.getCatid().equals(other.getCatid()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getRelateId() == null ? other.getRelateId() == null : this.getRelateId().equals(other.getRelateId()))
            && (this.getCat() == null ? other.getCat() == null : this.getCat().equals(other.getCat()))
            && (this.getCatpath() == null ? other.getCatpath() == null : this.getCatpath().equals(other.getCatpath()))
            && (this.getXuhao() == null ? other.getXuhao() == null : this.getXuhao().equals(other.getXuhao()))
            && (this.getIntro() == null ? other.getIntro() == null : this.getIntro().equals(other.getIntro()))
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
        result = prime * result + ((getCatid() == null) ? 0 : getCatid().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getRelateId() == null) ? 0 : getRelateId().hashCode());
        result = prime * result + ((getCat() == null) ? 0 : getCat().hashCode());
        result = prime * result + ((getCatpath() == null) ? 0 : getCatpath().hashCode());
        result = prime * result + ((getXuhao() == null) ? 0 : getXuhao().hashCode());
        result = prime * result + ((getIntro() == null) ? 0 : getIntro().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}