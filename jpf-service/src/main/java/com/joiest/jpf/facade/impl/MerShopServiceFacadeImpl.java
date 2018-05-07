package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayMerchants;
import com.joiest.jpf.common.po.PayMerchantsShop;
import com.joiest.jpf.common.po.PayMerchantsShopExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsShopMapper;
import com.joiest.jpf.entity.MerchantShopInfo;
import com.joiest.jpf.facade.MerShopServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class MerShopServiceFacadeImpl implements MerShopServiceFacade {

    private PayMerchantsShopExample e = new PayMerchantsShopExample();

    @Autowired
    private PayMerchantsShopMapper payMerchantsShopMapper;

    @Autowired
    private PayMerchantsMapper payMerchantsMapper;

    @Override
    public Integer getMerShopsCount(){
        return payMerchantsShopMapper.countByExample(e);
    }

    @Override
    public List<MerchantShopInfo> getMerShops(long page, long rows){
        List<PayMerchantsShop> list = payMerchantsShopMapper.selectByExample(e);

        e.setPageNo(page);
        e.setPageSize(rows);
        List<MerchantShopInfo> infos = new ArrayList<>();
        for (PayMerchantsShop payMerchantsShop:list){
            MerchantShopInfo merchantShopInfo = new MerchantShopInfo();
            BeanCopier beanCopier = BeanCopier.create(PayMerchantsShop.class, MerchantShopInfo.class, false);
            beanCopier.copy(payMerchantsShop, merchantShopInfo, null);

            // 根据商户ID查询商户信息
            PayMerchants payMerchants = new PayMerchants();
            payMerchants = payMerchantsMapper.selectByPrimaryKey(merchantShopInfo.getMtsid());
            merchantShopInfo.setMtsName(payMerchants.getMerchName());

            // 根据父商户ID查询商户信息
            if ( merchantShopInfo.getPid() > 0 ){
                payMerchants = payMerchantsMapper.selectByPrimaryKey(merchantShopInfo.getPid());
                merchantShopInfo.setParMtsName(payMerchants.getMerchName());
            }

            infos.add(merchantShopInfo);
        }

        return infos;
    }
}
