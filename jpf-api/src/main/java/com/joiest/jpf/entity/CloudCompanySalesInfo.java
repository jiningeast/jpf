package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudCompanySalesInfo {
    /**
     * 自增ID
     */
    private String id;

    /**
     * 聚合商户编号 merch_no
     */
    private String salesNo;

    /**
     * 服务费费率
     */
    private BigDecimal salesRate;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 修改时间
     */
    private Date updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalesNo() {
        return salesNo;
    }

    public void setSalesNo(String salesNo) {
        this.salesNo = salesNo;
    }

    public BigDecimal getSalesRate() {
        return salesRate;
    }

    public void setSalesRate(BigDecimal salesRate) {
        this.salesRate = salesRate;
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
