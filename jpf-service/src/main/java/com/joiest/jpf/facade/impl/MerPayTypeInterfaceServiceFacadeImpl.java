package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsPaytypeMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsTypeMapper;
import com.joiest.jpf.dto.AddMerPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeResponse;
import com.joiest.jpf.dto.ModifyMerPayTypeRequest;
import com.joiest.jpf.entity.MerchantPayTypeInfo;
import com.joiest.jpf.facade.MerPayTypeInterfaceServiceFacade;
import com.joiest.jpf.facade.MerPayTypeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.*;

public class MerPayTypeInterfaceServiceFacadeImpl implements MerPayTypeInterfaceServiceFacade {

    @Autowired
    private PayMerchantsMapper payMerchantsMapper;
    @Autowired
    private PayMerchantsPaytypeMapper payMerchantsPaytypeMapper;
    @Autowired
    private PayMerchantsTypeMapper payMerchantsTypeMapper;

    /**
     * 获取某个商户指定支付类型的详情
     * @param mtsid 商户mtsid
     * @param tpid 支付类型
     * @return 支付配置信息
     */
    @Override
    public MerchantPayTypeInfo getOneMerPayTypeByTpid(Long mtsid, Integer tpid){
        PayMerchantsPaytypeExample e = new PayMerchantsPaytypeExample();
        PayMerchantsPaytypeExample.Criteria c = e.createCriteria();
        c.andMtsidEqualTo(mtsid);
        c.andTpidEqualTo(tpid);
        List<PayMerchantsPaytype> list = payMerchantsPaytypeMapper.selectByExample(e);

        MerchantPayTypeInfo merchantPayTypeInfo = new MerchantPayTypeInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchantsPaytype.class, MerchantPayTypeInfo.class, false);
        beanCopier.copy(list.get(0), merchantPayTypeInfo, null);

        return merchantPayTypeInfo;
    }


}
