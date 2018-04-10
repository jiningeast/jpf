package com.joiest.jpf.dto;

public class GetProductRequest {

    /**
     * 产品名称
     */
    /**
     * 产品名称
     */
    private String pname;

    private long pageNo;

    private long pageSize;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
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
