package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

public class DouTransferRequest {

    @NotBlank(message = "转让服务金额不合法")
    @Pattern(regexp = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2}){1}$", message = "转让服务金额不合法")
    private String dou;

    @NotBlank(message = "转让价有误")
    @Pattern(regexp = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2}){1}$", message = "转让价不合法")
    private String transferPrice;


    @NotBlank(message = "收款人姓名不能为空")
    private String realName;

    @NotBlank(message = "银行卡号不能为空")
    private String bankNo;

    @NotBlank(message = "身份证号不能为空")
    private String idno;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0,0-9])|(19[8|9])|(16[6]))\\d{8}$",message = "手机号格式有误")
    private String phone;

    @NotBlank(message = "开户行不能为空")
    private String bankBrank;

    @NotBlank(message = "银行信息有误")
    private String bankId;

    @NotBlank(message = "银行信息有误")
    private String findCode;

    @NotBlank(message = "转让服务标识有误")
    private String bargainRequestId;

    public String getDou() {
        return dou;
    }

    public void setDou(String dou) {
        this.dou = dou;
    }

    public String getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(String transferPrice) {
        this.transferPrice = transferPrice;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBankBrank() {
        return bankBrank;
    }

    public void setBankBrank(String bankBrank) {
        this.bankBrank = bankBrank;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getFindCode() {
        return findCode;
    }

    public void setFindCode(String findCode) {
        this.findCode = findCode;
    }

    public String getBargainRequestId() {
        return bargainRequestId;
    }

    public void setBargainRequestId(String bargainRequestId) {
        this.bargainRequestId = bargainRequestId;
    }
}
