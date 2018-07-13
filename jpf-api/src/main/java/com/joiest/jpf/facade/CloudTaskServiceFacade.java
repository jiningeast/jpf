package com.joiest.jpf.facade;

import com.joiest.jpf.dto.CloudTaskRequest;
import com.joiest.jpf.dto.CloudTaskResponse;
import com.joiest.jpf.entity.CloudTaskInfo;


public interface CloudTaskServiceFacade {

    /**
     * 获取任务记录
     */
    public CloudTaskResponse getTasks(CloudTaskRequest request);

    /**
     * 新建任务记录
     */
    public int insTask(CloudTaskInfo cloudTaskInfo);

    /**
     * 获取单条任务记录
     */
    public CloudTaskInfo getOneTask(String id);
}
