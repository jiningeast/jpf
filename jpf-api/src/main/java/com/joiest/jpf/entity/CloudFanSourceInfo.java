package com.joiest.jpf.entity;

import com.joiest.jpf.common.po.PayCloudFansource;

import java.util.Date;

public class CloudFanSourceInfo {

    /**
     *
     */
    private String id;

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
    private String remarkuid;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getRemarkuid() {
        return remarkuid;
    }

    public void setRemarkuid(String remarkuid) {
        this.remarkuid = remarkuid == null ? null : remarkuid.trim();
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", catid=").append(catid);
        sb.append(", cat=").append(cat);
        sb.append(", mobile=").append(mobile);
        sb.append(", name=").append(name);
        sb.append(", created=").append(created);
        sb.append(", remark=").append(remark);
        sb.append(", remarkuid=").append(remarkuid);
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     * @param that
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PayCloudFansource other = (PayCloudFansource) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCatid() == null ? other.getCatid() == null : this.getCatid().equals(other.getCatid()))
                && (this.getCat() == null ? other.getCat() == null : this.getCat().equals(other.getCat()))
                && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getRemarkuid() == null ? other.getRemarkuid() == null : this.getRemarkuid().equals(other.getRemarkuid()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCatid() == null) ? 0 : getCatid().hashCode());
        result = prime * result + ((getCat() == null) ? 0 : getCat().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getRemarkuid() == null) ? 0 : getRemarkuid().hashCode());
        return result;
    }

}
