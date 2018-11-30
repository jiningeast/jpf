package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GetShopCompanyChargeRequest {
    /**
     * 主键id
     */
    private String id;

    /**
     * 公司id
     */
    private String companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 运营操作人id
     */
    private String operatorId;

    /**
     * 运营操作人姓名
     */
    private String operatorName;

    /**
     * 付款凭证图片地址（阿里云）
     */
    private String imgUrl;

    /**
     * 费率
     */
    private BigDecimal rate;

    /**
     * 合同金额
     */
    private BigDecimal contractMoney;


    /**
     * 充值金额
     */
    private BigDecimal money;

    /**
     * 财务审核操作者id
     */
    private String checkOperatorId;

    /**
     * 财务审核操作者姓名
     */
    private String checkOperatorName;

    /**
     * 审核时间
     */
    private Date checkTime;

    /**
     * 充值状态 -1=运营取消 0=新增，待审核 1=审核通过，已充值 2=审核驳回
     */
    private Byte status;

    /**
     * 状态名称
     */
    private String statusCn;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 页码
     */
    private Long page;

    /**
     * 总条数
     */
    private Long rows;

    /**
     * 开始时间
     */
    private String addtimeStart;

    /**
     * 结束时间
     */
    private String addtimeEnd;

    /**
     * 合同号
     */
    private String contractNo;

    /**
     * 合同到期时间
     */
    private String duetime;

    /**
     * 消费转让率
     */
    private BigDecimal transferRate;

    /**
     * 欣券金额
     */
    private BigDecimal couponMoney;

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getDuetime() {
        return duetime;
    }

    public void setDuetime(String duetime) {
        this.duetime = duetime;
    }

    public BigDecimal getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(BigDecimal transferRate) {
        this.transferRate = transferRate;
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public BigDecimal getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(BigDecimal serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    /**
     * 服务金额
     */



    private BigDecimal serviceMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getCheckOperatorId() {
        return checkOperatorId;
    }

    public void setCheckOperatorId(String checkOperatorId) {
        this.checkOperatorId = checkOperatorId;
    }

    public String getCheckOperatorName() {
        return checkOperatorName;
    }

    public void setCheckOperatorName(String checkOperatorName) {
        this.checkOperatorName = checkOperatorName;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public String getAddtimeStart() {
        return addtimeStart;
    }

    public void setAddtimeStart(String addtimeStart) {
        this.addtimeStart = addtimeStart;
    }

    public String getAddtimeEnd() {
        return addtimeEnd;
    }

    public void setAddtimeEnd(String addtimeEnd) {
        this.addtimeEnd = addtimeEnd;
    }

    public String getStatusCn() {
        return statusCn;
    }

    public void setStatusCn(String statusCn) {
        this.statusCn = statusCn;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(BigDecimal contractMoney) {
        this.contractMoney = contractMoney;
    }
}