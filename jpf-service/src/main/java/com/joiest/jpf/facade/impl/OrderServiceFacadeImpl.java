package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayOrderCustom;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
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

import java.math.BigDecimal;
import java.util.*;

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
        PayOrderExample.Criteria c = e.createCriteria();

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
        if ( orderRequest.getPaytype() != null ){
            c.andPaytypeEqualTo(orderRequest.getPaytype());
        }
        if ( orderRequest.getOrderstatus() != null ){
            c.andOrderstatusEqualTo(orderRequest.getOrderstatus());
        }
        // 退单状态
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

        // 给统计赋值
        Map<String, Object> map = this.getStatistics(orderRequest);
        orderResponse.setAllOrdersCount((long)map.get("allOrdersCount"));
        orderResponse.setAllOrdersMoney((BigDecimal)map.get("allOrdersMoney"));
        orderResponse.setAllRefundMoney((BigDecimal)map.get("allRefundMoney"));

        return orderResponse;
    }

    // 获取统计数据
    @Override
    public Map<String, Object> getStatistics(OrderRequest orderRequest){

        PayOrderExample e = new PayOrderExample();
        PayOrderExample.Criteria c = e.createCriteria();

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
        if ( orderRequest.getPaytype() != null ){
            c.andPaytypeEqualTo(orderRequest.getPaytype());
        }
        if ( orderRequest.getOrderstatus() != null ){
            c.andOrderstatusEqualTo(orderRequest.getOrderstatus());
        }
        // 退单状态
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

        // 汇总统计
        Map<String, Object> map = new HashMap<>();
        map.put("allOrdersCount", (long)payOrderMapper.countByExample(e));
        BigDecimal allOrdersMoney = payOrderCustomMapper.selectOrderpriceSum(e);
        allOrdersMoney = allOrdersMoney == null ? new BigDecimal(0) : allOrdersMoney;
        map.put("allOrdersMoney", allOrdersMoney);
        c.andSinglestatusEqualTo((byte)7);
        BigDecimal allRefundMoney = payOrderCustomMapper.selectOrderpriceSum(e);
        allRefundMoney = allRefundMoney == null ? new BigDecimal(0) : allRefundMoney;
        map.put("allRefundMoney", allRefundMoney);

        return map;
    }

    @Override
    public int insOrder(OrderInfo orderInfo){
        PayOrder payOrder = new PayOrder();
        payOrder.setOrdertype((byte)1);
        payOrder.setOrderid(orderInfo.getOrderid());
        payOrder.setForeignOrderid(orderInfo.getForeignOrderid());
        payOrder.setForeignRequest(orderInfo.getForeignRequest());
        payOrder.setReturnUrl(orderInfo.getReturnUrl());
        payOrder.setMtsid(orderInfo.getMtsid());
        payOrder.setUid(orderInfo.getUid());
        payOrder.setPaytype(7);
        payOrder.setOrderprice(orderInfo.getOrderprice());
        payOrder.setOrderselprice(orderInfo.getOrderselprice());
        payOrder.setOrdernum(orderInfo.getOrdernum());
        payOrder.setOrdername(orderInfo.getOrdername());
        payOrder.setOrderstatus(orderInfo.getOrderstatus());
        payOrder.setSinglestatus(orderInfo.getSinglestatus());
        payOrder.setAddtime(orderInfo.getAddtime());

        return payOrderMapper.insertSelective(payOrder);
    }

    @Override
    public OrderInfo getOrderByOrderidAndForeignOrderid(String orderid, String platformOrderid, boolean forInterface){
        PayOrderExample e = new PayOrderExample();
        PayOrderExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(platformOrderid);
        c.andForeignOrderidEqualTo(orderid);
        List<PayOrder> list = payOrderMapper.selectByExample(e);

        if ( forInterface && list == null){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(), JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());
        }

        OrderInfo orderInfo = new OrderInfo();
        BeanCopier beanCopier = BeanCopier.create(PayOrder.class, OrderInfo.class, false);
        beanCopier.copy(list.get(0), orderInfo, null);

        return orderInfo;
    }

    @Override
    public int updateOrdername(OrderInfo orderInfo, boolean forInterface){
        // 判断这条记录是否存在
        getOrderByOrderid(orderInfo.getOrderid(),true);

        // 开始更新
        PayOrderExample e = new PayOrderExample();
        PayOrderExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(orderInfo.getOrderid());

        PayOrder payOrder = new PayOrder();
        payOrder.setOrdername(orderInfo.getOrdername());

        return payOrderMapper.updateByExampleSelective(payOrder, e);
    }

    @Override
    public OrderInfo getOrderByOrderid(String orderid, boolean forInterface){
        PayOrderExample e = new PayOrderExample();
        PayOrderExample.Criteria c = e.createCriteria();
        c.andOrderidEqualTo(orderid);

        List<PayOrder> list = payOrderMapper.selectByExample(e);

        if ( forInterface && list == null){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(), JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());
        }

        OrderInfo orderInfo = new OrderInfo();
        BeanCopier beanCopier = BeanCopier.create(PayOrder.class, OrderInfo.class, false);
        beanCopier.copy(list.get(0), orderInfo, null);

        return orderInfo;
    }
}
