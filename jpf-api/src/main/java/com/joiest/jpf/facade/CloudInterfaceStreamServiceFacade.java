package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudInterfaceStreamInfo;

public interface CloudInterfaceStreamServiceFacade {

    /**
     * 新建流水记录
     */
    public int insRecord(CloudInterfaceStreamInfo cloudInterfaceStreamInfo);
}
