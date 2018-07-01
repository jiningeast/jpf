package com.joiest.jpf.dto;

import org.hibernate.validator.constraints.NotBlank;

public class GetUserBlanceRequest {

    @NotBlank(message = "token不能为空")
    private String token;

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
