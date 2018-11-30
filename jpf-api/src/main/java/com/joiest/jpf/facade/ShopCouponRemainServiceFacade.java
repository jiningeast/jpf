package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetCouponRemainResponse;
import com.joiest.jpf.dto.GetShopCouponRemainResponse;
import com.joiest.jpf.entity.ShopBargainOrderInfo;
import com.joiest.jpf.entity.ShopCouponRemainInfo;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.entity.ShopOrderInterfaceInfo;

import java.util.List;
import java.util.Map;

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
     * 支付消费扣除非转让
     */
    public int CouponHandler(List<ShopCouponRemainInfo> list, ShopOrderInterfaceInfo orderInfo, ShopCustomerInterfaceInfo userInfo);

    /**
     * 支付和转让整合方法
     * listSaleNo当前非转让欣券
     * listSaleYes当前可转让欣券
     * type 行为类型
     */
 /*   public int CouponHandlerAll( List<ShopCouponRemainInfo> list,ShopOrderInterfaceInfo orderInfo, ShopCustomerInterfaceInfo userInfo);*/

    /**
     * 转让欣豆
     */
    public int CouponAttorn(List<ShopCouponRemainInfo> list, ShopBargainOrderInfo orderInfo, ShopCustomerInterfaceInfo userInfo);

    /**
     * 获取用户的可转让一直非转让额度
     */
    public GetShopCouponRemainResponse getSum(String customerId);

}
