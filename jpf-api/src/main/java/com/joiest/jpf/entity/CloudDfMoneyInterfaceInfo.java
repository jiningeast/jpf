package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudDfMoneyInterfaceInfo {

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
     * 公司发放事件ID
     */
    private Long cmid;

    /**
     * 1:福利发放，2:优惠券
     */
    private Integer stftype;

    /**
     * 优惠券类别
     */
    private Long couponid;

    /**
     * 优惠券名称
     */
    private String couponname;

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


    //月份的记录总数
    private Integer count;

    //月份的总金额
    private BigDecimal totalMoney;

    //月份
    private String datemonth;

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
        this.agentNo = agentNo;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
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
        this.fid = fid;
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
        this.username = username;
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
        this.bankno = bankno;
    }

    public String getBanknickname() {
        return banknickname;
    }

    public void setBanknickname(String banknickname) {
        this.banknickname = banknickname;
    }

    public String getBankphone() {
        return bankphone;
    }

    public void setBankphone(String bankphone) {
        this.bankphone = bankphone;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankprovince() {
        return bankprovince;
    }

    public void setBankprovince(String bankprovince) {
        this.bankprovince = bankprovince;
    }

    public String getBankcity() {
        return bankcity;
    }

    public void setBankcity(String bankcity) {
        this.bankcity = bankcity;
    }

    public String getBanktype() {
        return banktype;
    }

    public void setBanktype(String banktype) {
        this.banktype = banktype;
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
        this.realname = realname;
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
        this.remark = remark;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Long getCmid() {
        return cmid;
    }

    public void setCmid(Long cmid) {
        this.cmid = cmid;
    }

    public Integer getStftype() {
        return stftype;
    }

    public void setStftype(Integer stftype) {
        this.stftype = stftype;
    }

    public Long getCouponid() {
        return couponid;
    }

    public void setCouponid(Long couponid) {
        this.couponid = couponid;
    }

    public String getCouponname() {
        return couponname;
    }

    public void setCouponname(String couponname) {
        this.couponname = couponname;
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
        this.content = content;
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
        this.tranno = tranno;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrdernewid() {
        return ordernewid;
    }

    public void setOrdernewid(String ordernewid) {
        this.ordernewid = ordernewid;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDatemonth() {
        return datemonth;
    }

    public void setDatemonth(String datemonth) {
        this.datemonth = datemonth;
    }
}
