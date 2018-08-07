package com.joiest.jpf.facade;

import com.joiest.jpf.entity.ShopInterfaceStreamInfo;

public interface ShopInterfaceStreamServiceFacade {

    /**
     * 添加一条接口流水记录
     */
    public int addStream(ShopInterfaceStreamInfo shopInterfaceStreamInfo);
}
