package com.joiest.jpf.entity;

import java.util.Date;

public class WeixinMenuInfo {


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

}
