package com.joiest.jpf.facade;

import com.joiest.jpf.entity.MerchantTypeInfo;

import java.util.List;

public interface MerTypeServiceFacade {

    /**
     * 获取类型信息
     * @param pid
     * @return
     */
    public List<MerchantTypeInfo> getMerTypes(String pid);

}
