package com.joiest.jpf.dto;

public class ChargeCompanyMoneyStreamInterfaceRequest {


    /**
     * 流水号 MS+时间戳+3位随机数
     */
    private String streamNo;


    /**
     * 商户号
     */
    private String merchNo;


    /**
     * 订单号 可能是消费订单、充值订单、退款订单
     */
    private String orderNo;


    /**
     * 产品名称
     */
    private String productName;

    /**
     * 流水类型 0=收入 1=支出
     */
    private String streamType;


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
     * 流水类型 1=充值 2=下单 3=退款
     */
    private Byte status;

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

    public String getStreamNo() {
        return streamNo;
    }

    public void setStreamNo(String streamNo) {
        this.streamNo = streamNo;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}