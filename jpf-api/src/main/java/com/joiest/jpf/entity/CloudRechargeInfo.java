package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudRechargeInfo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 交易充值记录ID，唯一值，CZ+10位时间戳+6位随机码
     */
    private String fid;

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
    private String employeeUid;

    /**
     * 操作人手机号
     */
    private String linkphone;

    /**
     * 操作人邮箱
     */
    private String linkemail;

    /**
     * 状态 1:申请中(待审核first) 2:已审核(待上传付款凭证) 3:已支付(已上传凭证) 4:已充值开票中(第二次审核); 5:已充值已开票; 6:已发货; 7:已完成(客户收到发票) 0:已取消
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

    private String status_cn;

    private String pactno;

    private String needid;

    private String needcatpath;

    private Date pacttime;

    //===========企业信息 begin ===========

    /**
     * 自增ID
     */
    private String companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 商户别名
     */
    private String merchName;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    //===========企业信息 end ===========
    //===========发票科目信息 begin ===========

    /**
     *
     */
    private String catid;

    /**
     *
     */
    private String pid;

    /**
     *
     */
    private String catpath;

    /**
     *
     */
    private String cat;

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCatpath() {
        return catpath;
    }

    public void setCatpath(String catpath) {
        this.catpath = catpath;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    //===========发票科目信息 end ===========

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

    public String getEmployeeUid() {
        return employeeUid;
    }

    public void setEmployeeUid(String employeeUid) {
        this.employeeUid = employeeUid == null ? null : employeeUid.trim();
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

    public String getStatus_cn() {
        return status_cn;
    }

    public void setStatus_cn(String status_cn) {
        this.status_cn = status_cn;
    }

    public Date getPacttime() {
        return pacttime;
    }

    public void setPacttime(Date pacttime) {
        this.pacttime = pacttime;
    }

    public String getPactno() {
        return pactno;
    }

    public void setPactno(String pactno) {
        this.pactno = pactno == null ? null : pactno.trim();
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
        this.needcatpath = needcatpath;
    }
}
