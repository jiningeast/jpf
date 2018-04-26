package com.joiest.jpf.dto;

public class GetMerchPayTypeRequest {
    /**
     * 商户ID
     */
    private Long mtsid;
    /**
     * 支付类型
     */
    private Integer tpid;

    /**
     * 支付类型catpath
     */
    private String catpath;

    /**
     * yyyy-mm-dd
     */
    private String createStartDate;

    /**
     * yyyy-mm-dd
     */
    private String createEndDate;

    private long rows;

    private long page;

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Integer getTpid() {
        return tpid;
    }

    public void setTpid(Integer tpid) {
        this.tpid = tpid;
    }

    public String getCatpath() {
        return catpath;
    }

    public void setCatpath(String catpath) {
        this.catpath = catpath;
    }

    public String getCreateStartDate() {
        return createStartDate;
    }

    public void setCreateStartDate(String createStartDate) {
        this.createStartDate = createStartDate;
    }

    public String getCreateEndDate() {
        return createEndDate;
    }

    public void setCreateEndDate(String createEndDate) {
        this.createEndDate = createEndDate;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }
}
