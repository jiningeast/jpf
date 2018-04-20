package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayOrderCpsingle;
import com.joiest.jpf.common.po.PayOrderCpsingleExample;
import com.joiest.jpf.common.po.PayOrderExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderCpsingleMapper;
import com.joiest.jpf.entity.OrderCpsingleInfo;
import com.joiest.jpf.facade.OrderCpsingleServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class OrderCpsingleServiceFacadeImpl implements OrderCpsingleServiceFacade {

    @Autowired
    private PayOrderCpsingleMapper payOrderCpsingleMapper;

    @Override
    public int getCpsCount(){
        PayOrderCpsingleExample e = new PayOrderCpsingleExample();
        return payOrderCpsingleMapper.countByExample(e);
    }

    @Override
    public List<OrderCpsingleInfo> getCps(long page, long rows){
        PayOrderCpsingleExample e = new PayOrderCpsingleExample();
        e.setPageNo(page);
        e.setPageSize(rows);
        List<PayOrderCpsingle> list = payOrderCpsingleMapper.selectByExample(e);
        List<OrderCpsingleInfo> infos = new ArrayList<>();
        for (PayOrderCpsingle payOrderCpsingle:list){
            OrderCpsingleInfo orderCpsingleInfo = new OrderCpsingleInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrderCpsingle.class, OrderCpsingleInfo.class, false);
            beanCopier.copy(payOrderCpsingle, orderCpsingleInfo, null);
            infos.add(orderCpsingleInfo);
        }

        return infos;
    }
}
