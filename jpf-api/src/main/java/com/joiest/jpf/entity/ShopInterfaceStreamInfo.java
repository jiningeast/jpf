package com.joiest.jpf.entity;

import java.util.Date;

public class ShopInterfaceStreamInfo {
    /**
     * 接口类型
     */
    private String id;

    /**
     * 接口类型 0=OSS文件上传 1=短信 3=话费是否可充值接口查询 4=话费充值接口 5=油卡是否可充值接口查询 6=油卡充值
     */
    private Byte type;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestContent;

    /**
     * 返回内容
     */
    private String responseContent;

    /**
     * 打款任务id
     */
    private String batchId;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     *
     */
    private Date addtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}
