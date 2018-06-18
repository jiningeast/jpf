package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayOrderYinjiaApiCustom;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.PayOrderYinjiaApi;
import com.joiest.jpf.common.po.PayOrderYinjiaApiExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayOrderYinjiaApiCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderYinjiaApiMapper;
import com.joiest.jpf.dto.OrderYinjiaApiRequest;
import com.joiest.jpf.dto.OrderYinjiaApiResponse;
import com.joiest.jpf.entity.OrderYinjiaApiInfo;
import com.joiest.jpf.facade.OrderYinjiaApiServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderYinjiaApiServiceFacadeImpl implements OrderYinjiaApiServiceFacade {

    @Autowired
    private PayOrderYinjiaApiMapper payOrderYinjiaApiMapper;

    @Autowired
    private PayOrderYinjiaApiCustomMapper payOrderYinjiaApiCustomMapper;

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
    public OrderYinjiaApiInfo getOrderBySignOrderid(String signOrerid, boolean forInterface){
        PayOrderYinjiaApiExample e = new PayOrderYinjiaApiExample();
        PayOrderYinjiaApiExample.Criteria c = e.createCriteria();
        c.andSignOrderidEqualTo(Long.parseLong(signOrerid));

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
        BeanCopier beanCopier = BeanCopier.create(OrderYinjiaApiInfo.class, PayOrderYinjiaApi.class, false);
        beanCopier.copy(orderYinjiaApiInfo, payOrderYinjiaApi, null);
        payOrderYinjiaApi.setUpdatetime(new Date());

        return payOrderYinjiaApiMapper.updateByExampleSelective(payOrderYinjiaApi, e);
    }

    @Override
    public int updateColumnBySignOrderid(OrderYinjiaApiInfo orderYinjiaApiInfo){
        // 判断这条记录是否存在
        getOrderBySignOrderid(orderYinjiaApiInfo.getSignOrderid().toString(), true);

        PayOrderYinjiaApiExample e = new PayOrderYinjiaApiExample();
        PayOrderYinjiaApiExample.Criteria c = e.createCriteria();
        c.andSignOrderidEqualTo(orderYinjiaApiInfo.getSignOrderid());

        PayOrderYinjiaApi payOrderYinjiaApi = new PayOrderYinjiaApi();
        BeanCopier beanCopier = BeanCopier.create(OrderYinjiaApiInfo.class, PayOrderYinjiaApi.class, false);
        beanCopier.copy(orderYinjiaApiInfo, payOrderYinjiaApi, null);
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

    /**
     * 获取 YinjinApi order List ---后台
     */
    public OrderYinjiaApiResponse getOrderYinjiaApi(OrderYinjiaApiRequest request)
    {
        if(request.getPage()<=0){
            request.setPage(1);
        }
        if (request.getRows() <= 0) {
            request.setRows(10);
        }

        PayOrderYinjiaApiExample example = new PayOrderYinjiaApiExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        PayOrderYinjiaApiExample.Criteria c = example.createCriteria();
        List<PayOrderYinjiaApiCustom> orderList = payOrderYinjiaApiCustomMapper.getOrderListYinJiaCustom(example);
        List<OrderYinjiaApiInfo> ordersList = new ArrayList<>();
        for (PayOrderYinjiaApiCustom one : orderList )
        {
            OrderYinjiaApiInfo info = new OrderYinjiaApiInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrderYinjiaApiCustom.class, OrderYinjiaApiInfo.class, false);
            beanCopier.copy(one, info, null);
            ordersList.add(info);
        }

        OrderYinjiaApiResponse response = new OrderYinjiaApiResponse();
        response.setList(ordersList);
        int count = payOrderYinjiaApiMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

    /**
     * 根据 orderid  获取api订单信息
     * @param orderid
     * @return
     */
    public OrderYinjiaApiInfo getOrderYinjiaApiByOrderid(String orderid)
    {
        PayOrderYinjiaApiExample example = new PayOrderYinjiaApiExample();
        PayOrderYinjiaApiExample.Criteria c = example.createCriteria();
        c.andOrderidEqualTo(orderid);
        List<PayOrderYinjiaApiCustom> list = payOrderYinjiaApiCustomMapper.getOrderListYinJiaCustom(example);
        OrderYinjiaApiInfo info = new OrderYinjiaApiInfo();
        if ( list == null || list.isEmpty() )
        {
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "信息不存在");
        }
        PayOrderYinjiaApiCustom one = list.get(0);
        BeanCopier beanCopier = BeanCopier.create(PayOrderYinjiaApiCustom.class, OrderYinjiaApiInfo.class, false);
        beanCopier.copy(one, info, null);
        return info;
    }
}
