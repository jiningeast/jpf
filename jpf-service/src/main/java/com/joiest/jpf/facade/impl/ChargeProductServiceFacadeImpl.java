package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.common.po.PayChargeProductExample;
import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeProductMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeProductMapper;
import com.joiest.jpf.facade.ChargeProductServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class ChargeProductServiceFacadeImpl implements ChargeProductServiceFacade {

    @Autowired
    private PayChargeProductMapper payChargeProductMapper;

    /**
     * 欣豆日志添加
     * */
    @Override
    public List<PayChargeProduct> getOne(PayChargeProduct record){

        PayChargeProductExample example = new PayChargeProductExample();
        PayChargeProductExample.Criteria c = example.createCriteria();
        if( record.getMobileType() !=null && !record.getMobileType().toString().equals("") ){
            c.andMobileTypeEqualTo(record.getMobileType());
        }

        List<PayChargeProduct> list = payChargeProductMapper.selectByExample(example);
        if( list==null || list.isEmpty() ){
            return null;
        }
        List<PayChargeProduct> infoList = payChargeProductMapper.selectByExample(example);
        for (PayChargeProduct one:infoList){
            //ChargeProductInfo info = new ChargeProductInfo();
            //BeanCopier beanCopier = BeanCopier.create(PayChargeProduct.class,ChargeProductInfo.class,false);
            //beanCopier.copy(list,infoList,null);
        }

        return infoList;
    }


}
