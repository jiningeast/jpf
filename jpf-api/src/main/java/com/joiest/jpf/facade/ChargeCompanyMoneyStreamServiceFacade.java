package com.joiest.jpf.facade;

import com.joiest.jpf.dto.ChargeCompanyMoneyStreamRequest;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamResponse;

public interface ChargeCompanyMoneyStreamServiceFacade {

    /**
     * 获取商户列表
     */
    public ChargeCompanyMoneyStreamResponse getRecords(ChargeCompanyMoneyStreamRequest request);

}
