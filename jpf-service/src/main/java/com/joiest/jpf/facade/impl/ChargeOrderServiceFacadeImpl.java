package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.po.PayChargeOrderExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeOrderMapper;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class ChargeOrderServiceFacadeImpl implements ChargeOrderServiceFacade {

    @Autowired
    private PayChargeOrderMapper payChargeOrderMapper;

    /**
     * 商品列表
     * */
    @Override
    public ChargeOrderInfo getOne(PayChargeOrder record){

        PayChargeOrderExample example = new PayChargeOrderExample();
        PayChargeOrderExample.Criteria c = example.createCriteria();
        if( StringUtils.isNotBlank(record.getForeignOrderNo()) ){
            c.andForeignOrderNoEqualTo(record.getForeignOrderNo());
        }
        if( StringUtils.isNotBlank(record.getOrderNo()) ){
            c.andOrderNoEqualTo(record.getOrderNo());
        }
        if( StringUtils.isNotBlank(record.getMerchNo()) ){
            c.andMerchNoEqualTo(record.getMerchNo());
        }

        List<PayChargeOrder> list = payChargeOrderMapper.selectByExample(example);
        if( list==null || list.isEmpty() ){
            return null;
        }

        ChargeOrderInfo info = new ChargeOrderInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeOrder.class,ChargeOrderInfo.class,false);
        beanCopier.copy(list.get(0),info,null);

        return info;
    }


}
