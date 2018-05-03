package com.joiest.jpf.common.po;

public class PayVirtual {
    /**
     * 
     */
    private Byte catid;

    /**
     * 
     */
    private Byte pid;

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
}