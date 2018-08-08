package com.joiest.jpf.facade;

import com.joiest.jpf.entity.WeixinMpInfo;

import java.util.Map;

public interface WeixinMpServiceFacade {

    /*
     * 公众号信息
     * */
    public WeixinMpInfo getWeixinMpByEncrypt(String encrypt);

    /*
    * 更新公众号信息
    * */
    public int upWeixinMpByEncrypt(String encrypt, Map<String,String> res);

    /**
     * 获取access_token
     * @param weixinMapInfo 公众号信息
     * */
    public String getAccessToken(WeixinMpInfo weixinMapInfo);
}
