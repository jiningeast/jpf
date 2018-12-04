package com.joiest.jpf.facade;

import com.joiest.jpf.common.po.PayChargeConsumerOrder;
import com.joiest.jpf.common.po.PayShopBargainRechargeOrder;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderResponse;

import java.math.BigDecimal;
import java.util.List;

public interface ShopBargainRechargeOrderServiceFacade {

    /**
     * 获取敬恒订单
     */
    public GetShopBargainRechargeOrderResponse getRecords(GetShopBargainRechargeOrderRequest request);

    BigDecimal getMoneyTotal(String userId);

    /**
     * 查询数据中的数据，向redis 中加入数据
     * @param querySize
     * @return
     */
    List<PayShopBargainRechargeOrder> pushDataToRedisTask(long querySize);

}
