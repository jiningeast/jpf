package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayBank;
import com.joiest.jpf.common.po.PayOrder;
import com.joiest.jpf.common.po.PayOrderExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderMapper;
import com.joiest.jpf.entity.BankInfo;
import com.joiest.jpf.entity.OrderInfo;
import com.joiest.jpf.facade.OrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceFacadeImpl implements OrderServiceFacade {

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Override
    public Long getOrdersCount(){
        PayOrderExample e = new PayOrderExample();
        Long count = (long)payOrderMapper.countByExample(e);

        return count;
    }

    @Override
    public List<OrderInfo> getOrders(long page, long rows){
        PayOrderExample e = new PayOrderExample();
        e.setPageNo(page);
        e.setPageSize(rows);
        List<PayOrder> list = payOrderMapper.selectByExample(e);
        List<OrderInfo> infos = new ArrayList<OrderInfo>();
        for (PayOrder payOrder : list){
            OrderInfo orderInfo = new OrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrder.class, OrderInfo.class, false);
            beanCopier.copy(payOrder, orderInfo, null);

            infos.add(orderInfo);
        }

        return infos;
    }

    @Override
    public Boolean refund(BigInteger oid){
        return true;
    }
}
