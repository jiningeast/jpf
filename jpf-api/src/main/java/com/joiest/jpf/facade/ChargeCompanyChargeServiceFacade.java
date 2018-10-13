package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetChargeCompanyChargeRequest;
import com.joiest.jpf.dto.GetChargeCompanyChargeResponse;
import com.joiest.jpf.entity.ChargeCompanyChargeInfo;

public interface ChargeCompanyChargeServiceFacade {

    /**
     * 获取充值订单
     */
    public GetChargeCompanyChargeResponse getRecords(GetChargeCompanyChargeRequest request);

    /**
     * 添加充值订单
     */
    public int addRecord(ChargeCompanyChargeInfo info);
}
