package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ChargeProductTypeRequest;
import com.joiest.jpf.entity.ChargeProductTypeInfo;

import java.util.List;

public interface ChargeProductTypeServiceFacade {

    /**
     * 获取所有商品分类
     * @return
     */
    List<ChargeProductTypeInfo> getAllShopProductTypeList();

    /**
     * 添加商品分类
     */
    public JpfResponseDto addShopProductType(ChargeProductTypeRequest request);
}
