package com.joiest.jpf.facade;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.ShopOrderCouponConsumeDetailResponse;

import java.util.List;

/**
 * 欣豆市场确认收入订单服务接口
 * @author admin 
 */
public interface ShopIncomeConfirmationServiceFacade {

    /**
     * 获取欣豆市场确认收入订单详情列表
     * @param orderNo
     * @return
     */
    ShopOrderCouponConsumeDetailResponse getOrderIncomeConfirmationDetail(String orderNo);

    /**
     * 查询所有订单及其对应的欣券详情记录
     * @param request
     * @return
     */
    List<PayShopOrderCustom> getShopOrderList(GetShopOrderRequest request);
}
