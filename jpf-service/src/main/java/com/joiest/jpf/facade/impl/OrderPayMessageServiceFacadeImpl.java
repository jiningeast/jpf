package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayOrderPayMerMessage;
import com.joiest.jpf.common.po.PayOrderPayMessage;
import com.joiest.jpf.common.po.PayOrderPayMessageExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderPayMessageMapper;
import com.joiest.jpf.entity.OrderPayMessageInfo;
import com.joiest.jpf.facade.OrderPayMessageServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class OrderPayMessageServiceFacadeImpl implements OrderPayMessageServiceFacade {

    @Autowired
    private PayOrderPayMessageMapper payOrderPayMessageMapper;

    /**
     * 根据 orderid 获取订单支付流水
     */
    public List<OrderPayMessageInfo> getOrderPayMessageListByOrderId(String orderid)
    {
        PayOrderPayMessageExample example = new PayOrderPayMessageExample();
        PayOrderPayMessageExample.Criteria c = example.createCriteria();
        c.andOrderidEqualTo(orderid);
        List<PayOrderPayMessage> list = payOrderPayMessageMapper.selectByExample(example);

        List<OrderPayMessageInfo> orderPayMessageList = new ArrayList<>();
        for (PayOrderPayMessage one : list )
        {
            OrderPayMessageInfo info = new OrderPayMessageInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrderPayMessage.class, OrderPayMessageInfo.class, false);
            beanCopier.copy(one, info, null);
            orderPayMessageList.add(info);
        }
        return orderPayMessageList;
    }

}
