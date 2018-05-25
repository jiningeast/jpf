package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.BigDecimalCalculateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.SignUtils;
import com.joiest.jpf.dto.GetMerchPayTypeResponse;
import com.joiest.jpf.dto.YinjiaCreateOrderRequest;
import com.joiest.jpf.dto.YinjiaTermsRequest;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.util.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.joiest.jpf.manage.web.constant.ManageConstants.*;

@Controller
@RequestMapping("/yinjiastage")
public class YinjiaStageController {

    @Autowired
    private MerchantInterfaceServiceFacade merchantInterfaceServiceFacade;

    @Autowired
    private OrderInterfaceServiceFacade orderInterfaceServiceFacade;

    @Autowired
    private OrderCpServiceFacade orderCpServiceFacade;

    @Autowired
    private MerchantServiceFacade merchantServiceFacade;

    @Autowired
    private MerPayTypeServiceFacade merPayTypeServiceFacade;

    @Autowired
    private OrderServiceFacade orderServiceFacade;

    private MerchantInfo merchInfo;
    private String token;
    private Long mid; //商户ID

    @Autowired
    private ChinaPayServiceFacade chinaPayServiceFacade;

    /**
     * 联银分期 支付
     */
    @RequestMapping(value = "/installpay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public YjResponseDto InstallPay(HttpServletRequest request)
    {
        String orderid = request.getParameter("orderid");
        String signOrderid = request.getParameter("signOrderid");
        String smsCode = request.getParameter("smsCode");
        String mid = request.getParameter("mid");

        if ( StringUtils.isBlank(orderid) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc(), "orderid不能为空");
        }
        if ( StringUtils.isBlank(signOrderid) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "signOrderid不能为空");
        }
        //获取订单信息
        OrderInterfaceInfo orderInfo = orderInterfaceServiceFacade.getOrder(orderid.trim());
        //商户签约信息
        OrderCpInterfaceInfo orderCpInfo = orderCpServiceFacade.getOrderCpByorderid(signOrderid.trim());
        YjResponseDto dtoResponse = new YjResponseDto();
        //验证
        String stage_tmp = "";
        if ( orderInfo != null && orderCpInfo != null )
        {
            YjResponseDto dto = new YjResponseDto();
            dto.setCode(JpfInterfaceErrorInfo.FAIL.getCode());
            dto.setInfo("订单或签约信息有误");
            if ( StringUtils.isBlank(smsCode) )
            {
                dto.setInfo("验证码错误");
                return dto;
            }
            if ( orderInfo.getOrderstatus().equals(1) )
            {
                dto.setInfo("订单已支付");
                return dto;
            }
            if ( !orderCpInfo.getSignstatus().equals("2") )
            {
                dto.setInfo("签约受理中，稍后再试");
                return dto;
            }
            if ( !orderInfo.getMtsid().equals(this.mid) )
            {
                dto.setInfo("订单与商户信息不匹配");
                return dto;
            }
            if ( StringUtils.isBlank(orderInfo.getOrdername()) )
            {
                dto.setInfo("支付方式所属分期有误");
                return dto;
            }
            Map<String,String> ordernameJson = new HashMap<>();
            ordernameJson = JsonUtils.toObject(orderInfo.getOrdername(), Map.class);
            String payType_cn = ordernameJson.get("stageType_cn");
            if ( StringUtils.isBlank(ordernameJson.get("stageType_cn")) )
            {
                dto.setInfo("未获取到订单分期信息，请重试");
                return dto;
            }
            Pattern pattern = Pattern.compile("^\\d");
            Matcher matcher = pattern.matcher(ordernameJson.get("stageType_cn"));

            if ( matcher.find() )
            {
                stage_tmp = matcher.group(0);
            } else
            {
                dto.setInfo("未获取到订单分期信息，请重试");
                return dto;
            }
        }
        //JsonUtils.toJson("13");
        //JsonUtils.toObject("13",Map.class);
        String ip = ServletUtils.getIpAddr(request);
        System.out.println("ip : =======" + ip);
        // 获取IP
        String IP = ServletUtils.getIpAddr(request);

//        String stage = Integer.valueOf(stage_tmp) < 10 ? "0"+stage_tmp : stage_tmp;
//        Map<String,String> mapParam = new HashMap<>();
//        mapParam.put("outOrderNo", orderid);
//        mapParam.put("smsCode", smsCode);
//        mapParam.put("backUrl", ManageConstants.CHINAPAY_PAYBACKURL);
//        mapParam.put("numberOfInstallments", stage);
//
        return dtoResponse;
    }

    /**
     * 商户获取银联信用卡分期支付的期数
     * @param yinjiaTermsRequest 此接口请求类
     * @return 返回固定DTO
     */
    @RequestMapping(value = "/getTerms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public YjResponseDto getTerms(YinjiaTermsRequest yinjiaTermsRequest){
        // 检查公钥是否有误
        String mtsid = yinjiaTermsRequest.getMid();

        // 获取该商户银联信用卡分期支付的配置信息
        MerchantPayTypeInfo merchantPayTypeInfo  = merPayTypeServiceFacade.getOneMerPayTypeByTpid(Long.parseLong(mtsid),7);
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

        String breakpoint = "1";
        System.out.println(breakpoint);
        return yjResponseDto;
    }

<<<<<<< HEAD

    // 检查公钥是否有误
    public String checkPublickey(String mtsid, String publickey){
        MerchantInfo merchant = merchantServiceFacade.getMerchant(Long.parseLong(mtsid));
        if ( merchant.getPrivateKey().equals(publickey) ){
            return "SUCCESS";
        }else{
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.ILLEGAL_PUBLICKEY,"公钥错误");
        }
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
            requestUrl = CHINAPAY_URL_REQUEST;
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
        YjResponseDto dto = new YjResponseDto();
        if(orderCpInfo!=null){

            if(orderInfo.getOrderstatus().equals(1)){

                dto.setCode("10008");
                dto.setInfo("此单已支付");
                return dto;
            }
            if(!orderCpInfo.getSignstatus().equals("2")){

                dto.setCode("10010");
                dto.setInfo("银行卡签约状态有误");
                return dto;
            }
            if(!orderInfo.getMtsid().toString().equals(mid) ){

                dto.setCode("10008");
                dto.setInfo("订单与商户信息不匹配");
                return dto;
            }
         }
        //获取商户信息
        MerchantInfo merchant = merchantServiceFacade.getMerchant(Long.parseLong(mid));
        //获取商户银联支付方式配置
        MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(orderInfo.getMtsid(),tpid);
        Map<String, String> maparr = JsonUtils.toCollection(merchantPayTypeInfo.getParam(),new TypeReference<HashMap<String, String>>(){});
        Map<String,Object> maptree= new TreeMap<String,Object>();
        if(maparr.containsKey("CP_Acctid") && maparr.containsKey("CP_MerchaNo") && maparr.containsKey("CP_Code") && maparr.containsKey("CP_Salt")){

            //组装银联发送短信参数
            //Map<String,String> maptree= new TreeMap<String,String>();
            maptree.put("service","smsCodeSend");
            maptree.put("sysMerchNo",maparr.get("CP_MerchaNo"));
           // maptree.put("inputCharset","UTF-8");
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

            YjResponseDto yjResponseDto = chinaPayServiceFacade.ChinaPaySmsCodeSend(maptree,requestUrl);

        }else{
            dto.setCode("10008");
            dto.setInfo("商戶支付参数配置有误");
            return dto;
        }

        return dto;
    }
    /*@ModelAttribute
=======
>>>>>>> 64bf87facb67828e7a67d71329c997109c6b2ce2
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
        String mySign = SignUtils.getSign(map, this.merchInfo.getPrivateKey(), "UTF-8");
        if ( !mySign.equals(request.getSign()) ){   // 判断我们计算的签名和对方传过来的签名是否一致
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode(),JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());
        }

        // 计算价钱是否合理
        Double totalPrice = new Double(request.getProductTotalPrice());
        Double myTotalPrice = BigDecimalCalculateUtils.mul( Double.parseDouble(request.getProductUnitPrice()), Double.parseDouble(request.getProductAmount()) );
        if ( myTotalPrice != totalPrice ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.WRONG_TOTAL_PRICE.getCode(), JpfInterfaceErrorInfo.WRONG_TOTAL_PRICE.getDesc());
        }

        // 生成订单
        OrderInfo orderInfo = new OrderInfo();
        getRandomInt(1000,9999);
        orderInfo.setForeignOrderid(request.getOrderid());
        orderInfo.setMtsid(Long.parseLong(request.getMid()));
        orderInfo.setAddtime(new Date());
        orderServiceFacade.insOrder(orderInfo);

        return new YjResponseDto();
    }
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
        //商户信息
        if ( StringUtils.isNotBlank(request.getParameter("mid")) ){
            this.merchInfo = merchantServiceFacade.getMerchant(Long.parseLong(request.getParameter("mid")));
        }
    }

    // 生成指定范围内的随机整数
    public int getRandomInt(int min, int max){
        Random random = new Random();
        int randomInt = random.nextInt(max)%(max-min+1) + min;

        return randomInt;
    }


}
