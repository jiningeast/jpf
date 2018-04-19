package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayMerchants;
import com.joiest.jpf.common.po.PayMerchantsAgent;
import com.joiest.jpf.common.po.PayMerchantsAgentExample;
import com.joiest.jpf.common.po.PayMerchantsExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsAgentMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsMapper;
import com.joiest.jpf.entity.MerchantAgentInfo;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.facade.MerAgentServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class MerAgentServiceFacadeImpl implements MerAgentServiceFacade{

    @Autowired
    private PayMerchantsAgentMapper payMerchantsAgentMapper;

    @Autowired
    private PayMerchantsMapper payMerchantsMapper;

    @Override
    public MerchantAgentInfo getMerchAgentInfo(Long mtsid)
    {
        if ( mtsid == null )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "mtsid不能为空");
        }
        PayMerchantsAgentExample example = new PayMerchantsAgentExample();
        PayMerchantsAgentExample.Criteria c = example.createCriteria();
        c.andMtsidEqualTo(mtsid);
        List<PayMerchantsAgent> list = payMerchantsAgentMapper.selectByExample(example);
        if ( list == null || list.isEmpty() )
        {
            return null;
        }
        PayMerchantsAgent payMerchantsAgent = list.get(0);
        MerchantAgentInfo merchantAgentInfo = new MerchantAgentInfo();
        BeanCopier beanCopier = BeanCopier.create(PayMerchantsAgent.class,MerchantAgentInfo.class,false);
        beanCopier.copy(payMerchantsAgent,merchantAgentInfo,null);
        return merchantAgentInfo;
    }

    /**
     * 根据不同等级，获取不同等级商户信息
     * @param tpid 等级
     * @return
     */
    public List<MerchantInfo> getAgentInfoByTpid(String tpid)
    {
        if ( tpid == null )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "tpid不能为空");
        }
        //获取商户ids
        PayMerchantsAgentExample example = new PayMerchantsAgentExample();
        PayMerchantsAgentExample.Criteria c = example.createCriteria();
        c.andTpidEqualTo(tpid);
        List<PayMerchantsAgent> merIdsList = payMerchantsAgentMapper.selectByExample(example);
        if ( merIdsList == null || merIdsList.isEmpty() )
        {
            System.out.println("merIdsList is not null.");
            return null;
        }
        // 获取某等级下所有商户信息集合
        List<PayMerchants> payMerchantsList = new ArrayList<>();
        for (PayMerchantsAgent payMerchantsAgent : merIdsList){
            Long mtsId = payMerchantsAgent.getMtsid();
            PayMerchants payMerchants = payMerchantsMapper.selectByPrimaryKey(mtsId);
            payMerchantsList.add(payMerchants);
        }

        // 遍历查询到的商户信息，准备copy&return
        List<MerchantInfo> merchantInfos = new ArrayList<>();
        for (PayMerchants payMerchants:payMerchantsList){
            MerchantInfo merchantInfo = new MerchantInfo();
            BeanCopier beanCopier = BeanCopier.create(PayMerchants.class, MerchantInfo.class, false);
            beanCopier.copy(payMerchants, merchantInfo, null);
            merchantInfos.add(merchantInfo);
        }
        return merchantInfos;
    }
}
