package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayChargeInterfaceStream;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeInterfaceStreamMapper;
import com.joiest.jpf.entity.ChargeInterfaceStreamInfo;
import com.joiest.jpf.facade.ChargeInterfaceStreamFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class ChargeInterfaceStreamFacadeImpl implements ChargeInterfaceStreamFacade {

    @Autowired
    private PayChargeInterfaceStreamMapper payChargeInterfaceStreamMapper;
    /*
     * 添加流水
     * */
    public int addStream(ChargeInterfaceStreamInfo streamInfo){

        PayChargeInterfaceStream payChargeInterfaceStream = new PayChargeInterfaceStream();
        BeanCopier beanCopier = BeanCopier.create(ChargeInterfaceStreamInfo.class,PayChargeInterfaceStream.class,false);
        beanCopier.copy(streamInfo,payChargeInterfaceStream,null);

        return payChargeInterfaceStreamMapper.insertSelective(payChargeInterfaceStream);
    }
}
