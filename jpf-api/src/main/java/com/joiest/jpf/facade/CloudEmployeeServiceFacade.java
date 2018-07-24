package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudEmployeeInfo;

import java.util.Map;

public interface CloudEmployeeServiceFacade {

    /**
     * 商户后台
     * 根据商户编号获取企业登陆表的信息
     */
    public CloudEmployeeInfo getEmployeeInfoByMerchNo(String merchNo);

    /**
     * 获取公司登录信息通过邮箱
     **/
    public CloudEmployeeInfo getCompayLoginInfoByEmail(String email);

    /**
     * 获取公司登录信息通过主键id
     **/
    public CloudEmployeeInfo getCompayEmployeeByUid(Integer uid);

    /**
     * 修改登录密码
     **/
    public int upCompanyEmployeePwdByUid(Map<String,String> comInfo,Integer uid);

    /**
     * 获取商户信息通过token
     * */
    public CloudEmployeeInfo companyIsLogin(String token);

}
