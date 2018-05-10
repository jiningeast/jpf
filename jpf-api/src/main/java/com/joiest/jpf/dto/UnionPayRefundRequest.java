package com.joiest.jpf.dto;

import javax.validation.constraints.NotNull;

public class UnionPayRefundRequest {

    @NotNull(message="code参数不能为空")
    private String code;

    @NotNull(message="info参数不能为空")
    private String info;

    @NotNull(message="orderid参数不能为空")
    private String orderid;

    @NotNull(message="json参数不能为空")
    private String json;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String toString(){
        return "code="+this.code+",info="+this.info+",orderid="+this.orderid+",json="+this.json;
    }
}
