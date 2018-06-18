package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayOrdersMoneyDetail;
import com.joiest.jpf.common.po.PayOrdersMoneyDetailExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrdersMoneyDetailMapper;
import com.joiest.jpf.entity.OrdersMoneyInfo;
import com.joiest.jpf.facade.OrdersMoneyServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class OrdersMoneyServiceFacadeImpl implements OrdersMoneyServiceFacade {


    @Autowired
    private PayOrdersMoneyDetailMapper payOrdersMoneyDetailMapper;

    /**
     * 根据orderid 获取订单费率详情
     */
    public OrdersMoneyInfo getOrdersMoneyInfo(Long orderid)
    {
        if ( orderid == null )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "orderid不能为空");
        }
        PayOrdersMoneyDetailExample example = new PayOrdersMoneyDetailExample();
        PayOrdersMoneyDetailExample.Criteria c = example.createCriteria();
        c.andOrderidEqualTo(orderid);
        OrdersMoneyInfo info = new OrdersMoneyInfo();
        List<PayOrdersMoneyDetail> list = payOrdersMoneyDetailMapper.selectByExample(example);
        if ( list == null || list.isEmpty() )
        {
            return info;
        }
        BeanCopier beanCopier = BeanCopier.create(PayOrdersMoneyDetail.class, OrdersMoneyInfo.class, false);
        beanCopier.copy(list.get(0), info, null);
        return info;
    }
}
