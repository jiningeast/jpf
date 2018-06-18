package com.joiest.jpf.entity;


import java.util.Date;

public class VirtualInfo {
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
}