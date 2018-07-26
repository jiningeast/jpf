package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetCloudFansourceRequest;
import com.joiest.jpf.dto.GetCloudFansourceResponse;
import com.joiest.jpf.entity.CloudFanSourceInfo;

import java.util.Map;

public interface CloudFanSourceServiceFacade {

    /**
     * 添加粉丝来源
     * */
    public int addFanSource(Map<String,String> map);

    /**
     * 获取粉丝信息通过手机号
     * */
    public CloudFanSourceInfo getFanSourceByMobile(String mobile,Byte type);

    /**
     * 代理列表---后台
     */
    public GetCloudFansourceResponse getFansourceList(GetCloudFansourceRequest request);


    /**
     * 获取公司单条信息
     */

    public CloudFanSourceInfo getFansource(String id);

    /**
     * 获取公司单条信息
     */

    public JpfResponseDto editPass(GetCloudFansourceRequest request,String id)throws Exception;
}
