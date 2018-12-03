package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayChargeConsumerOrder;
import com.joiest.jpf.common.po.PayChargeConsumerOrderExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeConsumerOrderMapper;
import com.joiest.jpf.entity.ChargeConsumerOrderInfo;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dao.repository.mapper.custom.PayChargeOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopBargainRechargeOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMoneyStreamMapper;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ConsumerOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;


public class ConsumerOrderServiceFacadeImpl implements ConsumerOrderServiceFacade {
    private static final Logger logger = LogManager.getLogger(ConsumerOrderServiceFacadeImpl.class);

    @Autowired
    private PayChargeConsumerOrderMapper payChargeConsumerOrderMapper;
    @Autowired
    private PayChargeCompanyMapper payChargeCompanyMapper;
    @Autowired
    private PayChargeOrderCustomMapper payChargeOrderCustomMapper;
    @Autowired
    private PayChargeCompanyMoneyStreamMapper payChargeCompanyMoneyStreamMapper;
    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;
    @Autowired
    private PayShopBargainRechargeOrderCustomMapper payShopBargainRechargeOrderCustomMapper;

    /**
     *
     * @param orderNo
     * @return
     * 根据订单号获取单条订单信息
     */
    @Override
    public ChargeConsumerOrderInfo getOneByOrderNo(String orderNo){

        if ( StringUtils.isBlank(orderNo) ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单号不能为空!");
        }
        PayChargeConsumerOrderExample e=new PayChargeConsumerOrderExample();
        PayChargeConsumerOrderExample.Criteria c=e.createCriteria();
        c.andOrderNoEqualTo(orderNo);

        List<PayChargeConsumerOrder> list=payChargeConsumerOrderMapper.selectByExample(e);

        if( list == null || list.size() <=0  ){
            return null;
        }
        ChargeConsumerOrderInfo chargeConsumerOrderInfo=new ChargeConsumerOrderInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeConsumerOrder.class,ChargeConsumerOrderInfo.class,false);
        beanCopier.copy(list.get(0),chargeConsumerOrderInfo,null);
        return chargeConsumerOrderInfo;

    }


    @Override
    public String payConsumerOrder(String merchNo, String money, ChargeCompanyInfo result) {
        PayChargeConsumerOrder payChargeConsumerOrder =new PayChargeConsumerOrder();
        //生成订单号码
        String orderNo = "PU"+System.currentTimeMillis()+ ToolUtils.getRandomInt(100000,999999);
        payChargeConsumerOrder.setOrderNo(orderNo);
        payChargeConsumerOrder.setCompanyId(result.getId());
        payChargeConsumerOrder.setMerchNo(result.getMerchNo());
        payChargeConsumerOrder.setStatus((byte)0);
        payChargeConsumerOrder.setAddtime(new Date());
        payChargeConsumerOrder.setMoney(new BigDecimal(money));
        payChargeConsumerOrderMapper.insertSelective(payChargeConsumerOrder);
        return orderNo;
    }

    @Override
    public PayChargeConsumerOrder selectConsumerOrderTask() {
        PayChargeConsumerOrderExample example = new PayChargeConsumerOrderExample();
        PayChargeConsumerOrderExample.Criteria criteria = example.createCriteria();
        example.setPageNo(1);
        example.setPageSize(1);
        //查询为执行的任务
        criteria.andStatusEqualTo((byte)0);
        example.setOrderByClause(" id desc");
        List<PayChargeConsumerOrder> payChargeConsumerOrders = payChargeConsumerOrderMapper.selectByExample(example);
        if(payChargeConsumerOrders!=null&&payChargeConsumerOrders.size()!=0){
            PayChargeConsumerOrder payChargeConsumerOrder = payChargeConsumerOrders.get(0);
            //payChargeConsumerOrder.setStatus((byte)1);
            //更新数据库状态为1 待处理 此处不再更新，因为会有事务问题
            //payChargeConsumerOrderMapper.updateByPrimaryKeySelective(payChargeConsumerOrder);
            return payChargeConsumerOrder;
        }else{
            return null;
        }
    }

    public void matchingDataTask(PayShopBargainRechargeOrder payShopBargainRechargeOrder, PayChargeCompany chargeCompany, PayChargeConsumerOrder payChargeConsumerOrder,String money) {
        //生成真实定单，并且生成正常流水
        //生成订单号
        PayChargeOrder order = new PayChargeOrder();
        String orderId = savePayChargeOrder(chargeCompany.getCompanyName(), payShopBargainRechargeOrder, order,payChargeConsumerOrder.getOrderNo());
        order.setId(orderId);
        //给每条记录保存流水
        savePayChargeMoneyStream(order,money);

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void matchingDataTaskStart(PayChargeConsumerOrder payChargeConsumerOrder) throws  Exception {
        payChargeConsumerOrder.setStatus((byte)1);
        payChargeConsumerOrderMapper.updateByPrimaryKeySelective(payChargeConsumerOrder);
        //拉取数据
        PayChargeCompany chargeCompany =  payChargeCompanyMapper.selectByPrimaryKey(payChargeConsumerOrder.getCompanyId());
        List<PayShopBargainRechargeOrder> list =new ArrayList<>();
        //记录余额
        String newCompanyMoney=chargeCompany.getMoney().toString();
        logger.info("余额"+newCompanyMoney);
        String money = payChargeConsumerOrder.getMoney().toString();
        int i=0;
        while (true){
            logger.info("循环"+i+"开始");
            String json = redisCustomServiceFacade.rPop("consumerOrderQueue");
            PayShopBargainRechargeOrder payShopBargainRechargeOrder = JsonUtils.toObject(json, PayShopBargainRechargeOrder.class);
            //判断剩余的钱是否小于下一条匹配的钱，如果是，直接break
            if(new BigDecimal(money).compareTo(payShopBargainRechargeOrder.getFacePrice())<0){
                //如果这条数据没有匹配，那就重新放回到redis队列中
                redisCustomServiceFacade.lpush("consumerOrderQueue",JsonUtils.toJson(payShopBargainRechargeOrder));
                break;
            }
            money= ArithmeticUtils.sub(money,payShopBargainRechargeOrder.getFacePrice().toString()).toString();
            logger.info("剪之前"+newCompanyMoney);
            newCompanyMoney = ArithmeticUtils.sub(newCompanyMoney,payShopBargainRechargeOrder.getFacePrice().toString()).toString();
            logger.info("剪之后"+newCompanyMoney);
            payShopBargainRechargeOrder.setUpdatetime(new Date());
            payShopBargainRechargeOrder.setPullCompanyId(payChargeConsumerOrder.getCompanyId());
            payShopBargainRechargeOrder.setPullOrderNo(payChargeConsumerOrder.getOrderNo());
            payShopBargainRechargeOrder.setPullMerchNo(payChargeConsumerOrder.getMerchNo());
            payShopBargainRechargeOrder.setMatchingStatus((byte)2);
            //这个地方实际更新对象余额 验证后，发现还是存在线程安全问题
            //chargeCompany.setMoney(ArithmeticUtils.sub(chargeCompany.getMoney().toString(),payShopBargainRechargeOrder.getFacePrice().toString()));
            list.add(payShopBargainRechargeOrder);
            //保存订单信息，并且保存流水信息
            matchingDataTask(payShopBargainRechargeOrder,chargeCompany,payChargeConsumerOrder,newCompanyMoney);
            logger.info("循环"+i+"结束");
        }
        //批量更新shopBargainRechargeOrder
        if(list.size()!=0){
            payShopBargainRechargeOrderCustomMapper.batchUpdatePayShopBro(list);
        }
        //扣减商户的钱
        String totalMoney=ArithmeticUtils.sub(payChargeConsumerOrder.getMoney().toString(),money).toString();
        chargeCompany.setMoney(ArithmeticUtils.sub(chargeCompany.getMoney().toString(),totalMoney));
        chargeCompany.setMoneyCode(Md5Encrypt.md5(chargeCompany.getId()+chargeCompany.getMoney()+ ConfigUtil.getValue("MERCH_VALIDE_CODE")));
        chargeCompany.setUpdatetime(new Date());
        payChargeCompanyMapper.updateByPrimaryKeySelective(chargeCompany);

        //更新订单匹配到的金额和钱
        payChargeConsumerOrder.setMatchMoney(new BigDecimal(totalMoney));
        payChargeConsumerOrder.setMatchRecordsAmount(list.size());
        payChargeConsumerOrder.setStatus((byte)2);
        payChargeConsumerOrderMapper.updateByPrimaryKeySelective(payChargeConsumerOrder);
    }

    /**
     * 新生成系统的订单
     * @param companyName  商户
     * @param payShopBargainRechargeOrder  敬恒的订单流水
     * @param order 新生成的订单
     */
    private String savePayChargeOrder(String companyName,PayShopBargainRechargeOrder payShopBargainRechargeOrder, PayChargeOrder order,String orderNo) {
        String payChargeOrderNo="CH"+ ToolUtils.getRandomInt(100,999)+System.currentTimeMillis()+ToolUtils.getRandomInt(100,999);
        order.setOrderNo(payChargeOrderNo);
        order.setAddtime(new Date());
        order.setChargePhone(payShopBargainRechargeOrder.getChargeNo());
        order.setCompanyId(payShopBargainRechargeOrder.getPullCompanyId());
        order.setCompanyName(companyName);
        order.setForeignOrderNo(payShopBargainRechargeOrder.getForeignOrderNo());
        order.setInterfaceType((byte)2);
        order.setMerchNo(payShopBargainRechargeOrder.getPullMerchNo());
        order.setNotifyUrl("");
        order.setProductAmount(payShopBargainRechargeOrder.getAmt());
        order.setProductId("0");
        order.setProductName(payShopBargainRechargeOrder.getItemName());
        order.setProductPrice(payShopBargainRechargeOrder.getPrice());
        if(payShopBargainRechargeOrder.getOrderType()==2){
            order.setProductType(3);
        }else if(payShopBargainRechargeOrder.getOrderType()==1){
            order.setProductType(2);
        }else{
            order.setProductType(4);
        }
        order.setProductValue(payShopBargainRechargeOrder.getFacePrice());
        order.setStatus((byte)0);
        order.setTotalMoney(payShopBargainRechargeOrder.getAmount());
        order.setConsumerOrderNo(orderNo);
        payChargeOrderCustomMapper.insertSelective(order);
        return order.getId();
    }


    /**
     * 保存交易的流水
     * @param order  新生成的系统订单
     */
    private void savePayChargeMoneyStream(PayChargeOrder order,String money) {
        PayChargeCompanyMoneyStream payChargeCompanyMoneyStream = new PayChargeCompanyMoneyStream();
        String moneyStramNo = "MS"+System.currentTimeMillis()+ ToolUtils.getRandomInt(100,999);
        payChargeCompanyMoneyStream.setMerchNo(order.getMerchNo()); //商户号
        payChargeCompanyMoneyStream.setStreamNo(moneyStramNo);//流水号
        payChargeCompanyMoneyStream.setCompanyId(order.getCompanyId());
        payChargeCompanyMoneyStream.setCompanyName(order.getCompanyName());
        payChargeCompanyMoneyStream.setAddtime(new Date());
        payChargeCompanyMoneyStream.setOrderId(order.getId());
        payChargeCompanyMoneyStream.setOrderNo(order.getOrderNo());
        payChargeCompanyMoneyStream.setInterfaceType((byte)2);
        payChargeCompanyMoneyStream.setIsDel((byte)0);
        logger.info("保存流水前:"+money);
        payChargeCompanyMoneyStream.setNewMoney(new BigDecimal(money));
        logger.info("保存流水后:"+money);
        payChargeCompanyMoneyStream.setProductAmount(order.getProductAmount());
        payChargeCompanyMoneyStream.setProductName(order.getProductName());
        payChargeCompanyMoneyStream.setProductSalePrice(order.getProductPrice());
        payChargeCompanyMoneyStream.setProductValue(order.getProductValue());
        payChargeCompanyMoneyStream.setStatus((byte)1);
        payChargeCompanyMoneyStream.setStreamType((byte)1);
        payChargeCompanyMoneyStream.setTotalMoney(order.getTotalMoney());
        payChargeCompanyMoneyStream.setConsumerOrderNo(order.getConsumerOrderNo());

        payChargeCompanyMoneyStreamMapper.insertSelective(payChargeCompanyMoneyStream);
    }

}
