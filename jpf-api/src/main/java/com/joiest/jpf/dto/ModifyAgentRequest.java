package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ModifyAgentRequest {

    /**
     * 自增ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 代理类型
     */
    private String tpid;

    /**
     * 商户上线id集合，一级id:二级ID:三级ID
     */
    private String superiorid;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    /**
     * 上级商户ID
     */
    private String mtsidBelong;

    public String getMtsidBelong() {
        return mtsidBelong;
    }

    public void setMtsidBelong(String mtsidBelong) {
        this.mtsidBelong = mtsidBelong;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public String getTpid() {
        return tpid;
    }

    public void setTpid(String tpid) {
        this.tpid = tpid == null ? null : tpid.trim();
    }

    public String getSuperiorid() {
        return superiorid;
    }

    public void setSuperiorid(String superiorid) {
        this.superiorid = superiorid == null ? null : superiorid.trim();
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
