package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ChargeBrandRequest;
import com.joiest.jpf.entity.ChargeProductBrandInfo;

import java.util.List;

public interface ChargeBrandServiceFacade {

    /**
     * 获取所有品牌 list
     * @return
     */
    List<ChargeProductBrandInfo> getShopBrandAllList();

    /**
     * 添加品牌
     */
    public JpfResponseDto addBrand(ChargeBrandRequest request);

}
