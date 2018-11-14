package com.joiest.jpf.dto;

import java.util.Date;

public class OuFeiPayRequest {

    /**
     * 业务单号
     */
    private String billId;

    /**
     * 公司
     */
    private String company;

    /**
     * 流水号
     */
    private String detailId;

    /**
     * 支出金额
     */
    private String expenditureCost;

    /**
     * 业务类型
     */
    private String fundsType;

    /**
     * 收入金额
     */
    private String inComeCost;

    /**
     * 收支类型
     */
    private String inOutType;

    /**
     * 账户余额
     */
    private String leftBalance;

    /**
     * 修改时间
     */
    private String occurTime;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getExpenditureCost() {
        return expenditureCost;
    }

    public void setExpenditureCost(String expenditureCost) {
        this.expenditureCost = expenditureCost;
    }

    public String getFundsType() {
        return fundsType;
    }

    public void setFundsType(String fundsType) {
        this.fundsType = fundsType;
    }

    public String getInComeCost() {
        return inComeCost;
    }

    public void setInComeCost(String inComeCost) {
        this.inComeCost = inComeCost;
    }

    public String getInOutType() {
        return inOutType;
    }

    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }

    public String getLeftBalance() {
        return leftBalance;
    }

    public void setLeftBalance(String leftBalance) {
        this.leftBalance = leftBalance;
    }

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }
}