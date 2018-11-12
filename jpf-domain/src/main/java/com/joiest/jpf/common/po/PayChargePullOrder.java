package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayChargePullOrder implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * PU+毫秒时间戳+6位随机数
     */
    private String orderNo;

    /**
     * 商户id
     */
    private String companyId;

    /**
     * 商户号
     */
    private String merchNo;

    /**
     * 拉取金额
     */
    private BigDecimal money;

    /**
     * 实际匹配到的金额
     */
    private BigDecimal matchMoney;

    /**
     * 匹配到的记录个数
     */
    private Integer matchRecordsAmount;

    /**
     * 
     */
    private Date addtime;

    /**
     * 
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo == null ? null : merchNo.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMatchMoney() {
        return matchMoney;
    }

    public void setMatchMoney(BigDecimal matchMoney) {
        this.matchMoney = matchMoney;
    }

    public Integer getMatchRecordsAmount() {
        return matchRecordsAmount;
    }

    public void setMatchRecordsAmount(Integer matchRecordsAmount) {
        this.matchRecordsAmount = matchRecordsAmount;
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
        sb.append(", orderNo=").append(orderNo);
        sb.append(", companyId=").append(companyId);
        sb.append(", merchNo=").append(merchNo);
        sb.append(", money=").append(money);
        sb.append(", matchMoney=").append(matchMoney);
        sb.append(", matchRecordsAmount=").append(matchRecordsAmount);
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
        PayChargePullOrder other = (PayChargePullOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getMatchMoney() == null ? other.getMatchMoney() == null : this.getMatchMoney().equals(other.getMatchMoney()))
            && (this.getMatchRecordsAmount() == null ? other.getMatchRecordsAmount() == null : this.getMatchRecordsAmount().equals(other.getMatchRecordsAmount()))
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
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getMerchNo() == null) ? 0 : getMerchNo().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getMatchMoney() == null) ? 0 : getMatchMoney().hashCode());
        result = prime * result + ((getMatchRecordsAmount() == null) ? 0 : getMatchRecordsAmount().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}