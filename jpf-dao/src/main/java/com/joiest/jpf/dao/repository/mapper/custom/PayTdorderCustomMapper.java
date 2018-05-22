package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayTdorderCustom;
import com.joiest.jpf.common.po.PayTdorderExample;
import java.util.List;

public interface PayTdorderCustomMapper {
    /**
     * 查询退单和商户信息
     */

    List<PayTdorderCustom> selectJoinMerchants(PayTdorderExample example);
}