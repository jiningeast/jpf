package com.joiest.jpf.common.dto;


import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 接口请求基类
 */
public class JpfRequestDto implements Serializable {


    /**
     * 请求IP IPV4
     */
    @Pattern(regexp = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$",message = "请求IP格式错误")
    private String clientIp;


    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("clientIp", clientIp)
                .toString();
    }
}
