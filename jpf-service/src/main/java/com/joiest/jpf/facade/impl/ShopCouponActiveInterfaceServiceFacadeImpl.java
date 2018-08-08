package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopCouponActive;
import com.joiest.jpf.common.po.PayShopCouponActiveExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponActiveMapper;
import com.joiest.jpf.dto.GetUserCouponActiveInterfaceResponse;
import com.joiest.jpf.entity.ShopCouponActiveInterfaceInfo;
import com.joiest.jpf.facade.ShopCouponActiveInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopCouponActiveInterfaceServiceFacadeImpl implements ShopCouponActiveInterfaceServiceFacade {

    @Autowired
    private PayShopCouponActiveMapper payShopCouponActiveMapper;

    @Override
    public GetUserCouponActiveInterfaceResponse getUserCouponList(String uid) {
        if ( StringUtils.isBlank(uid) )
        {
            return null;
        }
        PayShopCouponActiveExample example = new PayShopCouponActiveExample();
        example.setOrderByClause("addtime ASC");
        PayShopCouponActiveExample.Criteria c = example.createCriteria();
        c.andCustomerIdEqualTo(uid);
        c.andTypeEqualTo("0");
        List<PayShopCouponActive> list = payShopCouponActiveMapper.selectByExample(example);
        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        List<ShopCouponActiveInterfaceInfo> resultList = new ArrayList<>();
        int dou_able = 0;
        for (PayShopCouponActive one : list)
        {
            ShopCouponActiveInterfaceInfo info = new ShopCouponActiveInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCouponActive.class, ShopCouponActiveInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
            dou_able += one.getDou();
        }
        GetUserCouponActiveInterfaceResponse response = new GetUserCouponActiveInterfaceResponse();
        response.setList(resultList);
        int count = payShopCouponActiveMapper.countByExample(example);
        response.setCount(count);
        response.setDouTotal(dou_able);
        return response;
    }
    @Override
    public GetUserCouponActiveInterfaceResponse getUserCouponListAndPage(String uid,String pageNo,String pageSize ) {
        if ( StringUtils.isBlank(uid) )
        {
            return null;
        }

        PayShopCouponActiveExample example = new PayShopCouponActiveExample();

        if(StringUtils.isBlank(pageNo) || pageNo==null){
            example.setPageNo(1);
        }else {
            example.setPageNo(Long.parseLong(pageNo));
        }
        if(StringUtils.isBlank(pageSize) || pageSize==null){
            example.setPageSize(10);
        }else{
            example.setPageSize(Long.parseLong(pageSize));
        }

        example.setOrderByClause("addtime ASC");
        PayShopCouponActiveExample.Criteria c = example.createCriteria();
        c.andCustomerIdEqualTo(uid);
        c.andTypeEqualTo("0");

        List<PayShopCouponActive> list = payShopCouponActiveMapper.selectByExample(example);
        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        List<ShopCouponActiveInterfaceInfo> resultList = new ArrayList<>();
        int dou_able = 0;
        for (PayShopCouponActive one : list)
        {
            ShopCouponActiveInterfaceInfo info = new ShopCouponActiveInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCouponActive.class, ShopCouponActiveInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
            dou_able += one.getDou();
        }
        GetUserCouponActiveInterfaceResponse response = new GetUserCouponActiveInterfaceResponse();
        response.setList(resultList);
        int count = payShopCouponActiveMapper.countByExample(example);
        response.setCount(count);
        response.setDouTotal(dou_able);
        return response;
    }
}
