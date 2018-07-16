package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudEmployeeInfo;

public interface CloudEmployeeServiceFacade {

    /**
     * 商户后台
     * 根据商户编号获取企业登陆表的信息
     */
    public CloudEmployeeInfo getEmployeeInfoByMerchNo(String merchNo);
}
