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
     * 根据 orderid 获取订单支付同步流水
     */
    public List<OrderPayMessageInfo> getOrderPayMessageReturnListByOrderId(String orderid)
    {
        PayOrderPayMessageExample example = new PayOrderPayMessageExample();
        example.setOrderByClause("id DESC");
        PayOrderPayMessageExample.Criteria c = example.createCriteria();
        c.andOrderidEqualTo(orderid);
        c.andTypeEqualTo((byte)1);
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

    /**
     * 根据 orderid 获取订单支付异步回调流水
     */
    public List<OrderPayMessageInfo> getOrderPayMessageNotifyListByOrderId(String orderid)
    {
        PayOrderPayMessageExample example = new PayOrderPayMessageExample();
        example.setOrderByClause("id DESC");
        PayOrderPayMessageExample.Criteria c = example.createCriteria();
        c.andOrderidEqualTo(orderid);
        c.andTypeEqualTo((byte)2);
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
