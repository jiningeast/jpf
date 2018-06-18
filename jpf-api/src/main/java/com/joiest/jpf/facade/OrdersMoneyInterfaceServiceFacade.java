package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrdersMoneyInterfaceInfo;

public interface OrdersMoneyInterfaceServiceFacade {

    /**
     * 添加订单费率金额详情
     */
    public int addRecord(OrdersMoneyInterfaceInfo interfaceInfo);

    /**
     * 更新订单费率金额详情
     */
    public int modifyRecord(OrdersMoneyInterfaceInfo interfaceInfo);
}
