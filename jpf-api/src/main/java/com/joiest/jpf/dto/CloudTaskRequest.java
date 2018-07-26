package com.joiest.jpf.dto;

import java.util.Date;

public class CloudTaskRequest {

    /**
     * 任务id
     */
    private String id;

    /**
     * 操作人姓名
     */
    private String operatorName;


    /**
     * 商户名称
     */
    private String companyName;

    /**
     * 企业名称
     */
    private String merchName;

    /**
     * 代理商户号
     */
    private String agentNo;

    /**
     * 企业商户号
     */
    private String merchNo;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 鉴权状态
     */
    private byte status;

    /**
     * 锁定状态
     */
    private byte isLock;

    /**
     * 创建时间开始
     */
    private String addtimeStart;

    /**
     * 创建时间结束
     */
    private String addtimeEnd;

    /**
     * 完成时间开始
     */
    private String finishStart;

    /**
     * 完成时间结束
     */
    private String finishEnd;

    private int page;

    private int rows;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getIsLock() {
        return isLock;
    }

    public void setIsLock(byte isLock) {
        this.isLock = isLock;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddtimeStart() {
        return addtimeStart;
    }

    public void setAddtimeStart(String addtimeStart) {
        this.addtimeStart = addtimeStart;
    }

    public String getAddtimeEnd() {
        return addtimeEnd;
    }

    public void setAddtimeEnd(String addtimeEnd) {
        this.addtimeEnd = addtimeEnd;
    }

    public String getFinishStart() {
        return finishStart;
    }

    public void setFinishStart(String finishStart) {
        this.finishStart = finishStart;
    }

    public String getFinishEnd() {
        return finishEnd;
    }

    public void setFinishEnd(String finishEnd) {
        this.finishEnd = finishEnd;
    }
}
