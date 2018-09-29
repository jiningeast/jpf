package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudDfMoneyFreezeInfo {

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
        this.orderid = orderid;
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
        this.content = content;
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
