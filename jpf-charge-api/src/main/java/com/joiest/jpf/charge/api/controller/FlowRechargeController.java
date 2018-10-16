package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.charge.api.util.ServletUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.OfpayRequest;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeInterfaceStreamInfo;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.entity.ChargeProductInfo;
import com.joiest.jpf.facade.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("flowRecharge")
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

    ChargeCompanyInfo companyInfo = new ChargeCompanyInfo();
    Map<String,String> actParam = new HashMap<>();
    Map<String,Object> actTreeParam = new TreeMap<>();
    Boolean validate = true;
    String respond = null;

    @ModelAttribute
    public String beforAction(HttpServletRequest request) throws Exception{

        //需要过滤的方法执行此
        String requestURI = request.getRequestURI();
        String method_name = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        if ( !method_name.equals("ofpayNotifyUrl") || !method_name.equals("ofpayNotifyTest")) {

            JSONObject respondParam = new JSONObject();
            respondParam.put("code","10008");
            respondParam.put("info","parameter error");

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
            }
            if(validate.equals(true)){

                //验证签名
                actTreeParam.remove("sign");
                companyInfo.setMerchNo(actTreeParam.get("uniqueId").toString());
                companyInfo = chargeCompanyServiceFacade.getOne(companyInfo);
                if(companyInfo==null){

                    validate = false;
                    respondParam.put("info","商户信息有误");
                    respond = respondParam.toString();
                }else{

                    String respos = ToolUtils.mapToUrl(actTreeParam);
                    String selfSign = Md5Encrypt.md5(respos+companyInfo.getPrivateKey()).toUpperCase();
                    /*if(!selfSign.equals(outsign)){
                        validate = false;
                        respondParam.put("info","签名有误");
                        respond = respondParam.toString();
                    }*/
                }
            }

        }
        return "1";

    }

    @RequestMapping(value = "placeOrder",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String placeOrder(HttpServletRequest request){

        if(validate.equals(false))
            return respond;

        JSONObject responseParam = new JSONObject();
        //接口请求参数
        actParam = actParam;
        //商户信息
        companyInfo = companyInfo;

        //商户订单验证
        PayChargeOrder record = new PayChargeOrder();
        record.setForeignOrderNo(actParam.get("outOrderNo"));
        ChargeOrderInfo chargeOrderInfo = chargeOrderServiceFacade.getOne(record);
        if(chargeOrderInfo!=null){

            responseParam.put("code","10008");
            responseParam.put("info","商户订单号请保持唯一");
            //return responseParam.toString();
        }
        //String moneyCode = ToolUtils.CreateCode(companyInfo.getMoney().toString(),companyInfo.getId(),ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        Boolean newCode = ToolUtils.ValidateCode(companyInfo.getMoneyCode(),companyInfo.getId(),companyInfo.getMoney().toString(),ConfigUtil.getValue("MERCH_VALIDE_CODE"));
        if(newCode.equals(false)){

            responseParam.put("code","10008");
            responseParam.put("info","商户金额校验错误");
            return responseParam.toString();
        }
        //获取商品信息
        ChargeProductInfo chargeProductInfo = chargeProductServiceFacade.getProductById(actParam.get("productId"));
        if(chargeProductInfo == null){

            responseParam.put("code","10008");
            responseParam.put("info","未获取到商品信息");
            return responseParam.toString();
        }
        if(chargeProductInfo.getValue().compareTo(companyInfo.getMoney())>0){

            responseParam.put("code","10008");
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
        placeOrderInfo.setTotalMoney(new BigDecimal(actParam.get("money")));
        placeOrderInfo.setNotifyUrl(actParam.get("notifyUrl"));
        placeOrderInfo.setRequestParams(JSONObject.fromObject(actParam).toString());
        placeOrderInfo.setStatus((byte)0);
        placeOrderInfo.setAddtime(new Date());

        int orderId = chargeOrderServiceFacade.placeOrder(placeOrderInfo);
        actParam.put("selfOrder",orderno);

        ChargeOrderInfo upOrderInfo = new ChargeOrderInfo();
        upOrderInfo.setId(""+orderId);
        // 获取orderid的个位数，0,1时用欧非接口，2-9用威能接口 0=欧非 1=威能 （威能价格便宜，多用威能）
        String lastNum = StringUtils.substring(String.valueOf(orderId),-1,String.valueOf(orderId).length());
        Map<String, String> map = null;
        Byte type=1;
        if ( Integer.parseInt(lastNum) <= 1 ){

            type = 0;
            upOrderInfo.setInterfaceType(type);
            //请求欧非
            map = phoneRechargeOf(actParam);

        }else {

            upOrderInfo.setInterfaceType(type);
            //请求微能接口
            map = phoneRechargeWn(actParam);
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
        merRespons.put("outOrderNo",map.get("orderid"));//上游接口订单号
        merRespons.put("orderNo",orderno);//自己平台的订单号
        merRespons.put("phone",actParam.get("phone"));//充值手机号
        merRespons.put("money",actParam.get("money"));//充值金额
        merRespons.put("productId",orderno);//产品金额

        if(map.get("code").equals("10000")){

            upOrderInfo.setStatus((byte)1);
            responseParam.put("code","10000");
            responseParam.put("info","充值中");

            //更新商户信息  金额验证
            BigDecimal companyMoney = companyInfo.getMoney().subtract(new BigDecimal(actParam.get("money")));
            String moneyCode = ToolUtils.CreateCode(companyMoney.toString(),companyInfo.getId(),ConfigUtil.getValue("MERCH_VALIDE_CODE"));
            ChargeCompanyInfo comInfo = new ChargeCompanyInfo();
            comInfo.setId(companyInfo.getId());
            comInfo.setMoneyCode(moneyCode);
            comInfo.setMoney(companyMoney);

            chargeCompanyServiceFacade.updateColumnByPrimaryKey(comInfo);
        }else{

            upOrderInfo.setStatus((byte)3);
            responseParam.put("code","10008");
            responseParam.put("info","充值失败");
        }
        responseParam.put("data",merRespons);
        //修改订单信息
        upOrderInfo.setInterfaceOrderNo(map.get("orderid"));
        upOrderInfo.setUpdatetime(new Date());

        chargeOrderServiceFacade.upOrderInfo(upOrderInfo);

        return responseParam.toString();
    }
    /**
     * 话费直充 欧非
     */
    private Map<String, String> phoneRechargeOf(Map<String,String> actParam)
    {

        Map<String, String> resultMap = new HashMap<>();
       /*
        //查询
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("phoneno", actParam.get("phone"));
        queryMap.put("pervalue", actParam.get("money"));
        Map<String, String> queryPhoneResponseMap = new OfpayUtils().telquery(queryMap);

        //流水
        ShopInterfaceStreamInfo stream = new ShopInterfaceStreamInfo();
        stream.setType((byte)3);
        stream.setOrderNo(actParam.get("selfOrder"));
        stream.setRequestUrl(queryPhoneResponseMap.get("requestUrl"));
        stream.setRequestContent(queryPhoneResponseMap.get("requestParam"));
        String requestUrl = queryPhoneResponseMap.get("requestUrl");
        String requestParam = queryPhoneResponseMap.get("requestParam");
        queryPhoneResponseMap.remove("requestUrl");
        queryPhoneResponseMap.remove("requestParam");
        String responseJson = JsonUtils.toJson(queryPhoneResponseMap);
        stream.setResponseContent(responseJson);
        stream.setAddtime(new Date());
        int res_addstream = shopInterfaceStreamServiceFacade.addStream(stream);
        if ( queryPhoneResponseMap.getOrDefault("retcode","").equals("1") )
        {
            responseMap.put("code",JpfInterfaceErrorInfo.GOODLIST_IS_MATCH.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.GOODLIST_IS_MATCH.getDesc());

            return responseMap;
        }*/
        //话费充值
        Map<String, Object> rechargeMap = new HashMap<>();
        Map<String, String> responseMap = new HashMap<>();

        rechargeMap.put("cardnum", actParam.get("money"));
        rechargeMap.put("sporder_id", actParam.get("selfOrder"));
        rechargeMap.put("sporder_time", new Date());
        rechargeMap.put("game_userid", actParam.get("phone"));
        rechargeMap.put("buyNum", "1");     //暂定为 1
        rechargeMap.put("ret_url", ConfigUtil.getValue("notify_url"));
        responseMap = new OfpayUtils().chargePhone(rechargeMap);

        resultMap.put("orderid",responseMap.get("orderid"));//欧非订单号
        resultMap.put("requestUrl",responseMap.get("requestUrl"));
        resultMap.put("requestParam",responseMap.get("requestParam"));
        resultMap.put("responseParam",responseMap.get("responseParam"));

        // 欧飞接口返回处理
        if ( responseMap.containsKey("retcode") && responseMap.get("retcode").equals("1") ) {

            resultMap.put("code","10000");
        } else {

            resultMap.put("code","10008");
        }
        return resultMap;
    }
    /**
     * 话费直充 微能
     */
    private Map<String, String> phoneRechargeWn(Map<String,String> actParam)
    {
        Map<String, String> resultMap = new HashMap<>();
        //充值接口
        JSONObject requestParam = new JSONObject();
        requestParam.put("mobile",actParam.get("phone"));
        requestParam.put("productId",actParam.get("productId"));
        requestParam.put("outOrderId",actParam.get("selfOrder"));

        WnpayUtils wnpayUtils = new WnpayUtils(ConfigUtil.getValue("account"),ConfigUtil.getValue("password"),ConfigUtil.getValue("request_url"));
        String wnProduct = wnpayUtils.flowOrder(requestParam);

        JSONObject responseDeal = JSONObject.fromObject(wnProduct);
        JSONObject actualDeal = JSONObject.fromObject(responseDeal.get("data").toString());

        resultMap.put("orderid",actualDeal.get("wnorderid").toString());
        resultMap.put("requestUrl",actualDeal.get("requestUrl").toString());
        resultMap.put("requestParam",actualDeal.get("requestParam").toString());
        resultMap.put("responseParam",actualDeal.get("responseParam").toString());

        if(responseDeal.get("code").toString().equals("10000")){

            resultMap.put("code","10000");
        }else{

            resultMap.put("code","10008");
        }
        return resultMap;
    }
    /**
     * 欧非话费充值异步回调接口
     * */
    @RequestMapping(value = "/ofpayNotifyUrl",produces = "application/json;charset=utf-8")
    public String ofpayNotifyUrl(OfpayRequest request, HttpServletRequest httpRequest)
    {
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
        if ( orderInfo == null )
        {
            sbf.append("\n描述： 订单ID：" + request.getSporder_id() + "不存在");
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

        //更新订单信息
        ChargeOrderInfo upOrderInfo = new ChargeOrderInfo();

        Map<String,Object> sendParam = new HashMap<>();
        sendParam.put("outOrderNo",orderInfo.getForeignOrderNo());
        sendParam.put("orderNo",orderInfo.getOrderNo());
        sendParam.put("phone",orderInfo.getChargePhone());
        sendParam.put("money",orderInfo.getTotalMoney().toString());
        sendParam.put("productId",orderInfo.getProductId());

        if (request.getRet_code().equals("9")){    //1成功 9失败

            upOrderInfo.setStatus((byte)3);

            sendParam.put("code",JpfInterfaceErrorInfo.RECHARGE_FAILD.getCode());
            sendParam.put("info",JpfInterfaceErrorInfo.RECHARGE_FAILD.getCode());
            sbf.append("\n订单状态：充值失败");
        }else{

            upOrderInfo.setStatus((byte)2);
            sendParam.put("code",JpfInterfaceErrorInfo.RECHARGE_SUCCESS.getCode());
            sendParam.put("info",JpfInterfaceErrorInfo.RECHARGE_SUCCESS.getCode());
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

        OkHttpUtils.postForm(orderInfo.getNotifyUrl(),sendParam);
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
        resParam.put("info","parameter error");
        respond = resParam.toString();
        if(!actParam.containsKey("uniqueId") || !actParam.containsKey("sign") || !actParam.containsKey("dateTime") || !actParam.containsKey("productId") || !actParam.containsKey("outOrderNo") || !actParam.containsKey("phone")){

            validate = false;
            return validate;
        }
        if(StringUtils.isBlank(actParam.get("uniqueId").toString()) || StringUtils.isBlank(actParam.get("sign").toString()) || StringUtils.isBlank(actParam.get("dateTime").toString()) || StringUtils.isBlank(actParam.get("productId").toString()) || StringUtils.isBlank(actParam.get("outOrderNo").toString()) || StringUtils.isBlank(actParam.get("phone").toString())){

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


}
