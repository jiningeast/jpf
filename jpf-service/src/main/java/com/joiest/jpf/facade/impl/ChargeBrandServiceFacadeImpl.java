package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayChargeProductBrand;
import com.joiest.jpf.common.po.PayChargeProductBrandExample;
import com.joiest.jpf.common.po.PayShopBrand;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeProductBrandMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBrandMapper;
import com.joiest.jpf.dto.ChargeBrandRequest;
import com.joiest.jpf.dto.ShopBrandRequest;
import com.joiest.jpf.entity.ChargeProductBrandInfo;
import com.joiest.jpf.facade.ChargeBrandServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeBrandServiceFacadeImpl implements ChargeBrandServiceFacade {

    @Autowired
    private PayShopBrandMapper payShopBrandMapper;

    @Autowired
    private PayChargeProductBrandMapper  payChargeProductBrandMapper;

    @Override
    public List<ChargeProductBrandInfo> getShopBrandAllList() {

        PayChargeProductBrandExample example = new PayChargeProductBrandExample();
        example.setOrderByClause("addtime DESC");
        PayChargeProductBrandExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo((byte)1);
        List<PayChargeProductBrand> list = payChargeProductBrandMapper.selectByExample(example);

        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        List<ChargeProductBrandInfo> resultList = new ArrayList<>();
        for (PayChargeProductBrand one : list)
        {
            ChargeProductBrandInfo info = new ChargeProductBrandInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeProductBrand.class, ChargeProductBrandInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }

        return resultList;
    }

    @Override
    public JpfResponseDto addBrand(ChargeBrandRequest request) {
        if (StringUtils.isBlank(request.getBrandName()) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "品牌名称不能为空");
        }
        BeanCopier beanCopier = BeanCopier.create(ChargeBrandRequest.class, PayChargeProductBrand.class, false);
        PayChargeProductBrand info = new PayChargeProductBrand();
        beanCopier.copy(request, info, null);
        info.setStatus((byte)1);
        info.setAddtime(new Date());
        payChargeProductBrandMapper.insertSelective(info);
        return new JpfResponseDto();
    }
}
