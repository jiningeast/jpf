package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.charge.api.util.ServletUtils;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.OfpayRequest;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("flowRecharge")
@Scope("prototype")
public class FlowRechargeController {

    /**
    * 商户信息
    * */
    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;

    @Autowired
    private ChargeProductServiceFacade chargeProductServiceFacade;

    @Autowired
    private ChargeInterfaceStreamFacade chargeInterfaceStreamFacade;

    @Autowired
    private ChargeCompanyMoneyStreamServiceFacade chargeCompanyMoneyStreamServiceFacade;

    private static final Logger logger = LogManager.getLogger(FlowRechargeController.class);

    private ChargeCompanyInfo companyInfo = new ChargeCompanyInfo();
    private Map<String,String> actParam = new HashMap<>();
    private Map<String,Object> actTreeParam = new TreeMap<>();
    private Boolean validate = true;
    private String respond = "";

    @ModelAttribute
    public String beforAction(HttpServletRequest request) throws Exception{

        companyInfo = new ChargeCompanyInfo();
        actParam = new HashMap<>();
        actTreeParam = new TreeMap<>();
        /*validate = true;
        respond = null;*/
        //需要过滤的方法执行此
        String requestURI = request.getRequestURI();
        String method_name = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        if ( !method_name.equals("ofpayNotifyUrl") || !method_name.equals("ofpayNotifyTest")) {

            JSONObject respondParam = new JSONObject();
            respondParam.put("code","10008");
            respondParam.put("info","参数错误");

            Enumeration<String> requestParam = request.getParameterNames();
            while(requestParam.hasMoreElements()) {

                String paraName = requestParam.nextElement();
                if(!request.getParameter(paraName).isEmpty())
                    actParam.put(paraName,request.getParameter(paraName));
            }
            actTreeParam.putAll(actParam);
            String outsign = actParam.get("sign");

            if(!actParam.containsKey("service") || actParam.get("service").isEmpty()){

                validate = false;
                respond = respondParam.toString();
            }
            //基础参数验证通过
            if(validate.equals(true)){

                Class classGet = Class.forName("com.joiest.jpf.charge.api.controller.FlowRechargeController");
                Object obj = classGet.newInstance();
                Method method = classGet.getMethod(actParam.get("service"), Map.class);
                Boolean valite= (Boolean) method.invoke(obj, actParam);
                //Boolean valite= (Boolean)placeOrderVal(actParam);
            }
            if(validate.equals(true)){

                //验证签名
                actTreeParam.remove("sign");
                companyInfo.setMerchNo(actTreeParam.get("merchNo").toString());
                companyInfo = chargeCompanyServiceFacade.getOne(companyInfo);
                if(companyInfo==null){

                    validate = false;
                    respondParam.put("info","商户信息有误");
                    respond = respondParam.toString();
                }else{

                    String respos = ToolUtils.mapToUrl(actTreeParam);
                    String selfSign = Md5Encrypt.md5(respos+companyInfo.getPrivateKey()).toUpperCase();
                 /*   if(!selfSign.equals(outsign)){
                        logger.info("自签名："+respos+companyInfo.getPrivateKey()+"，传值签名："+outsign);
                        validate = false;
                        respondParam.put("info","签名有误");
                        respond = respondParam.toString();
                    }*/
                }
            }

            StringBuilder sbf = new StringBuilder();
            sbf.append("\n\nTime:" + DateUtils.getCurDate());
            sbf.append("\n接口类型:充值入口");
            sbf.append("\n访问地址："+request.getRequestURL().toString());
            sbf.append("\n请求参数：" + JSONObject.fromObject(actParam).toString());
            sbf.append("\n返回："+respond);
            String fileName = "ApiEntrance";
            String path = "/logs/jpf-charge-api/log/";
            LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
        }

        return "1";
    }

   /* @RequestMapping(value = "telPlaceOrder",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public String telPlaceOrder(){
        if(validate.equals(false)){
            return respond;
        }
        JSONObject responseParam = new JSONObject();
        //商户订单验证
        PayChargeOrder record = new PayChargeOrder();
        record.setForeignOrderNo(actParam.get("outOrderNo"));
        ChargeOrderInfo chargeOrderInfo = chargeOrderServiceFacade.getOne(record);
        //获取商品信息
        ChargeProductInfo chargeProductInfo = chargeProductServiceFacade.getProductById(actParam.get("productId"));
        if(chargeOrderInfo!=null){
            responseParam.put("code","10021");
            responseParam.put("info","订单号请保持唯一");
            return responseParam.toString();
        }
        //String moneyCode = ToolUtils.CreateCode(companyInfo.getMoney().toString(),companyInfo.getId(),ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        Boolean newCode = ToolUtils.ValidateCode(companyInfo.getMoneyCode(),companyInfo.getId(),companyInfo.getMoney().toString(),ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        logger.info("金额码："+companyInfo.getMoneyCode()+"，id:"+companyInfo.getId()+"，金额："+companyInfo.getMoney().toString()+"，校验码："+ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        System.out.println(companyInfo);
        if(!newCode.booleanValue()){
            responseParam.put("code","10022");
            responseParam.put("info","商户金额校验错误");
            return responseParam.toString();
        }

        if(chargeProductInfo == null){
            responseParam.put("code","10023");
            responseParam.put("info","未获取到商品信息");
            return responseParam.toString();
        }
        if(chargeProductInfo.getValue().compareTo(companyInfo.getMoney())>0){
            responseParam.put("code","10024");
            responseParam.put("info","商户余额不足，请尽快充值");
            return responseParam.toString();
        }
        actParam.put("money",chargeProductInfo.getValue().toString());//充值面值
        actParam.put("salePrice",chargeProductInfo.getValue().toString());//标准售价
        ChargeOrderInfo placeOrderInfo = new ChargeOrderInfo();
        String orderno = "CH"+ToolUtils.createOrderid();
        placeOrderInfo.setOrderNo(orderno);
        placeOrderInfo.setForeignOrderNo(actParam.get("outOrderNo"));
        placeOrderInfo.setCompanyId(companyInfo.getId());
        placeOrderInfo.setCompanyName(companyInfo.getCompanyName());
        placeOrderInfo.setMerchNo(companyInfo.getMerchNo());
        placeOrderInfo.setChargePhone(actParam.get("phone"));
        placeOrderInfo.setProductId(chargeProductInfo.getId());
        placeOrderInfo.setProductName(chargeProductInfo.getName());
        placeOrderInfo.setProductPrice(chargeProductInfo.getSalePrice());
        placeOrderInfo.setProductAmount((Integer) 1);
        placeOrderInfo.setTotalMoney(chargeProductInfo.getSalePrice());
        placeOrderInfo.setNotifyUrl(actParam.get("notifyUrl"));
        placeOrderInfo.setRequestParams(JSONObject.fromObject(actParam).toString());
        placeOrderInfo.setStatus((byte)0);
        placeOrderInfo.setAddtime(new Date());
        int orderId = chargeOrderServiceFacade.placeOrder(placeOrderInfo);
        actParam.put("selfOrder",orderno);

        //调用接口之前，先扣钱 更新商户信息  金额验证
        BigDecimal companyMoney = companyInfo.getMoney().subtract(chargeProductInfo.getSalePrice());
        String moneyCode = ToolUtils.CreateCode(companyMoney.toString(),companyInfo.getId(),ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        ChargeCompanyInfo comInfo = new ChargeCompanyInfo();
        comInfo.setId(companyInfo.getId());
        comInfo.setMoneyCode(moneyCode);
        comInfo.setMoney(companyMoney);
        chargeCompanyServiceFacade.updateColumnByPrimaryKey(comInfo);

        ChargeOrderInfo upOrderInfo = new ChargeOrderInfo();
        upOrderInfo.setId(""+orderId);
        // 获取orderid的个位数，0,1时用欧非接口，2-9用威能接口 0=欧非 1=威能 （威能价格便宜，多用威能）
        String lastNum = StringUtils.substring(String.valueOf(orderId),-1,String.valueOf(orderId).length());
        Map<String, String> map = null;
        Byte type=1;
        if (StringUtils.isBlank(ConfigUtil.getValue("OF_AND_WEINENG"))||(Integer.parseInt(lastNum)>=Integer.parseInt(ConfigUtil.getValue("OF_AND_WEINENG")))){
            type = 0;
            upOrderInfo.setInterfaceType(type);
            upOrderInfo.setProductType(0);
            //更新订单接口类型 防止请求接口异常 订单没有类型
            chargeOrderServiceFacade.upOrderInfo(upOrderInfo);
            //请求欧非
            map = phoneRechargeOf(actParam);
            logger.info("oufei"+map.get("orderid"));
        }else {
            upOrderInfo.setInterfaceType(type);
            upOrderInfo.setProductType(1);
            //更新订单接口类型 防止请求接口异常 订单没有类型
            chargeOrderServiceFacade.upOrderInfo(upOrderInfo);
            actParam.put("forProductId",chargeProductInfo.getWnProductId());
            //请求微能接口
            logger.info("weinengStart");
            map = chargeOrderServiceFacade.phoneRechargeWn(actParam);
            logger.info("weineng"+map.get("orderid"));
        }
        //添加流水
        ChargeInterfaceStreamInfo chargeInterfaceStreamInfo = new ChargeInterfaceStreamInfo();
        chargeInterfaceStreamInfo.setOrderId(""+orderId);
        chargeInterfaceStreamInfo.setOrderNo(orderno);
        chargeInterfaceStreamInfo.setType(type);
        chargeInterfaceStreamInfo.setRequestUrl(map.get("requestUrl"));
        chargeInterfaceStreamInfo.setRequestParam(map.get("requestParam"));
        chargeInterfaceStreamInfo.setResponse(map.get("responseParam"));
        chargeInterfaceStreamInfo.setAddtime(new Date());
        chargeInterfaceStreamFacade.addStream(chargeInterfaceStreamInfo);

        JSONObject merRespons = new JSONObject();
        merRespons.put("outOrderNo",actParam.get("outOrderNo"));//上游接口订单号
        merRespons.put("orderNo",orderno);//自己平台的订单号
        merRespons.put("phone",actParam.get("phone"));//充值手机号
        merRespons.put("value",chargeProductInfo.getValue());//充值面值
        merRespons.put("salePrice",chargeProductInfo.getSalePrice());//扣商户的钱
        merRespons.put("productId",actParam.get("productId"));//产品金额
        merRespons.put("foreignOrderNo",map.get("orderid"));//返回欧非或者威能订单号

        if("10000".equals(map.get("code"))||"10001".equals(map.get("code"))){
            if("10000".equals(map.get("code"))){
                upOrderInfo.setStatus((byte)1);
                responseParam.put("code","10000");
                responseParam.put("info","下单成功，充值中");
            }else{
                upOrderInfo.setStatus((byte)8);
                responseParam.put("code","10001");
                responseParam.put("info","下单异常，充值中");
            }
            upOrderInfo.setInterfaceOrderNo(map.get("orderid"));

            // 新增资金流水
            ChargeCompanyMoneyStreamInfo info = new ChargeCompanyMoneyStreamInfo();
            String streamNo = "MS"+System.currentTimeMillis()+ToolUtils.getRandomInt(100,999);
            info.setStreamNo(streamNo);
            info.setCompanyId(companyInfo.getId());
            info.setCompanyName(companyInfo.getCompanyName());
            info.setMerchNo(companyInfo.getMerchNo());
            info.setOrderId(""+orderId);
            info.setOrderNo(orderno);
            info.setProductId(chargeProductInfo.getId());
            info.setProductName(chargeProductInfo.getName());
            info.setProductValue(chargeProductInfo.getValue());
            if(type.equals((byte)1)){
                info.setProductBidPrice(chargeProductInfo.getWnProductPrice());
            }else{
                info.setProductBidPrice(chargeProductInfo.getOfProductPrice());
            }
            info.setProductSalePrice(chargeProductInfo.getSalePrice());
            info.setProductInterfacePrice(chargeProductInfo.getBidPrice());
            info.setProductAmount(1);
            info.setTotalMoney(chargeProductInfo.getSalePrice());
            info.setInterfaceType(type);
            info.setInterfaceOrderNo(map.get("orderid"));
            info.setStatus((byte)2);
            info.setStreamType((byte)1);
            info.setNewMoney(companyMoney);
            info.setIsDel((byte)0);
            info.setAddtime(new Date());
            ChargeCompanyMoneyStreamServiceFacade.insRecord(info);
        }else{
            upOrderInfo.setStatus((byte)3);
            responseParam.put("code","10025");
            responseParam.put("info","充值失败");
        }
        responseParam.put("data",merRespons);
        //修改订单信息
        upOrderInfo.setUpdatetime(new Date());
        chargeOrderServiceFacade.upOrderInfo(upOrderInfo);
        logger.info("backLogInfo="+responseParam.toString());
        return responseParam.toString();
    }*/
    @RequestMapping(value = "telPlaceOrder",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public String telPlaceOrderNew(){
        if(validate.equals(false)){
             return respond;
         }
        //商户订单验证
        PayChargeOrder record = new PayChargeOrder();
        record.setForeignOrderNo(actParam.get("outOrderNo"));
        ChargeOrderInfo chargeOrderInfo = chargeOrderServiceFacade.getOne(record);
        //获取商品信息
        ChargeProductInfo chargeProductInfo = chargeProductServiceFacade.getProductById(actParam.get("productId"));
        //验证基本信息
        JSONObject responseParam  = checkOrderInfo(chargeOrderInfo, chargeProductInfo);
        if(responseParam.get("code")!=null){
            return responseParam.toString();
        }
        PayChargeOrder payChargeOrder;
        JSONObject merRespons = new JSONObject();

        try{
            actParam.put("money",chargeProductInfo.getValue().toString());//充值面值
            actParam.put("salePrice",chargeProductInfo.getValue().toString());//标准售价
            //保存订单号和流水，并且做扣款操作 返回订单的id
            payChargeOrder= chargeOrderServiceFacade.savePayOrder(actParam,companyInfo,chargeProductInfo);
            actParam.put("selfOrder",payChargeOrder.getOrderNo());
            //请求欧非或者微能
            Map<String, String> map = payMoney(chargeProductInfo, payChargeOrder);
            //判断是否支付成功
            if("10000".equals(map.get("code"))||"10001".equals(map.get("code"))){
                if("10000".equals(map.get("code"))){
                    payChargeOrder.setStatus((byte)1);
                }else{
                    payChargeOrder.setStatus((byte)8);
                }
                responseParam.put("code","10000");
                responseParam.put("info","下单成功，充值中");
                payChargeOrder.setInterfaceOrderNo(map.get("orderid"));
            }else{
                payChargeOrder.setStatus((byte)3);
                responseParam.put("code","10025");
                responseParam.put("info","充值失败");
            }
            merRespons.put("outOrderNo",actParam.get("outOrderNo"));//上游接口订单号
            merRespons.put("phone",actParam.get("phone"));//充值手机号
            merRespons.put("productId",actParam.get("productId"));//产品金额
            merRespons.put("orderNo",payChargeOrder.getOrderNo());//自己平台的订单号
            merRespons.put("value",chargeProductInfo.getValue());//充值面值
            merRespons.put("salePrice",chargeProductInfo.getSalePrice());//扣商户的钱
            merRespons.put("foreignOrderNo",map.get("orderid"));//返回欧非或者威能订单号
            responseParam.put("data",merRespons);
            //修改订单信息
            payChargeOrder.setUpdatetime(new Date());
            chargeOrderServiceFacade.updateOrder(payChargeOrder);
        }catch (Exception e){
            responseParam.put("code","10025");
            responseParam.put("info","充值失败");
            logger.error(e.getMessage(),e);
        }
        logger.info("backLogInfo="+responseParam.toString());
        return responseParam.toString();
    }

    private  Map<String, String> payMoney(ChargeProductInfo chargeProductInfo, PayChargeOrder payChargeOrder) {
        //添加流水
        ChargeInterfaceStreamInfo chargeInterfaceStreamInfo = new ChargeInterfaceStreamInfo();
        Map<String, String> map=new HashMap<>();
        String lastNum = StringUtils.substring(String.valueOf(payChargeOrder.getId()),-1,String.valueOf(payChargeOrder.getId()).length());
        if (StringUtils.isBlank(ConfigUtil.getValue("OF_AND_WEINENG"))||(Integer.parseInt(lastNum)>=Integer.parseInt(ConfigUtil.getValue("OF_AND_WEINENG")))){
            payChargeOrder.setInterfaceType((byte)0);
            payChargeOrder.setProductType(0);
            chargeInterfaceStreamInfo.setType((byte)0);
            //更新订单接口类型 防止请求接口异常 订单没有类型
            chargeOrderServiceFacade.updateOrder(payChargeOrder);
            //请求欧非
            map = phoneRechargeOf(actParam);
        }else {
            payChargeOrder.setInterfaceType((byte)1);
            payChargeOrder.setProductType(1);
            chargeInterfaceStreamInfo.setType((byte)1);
            //更新订单接口类型 防止请求接口异常 订单没有类型
            chargeOrderServiceFacade.updateOrder(payChargeOrder);
            actParam.put("forProductId",chargeProductInfo.getWnProductId());
            //请求微能接口
            map = chargeOrderServiceFacade.phoneRechargeWn(actParam);
        }

        chargeInterfaceStreamInfo.setOrderId(""+payChargeOrder.getId());
        chargeInterfaceStreamInfo.setOrderNo(payChargeOrder.getOrderNo());
        chargeInterfaceStreamInfo.setRequestUrl(map.get("requestUrl"));
        chargeInterfaceStreamInfo.setRequestParam(map.get("requestParam"));
        chargeInterfaceStreamInfo.setResponse(map.get("responseParam"));
        chargeInterfaceStreamInfo.setAddtime(new Date());
        chargeInterfaceStreamFacade.addStream(chargeInterfaceStreamInfo);
        return map;
    }

    /**
     * 做订单信息的验证
     * @param chargeOrderInfo
     * @param chargeProductInfo
     * @return
     */
    private JSONObject checkOrderInfo( ChargeOrderInfo chargeOrderInfo, ChargeProductInfo chargeProductInfo) {
        JSONObject responseParam = new JSONObject();
        if(chargeOrderInfo!=null){
            responseParam.put("code","10021");
            responseParam.put("info","订单号请保持唯一");
            return responseParam;
        }
        Boolean newCode = ToolUtils.ValidateCode(companyInfo.getMoneyCode(),companyInfo.getId(),companyInfo.getMoney().toString(), ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        if(!newCode.booleanValue()){
            responseParam.put("code","10022");
            responseParam.put("info","商户金额校验错误");
            return responseParam;
        }

        if(chargeProductInfo == null){
            responseParam.put("code","10023");
            responseParam.put("info","未获取到商品信息");
            return responseParam;
        }
        if(chargeProductInfo.getValue().compareTo(companyInfo.getMoney())>0){
            responseParam.put("code","10024");
            responseParam.put("info","商户余额不足，请尽快充值");
            return responseParam;
        }
        return new JSONObject();
    }


    /**
     * 话费直充 欧非
     */
    private Map<String, String> phoneRechargeOf(Map<String,String> actParam)
    {

        Map<String, String> resultMap = new HashMap<>();
        //话费充值
        Map<String, Object> rechargeMap = new HashMap<>();
        Map<String, String> responseMap = new HashMap<>();

        rechargeMap.put("cardnum", actParam.get("money"));
        rechargeMap.put("sporder_id", actParam.get("selfOrder"));
        rechargeMap.put("sporder_time", new Date());
        rechargeMap.put("game_userid", actParam.get("phone"));
        //暂定为 1
        rechargeMap.put("buyNum", "1");
        rechargeMap.put("ret_url", ConfigUtil.getValue("notify_url"));
        try {
            responseMap = new OfpayUtils().chargePhone(rechargeMap);
            //欧非订单号
            resultMap.put("orderid",responseMap.get("orderid"));
            resultMap.put("requestUrl",responseMap.get("requestUrl"));
            resultMap.put("requestParam",responseMap.get("requestParam"));
            resultMap.put("responseParam",responseMap.get("responseParam"));
            logger.info("欧非直充接口，充值返回数据 responseDeal"+responseMap.toString());
        } catch (Exception e) {
            logger.error("self单号:"+rechargeMap.get("selfOrder")+"--话费直充欧非接口报错"+e.getMessage(),e);
            responseMap.put("retcode","1");
        }
        // 欧飞接口返回处理
        if (responseMap.containsKey("retcode") && "1".equals(responseMap.get("retcode"))) {
            resultMap.put("code","10000");
        } else {
            resultMap.put("code","10008");
        }
        return resultMap;

    }

//    private Map<String, String> phoneRechargeWn(Map<String,String> actParam)
//    {
//        Map<String, String> resultMap = new HashMap<>();
//        //充值接口
//        JSONObject requestParam = new JSONObject();
//        requestParam.put("mobile",actParam.get("phone"));
//        requestParam.put("productId",actParam.get("forProductId"));
//        requestParam.put("outOrderId",actParam.get("selfOrder"));
//
//        String wnProduct = null;
//        JSONObject responseDeal=null;
//        JSONObject actualDeal=null;
//        try {
//            WnpayUtils wnpayUtils = new WnpayUtils(ConfigUtil.getValue("account"),ConfigUtil.getValue("password"),ConfigUtil.getValue("request_url"));
//            wnProduct = wnpayUtils.flowOrder(requestParam);
//            responseDeal = JSONObject.fromObject(wnProduct);
//            actualDeal = JSONObject.fromObject(responseDeal.get("data").toString());
//            resultMap.put("requestUrl",actualDeal.get("requestUrl")==null?"":actualDeal.get("requestUrl").toString());
//            resultMap.put("requestParam",actualDeal.get("requestParam")==null?"":actualDeal.get("requestParam").toString());
//            resultMap.put("responseParam",actualDeal.get("responseParam")==null?"":actualDeal.get("responseParam").toString());
//            resultMap.put("orderid",actualDeal.get("wnorderid")==null?"":actualDeal.get("wnorderid").toString());
//        } catch (Exception e) {
//            logger.error("self单号"+actParam.get("selfOrder")+"微能的话费直充接口报错了"+e.getMessage(),e);
//            responseDeal=new JSONObject();
//            responseDeal.put("code","10001`");
//        }
//        if(responseDeal!=null&&"10000".equals(responseDeal.get("code"))){
//            resultMap.put("code","10000");
//        }else if(responseDeal!=null&&"10001".equals(responseDeal.get("code"))){
//            resultMap.put("code","10001");
//        }else{
//            resultMap.put("code","10008");
//        }
//        return resultMap;
//    }

    /**
     * 欧非话费充值异步回调接口
     * */
    @RequestMapping(value = "/ofpayNotifyUrl",produces = "text/plain;charset=utf-8")
    public String ofpayNotifyUrl(OfpayRequest request, HttpServletRequest httpRequest){
        try {
            logger.info("欧非回调单号start"+request.getSporder_id());
            //1.流水 2.订单信息 3.更新订单状态
            Map<String, Object> map = ClassUtil.requestToMap(request);
            String json = JsonUtils.toJson(map);

            StringBuilder sbf = new StringBuilder();
            Date date = new Date();
            SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sbf.append("\n\nTime:" + myfmt1.format(date));
            sbf.append("\n充值类型:" + "充值回调");
            sbf.append("\n访问地址：" + ServletUtils.getIpAddr(httpRequest));
            sbf.append("\n访问参数：" + map);

            String fileName = "ofpayNotify";
            String path = "/logs/jpf-charge-api/log/";

            PayChargeOrder payChargeOrder = new PayChargeOrder();
            payChargeOrder.setOrderNo(request.getSporder_id());

            ChargeOrderInfo orderInfo = chargeOrderServiceFacade.getOne(payChargeOrder);//request.getSporder_id()
            if (orderInfo == null){
                sbf.append("\n描述： 订单ID：" + request.getSporder_id() + "不存在");
                LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
                return "N";
            }
            if(orderInfo.getStatus()!=1){
                sbf.append("\n描述： 订单ID：" + request.getSporder_id() + "已处理");
                LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
                return "N";
            }
            //添加流水
            ChargeInterfaceStreamInfo chargeInterfaceStreamInfo = new ChargeInterfaceStreamInfo();
            chargeInterfaceStreamInfo.setOrderId(orderInfo.getId());
            chargeInterfaceStreamInfo.setOrderNo(orderInfo.getOrderNo());
            chargeInterfaceStreamInfo.setType((byte)0);
            chargeInterfaceStreamInfo.setRequestUrl(ServletUtils.getIpAddr(httpRequest));
            //chargeInterfaceStreamInfo.setRequestParam(map.get("requestParam"));
            chargeInterfaceStreamInfo.setResponse(json);
            chargeInterfaceStreamInfo.setAddtime(new Date());
            chargeInterfaceStreamFacade.addStream(chargeInterfaceStreamInfo);
            ChargeProductInfo chargeProductInfo = chargeProductServiceFacade.getProductById(orderInfo.getProductId());
            //更新订单信息
            ChargeOrderInfo upOrderInfo = new ChargeOrderInfo();

            Map<String,Object> sendParam = new HashMap<>();
            sendParam.put("outOrderNo",orderInfo.getForeignOrderNo());
            sendParam.put("orderNo",orderInfo.getOrderNo());
            if ( orderInfo.getProductType() == 0 || orderInfo.getProductType() == 1 ){
                sendParam.put("phone",orderInfo.getChargePhone());
            }else if ( orderInfo.getProductType() == 2 || orderInfo.getProductType() == 3 ){
                sendParam.put("cardNo",orderInfo.getChargePhone());
            }
            sendParam.put("value",chargeProductInfo.getValue());
            sendParam.put("salePrice",orderInfo.getProductPrice().toString());
            sendParam.put("productId",orderInfo.getProductId());
            //更新订单的流水
            List<PayChargeCompanyMoneyStream> streams = chargeCompanyMoneyStreamServiceFacade.getByOrderId(upOrderInfo.getId());
            if (streams!=null&&streams.size()!=0){
                PayChargeCompanyMoneyStream payChargeCompanyMoneyStream = streams.get(0);
                payChargeCompanyMoneyStream.setProductBidPrice(chargeProductInfo.getOfProductPrice());
                payChargeCompanyMoneyStream.setInterfaceType((byte)1);
                payChargeCompanyMoneyStream.setInterfaceOrderNo(orderInfo.getInterfaceOrderNo());
                chargeCompanyMoneyStreamServiceFacade.updateStram(payChargeCompanyMoneyStream);
            }

            if ("9".equals(request.getRet_code())){    //1成功 9失败
                sendParam.put("code","10001");
                sendParam.put("info","充值失败");
                sbf.append("\n订单状态：充值失败");
                //充值失败返还商户资金
                JSONObject isRet=new JSONObject();
                try {
                    isRet = chargeCompanyServiceFacade.returnComfunds(orderInfo);
                    upOrderInfo.setStatus((byte)5);
                }catch (Exception e){
                    logger.error(e.getMessage(),e);
                    upOrderInfo.setStatus((byte)7);
                }
                String remark = StringUtils.isBlank(orderInfo.getRemark())?"["+ DateUtils.getCurDate() + "]:"+isRet.get("info"):orderInfo.getRemark()+"&#13;&#10;["+ DateUtils.getCurDate() + "]:"+isRet.get("info");
                upOrderInfo.setRemark(remark);
                sbf.append("\n充值失败返还商户金额："+isRet.toString());
            }else{
                upOrderInfo.setStatus((byte)2);
                sendParam.put("code","10000");
                sendParam.put("info","充值成功");
                sbf.append("\n订单状态：充值成功");

            }

            sbf.append("\n通知商户地址："+orderInfo.getNotifyUrl());
            sbf.append("\n通知商户参数："+sendParam.toString());
            LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

            upOrderInfo.setId(orderInfo.getId());
            upOrderInfo.setNotifyParams(JSONObject.fromObject(sendParam).toString());
            upOrderInfo.setNotifyTime(new Date());
            upOrderInfo.setUpdatetime(new Date());
            chargeOrderServiceFacade.upOrderInfo(upOrderInfo);
            logger.info("欧非回调单号end"+request.getSporder_id());
            logger.info("回调站远单号start"+request.getSporder_id());
            OkHttpUtils.postForm(orderInfo.getNotifyUrl(),sendParam);
            logger.info("回调站远单号end"+request.getSporder_id());
        } catch (Exception e) {
           logger.error("欧非回调接口出错了，单号"+request.getSporder_id()+",错误信息："+e.getMessage());
        }
        return "Y";
    }

    @RequestMapping(value = "/ofpayNotifyTest")
    public String ofpayNotifyTest(HttpServletRequest request)
    {
        String aa = request.getParameter("code");
        return  "12";
    }

    public Boolean placeOrderVal(Map actParam){
        JSONObject resParam = new JSONObject();
        resParam.put("code","10008");
        resParam.put("info","参数错误");
        respond = resParam.toString();
        if(!actParam.containsKey("merchNo") || !actParam.containsKey("sign") || !actParam.containsKey("dateTime") || !actParam.containsKey("productId") || !actParam.containsKey("outOrderNo") || !actParam.containsKey("phone") || !actParam.containsKey("notifyUrl")){
            validate = false;
            return validate;
        }
        if(StringUtils.isBlank(actParam.get("merchNo").toString()) || StringUtils.isBlank(actParam.get("sign").toString()) || StringUtils.isBlank(actParam.get("dateTime").toString()) || StringUtils.isBlank(actParam.get("productId").toString()) || StringUtils.isBlank(actParam.get("outOrderNo").toString()) || StringUtils.isBlank(actParam.get("phone").toString()) || StringUtils.isBlank(actParam.get("notifyUrl").toString())){
            validate = false;
            return validate;
        }
        String regex = "^((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0,0-9])|(19[8|9])|(16[6]))\\d{8}$";
        //手机号验证
        boolean isMatch = Pattern.matches(regex, actParam.get("phone").toString());
        if(!isMatch){
            resParam.put("info","手机号格式有误");
            this.respond = resParam.toString();
            validate = false;
            return validate;
        }
        if (validate.equals(true)){
            resParam.put("code","10000");
            resParam.put("info","verify through");
        }
        respond = resParam.toString();

        return validate;
    }

    /**
     * 油卡充值
     * @return
     */
   /* @RequestMapping(value = "oilPlaceOrder",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public String oilPlaceOrder(){
        if(validate.equals(false))
            return respond;

        JSONObject responseParam = new JSONObject();
        //商户订单验证
        PayChargeOrder record = new PayChargeOrder();
        record.setForeignOrderNo(actParam.get("outOrderNo"));
        ChargeOrderInfo chargeOrderInfo = chargeOrderServiceFacade.getOne(record);
        if(chargeOrderInfo!=null){
            responseParam.put("code","10021");
            responseParam.put("info","商户订单号请保持唯一");
            return responseParam.toString();
        }
        //String moneyCode = ToolUtils.CreateCode(companyInfo.getMoney().toString(),companyInfo.getId(),ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        Boolean newCode = ToolUtils.ValidateCode(companyInfo.getMoneyCode(),companyInfo.getId(),companyInfo.getMoney().toString(),ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        logger.info("金额码："+companyInfo.getMoneyCode()+"，id:"+companyInfo.getId()+"，金额："+companyInfo.getMoney().toString()+"，校验码："+ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        System.out.println(companyInfo);
        if(newCode.equals(false)){
            responseParam.put("code","10022");
            responseParam.put("info","商户金额校验错误");
            return responseParam.toString();
        }
        //获取商品信息
        ChargeProductInfo chargeProductInfo = chargeProductServiceFacade.getProductById(actParam.get("productId"));
        if(chargeProductInfo == null){
            responseParam.put("code","10023");
            responseParam.put("info","未获取到商品信息");
            return responseParam.toString();
        }
        if(chargeProductInfo.getValue().compareTo(companyInfo.getMoney())>0){
            responseParam.put("code","10024");
            responseParam.put("info","商户余额不足，请尽快充值");
            return responseParam.toString();
        }
        actParam.put("money",chargeProductInfo.getValue().toString());//充值面值
        actParam.put("salePrice",chargeProductInfo.getValue().toString());//标准售价


        ChargeOrderInfo placeOrderInfo = new ChargeOrderInfo();
        String orderno = "CH"+ToolUtils.createOrderid();
        placeOrderInfo.setOrderNo(orderno);
        placeOrderInfo.setForeignOrderNo(actParam.get("outOrderNo"));
        placeOrderInfo.setCompanyId(companyInfo.getId());
        placeOrderInfo.setCompanyName(companyInfo.getCompanyName());
        placeOrderInfo.setMerchNo(companyInfo.getMerchNo());
        placeOrderInfo.setChargePhone(actParam.get("cardNo"));
        placeOrderInfo.setProductId(chargeProductInfo.getId());
        placeOrderInfo.setProductName(chargeProductInfo.getName());
        placeOrderInfo.setProductPrice(chargeProductInfo.getSalePrice());
        placeOrderInfo.setProductAmount((Integer) 1);
        placeOrderInfo.setTotalMoney(chargeProductInfo.getSalePrice());
        placeOrderInfo.setNotifyUrl(actParam.get("notifyUrl"));
        placeOrderInfo.setRequestParams(JSONObject.fromObject(actParam).toString());
        placeOrderInfo.setAddtime(new Date());
        placeOrderInfo.setProductType(Integer.valueOf(actParam.get("oilType")));
        placeOrderInfo.setStatus((byte)0);
        placeOrderInfo.setAddtime(new Date());

        int orderId = chargeOrderServiceFacade.placeOrder(placeOrderInfo);
        actParam.put("selfOrder",orderno);
        actParam.put("productId",chargeProductInfo.getOfProductId());

        ChargeOrderInfo upOrderInfo = new ChargeOrderInfo();
        upOrderInfo.setId(""+orderId);
        upOrderInfo.setInterfaceType((byte)0);

        //先扣除商户的金额，更新商户信息  金额验证
        BigDecimal companyMoney = companyInfo.getMoney().subtract(chargeProductInfo.getSalePrice());
        String moneyCode = ToolUtils.CreateCode(companyMoney.toString(),companyInfo.getId(),ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        ChargeCompanyInfo comInfo = new ChargeCompanyInfo();
        comInfo.setId(companyInfo.getId());
        comInfo.setMoneyCode(moneyCode);
        comInfo.setMoney(companyMoney);
        chargeCompanyServiceFacade.updateColumnByPrimaryKey(comInfo);

        //请求欧非油卡充值
        Map<String, String> map = gasRecharge(actParam);

        //添加流水
        ChargeInterfaceStreamInfo chargeInterfaceStreamInfo = new ChargeInterfaceStreamInfo();
        chargeInterfaceStreamInfo.setOrderId(""+orderId);
        chargeInterfaceStreamInfo.setOrderNo(orderno);
        chargeInterfaceStreamInfo.setType((byte)0);
        chargeInterfaceStreamInfo.setRequestUrl(map.get("requestUrl"));
        chargeInterfaceStreamInfo.setRequestParam(map.get("requestParam"));
        chargeInterfaceStreamInfo.setResponse(map.get("responseParam"));
        chargeInterfaceStreamInfo.setAddtime(new Date());
        chargeInterfaceStreamFacade.addStream(chargeInterfaceStreamInfo);

        JSONObject merRespons = new JSONObject();
        merRespons.put("outOrderNo",actParam.get("outOrderNo"));//上游接口订单号
        merRespons.put("orderNo",orderno);//自己平台的订单号
        merRespons.put("phone",actParam.get("phone"));//充值手机号
        merRespons.put("value",chargeProductInfo.getValue());//充值面值
        merRespons.put("salePrice",chargeProductInfo.getSalePrice());//扣商户的钱
        merRespons.put("productId",chargeProductInfo.getId());//产品id
        merRespons.put("foreignOrderNo",map.get("orderid"));//返回欧非或者威能订单号

        if("10000".equals(map.get("code"))){
            upOrderInfo.setStatus((byte)1);
            responseParam.put("code","10000");
            responseParam.put("info","充值中");
            // 新增资金流水
            ChargeCompanyMoneyStreamInfo info = new ChargeCompanyMoneyStreamInfo();
            String streamNo = "MS"+System.currentTimeMillis()+ToolUtils.getRandomInt(100,999);
            info.setStreamNo(streamNo);
            info.setCompanyId(companyInfo.getId());
            info.setCompanyName(companyInfo.getCompanyName());
            info.setMerchNo(companyInfo.getMerchNo());
            info.setOrderId(""+orderId);
            info.setOrderNo(orderno);
            info.setProductId(chargeProductInfo.getId());
            info.setProductName(chargeProductInfo.getName());
            info.setProductValue(chargeProductInfo.getValue());
            info.setProductBidPrice(chargeProductInfo.getOfProductPrice());
            info.setProductSalePrice(chargeProductInfo.getSalePrice());
            info.setProductInterfacePrice(chargeProductInfo.getBidPrice());
            info.setProductAmount(1);
            info.setTotalMoney(chargeProductInfo.getSalePrice());
            info.setInterfaceType((byte)0);
            info.setInterfaceOrderNo(map.get("orderid"));
            info.setStatus((byte)2);
            info.setStreamType((byte)1);
            info.setNewMoney(companyMoney);
            info.setIsDel((byte)0);
            info.setAddtime(new Date());
            chargeCompanyMoneyStreamServiceFacade.insRecord(info);
        }else{
            upOrderInfo.setStatus((byte)3);
            responseParam.put("code","10025");
            responseParam.put("info","充值失败");
        }
        responseParam.put("data",merRespons);
        //修改订单信息
        upOrderInfo.setInterfaceOrderNo(map.get("orderid"));
        upOrderInfo.setUpdatetime(new Date());
        chargeOrderServiceFacade.upOrderInfo(upOrderInfo);

        return responseParam.toString();
    }*/
    /**
     * 油卡充值
     * @return
     */
    @RequestMapping(value = "oilPlaceOrder",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public String oilPlaceOrder() {
        //商户订单验证
        PayChargeOrder record = new PayChargeOrder();
        record.setForeignOrderNo(actParam.get("outOrderNo"));
        ChargeOrderInfo chargeOrderInfo = chargeOrderServiceFacade.getOne(record);
        //获取商品信息
        ChargeProductInfo chargeProductInfo = chargeProductServiceFacade.getProductById(actParam.get("productId"));
        //验证基本信息
        JSONObject responseParam  = checkOrderInfo(chargeOrderInfo, chargeProductInfo);
        if(responseParam.get("code")!=null){
            return responseParam.toString();
        }
        PayChargeOrder payChargeOrder;
        JSONObject merRespons = new JSONObject();
        merRespons.put("outOrderNo",actParam.get("outOrderNo"));//上游接口订单号
        merRespons.put("phone",actParam.get("phone"));//充值手机号
        merRespons.put("productId",actParam.get("productId"));//产品金额
        try {
            actParam.put("money", chargeProductInfo.getValue().toString());//充值面值
            actParam.put("salePrice", chargeProductInfo.getValue().toString());//标准售价
            //保存订单号和流水，并且做扣款操作 返回订单的id
            payChargeOrder = chargeOrderServiceFacade.savePayOrder(actParam, companyInfo, chargeProductInfo);
            payChargeOrder.setProductType(Integer.valueOf(actParam.get("oilType")));
            actParam.put("selfOrder", payChargeOrder.getOrderNo());
            payChargeOrder.setInterfaceType((byte)0);
            //请求欧非油卡充值
            Map<String, String> map = gasRecharge(actParam);
            //添加流水
            ChargeInterfaceStreamInfo chargeInterfaceStreamInfo = new ChargeInterfaceStreamInfo();
            chargeInterfaceStreamInfo.setOrderId(""+payChargeOrder.getId());
            chargeInterfaceStreamInfo.setOrderNo(payChargeOrder.getOrderNo());
            chargeInterfaceStreamInfo.setType((byte)0);
            chargeInterfaceStreamInfo.setRequestUrl(map.get("requestUrl"));
            chargeInterfaceStreamInfo.setRequestParam(map.get("requestParam"));
            chargeInterfaceStreamInfo.setResponse(map.get("responseParam"));
            chargeInterfaceStreamInfo.setAddtime(new Date());
            chargeInterfaceStreamFacade.addStream(chargeInterfaceStreamInfo);
            merRespons.put("orderNo",payChargeOrder.getOrderNo());//自己平台的订单号
            merRespons.put("foreignOrderNo",map.get("orderid"));//返回欧非或者威能订单号
            if("10000".equals(map.get("code"))){
                payChargeOrder.setStatus((byte)1);
                responseParam.put("code","10000");
                responseParam.put("info","充值中");
            }else{
                payChargeOrder.setStatus((byte)3);
                responseParam.put("code","10025");
                responseParam.put("info","充值失败");
            }
            merRespons.put("phone",actParam.get("phone"));//充值手机号
            merRespons.put("value",chargeProductInfo.getValue());//充值面值
            merRespons.put("salePrice",chargeProductInfo.getSalePrice());//扣商户的钱
            merRespons.put("productId",chargeProductInfo.getId());//产品id
            responseParam.put("data",merRespons);
            //修改订单信息
            payChargeOrder.setInterfaceOrderNo(map.get("orderid"));
            payChargeOrder.setUpdatetime(new Date());
            chargeOrderServiceFacade.updateOrder(payChargeOrder);
        }catch (Exception e){
            responseParam.put("code","10025");
            responseParam.put("info","充值失败");
            logger.error(e.getMessage(),e);
        }
        return responseParam.toString();
    }

    /**
     * 油卡充值
     */
    private Map<String, String> gasRecharge(Map actParam)
    {
        //油卡直充
        Map<String, Object> rechargeMap = new HashMap<>();
        Map<String, String> resultMap = new HashMap<>();
        Map<String, String> responseMap = new HashMap<>();

        rechargeMap.put("cardnum", 1);  // 直充时这里表示数量
        rechargeMap.put("cardid", actParam.get("productId"));
        rechargeMap.put("sporder_id", actParam.get("selfOrder"));
        rechargeMap.put("sporder_time", new Date());
        rechargeMap.put("game_userid", actParam.get("cardNo"));
        rechargeMap.put("chargeType", actParam.get("chargeType"));
        //暂定为 1
        rechargeMap.put("buyNum", "1");
        rechargeMap.put("ret_url", ConfigUtil.getValue("notify_url"));

        try {
            responseMap = new OfpayUtils().chargeGas(rechargeMap);
            //欧非订单号
            resultMap.put("orderid",responseMap.get("orderid"));
            resultMap.put("requestUrl",responseMap.get("requestUrl"));
            resultMap.put("requestParam",responseMap.get("requestParam"));
            resultMap.put("responseParam",responseMap.get("responseParam"));
            logger.info("欧非油卡接口，充值返回数据 responseDeal"+responseMap.toString());
        } catch (Exception e) {
          logger.error("self单号:"+actParam.get("selfOrder")+":油卡充值的接口报错了"+e.getMessage(),e);
            responseMap.put("retcode","1");
        }
        // 欧飞接口返回处理
        if (responseMap.containsKey("retcode") && "1".equals(responseMap.get("retcode"))) {
            resultMap.put("code","10000");
        } else {
            resultMap.put("code","10008");
        }
        return resultMap;
    }
    public Boolean oilOrderVal(Map actParam){

        JSONObject resParam = new JSONObject();
        resParam.put("code","10008");
        resParam.put("info","参数错误");
        respond = resParam.toString();
        if(!actParam.containsKey("merchNo") || !actParam.containsKey("sign") || !actParam.containsKey("dateTime") || !actParam.containsKey("productId") || !actParam.containsKey("outOrderNo") || !actParam.containsKey("cardNo") || !actParam.containsKey("notifyUrl")){

            validate = false;
            return validate;
        }
        if(StringUtils.isBlank(actParam.get("merchNo").toString()) || StringUtils.isBlank(actParam.get("sign").toString()) || StringUtils.isBlank(actParam.get("dateTime").toString()) || StringUtils.isBlank(actParam.get("productId").toString()) || StringUtils.isBlank(actParam.get("outOrderNo").toString()) || StringUtils.isBlank(actParam.get("cardNo").toString()) || StringUtils.isBlank(actParam.get("notifyUrl").toString())){

            validate = false;
            return validate;
        }
        String zsh = "^(100011\\d{13})$";  //中石化：以100011开头共19位
        String zsy = "^(90\\d{14})$";      //中石油：以90开头共16位
        Boolean zshIsTrue = Pattern.compile(zsh).matcher(actParam.get("cardNo").toString()).matches();
        Boolean zsyIsTrue = Pattern.compile(zsy).matcher(actParam.get("cardNo").toString()).matches();
        if (zshIsTrue){//中石化

            actParam.put("oilType","2");
            actParam.put("chargeType","1");
        }else if (zsyIsTrue){//中石油

            actParam.put("oilType","3");
            actParam.put("chargeType","2");
        }else{

            validate = false;
            resParam.put("info","油卡格式错误");
            respond = resParam.toString();
            return validate;
        }
        if (validate.equals(true)){
            resParam.put("code","10000");
            resParam.put("info","verify through");
        }
        respond = resParam.toString();
        return validate;
    }

    public static void main(String[] args) {
        //merchNo=MC15411265483241688&service=placeOrderVa&productId=1007&outOrderNo=111111&phone=18801147519&dateTime=201811260022&notifyUrl=http://www.baidu.com&sign=508900BDA3F42200BDBBFDD19133F390
        System.out.println(Md5Encrypt.md5("Md5(dateTime=201811260022&merchNo=MC15411265483241688&notifyUrl=http://www.baidu.com&outOrderNo=111111&phone=18801147519&productId=1007&service=placeOrderValimyHcZOzMmhukCqB").toUpperCase());
    }

}
