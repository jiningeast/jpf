package com.joiest.jpf.dto;

import javax.validation.constraints.NotNull;

public class UnionPayRefundRequest {

    @NotNull(message="code参数不能为空")
    private String code;

    @NotNull(message="info参数不能为空")
    private String info;

    @NotNull(message="orderid参数不能为空")
    private String orderid;

    @NotNull(message="refund_content参数不能为空")
    private String refund_content;

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

    public String getRefundContent() {
        return refund_content;
    }

    public void setRefundContentn(String json) {
        this.refund_content = json;
    }

    public String toString(){
        return "code="+this.code+",info="+this.info+",orderid="+this.orderid+",json="+this.refund_content;
    }
}
