package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayMerchants;
import com.joiest.jpf.common.po.PayMerchantsAgent;
import com.joiest.jpf.common.po.PayMerchantsAgentExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsAgentMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsMapper;
import com.joiest.jpf.dto.ModifyAgentRequest;
import com.joiest.jpf.entity.MerchantAgentInfo;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.facade.MerAgentServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MerAgentServiceFacadeImpl implements MerAgentServiceFacade{

    @Autowired
    private PayMerchantsAgentMapper payMerchantsAgentMapper;

    @Autowired
    private PayMerchantsMapper payMerchantsMapper;

    /**
     * 获取单个商户等级信息
     * @param mtsid
     * @return
     */
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
     */
    @Override
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

    /**
     * 添加|修改商户代理信息
     */
    @Override
    public JpfResponseDto modifyAgent(ModifyAgentRequest request)
    {
        Byte levelOne = 2;  //一级
        String superioridNew;   //新路径
        //区分新增or编辑
        //add
        if ( request.getId() == null )
        {
            //获取上级商户等级路径 2种情况 : 1级代理 和 其它代理
            if ( Byte.valueOf(request.getTpid()) == levelOne )
            {
                //1级代理
                superioridNew = request.getMtsid() + ":";
            } else
            {
                if ( request.getMtsidBelong() == null )
                {
                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "上级商户ID不能为空");
                }
                //其它代理
                PayMerchantsAgentExample example = new PayMerchantsAgentExample();
                PayMerchantsAgentExample.Criteria c = example.createCriteria();
                c.andMtsidEqualTo(Long.valueOf(request.getMtsidBelong()));
                List<PayMerchantsAgent> agentList;
                //获取上级商户等级信息
                agentList = payMerchantsAgentMapper.selectByExample(example);
                if ( agentList == null || agentList.isEmpty() )
                {
                    throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "上级商户信息不存在");
                }
                String superiorid = agentList.get(0).getSuperiorid();
                superioridNew = superiorid + request.getMtsid() + ':';

            }
            PayMerchantsAgent payMerchantsAgent = new PayMerchantsAgent();
            BeanCopier beanCopier = BeanCopier.create(ModifyAgentRequest.class, PayMerchantsAgent.class, false);
            beanCopier.copy(request,payMerchantsAgent,null);
            payMerchantsAgent.setCreated(new Date());
            payMerchantsAgent.setSuperiorid(superioridNew);
            //新增
            int res = payMerchantsAgentMapper.insertSelective(payMerchantsAgent);
            if ( res != 1 )
            {
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "添加失败");
            }

        } else
        {
            //编辑
            //获取上级商户等级路径 2种情况 : 1级代理 和 其它代理
            if ( Byte.valueOf(request.getTpid()) == levelOne )
            {
                //1级代理
                superioridNew = request.getMtsid() + ":";
            }else
            {
                if ( request.getMtsidBelong() == null )
                {
                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "上级商户ID不能为空");
                }
                //获取上级商户path
                PayMerchantsAgentExample example = new PayMerchantsAgentExample();
                PayMerchantsAgentExample.Criteria c = example.createCriteria();
                c.andMtsidEqualTo(Long.valueOf(request.getMtsidBelong()));
                List<PayMerchantsAgent> agentList = payMerchantsAgentMapper.selectByExample(example);
                if ( agentList == null || agentList.isEmpty() )
                {
                    throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "上级商户信息不存在");
                }
                String superiorid = agentList.get(0).getSuperiorid();
                superioridNew = superiorid + request.getMtsid() + ":";
            }

            PayMerchantsAgentExample exampleUpdate = new PayMerchantsAgentExample();
            PayMerchantsAgentExample.Criteria cUpdate = exampleUpdate.createCriteria();
            cUpdate.andIdEqualTo(request.getId());  //等级表ID
            PayMerchantsAgent payMerchantsAgent = new PayMerchantsAgent();
            BeanCopier beanCopier = BeanCopier.create(ModifyAgentRequest.class, PayMerchantsAgent.class, false);
            beanCopier.copy(request, payMerchantsAgent, null);
            payMerchantsAgent.setSuperiorid(superioridNew);//赋值path
            payMerchantsAgent.setUpdated(new Date());
            int res = payMerchantsAgentMapper.updateByExampleSelective(payMerchantsAgent,exampleUpdate);
            if ( res != 1 )
            {
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新失败");
            }
        }
        return new JpfResponseDto();
    }
}
