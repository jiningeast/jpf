package com.joiest.jpf.facade;

import com.joiest.jpf.entity.ShopProductInterfaceInfo;

public interface ShopProductInterfaceServiceFacade {


    /**
     * 获取商品 单个
     */
    public ShopProductInterfaceInfo getShopProduct(String id);


}
