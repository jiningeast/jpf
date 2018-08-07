package com.joiest.jpf.facade;


import com.joiest.jpf.dto.GetUserCouponActiveInterfaceResponse;

public interface ShopCouponActiveInterfaceServiceFacade {

    /**
     * 获取用户激活券记录 list
     * @param uid
     * @return
     */
    GetUserCouponActiveInterfaceResponse getUserCouponList(String uid);

}
