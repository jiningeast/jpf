package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ShopBrandRequest;
import com.joiest.jpf.entity.ShopBrandInfo;

import java.util.List;

public interface ShopBrandServiceFacade {

    /**
     * 获取所有品牌 getRecords
     * @return
     */
    List<ShopBrandInfo> getShopBrandAllList();

    /**
     * 添加品牌
     */
    public JpfResponseDto addBrand(ShopBrandRequest request);

}
