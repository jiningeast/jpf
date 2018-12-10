package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.ArithmeticUtils;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopCouponOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCompanyChargeMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponOrderInfoMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponOrderMapper;
import com.joiest.jpf.entity.CouponOrderInfo;
import com.joiest.jpf.entity.CouponOrderList;
import com.joiest.jpf.entity.PayShopCouponOrderInfoResultInfo;
import com.joiest.jpf.entity.PayShopCouponOrderResultInfo;
import com.joiest.jpf.facade.PayShopCouponOrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: admin
 * @Date: 2018/11/30 10:05
 * @Description:
 */
public class PayShopCouponOrderServiceFacadeImpl implements PayShopCouponOrderServiceFacade {

    @Autowired
    private PayShopCouponOrderMapper payShopCouponOrderMapper;
    @Autowired
    private PayShopCompanyMapper payShopCompanyMapper;
    @Autowired
    private PayShopCompanyChargeMapper payShopCompanyChargeMapper;
    @Autowired
    private PayShopCouponOrderInfoMapper payShopCouponOrderInfoMapper;
    @Autowired
    private PayShopCouponOrderCustomMapper payShopCouponOrderCustomMapper;

    /**
     * 保存订单
     * @param couponOrderList
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCouponOrder(CouponOrderList couponOrderList) {
        PayShopCouponOrder payShopCouponOrder = new PayShopCouponOrder();
        payShopCouponOrder.setOrderNo("XQ"+System.currentTimeMillis()+ ToolUtils.getRandomInt(100000,999999));
        payShopCouponOrder.setCompanyId(couponOrderList.getCompanyId());
        payShopCouponOrder.setTotalNum(Integer.valueOf(couponOrderList.getTotalNum()));
        payShopCouponOrder.setContractId(couponOrderList.getContractId());
        payShopCouponOrder.setContractNo(couponOrderList.getContractNo());
        payShopCouponOrder.setStatus((byte)0);
        payShopCouponOrder.setAddtime(new Date());
        payShopCouponOrder.setTotalMoney(new BigDecimal(couponOrderList.getTotalMoney()));

        //查询企业信息
        PayShopCompany company = payShopCompanyMapper.selectByPrimaryKey(couponOrderList.getCompanyId());
        payShopCouponOrder.setCompanyName(company.getCompanyName());
        //查询合同信息
        PayShopCompanyCharge payShopCompanyCharge = payShopCompanyChargeMapper.selectByPrimaryKey(couponOrderList.getContractId());

        payShopCouponOrder.setCouponMoney(new BigDecimal(couponOrderList.getTotalMoney()));
        payShopCouponOrder.setServiceMoney(ArithmeticUtils.mul(couponOrderList.getTotalMoney(),ArithmeticUtils.div(payShopCompanyCharge.getRate().toString(),"100",3)));
        payShopCouponOrder.setServiceContent(payShopCompanyCharge.getServiceContent());
        payShopCouponOrderCustomMapper.insertSelective(payShopCouponOrder);
        //保存订单的详情信息
        saveCouponOrderInfo(payShopCouponOrder,couponOrderList);
        //扣减合同的钱  此操作只是更新合同上的金额，并非真实的扣除了账户的钱
        payShopCompanyCharge.setBalance(ArithmeticUtils.sub(payShopCompanyCharge.getBalance().toString(),couponOrderList.getTotalMoney()));
        payShopCompanyChargeMapper.updateByPrimaryKeySelective(payShopCompanyCharge);

    }

    /**
     * 根据订单号查询订单
     * @param map
     * @return
     */
    @Override
    public PayShopCouponOrderResultInfo getOrderList(Map<String, Object> map) {
        PayShopCouponOrderExample example = new PayShopCouponOrderExample();
        PayShopCouponOrderExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" id desc ");
        if (map.get("pageSize")==null||Long.valueOf(map.get("pageSize").toString())<= 0){
            example.setPageSize(10);
        }else{
            example.setPageSize(Long.valueOf(map.get("pageSize").toString()));
        }
        if(map.get("pageNo")==null||Long.valueOf(map.get("pageNo").toString())== 0){
            example.setPageNo(1);
        }else{
            example.setPageNo(Long.valueOf(map.get("pageNo").toString()));
        }
       if(map.get("startTime")!=null){
            criteria.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(map.get("startTime").toString(),DateUtils.DATEFORMATSHORT));
        }
        if(map.get("endTime")!=null){
            criteria.andAddtimeLessThan(DateUtils.getFdate(map.get("endTime").toString(),DateUtils.DATEFORMATLONG));
        }
        if(map.get("companyId")!=null){
            criteria.andCompanyIdEqualTo(map.get("companyId").toString());
        }
        if(map.get("status")!=null){
            criteria.andStatusEqualTo((byte)4);
        }else{
            criteria.andStatusNotEqualTo((byte)3);
        }
        List<PayShopCouponOrder> orderList = payShopCouponOrderMapper.selectByExample(example);
        int total = payShopCouponOrderMapper.countByExample(example);
        PayShopCouponOrderResultInfo payShopCouponOrderResultInfo = new PayShopCouponOrderResultInfo();
        payShopCouponOrderResultInfo.setPayShopCouponOrderResults(orderList);
        payShopCouponOrderResultInfo.setTotal(total);
        return payShopCouponOrderResultInfo;
    }



    /**
     * 根据订单号查询订单的详情
     * @param map
     * @return
     */
    @Override
    public PayShopCouponOrderInfoResultInfo getOrderInfo(Map<String,Object> map) {
        PayShopCouponOrderExample example = new PayShopCouponOrderExample();
        PayShopCouponOrderExample.Criteria criteria = example.createCriteria();
        if(map.get("orderNo")!=null){
            criteria.andOrderNoEqualTo(map.get("orderNo").toString());
        }
        if (map.get("pageSize")==null||Long.valueOf(map.get("pageSize").toString())<= 0){
            example.setPageSize(10);
        }else{
            example.setPageSize(Long.valueOf(map.get("pageSize").toString()));
        }
        if(map.get("pageSize")==null||Long.valueOf(map.get("pageNo").toString())== 0){
            example.setPageNo(1);
        }else{
            example.setPageNo(Long.valueOf(map.get("pageNo").toString()));
        }
        List<PayShopCouponOrder> orderList = payShopCouponOrderMapper.selectByExample(example);
        if(orderList==null||orderList.size()==0){
            return null;
        }
        PayShopCouponOrderInfoExample example1=new PayShopCouponOrderInfoExample();
        PayShopCouponOrderInfoExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andOrderIdEqualTo(orderList.get(0).getId());
        List<PayShopCouponOrderInfo> payShopCouponOrderInfos = payShopCouponOrderInfoMapper.selectByExample(example1);
        int total = payShopCouponOrderInfoMapper.countByExample(example1);
        PayShopCouponOrderInfoResultInfo payShopCouponOrderInfoResultInfo = new PayShopCouponOrderInfoResultInfo();
        payShopCouponOrderInfoResultInfo.setPayShopCouponOrderInfos(payShopCouponOrderInfos);
        payShopCouponOrderInfoResultInfo.setTotal(total);
        return payShopCouponOrderInfoResultInfo;
    }

    /**
     * 取消订单
     * @param payShopCouponOrder
     */
    @Override
    public void cancalOrder(PayShopCouponOrder payShopCouponOrder) {
        payShopCouponOrder.setStatus((byte)2);
        payShopCouponOrderMapper.updateByPrimaryKeySelective(payShopCouponOrder);
        //还原合同的余额
        PayShopCompanyCharge payShopCompanyCharge = payShopCompanyChargeMapper.selectByPrimaryKey(payShopCouponOrder.getContractId());
        payShopCompanyCharge.setBalance(ArithmeticUtils.add(payShopCompanyCharge.getBalance().toString(),payShopCouponOrder.getTotalMoney().toString()));
        payShopCompanyChargeMapper.updateByPrimaryKeySelective(payShopCompanyCharge);
    }

    /**
     * 根据订单id 查询订单
     * @param orderId
     * @return
     */
    @Override
    public PayShopCouponOrder getByOrderId(String orderId) {
        return payShopCouponOrderMapper.selectByPrimaryKey(orderId);
    }

    /**
     * 删除订单
     * @param payShopCouponOrder
     */
    @Override
    public void deleteOrder(PayShopCouponOrder payShopCouponOrder) {
        payShopCouponOrder.setStatus((byte)3);
        payShopCouponOrderMapper.updateByPrimaryKeySelective(payShopCouponOrder);
    }

    /**
     * 保存订单的详细信息
     * @param payShopCouponOrder
     * @param couponOrderList
     */
    private void saveCouponOrderInfo(PayShopCouponOrder payShopCouponOrder,CouponOrderList couponOrderList) {
        List<CouponOrderInfo> couponList = couponOrderList.getCouponList();
        for (CouponOrderInfo couponOrderInfo: couponList) {
            PayShopCouponOrderInfo payShopCouponOrderInfo = new PayShopCouponOrderInfo();
            payShopCouponOrderInfo.setNumber(Integer.parseInt(couponOrderInfo.getNumber()));
            payShopCouponOrderInfo.setCouponType(couponOrderInfo.getCoupon_type());
            payShopCouponOrderInfo.setOrderId(payShopCouponOrder.getId());
            payShopCouponOrderInfo.setPrice(new BigDecimal(couponOrderInfo.getPrice()));
            payShopCouponOrderInfo.setTotalPrice(new BigDecimal(couponOrderInfo.getTotalPrice()));
            payShopCouponOrderInfoMapper.insertSelective(payShopCouponOrderInfo);
        }
    }
}
