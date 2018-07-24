package com.joiest.jpf.common.po;

import java.util.Date;

public class PayCloudFansource {
    /**
     * 
     */
    private Long id;

    /**
     * 地区ID
     */
    private Integer catid;

    /**
     * 地区
     */
    private String cat;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 姓名
     */
    private String name;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注人ID
     */
    private Long remarkuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat == null ? null : cat.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getRemarkuid() {
        return remarkuid;
    }

    public void setRemarkuid(Long remarkuid) {
        this.remarkuid = remarkuid;
    }
}