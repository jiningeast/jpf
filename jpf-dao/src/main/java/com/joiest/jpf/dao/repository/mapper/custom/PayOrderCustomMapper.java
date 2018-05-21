package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayOrderCustom;
import com.joiest.jpf.common.po.PayOrderExample;

import java.math.BigDecimal;
import java.util.List;

public interface PayOrderCustomMapper {
    /**
     * 查询订单总金额
     */
    BigDecimal selectOrderpriceSum(PayOrderExample example);

    List<PayOrderCustom> selectJoinMerchants(PayOrderExample example);
}