package com.joiest.jpf.facade;

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
    public CloudFanSourceInfo getFanSourceByMobile(String mobile);


}
