package com.joiest.jpf.dto;

public class ShopBatchRequest {

    /**
     * id
     */
    private String id;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 状态 0=生成券码中 1=生成完毕，待发券  2=已发券 3=已取消
     */
    private byte status;

    /**
     * 销售人姓名
     */
    private String salesName;

    /**
     * 接收人姓名
     */
    private String receiveName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
}
