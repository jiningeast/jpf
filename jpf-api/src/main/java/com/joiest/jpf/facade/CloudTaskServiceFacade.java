package com.joiest.jpf.facade;

import com.joiest.jpf.dto.CloudTaskRequest;
import com.joiest.jpf.dto.CloudTaskResponse;

import java.util.List;

public interface CloudTaskServiceFacade {

    public CloudTaskResponse getTasks(CloudTaskRequest request);
}
