package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.po.PayShopCustomerExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCustomerMapper;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class ShopCustomerInterfaceServiceFacadeImpl implements ShopCustomerInterfaceServiceFacade {

    @Autowired
    private PayShopCustomerMapper payShopCustomerMapper;

    @Override
    public ShopCustomerInterfaceInfo getCustomer(String uid) {
       if ( StringUtils.isBlank(uid) )
       {
           return null;
       }
        PayShopCustomerExample example = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = example.createCriteria();
        PayShopCustomer payShopCustomer = payShopCustomerMapper.selectByPrimaryKey(uid);
        if ( payShopCustomer == null )
        {
            return null;
        }
        ShopCustomerInterfaceInfo info = new ShopCustomerInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopCustomer.class, ShopCustomerInterfaceInfo.class, false);
        beanCopier.copy(payShopCustomer, info, null);

        return info;
    }
}
