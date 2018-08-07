package com.joiest.jpf.dto;

import com.joiest.jpf.common.po.PayShopOrder;

import java.math.BigDecimal;
import java.util.Date;

public class ShopOrderInfoInterfaceRequest {

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
     * 订单号
     */
    private String orderNo;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}