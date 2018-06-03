package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.PayOrderYinjiaApi;
import com.joiest.jpf.common.po.PayOrderYinjiaApiExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderYinjiaApiMapper;
import com.joiest.jpf.entity.OrderYinjiaApiInfo;
import com.joiest.jpf.facade.OrderYinjiaApiServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;
import java.util.List;

public class OrderYinjiaApiServiceFacadeImpl implements OrderYinjiaApiServiceFacade {

    @Autowired
    private PayOrderYinjiaApiMapper payOrderYinjiaApiMapper;

    @Override
    public int insOrder(OrderYinjiaApiInfo orderYinjiaApiInfo){
        PayOrderYinjiaApi payOrderYinjiaApi = new PayOrderYinjiaApi();
        BeanCopier beanCopier = BeanCopier.create(OrderYinjiaApiInfo.class, PayOrderYinjiaApi.class, false);
        beanCopier.copy(orderYinjiaApiInfo, payOrderYinjiaApi, null);

        return payOrderYinjiaApiMapper.insertSelective(payOrderYinjiaApi);
    }

    @Override
    public OrderYinjiaApiInfo getOrderByOrderidAndForeignOrderid(String orderid, String platformOrderid, boolean forInterface){
        PayOrderYinjiaApiExample e = new PayOrderYinjiaApiExample();
        PayOrderYinjiaApiExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(platformOrderid);
        c.andForeignOrderidEqualTo(orderid);
        List<PayOrderYinjiaApi> list = payOrderYinjiaApiMapper.selectByExample(e);

        if ( forInterface && list == null){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(), JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());
        }

        OrderYinjiaApiInfo orderYinjiaApiInfo = new OrderYinjiaApiInfo();
        BeanCopier beanCopier = BeanCopier.create(PayOrderYinjiaApi.class, OrderYinjiaApiInfo.class, false);
        beanCopier.copy(list.get(0), orderYinjiaApiInfo, null);

        return orderYinjiaApiInfo;
    }

    @Override
    public OrderYinjiaApiInfo getOrderByOrderid(String orderid, boolean forInterface){
        PayOrderYinjiaApiExample e = new PayOrderYinjiaApiExample();
        PayOrderYinjiaApiExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(orderid);

        List<PayOrderYinjiaApi> list = payOrderYinjiaApiMapper.selectByExample(e);

        if ( forInterface && list == null){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(), JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());
        }

        OrderYinjiaApiInfo orderYinjiaApiInfo = new OrderYinjiaApiInfo();
        BeanCopier beanCopier = BeanCopier.create(PayOrderYinjiaApi.class, OrderYinjiaApiInfo.class, false);
        beanCopier.copy(list.get(0), orderYinjiaApiInfo, null);

        return orderYinjiaApiInfo;
    }

    @Override
    public int updataSignOrderid(OrderYinjiaApiInfo orderYinjiaApiInfo){
        PayOrderYinjiaApiExample e = new PayOrderYinjiaApiExample();
        PayOrderYinjiaApiExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(orderYinjiaApiInfo.getOrderid());

        PayOrderYinjiaApi payOrderYinjiaApi = new PayOrderYinjiaApi();
        payOrderYinjiaApi.setSignOrderid(orderYinjiaApiInfo.getSignOrderid());
        payOrderYinjiaApi.setUpdatetime(new Date());

        return payOrderYinjiaApiMapper.updateByExampleSelective(payOrderYinjiaApi, e);
    }

    @Override
    public int updateColumnByOrderid(OrderYinjiaApiInfo orderYinjiaApiInfo){
        // 判断这条记录是否存在
        getOrderByOrderid(orderYinjiaApiInfo.getOrderid(),true);

        PayOrderYinjiaApiExample e = new PayOrderYinjiaApiExample();
        PayOrderYinjiaApiExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(orderYinjiaApiInfo.getOrderid());

        PayOrderYinjiaApi payOrderYinjiaApi = new PayOrderYinjiaApi();
        if ( orderYinjiaApiInfo.getUserOperateStatus() != null ){
            payOrderYinjiaApi.setUserOperateStatus(orderYinjiaApiInfo.getUserOperateStatus());
        }
        payOrderYinjiaApi.setUpdatetime(new Date());

        return payOrderYinjiaApiMapper.updateByExampleSelective(payOrderYinjiaApi, e);
    }

    @Override
    public int updateOrdername(OrderYinjiaApiInfo orderYinjiaApiInfo, boolean forInterface){
        // 判断这条记录是否存在
        getOrderByOrderid(orderYinjiaApiInfo.getOrderid(),true);

        // 开始更新
        PayOrderYinjiaApiExample e = new PayOrderYinjiaApiExample();
        PayOrderYinjiaApiExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(orderYinjiaApiInfo.getOrderid());

        PayOrderYinjiaApi payOrderYinjiaApi = new PayOrderYinjiaApi();
        payOrderYinjiaApi.setPayDetail(orderYinjiaApiInfo.getPayDetail());
        payOrderYinjiaApi.setUserOperateStatus(orderYinjiaApiInfo.getUserOperateStatus());
        payOrderYinjiaApi.setUpdatetime(new Date());

        return payOrderYinjiaApiMapper.updateByExampleSelective(payOrderYinjiaApi, e);
    }
}
