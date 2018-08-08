package com.joiest.jpf.facade;

import com.joiest.jpf.entity.WeixinUserInfo;

import java.util.Map;

public interface WeixinUserServiceFacade {

    /**
    * 获取微信用户基本信息by openid
    * */
    public WeixinUserInfo getWeixinUserByOpenid(String openid,Long id);

    /**
     *添加微信用户基本信息
     * */
    public int addWeixinUser(Map<String ,String> wei);

    /**
     *更新微信用户基本信息通过 openid mpid
     * */
    public int upWeixinUserById(Map<String ,String> wei,Long mpid);


}
