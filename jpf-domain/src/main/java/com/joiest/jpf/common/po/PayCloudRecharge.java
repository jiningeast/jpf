package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayCloudRecharge implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 交易充值记录ID，唯一值，CZ+10位时间戳+6位随机码
     */
    private String fid;

    /**
     * 需求ID ','隔开
     */
    private String needid;

    /**
     * catpath 集合，','隔开
     */
    private String needcatpath;

    /**
     * 合同编号
     */
    private String pactno;

    /**
     * 代理聚合商户编号 merch_no
     */
    private String agentNo;

    /**
     * 到账聚合商户编号 merch_no
     */
    private String merchNo;

    /**
     * 支付方式 1=线下
     */
    private Byte payway;

    /**
     * 操作人ID
     */
    private Long employeeUid;

    /**
     * 操作人手机号
     */
    private String linkphone;

    /**
     * 操作人邮箱
     */
    private String linkemail;

    /**
     * 状态 ：1:申请中(需求提交，合同待审核first) 2:已审核(已签约，待上传付款凭证) 3:已支付(已上传凭证) 4:已充值开票中(第二次审核); 5:已充值已开票; 6:已发货; 7:已完成(客户收到发票) 0:已取消
     */
    private Byte status;

    /**
     * 充值金额
     */
    private BigDecimal money;

    /**
     * 实际到帐金额
     */
    private BigDecimal realmoney;

    /**
     * 手续费总额
     */
    private BigDecimal feemoney;

    /**
     * 代理手续费
     */
    private BigDecimal agentFeemoney;

    /**
     * 服务平台手续费
     */
    private BigDecimal salesFeemoney;

    /**
     * 代理平台费率，0.00:不收取费率
     */
    private BigDecimal agentRate;

    /**
     * 服务平台费费率
     */
    private BigDecimal salesRate;

    /**
     * 上传凭证
     */
    private String imgurl;

    /**
     * 客服备注
     */
    private String kfremarks;

    /**
     * 删除标记 0=未删 1=删除
     */
    private Byte isDel;

    /**
     * 申请时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 审核时间或取消时间
     */
    private Date shenhetime;

    /**
     * 充值时间
     */
    private Date chargetime;

    /**
     * 需求确认时间
     */
    private Date pacttime;

    /**
     * 需求确认：1默认，2:确认需求
     */
    private Byte pactstatus;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getNeedid() {
        return needid;
    }

    public void setNeedid(String needid) {
        this.needid = needid == null ? null : needid.trim();
    }

    public String getNeedcatpath() {
        return needcatpath;
    }

    public void setNeedcatpath(String needcatpath) {
        this.needcatpath = needcatpath == null ? null : needcatpath.trim();
    }

    public String getPactno() {
        return pactno;
    }

    public void setPactno(String pactno) {
        this.pactno = pactno == null ? null : pactno.trim();
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

    public Byte getPayway() {
        return payway;
    }

    public void setPayway(Byte payway) {
        this.payway = payway;
    }

    public Long getEmployeeUid() {
        return employeeUid;
    }

    public void setEmployeeUid(Long employeeUid) {
        this.employeeUid = employeeUid;
    }

    public String getLinkphone() {
        return linkphone;
    }

    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone == null ? null : linkphone.trim();
    }

    public String getLinkemail() {
        return linkemail;
    }

    public void setLinkemail(String linkemail) {
        this.linkemail = linkemail == null ? null : linkemail.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getRealmoney() {
        return realmoney;
    }

    public void setRealmoney(BigDecimal realmoney) {
        this.realmoney = realmoney;
    }

    public BigDecimal getFeemoney() {
        return feemoney;
    }

    public void setFeemoney(BigDecimal feemoney) {
        this.feemoney = feemoney;
    }

    public BigDecimal getAgentFeemoney() {
        return agentFeemoney;
    }

    public void setAgentFeemoney(BigDecimal agentFeemoney) {
        this.agentFeemoney = agentFeemoney;
    }

    public BigDecimal getSalesFeemoney() {
        return salesFeemoney;
    }

    public void setSalesFeemoney(BigDecimal salesFeemoney) {
        this.salesFeemoney = salesFeemoney;
    }

    public BigDecimal getAgentRate() {
        return agentRate;
    }

    public void setAgentRate(BigDecimal agentRate) {
        this.agentRate = agentRate;
    }

    public BigDecimal getSalesRate() {
        return salesRate;
    }

    public void setSalesRate(BigDecimal salesRate) {
        this.salesRate = salesRate;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getKfremarks() {
        return kfremarks;
    }

    public void setKfremarks(String kfremarks) {
        this.kfremarks = kfremarks == null ? null : kfremarks.trim();
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
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

    public Date getShenhetime() {
        return shenhetime;
    }

    public void setShenhetime(Date shenhetime) {
        this.shenhetime = shenhetime;
    }

    public Date getChargetime() {
        return chargetime;
    }

    public void setChargetime(Date chargetime) {
        this.chargetime = chargetime;
    }

    public Date getPacttime() {
        return pacttime;
    }

    public void setPacttime(Date pacttime) {
        this.pacttime = pacttime;
    }

    public Byte getPactstatus() {
        return pactstatus;
    }

    public void setPactstatus(Byte pactstatus) {
        this.pactstatus = pactstatus;
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
        sb.append(", fid=").append(fid);
        sb.append(", needid=").append(needid);
        sb.append(", needcatpath=").append(needcatpath);
        sb.append(", pactno=").append(pactno);
        sb.append(", agentNo=").append(agentNo);
        sb.append(", merchNo=").append(merchNo);
        sb.append(", payway=").append(payway);
        sb.append(", employeeUid=").append(employeeUid);
        sb.append(", linkphone=").append(linkphone);
        sb.append(", linkemail=").append(linkemail);
        sb.append(", status=").append(status);
        sb.append(", money=").append(money);
        sb.append(", realmoney=").append(realmoney);
        sb.append(", feemoney=").append(feemoney);
        sb.append(", agentFeemoney=").append(agentFeemoney);
        sb.append(", salesFeemoney=").append(salesFeemoney);
        sb.append(", agentRate=").append(agentRate);
        sb.append(", salesRate=").append(salesRate);
        sb.append(", imgurl=").append(imgurl);
        sb.append(", kfremarks=").append(kfremarks);
        sb.append(", isDel=").append(isDel);
        sb.append(", addtime=").append(addtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", shenhetime=").append(shenhetime);
        sb.append(", chargetime=").append(chargetime);
        sb.append(", pacttime=").append(pacttime);
        sb.append(", pactstatus=").append(pactstatus);
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
        PayCloudRecharge other = (PayCloudRecharge) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFid() == null ? other.getFid() == null : this.getFid().equals(other.getFid()))
            && (this.getNeedid() == null ? other.getNeedid() == null : this.getNeedid().equals(other.getNeedid()))
            && (this.getNeedcatpath() == null ? other.getNeedcatpath() == null : this.getNeedcatpath().equals(other.getNeedcatpath()))
            && (this.getPactno() == null ? other.getPactno() == null : this.getPactno().equals(other.getPactno()))
            && (this.getAgentNo() == null ? other.getAgentNo() == null : this.getAgentNo().equals(other.getAgentNo()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getPayway() == null ? other.getPayway() == null : this.getPayway().equals(other.getPayway()))
            && (this.getEmployeeUid() == null ? other.getEmployeeUid() == null : this.getEmployeeUid().equals(other.getEmployeeUid()))
            && (this.getLinkphone() == null ? other.getLinkphone() == null : this.getLinkphone().equals(other.getLinkphone()))
            && (this.getLinkemail() == null ? other.getLinkemail() == null : this.getLinkemail().equals(other.getLinkemail()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getRealmoney() == null ? other.getRealmoney() == null : this.getRealmoney().equals(other.getRealmoney()))
            && (this.getFeemoney() == null ? other.getFeemoney() == null : this.getFeemoney().equals(other.getFeemoney()))
            && (this.getAgentFeemoney() == null ? other.getAgentFeemoney() == null : this.getAgentFeemoney().equals(other.getAgentFeemoney()))
            && (this.getSalesFeemoney() == null ? other.getSalesFeemoney() == null : this.getSalesFeemoney().equals(other.getSalesFeemoney()))
            && (this.getAgentRate() == null ? other.getAgentRate() == null : this.getAgentRate().equals(other.getAgentRate()))
            && (this.getSalesRate() == null ? other.getSalesRate() == null : this.getSalesRate().equals(other.getSalesRate()))
            && (this.getImgurl() == null ? other.getImgurl() == null : this.getImgurl().equals(other.getImgurl()))
            && (this.getKfremarks() == null ? other.getKfremarks() == null : this.getKfremarks().equals(other.getKfremarks()))
            && (this.getIsDel() == null ? other.getIsDel() == null : this.getIsDel().equals(other.getIsDel()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getShenhetime() == null ? other.getShenhetime() == null : this.getShenhetime().equals(other.getShenhetime()))
            && (this.getChargetime() == null ? other.getChargetime() == null : this.getChargetime().equals(other.getChargetime()))
            && (this.getPacttime() == null ? other.getPacttime() == null : this.getPacttime().equals(other.getPacttime()))
            && (this.getPactstatus() == null ? other.getPactstatus() == null : this.getPactstatus().equals(other.getPactstatus()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFid() == null) ? 0 : getFid().hashCode());
        result = prime * result + ((getNeedid() == null) ? 0 : getNeedid().hashCode());
        result = prime * result + ((getNeedcatpath() == null) ? 0 : getNeedcatpath().hashCode());
        result = prime * result + ((getPactno() == null) ? 0 : getPactno().hashCode());
        result = prime * result + ((getAgentNo() == null) ? 0 : getAgentNo().hashCode());
        result = prime * result + ((getMerchNo() == null) ? 0 : getMerchNo().hashCode());
        result = prime * result + ((getPayway() == null) ? 0 : getPayway().hashCode());
        result = prime * result + ((getEmployeeUid() == null) ? 0 : getEmployeeUid().hashCode());
        result = prime * result + ((getLinkphone() == null) ? 0 : getLinkphone().hashCode());
        result = prime * result + ((getLinkemail() == null) ? 0 : getLinkemail().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getRealmoney() == null) ? 0 : getRealmoney().hashCode());
        result = prime * result + ((getFeemoney() == null) ? 0 : getFeemoney().hashCode());
        result = prime * result + ((getAgentFeemoney() == null) ? 0 : getAgentFeemoney().hashCode());
        result = prime * result + ((getSalesFeemoney() == null) ? 0 : getSalesFeemoney().hashCode());
        result = prime * result + ((getAgentRate() == null) ? 0 : getAgentRate().hashCode());
        result = prime * result + ((getSalesRate() == null) ? 0 : getSalesRate().hashCode());
        result = prime * result + ((getImgurl() == null) ? 0 : getImgurl().hashCode());
        result = prime * result + ((getKfremarks() == null) ? 0 : getKfremarks().hashCode());
        result = prime * result + ((getIsDel() == null) ? 0 : getIsDel().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getShenhetime() == null) ? 0 : getShenhetime().hashCode());
        result = prime * result + ((getChargetime() == null) ? 0 : getChargetime().hashCode());
        result = prime * result + ((getPacttime() == null) ? 0 : getPacttime().hashCode());
        result = prime * result + ((getPactstatus() == null) ? 0 : getPactstatus().hashCode());
        return result;
    }
}