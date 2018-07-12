package com.joiest.jpf.entity;

import java.util.Date;

public class CloudTaskInfo {
    /**
     * 主键id
     */
    private String id;

    /**
     * 操作人id
     */
    private String opratorId;

    /**
     * 操作人姓名
     */
    private String opratorName;

    /**
     * 商户id
     */
    private String companyId;

    /**
     * 商户名称
     */
    private String companyName;

    /**
     * 代理商户号
     */
    private String agentNo;

    /**
     * 商户号
     */
    private String merchNo;

    /**
     * 商户类型 0=业务商户 1=代理商户
     */
    private Byte companyType;

    /**
     * 用户批次号，订单号
     */
    private String batchno;

    /**
     * 上传的excel文件路径
     */
    private String filePath;

    /**
     * 处理状态 0=未处理 1=处理中 2=完成 3=失败
     */
    private Byte status;

    /**
     * 添加时间
     */
    private Date created;

    /**
     * 完成时间
     */
    private Date finishtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpratorId() {
        return opratorId;
    }

    public void setOpratorId(String opratorId) {
        this.opratorId = opratorId;
    }

    public String getOpratorName() {
        return opratorName;
    }

    public void setOpratorName(String opratorName) {
        this.opratorName = opratorName;
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

    public Byte getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Byte companyType) {
        this.companyType = companyType;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }
}
