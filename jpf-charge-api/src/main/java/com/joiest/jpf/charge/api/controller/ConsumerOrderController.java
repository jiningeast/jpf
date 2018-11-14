package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeConsumerOrder;
import com.joiest.jpf.common.po.PayShopBargainRechargeOrder;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ConsumerOrderServiceFacade;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopBargainRechargeOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * 充值记录下单，获取接口
 */
@Controller
@RequestMapping("consumerOrder")
public class ConsumerOrderController {
    private static final Logger logger = LogManager.getLogger(ConsumerOrderController.class);
    @Autowired
    private ShopBargainRechargeOrderServiceFacade shopBargainRechargeOrderServiceFacade;
    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;
    @Autowired
    private ConsumerOrderServiceFacade consumerOrderServiceFacade;
    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;
    /**
     * 下单接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/payConsumerOrder",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String  payConsumerOrder(HttpServletRequest request, HttpServletResponse response){
        String merchNo = request.getParameter("merchNo");
        //金额
        String money =request.getParameter("money");
        //签名串
        String sign = request.getParameter("sign");
        //数据验证
        Map<String,Object> resultMap = CheckData(merchNo,money,sign);

        String orderNo="";
        Map<String,Object> responseMap =new HashMap<String,Object>();
        ChargeCompanyInfo result;
        if(!JpfInterfaceErrorInfo.SUCCESS.getCode().equals(resultMap.get("code"))){
            return  JsonUtils.toJson(resultMap);
        }else {
            //生成订单数据
            ChargeCompanyInfo record = new ChargeCompanyInfo();
            record.setMerchNo(merchNo);
            result = chargeCompanyServiceFacade.getOne(record);
            try {
                orderNo  = consumerOrderServiceFacade.payConsumerOrder(merchNo,money,result);
            } catch (Exception e) {
                responseMap.put("code",JpfInterfaceErrorInfo.CREATE_ORDER_FAILED.getCode());
                responseMap.put("info",JpfInterfaceErrorInfo.CREATE_ORDER_FAILED.getDesc());
                return JsonUtils.toJson(responseMap);
            }
        }
        responseMap.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        responseMap.put("info",JpfInterfaceErrorInfo.SUCCESS.getDesc());
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("orderNo",orderNo);
        responseMap.put("data",data);
        return JsonUtils.toJson(responseMap);
    }

    /**
     * 数据验证接口
     * @param merchNo
     * @param money
     * @param sign
     * @return
     */
    private Map<String,Object> CheckData(String merchNo, String money, String sign){

        //接口返回参数数据
        Map<String,Object> responseMap = new HashMap<>();
        //参数不合法
        if(StringUtils.isBlank(merchNo)){
            responseMap.put("code",JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());
            return responseMap;
        }
        //缺少签名参数
        if( sign== null || StringUtils.isBlank(sign)){
            responseMap.put("code",JpfInterfaceErrorInfo.NO_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.NO_SIGN.getDesc());
            return responseMap;
        }

        Map<String,Object> map = new HashMap<>();
        map.put("merchNo",merchNo);
        map.put("money",money);

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        String respos = ToolUtils.mapToUrl(treeMap);

        //商户密码
        ChargeCompanyInfo record = new ChargeCompanyInfo();
        record.setMerchNo(merchNo);
        ChargeCompanyInfo result = chargeCompanyServiceFacade.getOne(record);
        //商户不存在
        if(result==null || result.getIsDel() == 1 ){
            responseMap.put("code",JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());
            return responseMap;
        }
        //商户删除 或者  商户关闭服务
        if( result.getIsFreeze() == 1 ){
            responseMap.put("code",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getDesc());
            return responseMap;
        }
        String  privateKey = result.getPrivateKey();

        //校验来源数据是否合法
        String selfSign = Md5Encrypt.md5(respos+privateKey).toUpperCase();
        if(!selfSign.equals(sign)){
            responseMap.put("code", JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());
            return responseMap;
        }


        //验证商户目前余额能够购买输入的金额
        if(new BigDecimal(money).compareTo(result.getMoney())>0){
            responseMap.put("code",JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getDesc());
            return responseMap;
        }

        //验证余额
        if(!ToolUtils.ValidateCode(result.getMoneyCode(),result.getId(),result.getMoney().toString(),ConfigUtil.getValue("MERCH_VALIDE_CODE"))){
            responseMap.put("code",JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());
            return responseMap;
        }
        //验证数据的存储量，够不够下单的钱
        BigDecimal moneyTotal = shopBargainRechargeOrderServiceFacade.getMoneyTotal();
        System.out.println(moneyTotal.compareTo(new BigDecimal(money))>0);
        if(moneyTotal.compareTo(new BigDecimal(money))<0){
            responseMap.put("code",JpfInterfaceErrorInfo.EXCESS_DEPOSIT.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.EXCESS_DEPOSIT.getDesc());
            return responseMap;
        }
        responseMap.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        return responseMap;
    }

    /**
     * 匹配数据
     */
    @RequestMapping(value="/matchingDataTask",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public void matchingDataTask(){
        //查询是否存在需要待执行的任务
        PayChargeConsumerOrder payChargeConsumerOrder = consumerOrderServiceFacade.selectConsumerOrderTask();
        if(payChargeConsumerOrder!=null){ //不为空准备执行任务
            //查询redis的队列是不是为空，如果为空不执行
            Long size = redisCustomServiceFacade.getSize("consumerOrderQueue");
            if(size.longValue() != 0){//开始执行匹配操作
                consumerOrderServiceFacade.matchingDataTaskStart(payChargeConsumerOrder);
            }else{
                //拉取数据，存储在list
                pushDateToRedis(1000L);
            }

        }
    }

    /**
     * 匹配数据到redis
     */
    @RequestMapping(value="/pushDataToRedisTask",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public void pushDataToRedisTask(){
        logger.info("开始push数据"+new Date());
        //首先获取redis的consumerOrderQueue 队列的长度，
        try {
            Long size = redisCustomServiceFacade.getSize("consumerOrderQueue");
            if(size.longValue() <1000){//保证list集合的size 最多是1000
                //开始执行匹配操作
                long querySize = 1000-size;
                pushDateToRedis(querySize);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        logger.info("结束push数据"+new Date());
    }

    /**
     * push data to redis
     * @param querySize
     */
    private void pushDateToRedis(long querySize) {
        List<PayShopBargainRechargeOrder> payShopBargainRechargeOrders = shopBargainRechargeOrderServiceFacade.pushDataToRedisTask(querySize);
        if(payShopBargainRechargeOrders!=null&&payShopBargainRechargeOrders.size()!=0){
            for (PayShopBargainRechargeOrder payShopBargainRechargeOrder:payShopBargainRechargeOrders) {
                redisCustomServiceFacade.lpush("consumerOrderQueue", JsonUtils.toJson(payShopBargainRechargeOrder));
            }
        }
    }

    public static void main(String[] args) {
        int i = 0;
        while (true){
            i++;
            if(i==5){
                break;
            }
        }

        System.out.println(i);
    }
}
