package com.joiest.jpf.dto;

import java.util.Date;

public class GetShopBargainRequestRequest {
    /**
     * 主键id
     */
    private String id;

    /**
     * 发布人id
     */
    private String customerId;

    /**
     * 折损率
     */
    private Double offRate;

    /**
     * 最低豆数
     */
    private Integer minDou;

    /**
     * 是否开启此收豆信息 0=不开启 1=开启
     */
    private Byte status;

    /**
     * 添加时间
     */
    private Date addtime;


    /**
     * 页码
     */
    private Integer page;

    /**
     * 条数
     */
    private Integer rows;


    /**
     * 添加时间搜索
     */
    private String addtimeStart;

    private String addtimeEnd;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Double getOffRate() {
        return offRate;
    }

    public void setOffRate(Double offRate) {
        this.offRate = offRate;
    }

    public Integer getMinDou() {
        return minDou;
    }

    public void setMinDou(Integer minDou) {
        this.minDou = minDou;
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
