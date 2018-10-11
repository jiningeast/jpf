package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayChargeProductSupplier;
import com.joiest.jpf.common.po.PayChargeProductSupplierExample;
import com.joiest.jpf.common.po.PayShopSupplier;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeProductSupplierMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopSupplierMapper;
import com.joiest.jpf.dto.ChargeSupplierRequest;
import com.joiest.jpf.dto.ShopSupplierRequest;
import com.joiest.jpf.entity.ChargeProductSupplierInfo;
import com.joiest.jpf.facade.ChargeSupplierServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeSupplierServiceFacadeImpl implements ChargeSupplierServiceFacade {

    @Autowired
    private PayShopSupplierMapper payShopSupplierMapper;


    @Autowired
    private PayChargeProductSupplierMapper payChargeProductSupplierMapper;

    @Override
    public List<ChargeProductSupplierInfo> getShopSupplierList() {
        PayChargeProductSupplierExample example = new PayChargeProductSupplierExample();
        example.setOrderByClause("addtime DESC");
        PayChargeProductSupplierExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo((byte)1);
        List<PayChargeProductSupplier> list = payChargeProductSupplierMapper.selectByExample(example);
        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        List<ChargeProductSupplierInfo> resultList = new ArrayList<>();

        for ( PayChargeProductSupplier one : list )
        {
            ChargeProductSupplierInfo info = new ChargeProductSupplierInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeProductSupplier.class, ChargeProductSupplierInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }

        return resultList;
    }

    @Override
    public JpfResponseDto addShopProductSupplier(ChargeSupplierRequest request) {
        if (StringUtils.isBlank(request.getSupplierName()) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "供应商名称不能为空");
        }
        BeanCopier beanCopier = BeanCopier.create(ChargeSupplierRequest.class, PayChargeProductSupplier.class, false);
        PayChargeProductSupplier info = new PayChargeProductSupplier();
        beanCopier.copy(request, info, null);
        info.setStatus((byte)1);
        info.setAddtime(new Date());
        payChargeProductSupplierMapper.insertSelective(info);
        return new JpfResponseDto();
    }
}
