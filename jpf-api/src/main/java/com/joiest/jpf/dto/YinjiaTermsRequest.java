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
    private String mid;

    /**
<<<<<<< HEAD
     * 商户公钥
     *
=======
     * sign签名串
>>>>>>> 64bf87facb67828e7a67d71329c997109c6b2ce2
     */
    @NotBlank(message = "签名串不能为空")
    private String sign;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
