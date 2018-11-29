package com.joiest.jpf.entity;


/**
 * @Auther: admin
 * @Date: 2018/11/29 11:02
 * @Description:
 */
public class BatchInfo {

    private String id;
    private String number;
    private String price;
    private String totalPrice;

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
