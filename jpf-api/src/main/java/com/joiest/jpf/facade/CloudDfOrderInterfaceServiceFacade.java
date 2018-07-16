package com.joiest.jpf.facade;

import com.joiest.jpf.dto.AddCloudDfTaskRequest;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;

import java.util.List;

public interface CloudDfOrderInterfaceServiceFacade {

    /**
     * 添加打款订单信息
     * @param list
     * @return
     */
    public int addOrder(String batchid, String dfid, List<CloudDfMoneyInterfaceInfo> list, String batchid_self);
}
