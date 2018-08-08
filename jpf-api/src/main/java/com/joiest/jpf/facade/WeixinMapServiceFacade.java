package com.joiest.jpf.facade;

import com.joiest.jpf.entity.WeixinMapInfo;

import java.util.Map;

public interface WeixinMapServiceFacade {

    /*
     * 公众号信息
     * */
    public WeixinMapInfo getWeixinMpByEncrypt(String encrypt);

    /*
    * 更新公众号信息
    * */
    public int upWeixinMpByEncrypt(String encrypt, Map<String,String> res);

    /**
     * 获取access_token
     * @param weixinMapInfo 公众号信息
     * */
    public String getAccessToken(WeixinMapInfo weixinMapInfo);
}
