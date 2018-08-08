package com.joiest.jpf.facade;

import com.joiest.jpf.entity.ShopProductInterfaceInfo;

import java.util.List;

public interface ShopProductInterfaceServiceFacade {


    /**
     * 获取商品 单个
     */
    public ShopProductInterfaceInfo getShopProduct(String id);

    /**
     * 获取商品列表 by 商品类型
     */

    public List<ShopProductInterfaceInfo> getShopProductByBrandId(String brandId);

}
