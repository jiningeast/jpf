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

public class MerchantInterfaceServiceFacadeImpl implements MerchantInterfaceServiceFacade {

    private static final Logger logger = LogManager.getLogger(MerchantInterfaceServiceFacadeImpl.class);

    @Autowired
    private PayMerchantsMapper payMerchantsMapper;

    @Override
    public MerchantInterfaceInfo getMerchant(Long id) {
        PayMerchants payMerchants = payMerchantsMapper.selectByPrimaryKey(id);
        if ( payMerchants == null ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(),JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());
        }
        MerchantInterfaceInfo merchantInterfaceInfo = new MerchantInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchants.class, MerchantInterfaceInfo.class, false);
        beanCopier.copy(payMerchants,merchantInterfaceInfo,null);

        return merchantInterfaceInfo;
    }

}
