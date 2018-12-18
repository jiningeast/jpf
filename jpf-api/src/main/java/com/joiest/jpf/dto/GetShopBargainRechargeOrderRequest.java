package com.joiest.jpf.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class GetShopBargainRechargeOrderRequest {

    private String orderNo;

    private String merchNo;

    private Integer orderType;

    private String foreignOrderNo;

    private String itemName;

    private BigDecimal facePrice;

    private String addtimeStart;

    private String addtimeEnd;

    private Integer infoStatus;

    private Map<String,String> infoStatusMap;

    public Map<String, String> getInfoStatusMap() {
        return infoStatusMap;
    }

    public void setInfoStatusMap(Map<String, String> infoStatusMap) {
        this.infoStatusMap = infoStatusMap;
    }

    private int page;

    private int rows;

    /**
     * 拉取数据时对应的订单号
     */
    private String pullOrderNo;

    private Byte matchingStatus;
    
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getForeignOrderNo() {
        return foreignOrderNo;
    }

    public void setForeignOrderNo(String foreignOrderNo) {
        this.foreignOrderNo = foreignOrderNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getFacePrice() {
        return facePrice;
    }

    public void setFacePrice(BigDecimal facePrice) {
        this.facePrice = facePrice;
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

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
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

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getPullOrderNo() {
        return pullOrderNo;
    }

    public void setPullOrderNo(String pullOrderNo) {
        this.pullOrderNo = pullOrderNo;
    }

    public Byte getMatchingStatus() {
        return matchingStatus;
    }

    public void setMatchingStatus(Byte matchingStatus) {
        this.matchingStatus = matchingStatus;
    }
}
