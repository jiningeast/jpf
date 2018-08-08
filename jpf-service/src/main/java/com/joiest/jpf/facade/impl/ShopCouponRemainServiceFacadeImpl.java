package com.joiest.jpf.facade.impl;

import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponRemainMapper;
import com.joiest.jpf.facade.ShopCouponRemainServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;

public class ShopCouponRemainServiceFacadeImpl implements ShopCouponRemainServiceFacade {

    @Autowired
    private PayShopCouponRemainMapper payShopCouponRemainMapper;

    /**
     * 处理过期的券
     */
    @Override
    public void dealCoupon(){
        
    }
}
