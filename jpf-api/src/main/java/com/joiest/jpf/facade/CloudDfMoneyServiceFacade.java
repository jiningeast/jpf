package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;

import java.util.List;

public interface CloudDfMoneyServiceFacade {

    //获取指定日期的记录 15
    public GetCloudMoneyDfResponse getDfMoneyList(String start, String end, String uid, long pageNo, long pageSize, int flag);

    //获取用户总金额
    public Double getUserDfTotalMoney(String uid);

    //获取用户记录 按月份

    public List<CloudDfMoneyInterfaceInfo> getUserMonthList(Long uid);
}
