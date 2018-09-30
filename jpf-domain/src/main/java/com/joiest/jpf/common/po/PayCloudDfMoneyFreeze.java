package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayCloudDfMoneyFreeze implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * pay_cloud_company_money.id
     */
    private Long companyMoneyId;

    /**
     * pay_cloud_df_money.id
     */
    private Long dfMoneyId;

    /**
     * pay_cloud_df_money.orderid
     */
    private String orderid;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 冻结原因/返回信息
     */
    private String content;

    /**
     * 1:冻结default 2:运营申请解冻 3:财务审核通过 4:财务拒绝
     */
    private Integer status;

    /**
     * 1:金额未解冻default 2:已解冻
     */
    private Integer moneyStatus;

    /**
     * 冻结时间
     */
    private Date addtime;

    /**
     * 运营操作人员id
     */
    private Integer operatorId;

    /**
     * 运营人员姓名
     */
    private String operatorName;

    /**
     * 运营申请时间
     */
    private Date operatorTime;

    /**
     * 财务审核人员id
     */
    private Integer financeId;

    /**
     * 财务审核人员姓名
     */
    private String financeName;

    /**
     * 财务审核时间
     */
    private Date financeTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyMoneyId() {
        return companyMoneyId;
    }

    public void setCompanyMoneyId(Long companyMoneyId) {
        this.companyMoneyId = companyMoneyId;
    }

    public Long getDfMoneyId() {
        return dfMoneyId;
    }

    public void setDfMoneyId(Long dfMoneyId) {
        this.dfMoneyId = dfMoneyId;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMoneyStatus() {
        return moneyStatus;
    }

    public void setMoneyStatus(Integer moneyStatus) {
        this.moneyStatus = moneyStatus;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Integer getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Integer financeId) {
        this.financeId = financeId;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName == null ? null : financeName.trim();
    }

    public Date getFinanceTime() {
        return financeTime;
    }

    public void setFinanceTime(Date financeTime) {
        this.financeTime = financeTime;
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
        sb.append(", companyMoneyId=").append(companyMoneyId);
        sb.append(", dfMoneyId=").append(dfMoneyId);
        sb.append(", orderid=").append(orderid);
        sb.append(", money=").append(money);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", moneyStatus=").append(moneyStatus);
        sb.append(", addtime=").append(addtime);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", operatorTime=").append(operatorTime);
        sb.append(", financeId=").append(financeId);
        sb.append(", financeName=").append(financeName);
        sb.append(", financeTime=").append(financeTime);
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
        PayCloudDfMoneyFreeze other = (PayCloudDfMoneyFreeze) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyMoneyId() == null ? other.getCompanyMoneyId() == null : this.getCompanyMoneyId().equals(other.getCompanyMoneyId()))
            && (this.getDfMoneyId() == null ? other.getDfMoneyId() == null : this.getDfMoneyId().equals(other.getDfMoneyId()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getMoneyStatus() == null ? other.getMoneyStatus() == null : this.getMoneyStatus().equals(other.getMoneyStatus()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getOperatorTime() == null ? other.getOperatorTime() == null : this.getOperatorTime().equals(other.getOperatorTime()))
            && (this.getFinanceId() == null ? other.getFinanceId() == null : this.getFinanceId().equals(other.getFinanceId()))
            && (this.getFinanceName() == null ? other.getFinanceName() == null : this.getFinanceName().equals(other.getFinanceName()))
            && (this.getFinanceTime() == null ? other.getFinanceTime() == null : this.getFinanceTime().equals(other.getFinanceTime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyMoneyId() == null) ? 0 : getCompanyMoneyId().hashCode());
        result = prime * result + ((getDfMoneyId() == null) ? 0 : getDfMoneyId().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMoneyStatus() == null) ? 0 : getMoneyStatus().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getOperatorTime() == null) ? 0 : getOperatorTime().hashCode());
        result = prime * result + ((getFinanceId() == null) ? 0 : getFinanceId().hashCode());
        result = prime * result + ((getFinanceName() == null) ? 0 : getFinanceName().hashCode());
        result = prime * result + ((getFinanceTime() == null) ? 0 : getFinanceTime().hashCode());
        return result;
    }
}