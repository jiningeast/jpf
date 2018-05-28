package com.joiest.jpf.dto;

import javax.validation.constraints.NotNull;

public class YinjiaSignUserInfoRequest {

    /**
     * AES加密数据
     */
    @NotNull(message = "加密数据不能为空")
    private String data;

    /**
     * 真实姓名
     */
    @NotNull(message = "真实姓名不能为空")
    private String signedName;

    /**
     * 身份证号
     */
    @NotNull(message = "身份证号不能为空")
    private String idNo;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String mobileNo;

    /**
     * 信用卡号
     */
    @NotNull(message = "信用卡号")
    private String accountNumber;

    /**
     * 银行code码
     */
    @NotNull(message = "银行code码")
    private String selectFinaCode;

    /**
     * 信用卡后三位尾数
     */
    @NotNull(message = "卡背后三位数字（CVN2）不能为空")
    private String cvn2;

    /**
     * 信用卡有效期
     */
    @NotNull(message = "信用卡有效期不能为空")
    private String validityCard;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSignedName() {
        return signedName;
    }

    public void setSignedName(String signedName) {
        this.signedName = signedName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSelectFinaCode() {
        return selectFinaCode;
    }

    public void setSelectFinaCode(String selectFinaCode) {
        this.selectFinaCode = selectFinaCode;
    }

    public String getCvn2() {
        return cvn2;
    }

    public void setCvn2(String cvn2) {
        this.cvn2 = cvn2;
    }

    public String getValidityCard() {
        return validityCard;
    }

    public void setValidityCard(String validityCard) {
        this.validityCard = validityCard;
    }
}
