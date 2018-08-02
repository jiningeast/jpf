package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

/**
 * 银嘉分期生成订单接口请求类
 */
public class YinjiaCreateOrderRequest {

    /**
     * 外来订单id
     */
    @NotBlank(message = "订单号不能为空")
    @Pattern( regexp = "[a-zA-Z0-9]{4,50}", message = "订单ID信息非法,请确认核对")
    private String orderid;

    /**
     * 支付方式
     * 1：7银联分期信用卡支付
     * 2：微信支付
     */
    private String payType;

    /**
     * 商户号
     */
    @NotBlank(message = "商户号不能为空")
    @Pattern(regexp = "\\d{1,17}", message = "商户号信息非法,请确认核对")
    private String mid;

    /**
     * 产品id
     */
    @NotBlank(message = "产品id不能为空")
    @Pattern(regexp = "\\d{1,17}", message = "产品ID信息非法,请确认核对")
    private String productId;

    /**
     * 产品名称
     */
    @NotBlank(message = "产品名称不能为空")
    @Pattern( regexp = "[a-zA-Z0-9_\\u4e00-\\u9fa5]{1,50}", message = "产品信息非法，请确认核对")
    private String productName;

    /**
     * 产品数量
     */
    @NotBlank(message = "商品数量不能为空")
    @Digits(integer = 3, fraction = 0, message = "商品数量非法，请确认核对")
    @Range(min = 1, max = 999, message = "商品数量非法，请确认核对")
    private String productAmount;

    /**
     * 产品单价
     */
    @NotBlank(message = "产品单价不能为空")
    @Digits(integer = 7, fraction = 2, message = "产品单价非法1，请确认核对")
    @Range(min = 1, max = 1000000, message = "产品单价非法2，请确认核对")
    private String productUnitPrice;

    /**
     * 产品总价
     */
    @NotBlank(message = "产品总价不能为空")
    @Digits(integer = 7, fraction = 2, message = "产品总价非法，请确认核对")
    @Range(min = 1, max = 1000000, message = "产品总价非法，请确认核对")
    private String productTotalPrice;

    /**
     * 返回地址
     */
    @NotBlank(message = "返回地址不能为空")
    private String returnUrl;

    /**
     * 异步通知地址
     */
    @NotBlank(message = "异步通知地址不能为空")
    private String notifyUrl;

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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
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
