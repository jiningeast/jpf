package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetShopCouponActiveRequest;
import com.joiest.jpf.dto.GetShopCouponActiveResponse;
public interface ShopCouponActiveServiceFacade {

    /**
     * 客户列表---欣豆行为列表
     */
    public GetShopCouponActiveResponse getList(GetShopCouponActiveRequest request,String id);


}
