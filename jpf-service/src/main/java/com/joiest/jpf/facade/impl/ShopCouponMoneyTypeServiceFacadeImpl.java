package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCouponMoneyType;
import com.joiest.jpf.common.po.PayShopCouponMoneyTypeExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopCouponMoneyTypeCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponMoneyTypeMapper;
import com.joiest.jpf.facade.ShopCouponMoneyTypeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ShopCouponMoneyTypeServiceFacadeImpl implements ShopCouponMoneyTypeServiceFacade {

    @Autowired
    private PayShopCouponMoneyTypeMapper payShopCouponMoneyTypeMapper;

    @Autowired
    private PayShopCouponMoneyTypeCustomMapper payShopCouponMoneyTypeCustomMapper;

    @Override
    public List<PayShopCouponMoneyType> getList(PayShopCouponMoneyType payShopCouponMoneyType, long page, long rows) {
        PayShopCouponMoneyTypeExample example = new PayShopCouponMoneyTypeExample();
        PayShopCouponMoneyTypeExample.Criteria criteria = example.createCriteria();
        if(payShopCouponMoneyType.getMoney()!=null){
            criteria.andMoneyEqualTo(payShopCouponMoneyType.getMoney());
        }
        if(payShopCouponMoneyType.getStatus()!=null){
            criteria.andStatusEqualTo(payShopCouponMoneyType.getStatus());
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
    public int getCount(PayShopCouponMoneyType payShopCouponMoneyType) {
        PayShopCouponMoneyTypeExample example = new PayShopCouponMoneyTypeExample();
        PayShopCouponMoneyTypeExample.Criteria criteria = example.createCriteria();
        if(payShopCouponMoneyType.getMoney()!=null){
            criteria.andMoneyEqualTo(payShopCouponMoneyType.getMoney());
        }
        if(payShopCouponMoneyType.getStatus()!=null){
            criteria.andStatusEqualTo(payShopCouponMoneyType.getStatus());
        }

        return payShopCouponMoneyTypeMapper.countByExample(example);
    }

    @Override
    public void add(PayShopCouponMoneyType payShopCouponMoneyType) {
        payShopCouponMoneyType.setAddtime(new Date());
        payShopCouponMoneyType.setUseNum(0);
        payShopCouponMoneyTypeMapper.insertSelective(payShopCouponMoneyType);
    }

    @Override
    public List<PayShopCouponMoneyType> getByMoney(PayShopCouponMoneyType payShopCouponMoneyType) {
        PayShopCouponMoneyTypeExample example = new PayShopCouponMoneyTypeExample();
        PayShopCouponMoneyTypeExample.Criteria criteria = example.createCriteria();
        if(payShopCouponMoneyType.getMoney()!=null){
            criteria.andMoneyEqualTo(payShopCouponMoneyType.getMoney());
        }
        criteria.andStatusNotEqualTo((byte)3);
        return payShopCouponMoneyTypeMapper.selectByExample(example);
    }

    @Override
    public PayShopCouponMoneyType getById(String id) {
        return payShopCouponMoneyTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public void edit(PayShopCouponMoneyType payShopCouponMoneyType) {
        payShopCouponMoneyType.setUpdatetime(new Date());
        payShopCouponMoneyTypeMapper.updateByPrimaryKeySelective(payShopCouponMoneyType);
    }

    @Override
    public List<BigDecimal> getMoneyList() {
        return payShopCouponMoneyTypeCustomMapper.getMoneyList();
    }

    @Override
    public List<Map<String,Object>> getMoneyToMap() {
        return payShopCouponMoneyTypeCustomMapper.getMoneyToMap();
    }

    @Override
    public String addAndGetId(PayShopCouponMoneyType payShopCouponMoneyType) {
        payShopCouponMoneyType.setAddtime(new Date());
        payShopCouponMoneyType.setUseNum(0);
        payShopCouponMoneyTypeCustomMapper.insertSelective(payShopCouponMoneyType);
        return payShopCouponMoneyType.getId();
    }
}
