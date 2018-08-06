package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopBatchCoupon;
import com.joiest.jpf.common.po.PayShopBatchCouponExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBatchCouponMapper;
import com.joiest.jpf.entity.ShopBatchCouponInfo;
import com.joiest.jpf.facade.ShopBatchCouponServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopBatchCouponServiceFacadeImpl implements ShopBatchCouponServiceFacade {

    @Autowired
    private PayShopBatchCouponMapper payShopBatchCouponMapper;

    /**
     * 根据批次号获取券
     */
    @Override
    public List<ShopBatchCouponInfo> getCouponByBatchId(String batchId){
        PayShopBatchCouponExample e = new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria c = e.createCriteria();
        c.andBatchIdEqualTo(batchId);

        List<PayShopBatchCoupon> list = payShopBatchCouponMapper.selectByExample(e);
        List<ShopBatchCouponInfo> infos = new ArrayList<>();
        if ( !list.isEmpty() ){
            for (PayShopBatchCoupon payShopBatchCoupon:list){
                ShopBatchCouponInfo shopBatchCouponInfo = new ShopBatchCouponInfo();
                BeanCopier beanCopier = BeanCopier.create(PayShopBatchCoupon.class, ShopBatchCouponInfo.class, false);
                beanCopier.copy(payShopBatchCoupon, shopBatchCouponInfo, null);

                infos.add(shopBatchCouponInfo);
            }
        }

        return infos;
    }
}
