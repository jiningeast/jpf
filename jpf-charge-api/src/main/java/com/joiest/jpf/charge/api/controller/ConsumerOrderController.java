package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.ArithmeticUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.ChargeOrderInterfaceRequest;
import com.joiest.jpf.dto.GetChargeOrderResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeConsumerOrderInfo;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.facade.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import com.joiest.jpf.common.po.PayChargeConsumerOrder;
import com.joiest.jpf.common.po.PayShopBargainRechargeOrder;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ConsumerOrderServiceFacade;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopBargainRechargeOrderServiceFacade;
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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;
    /**
     * 下单接口
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/payConsumerOrder",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String  payConsumerOrder(HttpServletRequest request, HttpServletResponse response){

        Map<String,Object> responseMap =new HashMap<String,Object>();

        if(!"OPEN".equals(ConfigUtil.getValue("API_IS_OPEN"))){

            responseMap.put("code",JpfInterfaceErrorInfo.API_IS_OPEN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.API_IS_OPEN.getDesc());
            return JsonUtils.toJson(responseMap);
        }
        String merchNo = request.getParameter("merchNo");
        //金额
        String money =request.getParameter("money");
        //签名串
        String sign = request.getParameter("sign");
        //数据验证
        Map<String,Object> resultMap = CheckData(merchNo,money,sign);

        String orderNo="";

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

        //单笔金额验证
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
        if(new BigDecimal(1000000).compareTo(new BigDecimal(money))<0){
            responseMap.put("code",JpfInterfaceErrorInfo.ONE_MONEY_MAX.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.ONE_MONEY_MAX.getDesc());
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
        BigDecimal moneyTotal = shopBargainRechargeOrderServiceFacade.getMoneyTotal(ConfigUtil.getValue("USERID"));
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
    @RequestMapping(value="/matchingDataTask",method = RequestMethod.GET,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String matchingDataTask(){
        logger.info("开始执行匹配Start");
        //查询是否存在需要待执行的任务
        String ret = "数据匹配成功";
        //先查询redis 该任务是否可以执行，如果不能执行，返回请等待
        String flag = redisCustomServiceFacade.get("matchingDataTaskFlag");
        if(StringUtils.isNotBlank(flag)&&StringUtils.equals("run",flag)){
            ret = "目前多条数据在匹配中，请等待.......";
            return ret;
        }
        PayChargeConsumerOrder payChargeConsumerOrder = consumerOrderServiceFacade.selectConsumerOrderTask();
        if(payChargeConsumerOrder!=null){ //不为空准备执行任务
            redisCustomServiceFacade.set("matchingDataTaskFlag","run",0);
            Long size = redisCustomServiceFacade.getSize("consumerOrderQueue");
            String num = ArithmeticUtils.div(payChargeConsumerOrder.getMoney().toString(), "50", 0);
            if(size.longValue() != 0){
                //删除list
                redisCustomServiceFacade.remove("consumerOrderQueue");
            }
            logger.info("拉取金额:"+payChargeConsumerOrder.getMoney()+",拉取条数"+num+" 加10，并压入队列");
            Long redis_length =Long.parseLong(ArithmeticUtils.add(num,"10").toString());
            pushDateToRedis(redis_length);
            logger.info("拉取订单号:"+payChargeConsumerOrder.getOrderNo()+"所需数据，并压入队列");

            try{
                consumerOrderServiceFacade.matchingDataTaskStart(payChargeConsumerOrder);
            }catch (Exception e){
                logger.error(e.getMessage());
                redisCustomServiceFacade.set("matchingDataTaskFlag","stop",0);
                ret="无匹配的记录";
                return ret;
            }
            redisCustomServiceFacade.set("matchingDataTaskFlag","stop",0);
            ret=ret+",订单号:"+payChargeConsumerOrder.getOrderNo();

        }
        logger.info("开始执行匹配end");
        return ret;
    }

    /**
     * 匹配数据到redis
     */
    @RequestMapping(value="/pushDataToRedisTask",method = RequestMethod.GET,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String  pushDataToRedisTask(){
        logger.info("开始push数据"+new Date());
        //首先获取redis的consumerOrderQueue 队列的长度，
        String ret = "数据push成功";
        try {
            Long size = redisCustomServiceFacade.getSize("consumerOrderQueue");
            Long redis_length = Long.valueOf(ConfigUtil.getValue("REDIS_LENGTH"));
            if(size.longValue() < redis_length){//保证list集合的size 最多是1000
                //开始执行匹配操作
                long querySize = redis_length-size;
                pushDateToRedis(querySize);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            ret="数据push失败";
        }
        logger.info("结束push数据"+new Date());
        return ret;
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
    /**
     * 订单查询列表
     * orderNo  订单号
     * startGmtCreate  开始时间
     * endGmtCreate     结束时间
     * pageSize     最大条数
     * currentPage     当前页
     * sign     签名
     * */
    @RequestMapping(value = "/orderList",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String orderList(HttpServletRequest request, HttpServletResponse response){

        //接口返回参数数据
        Map<String,Object> responseMap = new HashMap<>();

        if(!ConfigUtil.getValue("API_IS_OPEN").equals("OPEN")){

            responseMap.put("code",JpfInterfaceErrorInfo.API_IS_OPEN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.API_IS_OPEN.getDesc());
            return JsonUtils.toJson(responseMap);
        }

        //下单订单号
        String pullOrderNo = request.getParameter("pullOrderNo");

        //商户号
        String merchNo = request.getParameter("merchNo");

        //返回最大条数
        String pageSize = request.getParameter("pageSize");

        //当前页
        String currentPage = request.getParameter("currentPage");

        //签名串
        String sign = request.getParameter("sign");


        //参数不合法
        if( StringUtils.isBlank(merchNo) || StringUtils.isBlank(pageSize) || StringUtils.isBlank(currentPage) || StringUtils.isBlank(pullOrderNo) ){

            responseMap.put("code", JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        //缺少签名参数
        if( sign == null || StringUtils.isBlank(sign)){

            responseMap.put("code",JpfInterfaceErrorInfo.NO_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.NO_SIGN.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        //查询订单信息
        ChargeOrderInterfaceRequest requstRecgarge=new ChargeOrderInterfaceRequest();
        Map<String,Object> map = new HashMap<>();

        if( StringUtils.isNotBlank(pullOrderNo) ){

            map.put("pullOrderNo",pullOrderNo);
            requstRecgarge.setConsumerOrderNo(pullOrderNo);

        }
        if( StringUtils.isNotBlank(merchNo) ){

            map.put("merchNo",merchNo);
            requstRecgarge.setMerchNo(merchNo);

        }
        if( StringUtils.isNotBlank(pageSize) ){

            if( Integer.valueOf(pageSize) > 500 ){

                responseMap.put("code",JpfInterfaceErrorInfo.IS_MAXPARAM.getCode());
                responseMap.put("info",JpfInterfaceErrorInfo.IS_MAXPARAM.getDesc());

                return JsonUtils.toJson(responseMap);
            }
            map.put("pageSize",pageSize);
            requstRecgarge.setPageSize(pageSize);
        }
        if( StringUtils.isNotBlank(currentPage) ){

            map.put("currentPage",currentPage);
            requstRecgarge.setPage(currentPage);
        }

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);
        //商户密码
        ChargeCompanyInfo record = new ChargeCompanyInfo();
        record.setMerchNo(merchNo);
        ChargeCompanyInfo result = chargeCompanyServiceFacade.getOne(record);

        //商户不存在
        if(result == null || result.getIsDel() == 1 ){

            responseMap.put("code",JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());
            return JsonUtils.toJson(responseMap);

        }
        //商户删除 或者  商户关闭服务
        if( result.getIsFreeze() == 1 ){

            responseMap.put("code",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getDesc());
            return JsonUtils.toJson(responseMap);
        }
        String  privateKey = result.getPrivateKey();

        //校验来源数据是否合法
        String selfSign = Md5Encrypt.md5(respos+privateKey).toUpperCase();

        if( !selfSign.equals(sign) ){
            responseMap.put("code",JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());
            return JsonUtils.toJson(responseMap);
        }
        //查询单号是否匹配完
        ChargeConsumerOrderInfo chargeConsumerOrderInfo=consumerOrderServiceFacade.getOneByOrderNo(pullOrderNo);
        if( chargeConsumerOrderInfo == null ){

            responseMap.put("code",JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getDesc());

            return JsonUtils.toJson(responseMap);
        }
        if( chargeConsumerOrderInfo.getStatus()!= 2 ){

            responseMap.put("code",JpfInterfaceErrorInfo.ORDER_STATUS.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.ORDER_STATUS.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        //查询列表数据
        GetChargeOrderResponse responseRecharge=chargeOrderServiceFacade.getRecordsInterface(requstRecgarge);

        if( responseRecharge.getList() == null || responseRecharge.getList().size() < 1 ){

            responseMap.put("code",JpfInterfaceErrorInfo.ORDER_STATUS.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.ORDER_STATUS.getDesc());

            return JsonUtils.toJson(responseMap);
        }
        List<ChargeOrderInfo> listRecharge=responseRecharge.getList();

        JSONArray arrayRecharge=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        //拼装数据
        Map<String,Object> returnMap=new HashMap<>();

        for ( ChargeOrderInfo one : listRecharge ){

            jsonObject.put("id",one.getId());//序号
            jsonObject.put("orderNo",one.getOrderNo());//订单号
            jsonObject.put("companyName",one.getCompanyName());//企业名称
            jsonObject.put("merchNo",one.getMerchNo());//商户号
            jsonObject.put("chargePhone",one.getChargePhone());//充值号码
            jsonObject.put("productType",one.getProductType());//产品；类型
            jsonObject.put("productName",one.getProductName());//商品名称
            //jsonObject.put("productPrice",one.getProductPrice());//商品价格
            jsonObject.put("productAmount",one.getProductAmount());//商品数量
            jsonObject.put("productValue",one.getProductValue());//商品价格
            //jsonObject.put("totalMoney",one.getTotalMoney());//订单金额
            jsonObject.put("status",one.getStatus());//订单状态
            jsonObject.put("consumerOrderNo",one.getConsumerOrderNo());//拉取单号
            //时间格式转换
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String addtime= null;
            try {
                addtime = formatter.format(one.getAddtime());
            } catch (Exception e) {
                addtime="";
            }
            String updatetime= null;
            try {
                updatetime = formatter.format(one.getUpdatetime());
            } catch (Exception e) {
                updatetime="";
            }
            jsonObject.put("addtime",addtime);//下单时间
            jsonObject.put("updatetime",updatetime);
            arrayRecharge.add(jsonObject);
        }
        returnMap.put("count",responseRecharge.getList().size());
        returnMap.put("sumMoney",chargeConsumerOrderInfo.getMatchMoney());//匹配的总金额
        returnMap.put("list",arrayRecharge);
        //返回数据
        responseMap.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        responseMap.put("info",JpfInterfaceErrorInfo.SUCCESS.getDesc());
        responseMap.put("data",returnMap);

        return JsonUtils.toJson(responseMap);

    }

    public static void main(String[] args) {
        System.out.println(Md5Encrypt.md5("merchNo=MC1541126548324168863&money=20000.00imyHcZOzMmhukCqB").toUpperCase());
    }

}
