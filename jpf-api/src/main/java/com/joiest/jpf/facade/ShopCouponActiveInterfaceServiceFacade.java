package com.joiest.jpf.facade;


import com.joiest.jpf.dto.GetShopCouponActiveInterfaceRequest;
import com.joiest.jpf.dto.GetUserCouponActiveInterfaceResponse;

public interface ShopCouponActiveInterfaceServiceFacade {

    /**
     * 获取用户激活券记录 getRecords
     * @param uid
     * @return
     */
    GetUserCouponActiveInterfaceResponse getUserCouponList(String uid);

    /**
     * 欣豆明细列表 getRecords
     * @param uid
     * @return
     */
    GetUserCouponActiveInterfaceResponse getUserCouponActiveList(GetShopCouponActiveInterfaceRequest request);

    /**
     * 获取用户激活券记录 list翻页
     * @param uid
     * @return
     */
    GetUserCouponActiveInterfaceResponse getUserCouponListAndPage(String uid,String pageNo,String pageSize);

}
