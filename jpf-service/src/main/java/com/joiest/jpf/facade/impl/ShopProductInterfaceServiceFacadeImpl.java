package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopProductInterfaceCustom;
import com.joiest.jpf.common.po.PayShopProduct;
import com.joiest.jpf.common.po.PayShopProductExample;
import com.joiest.jpf.common.po.PayShopProductInfoExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopProductInterfaceCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductInfoMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductMapper;
import com.joiest.jpf.entity.ShopProductInterfaceInfo;
import com.joiest.jpf.facade.ShopProductInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopProductInterfaceServiceFacadeImpl implements ShopProductInterfaceServiceFacade {

    @Autowired
    private PayShopProductMapper payShopProductMapper;

    @Autowired
    private PayShopProductInterfaceCustomMapper payShopProductInterfaceCustomMapper;

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

    @Override
    public List<ShopProductInterfaceInfo> getShopProductByBrandId(String brandId, String chargeType) {

        if ( StringUtils.isBlank(brandId) )
        {
            return null;
        }

        if ( StringUtils.isBlank(chargeType) )
        {
            return null;
        }
        PayShopProductInfoExample example = new PayShopProductInfoExample();
        PayShopProductInfoExample.Criteria c = example.createCriteria();
        c.andBrandIdEqualTo(Integer.parseInt(brandId));

        PayShopProductInterfaceCustom where = new PayShopProductInterfaceCustom();
        where.setBrandId(Integer.parseInt(brandId));
        where.setType(Byte.parseByte(chargeType));

        List<PayShopProductInterfaceCustom> list = payShopProductInterfaceCustomMapper.getProductListCustomByBrand(where);

        List<ShopProductInterfaceInfo> resultList = new ArrayList<>();
        for (PayShopProductInterfaceCustom one : list)
        {
            ShopProductInterfaceInfo info = new ShopProductInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopProductInterfaceCustom.class, ShopProductInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }

        return resultList;
    }
}
