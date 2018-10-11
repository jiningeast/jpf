package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopCompanyChargeRequest;
import com.joiest.jpf.dto.GetShopCompanyChargeResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ShopCompanyChargeInfo;

public interface ChargeCompanyServiceFacade {

    /**
     * 查询商户信息
     */
    public ChargeCompanyInfo getOne(ChargeCompanyInfo companyInfo);

}
