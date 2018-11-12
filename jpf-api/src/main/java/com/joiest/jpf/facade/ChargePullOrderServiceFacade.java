package com.joiest.jpf.facade;

import com.joiest.jpf.dto.MarchingDataRequest;
import com.joiest.jpf.entity.ChargeCompanyInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ChargePullOrderServiceFacade {

    /**
     * 保存订单
     * @param merchNo 商户号
     * @param money 拉取金额
     */
    Map<String,Object> savePayOrder(String merchNo, String money, ChargeCompanyInfo companyInfo);

    void matchingDataByThread(MarchingDataRequest marchingDataRequest, HttpServletResponse httpResponse);
}
