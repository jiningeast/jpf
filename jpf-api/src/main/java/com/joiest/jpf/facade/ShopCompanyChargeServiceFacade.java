package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopCompanyChargeRequest;
import com.joiest.jpf.dto.GetShopCompanyChargeResponse;
import com.joiest.jpf.entity.ShopCompanyChargeInfo;

public interface ShopCompanyChargeServiceFacade {

    /**
     * 公司列表---后台
     */
    public GetShopCompanyChargeResponse getList(GetShopCompanyChargeRequest request);

    /**
     * 公司列表---后台
     */
    public ShopCompanyChargeInfo getOne(String order_no);

    /**
     * 公司列表---后台
     */
    public JpfResponseDto add(GetShopCompanyChargeRequest request);

    /**
     * 财务审核充值---后台
     */
    public JpfResponseDto auditCompanyCharge(GetShopCompanyChargeRequest request);

    /**
     * 财务审核充值---后台
     */
    public JpfResponseDto caiWuCompanyCharge(GetShopCompanyChargeRequest request);

}
