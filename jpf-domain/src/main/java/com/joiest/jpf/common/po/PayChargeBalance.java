package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayChargeBalance implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 威能余额
     */
    private BigDecimal wnBalance;

    /**
     * 报警阀值
     */
    private BigDecimal wnAlertLimit;

    /**
     * 报警开关 0=关闭 1=打开 短信报警过一次后就关闭
     */
    private Byte wnAlertSwitch;

    /**
     * 报警电话
     */
    private String wnAlertPhone;

    /**
     * 停止使用接口的阀值
     */
    private BigDecimal wnStopLimit;

    /**
     * 更新时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getWnBalance() {
        return wnBalance;
    }

    public void setWnBalance(BigDecimal wnBalance) {
        this.wnBalance = wnBalance;
    }

    public BigDecimal getWnAlertLimit() {
        return wnAlertLimit;
    }

    public void setWnAlertLimit(BigDecimal wnAlertLimit) {
        this.wnAlertLimit = wnAlertLimit;
    }

    public Byte getWnAlertSwitch() {
        return wnAlertSwitch;
    }

    public void setWnAlertSwitch(Byte wnAlertSwitch) {
        this.wnAlertSwitch = wnAlertSwitch;
    }

    public String getWnAlertPhone() {
        return wnAlertPhone;
    }

    public void setWnAlertPhone(String wnAlertPhone) {
        this.wnAlertPhone = wnAlertPhone == null ? null : wnAlertPhone.trim();
    }

    public BigDecimal getWnStopLimit() {
        return wnStopLimit;
    }

    public void setWnStopLimit(BigDecimal wnStopLimit) {
        this.wnStopLimit = wnStopLimit;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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
        sb.append(", wnBalance=").append(wnBalance);
        sb.append(", wnAlertLimit=").append(wnAlertLimit);
        sb.append(", wnAlertSwitch=").append(wnAlertSwitch);
        sb.append(", wnAlertPhone=").append(wnAlertPhone);
        sb.append(", wnStopLimit=").append(wnStopLimit);
        sb.append(", updatetime=").append(updatetime);
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
        PayChargeBalance other = (PayChargeBalance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWnBalance() == null ? other.getWnBalance() == null : this.getWnBalance().equals(other.getWnBalance()))
            && (this.getWnAlertLimit() == null ? other.getWnAlertLimit() == null : this.getWnAlertLimit().equals(other.getWnAlertLimit()))
            && (this.getWnAlertSwitch() == null ? other.getWnAlertSwitch() == null : this.getWnAlertSwitch().equals(other.getWnAlertSwitch()))
            && (this.getWnAlertPhone() == null ? other.getWnAlertPhone() == null : this.getWnAlertPhone().equals(other.getWnAlertPhone()))
            && (this.getWnStopLimit() == null ? other.getWnStopLimit() == null : this.getWnStopLimit().equals(other.getWnStopLimit()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWnBalance() == null) ? 0 : getWnBalance().hashCode());
        result = prime * result + ((getWnAlertLimit() == null) ? 0 : getWnAlertLimit().hashCode());
        result = prime * result + ((getWnAlertSwitch() == null) ? 0 : getWnAlertSwitch().hashCode());
        result = prime * result + ((getWnAlertPhone() == null) ? 0 : getWnAlertPhone().hashCode());
        result = prime * result + ((getWnStopLimit() == null) ? 0 : getWnStopLimit().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}