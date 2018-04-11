package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayMerchantsProduct implements Serializable {
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
     * 产品简介
     */
    private String pintro;

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
     * 商品状态：1显示，0不显示
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    private static final long serialVersionUID = 1L;

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
        this.pname = pname == null ? null : pname.trim();
    }

    public String getPintro() {
        return pintro;
    }

    public void setPintro(String pintro) {
        this.pintro = pintro == null ? null : pintro.trim();
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
        this.pdpicture = pdpicture == null ? null : pdpicture.trim();
    }

    public String getZftype() {
        return zftype;
    }

    public void setZftype(String zftype) {
        this.zftype = zftype == null ? null : zftype.trim();
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

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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
        sb.append(", pid=").append(pid);
        sb.append(", mtsid=").append(mtsid);
        sb.append(", pname=").append(pname);
        sb.append(", pintro=").append(pintro);
        sb.append(", pmoney=").append(pmoney);
        sb.append(", pdpicture=").append(pdpicture);
        sb.append(", zftype=").append(zftype);
        sb.append(", status=").append(status);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
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
        PayMerchantsProduct other = (PayMerchantsProduct) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getPname() == null ? other.getPname() == null : this.getPname().equals(other.getPname()))
            && (this.getPintro() == null ? other.getPintro() == null : this.getPintro().equals(other.getPintro()))
            && (this.getPmoney() == null ? other.getPmoney() == null : this.getPmoney().equals(other.getPmoney()))
            && (this.getPdpicture() == null ? other.getPdpicture() == null : this.getPdpicture().equals(other.getPdpicture()))
            && (this.getZftype() == null ? other.getZftype() == null : this.getZftype().equals(other.getZftype()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getMtsid() == null) ? 0 : getMtsid().hashCode());
        result = prime * result + ((getPname() == null) ? 0 : getPname().hashCode());
        result = prime * result + ((getPintro() == null) ? 0 : getPintro().hashCode());
        result = prime * result + ((getPmoney() == null) ? 0 : getPmoney().hashCode());
        result = prime * result + ((getPdpicture() == null) ? 0 : getPdpicture().hashCode());
        result = prime * result + ((getZftype() == null) ? 0 : getZftype().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }
}