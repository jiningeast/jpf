package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayOrderCustom;
import com.joiest.jpf.common.po.PayOrder;
import com.joiest.jpf.common.po.PayOrderExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayOrderCustomMapper;
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

    @Autowired
    private PayOrderCustomMapper payOrderCustomMapper;

    @Override
    public Long getOrdersCount(){
        PayOrderExample e = new PayOrderExample();
        Long count = (long)payOrderMapper.countByExample(e);

        return count;
    }

    @Override
    public OrderResponse getOrders(OrderRequest orderRequest){
        OrderResponse orderResponse = new OrderResponse();

        PayOrderExample e = new PayOrderExample();
        e.setPageNo(orderRequest.getPage());
        e.setPageSize(orderRequest.getRows());
        e.setOrderByClause("id DESC");
        PayOrderExample.Criteria c = e.createCriteria();

        // 汇总统计
        orderResponse.setAllOrdersCount(this.getOrdersCount());
        payOrderMapper.countByExample(e);
        orderResponse.setAllOrdersMoney(payOrderCustomMapper.selectOrderpriceSum(e));
        c.andSinglestatusEqualTo((byte)7);
        orderResponse.setAllRefundMoney(payOrderCustomMapper.selectOrderpriceSum(e));
        e.clear();

        // 构建查询example
        if ( StringUtils.isNotBlank(orderRequest.getOrderid()) ){
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
        e.setOrderByClause("id DESC");
        List<PayOrderCustom> list = payOrderCustomMapper.selectJoinMerchants(e);
        List<OrderInfo> infos = new ArrayList<OrderInfo>();
        for (PayOrderCustom payOrderCustom : list){
            OrderInfo orderInfo = new OrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayOrderCustom.class, OrderInfo.class, false);
            beanCopier.copy(payOrderCustom, orderInfo, null);

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
