package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudCompactStaffInterfaceCustomInfo;

import java.util.List;

public interface CloudCompactStaffInterfaceServiceFacade {

    /**
     * 获取用户签约合同
     * @param uid
     * @param compactActive 签约状态
     * @return
     */
    public List<CloudCompactStaffInterfaceCustomInfo> getUserCompactList(Long uid, byte compactActive);

    /**
     * 自定义查询
     * 获取用户签约合同
     * @param uid
     * @param compactActive
     * @return
     */
    public List<CloudCompactStaffInterfaceCustomInfo> getUserCompactListCustom(Long uid, byte compactActive);

}
