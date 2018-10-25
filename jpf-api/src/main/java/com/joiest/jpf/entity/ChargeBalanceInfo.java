package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ChargeBalanceInfo {
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


}