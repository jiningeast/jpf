package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetShopAdInterfaceRequest;
import com.joiest.jpf.dto.GetShopAdInterfaceResponse;

public interface ShopAdInterfaceServiceFacade {

    /**
     * 获取图片信息
     */
    GetShopAdInterfaceResponse getList(GetShopAdInterfaceRequest request);



}
