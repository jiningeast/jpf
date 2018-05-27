package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 银嘉分期生成订单接口请求类
 */
public class YinjiaCreateOrderRequest {

    /**
     * 外来订单id
     */
    @NotBlank(message = "订单号不能为空")
    private String orderid;

    /**
     * 商户号
     */
    @NotBlank(message = "商户号不能为空")
    private String mid;

    /**
     * 产品id
     */
    @NotBlank(message = "产品id不能为空")
    private String productId;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空")
    private String productName;

    /**
     * 产品数量
     */
    @NotBlank(message = "产品数量不能为空")
    private String productAmount;

    /**
     * 产品单价
     */
    @NotBlank(message = "产品单价不能为空")
    private String productUnitPrice;

    /**
     * 产品总价
     */
    @NotBlank(message = "产品总价不能为空")
    private String productTotalPrice;

    /**
     * 返回地址
     */
    @NotBlank(message = "返回地址不能为空")
    private String returnUrl;

    /**
     * 签名串
     */
    @NotBlank(message = "签名串不能为空")
    private String sign;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public String getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(String productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public String getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setProductTotalPrice(String productTotalPrice) {
        this.productTotalPrice = productTotalPrice;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String toString(){
        return "mid="+mid+"&orderid="+orderid+"&productAmount="+productAmount+"&productId="+productId+"&productName="+productName+"&productTotalPrice="+productTotalPrice+"&productUnitPrice="+productUnitPrice+"&returnUrl="+returnUrl+"&sign="+sign;
    }
}
