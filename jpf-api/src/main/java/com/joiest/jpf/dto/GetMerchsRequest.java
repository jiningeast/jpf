package com.joiest.jpf.dto;

public class GetMerchsRequest {

    /**
     * 聚合商户号
     */
    private String merchNo;

    /**
     * 聚合商户名称
     */
    private String merchName;

    private long pageNo;

    private long pageSize;

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
}
