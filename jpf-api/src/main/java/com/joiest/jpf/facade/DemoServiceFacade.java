package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetValueRequest;
import com.joiest.jpf.dto.GetValueResponse;

public interface DemoServiceFacade {

    /**
     * 根据code获取参数value
     * @param request
     * @return
     */
    public GetValueResponse getValue(GetValueRequest request);
}
