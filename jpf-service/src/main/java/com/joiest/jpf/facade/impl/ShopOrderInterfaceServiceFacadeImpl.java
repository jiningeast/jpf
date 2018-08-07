package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopOrder;
import com.joiest.jpf.common.po.PayShopOrderExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopOrderMapper;
import com.joiest.jpf.entity.ShopProductInterfaceInfo;
import com.joiest.jpf.facade.ShopOrderInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class ShopOrderInterfaceServiceFacadeImpl implements ShopOrderInterfaceServiceFacade {

    @Autowired
    private PayShopOrderMapper payShopOrderMapper;

    /*@Override
    public int addOrder(ShopProductInterfaceInfo info) {

        PayShopOrder order = new PayShopOrder();
        PayShopOrderExample example = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = example.createCriteria();
        BeanCopier beanCopier = BeanCopier.create(ShopProductInterfaceInfo.class, PayShopOrder.class, false);
        beanCopier.copy(info, order, null);

        return payShopOrderMapper.insertSelective(order);

    }*/
}
