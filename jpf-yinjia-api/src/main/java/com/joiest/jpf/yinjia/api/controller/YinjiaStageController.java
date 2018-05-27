package com.joiest.jpf.yinjia.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.BigDecimalCalculateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.SignUtils;
import com.joiest.jpf.dto.YinjiaCreateOrderRequest;
import com.joiest.jpf.dto.YinjiaTermsRequest;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.yinjia.api.constant.ManageConstants;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.joiest.jpf.yinjia.api.constant.ManageConstants.*;

@Controller
@RequestMapping("yinjiastage")
public class YinjiaStageController {

    @Autowired
    private MerchantInterfaceServiceFacade merchantInterfaceServiceFacade;

    @Autowired
    private MerPayTypeServiceFacade merPayTypeServiceFacade;

    @Autowired
    private OrderServiceFacade orderServiceFacade;

    private MerchantInterfaceInfo merchInfo;

    @Autowired
    private YjResponseDto yjResponseDto;

    @Autowired
    private OrderInterfaceServiceFacade orderInterfaceServiceFacade;

    @Autowired
    private OrderCpServiceFacade orderCpServiceFacade;

    @Autowired
    private MerchantServiceFacade merchantServiceFacade;

    @Autowired
    private ChinaPayServiceFacade chinaPayServiceFacade;

    /**
     * 商户获取银联信用卡分期支付的期数
     * @param request 此接口请求类
     * @return 返回固定DTO
     */
    @RequestMapping(value = "/getTerms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public YjResponseDto getTerms(YinjiaTermsRequest request){
        String mid = request.getMid();

        // 检查公钥是否有误
        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("mid",mid);
        String mySign = SignUtils.getSign(requestMap, merchInfo.getPrivateKey(),"UTF-8");
        String requestSign = request.getSign();
        if ( !mySign.equals(request.getSign()) ){   // 判断我们计算的签名和对方传过来的签名是否一致
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());

            return yjResponseDto;
        }

        // 获取该商户银联信用卡分期支付的配置信息
        MerchantPayTypeInfo merchantPayTypeInfo  = merPayTypeServiceFacade.getOneMerPayTypeByTpid(Long.parseLong(mid),7);
        // 判断该商户分期配置是否为空
        if ( StringUtils.isBlank(merchantPayTypeInfo.getBankcatid()) ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.NO_TERMS_CONFIGURATION.getCode(), JpfInterfaceErrorInfo.NO_TERMS_CONFIGURATION.getDesc());
        }
        String bankcatids[] = merchantPayTypeInfo.getBankcatid().split(",");
        StringBuilder sb = new StringBuilder();
        for (String bankcatid:bankcatids){
            switch (bankcatid){
                case "25":
                    sb.append("3,");
                    break;

                case "26":
                    sb.append("6,");
                    break;

                case "27":
                    sb.append("9,");
                    break;

                case "28":
                    sb.append("12,");
                    break;

                case "29":
                    sb.append("15");
                    break;

                case "30":
                    sb.append("24");
                    break;
            }
        }
        String terms = StringUtils.stripEnd(sb.toString(),",");

        // 准备返回给客户
        Map<String,Object> map = new HashMap<>();
        map.put("terms",terms);

        YjResponseDto yjResponseDto = new YjResponseDto();
        yjResponseDto.setData(map);

        return yjResponseDto;
    }

    /**
     * 下单
     * @param request 下单请求类
     * @return 返回固定DTO
     */
    @RequestMapping("/createOrder")
    @ResponseBody
    public YjResponseDto createOrder(YinjiaCreateOrderRequest request){
        // 验签
        Map<String, Object> map = new HashMap<>();
        map.put("orderid", request.getOrderid());
        map.put("mid", request.getMid());
        map.put("productId", request.getProductId());
        map.put("productName", request.getProductName());
        map.put("productAmount", request.getProductAmount());
        map.put("productUnitPrice", request.getProductUnitPrice());
        map.put("productTotalPrice", request.getProductTotalPrice());
        map.put("term", request.getTerm());
        map.put("returnUrl", request.getReturnUrl());
        String mySign = SignUtils.getSign(map, merchInfo.getPrivateKey(), "UTF-8");
        if ( !mySign.equals(request.getSign()) ){   // 判断我们计算的签名和对方传过来的签名是否一致
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());

            return yjResponseDto;
        }

        // 计算价钱是否合理
        Double totalPrice = new Double(request.getProductTotalPrice());
        Double myTotalPrice = BigDecimalCalculateUtils.mul( Double.parseDouble(request.getProductUnitPrice()), Double.parseDouble(request.getProductAmount()) );
        if ( !myTotalPrice.equals(totalPrice) ){
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.WRONG_TOTAL_PRICE.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.WRONG_TOTAL_PRICE.getDesc());

            return yjResponseDto;
        }

        // 判断用户是否有资格使用传过来的分期数
        int catid = 0;
        switch (request.getTerm()){
            // 将传过来的分期数转换成数据库里相应的id
            case "3":
                catid = 25;
                break;

            case "6":
                catid = 26;
                break;

            case "9":
                catid = 27;
                break;

            case "12":
                catid = 28;
                break;

            case "15":
                catid = 29;
                break;

            case "24":
                catid = 30;
                break;
        }
        if ( catid == 0 ){
            // 如果传过来的分期期数不符合预定规则
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.ILLEGAL_TERM.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.ILLEGAL_TERM.getDesc());

            return yjResponseDto;
        }
        MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(Long.parseLong(request.getMid()),7);
        String bankcatids[] = merchantPayTypeInfo.getBankcatid().split(",");
        if ( !ArrayUtils.contains(bankcatids, ""+catid) ){
            // 如果传过来的分期数不在该商户的支持范围内
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.UNSUPPORT_TERM.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.UNSUPPORT_TERM.getDesc());

            return yjResponseDto;
        }

        // 生成订单
        String orderid = createOrderid();
        Map<String, Object> ordernameMap = new HashMap<>();
        ordernameMap.put("payType",7);
        ordernameMap.put("stageType", request.getTerm());
        ordernameMap.put("payType_cn","银联信用卡分期支付");
        ordernameMap.put("stageType_cn",request.getTerm()+"期");
        String ordernameJson = JsonUtils.toJson(ordernameMap);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrdertype((byte)1);
        orderInfo.setOrderid(orderid);
        orderInfo.setForeignOrderid(request.getOrderid());
        map.put("sign",request.getSign());
        orderInfo.setForeignRequest(map.toString().replaceAll(", ","&"));
        orderInfo.setReturnUrl(request.getReturnUrl());
        orderInfo.setMtsid(Long.parseLong(request.getMid()));
        orderInfo.setUid((long)0);
        orderInfo.setPaytype(7);
        orderInfo.setOrderprice(new BigDecimal(request.getProductTotalPrice()));
        orderInfo.setOrderselprice(new BigDecimal(request.getProductTotalPrice()));
        orderInfo.setOrdernum(Integer.parseInt(request.getProductAmount()));
        orderInfo.setOrdername(ordernameJson);
        orderInfo.setOrderstatus((byte)0);
        orderInfo.setSinglestatus((byte)1);
        orderInfo.setAddtime(new Date());
        int res = orderServiceFacade.insOrder(orderInfo);
        if( res < 0 ){
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.CREATE_ORDER_FAILED.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.CREATE_ORDER_FAILED.getDesc());

            return yjResponseDto;
        }

        // 成功返回
        yjResponseDto.clear();
        yjResponseDto.setCode("10000");
        yjResponseDto.setInfo("创建订单成功,请跳转至signUrl");
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("signUrl",ManageConstants.SIGN_URL_FORMAL );
        yjResponseDto.setData(responseMap);

        return yjResponseDto;
    }

    /*
     * 商户获取银联信用卡分期支付 短信
     * @param yinjiaTermsRequest 此接口请求类
     * @param HttpServletRequest 请求接口参数类
     * */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public YjResponseDto sendSms(HttpServletRequest request)
    {
        String orderid = request.getParameter("orderid");
        String mid = request.getParameter("mid");
        String reUri = request.getServerName();   // 返回域名

        String requestUrl;
        if ( reUri.indexOf("cpapi.7shengqian.com") > -1 ){
            requestUrl = CHINAPAY_URL_REQUEST+"smsCodeSend";
        }else{
            requestUrl = CHINAPAY_URL_REQUEST_TEST+"smsCodeSend";
        }

        //String publickey = request.getParameter("publickey");
        //定义银联支付方式id
        Integer tpid = 7;

        if ( StringUtils.isBlank(orderid) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc(), "orderid不能为空");
        }
        if ( StringUtils.isBlank(mid) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "mid不能为空");
        }
        //this.checkPublickey(mid, publickey);
        //获取订单信息
        OrderInterfaceInfo orderInfo = orderInterfaceServiceFacade.getOrder(orderid.trim());
        if ( StringUtils.isBlank(orderInfo.getSignOrderid()) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_SIGE_NOT.getCode(), "用户信息未签约");
        }
        //商户签约信息
        OrderCpInterfaceInfo orderCpInfo = orderCpServiceFacade.getOrderCpByorderid(orderInfo.getSignOrderid());
        String stage="0";
        if(orderCpInfo!=null){

            if(orderInfo.getOrderstatus().equals(1)){

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("此单已支付");
                return yjResponseDto;
            }
            if(!orderCpInfo.getSignstatus().equals("2")){

                yjResponseDto.setCode("10010");
                yjResponseDto.setInfo("银行卡签约状态有误");
                return yjResponseDto;
            }
            if(!orderInfo.getMtsid().toString().equals(mid) ){

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("订单与商户信息不匹配");
                return yjResponseDto;
            }
            Map<String,String> ordernameJson = new HashMap<>();
            ordernameJson = JsonUtils.toObject(orderInfo.getOrdername(), Map.class);
            String payType_cn = ordernameJson.get("stageType_cn");
            if ( StringUtils.isBlank(ordernameJson.get("stageType_cn")) )
            {
                yjResponseDto.setInfo("未获取到订单分期信息，请重试");
                return yjResponseDto;
            }
            Pattern pattern = Pattern.compile("^\\d");
            Matcher matcher = pattern.matcher(ordernameJson.get("stageType_cn"));
            if ( matcher.find() ){

                stage = matcher.group(0);
                if (Integer.parseInt(stage)<=9) {
                    stage = "0"+stage;
                }
            } else{

                yjResponseDto.setInfo("未获取到订单分期信息，请重试");
                return yjResponseDto;
            }
        }
        //获取商户信息
        MerchantInfo merchant = merchantServiceFacade.getMerchant(Long.parseLong(mid));
        //获取商户银联支付方式配置
        MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(orderInfo.getMtsid(),tpid);
        Map<String, String> maparr = JsonUtils.toCollection(merchantPayTypeInfo.getParam(),new TypeReference<HashMap<String, String>>(){});
        Map<String,Object> maptree = new TreeMap<String,Object>();
        Map<String, String> chinaRe = new HashMap<String,String>();
        if(maparr.containsKey("CP_Acctid") && maparr.containsKey("CP_MerchaNo") && maparr.containsKey("CP_Code") && maparr.containsKey("CP_Salt")){

            //组装银联发送短信参数
            maptree.put("service","smsCodeSend");
            maptree.put("sysMerchNo",maparr.get("CP_MerchaNo"));
            maptree.put("outOrderNo",orderInfo.getOrderid().trim());
            maptree.put("reqType","02");//用于区分发送短信的类型
            //maptree.put("clientIp",request.getRemoteAddr());ServletUtils.getIpAddr(request)
            maptree.put("clientIp","10.10.18.17");
            maptree.put("chnCode",maparr.get("CP_Code"));
            maptree.put("chnAcctId",maparr.get("CP_Acctid"));
            maptree.put("selectFinaCode",orderCpInfo.getSelectfinacode());
            maptree.put("accountType","CREDIT");
            maptree.put("accountNumber",orderCpInfo.getBankaccountnumber());
            maptree.put("phoneNo",orderCpInfo.getMobileno());
            maptree.put("sysAgreeNo",orderCpInfo.getSignedname());
            maptree.put("tranAmt",orderInfo.getOrderselprice().toString());
            maptree.put("privatekey",maparr.get("CP_Salt"));
            maptree.put("numberOfInstallments",stage);

            yjResponseDto = chinaPayServiceFacade.ChinaPaySmsCodeSend(maptree,requestUrl);

            String smeRes = yjResponseDto.getData().toString();
            chinaRe = JsonUtils.toCollection(smeRes,new TypeReference<HashMap<String, String>>(){});
            if(chinaRe.containsKey("retCode") && chinaRe.get("retCode").equals("0000")){

                yjResponseDto.setInfo("短信发送成功");
                yjResponseDto.clearData();
            }else{

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("短信发送失败");
                yjResponseDto.clearData();
            }
        }else{

            yjResponseDto.setCode("10008");
            yjResponseDto.setInfo("商戶支付参数配置有误");
        }

        return yjResponseDto;
    }

    /*
     * 银联信用卡分期退款
     * @param yinjiaTermsRequest 此接口请求类
     * @param HttpServletRequest 请求接口参数类
     * */
    @RequestMapping(value = "/chinaRefund", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public YjResponseDto chinaRefund(HttpServletRequest request)
    {
        String orderid = request.getParameter("orderid");//退款单号
        String origOrderid = request.getParameter("origOrderid");//原始订单号
        String mid = request.getParameter("mid");
        String backUrl = request.getParameter("backUrl");//后台回调地址
        String refundAmt = request.getParameter("refundAmt");//退款金额
        String sign = request.getParameter("sign");//签名
        //getSign();
        String reUri = request.getServerName();   // 返回域名

        String requestUrl;
        if ( reUri.indexOf("cpapi.7shengqian.com") > -1 ){
            requestUrl = CHINAPAY_URL_REQUEST+"purchaseRefund";
        }else{
            requestUrl = CHINAPAY_URL_REQUEST_TEST+"purchaseRefund";
        }

        //定义银联支付方式id
        Integer tpid = 7;

        if ( StringUtils.isBlank(orderid) || StringUtils.isBlank(origOrderid) || StringUtils.isBlank(mid) || StringUtils.isBlank(backUrl) || StringUtils.isBlank(refundAmt))
        {
            yjResponseDto.setCode("10008");
            yjResponseDto.setInfo("请求参数不合法");
            return yjResponseDto;
        }
        if ( StringUtils.isBlank(refundAmt) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "退款金额不合法");
        }
        //获取订单信息
        OrderInterfaceInfo orderInfo = orderInterfaceServiceFacade.getOrder(origOrderid.trim());
        //获取商户信息
        MerchantInfo merchant = merchantServiceFacade.getMerchant(Long.parseLong(mid));

        Map<String,Object> signParam = new HashMap<String,Object>();
        signParam.put("orderid",orderid);
        signParam.put("origOrderid",origOrderid);
        signParam.put("mid",orderid);
        signParam.put("backUrl",orderid);
        signParam.put("refundAmt",orderid);

        String getSign = SignUtils.getSign(signParam,merchant.getPrivateKey(),"UTF-8");
        if(!getSign.equals(sign)){

            yjResponseDto.setCode("10008");
            yjResponseDto.setInfo("验签错误");
            return yjResponseDto;
        }
        //商户签约信息
       // OrderCpInterfaceInfo orderCpInfo = orderCpServiceFacade.getOrderCpByorderid(orderInfo.getSignOrderid());
        String stage="0";
        if(orderInfo!=null){

            if(!orderInfo.getOrderstatus().equals(1)){

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("订单状态有误，请查看是否已支付");
                return yjResponseDto;
            }
            if(!orderInfo.getMtsid().toString().equals(mid) ){

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("订单与商户信息不匹配");
                return yjResponseDto;
            }
            if(!orderInfo.getPaytype().equals(tpid) ){

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("订单支付");
                return yjResponseDto;
            }
        }
        //获取商户银联支付方式配置
        MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(orderInfo.getMtsid(),tpid);
        Map<String, String> maparr = JsonUtils.toCollection(merchantPayTypeInfo.getParam(),new TypeReference<HashMap<String, String>>(){});
        Map<String,Object> maptree = new TreeMap<String,Object>();
        Map<String, String> chinaRe = new HashMap<String,String>();
        if(maparr.containsKey("CP_Acctid") && maparr.containsKey("CP_MerchaNo") && maparr.containsKey("CP_Code") && maparr.containsKey("CP_Salt")){

            //组装银联发送短信参数
            maptree.put("service","purchaseRefund");
            maptree.put("sysMerchNo",maparr.get("CP_MerchaNo"));
            maptree.put("tranClass","INSTALLMENT");

            maptree.put("outOrderNo",orderid.trim());
            maptree.put("origOutOrderNo",origOrderid.trim());
            maptree.put("tranAmt",refundAmt);
            maptree.put("backUrl",backUrl);
            maptree.put("privatekey",maparr.get("CP_Salt"));

            yjResponseDto = chinaPayServiceFacade.ChinaPayRefund(maptree,requestUrl);

            String smeRes = yjResponseDto.getData().toString();
            chinaRe = JsonUtils.toCollection(smeRes,new TypeReference<HashMap<String, String>>(){});
            if(chinaRe.containsKey("retCode") && chinaRe.get("retCode").equals("0000")){

                yjResponseDto.setInfo("退款已受理");
                yjResponseDto.clearData();
            }else{

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo(chinaRe.get("retMsg"));
                yjResponseDto.clearData();
            }
        }else{

            yjResponseDto.setCode("10008");
            yjResponseDto.setInfo("商戶支付参数配置有误");
        }

        return yjResponseDto;
    }
    @ModelAttribute
    @ResponseBody
    public void getMerInfo(HttpServletRequest request)
    {
        /*String token = request.getParameter("token");
        if (StringUtils.isBlank(token) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "TOKEN不能为空");
        }
        String mid = AESUtils.decrypt(token, ManageConstants.SKEY);

        if ( !Pattern.matches("^\\d+$", mid) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.NOTlOGIN.getCode(), "1111");
        }
        this.mid = Long.valueOf(mid);*/

        // 商户号&商户信息
        if ( StringUtils.isNotBlank(request.getParameter("mid")) ){
            merchInfo = merchantInterfaceServiceFacade.getMerchant(Long.parseLong(request.getParameter("mid")));
        }else{
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());
        }
/*
        // 签名串
        if ( StringUtils.isBlank(request.getParameter("sign")) ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.NO_SIGN.getCode(), JpfInterfaceErrorInfo.NO_SIGN.getDesc());
        }*/
    }

    // 生成一个pay_order表的orderid
    public String createOrderid(){
        int pre = getRandomInt(100,999);
        int last = getRandomInt(100,999);
        String middle = String.valueOf(System.currentTimeMillis());
        middle = middle.substring(3,middle.length());

        return ""+pre+middle+last;
    }

    // 生成指定范围内的随机整数
    public int getRandomInt(int min, int max){
        Random random = new Random();
        int randomInt = random.nextInt(max)%(max-min+1) + min;

        return randomInt;
    }

}
