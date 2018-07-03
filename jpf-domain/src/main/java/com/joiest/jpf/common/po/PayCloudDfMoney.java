package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayCloudDfMoney implements Serializable {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 代理聚合商户编号 merch_no
     */
    private String agentNo;

    /**
     * 聚合商户企业编号
     */
    private String merchNo;

    /**
     * 企业添加人ID
     */
    private Long uid;

    /**
     * 充值到账对应的交易记录编号
     */
    private String fid;

    /**
     * pay_cloud_company_staff表主键ID
     */
    private Long busstaffid;

    /**
     * 手机号
     */
    private String username;

    /**
     * 发放金额
     */
    private BigDecimal commoney;

    /**
     * 银行卡号
     */
    private String bankno;

    /**
     * 收款人
     */
    private String banknickname;

    /**
     * 银行预留手机号
     */
    private String bankphone;

    /**
     * 开户行
     */
    private String bankname;

    /**
     * 开户行省
     */
    private String bankprovince;

    /**
     * 开户行市
     */
    private String bankcity;

    /**
     * 卡类型 例如：建行 工商
     */
    private String banktype;

    /**
     * 收款人账号属性 0=PRIVATE对私,1=PUBLIC对公
     */
    private Integer bankacctattr;

    /**
     * 发放时间
     */
    private Date addtime;

    /**
     * 姓名
     */
    private String realname;

    /**
     * 打款状态0:未申请打款 1:待打款，2=打款成功 3=打款失败，4=打款中
     */
    private Integer montype;

    /**
     * 打款备注
     */
    private String remark;

    /**
     * 虚拟类型 cat 001 类型
     */
    private Integer vid;

    /**
     * 用户代付状态  0 不可代付 1  已激活
     */
    private Integer isActive;

    /**
     * 操作信息
     */
    private String content;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 操作信息状态 0=申请中
     */
    private Integer operastate;

    /**
     * 第三方交易号
     */
    private String tranno;

    /**
     * 打款单号
     */
    private String orderid;

    /**
     * 重新打款单号
     */
    private String ordernewid;

    /**
     * 应发金额
     */
    private BigDecimal payablemoney;

    /**
     * 代扣金额
     */
    private BigDecimal withholdmoney;

    /**
     * 是否提交报税 1=未申报 2=已申报
     */
    private Integer invostatus;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public Long getBusstaffid() {
        return busstaffid;
    }

    public void setBusstaffid(Long busstaffid) {
        this.busstaffid = busstaffid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public BigDecimal getCommoney() {
        return commoney;
    }

    public void setCommoney(BigDecimal commoney) {
        this.commoney = commoney;
    }

    public String getBankno() {
        return bankno;
    }

    public void setBankno(String bankno) {
        this.bankno = bankno == null ? null : bankno.trim();
    }

    public String getBanknickname() {
        return banknickname;
    }

    public void setBanknickname(String banknickname) {
        this.banknickname = banknickname == null ? null : banknickname.trim();
    }

    public String getBankphone() {
        return bankphone;
    }

    public void setBankphone(String bankphone) {
        this.bankphone = bankphone == null ? null : bankphone.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getBankprovince() {
        return bankprovince;
    }

    public void setBankprovince(String bankprovince) {
        this.bankprovince = bankprovince == null ? null : bankprovince.trim();
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity == null ? null : bankcity.trim();
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype == null ? null : banktype.trim();
    }

    public Integer getBankacctattr() {
        return bankacctattr;
    }

    public void setBankacctattr(Integer bankacctattr) {
        this.bankacctattr = bankacctattr;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Integer getMontype() {
        return montype;
    }

    public void setMontype(Integer montype) {
        this.montype = montype;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getOperastate() {
        return operastate;
    }

    public void setOperastate(Integer operastate) {
        this.operastate = operastate;
    }

    public String getTranno() {
        return tranno;
    }

    public void setTranno(String tranno) {
        this.tranno = tranno == null ? null : tranno.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getOrdernewid() {
        return ordernewid;
    }

    public void setOrdernewid(String ordernewid) {
        this.ordernewid = ordernewid == null ? null : ordernewid.trim();
    }

    public BigDecimal getPayablemoney() {
        return payablemoney;
    }

    public void setPayablemoney(BigDecimal payablemoney) {
        this.payablemoney = payablemoney;
    }

    public BigDecimal getWithholdmoney() {
        return withholdmoney;
    }

    public void setWithholdmoney(BigDecimal withholdmoney) {
        this.withholdmoney = withholdmoney;
    }

    public Integer getInvostatus() {
        return invostatus;
    }

    public void setInvostatus(Integer invostatus) {
        this.invostatus = invostatus;
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
        sb.append(", busstaffid=").append(busstaffid);
        sb.append(", username=").append(username);
        sb.append(", commoney=").append(commoney);
        sb.append(", bankno=").append(bankno);
        sb.append(", banknickname=").append(banknickname);
        sb.append(", bankphone=").append(bankphone);
        sb.append(", bankname=").append(bankname);
        sb.append(", bankprovince=").append(bankprovince);
        sb.append(", bankcity=").append(bankcity);
        sb.append(", banktype=").append(banktype);
        sb.append(", bankacctattr=").append(bankacctattr);
        sb.append(", addtime=").append(addtime);
        sb.append(", realname=").append(realname);
        sb.append(", montype=").append(montype);
        sb.append(", remark=").append(remark);
        sb.append(", vid=").append(vid);
        sb.append(", isActive=").append(isActive);
        sb.append(", content=").append(content);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", operastate=").append(operastate);
        sb.append(", tranno=").append(tranno);
        sb.append(", orderid=").append(orderid);
        sb.append(", ordernewid=").append(ordernewid);
        sb.append(", payablemoney=").append(payablemoney);
        sb.append(", withholdmoney=").append(withholdmoney);
        sb.append(", invostatus=").append(invostatus);
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
        PayCloudDfMoney other = (PayCloudDfMoney) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAgentNo() == null ? other.getAgentNo() == null : this.getAgentNo().equals(other.getAgentNo()))
            && (this.getMerchNo() == null ? other.getMerchNo() == null : this.getMerchNo().equals(other.getMerchNo()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getFid() == null ? other.getFid() == null : this.getFid().equals(other.getFid()))
            && (this.getBusstaffid() == null ? other.getBusstaffid() == null : this.getBusstaffid().equals(other.getBusstaffid()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getCommoney() == null ? other.getCommoney() == null : this.getCommoney().equals(other.getCommoney()))
            && (this.getBankno() == null ? other.getBankno() == null : this.getBankno().equals(other.getBankno()))
            && (this.getBanknickname() == null ? other.getBanknickname() == null : this.getBanknickname().equals(other.getBanknickname()))
            && (this.getBankphone() == null ? other.getBankphone() == null : this.getBankphone().equals(other.getBankphone()))
            && (this.getBankname() == null ? other.getBankname() == null : this.getBankname().equals(other.getBankname()))
            && (this.getBankprovince() == null ? other.getBankprovince() == null : this.getBankprovince().equals(other.getBankprovince()))
            && (this.getBankcity() == null ? other.getBankcity() == null : this.getBankcity().equals(other.getBankcity()))
            && (this.getBanktype() == null ? other.getBanktype() == null : this.getBanktype().equals(other.getBanktype()))
            && (this.getBankacctattr() == null ? other.getBankacctattr() == null : this.getBankacctattr().equals(other.getBankacctattr()))
            && (this.getAddtime() == null ? other.getAddtime() == null : this.getAddtime().equals(other.getAddtime()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getMontype() == null ? other.getMontype() == null : this.getMontype().equals(other.getMontype()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getVid() == null ? other.getVid() == null : this.getVid().equals(other.getVid()))
            && (this.getIsActive() == null ? other.getIsActive() == null : this.getIsActive().equals(other.getIsActive()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()))
            && (this.getOperastate() == null ? other.getOperastate() == null : this.getOperastate().equals(other.getOperastate()))
            && (this.getTranno() == null ? other.getTranno() == null : this.getTranno().equals(other.getTranno()))
            && (this.getOrderid() == null ? other.getOrderid() == null : this.getOrderid().equals(other.getOrderid()))
            && (this.getOrdernewid() == null ? other.getOrdernewid() == null : this.getOrdernewid().equals(other.getOrdernewid()))
            && (this.getPayablemoney() == null ? other.getPayablemoney() == null : this.getPayablemoney().equals(other.getPayablemoney()))
            && (this.getWithholdmoney() == null ? other.getWithholdmoney() == null : this.getWithholdmoney().equals(other.getWithholdmoney()))
            && (this.getInvostatus() == null ? other.getInvostatus() == null : this.getInvostatus().equals(other.getInvostatus()));
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
        result = prime * result + ((getBusstaffid() == null) ? 0 : getBusstaffid().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getCommoney() == null) ? 0 : getCommoney().hashCode());
        result = prime * result + ((getBankno() == null) ? 0 : getBankno().hashCode());
        result = prime * result + ((getBanknickname() == null) ? 0 : getBanknickname().hashCode());
        result = prime * result + ((getBankphone() == null) ? 0 : getBankphone().hashCode());
        result = prime * result + ((getBankname() == null) ? 0 : getBankname().hashCode());
        result = prime * result + ((getBankprovince() == null) ? 0 : getBankprovince().hashCode());
        result = prime * result + ((getBankcity() == null) ? 0 : getBankcity().hashCode());
        result = prime * result + ((getBanktype() == null) ? 0 : getBanktype().hashCode());
        result = prime * result + ((getBankacctattr() == null) ? 0 : getBankacctattr().hashCode());
        result = prime * result + ((getAddtime() == null) ? 0 : getAddtime().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getMontype() == null) ? 0 : getMontype().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getVid() == null) ? 0 : getVid().hashCode());
        result = prime * result + ((getIsActive() == null) ? 0 : getIsActive().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        result = prime * result + ((getOperastate() == null) ? 0 : getOperastate().hashCode());
        result = prime * result + ((getTranno() == null) ? 0 : getTranno().hashCode());
        result = prime * result + ((getOrderid() == null) ? 0 : getOrderid().hashCode());
        result = prime * result + ((getOrdernewid() == null) ? 0 : getOrdernewid().hashCode());
        result = prime * result + ((getPayablemoney() == null) ? 0 : getPayablemoney().hashCode());
        result = prime * result + ((getWithholdmoney() == null) ? 0 : getWithholdmoney().hashCode());
        result = prime * result + ((getInvostatus() == null) ? 0 : getInvostatus().hashCode());
        return result;
    }
}