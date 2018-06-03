package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayOrderYinjiaApiInterfaceCustom;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.PayOrderYinjiaApi;
import com.joiest.jpf.common.po.PayOrderYinjiaApiExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayOrderYinjiaApiInterfaceCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderYinjiaApiMapper;
import com.joiest.jpf.entity.OrderInterfaceInfo;
import com.joiest.jpf.facade.OrderInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigInteger;

public class OrderInterfaceServiceFacadeImpl implements OrderInterfaceServiceFacade {

    @Autowired
    private PayOrderYinjiaApiInterfaceCustomMapper payOrderYinjiaApiInterfaceCustomMapper;

    @Autowired
    private PayOrderYinjiaApiMapper payOrderYinjiaApiMapper;
    /**
     * 根据 orderid 获取商户信息&产品信息&订单支付方式
     */
    public OrderInterfaceInfo getOrder(String orderid)
    {
        PayOrderYinjiaApiInterfaceCustom orderInfo = payOrderYinjiaApiInterfaceCustomMapper.selectOrderAndProductAndPaytype(new BigInteger(orderid));
        if ( orderInfo == null )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.RECORD_NOT_EXIST.getCode(), JpfInterfaceErrorInfo.RECORD_NOT_EXIST.getDesc());
        }
        OrderInterfaceInfo orderInterfaceInfo = new OrderInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayOrderYinjiaApiInterfaceCustom.class, OrderInterfaceInfo.class, false);
        beanCopier.copy(orderInfo, orderInterfaceInfo, null);
        return orderInterfaceInfo;
    }

    /**
     * 支付回调--更新订单状态&paytime
     * @param orderInfo
     */
    public int updateOrderStatus(OrderInterfaceInfo orderInfo)
    {
        PayOrderYinjiaApiExample e = new PayOrderYinjiaApiExample();
        PayOrderYinjiaApiExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(orderInfo.getOrderid());

        PayOrderYinjiaApi payOrderYinjiaApi = new PayOrderYinjiaApi();
        BeanCopier beanCopier = BeanCopier.create(OrderInterfaceInfo.class, PayOrderYinjiaApi.class, false);
        beanCopier.copy(orderInfo, payOrderYinjiaApi, null);
        return payOrderYinjiaApiMapper.updateByExampleSelective(payOrderYinjiaApi,e);
    }

    /**
     * 退款回调--更新订单状态&updatetime
     * @param orderInfo
     */
    public int updateOrderRefund(OrderInterfaceInfo orderInfo){

        PayOrderYinjiaApiExample e = new PayOrderYinjiaApiExample();
        PayOrderYinjiaApiExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(orderInfo.getOrderid());

        PayOrderYinjiaApi payOrderYinjiaApi = new PayOrderYinjiaApi();
        payOrderYinjiaApi.setRefundStatus(orderInfo.getRefundStatus());
        payOrderYinjiaApi.setUpdatetime(orderInfo.getUpdatetime());

        return payOrderYinjiaApiMapper.updateByExampleSelective(payOrderYinjiaApi,e);
    }

}
