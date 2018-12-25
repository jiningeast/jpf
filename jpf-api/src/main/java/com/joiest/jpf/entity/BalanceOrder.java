package com.joiest.jpf.entity;


/**
 * @Auther: admin
 * @Date: 2018/12/18 16:12
 * @Description:
 */

public class BalanceOrder {

    private String interfaceOrderNo;

    private String interfaceOrderStatus;

    private String selfOrderNo;

    private String selfOrderStatus;

    private String money;

    private String phone;

    private String orderId;

    private String addtime;

    public String getInterfaceOrderNo() {
        return interfaceOrderNo;
    }

    public void setInterfaceOrderNo(String interfaceOrderNo) {
        this.interfaceOrderNo = interfaceOrderNo;
    }

    public String getInterfaceOrderStatus() {
        return interfaceOrderStatus;
    }

    public void setInterfaceOrderStatus(String interfaceOrderStatus) {
        this.interfaceOrderStatus = interfaceOrderStatus;
    }

    public String getSelfOrderNo() {
        return selfOrderNo;
    }

    public void setSelfOrderNo(String selfOrderNo) {
        this.selfOrderNo = selfOrderNo;
    }

    public String getSelfOrderStatus() {
        return selfOrderStatus;
    }

    public void setSelfOrderStatus(String selfOrderStatus) {
        this.selfOrderStatus = selfOrderStatus;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
}
