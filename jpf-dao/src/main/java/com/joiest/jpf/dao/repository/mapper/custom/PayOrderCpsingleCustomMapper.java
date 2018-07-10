package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayOrderCpsingleCustom;
import com.joiest.jpf.common.po.PayOrderCpsingle;
import com.joiest.jpf.common.po.PayOrderCpsingleExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

public interface PayOrderCpsingleCustomMapper {


    /**
     * 查询退单和商户信息
     */

    List<PayOrderCpsingleCustom> selectJoinMerchants(PayOrderCpsingleExample example);
}
