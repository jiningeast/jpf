package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;

public interface ShopCustomerInterfaceServiceFacade {

    /**
     * 获取个人信息
     */
    public ShopCustomerInterfaceInfo getCustomer(String uid);

    /**
     * 根据openId 获取个人信息
     */
    public ShopCustomerInterfaceInfo getCustomerByOpenId(String openId);

    /**
     * 根据手机号 获取个人信息
     */
    public ShopCustomerInterfaceInfo getCustomerByPhone(String phone);

    /**
     * 根据openId 更新用户信息
     */
    public JpfResponseDto updateCustomerByOpenId(PayShopCustomer record, String openId);

}
