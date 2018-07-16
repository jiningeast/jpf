package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudDfTask;
import com.joiest.jpf.common.po.PayCloudDfTaskExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfTaskMapper;
import com.joiest.jpf.dto.AddCloudDfTaskRequest;
import com.joiest.jpf.facade.CloudDfTaskInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

public class CloudDfTaskInterfaceServiceFacadeImpl implements CloudDfTaskInterfaceServiceFacade {

    @Autowired
    private PayCloudDfTaskMapper payCloudDfTaskMapper;
    //添加任务
    public int addTask(AddCloudDfTaskRequest request)
    {
        PayCloudDfTaskExample example = new PayCloudDfTaskExample();
        PayCloudDfTaskExample.Criteria c = example.createCriteria();

        PayCloudDfTask dfTask = new PayCloudDfTask();
        BeanCopier beanCopier = BeanCopier.create(AddCloudDfTaskRequest.class, PayCloudDfTask.class, false);
        beanCopier.copy(request, dfTask, null);
        int res = payCloudDfTaskMapper.insertSelective(dfTask);

        return res;
    }
}
