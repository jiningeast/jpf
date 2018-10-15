package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.dto.GetChargeCompanyRequest;
import com.joiest.jpf.dto.GetChargeCompanyResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;

public interface ChargeCompanyServiceFacade {

    /**
     * 获取商户列表
     */
    public GetChargeCompanyResponse getRecords(GetChargeCompanyRequest request);

    /**
     * 查询商户信息
     */
    public ChargeCompanyInfo getOne(ChargeCompanyInfo companyInfo);

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

    /**
     * 根据主键更新公司信息
     */
    public JpfResponseDto updateCompanyRecord(PayChargeCompany payChargeCompany);
}
