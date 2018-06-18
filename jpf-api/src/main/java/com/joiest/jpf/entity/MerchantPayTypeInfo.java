package com.joiest.jpf.entity;

import java.util.Date;

public class MerchantPayTypeInfo {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 支付类型
     */
    private Integer tpid;

    /**
     * 支付类型catpath
     */
    private String catpath;

    /**
     * 支付类型 zh
     */
    private String catpath_zh;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    /**
     * 属性配置
     */
    private String param;

    /**
     * 属性配置2
     */
    private String paramSec;

    /**
     * 银行ID
     */
    private Long bankid;

    /**
     * 银行分期ID集合 :type表
     */
    private String bankcatid;

    /**
     * 聚合商户名称
     */
    private String merch_name;

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

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public String getCatpath() {
        return catpath;
    }

    public void setCatpath(String catpath) {
        this.catpath = catpath;
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

    public String getCatpath_zh() {
        return catpath_zh;
    }

    public void setCatpath_zh(String catpath_zh) {
        this.catpath_zh = catpath_zh;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public String getBankcatid() {
        return bankcatid;
    }

    public void setBankcatid(String bankcatid) {
        this.bankcatid = bankcatid == null ? null : bankcatid.trim();
    }

    public String getMerch_name() {
        return merch_name;
    }

    public void setMerch_name(String merch_name) {
        this.merch_name = merch_name;
    }

    public String getParamSec() {
        return paramSec;
    }

    public void setParamSec(String paramSec) {
        this.paramSec = paramSec;
    }
}
