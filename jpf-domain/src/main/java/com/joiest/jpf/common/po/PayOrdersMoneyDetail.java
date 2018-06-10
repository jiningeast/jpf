package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayOrdersMoneyDetail implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 订单ID
     */
    private Long orderid;

    /**
     * 订单总金额
     */
    private BigDecimal money;

    /**
     * 分期期数ID
     */
    private String stageId;

    /**
     * 分期名称
     */
    private String stageName;

    /**
     * 分期费率
     */
    private String stageRate;

    /**
     * 分期费率的金额
     */
    private BigDecimal stageMoney;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 商户费率
     */
    private String merchRate;

    /**
     * 商户费率的金额
     */
    private BigDecimal merchMoney;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId == null ? null : stageId.trim();
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName == null ? null : stageName.trim();
    }

    public String getStageRate() {
        return stageRate;
    }

    public void setStageRate(String stageRate) {
        this.stageRate = stageRate == null ? null : stageRate.trim();
    }

    public BigDecimal getStageMoney() {
        return stageMoney;
    }

    public void setStageMoney(BigDecimal stageMoney) {
        this.stageMoney = stageMoney;
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public String getMerchRate() {
        return merchRate;
    }

    public void setMerchRate(String merchRate) {
        this.merchRate = merchRate == null ? null : merchRate.trim();
    }

    public BigDecimal getMerchMoney() {
        return merchMoney;
    }

    public void setMerchMoney(BigDecimal merchMoney) {
        this.merchMoney = merchMoney;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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
        sb.append(", orderid=").append(orderid);
        sb.append(", money=").append(money);
        sb.append(", stageId=").append(stageId);
        sb.append(", stageName=").append(stageName);
        sb.append(", stageRate=").append(stageRate);
        sb.append(", stageMoney=").append(stageMoney);
        sb.append(", mtsid=").append(mtsid);
        sb.append(", merchRate=").append(merchRate);
        sb.append(", merchMoney=").append(merchMoney);
        sb.append(", addtime=").append(addtime);
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
        PayOrdersMoneyDetail other = (PayOrdersMoneyDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getStageId() == null ? other.getStageId() == null : this.getStageId().equals(other.getStageId()))
            && (this.getStageName() == null ? other.getStageName() == null : this.getStageName().equals(other.getStageName()))
            && (this.getStageRate() == null ? other.getStageRate() == null : this.getStageRate().equals(other.getStageRate()))
            && (this.getStageMoney() == null ? other.getStageMoney() == null : this.getStageMoney().equals(other.getStageMoney()))
            && (this.getMtsid() == null ? other.getMtsid() == null : this.getMtsid().equals(other.getMtsid()))
            && (this.getMerchRate() == null ? other.getMerchRate() == null : this.getMerchRate().equals(other.getMerchRate()))
            && (this.getMerchMoney() == null ? other.getMerchMoney() == null : this.getMerchMoney().equals(other.getMerchMoney()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
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
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getStageId() == null) ? 0 : getStageId().hashCode());
        result = prime * result + ((getStageName() == null) ? 0 : getStageName().hashCode());
        result = prime * result + ((getStageRate() == null) ? 0 : getStageRate().hashCode());
        result = prime * result + ((getStageMoney() == null) ? 0 : getStageMoney().hashCode());
        result = prime * result + ((getMtsid() == null) ? 0 : getMtsid().hashCode());
        result = prime * result + ((getMerchRate() == null) ? 0 : getMerchRate().hashCode());
        result = prime * result + ((getMerchMoney() == null) ? 0 : getMerchMoney().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}