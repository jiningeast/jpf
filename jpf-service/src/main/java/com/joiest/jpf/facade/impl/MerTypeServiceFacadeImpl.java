package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayMerchantsTypeCustom;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayMerchantsPaytype;
import com.joiest.jpf.common.po.PayMerchantsType;
import com.joiest.jpf.common.po.PayMerchantsTypeExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayMerchantsTypeCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsTypeMapper;
import com.joiest.jpf.entity.MerchantTypeInfo;
import com.joiest.jpf.entity.MerchantTypeTree;
import com.joiest.jpf.facade.MerTypeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class MerTypeServiceFacadeImpl implements MerTypeServiceFacade {

    @Autowired
    private PayMerchantsTypeMapper payMerchantsTypeMapper;

    @Autowired
    private PayMerchantsTypeCustomMapper payMerchantsTypeCustomMapper;


    @Override
    public List<MerchantTypeInfo> getMerTypes(String pid) {
        if(StringUtils.isBlank(pid)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "pid不能为空");
        }
        PayMerchantsTypeExample example = new PayMerchantsTypeExample();
        PayMerchantsTypeExample.Criteria c = example.createCriteria();
        c.andPidEqualTo(pid);
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

    /**
     * 获取单条信息 pay_merchants_type
     * @param catid
     */
    public MerchantTypeInfo getOneTypeByCatid(String catid)
    {
        if ( StringUtils.isBlank(catid) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "catid不能为空");
        }
        PayMerchantsTypeExample example = new PayMerchantsTypeExample();
        PayMerchantsTypeExample.Criteria c = example.createCriteria();
        c.andCatidEqualTo(Integer.valueOf(catid));
        PayMerchantsType payMerchantsType= payMerchantsTypeMapper.selectByPrimaryKey(Integer.valueOf(catid));
        MerchantTypeInfo merchantTypeInfo = new MerchantTypeInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchantsType.class, MerchantTypeInfo.class, false);
        beanCopier.copy(payMerchantsType, merchantTypeInfo, null);
        return merchantTypeInfo;
    }

    /**
     * 获取单条信息 pay_merchants_type
     * @param catid
     */
    public List<MerchantTypeInfo> getOneTypeByCatId(String catid)
    {
        if ( StringUtils.isBlank(catid) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "catid不能为空");
        }
        PayMerchantsTypeExample example = new PayMerchantsTypeExample();
        PayMerchantsTypeExample.Criteria c = example.createCriteria();
        c.andCatidEqualTo(Integer.valueOf(catid));
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

    @Override
    public List<MerchantTypeTree> getMerchantTypeTree(int catId) {
        List<PayMerchantsTypeCustom> payMerchantsTypeCustoms = payMerchantsTypeCustomMapper.findPayMerchantsTypeTree(catId);
        List<MerchantTypeTree> merchantTypeTrees = new ArrayList<>();
        for (PayMerchantsTypeCustom payMerchantsTypeCustom : payMerchantsTypeCustoms) {
            MerchantTypeTree merchantTypeTree = new MerchantTypeTree();
            BeanCopier beanCopier = BeanCopier.create(PayMerchantsTypeCustom.class, MerchantTypeTree.class, false);
            beanCopier.copy(payMerchantsTypeCustom, merchantTypeTree, null);
            merchantTypeTrees.add(merchantTypeTree);
        }
        return merchantTypeTrees;
    }

}
