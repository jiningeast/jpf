package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GetChargeConsumerOrderRequest {
    /**
     * 数据库主键
     */
    private String id;

    /**
     * 订单号PU+毫秒时间戳+6位随机数
     */
    private String orderNo;

    /**
     * 商户id
     */
    private String merchNo;

    /**
     * 订单金额
     */
    private BigDecimal money;

    /**
     * 创建时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 匹配金额
     */
    private BigDecimal matchMoney;

    /**
     * 匹配个数
     */
    private Integer matchRecordsAmount;

    /**
     * 企业的id
     */
    private String companyId;

    /**
     * 状态  0 已下单,1 处理中,2 处理完成
     */
    private Byte status;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo == null ? null : merchNo.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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

    public BigDecimal getMatchMoney() {
        return matchMoney;
    }

    public void setMatchMoney(BigDecimal matchMoney) {
        this.matchMoney = matchMoney;
    }

    public Integer getMatchRecordsAmount() {
        return matchRecordsAmount;
    }

    public void setMatchRecordsAmount(Integer matchRecordsAmount) {
        this.matchRecordsAmount = matchRecordsAmount;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }


}