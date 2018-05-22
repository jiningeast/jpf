package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayOrderCustom;
import com.joiest.jpf.common.custom.PayOrderInterfaceCustom;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.PayOrderExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayOrderInterfaceCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderMapper;
import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.entity.OrderInfo;
import com.joiest.jpf.entity.OrderInterfaceInfo;
import com.joiest.jpf.facade.OrderInterfaceServiceFacade;
import com.joiest.jpf.facade.OrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class OrderInterfaceServiceFacadeImpl implements OrderInterfaceServiceFacade {

    @Autowired
    private PayOrderInterfaceCustomMapper payOrderInterfaceCustomMapper;

    /**
     * 根据 orderid 获取商户信息&产品信息&订单支付方式
     */
    public OrderInterfaceInfo getOrder(String orderid)
    {
        PayOrderInterfaceCustom orderInfo = payOrderInterfaceCustomMapper.selectOrderAndProductAndPaytype(new BigInteger(orderid));
        if ( orderInfo == null )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.RECORD_NOT_EXIST.getCode(), JpfInterfaceErrorInfo.RECORD_NOT_EXIST.getDesc());
        }
        OrderInterfaceInfo orderInterfaceInfo = new OrderInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayOrderInterfaceCustom.class, OrderInterfaceInfo.class, false);
        beanCopier.copy(orderInfo, orderInterfaceInfo, null);
        return orderInterfaceInfo;
    }
}
