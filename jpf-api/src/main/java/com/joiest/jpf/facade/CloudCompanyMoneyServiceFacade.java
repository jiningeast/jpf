package com.joiest.jpf.facade;

import com.joiest.jpf.dto.CloudCompanyMoneyRequest;
import com.joiest.jpf.dto.CloudCompanyMoneyResponse;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;

import java.util.Map;

public interface CloudCompanyMoneyServiceFacade {

    /*
     * 批次列表总数
     * */
    public Integer getCount();

    /*
     * 批次列表
     * */
    public CloudCompanyMoneyResponse getRecords(CloudCompanyMoneyRequest cloudCompanyMoneyRequest);

    /*
     * 财务审核列表
     * */
    public CloudCompanyMoneyResponse getCaiwuRecords(CloudCompanyMoneyRequest cloudCompanyMoneyRequest);

    /*
     * 代付列表
     * */
    public GetCloudMoneyDfResponse getAllByfid(String fid);

    /**
     * 新增代付订单
     */
    public int addRec(CloudCompanyMoneyRequest cloudCompanyMoneyRequest);
}
