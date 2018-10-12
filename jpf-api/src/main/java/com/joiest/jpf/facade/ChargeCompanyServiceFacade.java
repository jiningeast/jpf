package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetChargeCompanyRequest;
import com.joiest.jpf.dto.GetChargeCompanyResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;

public interface ChargeCompanyServiceFacade {

    /**
     * 获取商户列表
     */
    public GetChargeCompanyResponse getRecords(GetChargeCompanyRequest request);

    /**
     * 根据主键id获取商户
     */
    public ChargeCompanyInfo getRecordByPrimaryKey(String id);

    /**
     * 添加商户
     */
    public int addRecord(ChargeCompanyInfo chargeCompanyInfo);

    /**
     * 更新商户
     */
    public int updateColumnByPrimaryKey(ChargeCompanyInfo chargeCompanyInfo);
}
