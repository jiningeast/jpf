package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayOrderPayMerMessage;
import com.joiest.jpf.common.po.PayOrderPayMerMessageExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderPayMerMessageMapper;
import com.joiest.jpf.entity.OrderPayMerMessageInfo;
import com.joiest.jpf.entity.OrderPayMessageInfo;
import com.joiest.jpf.facade.OrderPayMerMessageServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class OrderPayMerMessageServiceFacadeImpl implements OrderPayMerMessageServiceFacade {

    @Autowired
    private PayOrderPayMerMessageMapper payOrderPayMerMessageMapper;

    /**
     * 根据 orderid 获取订单支付流水
     */
    public List<OrderPayMerMessageInfo> getOrderPayMerMessageListByOrderId(String orderid)
    {
        PayOrderPayMerMessageExample example = new PayOrderPayMerMessageExample();
        PayOrderPayMerMessageExample.Criteria c = example.createCriteria();
        c.andOrderidEqualTo(orderid);
        List<PayOrderPayMerMessage> list = payOrderPayMerMessageMapper.selectByExample(example);

        List<OrderPayMerMessageInfo> orderPayMerMessageList = new ArrayList<>();
        for (PayOrderPayMerMessage one : list )
        {
            OrderPayMerMessageInfo info = new OrderPayMerMessageInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrderPayMerMessage.class, OrderPayMerMessageInfo.class, false);
            beanCopier.copy(one, info, null);
            orderPayMerMessageList.add(info);
        }
        return orderPayMerMessageList;
    }

}
