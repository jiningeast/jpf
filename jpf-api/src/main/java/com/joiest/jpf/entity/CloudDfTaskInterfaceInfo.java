package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudDfTaskInterfaceInfo {
    /**
     *
     */
    private Long id;

    /**
     * 打款批次id
     */
    private String batchid;

    /**
     * 外来批次编号
     */
    private String requestBatchno;

    /**
     * 单独打款id df_money.id
     */
    private String requestDfId;

    /**
     * 外来请求信息
     */
    private String requestStr;

    /**
     * 订单总数
     */
    private Integer orderCount;

    /**
     * 订单总金额
     */
    private BigDecimal orderMoney;

    /**
     * 已打款数量
     */
    private Integer alreadyCount;

    /**
     * 已打款金额
     */
    private BigDecimal alreadyMoney;

    /**
     * 失败订单数量
     */
    private Integer failCount;

    /**
     * 失败金额
     */
    private BigDecimal failMoney;

    /**
     * 数据写入状态 -1异常 0未写入 1处理中 2处理完成
     */
    private Integer insertStatus;

    /**
     * 状态 0未处理 1处理中 2全部完成 3部分完成
     */
    private Integer status;

    /**
     * 添加时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchid() {
        return batchid;
    }

    public void setBatchid(String batchid) {
        this.batchid = batchid;
    }

    public String getRequestBatchno() {
        return requestBatchno;
    }

    public void setRequestBatchno(String requestBatchno) {
        this.requestBatchno = requestBatchno;
    }

    public String getRequestDfId() {
        return requestDfId;
    }

    public void setRequestDfId(String requestDfId) {
        this.requestDfId = requestDfId;
    }

    public String getRequestStr() {
        return requestStr;
    }

    public void setRequestStr(String requestStr) {
        this.requestStr = requestStr;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getAlreadyCount() {
        return alreadyCount;
    }

    public void setAlreadyCount(Integer alreadyCount) {
        this.alreadyCount = alreadyCount;
    }

    public BigDecimal getAlreadyMoney() {
        return alreadyMoney;
    }

    public void setAlreadyMoney(BigDecimal alreadyMoney) {
        this.alreadyMoney = alreadyMoney;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public BigDecimal getFailMoney() {
        return failMoney;
    }

    public void setFailMoney(BigDecimal failMoney) {
        this.failMoney = failMoney;
    }

    public Integer getInsertStatus() {
        return insertStatus;
    }

    public void setInsertStatus(Integer insertStatus) {
        this.insertStatus = insertStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
