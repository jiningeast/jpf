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
    private BigDecimal balance;

    /**
     * 报警阀值
     */
    private BigDecimal alertLimit;

    /**
     * 报警开关 0=关闭 1=打开 短信报警过一次后就关闭
     */
    private Byte alertSwitch;

    /**
     * 报警电话
     */
    private String alertPhone;

    /**
     * 停止使用接口的阀值
     */
    private BigDecimal stopLimit;

    /**
     * 接口商家 0=欧飞 1=威能
     */
    private Byte type;

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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAlertLimit() {
        return alertLimit;
    }

    public void setAlertLimit(BigDecimal alertLimit) {
        this.alertLimit = alertLimit;
    }

    public Byte getAlertSwitch() {
        return alertSwitch;
    }

    public void setAlertSwitch(Byte alertSwitch) {
        this.alertSwitch = alertSwitch;
    }

    public String getAlertPhone() {
        return alertPhone;
    }

    public void setAlertPhone(String alertPhone) {
        this.alertPhone = alertPhone == null ? null : alertPhone.trim();
    }

    public BigDecimal getStopLimit() {
        return stopLimit;
    }

    public void setStopLimit(BigDecimal stopLimit) {
        this.stopLimit = stopLimit;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
        sb.append(", balance=").append(balance);
        sb.append(", alertLimit=").append(alertLimit);
        sb.append(", alertSwitch=").append(alertSwitch);
        sb.append(", alertPhone=").append(alertPhone);
        sb.append(", stopLimit=").append(stopLimit);
        sb.append(", type=").append(type);
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
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getAlertLimit() == null ? other.getAlertLimit() == null : this.getAlertLimit().equals(other.getAlertLimit()))
            && (this.getAlertSwitch() == null ? other.getAlertSwitch() == null : this.getAlertSwitch().equals(other.getAlertSwitch()))
            && (this.getAlertPhone() == null ? other.getAlertPhone() == null : this.getAlertPhone().equals(other.getAlertPhone()))
            && (this.getStopLimit() == null ? other.getStopLimit() == null : this.getStopLimit().equals(other.getStopLimit()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
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
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getAlertLimit() == null) ? 0 : getAlertLimit().hashCode());
        result = prime * result + ((getAlertSwitch() == null) ? 0 : getAlertSwitch().hashCode());
        result = prime * result + ((getAlertPhone() == null) ? 0 : getAlertPhone().hashCode());
        result = prime * result + ((getStopLimit() == null) ? 0 : getStopLimit().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}