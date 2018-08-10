package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.po.PayShopCustomerExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopCustomerCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCustomerMapper;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopCustomerInterfaceServiceFacadeImpl implements ShopCustomerInterfaceServiceFacade {

    @Autowired
    private PayShopCustomerMapper payShopCustomerMapper;

    @Autowired
    private PayShopCustomerCustomMapper payShopCustomerCustomMapper;

    @Override
    public ShopCustomerInterfaceInfo getCustomer(String uid) {
       if ( StringUtils.isBlank(uid) )
       {
           return null;
       }
        PayShopCustomerExample example = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = example.createCriteria();
        PayShopCustomer payShopCustomer = payShopCustomerMapper.selectByPrimaryKey(uid);
        if ( payShopCustomer == null )
        {
            return null;
        }
        ShopCustomerInterfaceInfo info = new ShopCustomerInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopCustomer.class, ShopCustomerInterfaceInfo.class, false);
        beanCopier.copy(payShopCustomer, info, null);

        return info;
    }

    @Override
    public List<ShopCustomerInterfaceInfo> getCustomerByOpenId(String openId) {
        if ( StringUtils.isBlank(openId) )
        {
            return null;
        }
        PayShopCustomerExample example = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = example.createCriteria();
        c.andOpenidEqualTo(openId);
        List<PayShopCustomer> list = payShopCustomerMapper.selectByExample(example);
        if ( list == null || list.size()<=0  )
        {
            return null;
        }
        List<ShopCustomerInterfaceInfo> infos = new ArrayList<>();
        for(PayShopCustomer payShopCustomer:list){
            ShopCustomerInterfaceInfo shopCustomerInterfaceInfo = new ShopCustomerInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCustomer.class, ShopCustomerInterfaceInfo.class, false);
            beanCopier.copy(payShopCustomer,shopCustomerInterfaceInfo, null);

            infos.add(shopCustomerInterfaceInfo);
        }

        return infos;
    }

    @Override
    public List<ShopCustomerInterfaceInfo> getCustomerByPhone(String phone) {
        if ( StringUtils.isBlank(phone) )
        {
            return null;
        }
        PayShopCustomerExample example = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = example.createCriteria();
        c.andPhoneEqualTo(phone);
        List<PayShopCustomer> list = payShopCustomerMapper.selectByExample(example);
        if ( list == null || list.size()<=0 )
        {
            return null;
        }
        List<ShopCustomerInterfaceInfo> infos = new ArrayList<>();
        for(PayShopCustomer payShopCustomer:list){
            ShopCustomerInterfaceInfo shopCustomerInterfaceInfo = new ShopCustomerInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCustomer.class, ShopCustomerInterfaceInfo.class, false);
            beanCopier.copy(payShopCustomer,shopCustomerInterfaceInfo, null);

            infos.add(shopCustomerInterfaceInfo);
        }


        return infos;
    }

    @Override
    public JpfResponseDto updateCustomerByOpenId(PayShopCustomer record,String openId) {
        JpfResponseDto responseDto = new JpfResponseDto();

        PayShopCustomerExample example = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = example.createCriteria();
        c.andOpenidEqualTo(openId);
        int count = payShopCustomerMapper.updateByExampleSelective(record,example);
        if ( count != 1  )
        {
            responseDto.setRetCode("0001"); //更新失败
        }

        return responseDto;

    }

    @Override
    public String addCustomer(PayShopCustomer record) {
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        if(record == null ){
            return null;
        }
        int count = payShopCustomerCustomMapper.insertSelective(record);
        if( count <= 0 ){
            return null;
        }


        return record.getId().toString();
    }

}
