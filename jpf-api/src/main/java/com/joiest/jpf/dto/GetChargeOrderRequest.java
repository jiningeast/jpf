package com.joiest.jpf.dto;

import java.util.Map;

public class GetChargeOrderRequest {

    private String id;

    private String orderNo;

    private String foreignOrderNo;

    private String companyId;

    private String companyName;

    private String merchNo;

    private String chargePhone;

    private String productId;

    private String productName;

    private Byte interfaceType;

    private Byte status;

    private int page;

    private int rows;

    private String addtimeStart;

    private String addtimeEnd;

    private String interfaceOrderNo;
    
    private Map<String,String> interfaceTypeParam;
    
    private Map<String,String> statusParam;

    public String getInterfaceOrderNo() {
        return interfaceOrderNo;
    }

    public void setInterfaceOrderNo(String interfaceOrderNo) {
        this.interfaceOrderNo = interfaceOrderNo;
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

    public Map<String, String> getInterfaceTypeParam() {
        return interfaceTypeParam;
    }

    public void setInterfaceTypeParam(Map<String, String> interfaceTypeParam) {
        this.interfaceTypeParam = interfaceTypeParam;
    }

    public Map<String, String> getStatusParam() {
        return statusParam;
    }

    public void setStatusParam(Map<String, String> statusParam) {
        this.statusParam = statusParam;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getForeignOrderNo() {
        return foreignOrderNo;
    }

    public void setForeignOrderNo(String foreignOrderNo) {
        this.foreignOrderNo = foreignOrderNo;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getChargePhone() {
        return chargePhone;
    }

    public void setChargePhone(String chargePhone) {
        this.chargePhone = chargePhone;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Byte getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(Byte interfaceType) {
        this.interfaceType = interfaceType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
