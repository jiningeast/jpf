package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudDfMoneyFreezeInfo {

    /**
     *
     */
    private String id;

    /**
     * 企业ID
     */
    private String companyId;

    /**
     * 企业名称
     */
    private String companyName;

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
    private String companyMoneyId;

    /**
     * pay_cloud_df_money.id
     */
    private String dfMoneyId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getCompanyMoneyId() {
        return companyMoneyId;
    }

    public void setCompanyMoneyId(String companyMoneyId) {
        this.companyMoneyId = companyMoneyId;
    }

    public String getDfMoneyId() {
        return dfMoneyId;
    }

    public void setDfMoneyId(String dfMoneyId) {
        this.dfMoneyId = dfMoneyId;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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
        this.info = info;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
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
        this.operatorName = operatorName;
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
        this.financeName = financeName;
    }

    public Date getFinanceTime() {
        return financeTime;
    }

    public void setFinanceTime(Date financeTime) {
        this.financeTime = financeTime;
    }
}
