package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ProductInfo {

    /**
     * 自增ID
     */
    private Long pid;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 产品名称
     */
    private String pname;

    /**
     * 产品价格
     */
    private BigDecimal pmoney;

    /**
     * 产品图片地址
     */
    private String pdpicture;

    /**
     * 支付方式：
     */
    private String zftype;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    /**
     * 产品简介
     */
    private String pintro;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public BigDecimal getPmoney() {
        return pmoney;
    }

    public void setPmoney(BigDecimal pmoney) {
        this.pmoney = pmoney;
    }

    public String getPdpicture() {
        return pdpicture;
    }

    public void setPdpicture(String pdpicture) {
        this.pdpicture = pdpicture;
    }

    public String getZftype() {
        return zftype;
    }

    public void setZftype(String zftype) {
        this.zftype = zftype;
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

    public String getPintro() {
        return pintro;
    }

    public void setPintro(String pintro) {
        this.pintro = pintro;
    }
}
