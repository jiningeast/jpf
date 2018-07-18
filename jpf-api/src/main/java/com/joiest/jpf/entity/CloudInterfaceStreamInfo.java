package com.joiest.jpf.entity;

import java.util.Date;

public class CloudInterfaceStreamInfo {
    /**
     * 接口类型
     */
    private Long id;

    /**
     * 接口类型 0=OSS文件上传 1=4要素鉴权 2=短信 3=打款
     */
    private Byte type;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestContent;

    /**
     * 返回内容
     */
    private String responseContent;

    /**
     * 打款任务id
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

    /**
     * 批次订单id
     */
    private String companyMoneyId;

    /**
     *
     */
    private Date addtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
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

    public String getCompanyMoneyId() {
        return companyMoneyId;
    }

    public void setCompanyMoneyId(String companyMoneyId) {
        this.companyMoneyId = companyMoneyId;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}
