package com.joiest.jpf.facade;

import com.joiest.jpf.dto.CloudCompanyMoneyRequest;
import com.joiest.jpf.dto.CloudCompanyMoneyResponse;

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


}
