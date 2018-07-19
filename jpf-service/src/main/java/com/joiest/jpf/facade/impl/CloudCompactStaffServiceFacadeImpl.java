package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudCompactStaff;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompactStaffMapper;
import com.joiest.jpf.entity.CloudCompactStaffInfo;
import com.joiest.jpf.facade.CloudCompactStaffServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class CloudCompactStaffServiceFacadeImpl implements CloudCompactStaffServiceFacade {

    @Autowired
    private PayCloudCompactStaffMapper payCloudCompactStaffMapper;

    /**
     * 插入一条记录
     */
    @Override
    public int insRecord(CloudCompactStaffInfo cloudCompactStaffInfo){
        PayCloudCompactStaff payCloudCompactStaff = new PayCloudCompactStaff();

        BeanCopier beanCopier = BeanCopier.create(CloudCompactStaffInfo.class, PayCloudCompactStaff.class, false);
        beanCopier.copy(cloudCompactStaffInfo, payCloudCompactStaff, null);

        return payCloudCompactStaffMapper.insert(payCloudCompactStaff);
    }
}
