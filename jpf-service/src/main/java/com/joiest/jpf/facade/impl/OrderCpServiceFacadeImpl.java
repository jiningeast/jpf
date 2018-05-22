package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayOrderInterfaceCustom;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.PayOrderCp;
import com.joiest.jpf.common.po.PayOrderCpExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayOrderInterfaceCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderCpMapper;
import com.joiest.jpf.dto.GetValueRequest;
import com.joiest.jpf.entity.OrderCpInterfaceInfo;
import com.joiest.jpf.entity.OrderInterfaceInfo;
import com.joiest.jpf.facade.OrderCpServiceFacade;
import com.joiest.jpf.facade.OrderInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigInteger;
import java.util.List;

public class OrderCpServiceFacadeImpl implements OrderCpServiceFacade {

    @Autowired
    PayOrderCpMapper payOrderCpMapper;
    /**
     * 根据 orderid 获取商户签约信息
     */
    @Override
    public OrderCpInterfaceInfo getOrderCpByorderid(String orderid)
    {
        PayOrderCpExample example = new PayOrderCpExample();
        PayOrderCpExample.Criteria c = example.createCriteria();
        c.andOrderidEqualTo(Long.valueOf(orderid));
        List<PayOrderCp> ordercpList = payOrderCpMapper.selectByExample(example);
        if ( ordercpList == null || ordercpList.isEmpty())
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.RECORD_NOT_EXIST.getCode(), "签约信息不存在");
        }
        PayOrderCp payOrderCp = ordercpList.get(0);
        OrderCpInterfaceInfo orderCpInterfaceInfo = new OrderCpInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create( PayOrderCp.class, OrderCpInterfaceInfo.class, false);
        beanCopier.copy(payOrderCp, orderCpInterfaceInfo, null);
        return orderCpInterfaceInfo;
    }
}
