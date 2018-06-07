package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsMapper;
import com.joiest.jpf.entity.MerchantInterfaceInfo;
import com.joiest.jpf.facade.MerchantInterfaceServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class MerchantInterfaceServiceFacadeImpl implements MerchantInterfaceServiceFacade {

    private static final Logger logger = LogManager.getLogger(MerchantInterfaceServiceFacadeImpl.class);

    @Autowired
    private PayMerchantsMapper payMerchantsMapper;

    @Override
    public MerchantInterfaceInfo getMerchantByMerchNo(String MerchNo) {
        PayMerchantsExample e = new PayMerchantsExample();
        PayMerchantsExample.Criteria c = e.createCriteria();
        c.andMerchNoEqualTo(MerchNo);
        List<PayMerchants> list = payMerchantsMapper.selectByExample(e);
        if ( list.isEmpty() ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(),JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());
        }
        MerchantInterfaceInfo merchantInterfaceInfo = new MerchantInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchants.class, MerchantInterfaceInfo.class, false);
        beanCopier.copy(list.get(0),merchantInterfaceInfo,null);

        return merchantInterfaceInfo;
    }

    @Override
    public MerchantInterfaceInfo getMerchantByMid(Long id){
        PayMerchants payMerchants = payMerchantsMapper.selectByPrimaryKey(id);
        if ( payMerchants.getId() == null ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(),JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());
        }
        MerchantInterfaceInfo merchantInterfaceInfo = new MerchantInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchants.class, MerchantInterfaceInfo.class, false);
        beanCopier.copy(payMerchants,merchantInterfaceInfo,null);

        return merchantInterfaceInfo;
    }

}
