package com.joiest.jpf.dto;

public class ChargeProductInterfaceRequest {


    /**
     * 搜过内容
     */
    private String  keyword;

    /**
     * 页码
     */
    private String page;

    /**
     * 分页条数
     */
    private String pageSize;

    /**
     * 时间搜索
     */
    private String addtimeStart;

    private String addtimeEnd;


    /**
     * 上下架 0=下架 1=上架
     */
    private String isOnSale;




    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
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

    public String getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(String isOnSale) {
        this.isOnSale = isOnSale;
    }
}