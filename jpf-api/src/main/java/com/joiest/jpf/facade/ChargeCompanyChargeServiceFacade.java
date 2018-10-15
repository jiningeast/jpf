package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
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

    /**
     * 获取单条充值信息
     */
    public ChargeCompanyChargeInfo getOne(String id);


    /**
     * 财务审核充值---后台
     */
    public JpfResponseDto caiWuCompanyCharge(GetChargeCompanyChargeRequest request);
}
