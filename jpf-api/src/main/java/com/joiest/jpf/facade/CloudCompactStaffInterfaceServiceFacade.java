package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudCompactStaffInterfaceCustomInfo;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取单个合同通过合同单号、员工id
     */
    public CloudCompactStaffInterfaceCustomInfo getUserCompactById(Long id);

    /**
     * 更新合同状态
     */
    public int upUserCompactActiveById(Map<String,String> compant, Long id);

}
