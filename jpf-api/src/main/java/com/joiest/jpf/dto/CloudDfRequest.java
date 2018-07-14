package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 云帐户代付接口请求类
 */
public class CloudDfRequest {


    /**
     * df订单ID
     */
    @Size(min = 10, max = 30, message = "订单ID错误")
    private String orderid;

    @NotBlank(message = "商户号不能为空")
    private String sysMerchNo;

    /**
     * 签名
     */
    @NotBlank(message = "签名不能为空")
    private String sign;

    @NotBlank(message = "银行编码为空")
    @Pattern(regexp = "^[a-zA-Z]{1,6}$", message = "银行编码错误")
    private String finaCode;

    /**
     * 交易金额
     */
    @Pattern(regexp = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2}){1}$", message = "交易金额格式错误")
    private String tranAmt;

    /**
     * 收款人银行帐号
     */
    @Pattern(regexp = "\\d{13,26}", message = "银行帐号信息错误")
    private String payeeAcct;

    @NotBlank(message = "收款人银行户名不能为空")
    private String payeeName;

    @Pattern(regexp = "^PRIVATE|PUBLIC$", message = "收款人账号属性错误")
    private String payeeAcctAttr;

    @NotBlank(message = "开户行名称不能为空")
    private String bankName;

    @NotBlank(message = "开户行省不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,8}$", message = "开户行省信息错误")
    private String bankProvince;

    @NotBlank(message = "开户行城市不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,8}$", message = "开户行城市信息错误")
    private String bankCity;

    @Pattern(regexp = "^((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0,0-9])|(19[8|9])|(16[6]))\\d{8}$", message = "手机号码错误")
    private String phoneNo;

    @Pattern(regexp = "(^\\d{17}[\\d|x]|\\d{15}$)", message = "身份证号码错误")
    private String certNo;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getSysMerchNo() {
        return sysMerchNo;
    }

    public void setSysMerchNo(String sysMerchNo) {
        this.sysMerchNo = sysMerchNo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getFinaCode() {
        return finaCode;
    }

    public void setFinaCode(String finaCode) {
        this.finaCode = finaCode;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getPayeeAcct() {
        return payeeAcct;
    }

    public void setPayeeAcct(String payeeAcct) {
        this.payeeAcct = payeeAcct;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeAcctAttr() {
        return payeeAcctAttr;
    }

    public void setPayeeAcctAttr(String payeeAcctAttr) {
        this.payeeAcctAttr = payeeAcctAttr;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }
}
