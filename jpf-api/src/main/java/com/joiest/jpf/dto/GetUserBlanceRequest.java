package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

public class GetUserBlanceRequest {

    @NotBlank(message = "token不能为空")
    private String token;

    @Pattern(regexp = "^^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-((0[13578]|1[02])|(0[469]|11)|(02))$$", message = "日期格式错误")
    private String data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
