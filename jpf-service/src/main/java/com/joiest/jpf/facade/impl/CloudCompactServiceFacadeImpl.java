package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudCompact;
import com.joiest.jpf.common.po.PayCloudCompactExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompactMapper;
import com.joiest.jpf.entity.CloudCompactInfo;
import com.joiest.jpf.facade.CloudCompactServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class CloudCompactServiceFacadeImpl implements CloudCompactServiceFacade {

    @Autowired
    private PayCloudCompactMapper payCloudCompactMapper;

    /**
     * 根据主键id查询记录
     */
    @Override
    public CloudCompactInfo getRecById(String id){
        PayCloudCompact payCloudCompact = payCloudCompactMapper.selectByPrimaryKey(id);
        CloudCompactInfo cloudCompactInfo = new CloudCompactInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudCompact.class, CloudCompactInfo.class, false);
        beanCopier.copy(payCloudCompact, cloudCompactInfo, null);

        return cloudCompactInfo;
    }

    /**
     * 根据type类型字段查询记录
     */
    public CloudCompactInfo getRecByType(byte type){
        PayCloudCompactExample e = new PayCloudCompactExample();
        PayCloudCompactExample.Criteria c = e.createCriteria();
        c.andTypeEqualTo(type);
        List<PayCloudCompact> list = payCloudCompactMapper.selectByExample(e);
        CloudCompactInfo cloudCompactInfo = new CloudCompactInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudCompact.class, CloudCompactInfo.class, false);
        beanCopier.copy(list.get(0), cloudCompactInfo, null);

        return cloudCompactInfo;
    }
}
