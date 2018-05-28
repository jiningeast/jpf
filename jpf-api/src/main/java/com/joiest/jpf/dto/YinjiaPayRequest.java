package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 银嘉分期支付接口请求类
 */
public class YinjiaPayRequest {

    /**
     * 商户号
     */
    @NotBlank(message = "验证码不能为空")
    private String smsCode;

    /**
     * 商户公钥
     * sign签名串
     */
    @NotBlank(message = "签名串不能为空")
    private String data;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
