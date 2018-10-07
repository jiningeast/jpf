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
     * 企业ID
     */
    private Integer companyid;

    /**
     * 企业总金额
     */
    private BigDecimal companyCloudmoney;

    /**
     * 企业预付款金额(不包含此次冻结金额)
     */
    private BigDecimal companyAdvanceMoney;

    /**
     * 企业当前冻结金额(不包含此次冻结金额)
     */
    private BigDecimal companyFreezeMoney;

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
     * 冻结金额
     */
    private BigDecimal freezeMoney;

    /**
     * 
     */
    private String info;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 冻结原因/返回信息
     */
    private String returnContent;

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

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public BigDecimal getCompanyCloudmoney() {
        return companyCloudmoney;
    }

    public void setCompanyCloudmoney(BigDecimal companyCloudmoney) {
        this.companyCloudmoney = companyCloudmoney;
    }

    public BigDecimal getCompanyAdvanceMoney() {
        return companyAdvanceMoney;
    }

    public void setCompanyAdvanceMoney(BigDecimal companyAdvanceMoney) {
        this.companyAdvanceMoney = companyAdvanceMoney;
    }

    public BigDecimal getCompanyFreezeMoney() {
        return companyFreezeMoney;
    }

    public void setCompanyFreezeMoney(BigDecimal companyFreezeMoney) {
        this.companyFreezeMoney = companyFreezeMoney;
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

    public BigDecimal getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(BigDecimal freezeMoney) {
        this.freezeMoney = freezeMoney;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent == null ? null : returnContent.trim();
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
        sb.append(", companyid=").append(companyid);
        sb.append(", companyCloudmoney=").append(companyCloudmoney);
        sb.append(", companyAdvanceMoney=").append(companyAdvanceMoney);
        sb.append(", companyFreezeMoney=").append(companyFreezeMoney);
        sb.append(", companyMoneyId=").append(companyMoneyId);
        sb.append(", dfMoneyId=").append(dfMoneyId);
        sb.append(", orderid=").append(orderid);
        sb.append(", freezeMoney=").append(freezeMoney);
        sb.append(", info=").append(info);
        sb.append(", remarks=").append(remarks);
        sb.append(", returnContent=").append(returnContent);
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
            && (this.getCompanyid() == null ? other.getCompanyid() == null : this.getCompanyid().equals(other.getCompanyid()))
            && (this.getCompanyCloudmoney() == null ? other.getCompanyCloudmoney() == null : this.getCompanyCloudmoney().equals(other.getCompanyCloudmoney()))
            && (this.getCompanyAdvanceMoney() == null ? other.getCompanyAdvanceMoney() == null : this.getCompanyAdvanceMoney().equals(other.getCompanyAdvanceMoney()))
            && (this.getCompanyFreezeMoney() == null ? other.getCompanyFreezeMoney() == null : this.getCompanyFreezeMoney().equals(other.getCompanyFreezeMoney()))
            && (this.getCompanyMoneyId() == null ? other.getCompanyMoneyId() == null : this.getCompanyMoneyId().equals(other.getCompanyMoneyId()))
            && (this.getDfMoneyId() == null ? other.getDfMoneyId() == null : this.getDfMoneyId().equals(other.getDfMoneyId()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getFreezeMoney() == null ? other.getFreezeMoney() == null : this.getFreezeMoney().equals(other.getFreezeMoney()))
            && (this.getInfo() == null ? other.getInfo() == null : this.getInfo().equals(other.getInfo()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getReturnContent() == null ? other.getReturnContent() == null : this.getReturnContent().equals(other.getReturnContent()))
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
        result = prime * result + ((getCompanyid() == null) ? 0 : getCompanyid().hashCode());
        result = prime * result + ((getCompanyCloudmoney() == null) ? 0 : getCompanyCloudmoney().hashCode());
        result = prime * result + ((getCompanyAdvanceMoney() == null) ? 0 : getCompanyAdvanceMoney().hashCode());
        result = prime * result + ((getCompanyFreezeMoney() == null) ? 0 : getCompanyFreezeMoney().hashCode());
        result = prime * result + ((getCompanyMoneyId() == null) ? 0 : getCompanyMoneyId().hashCode());
        result = prime * result + ((getDfMoneyId() == null) ? 0 : getDfMoneyId().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getFreezeMoney() == null) ? 0 : getFreezeMoney().hashCode());
        result = prime * result + ((getInfo() == null) ? 0 : getInfo().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getReturnContent() == null) ? 0 : getReturnContent().hashCode());
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