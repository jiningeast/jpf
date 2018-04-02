package com.joiest.jpf.dto;

public class GetMerchsRequest {

    /**
     * 聚合商户号
     */
    private String merchNo;

    /**
     * 聚合商户名称
     */
    private String merchName;

    /**
     * 用户名
     */
    private String username;

    /**
     * 企业名称
     */
    private String companyname;

    /**
     * 注册时间
     */
    private String startAddTime;
    private String endAddTime;

    /**
     * 用户状态0正常，1冻结余额
     */
    private Byte status;

    /**
     * 营业执照
     */
    private String bslicense;

    /**
     * 企业认证：0:未认证；1:已认证
     */
    private Boolean attestation;

    /**
     * 对应yifuwang库中user表
     */
    private Integer muserid;

    private long pageNo;

    private long pageSize;

    public String getMerchNo() {
        return merchNo;
    }

    public void setMerchNo(String merchNo) {
        this.merchNo = merchNo;
    }

    public String getMerchName() {
        return merchName;
    }

    public void setMerchName(String merchName) {
        this.merchName = merchName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getStartAddTime() {
        return startAddTime;
    }

    public void setStartAddTime(String startAddTime) {
        this.startAddTime = startAddTime;
    }

    public String getEndAddTime() {
        return endAddTime;
    }

    public void setEndAddTime(String endAddTime) {
        this.endAddTime = endAddTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getBslicense() {
        return bslicense;
    }

    public void setBslicense(String bslicense) {
        this.bslicense = bslicense;
    }

    public Boolean getAttestation() {
        return attestation;
    }

    public void setAttestation(Boolean attestation) {
        this.attestation = attestation;
    }

    public Integer getMuserid() {
        return muserid;
    }

    public void setMuserid(Integer muserid) {
        this.muserid = muserid;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
}
