package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayMerchants;
import com.joiest.jpf.common.po.PayMerchantsShop;
import com.joiest.jpf.common.po.PayMerchantsShopExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsShopMapper;
import com.joiest.jpf.dto.MerShopRequest;
import com.joiest.jpf.dto.MerShopResponse;
import com.joiest.jpf.entity.MerchantShopInfo;
import com.joiest.jpf.facade.MerShopServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
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
    public MerShopResponse getMerShops(MerShopRequest merShopRequest){
        PayMerchantsShopExample e = new PayMerchantsShopExample();
        PayMerchantsShopExample.Criteria c = e.createCriteria();
        e.setPageNo(merShopRequest.getPage());
        e.setPageSize(merShopRequest.getRows());
        // 构造查询条件
        c.andIsDelEqualTo(0);   // 筛选掉逻辑删除的记录
        if ( merShopRequest.getMtsid() != null ){
            c.andMtsidEqualTo(merShopRequest.getMtsid());
        }
        if ( merShopRequest.getPid() != null ){
            c.andPidEqualTo(merShopRequest.getPid());
        }
        Date addtimeStart = new Date();
        if (StringUtils.isNotBlank(merShopRequest.getAddtimeStart()) ){
            addtimeStart = DateUtils.getFdate( merShopRequest.getAddtimeStart(),DateUtils.DATEFORMATLONG );
            c.andCreatedGreaterThanOrEqualTo(addtimeStart);
        }
        Date addtimeEnd = new Date();
        if ( StringUtils.isNotBlank( merShopRequest.getAddtimeEnd() ) ){
            addtimeEnd = DateUtils.getFdate( merShopRequest.getAddtimeEnd(),DateUtils.DATEFORMATLONG );
            c.andCreatedLessThanOrEqualTo(addtimeEnd);
        }
        if ( StringUtils.isNotBlank(merShopRequest.getAddtimeStart()) && StringUtils.isNotBlank(merShopRequest.getAddtimeEnd()) ){
            c.andCreatedBetween( addtimeStart, addtimeEnd );
        }
        List<PayMerchantsShop> list = payMerchantsShopMapper.selectByExample(e);
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

        MerShopResponse merShopResponse = new MerShopResponse();
        merShopResponse.setList(infos);
        merShopResponse.setCount(payMerchantsShopMapper.countByExample(e));

        return merShopResponse;
    }
}
