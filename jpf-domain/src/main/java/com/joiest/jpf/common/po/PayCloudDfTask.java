package com.joiest.jpf.common.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PayCloudDfTask implements Serializable {
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
     * 备注
     */
    private String remarks;

    /**
     * 添加时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;

    private static final long serialVersionUID = 1L;

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
        this.batchid = batchid == null ? null : batchid.trim();
    }

    public String getRequestBatchno() {
        return requestBatchno;
    }

    public void setRequestBatchno(String requestBatchno) {
        this.requestBatchno = requestBatchno == null ? null : requestBatchno.trim();
    }

    public String getRequestDfId() {
        return requestDfId;
    }

    public void setRequestDfId(String requestDfId) {
        this.requestDfId = requestDfId == null ? null : requestDfId.trim();
    }

    public String getRequestStr() {
        return requestStr;
    }

    public void setRequestStr(String requestStr) {
        this.requestStr = requestStr == null ? null : requestStr.trim();
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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
        sb.append(", batchid=").append(batchid);
        sb.append(", requestBatchno=").append(requestBatchno);
        sb.append(", requestDfId=").append(requestDfId);
        sb.append(", requestStr=").append(requestStr);
        sb.append(", orderCount=").append(orderCount);
        sb.append(", orderMoney=").append(orderMoney);
        sb.append(", alreadyCount=").append(alreadyCount);
        sb.append(", alreadyMoney=").append(alreadyMoney);
        sb.append(", failCount=").append(failCount);
        sb.append(", failMoney=").append(failMoney);
        sb.append(", insertStatus=").append(insertStatus);
        sb.append(", status=").append(status);
        sb.append(", remarks=").append(remarks);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
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
        PayCloudDfTask other = (PayCloudDfTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBatchid() == null ? other.getBatchid() == null : this.getBatchid().equals(other.getBatchid()))
            && (this.getRequestBatchno() == null ? other.getRequestBatchno() == null : this.getRequestBatchno().equals(other.getRequestBatchno()))
            && (this.getRequestDfId() == null ? other.getRequestDfId() == null : this.getRequestDfId().equals(other.getRequestDfId()))
            && (this.getRequestStr() == null ? other.getRequestStr() == null : this.getRequestStr().equals(other.getRequestStr()))
            && (this.getOrderCount() == null ? other.getOrderCount() == null : this.getOrderCount().equals(other.getOrderCount()))
            && (this.getOrderMoney() == null ? other.getOrderMoney() == null : this.getOrderMoney().equals(other.getOrderMoney()))
            && (this.getAlreadyCount() == null ? other.getAlreadyCount() == null : this.getAlreadyCount().equals(other.getAlreadyCount()))
            && (this.getAlreadyMoney() == null ? other.getAlreadyMoney() == null : this.getAlreadyMoney().equals(other.getAlreadyMoney()))
            && (this.getFailCount() == null ? other.getFailCount() == null : this.getFailCount().equals(other.getFailCount()))
            && (this.getFailMoney() == null ? other.getFailMoney() == null : this.getFailMoney().equals(other.getFailMoney()))
            && (this.getInsertStatus() == null ? other.getInsertStatus() == null : this.getInsertStatus().equals(other.getInsertStatus()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBatchid() == null) ? 0 : getBatchid().hashCode());
        result = prime * result + ((getRequestBatchno() == null) ? 0 : getRequestBatchno().hashCode());
        result = prime * result + ((getRequestDfId() == null) ? 0 : getRequestDfId().hashCode());
        result = prime * result + ((getRequestStr() == null) ? 0 : getRequestStr().hashCode());
        result = prime * result + ((getOrderCount() == null) ? 0 : getOrderCount().hashCode());
        result = prime * result + ((getOrderMoney() == null) ? 0 : getOrderMoney().hashCode());
        result = prime * result + ((getAlreadyCount() == null) ? 0 : getAlreadyCount().hashCode());
        result = prime * result + ((getAlreadyMoney() == null) ? 0 : getAlreadyMoney().hashCode());
        result = prime * result + ((getFailCount() == null) ? 0 : getFailCount().hashCode());
        result = prime * result + ((getFailMoney() == null) ? 0 : getFailMoney().hashCode());
        result = prime * result + ((getInsertStatus() == null) ? 0 : getInsertStatus().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }
}