package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopCouponRemainCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.*;
import com.joiest.jpf.dto.GetCouponRemainResponse;
import com.joiest.jpf.dto.GetShopCouponRemainResponse;
import com.joiest.jpf.entity.ShopBargainOrderInfo;
import com.joiest.jpf.entity.ShopCouponRemainInfo;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.entity.ShopOrderInterfaceInfo;
import com.joiest.jpf.facade.ShopCouponRemainServiceFacade;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

    @Autowired
    private PayShopBargainOrderMapper payShopBargainOrderMapper;
    @Autowired
    private PayShopCouponRemainCustomMapper payShopCouponRemainCustomMapper;

    /**
     * 处理过期的券
     */
    @Override
    public int dealAllExpiredCoupon(){

        PayShopCouponRemainExample e = new PayShopCouponRemainExample();
        PayShopCouponRemainExample.Criteria c = e.createCriteria();
        PayShopCouponRemainExample.Criteria cOr = e.createCriteria();
        c.andExpireTimeLessThanOrEqualTo(new Date());
        c.andStatusEqualTo((byte)0);
        //改版2018-11-22
        cOr.andSalestatusEqualTo((byte)0);
        cOr.andExpireTimeLessThanOrEqualTo(new Date());
        e.or(cOr);
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
        PayShopCouponRemainExample.Criteria cOr = e.createCriteria();
        c.andCustomerIdEqualTo(customerId);
        c.andExpireTimeLessThanOrEqualTo(new Date());
        c.andStatusEqualTo((byte)0);

        cOr.andCustomerIdEqualTo(customerId);
        cOr.andExpireTimeLessThanOrEqualTo(new Date());
        cOr.andSalestatusEqualTo((byte)0);
        e.or(cOr);
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
                //改版===
                int douSale=payShopCouponRemain.getSaleDouLeft();
                int countDou=douLeft+douSale;
                PayShopCustomer payShopCustomer = payShopCustomerMapper.selectByPrimaryKey(payShopCouponRemain.getCustomerId());
                PayShopBatchCoupon payShopBatchCoupon = payShopBatchCouponMapper.selectByPrimaryKey(payShopCouponRemain.getCouponId());
                PayShopCompany payShopCompany = payShopCompanyMapper.selectByPrimaryKey(payShopBatchCoupon.getCompanyId());
                PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(payShopBatchCoupon.getBatchId());

                // 更新批次余额表pay_shop_coupon_remain
                payShopCouponRemain.setCouponDouLeft(0);
                //转让额度清零2018-11-22
                payShopCouponRemain.setSaleDouLeft(0);
                payShopCouponRemain.setSalestatus((byte)2);
                //------end
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
                payShopCouponActive.setDou(countDou);
                payShopCouponActive.setContent("用户" + payShopCustomer.getName() + "有" + countDou + "个豆过期");
                payShopCouponActive.setType("3");
                payShopCouponActive.setExpireTime(payShopBatchCoupon.getExpireTime());
                payShopCouponActive.setAddtime(new Date());
                payShopCouponActiveMapper.insertSelective(payShopCouponActive);

                // 客户总豆数量减去一部分pay_shop_customer
                PayShopCustomer payShopCustomerUpdate = new PayShopCustomer();
                int dou = payShopCustomer.getDou() - countDou;
                //改版
                int saleDou=payShopCustomer.getSaleDou()-douSale;
                String code = ToolUtils.CreateCode(String.valueOf(dou),payShopCouponRemain.getCustomerId());
                payShopCustomerUpdate.setId(payShopCouponRemain.getCustomerId());
                payShopCustomerUpdate.setDou(dou);
                payShopCustomerUpdate.setSaleDou(saleDou);
                payShopCustomerUpdate.setCode(code);
                payShopCustomerMapper.updateByPrimaryKeySelective(payShopCustomerUpdate);

                count++;
            }
        }

        return count;
    }

//    @Override
//    public GetCouponRemainResponse getCouponRemainByUidForInterface(String uid) {
//
//        PayShopCouponRemainExample example = new PayShopCouponRemainExample();
//        example.setOrderByClause("id ASC");
//        PayShopCouponRemainExample.Criteria c = example.createCriteria();
//        PayShopCouponRemainExample.Criteria cOr = example.createCriteria();
//        c.andCustomerIdEqualTo(uid);
//        cOr.andStatusEqualTo((byte)0);
//        cOr.andStatusEqualTo((byte)3);
//        example.or(cOr);
//        List<PayShopCouponRemain> list = payShopCouponRemainMapper.selectByExample(example);
//        if ( list.isEmpty() || list == null )
//        {
//            return null;
//        }
//        List<ShopCouponRemainInfo> resultList = new ArrayList<>();
//        int douTotal = 0;
//        for ( PayShopCouponRemain one : list)
//        {
//            ShopCouponRemainInfo info = new ShopCouponRemainInfo();
//            BeanCopier beanCopier = BeanCopier.create(PayShopCouponRemain.class, ShopCouponRemainInfo.class, false);
//            beanCopier.copy(one, info, null);
//            resultList.add(info);
//            douTotal += one.getCouponDouLeft();
//        }
//        GetCouponRemainResponse response = new GetCouponRemainResponse();
//        response.setList(resultList);
//        response.setCount(list.size());
//        response.setDouTotal(douTotal);
//
//        return response;
//    }

    /**
     * 重新修改 2018-11-22
     * @param uid
     * @return
     */
    @Override
    public GetCouponRemainResponse getCouponRemainByUidForInterface(String uid) {

        PayShopCouponRemainExample example = new PayShopCouponRemainExample();
        example.setOrderByClause("id ASC");
        PayShopCouponRemainExample.Criteria c = example.createCriteria();
        PayShopCouponRemainExample.Criteria cOr = example.createCriteria();
        c.andCustomerIdEqualTo(uid);
        c.andStatusEqualTo((byte)0);
        //改版2018-11-22
        cOr.andCustomerIdEqualTo(uid);
        cOr.andSalestatusEqualTo((byte)0);
        example.or(cOr);

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
     * 个人支付操作整合改版
     */
    @Override
    @Transactional
    public int CouponHandler( List<ShopCouponRemainInfo> list,ShopOrderInterfaceInfo orderInfo, ShopCustomerInterfaceInfo userInfo)
    {
        if(orderInfo==null){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "订单信息不存在");
        }
        //获取个人的可转让和不可转让的额度
        GetShopCouponRemainResponse remainResponse=getSum(userInfo.getId());
        List<ShopCouponRemainInfo> saleNo=remainResponse.getSaleNo();//不可转让欣券列表
        int saleNoSum=remainResponse.getSaleNoSum();//不可转让总额
        List<ShopCouponRemainInfo> saleYesBefore=remainResponse.getSaleYes();//可转让欣券列表
        int saleYesSumBefore=remainResponse.getSaleYesSum();//可转让总额
        List<Map<String,String>> coupon_detail = new ArrayList<>();
        List<Map<String,String>> coupon_detailsale = new ArrayList<>();
        //判断当前人是否有可支付的欣券
        String json_couponDetail = null;//表中记录的json券扣除详细
        String json_couponDetailSale = null;//表中记录的json可转让券扣除详细

        if(saleNoSum==0 && saleYesSumBefore==0){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "无可用欣豆");
        }else if((saleNoSum+saleYesSumBefore)<orderInfo.getTotalDou()){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "可用欣豆不足");
        }

        if(saleNo.size()>0 && saleNoSum >0 ){

            int orderBlance = orderInfo.getTotalDou();  //订单总额

            Map<String,String> map = new HashMap<>();

            //1:用完  0未用完
            for ( ShopCouponRemainInfo one : saleNo)
            {
                //查出当前用户的可转让以及不可转让金额
                Map<String,String> mapJosn = new HashMap<>();
                mapJosn.put("remainId", one.getId());
                mapJosn.put("couponId", one.getCouponId());
                mapJosn.put("couponNo", one.getCouponNo());

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
            json_couponDetail=  JsonUtils.toJson(coupon_detail);
        }
        //获取个人的可转让和不可转让的额度
        GetShopCouponRemainResponse remainResponseAfter=getSum(userInfo.getId());
        List<ShopCouponRemainInfo> saleYes=remainResponseAfter.getSaleYes();//可转让欣券列表
        int saleYesSum=remainResponseAfter.getSaleYesSum();//可转让总额

        //如果订单金额大于非转让金额执行扣除转让的百分比
         if(orderInfo.getTotalDou()>saleNoSum && saleYesSum>0 ){

             int orderBlanceAfter = orderInfo.getTotalDou()-saleNoSum;  //剩余的总金额

             for ( ShopCouponRemainInfo oneSale : saleYes)
             {
                 Map<String,String> mapJosnSale = new HashMap<>();
                 mapJosnSale.put("remainId", oneSale.getId());
                 mapJosnSale.put("couponId", oneSale.getCouponId());
                 mapJosnSale.put("couponNo", oneSale.getCouponNo());

                 if ( orderBlanceAfter > oneSale.getSaleDouLeft() )
                 {
                     //全部扣除
                     oneSale.setSalestatus((byte)1);
                     Boolean isTrue = doCouponAfter(oneSale, oneSale.getSaleDouLeft(), orderInfo);      //用完

                     orderBlanceAfter = orderBlanceAfter - oneSale.getSaleDouLeft();     //订单余额金额

                     mapJosnSale.put("deduct", oneSale.getSaleDouLeft().toString());   //此券扣减豆的数量  = 此券数量
                     int douBlance = 0;      //本券剩余豆数量
                     mapJosnSale.put("remainDou", String.valueOf(douBlance));   //  全部扣除
                 } else if( orderBlanceAfter <= oneSale.getSaleDouLeft() )
                 {
                     byte setSalestatus = 0;
                     if ( orderBlanceAfter < oneSale.getSaleDouLeft() )
                     {
                         setSalestatus = 0;    //未扣完
                     } else if ( orderBlanceAfter == oneSale.getSaleDouLeft() )
                     {
                         setSalestatus = 1;    //全部扣除
                     }
                     oneSale.setSalestatus(setSalestatus);
                     Boolean isTrue = doCouponAfter(oneSale, orderBlanceAfter, orderInfo);     //未用完

                     int douBlance = oneSale.getSaleDouLeft() - orderBlanceAfter;       //本券剩余豆数量

                     mapJosnSale.put("deduct", String.valueOf(orderBlanceAfter));         //此券扣减豆的数量 = 订单金额
                     mapJosnSale.put("remainDou", String.valueOf(douBlance));

                     orderBlanceAfter = 0;    //订单余额金额
                 }
                 coupon_detailsale.add(mapJosnSale);
                 if ( orderBlanceAfter == 0 ) break;
             }
             json_couponDetailSale=JsonUtils.toJson(coupon_detailsale);
         }
        //更新订单状态
        orderInfo.setPaytime(new Date());
        int res_order = updateOrderSale(orderInfo, json_couponDetail,json_couponDetailSale);
        if ( res_order < 1 )
        {
            //更新信息
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "更新订单信息失败");
        }
        //=========日志记录==========
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n订单号:"+orderInfo.getOrderNo());
        sbf.append("\n用户信息:"+userInfo);
        sbf.append("\n扣除非转让：" + json_couponDetail);
        sbf.append("\n扣除转让部分：" + json_couponDetailSale);
        String fileName = "DouSalePay";
        String path = "/logs/jpf-market-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        return res_order;
    }




    /**
     * 个人支付操作单减非转让
     */
 /*   @Override
    @Transactional
    public int CouponHandler(List<ShopCouponRemainInfo> list, ShopOrderInterfaceInfo orderInfo, ShopCustomerInterfaceInfo userInfo)
    {
        int orderBlance = orderInfo.getTotalDou();  //订单总额

        List<Map<String,String>> coupon_detail = new ArrayList<>();
        Map<String,String> map = new HashMap<>();

        //1:用完  0未用完
        for ( ShopCouponRemainInfo one : list)
        {
            //查出当前用户的可转让以及不可转让金额
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
        orderInfo.setPaytime(new Date());
        int res_order = updateOrder(orderInfo, json_couponDetail);
        if ( res_order < 1 )
        {
            //更新信息
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "更新订单信息失败");
        }
        return res_order;
    }*/


    /**
     * 转让操作
     */
    @Override
    @Transactional
    public int CouponAttorn(List<ShopCouponRemainInfo> list, ShopBargainOrderInfo orderInfo, ShopCustomerInterfaceInfo userInfo)
    {
        int orderBlance = orderInfo.getDou();  //订单总额

        List<Map<String,String>> coupon_detail = new ArrayList<>();
        Map<String,String> map = new HashMap<>();

        //1:用完  0未用完
        for ( ShopCouponRemainInfo one : list)
        {
            Map<String,String> mapJosn = new HashMap<>();
            mapJosn.put("remainId", one.getId());
            mapJosn.put("couponId", one.getCouponId());
            mapJosn.put("couponNo", one.getCouponNo());

            if ( orderBlance > one.getSaleDouLeft() )
            {
                //全部扣除
                one.setSalestatus((byte)1);
                Boolean isTrue = doCouponAttorn(one, one.getSaleDouLeft(), orderInfo);      //用完

                orderBlance = orderBlance - one.getSaleDouLeft();     //订单余额金额

                mapJosn.put("deduct", one.getSaleDouLeft().toString());   //此券扣减豆的数量  = 此券数量
                int douBlance = 0;      //本券剩余豆数量
                mapJosn.put("remainDou", String.valueOf(douBlance));   //  全部扣除
            } else if( orderBlance <= one.getSaleDouLeft() )
            {
                byte status = 0;
                if ( orderBlance < one.getSaleDouLeft() )
                {
                    status = 0;    //未扣完
                } else if ( orderBlance == one.getSaleDouLeft() )
                {
                    status = 1;    //全部扣除
                }
                one.setSalestatus(status);
                Boolean isTrue = doCouponAttorn(one, orderBlance, orderInfo);     //未用完

                int douBlance = one.getSaleDouLeft() - orderBlance;       //本券剩余豆数量

                mapJosn.put("deduct", String.valueOf(orderBlance));         //此券扣减豆的数量 = 订单金额
                mapJosn.put("remainDou", String.valueOf(douBlance));

                orderBlance = 0;    //订单余额金额
            }
            coupon_detail.add(mapJosn);
            if ( orderBlance == 0 ) break;
        }
        String json_couponDetail = JsonUtils.toJson(coupon_detail);
        //更新订单状态
        orderInfo.setPaytime(new Date());
        int res_order = updateBargainOrder(orderInfo);
        if ( res_order < 1 )
        {
            //更新信息
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "更新订单信息失败");
        }

        //=========转让日志记录==========
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n转让订单号:"+orderInfo.getOrderNo());
        sbf.append("\n用户信息:"+userInfo);
        sbf.append("\n扣除转让部分欣豆：" + json_couponDetail);
        String fileName = "DouTranfer";
        String path = "/logs/jpf-market-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        return res_order;
    }

     /**
     * @param remainInfo
     * @param deduct    转让需要扣除的豆
     */
//    @Transactional
    public Boolean doCouponAttorn(ShopCouponRemainInfo remainInfo, int deduct, ShopBargainOrderInfo orderInfo)
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
            int remain = payShopCouponRemain.getSaleDouLeft() - deduct;
            payShopCouponRemain.setSaleDouLeft(remain);
            payShopCouponRemain.setUpdatetime(new Date());
            int res_couponRemain = payShopCouponRemainMapper.updateByPrimaryKeySelective(payShopCouponRemain);
            if ( res_couponRemain < 1 )
            {
                throw new JpfException("10007","扣除券余额失败");
            }

            // 新增日志表一条记录pay_shop_coupon_active
            PayShopCouponActive payShopCouponActive = new PayShopCouponActive();
            payShopCouponActive.setCustomerId(orderInfo.getSellerCustomerId());
            //根据id查出昵称
            //payShopCouponActive.setCustomerName(orderInfo.getCustomerName());
            payShopCouponActive.setCompanyId(Integer.parseInt(payShopCompany.getId()));
            payShopCouponActive.setCompanyName(payShopCompany.getCompanyName());
            payShopCouponActive.setBatchId(Integer.parseInt(payShopBatch.getId()));
            payShopCouponActive.setBatchNo(payShopBatch.getBatchNo());
            payShopCouponActive.setCouponNo(payShopBatchCoupon.getCouponNo());
            payShopCouponActive.setActiveCode(payShopBatchCoupon.getActiveCode());
            payShopCouponActive.setPayWay((byte)0);
            payShopCouponActive.setMoney(new BigDecimal("0"));
            payShopCouponActive.setDou(deduct);     //消费豆数量
            payShopCouponActive.setContent("行为:欣豆转让;用户ID:" + payShopCustomer.getId() + ";用户名称:" + payShopCustomer.getNickname() + ";欣豆转让:" + deduct + ";orderId:" + orderInfo.getId() + ";剩余豆:" + remain);
            payShopCouponActive.setType("5");
            payShopCouponActive.setExpireTime(payShopBatchCoupon.getExpireTime());
            payShopCouponActive.setAddtime(new Date());
            payShopCouponActive.setBargainOrderId(orderInfo.getId());
            payShopCouponActive.setBargainOrderNo(orderInfo.getOrderNo());
            int res_couponActive = payShopCouponActiveMapper.insertSelective(payShopCouponActive);
            if ( res_couponActive < 1 )
            {
                throw new JpfException("10009","添加券记录失败");
            }

            // 客户总豆数量减去一部分pay_shop_customer
            PayShopCustomer payShopCustomerUpdate = new PayShopCustomer();
            int dou = payShopCustomer.getFreezeDou() - deduct;
            //int countDou=payShopCustomer.getDou()-deduct;
            int saledou=payShopCustomer.getSaleDou()-deduct;
            //String code = ToolUtils.CreateCode(String.valueOf(dou),payShopCouponRemain.getCustomerId());
            payShopCustomerUpdate.setId(orderInfo.getSellerCustomerId());
            payShopCustomerUpdate.setFreezeDou(dou);
            //payShopCustomerUpdate.setSaleDou(saledou);
            //payShopCustomerUpdate.setDou(countDou);
            //payShopCustomerUpdate.setCode(code);
            payShopCustomerUpdate.setUpdatetime(new Date());
            int res_updateCustomCode = payShopCustomerMapper.updateByPrimaryKeySelective(payShopCustomerUpdate);
            if ( res_updateCustomCode < 1 )
            {
                throw new JpfException("10010","更新用户余额失败");
            }

            return true;
        }catch (Exception e)
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), e.getMessage());
        }

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
            payShopCouponActive.setContent("行为:消费;用户ID:" + payShopCustomer.getId() + ";用户名称:" + payShopCustomer.getNickname() + ";豆消费:" + deduct + ";orderId:" + orderInfo.getId() + ";剩余豆:" + remain);
            payShopCouponActive.setType("1");
            payShopCouponActive.setExpireTime(payShopBatchCoupon.getExpireTime());
            payShopCouponActive.setAddtime(new Date());
            payShopCouponActive.setOrderId(orderInfo.getId());
            payShopCouponActive.setOrderNo(orderInfo.getOrderNo());
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
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), e.getMessage());
        }

    }

    /**
     * @param remainInfo
     * @param deduct    支付剩余扣除的豆即转让的豆//改版
     */
//    @Transactional
    public Boolean doCouponAfter(ShopCouponRemainInfo remainInfo, int deduct, ShopOrderInterfaceInfo orderInfo)
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
            int remain = payShopCouponRemain.getSaleDouLeft() - deduct;
            payShopCouponRemain.setSaleDouLeft(remain);
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
            payShopCouponActive.setContent("行为:消费;用户ID:" + payShopCustomer.getId() + ";用户名称:" + payShopCustomer.getNickname() + ";豆消费:" + deduct + ";orderId:" + orderInfo.getId() + ";剩余豆:" + remain);
            payShopCouponActive.setType("1");
            payShopCouponActive.setExpireTime(payShopBatchCoupon.getExpireTime());
            payShopCouponActive.setAddtime(new Date());
            payShopCouponActive.setOrderId(orderInfo.getId());
            payShopCouponActive.setOrderNo(orderInfo.getOrderNo());
            int res_couponActive = payShopCouponActiveMapper.insertSelective(payShopCouponActive);
            if ( res_couponActive < 1 )
            {
                throw new Exception("添加券记录失败");
            }

            // 客户总豆数量减去一部分pay_shop_customer
            PayShopCustomer payShopCustomerUpdate = new PayShopCustomer();
            int dou = payShopCustomer.getDou() - deduct;
            int saleDou = payShopCustomer.getSaleDou() - deduct;
            String code = ToolUtils.CreateCode(String.valueOf(dou),payShopCouponRemain.getCustomerId());
            payShopCustomerUpdate.setId(orderInfo.getCustomerId());
            payShopCustomerUpdate.setDou(dou);
            payShopCustomerUpdate.setSaleDou(saleDou);
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
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), e.getMessage());
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

        payShopOrder.setStatus((byte)1);
        payShopOrder.setId(orderInfo.getId());
        payShopOrder.setCouponDetail(coupon_detail);
        payShopOrder.setPaytime(new Date());

        int res_upOrder = payShopOrderMapper.updateByPrimaryKeySelective(payShopOrder);

        return res_upOrder;
    }
    /**
     * 更新订单信息
     * @param orderInfo
     * @return
     */
    public int updateOrderSale(ShopOrderInterfaceInfo orderInfo, String coupon_detail,String json_couponDetailSale )
    {
        PayShopOrder payShopOrder = new PayShopOrder();

        payShopOrder.setStatus((byte)1);
        payShopOrder.setId(orderInfo.getId());
        if(StringUtils.isNotBlank(coupon_detail) && coupon_detail !=null){
            payShopOrder.setCouponDetail(coupon_detail);
        }
        if(StringUtils.isNotBlank(json_couponDetailSale) &&  json_couponDetailSale!=null){
            payShopOrder.setCouponDetailSale(json_couponDetailSale);
        }
        payShopOrder.setPaytime(new Date());

        int res_upOrder = payShopOrderMapper.updateByPrimaryKeySelective(payShopOrder);

        return res_upOrder;
    }
    /**
     * 更新转让订单信息
     * @param orderInfo
     * @return
     */
    public int updateBargainOrder(ShopBargainOrderInfo orderInfo)
    {
        PayShopBargainOrder payShopBargainOrder = new PayShopBargainOrder();
        payShopBargainOrder.setStatus((byte)3);
        payShopBargainOrder.setId(orderInfo.getId());
        payShopBargainOrder.setPaytime(new Date());
        int res_upOrder = payShopBargainOrderMapper.updateByPrimaryKeySelective(payShopBargainOrder);
        return res_upOrder;
    }

    /**
     * 查询出可转让不可转让的总额及列表
     * @param customId
     * @return
     */
    @Override
    public GetShopCouponRemainResponse getSum(String customId){

        //拼装数据
        GetShopCouponRemainResponse returnMap=new GetShopCouponRemainResponse();
        //查出当前用户的可转让金额总数
        PayShopCouponRemainExample exampleNo=new PayShopCouponRemainExample();
        PayShopCouponRemainExample.Criteria cNo=exampleNo.createCriteria();
        cNo.andCustomerIdEqualTo(customId);
        cNo.andStatusEqualTo((byte)0);
        List<PayShopCouponRemain>saleNo= null;
        try {
            saleNo = payShopCouponRemainMapper.selectByExample(exampleNo);
        } catch (Exception e) {
            saleNo=null;
        }
        int saleNoSum= 0;
        try {
            saleNoSum = payShopCouponRemainCustomMapper.SaleNoSum(exampleNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //不可转换欣券列表
        List<ShopCouponRemainInfo> saleNoInfo = new ArrayList<>();
        for (PayShopCouponRemain one : saleNo)
        {
            ShopCouponRemainInfo info = new ShopCouponRemainInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCouponRemain.class, ShopCouponRemainInfo.class, false);
            beanCopier.copy(one, info, null);
            saleNoInfo.add(info);
        }
        //查出当前用户的非转让金额总数
        PayShopCouponRemainExample exampleYes=new PayShopCouponRemainExample();
        PayShopCouponRemainExample.Criteria cYes=exampleYes.createCriteria();
        cYes.andCustomerIdEqualTo(customId);
        cYes.andSalestatusEqualTo((byte)0);
        int saleYesSum= 0;
        try {
            saleYesSum = payShopCouponRemainCustomMapper.SaleYesSum(exampleYes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //可转换列表
        List<PayShopCouponRemain>saleYes= null;
        try {
            saleYes = payShopCouponRemainMapper.selectByExample(exampleYes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<ShopCouponRemainInfo> saleYesInfo = new ArrayList<>();
        for (PayShopCouponRemain one : saleYes)
        {
            ShopCouponRemainInfo info = new ShopCouponRemainInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCouponRemain.class, ShopCouponRemainInfo.class, false);
            beanCopier.copy(one, info, null);
            saleYesInfo.add(info);
        }
        returnMap.setSaleNoSum(saleNoSum);
        returnMap.setSaleNo(saleNoInfo);
        returnMap.setSaleYesSum(saleYesSum);
        returnMap.setSaleYes(saleYesInfo);

        return returnMap;
    }


}
