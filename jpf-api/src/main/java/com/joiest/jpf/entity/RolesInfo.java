package com.joiest.jpf.entity;

import java.util.Date;

public class RolesInfo {

    /**
     * ID主键
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 简介
     */
    private String intro;

    /**
     * 状态：0：正常,1:禁用;default:0
     */
    private String status;

    /**
     * 创建日期
     */
    private Date created;

    /**
     * 更新日期

    private Date updateTime;  */

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
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
