package com.joiest.jpf.facade;

import com.joiest.jpf.entity.WeixinMapInfo;

public interface WeixinMapServiceFacade {

    /*
     * 公众号信息
     * */
    public WeixinMapInfo getWeixinMpByEncrypt(String encrypt);
}
