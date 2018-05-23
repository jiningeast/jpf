package com.joiest.jpf.dto;

import javax.validation.constraints.NotNull;

public class YinjiaTermsRequest {

    /**
     * 商户号
     */
    @NotNull(message = "商户号不能为空")
    private String mtsid;

    /**
     * 商户公钥
     */
    @NotNull(message = "商户公玥不能为空")
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
