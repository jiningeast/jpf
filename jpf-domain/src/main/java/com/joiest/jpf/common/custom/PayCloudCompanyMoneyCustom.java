package com.joiest.jpf.common.custom;

import java.math.BigDecimal;
import java.util.Date;

public class PayCloudCompanyMoneyCustom {
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
     * 发放总金额
     */
    private BigDecimal commoney;

    /**
     * 发放时间
     */
    private Date addtime;

    /**
     * 企业添加人ID
     */
    private String uid;

    /**
     * 充值到账对应的交易记录编号
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
     * 更新时间
     */
    private Date updatetime;

    /**
     * 服务费金额
     */
    private BigDecimal feemoney;

    /**
     * 增值税金额
     */
    private BigDecimal taxmoney;

    /**
     * 增值税附加金额
     */
    private BigDecimal taxmoremoney;

    /**
     * 毛利金额
     */
    private BigDecimal profitmoney;

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

    public BigDecimal getCommoney() {
        return commoney;
    }

    public void setCommoney(BigDecimal commoney) {
        this.commoney = commoney;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
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

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public BigDecimal getFeemoney() {
        return feemoney;
    }

    public void setFeemoney(BigDecimal feemoney) {
        this.feemoney = feemoney;
    }

    public BigDecimal getTaxmoney() {
        return taxmoney;
    }

    public void setTaxmoney(BigDecimal taxmoney) {
        this.taxmoney = taxmoney;
    }

    public BigDecimal getTaxmoremoney() {
        return taxmoremoney;
    }

    public void setTaxmoremoney(BigDecimal taxmoremoney) {
        this.taxmoremoney = taxmoremoney;
    }

    public BigDecimal getProfitmoney() {
        return profitmoney;
    }

    public void setProfitmoney(BigDecimal profitmoney) {
        this.profitmoney = profitmoney;
    }
}