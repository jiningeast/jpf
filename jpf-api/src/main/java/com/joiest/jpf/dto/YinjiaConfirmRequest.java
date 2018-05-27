package com.joiest.jpf.dto;

import javax.validation.constraints.NotNull;

public class YinjiaConfirmRequest {

    /**
     * 订单id
     */
    @NotNull(message = "平台订单号不能为空")
    private String orderid;

    /**
     * 分期期数
     */
    @NotNull(message = "分期期数不能为空")
    private String term;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
