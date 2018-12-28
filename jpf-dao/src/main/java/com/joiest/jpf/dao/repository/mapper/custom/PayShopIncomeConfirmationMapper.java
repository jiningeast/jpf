package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.custom.PayShopOrderCustomExample;

import java.util.List;

/**
 * @author admin 
 */
public interface PayShopIncomeConfirmationMapper {

    /**
     * 查询所有订单及其欣券详情数据
     * @param payShopOrderCustomExample
     * @return
     */
    List<PayShopOrderCustom> selectShopOrderCouponDetailList(PayShopOrderCustomExample payShopOrderCustomExample);
}
