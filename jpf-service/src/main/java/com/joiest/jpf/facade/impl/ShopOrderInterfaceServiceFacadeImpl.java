package com.joiest.jpf.facade.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.*;
import com.joiest.jpf.entity.ShopOrderInterfaceInfo;
import com.joiest.jpf.facade.ShopOrderInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigDecimal;
import java.util.*;

public class ShopOrderInterfaceServiceFacadeImpl implements ShopOrderInterfaceServiceFacade {

    @Autowired
    private PayShopOrderMapper payShopOrderMapper;

    @Autowired
    private PayShopCouponRemainMapper payShopCouponRemainMapper;

    @Autowired
    private PayShopBatchCouponMapper payShopBatchCouponMapper;

    @Autowired
    private PayShopBatchMapper payShopBatchMapper;

    @Autowired
    private PayShopCouponActiveMapper payShopCouponActiveMapper;

    @Autowired
    private PayShopCustomerMapper payShopCustomerMapper;

    @Override
    public int addOrder(ShopOrderInterfaceInfo info) {

        PayShopOrder order = new PayShopOrder();
        PayShopOrderExample example = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = example.createCriteria();
        BeanCopier beanCopier = BeanCopier.create(ShopOrderInterfaceInfo.class, PayShopOrder.class, false);
        beanCopier.copy(info, order, null);

        return payShopOrderMapper.insertSelective(order);
    }

    @Override
    public ShopOrderInterfaceInfo getOrderOne(String orderNo, String uid) {

        PayShopOrderExample example = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = example.createCriteria();
        c.andOrderNoEqualTo(orderNo);
        c.andCustomerIdEqualTo(uid);
        c.andStatusEqualTo((byte)0);
        List<PayShopOrder> list = payShopOrderMapper.selectByExample(example);
        if ( list == null || list.isEmpty() )
        {
            return null;
        }
        ShopOrderInterfaceInfo info = new ShopOrderInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopOrder.class, ShopOrderInterfaceInfo.class, false);
        beanCopier.copy(list.get(0), info, null);

        return info;
    }

    @Override
    public ShopOrderInterfaceInfo getOrder(String orderNo) {

        PayShopOrderExample example = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = example.createCriteria();
        c.andOrderNoEqualTo(orderNo);
        c.andStatusEqualTo((byte)1);
        List<PayShopOrder> list = payShopOrderMapper.selectByExample(example);
        if ( list == null || list.isEmpty() )
        {
            return null;
        }
        ShopOrderInterfaceInfo info = new ShopOrderInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopOrder.class, ShopOrderInterfaceInfo.class, false);
        beanCopier.copy(list.get(0), info, null);

        return info;
    }

    /**
     * 获取用户订单数量，已取消的不统计
     */
    @Override
    public int getOrdersCount(String customerId){
        PayShopOrderExample e = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = e.createCriteria();
        c.andCustomerIdEqualTo(customerId);
        // c.andStatusNotEqualTo((byte)3);

        return payShopOrderMapper.countByExample(e);
    }

    @Override
    public int updateOrder(ShopOrderInterfaceInfo info) {
        PayShopOrder payShopOrder = new PayShopOrder();
        BeanCopier beanCopier = BeanCopier.create(ShopOrderInterfaceInfo.class, PayShopOrder.class, false);
        beanCopier.copy(info, payShopOrder, null);
        return payShopOrderMapper.updateByPrimaryKeySelective(payShopOrder);
    }

    @Override
    public Map<String,String> cancelOrderDou(String orderNo) {
        Map<String,String> resultMap = new HashMap<>();
        ShopOrderInterfaceInfo orderInfo = getOrder(orderNo);
        if ( orderInfo.getCouponDetail().equals("") )
        {
            resultMap.put("code","-1");
            resultMap.put("msg", "订单豆消费记录为空");
            return resultMap;  //订单豆消费为空
        }
        List<Map<String,String>> couponDetailList = new ArrayList<>();
        couponDetailList = JsonUtils.toObject(orderInfo.getCouponDetail(), List.class);
        Map<String,String> douAddMap = new HashMap<>();
        List<Map<String,String>> doutAddList = new ArrayList<>();
        //[{"deduct":"15","remainId":"1","remainDou":"20","couponNo":"CP2031533283923997641","couponId":"41"}]
        for ( int i=0; i<couponDetailList.size(); i++ )
        {
            //1.remain添加豆数量 2.添加active 3 用户余额&code
            PayShopCouponRemain remain = getCoupon(couponDetailList.get(i).get("remainId"));
            // 用户信息
            PayShopCustomer payShopCustomer = payShopCustomerMapper.selectByPrimaryKey(remain.getCustomerId());
            //券详情
            PayShopBatchCoupon payShopBatchCoupon = payShopBatchCouponMapper.selectByPrimaryKey(remain.getCouponId());
            //批次信息
            PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(payShopBatchCoupon.getBatchId());

            int deduct = Integer.parseInt(couponDetailList.get(i).get("deduct"));
            int curr_total = deduct + remain.getCouponDouLeft();
            remain.setCouponDouLeft(curr_total);
            remain.setUpdatetime(new Date());
            PayShopCouponRemainExample example = new PayShopCouponRemainExample();
            int res_updateRemain = payShopCouponRemainMapper.updateByPrimaryKeySelective(remain);

            douAddMap.put("add", couponDetailList.get(i).get("deduct"));
            douAddMap.put("remainId", remain.getId());
            douAddMap.put("currDouTotal", String.valueOf(curr_total));
            douAddMap.put("couponNo", remain.getCouponNo());
            douAddMap.put("couponId", remain.getCouponId());
            doutAddList.add(douAddMap);

            // 新增日志表一条记录pay_shop_coupon_active
            PayShopCouponActive payShopCouponActive = new PayShopCouponActive();
            payShopCouponActive.setCustomerId(orderInfo.getCustomerId());
            payShopCouponActive.setCustomerName(orderInfo.getCustomerName());
            payShopCouponActive.setCompanyId(Integer.parseInt(payShopBatchCoupon.getCompanyId()));
            payShopCouponActive.setCompanyName(payShopBatchCoupon.getCompanyName());
            payShopCouponActive.setBatchId(Integer.parseInt(payShopBatchCoupon.getBatchId()));
            payShopCouponActive.setBatchNo(payShopBatch.getBatchNo());
            payShopCouponActive.setCouponNo(payShopBatchCoupon.getCouponNo());
            payShopCouponActive.setActiveCode(payShopBatchCoupon.getActiveCode());
            payShopCouponActive.setPayWay(orderInfo.getPayWay());
            payShopCouponActive.setMoney(new BigDecimal("0"));
            payShopCouponActive.setDou(deduct);     //消费豆数量
            payShopCouponActive.setContent("行为:退豆;;退还:" + deduct + ";orderId:" + orderInfo.getId() + ";剩余豆:" + curr_total + ";remainId" + remain.getId() + ";couponNo" + remain.getCouponNo());
            payShopCouponActive.setType("2");
            payShopCouponActive.setExpireTime(payShopBatchCoupon.getExpireTime());
            payShopCouponActive.setAddtime(new Date());
            payShopCouponActive.setOrderId(orderInfo.getId());
            payShopCouponActive.setOrderId(orderInfo.getOrderNo());
            int res_couponActive = payShopCouponActiveMapper.insertSelective(payShopCouponActive);

            // 客户总豆数量减去一部分pay_shop_customer
            PayShopCustomer payShopCustomerUpdate = new PayShopCustomer();
            int dou = payShopCustomer.getDou() + deduct;
            String code = ToolUtils.CreateCode(String.valueOf(dou),remain.getCustomerId());
            payShopCustomerUpdate.setId(orderInfo.getCustomerId());
            payShopCustomerUpdate.setDou(dou);
            payShopCustomerUpdate.setCode(code);
            payShopCustomerUpdate.setUpdatetime(new Date());
            int res_updateCustomCode = payShopCustomerMapper.updateByPrimaryKeySelective(payShopCustomerUpdate);
        }
        String douJson = JsonUtils.toJson(doutAddList);
        resultMap.put("code","1");
        resultMap.put("msg", "退豆操作成功");
        resultMap.put("douJson", douJson);

        return resultMap;   //恢复豆操作成功
    }

    //获取券信息
    public PayShopCouponRemain getCoupon(String remainId)
    {
        PayShopCouponRemain payShopCouponRemain = payShopCouponRemainMapper.selectByPrimaryKey(remainId);
        return payShopCouponRemain;
    }
}
