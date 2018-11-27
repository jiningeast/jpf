package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopSupplier;
import com.joiest.jpf.common.po.PayShopSupplierExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopSupplierMapper;
import com.joiest.jpf.dto.ShopSupplierRequest;
import com.joiest.jpf.entity.ShopSupplierInfo;
import com.joiest.jpf.facade.ShopSupplierServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopSupplierServiceFacadeImpl implements ShopSupplierServiceFacade {

    @Autowired
    private PayShopSupplierMapper payShopSupplierMapper;

    @Override
    public List<ShopSupplierInfo> getShopSupplierList() {
        PayShopSupplierExample example = new PayShopSupplierExample();
        example.setOrderByClause("addtime DESC");
        PayShopSupplierExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo((byte)1);
        List<PayShopSupplier> list = payShopSupplierMapper.selectByExample(example);
        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        List<ShopSupplierInfo> resultList = new ArrayList<>();

        for ( PayShopSupplier one : list )
        {
            ShopSupplierInfo info = new ShopSupplierInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopSupplier.class, ShopSupplierInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }

        return resultList;
    }

    @Override
    public JpfResponseDto addShopProductSupplier(ShopSupplierRequest request) {
        if (StringUtils.isBlank(request.getSupplierName()) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "供应商名称不能为空");
        }
        BeanCopier beanCopier = BeanCopier.create(ShopSupplierRequest.class, PayShopSupplier.class, false);
        PayShopSupplier info = new PayShopSupplier();
        beanCopier.copy(request, info, null);
        info.setStatus((byte)1);
        info.setAddtime(new Date());
        payShopSupplierMapper.insertSelective(info);
        return new JpfResponseDto();
    }

    @Override
    public JpfResponseDto editShopProductSupplier(ShopSupplierRequest request) {
        if (StringUtils.isBlank(request.getSupplierName()) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "供应商名称不能为空");
        }
        if(StringUtils.isBlank(request.getId().toString()) ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "供应商ID不能为空");
        }
        PayShopSupplier info = new PayShopSupplier();
        info.setSupplierName(request.getSupplierName());
        info.setUpdatetime(new Date());
        info.setId(request.getId());
        payShopSupplierMapper.updateByPrimaryKeySelective(info);
        return new JpfResponseDto();
    }

}
