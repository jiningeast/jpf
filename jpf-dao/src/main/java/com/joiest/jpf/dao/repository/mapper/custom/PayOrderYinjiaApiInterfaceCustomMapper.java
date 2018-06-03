package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayOrderYinjiaApiInterfaceCustom;

import java.math.BigInteger;

public interface PayOrderYinjiaApiInterfaceCustomMapper {

    /**
     * 自定义查询 ： 根据订单号查询订单信息&产品信息&订单支付信息
     */
    PayOrderYinjiaApiInterfaceCustom selectOrderAndProductAndPaytype(BigInteger oid);
}