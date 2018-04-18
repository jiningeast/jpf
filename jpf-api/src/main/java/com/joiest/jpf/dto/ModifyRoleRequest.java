package com.joiest.jpf.dto;

import java.util.Date;

public class ModifyRoleRequest {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色简介
     */
    private String intro;

    /**
     * 禁用；0:正常
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 权限ID集合
     */
    private String rights;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights == null ? null : rights.trim();
    }

}
