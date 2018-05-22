package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetValueRequest;
import com.joiest.jpf.dto.GetValueResponse;
import com.joiest.jpf.entity.OrderCpInterfaceInfo;

public interface OrderCpServiceFacade {

    /**
     * 根据 orderid 获取商户签约信息
     */
    public OrderCpInterfaceInfo getOrderCpByorderid(String orderid);
}
