package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopInterfaceStream;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopInterfaceStreamMapper;
import com.joiest.jpf.entity.ShopInterfaceStreamInfo;
import com.joiest.jpf.facade.ShopInterfaceStreamServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class ShopInterfaceStreamServiceFacadeImpl implements ShopInterfaceStreamServiceFacade {

    @Autowired
    private PayShopInterfaceStreamMapper payShopInterfaceStreamMapper;

    /**
     * 添加一条接口流水记录
     */
    public int addStream(ShopInterfaceStreamInfo shopInterfaceStreamInfo){
        PayShopInterfaceStream payShopInterfaceStream = new PayShopInterfaceStream();

        BeanCopier beanCopier = BeanCopier.create(ShopInterfaceStreamInfo.class, PayShopInterfaceStream.class, false);
        beanCopier.copy(shopInterfaceStreamInfo, payShopInterfaceStream, null);

        return payShopInterfaceStreamMapper.insertSelective(payShopInterfaceStream);
    }
}
