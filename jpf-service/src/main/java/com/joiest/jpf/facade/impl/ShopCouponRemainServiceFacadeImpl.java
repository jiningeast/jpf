package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Boolean CouponHandler(List<ShopCouponRemainInfo> list, ShopOrderInterfaceInfo orderInfo, ShopCustomerInterfaceInfo userInfo)
    {
        int orderDou = orderInfo.getTotalDou();
        int orderBlance = orderInfo.getTotalDou();  //订单总额

        //1单张券满足 2.需要多张券
        if ( orderDou == list.get(0).getCouponDouLeft() )
        {
            return doCoupon(list.get(0),"1", orderDou);
        }

        if ( orderDou < list.get(0).getCouponDouLeft() )
        {
            return doCoupon(list.get(0),"0", list.get(0).getCouponDouLeft());
        }

        //1:用完  0未用完
        if ( orderDou > list.get(0).getCouponDouLeft() )
        {
            for ( ShopCouponRemainInfo one : list)
            {
                //1.remain减去金额 2.active log 3.customer减去金额 4.code生成

                if ( orderBlance > one.getCouponDouLeft() )
                {
                    orderBlance = orderBlance - one.getCouponDouLeft();
                }
                if ( orderBlance > 0 )
                {
                    Boolean isTrue = doCoupon(one,"1", one.getCouponDouLeft());
                }
                if ( orderBlance < 0 )
                {
                    Boolean isTrue = doCoupon(one,"0", orderBlance); //未用完
                    return isTrue;
                }

            }
        }


        //1.remain减去金额 2.active log 3.customer减去金额 4.code生成
//        // 更新批次余额表pay_shop_coupon_remain
//        PayShopCouponRemain payShopCouponRemain = new PayShopCouponRemain();
//        BeanCopier beanCopier = BeanCopier.create(ShopCouponRemainInfo.class, PayShopCouponRemain.class, false);
//        beanCopier.copy(remainInfo, payShopCouponRemain, null);
//
//        payShopCouponRemainMapper.updateByPrimaryKeySelective(payShopCouponRemain);


        return true;
    }

    public Boolean doCoupon(ShopCouponRemainInfo remainInfo, String type, int money)
    {
        System.out.println("type:" + type);
        System.out.println("money:" + money);
        return true;
    }

}
