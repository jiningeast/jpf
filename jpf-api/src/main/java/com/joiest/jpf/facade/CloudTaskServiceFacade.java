package com.joiest.jpf.facade;

import com.joiest.jpf.dto.CloudTaskRequest;
import com.joiest.jpf.dto.CloudTaskResponse;
import com.joiest.jpf.entity.CloudTaskInfo;

import java.util.List;


public interface CloudTaskServiceFacade {

    /**
     * 获取任务记录
     */
    public CloudTaskResponse getTasks(CloudTaskRequest request);

    /**
     * 新建任务记录
     */
    public String insTask(CloudTaskInfo cloudTaskInfo);

    /**
     * 获取单条任务记录
     */
    public CloudTaskInfo getOneTask(String id);

    /**
     * 更新任务记录
     */
    public int updateColumn(CloudTaskInfo cloudTaskInfo);

    /**
     * 根据合同号获取任务
     */
    public CloudTaskInfo getOneTaskByBatchNo(String batchNo);
}
