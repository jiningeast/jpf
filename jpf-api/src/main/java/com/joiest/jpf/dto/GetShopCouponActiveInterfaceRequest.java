package com.joiest.jpf.dto;

import com.joiest.jpf.common.po.PayShopCouponActive;

import java.math.BigDecimal;
import java.util.Date;

public class GetShopCouponActiveInterfaceRequest {
    /**
     * 消费行为 0=激活 1=消费 2=退豆 3=过期
     */
    private String type;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 页码
     */
    private String page;

    /**
     * 分页条数
     */
    private String pageSize;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
