package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 银嘉分期获取商户分期数接口请求类
 */
public class YinjiaTermsRequest {

    /**
     * 商户号
     */
    @NotBlank(message = "商户号不能为空")
    private String mtsid;

    /**
     * 商户公钥
     *
     */
    @NotBlank(message = "商户公玥不能为空")
    private String publickey;

    public String getMtsid() {
        return mtsid;
    }

    public void setMtsid(String mtsid) {
        this.mtsid = mtsid;
    }

    public String getPublickey() {
        return publickey;
    }

    public void setPublickey(String publickey) {
        this.publickey = publickey;
    }
}
