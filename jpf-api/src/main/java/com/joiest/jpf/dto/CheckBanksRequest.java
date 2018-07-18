package com.joiest.jpf.dto;

import java.util.Date;

public class CheckBanksRequest {

    /**
     * 银行卡号
     */
    private String accountNo;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 姓名
     */
    private String name;

    /**
     * 当前时间
     */
    private Date dateTime;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 员工id
     */
    private String staffId;

    /**
     * 员工银行卡id
     */
    private String staffBanksId;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffBanksId() {
        return staffBanksId;
    }

    public void setStaffBanksId(String staffBanksId) {
        this.staffBanksId = staffBanksId;
    }
}
