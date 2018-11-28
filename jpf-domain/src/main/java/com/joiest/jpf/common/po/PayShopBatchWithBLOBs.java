package com.joiest.jpf.common.po;

import java.io.Serializable;

public class PayShopBatchWithBLOBs extends PayShopBatch implements Serializable {
    /**
     * 短信内容
     */
    private String smsContent;

    /**
     * 群发给个人时运营上传的excel文件地址
     */
    private String excelUrl;

    private static final long serialVersionUID = 1L;

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent == null ? null : smsContent.trim();
    }

    public String getExcelUrl() {
        return excelUrl;
    }

    public void setExcelUrl(String excelUrl) {
        this.excelUrl = excelUrl == null ? null : excelUrl.trim();
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
        sb.append(", smsContent=").append(smsContent);
        sb.append(", excelUrl=").append(excelUrl);
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
        PayShopBatchWithBLOBs other = (PayShopBatchWithBLOBs) that;
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
            && (this.getZipPassword() == null ? other.getZipPassword() == null : this.getZipPassword().equals(other.getZipPassword()))
            && (this.getEmailContent() == null ? other.getEmailContent() == null : this.getEmailContent().equals(other.getEmailContent()))
            && (this.getEmailTime() == null ? other.getEmailTime() == null : this.getEmailTime().equals(other.getEmailTime()))
            && (this.getEmailStatus() == null ? other.getEmailStatus() == null : this.getEmailStatus().equals(other.getEmailStatus()))
            && (this.getSmsTime() == null ? other.getSmsTime() == null : this.getSmsTime().equals(other.getSmsTime()))
            && (this.getSmsStatus() == null ? other.getSmsStatus() == null : this.getSmsStatus().equals(other.getSmsStatus()))
            && (this.getOperatorId() == null ? other.getOperatorId() == null : this.getOperatorId().equals(other.getOperatorId()))
            && (this.getOperatorName() == null ? other.getOperatorName() == null : this.getOperatorName().equals(other.getOperatorName()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getSendType() == null ? other.getSendType() == null : this.getSendType().equals(other.getSendType()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getTransferRate() == null ? other.getTransferRate() == null : this.getTransferRate().equals(other.getTransferRate()))
            && (this.getCompanyChargeId() == null ? other.getCompanyChargeId() == null : this.getCompanyChargeId().equals(other.getCompanyChargeId()))
            && (this.getSmsContent() == null ? other.getSmsContent() == null : this.getSmsContent().equals(other.getSmsContent()))
            && (this.getExcelUrl() == null ? other.getExcelUrl() == null : this.getExcelUrl().equals(other.getExcelUrl()));
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
        result = prime * result + ((getZipPassword() == null) ? 0 : getZipPassword().hashCode());
        result = prime * result + ((getEmailContent() == null) ? 0 : getEmailContent().hashCode());
        result = prime * result + ((getEmailTime() == null) ? 0 : getEmailTime().hashCode());
        result = prime * result + ((getEmailStatus() == null) ? 0 : getEmailStatus().hashCode());
        result = prime * result + ((getSmsTime() == null) ? 0 : getSmsTime().hashCode());
        result = prime * result + ((getSmsStatus() == null) ? 0 : getSmsStatus().hashCode());
        result = prime * result + ((getOperatorId() == null) ? 0 : getOperatorId().hashCode());
        result = prime * result + ((getOperatorName() == null) ? 0 : getOperatorName().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getSendType() == null) ? 0 : getSendType().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getTransferRate() == null) ? 0 : getTransferRate().hashCode());
        result = prime * result + ((getCompanyChargeId() == null) ? 0 : getCompanyChargeId().hashCode());
        result = prime * result + ((getSmsContent() == null) ? 0 : getSmsContent().hashCode());
        result = prime * result + ((getExcelUrl() == null) ? 0 : getExcelUrl().hashCode());
        return result;
    }
}