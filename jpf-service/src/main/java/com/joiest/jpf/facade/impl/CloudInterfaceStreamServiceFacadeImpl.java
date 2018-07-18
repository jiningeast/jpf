package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudInterfaceStream;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudInterfaceStreamMapper;
import com.joiest.jpf.entity.CloudInterfaceStreamInfo;
import com.joiest.jpf.facade.CloudInterfaceStreamServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class CloudInterfaceStreamServiceFacadeImpl implements CloudInterfaceStreamServiceFacade {

    @Autowired
    PayCloudInterfaceStreamMapper payCloudInterfaceStreamMapper;

    /**
     * 新建流水记录
     */
    public int insRecord(CloudInterfaceStreamInfo cloudInterfaceStreamInfo){
        PayCloudInterfaceStream payCloudInterfaceStream = new PayCloudInterfaceStream();
        BeanCopier beanCopier = BeanCopier.create(CloudInterfaceStreamInfo.class, PayCloudInterfaceStream.class, false);
        beanCopier.copy(cloudInterfaceStreamInfo, payCloudInterfaceStream, null);

        return payCloudInterfaceStreamMapper.insertSelective(payCloudInterfaceStream);
    }
}
