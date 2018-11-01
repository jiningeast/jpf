package com.joiest.jpf.facade;

import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamRequest;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamResponse;
import com.joiest.jpf.entity.ChargeCompanyMoneyStreamInfo;

public interface ChargeCompanyMoneyStreamServiceFacade {

    /**
     * 获取商户列表
     */
    public ChargeCompanyMoneyStreamResponse getRecords(ChargeCompanyMoneyStreamRequest request);

    /**
     * 添加流水记录
     */
    public int create(PayChargeCompanyMoneyStream record);

    /**
     * 新增流水
     */
    public int insRecord(ChargeCompanyMoneyStreamInfo info);

}
