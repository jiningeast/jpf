package com.joiest.jpf.facade;

import com.joiest.jpf.entity.WeixinUserInfo;

public interface WeixinUserServiceFacade {

    /**
    * 获取微信用户基本信息by openid
    * */
    public WeixinUserInfo getWeixinMapByOpenid(String openid);
}
