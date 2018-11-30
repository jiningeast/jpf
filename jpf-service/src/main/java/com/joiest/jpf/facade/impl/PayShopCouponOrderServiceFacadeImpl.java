package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopCompany;
import com.joiest.jpf.common.po.PayShopCompanyCharge;
import com.joiest.jpf.common.po.PayShopCouponOrder;
import com.joiest.jpf.common.po.PayShopCouponOrderInfo;
import com.joiest.jpf.common.util.ArithmeticUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCompanyChargeMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponOrderInfoMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponOrderMapper;
import com.joiest.jpf.entity.CouponOrderInfo;
import com.joiest.jpf.entity.CouponOrderList;
import com.joiest.jpf.facade.PayShopCouponOrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
        payShopCouponOrder.setServiceContent(new BigDecimal(0));
        payShopCouponOrderMapper.insertSelective(payShopCouponOrder);
        //保存订单的详情信息
        saveCouponOrderInfo(payShopCouponOrder,couponOrderList);
        //扣减合同的钱  此操作只是更新合同上的金额，并非真实的扣除了账户的钱
        payShopCompanyCharge.setBalance(ArithmeticUtils.sub(payShopCompanyCharge.getBalance().toString(),couponOrderList.getTotalMoney()));
        payShopCompanyChargeMapper.updateByPrimaryKeySelective(payShopCompanyCharge);

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
