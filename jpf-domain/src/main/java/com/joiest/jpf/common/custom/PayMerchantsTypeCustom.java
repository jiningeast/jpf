package com.joiest.jpf.common.custom;

import java.util.List;

public class PayMerchantsTypeCustom {

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

    private List<PayMerchantsTypeCustom> childList;

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
        this.pid = pid;
    }

    public String getCatpath() {
        return catpath;
    }

    public void setCatpath(String catpath) {
        this.catpath = catpath;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public List<PayMerchantsTypeCustom> getChildList() {
        return childList;
    }

    public void setChildList(List<PayMerchantsTypeCustom> childList) {
        this.childList = childList;
    }
}
