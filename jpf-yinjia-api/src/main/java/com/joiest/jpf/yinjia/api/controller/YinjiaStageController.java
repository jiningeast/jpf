package com.joiest.jpf.yinjia.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.MerPayTypeServiceFacade;
import com.joiest.jpf.facade.MerchantInterfaceServiceFacade;
import com.joiest.jpf.facade.OrderCpServiceFacade;
import com.joiest.jpf.facade.OrderServiceFacade;
import com.joiest.jpf.yinjia.api.constant.ManageConstants;
import com.joiest.jpf.yinjia.api.util.ServletUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
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

    @Autowired
    private OrderCpServiceFacade orderCpServiceFacade;

    private MerchantInterfaceInfo merchInfo;

    @Autowired
    private YjResponseDto yjResponseDto;

    private static final Logger logger = LogManager.getLogger(YinjiaStageController.class);

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
        Map<String, Object> requestMap = new HashMap<>();
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
        String returnUrl = null;
        try{
            returnUrl = URLEncoder.encode(request.getReturnUrl(),"UTF-8");
        }catch (UnsupportedEncodingException e){
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.RETURNURL_ENCODING_ERROR.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.RETURNURL_ENCODING_ERROR.getDesc());

            return yjResponseDto;
        }
        map.put("returnUrl", returnUrl.toLowerCase());
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

        // 生成订单
        String orderid = createOrderid();

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrdertype((byte)1);
        orderInfo.setOrderid(orderid);
        orderInfo.setForeignOrderid(request.getOrderid());
        orderInfo.setForeignRequest(request.toString());
        orderInfo.setReturnUrl(request.getReturnUrl());
        orderInfo.setMtsid(Long.parseLong(request.getMid()));
        orderInfo.setUid((long)0);
        orderInfo.setPaytype(7);
        orderInfo.setOrderprice(new BigDecimal(request.getProductTotalPrice()));
        orderInfo.setOrderselprice(new BigDecimal(request.getProductTotalPrice()));
        orderInfo.setOrdernum(Integer.parseInt(request.getProductAmount()));
        orderInfo.setOrdername(" ");
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
        // 构建H5 URL后缀
        Map<String, String> tailMap = new HashMap<>();
        tailMap.put("mid", request.getMid());
        tailMap.put("orderid", request.getOrderid());
        tailMap.put("platformOrderid", orderid);
        String tailJson = JsonUtils.toJson(tailMap);
        String urlTail = AESUtils.encrypt(tailJson,AES_KEY);
        // 构建返回的data
        Map<String, String> dataMap = new HashMap<>();
        String signUrl = null;
        try{
            signUrl = URLEncoder.encode(ManageConstants.TERMS_URL+urlTail, "UTF-8");
        }catch (UnsupportedEncodingException e){
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.SIGNURL_ENCODING_ERROR.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.SIGNURL_ENCODING_ERROR.getDesc());

            return yjResponseDto;
        }
        dataMap.put("signUrl", signUrl);
        dataMap.put("orderid", request.getOrderid());
        dataMap.put("platformOrderid", orderid);
        yjResponseDto.setData(dataMap);

        return yjResponseDto;
    }

    /** 选择分期期数页面渲染
     * H5 第一步 获取商户支付方式等信息
     * @param "data中包含mid,orderid,platformOrderid"
     * @return 返回订单信息和商户信息，先json再base64
     */
    @RequestMapping(value = "/getMerPay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getMerPay(String data){
        String dataJson = AESUtils.decrypt(data, AES_KEY);
        Map<String,String> dataMap = JsonUtils.toCollection(dataJson, new TypeReference<HashMap<String,String>>(){});
        if ( dataMap.get("mid") == null || dataMap.get("orderid") == null || dataMap.get("platformOrderid") == null ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INCORRECT_DATA.getCode(), JpfInterfaceErrorInfo.INCORRECT_DATA.getDesc());
        }
        OrderInfo orderInfo = orderServiceFacade.getOrderByOrderidAndForeignOrderid(dataMap.get("orderid"), dataMap.get("platformOrderid"), true);
        MerchantInterfaceInfo merInfo = merchantInterfaceServiceFacade.getMerchant(Long.parseLong(dataMap.get("mid")));
        MerchantPayTypeInfo merPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(Long.parseLong(dataMap.get("mid")), orderInfo.getPaytype(), true);

        // 构建返回
        Map<String, Object> responseDataMap = new HashMap<>();
        responseDataMap.put("mid",dataMap.get("mid"));
        responseDataMap.put("orderid", dataMap.get("platformOrderid"));
        responseDataMap.put("logo", merInfo.getLogo());
        responseDataMap.put("companyname", merInfo.getCompanyname());
        /*responseDataMap.put("payType", orderInfo.getPaytype());
        responseDataMap.put("payType_cn", getPayTypeCn(orderInfo.getPaytype()));
        responseDataMap.put("bankcatid",merPayTypeInfo.getBankcatid());*/
        // 分期具体参数
        List<Object> stageJsonList = new ArrayList<>();
        String bankcatids[] = merPayTypeInfo.getBankcatid().split(",");
        for (int i=0; i<bankcatids.length; i++){
            Map<String, Object> stageMap = new HashMap<>();
            Map<String, Object> termsMap = getPayTypeCn(Integer.parseInt(bankcatids[i]));
            stageMap.put("catid",termsMap.get("term"));
            stageMap.put("cat", termsMap.get("term_cn"));
            stageJsonList.add(stageMap);
        }
        String stageJson = JsonUtils.toJson(stageJsonList);
        responseDataMap.put("stage", stageJson);
        responseDataMap.put("money", orderInfo.getOrderprice().toString());
        responseDataMap.put("bankName", SUPPORTED_BANKNAMES);
        String responseDataJson = JsonUtils.toJson(responseDataMap);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("code", "10000");
        responseMap.put("info", "SUCCESS");
        responseMap.put("data", responseDataJson);
        String responseJson = JsonUtils.toJson(responseMap);

        return Base64CustomUtils.base64Encoder(responseJson);
    }

    /**
     * H5 第二步
     * 选择分期数点击确认付款
     */
    @RequestMapping(value = "/confirmTerms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String corfirmTerms(YinjiaConfirmRequest request){

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
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("code", JpfInterfaceErrorInfo.ILLEGAL_TERM.getCode());
            responseMap.put("info", JpfInterfaceErrorInfo.ILLEGAL_TERM.getDesc());
            String responseJson = JsonUtils.toJson(responseMap);

            return Base64CustomUtils.base64Encoder(responseJson);
        }

        // 获取此订单所有相关信息
        OrderInfo orderInfo = orderServiceFacade.getOrderByOrderid(request.getOrderid(),true);
        Long mtsid = orderInfo.getMtsid();
        MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(mtsid,7);
        String bankcatids[] = merchantPayTypeInfo.getBankcatid().split(",");
        if ( !ArrayUtils.contains(bankcatids, ""+catid) ){
            // 如果传过来的分期数不在该商户的支持范围内
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("code", JpfInterfaceErrorInfo.UNSUPPORT_TERM.getCode());
            responseMap.put("info", JpfInterfaceErrorInfo.UNSUPPORT_TERM.getDesc());
            String responseJson = JsonUtils.toJson(responseMap);

            return Base64CustomUtils.base64Encoder(responseJson);
        }

        // 生成分期支付ordername字段值
        Map<String, Object> ordernameMap = new HashMap<>();
        ordernameMap.put("payType",7);
        ordernameMap.put("stageType", request.getTerm());
        ordernameMap.put("payType_cn","银联信用卡分期支付");
        ordernameMap.put("stageType_cn",request.getTerm()+"期");
        String ordernameJson = JsonUtils.toJson(ordernameMap);

        OrderInfo orderInfoForUpdate = new OrderInfo();
        orderInfoForUpdate.setOrderid(request.getOrderid());
        orderInfoForUpdate.setOrdername(ordernameJson);
        int res = orderServiceFacade.updateOrdername(orderInfoForUpdate,true);

        if ( res <= 0 ){
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("code", JpfInterfaceErrorInfo.UPDATE_ORDERNAME_FAILED.getCode());
            responseMap.put("info", JpfInterfaceErrorInfo.UPDATE_ORDERNAME_FAILED.getDesc());
            String responseJson = JsonUtils.toJson(responseMap);

            return Base64CustomUtils.base64Encoder(responseJson);
        }

        // 正确返回
        Map<String, String> responseDataMap = new HashMap<>();
        responseDataMap.put("orderid", request.getOrderid());
        responseDataMap.put("mid", ""+mtsid);
        String responseDataJson = JsonUtils.toJson(responseDataMap);
        String AESStr = AESUtils.encrypt(responseDataJson, AES_KEY);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("code", JpfInterfaceErrorInfo.SUCCESS.getCode());
        responseMap.put("info", JpfInterfaceErrorInfo.SUCCESS.getDesc());
        responseMap.put("data", AESStr);
        String responseJson = JsonUtils.toJson(responseMap);

        return Base64CustomUtils.base64Encoder(responseJson);
    }

    /**
     * H5 第三步 点击提交的签约操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/signUserInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String signUserInfo(YinjiaSignUserInfoRequest request, HttpServletRequest httpRequest){
        String dataJson = AESUtils.decrypt(request.getData(), AES_KEY);
        Map<String, String> dataMap = JsonUtils.toCollection(dataJson, new TypeReference<Map<String, String>>(){});
        String signOrderid = createOrderid();

        // 判断用户有没有签约过
        OrderCpInterfaceInfo orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpBybankaccountnumber(request.getAccountNumber());
        if ( orderCpInterfaceInfo == null ){
            // 未签约，准备插入一条签约记录
            OrderCpInterfaceInfo orderCpInsert = new OrderCpInterfaceInfo();
            orderCpInsert.setOrderid(signOrderid);
            orderCpInsert.setMtsid(Long.parseLong(dataMap.get("mid")));
            orderCpInsert.setInterestmode((long)1);
            orderCpInsert.setSubmerid("2code");
            orderCpInsert.setSubmername("2name");
            orderCpInsert.setSubmerabbr("2sj");
            orderCpInsert.setSignedname(request.getSignedName());
            orderCpInsert.setIdtype((byte)1);
            orderCpInsert.setIdno(request.getIdNo());
            orderCpInsert.setMobileno(request.getMobileNo());
            orderCpInsert.setChncode((long)0);
            orderCpInsert.setChnacctid((long)0);
            orderCpInsert.setSelectfinacode(request.getSelectFinaCode());
            orderCpInsert.setBankaccounttype((byte)2);
            orderCpInsert.setBankaccountnumber(request.getAccountNumber());
            orderCpInsert.setCvn2(Long.parseLong(request.getCvn2()));
            orderCpInsert.setValiditycard(""+request.getValidityCard()+"-01");
            // 设置过期时间
            /*String yearMonth[] = request.getValidityCard().split("-");
            Long year = Long.parseLong(yearMonth[0]);
            Long newYear = year+1;
            String newYearMonth = ""+newYear+"-"+yearMonth[1];
            Date validityyear = DateUtils.getFdate(newYearMonth+"-"+DateUtils.getDay()+" "+DateUtils.getCurTimeString(),DateUtils.Date_FORMAT_YMDHMS);*/
            Date validityyear = org.apache.commons.lang3.time.DateUtils.addYears(new Date(),1);
            orderCpInsert.setValidityyear(validityyear);
            // 设置IP
            String IP = ServletUtils.getIpAddr(httpRequest);
            orderCpInsert.setClientip(IP);
            orderCpInsert.setSignstatus("1");
            orderCpInsert.setSysagreeno("");
            orderCpInsert.setCreated(DateUtils.getCurrentDate());

            int res = orderCpServiceFacade.insRecord(orderCpInsert);
            if ( res > 0 ){
                // 获取银联签约接口url
                MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(Long.parseLong(dataMap.get("mid")), 7, true);
                String paramJson = merchantPayTypeInfo.getParam();
                Map<String, String> paramMap = JsonUtils.toCollection(paramJson, new TypeReference<Map<String, String>>() {});

                // 构建返回加密串
                Map<String , String> frontMap = new HashMap<>();
                frontMap.put("mid","");
                frontMap.put("orderid","");
                frontMap.put("signOrderid","");
                String AESJson = JsonUtils.toJson(frontMap);
                String frontAES = AESUtils.encrypt(AESJson,AES_KEY);

                // 构建银联签约接口request参数
                Map<String,String> chinapayMap = new HashMap<>();
                chinapayMap.put("service","sign");
                chinapayMap.put("sysMerchNo", paramMap.get("CP_MerchaNo"));
                chinapayMap.put("inputCharset", "UTF-8");
                chinapayMap.put("interestMode", "0"+orderCpInsert.getInterestmode());
                chinapayMap.put("chnCode", paramMap.get("CP_Code"));
                chinapayMap.put("chnAcctId", paramMap.get("CP_Acctid"));
                chinapayMap.put("outOrderNo", signOrderid);
                chinapayMap.put("frontUrl", CHINAPAY_SIGN_RETURN_URL+frontAES);
                chinapayMap.put("backUrl", CHINAPAY_SIGN_BACK_URL);
                chinapayMap.put("subMerId", orderCpInsert.getSubmerid());
                chinapayMap.put("subMerName", orderCpInsert.getSubmername());
                chinapayMap.put("subMerAbbr", orderCpInsert.getSubmerabbr());
                chinapayMap.put("signedName", request.getSignedName());
                chinapayMap.put("idType", "0"+orderCpInsert.getIdtype());
                chinapayMap.put("idNo", request.getIdNo());
                chinapayMap.put("mobileNo", request.getMobileNo());
                chinapayMap.put("selectFinaCode", request.getSelectFinaCode());
                String accountType = orderCpInsert.getBankaccounttype() == 2 ? "CREDIT" : "DEBIT";
                chinapayMap.put("accountType", accountType);
                chinapayMap.put("accountNumber", request.getAccountNumber());
                chinapayMap.put("clientIp", orderCpInsert.getClientip());
                if ( accountType.equals("CREDIT") ){
                    chinapayMap.put("cvn2", request.getCvn2());
                    String yearMonth[] = request.getValidityCard().split("-");
                    chinapayMap.put("validityYear", yearMonth[0]);
                    chinapayMap.put("validityMonth", yearMonth[1]);
                }
                Map<String, String> treeMap = new TreeMap<>();
                treeMap.putAll(chinapayMap);
                Iterator<String> iter = treeMap.keySet().iterator();
                StringBuilder sb = new StringBuilder();
                Map<String, Object> requestMap = new HashMap<>();
                while (iter.hasNext()){
                    String k = (String)iter.next();
                    String v = (String)treeMap.get(k);
                    sb.append(k+"="+v+"&");

                    requestMap.put(k,v);
                }
                String sbString = sb.toString();
                sbString = StringUtils.stripEnd(sbString,"&");
                String signMySign = Md5Encrypt.md5(sbString+paramMap.get("CP_Salt"),"UTF-8");
                requestMap.put("sign",signMySign);
                requestMap.put("signType","MD5");
//                String requestString = sbString+"&sign="+signMySign+"&signType=MD5";
                // 请求签约url
                String response = OkHttpUtils.postForm(ChinaPay_Rurl+"sign",requestMap);
                logger.info("签约返回："+response);
                Map<String, String> signResponseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>(){});
                Matcher matcher = null;
                if ( signResponseMap.get("retCode").equals("0000") ){
                    // 更新签约表
                    OrderCpInterfaceInfo orderCpInfo = new OrderCpInterfaceInfo();
                    orderCpInfo.setOrderid(signOrderid);
                    orderCpInfo.setTranno(signResponseMap.get("transNo"));
                    orderCpInfo.setSignstatus("1");
                    if ( signResponseMap.get("sysAgreeNo") == null && !signResponseMap.get("sysAgreeNo").isEmpty() ){
                        orderCpInfo.setSysagreeno(signResponseMap.get("sysAgreeNo"));
                    }
                    int updateRes = orderCpServiceFacade.updateRecord(orderCpInfo);
                    if ( updateRes > 0 ){
                        // 处理返回的url
                        Pattern pattern = Pattern.compile("%<body.*?>(.*?)</body>%si");
                        matcher = pattern.matcher(signResponseMap.get("autoSubmitForm"));
                    }else{
                        // 更新签约信息失败
                        Map<String, String> responseMap = new HashMap<>();
                        responseMap.put("code", JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode());
                        responseMap.put("info", JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc());
                        String responseJson = JsonUtils.toJson(responseMap);

                        return Base64CustomUtils.base64Encoder(responseJson);
                    }
                }

                // 构建返回
                Map<String, Object> responseDataMap = new HashMap<>();
                responseDataMap.put("orderid", dataMap.get("orderid"));
                responseDataMap.put("signOrderid", signOrderid);
                responseDataMap.put("url",matcher.group(0));
                String responseDataJson = JsonUtils.toJson(responseDataMap);

                Map<String, String> responseMap = new HashMap<>();
                responseMap.put("code", JpfInterfaceErrorInfo.USER_NOT_SIGNED.getCode());
                responseMap.put("info", JpfInterfaceErrorInfo.USER_NOT_SIGNED.getDesc());
                responseMap.put("data", responseDataJson);
                String responseJson = JsonUtils.toJson(responseMap);
                String responseBase64 = Base64CustomUtils.base64Encoder(responseJson);

                return responseBase64;
            }

        }else{
            // 已签约
            Map<String, String> responseDataMap = new HashMap<>();
            responseDataMap.put("orderid", dataMap.get("orderid"));
            responseDataMap.put("signOrderid", signOrderid);
            String responseDataJson = JsonUtils.toJson(responseDataMap);
            String AESStr = AESUtils.encrypt(responseDataJson, AES_KEY);

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("code", JpfInterfaceErrorInfo.SUCCESS.getCode());
            responseMap.put("info", JpfInterfaceErrorInfo.SUCCESS.getDesc());
            responseMap.put("data", AESStr);
            String responseJson = JsonUtils.toJson(responseMap);

            return Base64CustomUtils.base64Encoder(responseJson);
        }

        return "";
    }

    /**
     * H5 第四步 渲染支付页
     */
    @RequestMapping("InstallPay")
    public void installPay(String data){

    }

    /**
     * 签约回调通知地址
     */
    @RequestMapping("/signNotify")
    public void signNotify(YinjiaSignNotifyRequest request){

    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        Map<String, String> map = new HashMap<>();
        map.put("mid","117");
        map.put("orderid","201805231422031547");
        map.put("platformOrderid", "6907334152323990");
        String tailJson = JsonUtils.toJson(map);
        String urlTail = AESUtils.encrypt(tailJson,AES_KEY);

        return urlTail;
    }

    @ModelAttribute
    public void getMerInfo(HttpServletRequest request, HttpServletRequest httpServletRequest)
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

        String serverName = httpServletRequest.getServerName();
        if ( serverName.contains("/yinjiastage/createOrder") ){
            // 商户号&商户信息
            if ( StringUtils.isNotBlank(request.getParameter("mid")) ){
                merchInfo = merchantInterfaceServiceFacade.getMerchant(Long.parseLong(request.getParameter("mid")));
            }else{
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());
            }

            // 签名串
            if ( StringUtils.isBlank(request.getParameter("sign")) ){
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.NO_SIGN.getCode(), JpfInterfaceErrorInfo.NO_SIGN.getDesc());
            }
        }
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

    // 获取支付方式名称
    public Map<String, Object> getPayTypeCn(int payType){
        Map<String, Object> map = new HashMap<>();

        switch (payType){
            // 将传过来的分期数转换成数据库里相应的id
            case 25:
                map.put("term",3);
                map.put("term_cn", "3期");
                break;

            case 26:
                map.put("term",6);
                map.put("term_cn", "6期");
                break;

            case 27:
                map.put("term",9);
                map.put("term_cn", "9期");
                break;

            case 28:
                map.put("term",12);
                map.put("term_cn", "12期");
                break;

            case 29:
                map.put("term",15);
                map.put("term_cn", "15期");
                break;

            case 30:
                map.put("term",24);
                map.put("term_cn", "24期");
                break;
        }

        return map;
    }
}
