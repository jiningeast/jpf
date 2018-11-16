package com.joiest.jpf.dto;

public class ChargeOrderInterfaceRequest {


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
     * 订单状态
     */

    private String status;

    /**
     * 订单号
     */

    private String orderNo;

    /**
     * 商户号
     */
    private String merchNo;


    /**
     * 商户号
     */
    private String productType;

    /**
     * pay_charge_consumer_order表中的订单号
     */
    private String consumerOrderNo;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getConsumerOrderNo() {
        return consumerOrderNo;
    }

    public void setConsumerOrderNo(String consumerOrderNo) {
        this.consumerOrderNo = consumerOrderNo;
    }
}