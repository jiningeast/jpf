package com.joiest.jpf.facade;

import com.joiest.jpf.dto.AddCloudDfTaskRequest;
import com.joiest.jpf.dto.GetCloudDfTaskInterfaceResponse;

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
}
