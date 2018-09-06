package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.dto.GetShopCustomerRequest;
import com.joiest.jpf.dto.GetShopCustomerResponse;

public interface ShopCustomerServiceFacade {

    /**
     * 客户列表---后台
     */
    public GetShopCustomerResponse getList(GetShopCustomerRequest request);

    /**
     * 公司停用和启用
     */
    public JpfResponseDto delCompanyCustomer(String id,int type);

    /**
     * 编辑用户信息
     */
    public JpfResponseDto editCompanyCustomer(GetShopCustomerRequest request);

    /**
     * 根据手机号获取用户
     */
    public PayShopCustomer getCustomerByPhone(String phoen);

    /**
     * 更新主键获取用户信息
     */
    public PayShopCustomer getCustomerById(String id);

}
