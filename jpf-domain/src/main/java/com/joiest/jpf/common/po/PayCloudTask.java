package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class PayCloudTask implements Serializable {
    /**
     * 
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

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpratorId() {
        return opratorId;
    }

    public void setOpratorId(String opratorId) {
        this.opratorId = opratorId == null ? null : opratorId.trim();
    }

    public String getOpratorName() {
        return opratorName;
    }

    public void setOpratorName(String opratorName) {
        this.opratorName = opratorName == null ? null : opratorName.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo == null ? null : agentNo.trim();
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo == null ? null : merchNo.trim();
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno == null ? null : batchno.trim();
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
        sb.append(", opratorId=").append(opratorId);
        sb.append(", opratorName=").append(opratorName);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", agentNo=").append(agentNo);
        sb.append(", merchNo=").append(merchNo);
        sb.append(", batchno=").append(batchno);
        sb.append(", status=").append(status);
        sb.append(", created=").append(created);
        sb.append(", finishtime=").append(finishtime);
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
        PayCloudTask other = (PayCloudTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOpratorId() == null ? other.getOpratorId() == null : this.getOpratorId().equals(other.getOpratorId()))
            && (this.getOpratorName() == null ? other.getOpratorName() == null : this.getOpratorName().equals(other.getOpratorName()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getAgentNo() == null ? other.getAgentNo() == null : this.getAgentNo().equals(other.getAgentNo()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getBatchno() == null ? other.getBatchno() == null : this.getBatchno().equals(other.getBatchno()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getFinishtime() == null ? other.getFinishtime() == null : this.getFinishtime().equals(other.getFinishtime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOpratorId() == null) ? 0 : getOpratorId().hashCode());
        result = prime * result + ((getOpratorName() == null) ? 0 : getOpratorName().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getAgentNo() == null) ? 0 : getAgentNo().hashCode());
        result = prime * result + ((getMerchNo() == null) ? 0 : getMerchNo().hashCode());
        result = prime * result + ((getBatchno() == null) ? 0 : getBatchno().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getFinishtime() == null) ? 0 : getFinishtime().hashCode());
        return result;
    }
}