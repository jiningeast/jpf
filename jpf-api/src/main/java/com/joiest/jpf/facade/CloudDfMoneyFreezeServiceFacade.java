package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetCloudDfMoneyFreezeRequest;
import com.joiest.jpf.dto.GetCloudDfMoneyFreezeResponse;
import com.joiest.jpf.entity.CloudDfMoneyFreezeInfo;
import com.joiest.jpf.entity.UserInfo;

public interface CloudDfMoneyFreezeServiceFacade {

    /**
     * 查询冻结信息
     */
    public GetCloudDfMoneyFreezeResponse getRecords(GetCloudDfMoneyFreezeRequest request);

    /**
     * 根据主键id查询单条信息
     */
    public CloudDfMoneyFreezeInfo getRecordByPrimaryKey(String id);

    /**
     * 添加一条冻结信息
     */
    public int add(CloudDfMoneyFreezeInfo freezeInfo);

    /**
     * 根据主键id更新单条信息
     */
    public int updateColumnByPrimaryKey(CloudDfMoneyFreezeInfo cloudDfMoneyFreezeInfo);

    /**
     * 解冻操作
     */
    public int unfreeze(String freezeId,UserInfo userInfo);
}
