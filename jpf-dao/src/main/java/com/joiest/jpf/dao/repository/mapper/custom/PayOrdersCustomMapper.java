package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayOrdersCustom;
import com.joiest.jpf.common.po.PayOrders;
import com.joiest.jpf.common.po.PayOrdersExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayOrdersCustomMapper {

    List<PayOrdersCustom> getOrdersListAndPaytype(PayOrdersExample example);
}