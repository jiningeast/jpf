package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayChargeProductType;
import com.joiest.jpf.common.po.PayChargeProductTypeExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeProductTypeMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductTypeMapper;
import com.joiest.jpf.dto.ChargeProductTypeRequest;
import com.joiest.jpf.entity.ChargeProductTypeInfo;
import com.joiest.jpf.facade.ChargeProductTypeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeProductTypeServiceFacadeImpl implements ChargeProductTypeServiceFacade {

    @Autowired
    private PayShopProductTypeMapper payShopProductTypeMapper;

    @Autowired
    private PayChargeProductTypeMapper payChargeProductTypeMapper;

    @Override
    public List<ChargeProductTypeInfo> getAllShopProductTypeList() {
        PayChargeProductTypeExample example = new PayChargeProductTypeExample();
        example.setOrderByClause("addtime DESC");
        PayChargeProductTypeExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo((byte)1);
        List<PayChargeProductType> list = payChargeProductTypeMapper.selectByExample(example);

        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        List<ChargeProductTypeInfo> resultList = new ArrayList<>();
        for (PayChargeProductType one : list)
        {
            ChargeProductTypeInfo info = new ChargeProductTypeInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeProductType.class, ChargeProductTypeInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);

        }

        return resultList;
    }

    @Override
    public JpfResponseDto addShopProductType(ChargeProductTypeRequest request) {
        if (StringUtils.isBlank(request.getTypeName()) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "商品类型不能为空");
        }
        BeanCopier beanCopier = BeanCopier.create(ChargeProductTypeRequest.class, PayChargeProductType.class, false);
        PayChargeProductType info = new PayChargeProductType();
        beanCopier.copy(request, info, null);
        info.setStatus((byte)1);
        info.setAddtime(new Date());
        payChargeProductTypeMapper.insertSelective(info);
        return new JpfResponseDto();
    }
}
