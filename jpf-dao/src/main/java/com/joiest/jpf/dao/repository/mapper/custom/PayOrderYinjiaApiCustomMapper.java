package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayOrderYinjiaApiCustom;
import com.joiest.jpf.common.custom.PayOrderYinjiaApiInterfaceCustom;
import com.joiest.jpf.common.po.PayOrderYinjiaApiExample;

import java.math.BigInteger;
import java.util.List;

public interface PayOrderYinjiaApiCustomMapper {

    /**
     * 自定义查询 ： 根据订单号查询订单信息&产品信息&订单支付信息
     */
    List<PayOrderYinjiaApiCustom> getOrderListYinJiaCustom(PayOrderYinjiaApiExample example);
}