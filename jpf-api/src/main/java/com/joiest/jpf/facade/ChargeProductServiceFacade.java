package com.joiest.jpf.facade;

import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.entity.ChargeCompanyInfo;

import java.util.List;

public interface ChargeProductServiceFacade {

    /**
     * 查询商户信息
     */
    public List<PayChargeProduct> getOne(PayChargeProduct record);

}
