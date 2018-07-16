package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudEmployeeInfo;

public interface CloudEmployeeServiceFacade {

    /**
     * 获取公司登录信息通过邮箱
     **/
    public CloudEmployeeInfo getCompayLoginInfoByEmail(String email);

    /**
     * 获取公司登录信息通过主键id
     **/
    public CloudEmployeeInfo getCompayEmployeeByUid(Integer uid);
}
