package com.joiest.jpf.facade;

import com.joiest.jpf.dto.AddCloudDfTaskRequest;
import com.joiest.jpf.dto.GetCloudDfTaskInterfaceResponse;
import com.joiest.jpf.entity.CloudDfTaskInterfaceInfo;

public interface CloudDfTaskInterfaceServiceFacade {

    /**
     * dfApi 添加任务
     * @param request
     * @return
     */
    public int addTask(AddCloudDfTaskRequest request);

    /**
     * 获取当前可执行的任务列表
     * @return
     */
    public GetCloudDfTaskInterfaceResponse getCanableTaskList();

    /**
     * 更新任务状态等信息 by PrimaryKey
     * @param info
     * @return
     */
    public int updateTask(CloudDfTaskInterfaceInfo info);

    /**
     * 更新任务状态等信息 by example
     * @param info
     * @return
     */
    public int updateTaskByExample(CloudDfTaskInterfaceInfo info);

}
