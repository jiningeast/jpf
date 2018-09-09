package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayCloudCompanyMoney implements Serializable {
    /**
     * 自增ID
     */
    private String id;

    /**
     * 代理聚合商户编号 merch_no
     */
    private String agentNo;

    /**
     * 聚合商户编号
     */
    private String merchNo;

    /**
     * 企业ID
     */
    private String uid;

    /**
     * excel中的合同编号
     */
    private String fid;

    /**
     * 虚拟类型 cat 008 类型
     */
    private Byte vid;

    /**
     * 备注信息
     */
    private String intro;

    /**
     * 发放个状态 -1删除 0=待锁定 1=待付款 2=处理完成,3=处理完成(部分失败),4=处理失败
     */
    private Byte montype;

    /**
     * 0=未处理 1=部分失败 2=全部失败 3=全部成功
     */
    private Byte batchstatus;

    /**
     * 用户批次号
     */
    private String batchno;

    /**
     * 批次总笔数
     */
    private String batchitems;

    /**
     * 批次总金额
     */
    private BigDecimal batchallmoney;

    /**
     * 批次成功笔数
     */
    private String batchdealitems;

    /**
     * 批次成功总金额
     */
    private BigDecimal batchdealmoney;

    /**
     * 批次失败笔数
     */
    private String batchfailitems;

    /**
     * 批次失败总金额
     */
    private BigDecimal batchfailmoney;

    /**
     * 发放总金额
     */
    private BigDecimal commoney;

    /**
     * 服务费率
     */
    private BigDecimal feeRate;

    /**
     * 服务费金额
     */
    private BigDecimal feemoney;

    /**
     * 毛利金额
     */
    private BigDecimal profitmoney;

    /**
     * 应发金额
     */
    private BigDecimal shouldMoney;

    /**
     * 个人所得税税率
     */
    private BigDecimal individualTax;

    /**
     * 个人所得税金额
     */
    private BigDecimal individualMoney;

    /**
     * 印花税
     */
    private BigDecimal yinhuaTax;

    /**
     * 印花税金额
     */
    private BigDecimal yinhuaMoney;

    /**
     * 增值税税率
     */
    private BigDecimal taxRate;

    /**
     * 增值税金额
     */
    private BigDecimal taxmoney;

    /**
     * 增值税税率
     */
    private BigDecimal taxmoreTax;

    /**
     * 增值税附加金额
     */
    private BigDecimal taxmoremoney;

    /**
     * 发放时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public Byte getVid() {
        return vid;
    }

    public void setVid(Byte vid) {
        this.vid = vid;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Byte getMontype() {
        return montype;
    }

    public void setMontype(Byte montype) {
        this.montype = montype;
    }

    public Byte getBatchstatus() {
        return batchstatus;
    }

    public void setBatchstatus(Byte batchstatus) {
        this.batchstatus = batchstatus;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno == null ? null : batchno.trim();
    }

    public String getBatchitems() {
        return batchitems;
    }

    public void setBatchitems(String batchitems) {
        this.batchitems = batchitems == null ? null : batchitems.trim();
    }

    public BigDecimal getBatchallmoney() {
        return batchallmoney;
    }

    public void setBatchallmoney(BigDecimal batchallmoney) {
        this.batchallmoney = batchallmoney;
    }

    public String getBatchdealitems() {
        return batchdealitems;
    }

    public void setBatchdealitems(String batchdealitems) {
        this.batchdealitems = batchdealitems == null ? null : batchdealitems.trim();
    }

    public BigDecimal getBatchdealmoney() {
        return batchdealmoney;
    }

    public void setBatchdealmoney(BigDecimal batchdealmoney) {
        this.batchdealmoney = batchdealmoney;
    }

    public String getBatchfailitems() {
        return batchfailitems;
    }

    public void setBatchfailitems(String batchfailitems) {
        this.batchfailitems = batchfailitems == null ? null : batchfailitems.trim();
    }

    public BigDecimal getBatchfailmoney() {
        return batchfailmoney;
    }

    public void setBatchfailmoney(BigDecimal batchfailmoney) {
        this.batchfailmoney = batchfailmoney;
    }

    public BigDecimal getCommoney() {
        return commoney;
    }

    public void setCommoney(BigDecimal commoney) {
        this.commoney = commoney;
    }

    public BigDecimal getFeeRate() {
        return feeRate;
    }

    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }

    public BigDecimal getFeemoney() {
        return feemoney;
    }

    public void setFeemoney(BigDecimal feemoney) {
        this.feemoney = feemoney;
    }

    public BigDecimal getProfitmoney() {
        return profitmoney;
    }

    public void setProfitmoney(BigDecimal profitmoney) {
        this.profitmoney = profitmoney;
    }

    public BigDecimal getShouldMoney() {
        return shouldMoney;
    }

    public void setShouldMoney(BigDecimal shouldMoney) {
        this.shouldMoney = shouldMoney;
    }

    public BigDecimal getIndividualTax() {
        return individualTax;
    }

    public void setIndividualTax(BigDecimal individualTax) {
        this.individualTax = individualTax;
    }

    public BigDecimal getIndividualMoney() {
        return individualMoney;
    }

    public void setIndividualMoney(BigDecimal individualMoney) {
        this.individualMoney = individualMoney;
    }

    public BigDecimal getYinhuaTax() {
        return yinhuaTax;
    }

    public void setYinhuaTax(BigDecimal yinhuaTax) {
        this.yinhuaTax = yinhuaTax;
    }

    public BigDecimal getYinhuaMoney() {
        return yinhuaMoney;
    }

    public void setYinhuaMoney(BigDecimal yinhuaMoney) {
        this.yinhuaMoney = yinhuaMoney;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxmoney() {
        return taxmoney;
    }

    public void setTaxmoney(BigDecimal taxmoney) {
        this.taxmoney = taxmoney;
    }

    public BigDecimal getTaxmoreTax() {
        return taxmoreTax;
    }

    public void setTaxmoreTax(BigDecimal taxmoreTax) {
        this.taxmoreTax = taxmoreTax;
    }

    public BigDecimal getTaxmoremoney() {
        return taxmoremoney;
    }

    public void setTaxmoremoney(BigDecimal taxmoremoney) {
        this.taxmoremoney = taxmoremoney;
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
        sb.append(", agentNo=").append(agentNo);
        sb.append(", merchNo=").append(merchNo);
        sb.append(", uid=").append(uid);
        sb.append(", fid=").append(fid);
        sb.append(", vid=").append(vid);
        sb.append(", intro=").append(intro);
        sb.append(", montype=").append(montype);
        sb.append(", batchstatus=").append(batchstatus);
        sb.append(", batchno=").append(batchno);
        sb.append(", batchitems=").append(batchitems);
        sb.append(", batchallmoney=").append(batchallmoney);
        sb.append(", batchdealitems=").append(batchdealitems);
        sb.append(", batchdealmoney=").append(batchdealmoney);
        sb.append(", batchfailitems=").append(batchfailitems);
        sb.append(", batchfailmoney=").append(batchfailmoney);
        sb.append(", commoney=").append(commoney);
        sb.append(", feeRate=").append(feeRate);
        sb.append(", feemoney=").append(feemoney);
        sb.append(", profitmoney=").append(profitmoney);
        sb.append(", shouldMoney=").append(shouldMoney);
        sb.append(", individualTax=").append(individualTax);
        sb.append(", individualMoney=").append(individualMoney);
        sb.append(", yinhuaTax=").append(yinhuaTax);
        sb.append(", yinhuaMoney=").append(yinhuaMoney);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", taxmoney=").append(taxmoney);
        sb.append(", taxmoreTax=").append(taxmoreTax);
        sb.append(", taxmoremoney=").append(taxmoremoney);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
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
        PayCloudCompanyMoney other = (PayCloudCompanyMoney) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAgentNo() == null ? other.getAgentNo() == null : this.getAgentNo().equals(other.getAgentNo()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getFid() == null ? other.getFid() == null : this.getFid().equals(other.getFid()))
            && (this.getVid() == null ? other.getVid() == null : this.getVid().equals(other.getVid()))
            && (this.getIntro() == null ? other.getIntro() == null : this.getIntro().equals(other.getIntro()))
            && (this.getMontype() == null ? other.getMontype() == null : this.getMontype().equals(other.getMontype()))
            && (this.getBatchstatus() == null ? other.getBatchstatus() == null : this.getBatchstatus().equals(other.getBatchstatus()))
            && (this.getBatchno() == null ? other.getBatchno() == null : this.getBatchno().equals(other.getBatchno()))
            && (this.getBatchitems() == null ? other.getBatchitems() == null : this.getBatchitems().equals(other.getBatchitems()))
            && (this.getBatchallmoney() == null ? other.getBatchallmoney() == null : this.getBatchallmoney().equals(other.getBatchallmoney()))
            && (this.getBatchdealitems() == null ? other.getBatchdealitems() == null : this.getBatchdealitems().equals(other.getBatchdealitems()))
            && (this.getBatchdealmoney() == null ? other.getBatchdealmoney() == null : this.getBatchdealmoney().equals(other.getBatchdealmoney()))
            && (this.getBatchfailitems() == null ? other.getBatchfailitems() == null : this.getBatchfailitems().equals(other.getBatchfailitems()))
            && (this.getBatchfailmoney() == null ? other.getBatchfailmoney() == null : this.getBatchfailmoney().equals(other.getBatchfailmoney()))
            && (this.getCommoney() == null ? other.getCommoney() == null : this.getCommoney().equals(other.getCommoney()))
            && (this.getFeeRate() == null ? other.getFeeRate() == null : this.getFeeRate().equals(other.getFeeRate()))
            && (this.getFeemoney() == null ? other.getFeemoney() == null : this.getFeemoney().equals(other.getFeemoney()))
            && (this.getProfitmoney() == null ? other.getProfitmoney() == null : this.getProfitmoney().equals(other.getProfitmoney()))
            && (this.getShouldMoney() == null ? other.getShouldMoney() == null : this.getShouldMoney().equals(other.getShouldMoney()))
            && (this.getIndividualTax() == null ? other.getIndividualTax() == null : this.getIndividualTax().equals(other.getIndividualTax()))
            && (this.getIndividualMoney() == null ? other.getIndividualMoney() == null : this.getIndividualMoney().equals(other.getIndividualMoney()))
            && (this.getYinhuaTax() == null ? other.getYinhuaTax() == null : this.getYinhuaTax().equals(other.getYinhuaTax()))
            && (this.getYinhuaMoney() == null ? other.getYinhuaMoney() == null : this.getYinhuaMoney().equals(other.getYinhuaMoney()))
            && (this.getTaxRate() == null ? other.getTaxRate() == null : this.getTaxRate().equals(other.getTaxRate()))
            && (this.getTaxmoney() == null ? other.getTaxmoney() == null : this.getTaxmoney().equals(other.getTaxmoney()))
            && (this.getTaxmoreTax() == null ? other.getTaxmoreTax() == null : this.getTaxmoreTax().equals(other.getTaxmoreTax()))
            && (this.getTaxmoremoney() == null ? other.getTaxmoremoney() == null : this.getTaxmoremoney().equals(other.getTaxmoremoney()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAgentNo() == null) ? 0 : getAgentNo().hashCode());
        result = prime * result + ((getMerchNo() == null) ? 0 : getMerchNo().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getFid() == null) ? 0 : getFid().hashCode());
        result = prime * result + ((getVid() == null) ? 0 : getVid().hashCode());
        result = prime * result + ((getIntro() == null) ? 0 : getIntro().hashCode());
        result = prime * result + ((getMontype() == null) ? 0 : getMontype().hashCode());
        result = prime * result + ((getBatchstatus() == null) ? 0 : getBatchstatus().hashCode());
        result = prime * result + ((getBatchno() == null) ? 0 : getBatchno().hashCode());
        result = prime * result + ((getBatchitems() == null) ? 0 : getBatchitems().hashCode());
        result = prime * result + ((getBatchallmoney() == null) ? 0 : getBatchallmoney().hashCode());
        result = prime * result + ((getBatchdealitems() == null) ? 0 : getBatchdealitems().hashCode());
        result = prime * result + ((getBatchdealmoney() == null) ? 0 : getBatchdealmoney().hashCode());
        result = prime * result + ((getBatchfailitems() == null) ? 0 : getBatchfailitems().hashCode());
        result = prime * result + ((getBatchfailmoney() == null) ? 0 : getBatchfailmoney().hashCode());
        result = prime * result + ((getCommoney() == null) ? 0 : getCommoney().hashCode());
        result = prime * result + ((getFeeRate() == null) ? 0 : getFeeRate().hashCode());
        result = prime * result + ((getFeemoney() == null) ? 0 : getFeemoney().hashCode());
        result = prime * result + ((getProfitmoney() == null) ? 0 : getProfitmoney().hashCode());
        result = prime * result + ((getShouldMoney() == null) ? 0 : getShouldMoney().hashCode());
        result = prime * result + ((getIndividualTax() == null) ? 0 : getIndividualTax().hashCode());
        result = prime * result + ((getIndividualMoney() == null) ? 0 : getIndividualMoney().hashCode());
        result = prime * result + ((getYinhuaTax() == null) ? 0 : getYinhuaTax().hashCode());
        result = prime * result + ((getYinhuaMoney() == null) ? 0 : getYinhuaMoney().hashCode());
        result = prime * result + ((getTaxRate() == null) ? 0 : getTaxRate().hashCode());
        result = prime * result + ((getTaxmoney() == null) ? 0 : getTaxmoney().hashCode());
        result = prime * result + ((getTaxmoreTax() == null) ? 0 : getTaxmoreTax().hashCode());
        result = prime * result + ((getTaxmoremoney() == null) ? 0 : getTaxmoremoney().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }
}