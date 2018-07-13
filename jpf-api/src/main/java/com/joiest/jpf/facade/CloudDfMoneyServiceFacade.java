package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;

import java.util.List;
import java.util.Map;

public interface CloudDfMoneyServiceFacade {

    //获取指定日期的记录 15
    public GetCloudMoneyDfResponse getDfMoneyList(String start, String end, String uid, long pageNo, long pageSize, int flag);

    //获取用户总金额
    public Double getUserDfTotalMoney(String uid);

    //获取用户记录 按月份

    public List<CloudDfMoneyInterfaceInfo> getUserMonthList(Long uid);

    //获取充值记录数据通过主键
    public CloudDfMoneyInterfaceInfo getDfMoneyById(Long id);

    //更新代付状态
    public int updateDfMoneyActive(Map<String,String> dfMoney,Long id);
}


