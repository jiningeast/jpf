package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayShopBatch implements Serializable {
    /**
     * 
     */
    private String id;

    /**
     * 企业id
     */
    private String companyId;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 总面额
     */
    private BigDecimal money;

    /**
     * 面额兑换豆比例 1即1元兑换1豆
     */
    private Double scale;

    /**
     * 券总数量
     */
    private Integer count;

    /**
     * 有效期
     */
    private Integer expireMonth;

    /**
     * 批次状态 0=生成券码中 1=生成完毕，待发券  2=已发券 3=已取消
     */
    private Byte status;

    /**
     * 批次详情
     */
    private String batchContent;

    /**
     * 激活数量
     */
    private Integer activetedNum;

    /**
     * 所属销售
     */
    private Integer salesId;

    /**
     * 销售名称
     */
    private String salesName;

    /**
     * 接收人
     */
    private String receiveName;

    /**
     * 接收人邮箱
     */
    private String receiveEmail;

    /**
     * 接收人电话
     */
    private String receivePhone;

    /**
     * 
     */
    private String ossUrl;

    /**
     * 邮件内容
     */
    private String emailContent;

    /**
     * 
     */
    private Date emailTime;

    /**
     * 邮件发送 0::未发送 1:已发送 2:发送失败
     */
    private Byte emailStatus;

    /**
     * 短信发送时间
     */
    private Date smsTime;

    /**
     * 短信发送 0::未发送 1:已发送 2:发送失败
     */
    private Byte smsStatus;

    /**
     * 操作人id
     */
    private String operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 
     */
    private Date addtime;

    /**
     * 
     */
    private Date updatetime;

    /**
     * 短信内容
     */
    private String smsContent;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo == null ? null : batchNo.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getExpireMonth() {
        return expireMonth;
    }

    public void setExpireMonth(Integer expireMonth) {
        this.expireMonth = expireMonth;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getBatchContent() {
        return batchContent;
    }

    public void setBatchContent(String batchContent) {
        this.batchContent = batchContent == null ? null : batchContent.trim();
    }

    public Integer getActivetedNum() {
        return activetedNum;
    }

    public void setActivetedNum(Integer activetedNum) {
        this.activetedNum = activetedNum;
    }

    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName == null ? null : salesName.trim();
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail == null ? null : receiveEmail.trim();
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl == null ? null : ossUrl.trim();
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent == null ? null : emailContent.trim();
    }

    public Date getEmailTime() {
        return emailTime;
    }

    public void setEmailTime(Date emailTime) {
        this.emailTime = emailTime;
    }

    public Byte getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Byte emailStatus) {
        this.emailStatus = emailStatus;
    }

    public Date getSmsTime() {
        return smsTime;
    }

    public void setSmsTime(Date smsTime) {
        this.smsTime = smsTime;
    }

    public Byte getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Byte smsStatus) {
        this.smsStatus = smsStatus;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent == null ? null : smsContent.trim();
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
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", batchNo=").append(batchNo);
        sb.append(", money=").append(money);
        sb.append(", scale=").append(scale);
        sb.append(", count=").append(count);
        sb.append(", expireMonth=").append(expireMonth);
        sb.append(", status=").append(status);
        sb.append(", batchContent=").append(batchContent);
        sb.append(", activetedNum=").append(activetedNum);
        sb.append(", salesId=").append(salesId);
        sb.append(", salesName=").append(salesName);
        sb.append(", receiveName=").append(receiveName);
        sb.append(", receiveEmail=").append(receiveEmail);
        sb.append(", receivePhone=").append(receivePhone);
        sb.append(", ossUrl=").append(ossUrl);
        sb.append(", emailContent=").append(emailContent);
        sb.append(", emailTime=").append(emailTime);
        sb.append(", emailStatus=").append(emailStatus);
        sb.append(", smsTime=").append(smsTime);
        sb.append(", smsStatus=").append(smsStatus);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", operatorName=").append(operatorName);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", smsContent=").append(smsContent);
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
        PayShopBatch other = (PayShopBatch) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getBatchNo() == null ? other.getBatchNo() == null : this.getBatchNo().equals(other.getBatchNo()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getScale() == null ? other.getScale() == null : this.getScale().equals(other.getScale()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
            && (this.getExpireMonth() == null ? other.getExpireMonth() == null : this.getExpireMonth().equals(other.getExpireMonth()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBatchContent() == null ? other.getBatchContent() == null : this.getBatchContent().equals(other.getBatchContent()))
            && (this.getActivetedNum() == null ? other.getActivetedNum() == null : this.getActivetedNum().equals(other.getActivetedNum()))
            && (this.getSalesId() == null ? other.getSalesId() == null : this.getSalesId().equals(other.getSalesId()))
            && (this.getSalesName() == null ? other.getSalesName() == null : this.getSalesName().equals(other.getSalesName()))
            && (this.getReceiveName() == null ? other.getReceiveName() == null : this.getReceiveName().equals(other.getReceiveName()))
            && (this.getReceiveEmail() == null ? other.getReceiveEmail() == null : this.getReceiveEmail().equals(other.getReceiveEmail()))
            && (this.getReceivePhone() == null ? other.getReceivePhone() == null : this.getReceivePhone().equals(other.getReceivePhone()))
            && (this.getOssUrl() == null ? other.getOssUrl() == null : this.getOssUrl().equals(other.getOssUrl()))
            && (this.getEmailContent() == null ? other.getEmailContent() == null : this.getEmailContent().equals(other.getEmailContent()))
            && (this.getEmailTime() == null ? other.getEmailTime() == null : this.getEmailTime().equals(other.getEmailTime()))
            && (this.getEmailStatus() == null ? other.getEmailStatus() == null : this.getEmailStatus().equals(other.getEmailStatus()))
            && (this.getSmsTime() == null ? other.getSmsTime() == null : this.getSmsTime().equals(other.getSmsTime()))
            && (this.getSmsStatus() == null ? other.getSmsStatus() == null : this.getSmsStatus().equals(other.getSmsStatus()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getSmsContent() == null ? other.getSmsContent() == null : this.getSmsContent().equals(other.getSmsContent()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getBatchNo() == null) ? 0 : getBatchNo().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getScale() == null) ? 0 : getScale().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getExpireMonth() == null) ? 0 : getExpireMonth().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBatchContent() == null) ? 0 : getBatchContent().hashCode());
        result = prime * result + ((getActivetedNum() == null) ? 0 : getActivetedNum().hashCode());
        result = prime * result + ((getSalesId() == null) ? 0 : getSalesId().hashCode());
        result = prime * result + ((getSalesName() == null) ? 0 : getSalesName().hashCode());
        result = prime * result + ((getReceiveName() == null) ? 0 : getReceiveName().hashCode());
        result = prime * result + ((getReceiveEmail() == null) ? 0 : getReceiveEmail().hashCode());
        result = prime * result + ((getReceivePhone() == null) ? 0 : getReceivePhone().hashCode());
        result = prime * result + ((getOssUrl() == null) ? 0 : getOssUrl().hashCode());
        result = prime * result + ((getEmailContent() == null) ? 0 : getEmailContent().hashCode());
        result = prime * result + ((getEmailTime() == null) ? 0 : getEmailTime().hashCode());
        result = prime * result + ((getEmailStatus() == null) ? 0 : getEmailStatus().hashCode());
        result = prime * result + ((getSmsTime() == null) ? 0 : getSmsTime().hashCode());
        result = prime * result + ((getSmsStatus() == null) ? 0 : getSmsStatus().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getSmsContent() == null) ? 0 : getSmsContent().hashCode());
        return result;
    }
}