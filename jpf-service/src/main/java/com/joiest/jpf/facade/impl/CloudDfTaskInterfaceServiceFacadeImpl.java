package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudDfTask;
import com.joiest.jpf.common.po.PayCloudDfTaskExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfTaskMapper;
import com.joiest.jpf.dto.AddCloudDfTaskRequest;
import com.joiest.jpf.dto.GetCloudDfTaskInterfaceResponse;
import com.joiest.jpf.entity.CloudDfTaskInterfaceInfo;
import com.joiest.jpf.facade.CloudDfTaskInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class CloudDfTaskInterfaceServiceFacadeImpl implements CloudDfTaskInterfaceServiceFacade {

    @Autowired
    private PayCloudDfTaskMapper payCloudDfTaskMapper;

    /**
     * dfApi 添加任务
     * @param request
     * @return
     */
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

    /**
     * 获取当前可执行的任务列表
     * @return
     */
    public GetCloudDfTaskInterfaceResponse getCanableTaskList()
    {
        PayCloudDfTaskExample example = new PayCloudDfTaskExample();
        PayCloudDfTaskExample.Criteria c = example.createCriteria();
        //数据写入完成
        c.andInsertStatusEqualTo(2);
        //未执行的
        c.andStatusEqualTo(0);
        List<PayCloudDfTask> list = payCloudDfTaskMapper.selectByExample(example);
        List<CloudDfTaskInterfaceInfo> responseList = new ArrayList<>();
        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        for (PayCloudDfTask one : list)
        {
            CloudDfTaskInterfaceInfo info = new CloudDfTaskInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfTask.class, CloudDfTaskInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            responseList.add(info);
        }
        int count = payCloudDfTaskMapper.countByExample(example);
        GetCloudDfTaskInterfaceResponse response = new GetCloudDfTaskInterfaceResponse();
        response.setCount(count);
        response.setList(responseList);

        return response;
    }

    public int updateTask(CloudDfTaskInterfaceInfo info)
    {
        PayCloudDfTask payCloudDfTask = new PayCloudDfTask();
        BeanCopier beanCopier = BeanCopier.create(CloudDfTaskInterfaceInfo.class , PayCloudDfTask.class, false);
        beanCopier.copy(info, payCloudDfTask, null);

        return payCloudDfTaskMapper.updateByPrimaryKeySelective(payCloudDfTask);
    }

    public int updateTaskByExample(CloudDfTaskInterfaceInfo info)
    {
        PayCloudDfTask payCloudDfTask = new PayCloudDfTask();
        PayCloudDfTaskExample example = new PayCloudDfTaskExample();
        PayCloudDfTaskExample.Criteria c = example.createCriteria();
        BeanCopier beanCopier = BeanCopier.create(CloudDfTaskInterfaceInfo.class, PayCloudDfTask.class, false);
        beanCopier.copy(info, payCloudDfTask, null);
        c.andBatchidEqualTo(info.getBatchid());

        return  payCloudDfTaskMapper.updateByExampleSelective(payCloudDfTask,example);
    }
}
