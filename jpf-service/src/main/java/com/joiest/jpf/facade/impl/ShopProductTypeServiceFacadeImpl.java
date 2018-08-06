package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopProductType;
import com.joiest.jpf.common.po.PayShopProductTypeExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductTypeMapper;
import com.joiest.jpf.dto.ShopProductTypeRequest;
import com.joiest.jpf.entity.ShopProductTypeInfo;
import com.joiest.jpf.facade.ShopProductTypeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopProductTypeServiceFacadeImpl implements ShopProductTypeServiceFacade {

    @Autowired
    private PayShopProductTypeMapper payShopProductTypeMapper;

    @Override
    public List<ShopProductTypeInfo> getAllShopProductTypeList() {
        PayShopProductTypeExample example = new PayShopProductTypeExample();
        example.setOrderByClause("addtime DESC");
        PayShopProductTypeExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo((byte)1);
        List<PayShopProductType> list = payShopProductTypeMapper.selectByExample(example);

        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        List<ShopProductTypeInfo> resultList = new ArrayList<>();
        for (PayShopProductType one : list)
        {
            ShopProductTypeInfo info = new ShopProductTypeInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopProductType.class, ShopProductTypeInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }

        return resultList;
    }

    @Override
    public JpfResponseDto addShopProductType(ShopProductTypeRequest request) {
        if (StringUtils.isBlank(request.getPname()) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "供应商名称不能为空");
        }
        BeanCopier beanCopier = BeanCopier.create(ShopProductTypeRequest.class, PayShopProductType.class, false);
        PayShopProductType info = new PayShopProductType();
        beanCopier.copy(request, info, null);
        info.setStatus((byte)1);
        info.setAddtime(new Date());
        payShopProductTypeMapper.insertSelective(info);
        return new JpfResponseDto();
    }
}
