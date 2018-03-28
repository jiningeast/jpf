package com.joiest.jpf.dto;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.entity.UserInfo;

public class LoginVerifyResponse extends JpfResponseDto{

    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
