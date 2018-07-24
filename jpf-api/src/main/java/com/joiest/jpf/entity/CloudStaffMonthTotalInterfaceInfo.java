package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudStaffMonthTotalInterfaceInfo {
    /**
     *
     */
    private Long id;

    /**
     * pay_cloud_company_staff表主键ID
     */
    private Long busstaffid;

    /**
     * 月份
     */
    private String month;

    /**
     * 月份总额
     */
    private BigDecimal monthTotal;

    /**
     * orderid集合
     */
    private String orderids;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态 0:未超额 1已超额
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

    public Long getBusstaffid() {
        return busstaffid;
    }

    public void setBusstaffid(Long busstaffid) {
        this.busstaffid = busstaffid;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getMonthTotal() {
        return monthTotal;
    }

    public void setMonthTotal(BigDecimal monthTotal) {
        this.monthTotal = monthTotal;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getOrderids() {
        return orderids;
    }

    public void setOrderids(String orderids) {
        this.orderids = orderids;
    }
}
