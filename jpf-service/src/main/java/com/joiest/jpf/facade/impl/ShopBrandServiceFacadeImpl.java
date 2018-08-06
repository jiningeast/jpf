package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopBrand;
import com.joiest.jpf.common.po.PayShopBrandExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBrandMapper;
import com.joiest.jpf.dto.ShopBrandRequest;
import com.joiest.jpf.entity.ShopBrandInfo;
import com.joiest.jpf.facade.ShopBrandServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopBrandServiceFacadeImpl implements ShopBrandServiceFacade {

    @Autowired
    private PayShopBrandMapper payShopBrandMapper;

    @Override
    public List<ShopBrandInfo> getShopBrandAllList() {

        PayShopBrandExample example = new PayShopBrandExample();
        example.setOrderByClause("addtime DESC");
        PayShopBrandExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo((byte)1);
        List<PayShopBrand> list = payShopBrandMapper.selectByExample(example);

        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        List<ShopBrandInfo> resultList = new ArrayList<>();
        for (PayShopBrand one : list)
        {
            ShopBrandInfo info = new ShopBrandInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopBrand.class, ShopBrandInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }

        return resultList;
    }

    @Override
    public JpfResponseDto addBrand(ShopBrandRequest request) {
        if (StringUtils.isBlank(request.getBrandName()) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "供应商名称不能为空");
        }
        BeanCopier beanCopier = BeanCopier.create(ShopBrandRequest.class, PayShopBrand.class, false);
        PayShopBrand info = new PayShopBrand();
        beanCopier.copy(request, info, null);
        info.setStatus((byte)1);
        info.setAddtime(new Date());
        payShopBrandMapper.insertSelective(info);
        return new JpfResponseDto();
    }
}
