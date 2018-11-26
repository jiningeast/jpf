package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCouponMoneyType;
import com.joiest.jpf.common.po.PayShopCouponMoneyTypeExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponMoneyTypeMapper;
import com.joiest.jpf.facade.ShopCouponMoneyTypeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class ShopCouponMoneyTypeServiceFacadeImpl implements ShopCouponMoneyTypeServiceFacade {

    @Autowired
    private PayShopCouponMoneyTypeMapper payShopCouponMoneyTypeMapper;

    @Override
    public List<PayShopCouponMoneyType> getList(String money, byte status, long page, long rows) {
        PayShopCouponMoneyTypeExample example = new PayShopCouponMoneyTypeExample();
        PayShopCouponMoneyTypeExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(money)){
            criteria.andMoneyEqualTo(new BigDecimal(money));
        }
        if((Byte)status!=null){
            criteria.andStatusEqualTo(status);
        }
        if (page<=0) {
            page = 1;
        }
        if (rows<=0) {
            rows = 20;
        }
        example.setPageNo(page);
        example.setPageSize(rows);
        return payShopCouponMoneyTypeMapper.selectByExample(example);
    }

    @Override
    public int getCount(String money, byte status) {
        PayShopCouponMoneyTypeExample example = new PayShopCouponMoneyTypeExample();
        PayShopCouponMoneyTypeExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(money)){
            criteria.andMoneyEqualTo(new BigDecimal(money));
        }
        if((Byte)status!=null){
            criteria.andStatusEqualTo(status);
        }

        return payShopCouponMoneyTypeMapper.countByExample(example);
    }

    @Override
    public JpfResponseDto add(PayShopCouponMoneyType payShopCouponMoneyType) {
        return  new JpfResponseDto();
    }
}
