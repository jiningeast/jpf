package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudIdenauthInfo;

import java.math.BigInteger;
import java.util.Map;

public interface CloudIdenauthServiceFacade {

    /*
     * 新增认证信息
     * */
    public int addCloudIdenauth(Map<String,String> idenAuth);

    /*
     * 更新增认证信息
     * */
    public int updateCloudIdenauthById(Map<String,String> idenAuth,BigInteger id);

    /*
     * 查询认证信息通过身份证号、姓名
     * */
    public CloudIdenauthInfo getCloudIdenauthByNumName(String name,String num);

}
