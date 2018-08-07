package com.joiest.jpf.dto;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

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
    private String cardNo;

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
    @Pattern(regexp = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2}){1}$", message = "充值金额错误")
    private String money;

    /**
     * 实付金额
     */
    @Pattern(regexp = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2}){1}$", message = "交易金额格式错误")
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

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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
}
