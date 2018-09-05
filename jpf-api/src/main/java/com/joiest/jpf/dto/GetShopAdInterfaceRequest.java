package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class GetShopAdInterfaceRequest {


    /**
     * 广告类型 0=首页图标 1=首页轮播图 2=首页banner图片
     */
    private String type;

    /**
     * 图片文字
     */
    private String title;

    /**
     * 页码
     */
    private String page;

    /**
     * 分页条数
     */
    private String pageSize;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}