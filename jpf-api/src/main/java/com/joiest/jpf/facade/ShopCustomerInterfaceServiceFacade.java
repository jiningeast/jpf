package com.joiest.jpf.facade;

import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;

public interface ShopCustomerInterfaceServiceFacade {

    /**
     * 获取个人信息
     */
    public ShopCustomerInterfaceInfo getCustomer(String uid);

}
