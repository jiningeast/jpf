package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
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
     * 代理商户名称
     */
    private String agentName;

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
     * 任务总笔数
     */
    private Integer persons;

    /**
     * 任务总金额
     */
    private BigDecimal money;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 上传的excel文件路径
     */
    private String filePath;

    /**
     * 上传excel到阿里云oss的路径
     */
    private String ossPath;

    /**
     * 处理状态 0=未处理 1=部分失败 2=全部失败 3=全部成功
     */
    private Byte status;

    /**
     * 任务锁定 0=未锁 1=锁定
     */
    private Byte isLock;

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

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo == null ? null : merchNo.trim();
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
        this.batchno = batchno == null ? null : batchno.trim();
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getOssPath() {
        return ossPath;
    }

    public void setOssPath(String ossPath) {
        this.ossPath = ossPath == null ? null : ossPath.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsLock() {
        return isLock;
    }

    public void setIsLock(Byte isLock) {
        this.isLock = isLock;
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
        sb.append(", agentName=").append(agentName);
        sb.append(", merchNo=").append(merchNo);
        sb.append(", companyType=").append(companyType);
        sb.append(", batchno=").append(batchno);
        sb.append(", persons=").append(persons);
        sb.append(", money=").append(money);
        sb.append(", contractNo=").append(contractNo);
        sb.append(", filePath=").append(filePath);
        sb.append(", ossPath=").append(ossPath);
        sb.append(", status=").append(status);
        sb.append(", isLock=").append(isLock);
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
            && (this.getAgentName() == null ? other.getAgentName() == null : this.getAgentName().equals(other.getAgentName()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getCompanyType() == null ? other.getCompanyType() == null : this.getCompanyType().equals(other.getCompanyType()))
            && (this.getBatchno() == null ? other.getBatchno() == null : this.getBatchno().equals(other.getBatchno()))
            && (this.getPersons() == null ? other.getPersons() == null : this.getPersons().equals(other.getPersons()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getContractNo() == null ? other.getContractNo() == null : this.getContractNo().equals(other.getContractNo()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getOssPath() == null ? other.getOssPath() == null : this.getOssPath().equals(other.getOssPath()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getIsLock() == null ? other.getIsLock() == null : this.getIsLock().equals(other.getIsLock()))
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
        result = prime * result + ((getAgentName() == null) ? 0 : getAgentName().hashCode());
        result = prime * result + ((getMerchNo() == null) ? 0 : getMerchNo().hashCode());
        result = prime * result + ((getCompanyType() == null) ? 0 : getCompanyType().hashCode());
        result = prime * result + ((getBatchno() == null) ? 0 : getBatchno().hashCode());
        result = prime * result + ((getPersons() == null) ? 0 : getPersons().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getContractNo() == null) ? 0 : getContractNo().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getOssPath() == null) ? 0 : getOssPath().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsLock() == null) ? 0 : getIsLock().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getFinishtime() == null) ? 0 : getFinishtime().hashCode());
        return result;
    }
}