package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopAd;
import com.joiest.jpf.common.po.PayShopAdExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopAdMapper;
import com.joiest.jpf.dto.GetShopAdInterfaceRequest;
import com.joiest.jpf.dto.GetShopAdInterfaceResponse;
import com.joiest.jpf.dto.GetShopAdResponse;
import com.joiest.jpf.entity.ShopAdInfo;
import com.joiest.jpf.facade.ShopAdInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopAdInterfaceServiceFacadeImpl implements ShopAdInterfaceServiceFacade {

    @Autowired
    private PayShopAdMapper payShopAdMapper;


    @Override
    public GetShopAdInterfaceResponse getList(GetShopAdInterfaceRequest GetShopAdRequest){
        GetShopAdInterfaceResponse getShopAdInterfaceResponse = new GetShopAdInterfaceResponse();

        PayShopAdExample e = new PayShopAdExample();
        PayShopAdExample.Criteria c = e.createCriteria();

        if ( GetShopAdRequest.getType() != null &&  StringUtils.isNotBlank(GetShopAdRequest.getType()) ){
            c.andTypeEqualTo(Byte.parseByte(GetShopAdRequest.getType()));
        }

        //e.setPageSize(Long.parseLong(GetShopAdRequest.getPageSize()));
        //e.setPageNo(Long.parseLong(GetShopAdRequest.getPage()));
        e.setOrderByClause("weight DESC");

        List<PayShopAd> list = payShopAdMapper.selectByExample(e);
        getShopAdInterfaceResponse.setCount(payShopAdMapper.countByExample(e));
        List<ShopAdInfo> infos = new ArrayList<>();
        if ( !list.isEmpty() ){
            for (PayShopAd payShopAd:list){
                ShopAdInfo shopAdInfo = new ShopAdInfo();
                BeanCopier beanCopier = BeanCopier.create(PayShopAd.class, ShopAdInfo.class, false);
                beanCopier.copy(payShopAd, shopAdInfo, null);

                infos.add(shopAdInfo);
            }
        }

        getShopAdInterfaceResponse.setList(infos);

        return getShopAdInterfaceResponse;
    }


}
