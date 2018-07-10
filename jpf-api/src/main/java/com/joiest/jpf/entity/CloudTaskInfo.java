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
     *
     */
    private String opratorName;

    /**
     * 公司id
     */
    private String companyId;

    /**
     *
     */
    private String companyName;

    /**
     * 代理号
     */
    private String agentNo;

    /**
     * 公司号
     */
    private String merchNo;

    /**
     * 用户批次号
     */
    private String batchno;

    /**
     * 处理状态 0=未完成 1=完成 2=报错
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

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
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
