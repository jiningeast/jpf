package com.joiest.jpf.common.po;

public class PayAuth {
    /**
     * id
     */
    private Integer catid;

    /**
     * 分类ID
     */
    private Integer pid;

    /**
     * 菜单名称
     */
    private String cat;

    /**
     * 二级ID
     */
    private String catpath;

    /**
     * 备注
     */
    private String intro;

    /**
     * 排序
     */
    private Integer xuhao;

    /**
     * 1:正常；-1:删除
     */
    private Boolean status;

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Integer getXuhao() {
        return xuhao;
    }

    public void setXuhao(Integer xuhao) {
        this.xuhao = xuhao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}