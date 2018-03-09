package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.util.Date;

public class SysParaInfo implements Serializable {
    /**
     * 系统参数Id
     */
    private Long paraId;

    /**
     * 系统参数类别编码
     */
    private String paraCode;

    /**
     * 系统参数名称
     */
    private String paraName;

    /**
     * 系统参数值
     */
    private String paraValue;

    /**
     * 系统参数描述
     */
    private String paraDesc;

    /**
     * 启用标识
     */
    private Boolean isUsed;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getParaId() {
        return paraId;
    }

    public void setParaId(Long paraId) {
        this.paraId = paraId;
    }

    public String getParaCode() {
        return paraCode;
    }

    public void setParaCode(String paraCode) {
        this.paraCode = paraCode == null ? null : paraCode.trim();
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName == null ? null : paraName.trim();
    }

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue == null ? null : paraValue.trim();
    }

    public String getParaDesc() {
        return paraDesc;
    }

    public void setParaDesc(String paraDesc) {
        this.paraDesc = paraDesc == null ? null : paraDesc.trim();
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", paraId=").append(paraId);
        sb.append(", paraCode=").append(paraCode);
        sb.append(", paraName=").append(paraName);
        sb.append(", paraValue=").append(paraValue);
        sb.append(", paraDesc=").append(paraDesc);
        sb.append(", isUsed=").append(isUsed);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
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
        SysParaInfo other = (SysParaInfo) that;
        return (this.getParaId() == null ? other.getParaId() == null : this.getParaId().equals(other.getParaId()))
            && (this.getParaCode() == null ? other.getParaCode() == null : this.getParaCode().equals(other.getParaCode()))
            && (this.getParaName() == null ? other.getParaName() == null : this.getParaName().equals(other.getParaName()))
            && (this.getParaValue() == null ? other.getParaValue() == null : this.getParaValue().equals(other.getParaValue()))
            && (this.getParaDesc() == null ? other.getParaDesc() == null : this.getParaDesc().equals(other.getParaDesc()))
            && (this.getIsUsed() == null ? other.getIsUsed() == null : this.getIsUsed().equals(other.getIsUsed()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getParaId() == null) ? 0 : getParaId().hashCode());
        result = prime * result + ((getParaCode() == null) ? 0 : getParaCode().hashCode());
        result = prime * result + ((getParaName() == null) ? 0 : getParaName().hashCode());
        result = prime * result + ((getParaValue() == null) ? 0 : getParaValue().hashCode());
        result = prime * result + ((getParaDesc() == null) ? 0 : getParaDesc().hashCode());
        result = prime * result + ((getIsUsed() == null) ? 0 : getIsUsed().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}