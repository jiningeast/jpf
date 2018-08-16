package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopBatchCoupon;
import com.joiest.jpf.common.po.PayShopBatchCouponExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBatchCouponMapper;
import com.joiest.jpf.dto.ShopBatchCouponResponse;
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
    public ShopBatchCouponResponse getCouponByBatchId(String batchId, int page, int rows){
        ShopBatchCouponResponse response = new ShopBatchCouponResponse();

        PayShopBatchCouponExample e = new PayShopBatchCouponExample();
        PayShopBatchCouponExample.Criteria c = e.createCriteria();
        c.andBatchIdEqualTo(batchId);
        int count = payShopBatchCouponMapper.countByExample(e);
        e.setPageNo(page);
        e.setPageSize(rows);

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
        response.setList(infos);
        response.setCount(count);

        return response;
    }
}
