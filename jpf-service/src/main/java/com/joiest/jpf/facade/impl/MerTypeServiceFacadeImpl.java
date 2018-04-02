package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayMerchantsPaytype;
import com.joiest.jpf.common.po.PayMerchantsType;
import com.joiest.jpf.common.po.PayMerchantsTypeExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsTypeMapper;
import com.joiest.jpf.entity.MerchantTypeInfo;
import com.joiest.jpf.facade.MerTypeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class MerTypeServiceFacadeImpl implements MerTypeServiceFacade {

    @Autowired
    private PayMerchantsTypeMapper payMerchantsTypeMapper;


    @Override
    public List<MerchantTypeInfo> getMerTypes(String pid) {
        PayMerchantsTypeExample example = new PayMerchantsTypeExample();
        PayMerchantsTypeExample.Criteria c = example.createCriteria();
        if(StringUtils.isNotBlank(pid)){
            c.andPidEqualTo(pid);
        }
        List<PayMerchantsType> payMerchantsTypes = payMerchantsTypeMapper.selectByExample(example);
        List<MerchantTypeInfo> merchantTypeInfos = new ArrayList<>();
        for (PayMerchantsType payMerchantsType : payMerchantsTypes) {
            MerchantTypeInfo merchantTypeInfo = new MerchantTypeInfo();
            BeanCopier beanCopier = BeanCopier.create(PayMerchantsType.class, MerchantTypeInfo.class, false);
            beanCopier.copy(payMerchantsType, merchantTypeInfo, null);
            merchantTypeInfos.add(merchantTypeInfo);
        }
        return merchantTypeInfos;
    }
}
