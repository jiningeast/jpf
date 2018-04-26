package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayOrder;
import com.joiest.jpf.common.po.PayOrderExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderMapper;
import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.entity.OrderInfo;
import com.joiest.jpf.facade.OrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
    public OrderResponse getOrders(OrderRequest orderRequest){
        OrderResponse orderResponse = new OrderResponse();

        // 构建查询example
        PayOrderExample e = new PayOrderExample();
        PayOrderExample.Criteria c = e.createCriteria();
        if ( orderRequest.getOrderid() != null ){
            c.andOrderidEqualTo(orderRequest.getOrderid());
        }
        if ( orderRequest.getMtsid() != null ){
            c.andMtsidEqualTo(orderRequest.getMtsid());
        }
        if ( orderRequest.getPid() != null ){
            c.andPidEqualTo(orderRequest.getPid());
        }
        // 价格区间
        if ( orderRequest.getPaytype() != null ){
            c.andPaytypeEqualTo(orderRequest.getPaytype());
        }
        if ( orderRequest.getOrderstatus() != null ){
            c.andOrderstatusEqualTo(orderRequest.getOrderstatus());
        }
        if ( orderRequest.getSinglestatus() != null ){
            c.andSinglestatusEqualTo(orderRequest.getSinglestatus());
        }
        // 支付时间搜索
        Date paytimeStart = new Date();
        if ( StringUtils.isNotBlank(orderRequest.getPaytimeStart()) ){
            paytimeStart = DateUtils.getFdate( orderRequest.getPaytimeStart(), DateUtils.DATEFORMATLONG );
            c.andPaytimeGreaterThanOrEqualTo( paytimeStart );
        }
        Date paytimeEnd = new Date();
        if ( StringUtils.isNotBlank(orderRequest.getPaytimeEnd()) ){
            paytimeEnd = DateUtils.getFdate( orderRequest.getPaytimeEnd(), DateUtils.DATEFORMATLONG );
            c.andPaytimeLessThanOrEqualTo( paytimeEnd );
        }
        if ( StringUtils.isNotBlank(orderRequest.getPaytimeStart()) && StringUtils.isNotBlank(orderRequest.getPaytimeEnd()) ) {
            c.andPaytimeBetween(paytimeStart,paytimeEnd);
        }
        // 添加时间搜索
        Date addtimeStart = new Date();
        if ( StringUtils.isNotBlank(orderRequest.getAddtimeStart()) ){
            addtimeStart = DateUtils.getFdate( orderRequest.getAddtimeStart(), DateUtils.DATEFORMATLONG );
            c.andAddtimeGreaterThan( addtimeStart );
        }
        Date addtimeEnd = new Date();
        if ( StringUtils.isNotBlank(orderRequest.getAddtimeEnd()) ){
            addtimeEnd = DateUtils.getFdate( orderRequest.getAddtimeEnd(), DateUtils.DATEFORMATLONG );
            c.andAddtimeLessThan( addtimeEnd );
        }
        if ( StringUtils.isNotBlank(orderRequest.getAddtimeStart()) && StringUtils.isNotBlank(orderRequest.getAddtimeEnd()) ){
            c.andAddtimeBetween(addtimeStart, addtimeEnd);
        }

        e.setPageNo(orderRequest.getPage());
        e.setPageSize(orderRequest.getRows());
        List<PayOrder> list = payOrderMapper.selectByExample(e);
        List<OrderInfo> infos = new ArrayList<OrderInfo>();
        for (PayOrder payOrder : list){
            OrderInfo orderInfo = new OrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrder.class, OrderInfo.class, false);
            beanCopier.copy(payOrder, orderInfo, null);

            infos.add(orderInfo);
        }

        orderResponse.setList(infos);
        orderResponse.setCount(payOrderMapper.countByExample(e));
        return orderResponse;
    }

    @Override
    public Boolean refund(BigInteger oid){
        return true;
    }
}
