package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayCloudInterfaceStream implements Serializable {
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

    private static final long serialVersionUID = 1L;

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
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent == null ? null : requestContent.trim();
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent == null ? null : responseContent.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public String getStaffBanksId() {
        return staffBanksId;
    }

    public void setStaffBanksId(String staffBanksId) {
        this.staffBanksId = staffBanksId == null ? null : staffBanksId.trim();
    }

    public String getCompanyMoneyId() {
        return companyMoneyId;
    }

    public void setCompanyMoneyId(String companyMoneyId) {
        this.companyMoneyId = companyMoneyId == null ? null : companyMoneyId.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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
        sb.append(", type=").append(type);
        sb.append(", requestUrl=").append(requestUrl);
        sb.append(", requestContent=").append(requestContent);
        sb.append(", responseContent=").append(responseContent);
        sb.append(", taskId=").append(taskId);
        sb.append(", staffId=").append(staffId);
        sb.append(", staffBanksId=").append(staffBanksId);
        sb.append(", companyMoneyId=").append(companyMoneyId);
        sb.append(", addtime=").append(addtime);
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
        PayCloudInterfaceStream other = (PayCloudInterfaceStream) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getRequestUrl() == null ? other.getRequestUrl() == null : this.getRequestUrl().equals(other.getRequestUrl()))
            && (this.getRequestContent() == null ? other.getRequestContent() == null : this.getRequestContent().equals(other.getRequestContent()))
            && (this.getResponseContent() == null ? other.getResponseContent() == null : this.getResponseContent().equals(other.getResponseContent()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getStaffId() == null ? other.getStaffId() == null : this.getStaffId().equals(other.getStaffId()))
            && (this.getStaffBanksId() == null ? other.getStaffBanksId() == null : this.getStaffBanksId().equals(other.getStaffBanksId()))
            && (this.getCompanyMoneyId() == null ? other.getCompanyMoneyId() == null : this.getCompanyMoneyId().equals(other.getCompanyMoneyId()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getRequestUrl() == null) ? 0 : getRequestUrl().hashCode());
        result = prime * result + ((getRequestContent() == null) ? 0 : getRequestContent().hashCode());
        result = prime * result + ((getResponseContent() == null) ? 0 : getResponseContent().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getStaffId() == null) ? 0 : getStaffId().hashCode());
        result = prime * result + ((getStaffBanksId() == null) ? 0 : getStaffBanksId().hashCode());
        result = prime * result + ((getCompanyMoneyId() == null) ? 0 : getCompanyMoneyId().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        return result;
    }
}