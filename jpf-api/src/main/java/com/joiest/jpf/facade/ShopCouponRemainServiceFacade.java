package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetCouponRemainResponse;
import com.joiest.jpf.entity.ShopCouponRemainInfo;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.entity.ShopOrderInterfaceInfo;

import java.util.List;

public interface ShopCouponRemainServiceFacade {

    /**
     * 处理过期的券
     */
    public int dealAllExpiredCoupon();

    /**
     * 处理个人的过期的券
     */
    public int dealCustomerExpiredCoupon(String customerId);

    /**
     * 获取个人可用券列表
     */
    public GetCouponRemainResponse getCouponRemainByUidForInterface(String uid);

    /**
     * 支付消费
     */
    public Boolean CouponHandler(List<ShopCouponRemainInfo> list, ShopOrderInterfaceInfo orderInfo, ShopCustomerInterfaceInfo userInfo);

}
