package com.joiest.jpf.dto;

import javax.validation.constraints.Pattern;

/**
 * 油卡充值
 */
public class CreateOrderInterfaceRequest {

    /**
     * 卡号
     */
    private String cardno;

    /**
     * 卡号2
     */
    private String cardnumber;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 手机号2
     */
    private String mobile;

    /**
     * 充值金额
     */
    @Pattern(regexp = "^\\d{1,5}$", message = "充值金额错误")
    private String money;

    /**
     * 实付金额
     */
    @Pattern(regexp = "^\\d{1,5}$", message = "交易金额格式错误")
    private String paymoney;

    /**
     * 1中石化充值 2中石油充值 3话费充值
     */
    @Pattern(regexp = "^\\d{1}$", message = "类型错误")
    private String otype;

    /**
     * 商品ID
     */
    @Pattern(regexp = "^\\d{1,9}$", message = "商品ID错误")

    private String pid;

    /**
     * 数量
     */
    private String amount;

    /**
     * 接收卡密方式
     */
    private String receiveType;

    /**
     * 接收值，手机号或邮箱
     */
    private String reveiveValue;

    /**
     * 商品类型
     */
    private String productType;

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(String paymoney) {
        this.paymoney = paymoney;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getReveiveValue() {
        return reveiveValue;
    }

    public void setReveiveValue(String reveiveValue) {
        this.reveiveValue = reveiveValue;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
