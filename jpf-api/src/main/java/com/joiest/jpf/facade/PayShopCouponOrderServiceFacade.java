package com.joiest.jpf.facade;

import com.joiest.jpf.common.po.PayShopCouponOrder;
import com.joiest.jpf.common.po.PayShopCouponOrderInfo;
import com.joiest.jpf.entity.CouponOrderList;

import java.util.List;
import java.util.Map;

/**
 * @Auther: admin
 * @Date: 2018/11/30 10:04
 * @Description:
 */

public interface PayShopCouponOrderServiceFacade {
    /**
     * 保存前台提交的订单
     * @param couponOrderList
     */
    void saveCouponOrder(CouponOrderList couponOrderList);

    /**
     * 获取订单号
     * @param map
     * @return
     */
    List<PayShopCouponOrder> getOrderList(Map<String, Object> map);

    /**
     * 查询订单的详情
     * @param map
     * @return
     */
    List<PayShopCouponOrderInfo> getOrderInfo(Map<String, Object> map);

    /**
     * 取消订单
     * @param orderId
     */
    void cancalOrder(PayShopCouponOrder orderId);

    /**
     * 根据订单id查询订单
     * @param orderId
     * @return
     */
    PayShopCouponOrder getByOrderId(String orderId);

    /**
     * 删除订单
     * @param payShopCouponOrder
     */
    void deleteOrder(PayShopCouponOrder payShopCouponOrder);
}
