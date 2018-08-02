package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ShopProductTypeRequest;
import com.joiest.jpf.entity.ShopProductTypeInfo;

import java.util.List;

public interface ShopProductTypeServiceFacade {

    /**
     * 获取所有商品分类
     * @return
     */
    List<ShopProductTypeInfo> getAllShopProductTypeList();

    /**
     * 添加商品分类
     */
    public JpfResponseDto addShopProductType(ShopProductTypeRequest request);
}
