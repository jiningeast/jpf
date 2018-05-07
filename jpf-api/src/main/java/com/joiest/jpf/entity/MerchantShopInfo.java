package com.joiest.jpf.entity;

import java.util.Date;

public class MerchantShopInfo {

    /**
     * 自增ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 商户名称
     */
    private String mtsName;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 上线商户名称
     */
    private String parMtsName;

    /**
     * 路径
     */
    private String path;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

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

    public String getMtsName() {
        return mtsName;
    }

    public void setMtsName(String mtsName) {
        this.mtsName = mtsName;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getParMtsName() {
        return parMtsName;
    }

    public void setParMtsName(String parMtsName) {
        this.parMtsName = parMtsName;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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
