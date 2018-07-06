package com.joiest.jpf.dto;

public class GetCloudCompanysRequest {

    /**
     * 商户号
     */
    private String merchNo;

    /**
     * 商户id
     */
    private String mId;

    /**
     * 商户名称
     */
    private String name;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 条数
     */
    private Integer rows;

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
