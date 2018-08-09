package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetWeixinMpRequest;
import com.joiest.jpf.dto.GetWeixinMpResponse;
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

    /**
     * 获取列表
     * */
     public GetWeixinMpResponse getList(GetWeixinMpRequest request);

    /**
     * 添加信息
     * */
    public JpfResponseDto addsub(GetWeixinMpRequest request);


    /**
     * 获取单条信息
     * */

    public WeixinMpInfo getOne(String id);

    /**
     * 修改信息
     * */
    public JpfResponseDto edit(GetWeixinMpRequest request);

}
