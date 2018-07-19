package com.joiest.jpf.entity;

import java.math.BigDecimal;

public class CloudRemitExcelInfo {

    /**
     * 发放总金额
     */
    private BigDecimal commoney;
    /**
     * 批次号
     */
    private String batchno ;
    /**
     * 类型
     * 0=个人 1=企业
     */
    private byte type;

    /**
     * 总行名称
     */
    private String bankName;

    /**
     * 开户行省
     */
    private String province;

    /**
     * 开户行市
     */
    private String city;

    /**
     * 收款账号
     */
    private String bankNo;

    /**
     * 收款户名
     */
    private String name;

    /**
     * 身份证号
     */
    private String IDNo;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 打款金额
     */
    private BigDecimal money;

    /**
     * 备注
     */
    private String memo;

    /**
     * 失败原因
     */
    private String failure;

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDNo() {
        return IDNo;
    }

    public void setIDNo(String IDNo) {
        this.IDNo = IDNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getCommoney() {
        return commoney;
    }

    public void setCommoney(BigDecimal commoney) {
        this.commoney = commoney;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public String getFailure() {
        return failure;
    }

    public void setFailure(String failure) {
        this.failure = failure;
    }
}
