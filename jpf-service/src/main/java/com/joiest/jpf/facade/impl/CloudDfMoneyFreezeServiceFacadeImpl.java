package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudDfMoneyFreeze;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfMoneyFreezeMapper;
import com.joiest.jpf.entity.CloudDfMoneyFreezeInfo;
import com.joiest.jpf.facade.CloudDfMoneyFreezeServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class CloudDfMoneyFreezeServiceFacadeImpl implements CloudDfMoneyFreezeServiceFacade {

    @Autowired
    private PayCloudDfMoneyFreezeMapper payCloudDfMoneyFreezeMapper;

    @Override
    public int add(CloudDfMoneyFreezeInfo freezeInfo) {
        PayCloudDfMoneyFreeze payCloudDfMoneyFreeze = new PayCloudDfMoneyFreeze();
        BeanCopier beanCopier = BeanCopier.create(CloudDfMoneyFreezeInfo.class, PayCloudDfMoneyFreeze.class, false);
        beanCopier.copy(freezeInfo, payCloudDfMoneyFreeze, null);

        return payCloudDfMoneyFreezeMapper.insertSelective(payCloudDfMoneyFreeze);
    }
}
