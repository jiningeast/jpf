package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudCompactInfo;

public interface CloudCompactServiceFacade {

    /**
     * 根据主键id查询记录
     */
    public CloudCompactInfo getRecById(String id);

    /**
     * 根据type类型字段查询记录
     */
    public CloudCompactInfo getRecByType(byte type);
}
