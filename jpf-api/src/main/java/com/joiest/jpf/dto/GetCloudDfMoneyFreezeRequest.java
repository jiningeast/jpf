package com.joiest.jpf.dto;

public class GetCloudDfMoneyFreezeRequest {

    private String id;

    private int page;

    private int rows;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
