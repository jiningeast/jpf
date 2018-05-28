package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrderCpInterfaceInfo;

public interface OrderCpServiceFacade {

    /**
     * 根据 orderid 获取商户签约信息
     */
    public OrderCpInterfaceInfo getOrderCpByorderid(String orderid);

    /**
     * 根据银行卡号判断用户是否已签约
     */
    public OrderCpInterfaceInfo getOrderCpBybankaccountnumber(String bankaccountnumber);

    /**
     * 插入一条签约记录
     */
    public int insRecord(OrderCpInterfaceInfo orderCpInterfaceInfo);

    /**
     * 更新记录
     */
    public int updateRecord(OrderCpInterfaceInfo orderCpInterfaceInfo);
}
