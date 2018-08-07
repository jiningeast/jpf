package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopProduct;
import com.joiest.jpf.common.po.PayShopProductExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductMapper;
import com.joiest.jpf.entity.ShopProductInterfaceInfo;
import com.joiest.jpf.facade.ShopProductInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class ShopProductInterfaceServiceFacadeImpl implements ShopProductInterfaceServiceFacade {

    @Autowired
    private PayShopProductMapper payShopProductMapper;

    @Override
    public ShopProductInterfaceInfo getShopProduct(String id) {
        if ( StringUtils.isBlank(id) )
        {
            return null;
        }
        PayShopProductExample example = new PayShopProductExample();
        PayShopProductExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);
        PayShopProduct payShopProduct = payShopProductMapper.selectByPrimaryKey(id);

        if ( payShopProduct == null )
        {
            return null;
        }
        ShopProductInterfaceInfo info = new ShopProductInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopProduct.class, ShopProductInterfaceInfo.class, false);
        beanCopier.copy(payShopProduct, info, null);

        return info;
    }
}
