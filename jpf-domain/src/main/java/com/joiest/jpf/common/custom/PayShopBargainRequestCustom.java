package com.joiest.jpf.common.custom;

import java.io.Serializable;
import java.util.Date;

public class PayShopBargainRequestCustom implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 发布人id
     */
    private String customerId;

    /**
     * 折损率
     */
    private Double offRate;

    /**
     * 最低豆数
     */
    private Integer minDou;

    /**
     * 是否开启此收豆信息 0=不开启 1=开启
     */
    private Byte status;

    /**
     * 添加时间
     */
    private Date addtime;


    /**
     * 微信昵称
     */
    private String nickname;


    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Double getOffRate() {
        return offRate;
    }

    public void setOffRate(Double offRate) {
        this.offRate = offRate;
    }

    public Integer getMinDou() {
        return minDou;
    }

    public void setMinDou(Integer minDou) {
        this.minDou = minDou;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
        sb.append(", customerId=").append(customerId);
        sb.append(", offRate=").append(offRate);
        sb.append(", minDou=").append(minDou);
        sb.append(", status=").append(status);
        sb.append(", addtime=").append(addtime);
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
        PayShopBargainRequestCustom other = (PayShopBargainRequestCustom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getOffRate() == null ? other.getOffRate() == null : this.getOffRate().equals(other.getOffRate()))
            && (this.getMinDou() == null ? other.getMinDou() == null : this.getMinDou().equals(other.getMinDou()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getOffRate() == null) ? 0 : getOffRate().hashCode());
        result = prime * result + ((getMinDou() == null) ? 0 : getMinDou().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        return result;
    }
}