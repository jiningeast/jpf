package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.*;
import com.joiest.jpf.dto.GetCouponRemainResponse;
import com.joiest.jpf.entity.ShopCouponRemainInfo;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.entity.ShopOrderInterfaceInfo;
import com.joiest.jpf.facade.ShopCouponRemainServiceFacade;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

public class ShopCouponRemainServiceFacadeImpl implements ShopCouponRemainServiceFacade {

    @Autowired
    private PayShopCouponRemainMapper payShopCouponRemainMapper;

    @Autowired
    private PayShopBatchCouponMapper payShopBatchCouponMapper;

    @Autowired
    private PayShopCouponActiveMapper payShopCouponActiveMapper;

    @Autowired
    private PayShopCustomerMapper payShopCustomerMapper;

    @Autowired
    private PayShopCompanyMapper payShopCompanyMapper;

    @Autowired
    private PayShopBatchMapper payShopBatchMapper;

    @Autowired
    private PayShopOrderMapper payShopOrderMapper;

    /**
     * 处理过期的券
     */
    @Override
    public int dealAllExpiredCoupon(){
        PayShopCouponRemainExample e = new PayShopCouponRemainExample();
        PayShopCouponRemainExample.Criteria c = e.createCriteria();
        c.andExpireTimeLessThanOrEqualTo(new Date());
        c.andStatusEqualTo((byte)0);

        List<PayShopCouponRemain> list =  payShopCouponRemainMapper.selectByExample(e);

        return dealExpiredCoupon(list);
    }

    /**
     * 处理个人的过期的券
     */
    @Override
    public int dealCustomerExpiredCoupon(String customerId){
        PayShopCouponRemainExample e = new PayShopCouponRemainExample();
        PayShopCouponRemainExample.Criteria c = e.createCriteria();
        c.andCustomerIdEqualTo(customerId);
        c.andExpireTimeLessThanOrEqualTo(new Date());
        c.andStatusEqualTo((byte)0);

        List<PayShopCouponRemain> list =  payShopCouponRemainMapper.selectByExample(e);

        return dealExpiredCoupon(list);
    }

    /**
     * 处理过期的券
     */
    @Transactional
    public int dealExpiredCoupon(List<PayShopCouponRemain> list){
        int count = 0;
        if ( !list.isEmpty() ){
            for (PayShopCouponRemain payShopCouponRemain:list){
                int douLeft = payShopCouponRemain.getCouponDouLeft();
                PayShopCustomer payShopCustomer = payShopCustomerMapper.selectByPrimaryKey(payShopCouponRemain.getCustomerId());
                PayShopBatchCoupon payShopBatchCoupon = payShopBatchCouponMapper.selectByPrimaryKey(payShopCouponRemain.getCouponId());
                PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(payShopBatchCoupon.getCompanyId());
                PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(payShopBatchCoupon.getBatchId());

                // 更新批次余额表pay_shop_coupon_remain
                payShopCouponRemain.setCouponDouLeft(0);
                payShopCouponRemain.setStatus((byte)2);
                payShopCouponRemain.setUpdatetime(new Date());
                payShopCouponRemainMapper.updateByPrimaryKeySelective(payShopCouponRemain);

                // 更新券详情表pay_shop_batch_coupon
                PayShopBatchCoupon payShopBatchCouponUpdate = new PayShopBatchCoupon();
                payShopBatchCouponUpdate.setId(payShopCouponRemain.getCouponId());
                payShopBatchCouponUpdate.setIsExpired((byte)1);
                payShopBatchCouponUpdate.setUpdatetime(new Date());
                payShopBatchCouponMapper.updateByPrimaryKeySelective(payShopBatchCouponUpdate);

                // 新增日志表一条记录pay_shop_coupon_active
                PayShopCouponActive payShopCouponActive = new PayShopCouponActive();
                payShopCouponActive.setCustomerId(payShopCouponRemain.getCustomerId());
                payShopCouponActive.setCustomerName(payShopCustomer.getName());
                payShopCouponActive.setCompanyId(Integer.parseInt(payShopCompany.getId()));
                payShopCouponActive.setCompanyName(payShopCompany.getCompanyName());
                payShopCouponActive.setBatchId(Integer.parseInt(payShopBatch.getId()));
                payShopCouponActive.setBatchNo(payShopBatch.getBatchNo());
                payShopCouponActive.setCouponNo(payShopBatchCoupon.getCouponNo());
                payShopCouponActive.setActiveCode(payShopBatchCoupon.getActiveCode());
                payShopCouponActive.setPayWay((byte)0);
                payShopCouponActive.setMoney(new BigDecimal(0));
                payShopCouponActive.setDou(douLeft);
                payShopCouponActive.setContent("用户" + payShopCustomer.getName() + "有" + douLeft + "个豆过期");
                payShopCouponActive.setType("3");
                payShopCouponActive.setExpireTime(payShopBatchCoupon.getExpireTime());
                payShopCouponActive.setAddtime(new Date());
                payShopCouponActiveMapper.insertSelective(payShopCouponActive);

                // 客户总豆数量减去一部分pay_shop_customer
                PayShopCustomer payShopCustomerUpdate = new PayShopCustomer();
                int dou = payShopCustomer.getDou() - douLeft;
                String code = ToolUtils.CreateCode(String.valueOf(dou),payShopCouponRemain.getCustomerId());
                payShopCustomerUpdate.setId(payShopCouponRemain.getCustomerId());
                payShopCustomerUpdate.setDou(dou);
                payShopCustomerUpdate.setCode(code);
                payShopCustomerMapper.updateByPrimaryKeySelective(payShopCustomerUpdate);

                count++;
            }
        }

        return count;
    }

    @Override
    public GetCouponRemainResponse getCouponRemainByUidForInterface(String uid) {

        PayShopCouponRemainExample example = new PayShopCouponRemainExample();
        example.setOrderByClause("id ASC");
        PayShopCouponRemainExample.Criteria c = example.createCriteria();
        c.andCustomerIdEqualTo(uid);
        c.andStatusEqualTo((byte)0);
        List<PayShopCouponRemain> list = payShopCouponRemainMapper.selectByExample(example);
        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        List<ShopCouponRemainInfo> resultList = new ArrayList<>();
        int douTotal = 0;
        for ( PayShopCouponRemain one : list)
        {
            ShopCouponRemainInfo info = new ShopCouponRemainInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCouponRemain.class, ShopCouponRemainInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
            douTotal += one.getCouponDouLeft();
        }
        GetCouponRemainResponse response = new GetCouponRemainResponse();
        response.setList(resultList);
        response.setCount(list.size());
        response.setDouTotal(douTotal);

        return response;
    }

    /**
     * 个人支付操作
     */
//    @Transactional
    public int CouponHandler(List<ShopCouponRemainInfo> list, ShopOrderInterfaceInfo orderInfo, ShopCustomerInterfaceInfo userInfo)
    {
        int orderBlance = orderInfo.getTotalDou();  //订单总额

        List<Map<String,String>> coupon_detail = new ArrayList<>();
        Map<String,String> map = new HashMap<>();

        //1:用完  0未用完
        for ( ShopCouponRemainInfo one : list)
        {
            Map<String,String> mapJosn = new HashMap<>();
            mapJosn.put("remainId", list.get(0).getId());
            mapJosn.put("couponId", list.get(0).getCouponId());
            mapJosn.put("couponNo", list.get(0).getCouponNo());

            if ( orderBlance > one.getCouponDouLeft() )
            {
                //全部扣除
                one.setStatus((byte)1);
                Boolean isTrue = doCoupon(one, one.getCouponDouLeft(), orderInfo);      //用完

                orderBlance = orderBlance - one.getCouponDouLeft();     //订单余额金额

                mapJosn.put("deduct", one.getCouponDouLeft().toString());   //此券扣减豆的数量  = 此券数量
                int douBlance = 0;      //本券剩余豆数量
                mapJosn.put("remainDou", String.valueOf(douBlance));   //  全部扣除
            } else if( orderBlance <= one.getCouponDouLeft() )
            {
                byte status = 0;
                if ( orderBlance < one.getCouponDouLeft() )
                {
                    status = 0;    //未扣完
                } else if ( orderBlance == one.getCouponDouLeft() )
                {
                    status = 1;    //全部扣除
                }
                one.setStatus(status);
                Boolean isTrue = doCoupon(one, orderBlance, orderInfo);     //未用完

                int douBlance = one.getCouponDouLeft() - orderBlance;       //本券剩余豆数量

                mapJosn.put("deduct", String.valueOf(orderBlance));         //此券扣减豆的数量 = 订单金额
                mapJosn.put("remainDou", String.valueOf(douBlance));

                orderBlance = 0;    //订单余额金额
            }
            coupon_detail.add(mapJosn);
            if ( orderBlance == 0 ) break;
        }
        String json_couponDetail = JsonUtils.toJson(coupon_detail);
        //更新订单状态
        int res_order = updateOrder(orderInfo, json_couponDetail);
//        if ( res_order == 1 )
//        {
//            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "更新订单信息失败");
//        }
        return res_order;
    }

    /**
     * @param remainInfo
     * @param deduct    需要扣除的豆
     */
//    @Transactional
    public Boolean doCoupon(ShopCouponRemainInfo remainInfo, int deduct, ShopOrderInterfaceInfo orderInfo)
    {
        //1.remain减去金额 2.active log 3.customer减去金额 & code生成
        try{
            PayShopCustomer payShopCustomer = payShopCustomerMapper.selectByPrimaryKey(remainInfo.getCustomerId());
            PayShopBatchCoupon payShopBatchCoupon = payShopBatchCouponMapper.selectByPrimaryKey(remainInfo.getCouponId());
            PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(payShopBatchCoupon.getCompanyId());
            PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(payShopBatchCoupon.getBatchId());

            //更新批次余额表pay_shop_coupon_remain
            PayShopCouponRemain payShopCouponRemain = new PayShopCouponRemain();
            BeanCopier beanCopier = BeanCopier.create(ShopCouponRemainInfo.class, PayShopCouponRemain.class, false);
            beanCopier.copy(remainInfo, payShopCouponRemain, null);
            int remain = payShopCouponRemain.getCouponDouLeft() - deduct;
            payShopCouponRemain.setCouponDouLeft(remain);
            payShopCouponRemain.setUpdatetime(new Date());
            int res_couponRemain = payShopCouponRemainMapper.updateByPrimaryKeySelective(payShopCouponRemain);
            if ( res_couponRemain < 1 )
            {
                throw new Exception("扣除券余额失败");
            }

            // 新增日志表一条记录pay_shop_coupon_active
            PayShopCouponActive payShopCouponActive = new PayShopCouponActive();
            payShopCouponActive.setCustomerId(orderInfo.getCustomerId());
            payShopCouponActive.setCustomerName(orderInfo.getCustomerName());
            payShopCouponActive.setCompanyId(Integer.parseInt(payShopCompany.getId()));
            payShopCouponActive.setCompanyName(payShopCompany.getCompanyName());
            payShopCouponActive.setBatchId(Integer.parseInt(payShopBatch.getId()));
            payShopCouponActive.setBatchNo(payShopBatch.getBatchNo());
            payShopCouponActive.setCouponNo(payShopBatchCoupon.getCouponNo());
            payShopCouponActive.setActiveCode(payShopBatchCoupon.getActiveCode());
            payShopCouponActive.setPayWay(orderInfo.getPayWay());
            payShopCouponActive.setMoney(new BigDecimal("0"));
            payShopCouponActive.setDou(deduct);     //消费豆数量
            payShopCouponActive.setContent("行为:消费;用户ID:" + payShopCustomer.getId() + ";用户名称:" + payShopCustomer.getName() + ";豆消费:" + deduct + ";orderId:" + orderInfo.getId() + ";剩余豆:" + remain);
            payShopCouponActive.setType("1");
            payShopCouponActive.setExpireTime(payShopBatchCoupon.getExpireTime());
            payShopCouponActive.setAddtime(new Date());
            payShopCouponActive.setOrderId(orderInfo.getId());
            int res_couponActive = payShopCouponActiveMapper.insertSelective(payShopCouponActive);
            if ( res_couponActive < 1 )
            {
                throw new Exception("添加券记录失败");
            }

            // 客户总豆数量减去一部分pay_shop_customer
            PayShopCustomer payShopCustomerUpdate = new PayShopCustomer();
            int dou = payShopCustomer.getDou() - deduct;
            String code = ToolUtils.CreateCode(String.valueOf(dou),payShopCouponRemain.getCustomerId());
            payShopCustomerUpdate.setId(orderInfo.getCustomerId());
            payShopCustomerUpdate.setDou(dou);
            payShopCustomerUpdate.setCode(code);
            payShopCustomerUpdate.setUpdatetime(new Date());
            int res_updateCustomCode = payShopCustomerMapper.updateByPrimaryKeySelective(payShopCustomerUpdate);
            if ( res_updateCustomCode < 1 )
            {
                throw new Exception("更新用户余额失败");
            }

            return true;
        }catch (Exception e)
        {
            throw new JpfInterfaceException("10008", e.getMessage());
        }

    }

    /**
     * 更新订单信息
     * @param orderInfo
     * @return
     */
    public int updateOrder(ShopOrderInterfaceInfo orderInfo, String coupon_detail)
    {
        PayShopOrder payShopOrder = new PayShopOrder();
        BeanCopier beanCopier = BeanCopier.create(ShopOrderInterfaceInfo.class, PayShopOrder.class, false);
        beanCopier.copy(orderInfo, payShopOrder, null);

        payShopOrder.setStatus((byte)1);
        payShopOrder.setId(orderInfo.getId());
        payShopOrder.setCouponDetail(coupon_detail);

        PayShopOrderExample example = new PayShopOrderExample();
        PayShopOrderExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(orderInfo.getId());
        c.andCustomerIdEqualTo(orderInfo.getCustomerId());
        c.andOrderNoEqualTo(orderInfo.getOrderNo());

        int res_upOrder = payShopOrderMapper.updateByExample(payShopOrder, example);

        return res_upOrder;
    }


}
