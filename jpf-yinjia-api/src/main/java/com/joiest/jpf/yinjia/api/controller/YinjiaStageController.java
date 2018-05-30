package com.joiest.jpf.yinjia.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
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
import java.text.SimpleDateFormat;
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


    private static final Logger logger = LogManager.getLogger(YinjiaStageController.class);

    @Autowired
    private OrderInterfaceServiceFacade orderInterfaceServiceFacade;

    @Autowired
    private MerchantServiceFacade merchantServiceFacade;

    @Autowired
    private ChinaPayServiceFacade chinaPayServiceFacade;

    @Autowired
    private OrderRefundServiceFacade orderRefundServiceFacade;

    @Autowired
    private BankCardServiceFacade bankCardServiceFacade;

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
            YjResponseDto yjResponseDto = new YjResponseDto();
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
        // 商户号&商户信息
        if ( StringUtils.isNotBlank(request.getMid()) ){
            merchInfo = merchantInterfaceServiceFacade.getMerchant(Long.parseLong(request.getMid()));
        }else{
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());
        }

        // 签名串
        if ( StringUtils.isBlank(request.getSign()) ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.NO_SIGN.getCode(), JpfInterfaceErrorInfo.NO_SIGN.getDesc());
        }
        YjResponseDto yjResponseDto = new YjResponseDto();
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

        return Base64CustomUtils.base64Encoder(responseJson).replaceAll("\r","").replaceAll("\n","");
    }

    /**
     * H5 第三步 点击提交的签约操作
     * request的data加密串中包含orderid和mid
     *
     */
    @RequestMapping(value = "/signUserInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String signUserInfo(YinjiaSignUserInfoRequest request, HttpServletRequest httpRequest){
        String dataJson = AESUtils.decrypt(request.getData(), AES_KEY);
        Map<String, String> dataMap = JsonUtils.toCollection(dataJson, new TypeReference<Map<String, String>>(){});
        String signOrderid = createOrderid();

        // 获取订单信息
        OrderInfo orderInfo = orderServiceFacade.getOrderByOrderid(dataMap.get("orderid"), true);
        String foreignRequest = orderInfo.getForeignRequest();
        Map<String, String> foreignRequestMap = ToolUtils.urlToMap(foreignRequest);

        // 判断用户有没有签约过
        OrderCpInterfaceInfo orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpBybankaccountnumber(request.getAccountNumber());
        if ( orderCpInterfaceInfo.getBankaccountnumber() == null ){
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
                Map<String , Object> frontMap = new HashMap<>();
                frontMap.put("orderid",dataMap.get("orderid"));
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
                logger.info("frontUrl="+CHINAPAY_SIGN_RETURN_URL+frontAES+" length="+CHINAPAY_SIGN_RETURN_URL.length()+frontAES.length());
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
                String response = OkHttpUtils.postForm(CHINAPAY_URL_REQUEST+"sign",requestMap);
                logger.info("签约返回："+response);
                Map<String, String> signResponseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>(){});
                String html = null;
                if ( signResponseMap.get("retCode").equals("0000") ){
                    // 更新签约表
                    OrderCpInterfaceInfo orderCpInfo = new OrderCpInterfaceInfo();
                    orderCpInfo.setOrderid(signOrderid);
                    orderCpInfo.setTranno(signResponseMap.get("tranNo"));
                    orderCpInfo.setSignstatus("2");
                    if ( signResponseMap.containsKey("sysAgreeNo") ){
                        orderCpInfo.setSysagreeno(signResponseMap.get("sysAgreeNo"));
                    }
                    int updateOrderCpRes = orderCpServiceFacade.updateRecord(orderCpInfo);
                    if ( updateOrderCpRes <= 0 ){
                        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode(), JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc(), null);
                    }

                    // 更新订单表的signOrderid
                    OrderInfo updateOrderinfo = new OrderInfo();
                    updateOrderinfo.setOrderid(dataMap.get("orderid"));
                    updateOrderinfo.setSignOrderid(Long.parseLong(signOrderid));
                    int updateOrderRes = orderServiceFacade.updataSignOrderid(updateOrderinfo);
                    if ( updateOrderRes <= 0 ){
                        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode(), JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc(), null);
                    }

                    if ( updateOrderRes > 0 && updateOrderRes > 0 ){
                        // 处理返回的url
                        /*Pattern pattern = Pattern.compile("<form.*?>(.*?)</form>");
                        matcher = pattern.matcher(signResponseMap.get("autoSubmitForm"));*/

                        html = StringUtils.substringBetween(signResponseMap.get("autoSubmitForm"), "<body>","</body>");
                    }
                }else{
                    return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SIGN_FAILED.getCode(), signResponseMap.get("retMsg"), null);
                }

                // 构建返回
                Map<String, Object> responseDataMap = new HashMap<>();
                responseDataMap.put("html", html);
                String responseDataJson = JsonUtils.toJson(responseDataMap);

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_NOT_SIGNED.getCode(), JpfInterfaceErrorInfo.USER_NOT_SIGNED.getDesc(),responseDataJson);
            }else{
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode(), JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc(), null);
            }
        }

        // 已签约
        // 更新订单表的signOrderid
        OrderInfo updateOrderinfo = new OrderInfo();
        updateOrderinfo.setOrderid(dataMap.get("orderid"));
        updateOrderinfo.setSignOrderid(Long.parseLong(orderCpInterfaceInfo.getOrderid()));
        int updateOrderRes = orderServiceFacade.updataSignOrderid(updateOrderinfo);
        if ( updateOrderRes <= 0 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode(), JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc(), null);
        }

        Map<String, Object> responseDataMap = new HashMap<>();
        responseDataMap.put("orderid", dataMap.get("orderid"));
        String responseDataJson = JsonUtils.toJson(responseDataMap);
        String AESStr = AESUtils.encrypt(responseDataJson, AES_KEY);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("code", JpfInterfaceErrorInfo.SUCCESS.getCode());
        responseMap.put("info", JpfInterfaceErrorInfo.SUCCESS.getDesc());
        responseMap.put("data", AESStr);
        String responseJson = JsonUtils.toJson(responseMap);

        return Base64CustomUtils.base64Encoder(responseJson).replaceAll("\r","").replaceAll("\n","");
    }

    /**
     * H5 第四步 渲染支付页
     */
    @RequestMapping(value = "/getPayInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getPayInfo(String data){
        /*frontMap.put("productName", foreignRequestMap.get("productName"));
        frontMap.put("orderPrice", orderInfo.getOrderprice());
        frontMap.put("bankAccountNumber", request.getAccountNumber());
        frontMap.put("signedName", request.getSignedName());*/
        String dataJson = AESUtils.decrypt(data, AES_KEY);
        Map<String, String> dataMap = JsonUtils.toCollection(dataJson, new TypeReference<Map<String, String>>(){});

        // 获取订单信息
        OrderInfo orderInfo = orderServiceFacade.getOrderByOrderid(dataMap.get("orderid"),true);
        String foreignRequest = orderInfo.getForeignRequest();
        Map<String, String> foreignRequestMap = ToolUtils.urlToMap(foreignRequest);

        // 获取签约信息
        OrderCpInterfaceInfo orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpByorderid(orderInfo.getSignOrderid().toString());

        Map<String, String> responseDataMap = new HashMap<>();
        responseDataMap.put("productName", foreignRequestMap.get("productName"));
        responseDataMap.put("orderPrice", orderInfo.getOrderprice().toString());
        responseDataMap.put("bankAccountNumber", orderCpInterfaceInfo.getBankaccountnumber());
        responseDataMap.put("signedName", orderCpInterfaceInfo.getSignedname());
        String responseDataJson = JsonUtils.toJson(responseDataMap);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(),responseDataJson);
    }

    /**
     * 签约回调通知地址
     */
    @RequestMapping("/signNotify")
    public void signNotify(YinjiaSignNotifyRequest request){

    }

    /**
     * H5 第五步 商户获取银联信用卡分期支付 短信
     * @param data 加密字符串
     * @param request 请求接口参数类
     * */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendSms(String data, HttpServletRequest request)
    {
        // 加密串解码
        String dataStr = AESUtils.decrypt(data, AES_KEY);
        Map<String, String> dataMap = JsonUtils.toCollection(dataStr, new TypeReference<Map<String, String>>(){});
        String orderid = dataMap.get("orderid");

        // 发送短信接口地址
        String requestUrl = CHINAPAY_URL_REQUEST+"smsCodeSend";

        //定义银联支付方式id
        Integer tpid = 7;

        if ( StringUtils.isBlank(orderid) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "orderid不能为空", null);
        }
        //获取订单信息
        OrderInterfaceInfo orderInfo = orderInterfaceServiceFacade.getOrder(orderid.trim());
        if ( orderInfo.getSignOrderid()==null )
        {
            //throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_SIGE_NOT.getCode(), "用户信息未签约");
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到订单相关用户信息", null);
        }
        //商户签约信息
        OrderCpInterfaceInfo orderCpInfo = orderCpServiceFacade.getOrderCpByorderid(orderInfo.getSignOrderid().toString());
        String stage="0";
        if(orderCpInfo!=null){

            if(orderInfo.getOrderstatus().equals(1)){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "此单已支付", null);
            }
            if(!orderCpInfo.getSignstatus().equals("2")){

                ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_NOT_SIGNED.getCode(), JpfInterfaceErrorInfo.USER_NOT_SIGNED.getCode(), null);
            }
            Map<String,String> ordernameJson = new HashMap<>();
            ordernameJson = JsonUtils.toObject(orderInfo.getOrdername(), Map.class);
            String payType_cn = ordernameJson.get("stageType_cn");
            if ( StringUtils.isBlank(ordernameJson.get("stageType_cn")) )
            {
                //分期信息有误
               return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到订单分期信息", null);
            }
            Pattern pattern = Pattern.compile("^\\d");
            Matcher matcher = pattern.matcher(ordernameJson.get("stageType_cn"));
            if ( matcher.find() ){

                stage = matcher.group(0);
                if (Integer.parseInt(stage)<=9) {
                    stage = "0"+stage;
                }
            } else{

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到订单分期信息", null);
                //yjResponseDto.setInfo("未获取到订单分期信息，请重试");
                //return yjResponseDto;
            }
        }
        //获取商户信息
        MerchantInfo merchant = merchantServiceFacade.getMerchant(orderInfo.getMtsid());
        //获取商户银联支付方式配置
        MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(orderInfo.getMtsid(),tpid);
        Map<String, String> maparr = JsonUtils.toCollection(merchantPayTypeInfo.getParam(),new TypeReference<HashMap<String, String>>(){});
        Map<String,Object> maptree = new TreeMap<String,Object>();
        Map<String, String> chinaRe = new HashMap<String,String>();

        String code = "";
        String msg = "";
        if(maparr.containsKey("CP_Acctid") && maparr.containsKey("CP_MerchaNo") && maparr.containsKey("CP_Code") && maparr.containsKey("CP_Salt")){

            //组装银联发送短信参数
            maptree.put("service","smsCodeSend");
            maptree.put("sysMerchNo",maparr.get("CP_MerchaNo"));
            maptree.put("outOrderNo",orderInfo.getOrderid().trim());
            maptree.put("reqType","02");//用于区分发送短信的类型
            maptree.put("clientIp",ServletUtils.getIpAddr(request));//ServletUtils.getIpAddr(request)
            //maptree.put("clientIp","10.10.18.17");
            maptree.put("chnCode",maparr.get("CP_Code"));
            maptree.put("chnAcctId",maparr.get("CP_Acctid"));
            maptree.put("selectFinaCode",orderCpInfo.getSelectfinacode());
            maptree.put("accountType","CREDIT");
            maptree.put("accountNumber",orderCpInfo.getBankaccountnumber());
            maptree.put("phoneNo",orderCpInfo.getMobileno());
            maptree.put("sysAgreeNo",orderCpInfo.getSignedname());
            maptree.put("tranAmt",orderInfo.getOrderprice().toString());
            maptree.put("privatekey",maparr.get("CP_Salt"));
            maptree.put("numberOfInstallments",stage);
            YjResponseDto yjResponseDto = new YjResponseDto();
            yjResponseDto = chinaPayServiceFacade.ChinaPaySmsCodeSend(maptree,requestUrl);

            String smeRes = yjResponseDto.getData().toString();
            chinaRe = JsonUtils.toCollection(smeRes,new TypeReference<HashMap<String, String>>(){});

            if(chinaRe.containsKey("retCode") && chinaRe.get("retCode").equals("0000")){

                code = JpfInterfaceErrorInfo.SUCCESS.getCode();
                msg = "短信发送成功";
                //yjResponseDto.setInfo("短信发送成功");
                //yjResponseDto.clearData();
            }else{

                code = JpfInterfaceErrorInfo.FAIL.getCode();
                msg = "短信发送失败";
                /*yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("短信发送失败");
                yjResponseDto.clearData();*/
            }
        }else{

            code = JpfInterfaceErrorInfo.FAIL.getCode();
            msg = "商戶支付参数配置有误";
        }
        return ToolUtils.toJsonBase64(code, msg, null);
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
        String origOrderid = request.getParameter("platformOrderid");//原始订单号
        String mid = request.getParameter("mid");
        String backUrl = request.getParameter("backUrl");//后台回调地址
        String refundAmt = request.getParameter("refundAmt");//退款金额
        String sign = request.getParameter("sign");//签名

        String reUri = request.getServerName();   // 返回域名

        String requestUrl = CHINAPAY_URL_REQUEST+"purchaseRefund";
        YjResponseDto yjResponseDto = new YjResponseDto();

        if ( StringUtils.isBlank(orderid) || StringUtils.isBlank(origOrderid) || StringUtils.isBlank(mid) || StringUtils.isBlank(backUrl) || StringUtils.isBlank(refundAmt))
        {
            yjResponseDto.setCode("10008");
            yjResponseDto.setInfo("请求参数不合法");
            return yjResponseDto;
        }
        //获取订单信息
        OrderInterfaceInfo orderInfo = orderInterfaceServiceFacade.getOrder(origOrderid.trim());
        //获取商户信息
        MerchantInfo merchant = merchantServiceFacade.getMerchant(Long.parseLong(mid));
        Map<String,Object> signParam = new HashMap<String,Object>();
        signParam.put("orderid",orderid);
        signParam.put("origOrderid",origOrderid);
        signParam.put("mid",mid);
        signParam.put("backUrl",backUrl);
        String getSign = SignUtils.getSign(signParam,merchant.getPrivateKey(),"UTF-8");
        if(!getSign.equals(sign)){

            yjResponseDto.setCode("10008");
            yjResponseDto.setInfo("验签错误");
            return yjResponseDto;
        }
        //获取退单表信息
        OrderRefundInfo getOrderRefund = orderRefundServiceFacade.getOrderRefund(orderid);
        if(getOrderRefund!=null){

            yjResponseDto.setCode("10008");
            yjResponseDto.setInfo("请确保退款单号唯一");
            return yjResponseDto;
        }
        //退单信息入库
        OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
        orderRefundInfo.setOrderid(origOrderid);
        orderRefundInfo.setRefundOrderid(orderid);
        orderRefundInfo.setMoney(new BigDecimal(refundAmt));
        orderRefundInfo.setStatus("1");
        orderRefundInfo.setBackurl(backUrl);
        orderRefundInfo.setCreated(new Date());

        int res = orderRefundServiceFacade.insOrderRefund(orderRefundInfo);

        Byte status='1';
        Integer tpid=7;
        BigDecimal refundPrice =new BigDecimal(refundAmt);// Long.valueOf(refundAmt).longValue();
        if(orderInfo!=null){
            if(refundPrice.compareTo(orderInfo.getOrderprice())>0){

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("退款金额有误");
                return yjResponseDto;
            }
            if(!orderInfo.getOrderstatus().toString().equals("1")){

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
                yjResponseDto.setInfo("订单支付方式有误");
                return yjResponseDto;
            }
        }else{
            yjResponseDto.setCode("10008");
            yjResponseDto.setInfo("未获取到此单");
            return yjResponseDto;
        }
        //获取商户银联支付方式配置
        MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(orderInfo.getMtsid(),tpid);
        Map<String, String> maparr = JsonUtils.toCollection(merchantPayTypeInfo.getParam(),new TypeReference<HashMap<String, String>>(){});
        Map<String,Object> maptree = new TreeMap<String,Object>();
        Map<String, String> chinaRe = new HashMap<String,String>();
        if(maparr.containsKey("CP_Acctid") && maparr.containsKey("CP_MerchaNo") && maparr.containsKey("CP_Code") && maparr.containsKey("CP_Salt")){

            //组装银联退款参数
            maptree.put("service","purchaseRefund");
            maptree.put("sysMerchNo",maparr.get("CP_MerchaNo"));
            maptree.put("tranClass","INSTALLMENT");

            maptree.put("outOrderNo",orderid.trim());
            maptree.put("origOutOrderNo",origOrderid.trim());
            maptree.put("tranAmt",refundAmt);
            maptree.put("backUrl",CHINAPAY_REFUND_BACK_URL);
            maptree.put("privatekey",maparr.get("CP_Salt"));

            yjResponseDto = chinaPayServiceFacade.ChinaPayRefund(maptree,requestUrl);

            String smeRes = yjResponseDto.getData().toString();
            chinaRe = JsonUtils.toCollection(smeRes,new TypeReference<HashMap<String, String>>(){});
            if(chinaRe.containsKey("retCode") && chinaRe.get("retCode").equals("0000")){

                yjResponseDto.setCode("10000");
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
    /*
     * 银联信用卡分期退款回调
     * @param HttpServletRequest 请求接口参数类
     * */
    @RequestMapping(value = "/purchaseRefundReturn", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String purchaseRefundReturn(HttpServletRequest request)
    {
        String sign = request.getParameter("sign");
        String signType = request.getParameter("signType");
        Map<String,Object> refundCancel = new HashMap<String,Object>();
        refundCancel.put("finishTime",request.getParameter("finishTime"));
        refundCancel.put("sysMerchNo",request.getParameter("sysMerchNo"));
        refundCancel.put("tranAmt",request.getParameter("tranAmt"));
        refundCancel.put("inputCharset",request.getParameter("inputCharset"));
        refundCancel.put("tranNo",request.getParameter("tranNo"));
        refundCancel.put("tranResult",request.getParameter("tranResult"));
        refundCancel.put("oriOrderNo",request.getParameter("oriOrderNo"));
        refundCancel.put("outOrderNo",request.getParameter("outOrderNo"));
        refundCancel.put("tranType",request.getParameter("tranType"));

        //获取订单信息
        OrderInterfaceInfo orderInfo = orderInterfaceServiceFacade.getOrder(refundCancel.get("oriOrderNo").toString());
        //获取退单表信息
        OrderRefundInfo getOrderRefund = orderRefundServiceFacade.getOrderRefund(refundCancel.get("outOrderNo").toString());
        //获取商户信息
        MerchantInfo merchant = merchantServiceFacade.getMerchant(orderInfo.getMtsid());

        //获取商户银联支付方式配置
        MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(orderInfo.getMtsid(),orderInfo.getPaytype());
        Map<String, String> maparr = JsonUtils.toCollection(merchantPayTypeInfo.getParam(),new TypeReference<HashMap<String, String>>(){});

        Map<String,Object> treeRefundCancel = new TreeMap<String,Object>();
        treeRefundCancel.putAll(refundCancel);
        String getKeyVal = ToolUtils.signData(treeRefundCancel);
        String getSign = Md5Encrypt.md5(getKeyVal+maparr.get("CP_Salt"));

        refundCancel.put("sign",sign);
        refundCancel.put("signType",signType);

        OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
        orderRefundInfo.setTranno(refundCancel.get("tranNo").toString());
        orderRefundInfo.setTrantype( Byte.valueOf(refundCancel.get("tranType").toString()));
        orderRefundInfo.setNotifyTime(new Date());
        orderRefundInfo.setResponsParam(JsonUtils.toJson(refundCancel));
        orderRefundInfo.setRefundOrderid(refundCancel.get("outOrderNo").toString());

        if(!getSign.equals(sign)){

            orderRefundInfo.setStatus("3");
        }else if(refundCancel.get("tranResult").equals("SUCCESS")){

            orderRefundInfo.setStatus("2");
        }else{

            orderRefundInfo.setStatus("3");
        }
        orderRefundServiceFacade.upOrderRefundByRefundOrder(orderRefundInfo);

        Map<String,Object> postParam= new HashMap<String,Object>();

        postParam.put("finishTime",request.getParameter("finishTime"));
        postParam.put("mid",orderInfo.getMtsid());
        postParam.put("tranAmt",request.getParameter("tranAmt"));
        postParam.put("tranResult",request.getParameter("tranResult"));
        postParam.put("platformOrderid",request.getParameter("oriOrderNo"));
        postParam.put("orderid",request.getParameter("outOrderNo"));

        Map<String,Object> postParamTree= new TreeMap<String,Object>();
        postParamTree.putAll(postParam);

        String postSign = SignUtils.getSign(postParamTree,merchant.getPrivateKey(),"UTF-8");
        postParam.put("sign",postSign);

        String response = OkHttpUtils.postForm(getOrderRefund.getBackurl(),postParam);
        if(response == "SUCCESS"){

            return "SUCCESS";
        }
        return "notice";
    }

    @RequestMapping("/checkCard")
    @ResponseBody
    public String checkCard(String cardNo ){
        BankCardInfo bankCardInfo = bankCardServiceFacade.getBankCardByCardNO(cardNo);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("id",bankCardInfo.getFindcode());
        responseMap.put("bankName",bankCardInfo.getBankname());
        responseMap.put("cardName",bankCardInfo.getBankname());
        String responseString = JsonUtils.toJson(responseMap);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(),responseString);
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

    @RequestMapping("/test2")
    @ResponseBody
    public String test2(){
        String html = "<body><form id = \\\"pay_form\\\" action=\\\"https://gateway.95516.com/gateway/api/frontTransReq.do\\\" method=\\\"post\\\"><input type=\\\"hidden\\\" name=\\\"accessType\\\" id=\\\"accessType\\\" value=\\\"0\\\"/><input type=\\\"hidden\\\" name=\\\"backUrl\\\" id=\\\"backUrl\\\" value=\\\"http://vip2.7shengqian.com/unipayNoti/chn/backNotify/UNIONPAY_INSTAL/99\\\"/><input type=\\\"hidden\\\" name=\\\"bizType\\\" id=\\\"bizType\\\" value=\\\"000301\\\"/><input type=\\\"hidden\\\" name=\\\"certId\\\" id=\\\"certId\\\" value=\\\"74778248003\\\"/><input type=\\\"hidden\\\" name=\\\"channelType\\\" id=\\\"channelType\\\" value=\\\"07\\\"/><input type=\\\"hidden\\\" name=\\\"encoding\\\" id=\\\"encoding\\\" value=\\\"UTF-8\\\"/><input type=\\\"hidden\\\" name=\\\"frontUrl\\\" id=\\\"frontUrl\\\" value=\\\"http://vip2.7shengqian.com/unipayNoti/chn/frontNotify/UNIONPAY_INSTAL/99\\\"/><input type=\\\"hidden\\\" name=\\\"merId\\\" id=\\\"merId\\\" value=\\\"802310048161356\\\"/><input type=\\\"hidden\\\" name=\\\"orderId\\\" id=\\\"orderId\\\" value=\\\"1000020141\\\"/><input type=\\\"hidden\\\" name=\\\"signMethod\\\" id=\\\"signMethod\\\" value=\\\"01\\\"/><input type=\\\"hidden\\\" name=\\\"signature\\\" id=\\\"signature\\\" value=\\\"cz+XJzC8gxPkvTOGyCad6nz9GgObvT6m2KC0tA3LrdAEiHpOQ9I5ip27+Z4GoyVwKTIn5kHxe+pKOhcaYdUMgq8w+gqtypH/lEUbHzKOmXGDHb0BXguMlnlNFbTmdPlt/xmASwsd49UhwpHxu3g2AZ2ZN2HRjD+Yhs1MER/8YkX6Bg4xyPtERi7/LqUGkyEuW6ELHpBtHu4lCGMPRSI2JBLmB5bUvo1tUrYn01IzXYvsJ4j20DBbGa6yhULif+OWn2x4Upqpl7az3fPFtKiyYWClxVSjKkBN5PCaLFHgxrTENhHCCEQx93puHEveA0RUyQl7oj59xBOnEsMV+mleCw==\\\"/><input type=\\\"hidden\\\" name=\\\"txnSubType\\\" id=\\\"txnSubType\\\" value=\\\"00\\\"/><input type=\\\"hidden\\\" name=\\\"txnTime\\\" id=\\\"txnTime\\\" value=\\\"20180528112220\\\"/><input type=\\\"hidden\\\" name=\\\"txnType\\\" id=\\\"txnType\\\" value=\\\"79\\\"/><input type=\\\"hidden\\\" name=\\\"version\\\" id=\\\"version\\\" value=\\\"5.1.0\\\"/></form></body><script type=\\\"text/javascript\\\">document.all.pay_form.submit();</script>";

        Map<String , Object> frontMap = new HashMap<>();
        frontMap.put("orderid", "1447487259621113");
        String AESJson = JsonUtils.toJson(frontMap);
        String frontAES = AESUtils.encrypt(AESJson,AES_KEY);

        return frontAES;
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

        /*String serverName = httpServletRequest.getServerName();
        if ( serverName.contains("/createOrder") ){
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

    /**
     * 联银分期 支付
     */
    @RequestMapping(value = "/installpay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String InstallPay(YinjiaPayRequest request, HttpServletRequest httpRequest)
    {
        YjResponseDto dto = new YjResponseDto();
        if ( StringUtils.isBlank( request.getSmsCode() ) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "验证码错误", null);
        }

        String dataJson = AESUtils.decrypt(request.getData(), AES_KEY);
        Map<String,String> dataMap = JsonUtils.toCollection(dataJson, new TypeReference<Map<String, String>>(){});
        if ( StringUtils.isBlank(dataMap.get("orderid")) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "orderid不能为空", null);
        }

        //获取订单信息
        OrderInterfaceInfo orderInfo = orderInterfaceServiceFacade.getOrder(dataMap.get("orderid"));
        if ( orderInfo.getSignOrderid() == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MER_SIGE_NOT.getCode(), "用户信息未签约", null);
        }
/*        if( !orderInfo.getMtsid().toString().equals(dataMap.get("mid")) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单与商户信息不匹配", null);
        }*/
        //商户签约信息
        OrderCpInterfaceInfo orderCpInfo = orderCpServiceFacade.getOrderCpByorderid(orderInfo.getSignOrderid().toString());

        //验证
        String stage_tmp = "";
        if ( orderInfo != null && orderCpInfo != null )
        {
            if ( orderInfo.getOrderstatus().equals(1) )
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单已支付", null);
            }
            if ( !orderCpInfo.getSignstatus().equals("2") )
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "签约受理中，稍后再试", null);
            }
            if ( StringUtils.isBlank(orderInfo.getOrdername()) )
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单分期信息非法", null);
            }
            Map<String,String> ordernameMap = new HashMap<>();
            ordernameMap = JsonUtils.toObject(orderInfo.getOrdername(), Map.class);
            String payType_cn = ordernameMap.get("stageType_cn");
            if ( StringUtils.isBlank(ordernameMap.get("stageType_cn")) )
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到订单分期信息，请重试", null);

            }
            Pattern pattern = Pattern.compile("^\\d");
            Matcher matcher = pattern.matcher(ordernameMap.get("stageType_cn"));
            if ( matcher.find() )
            {
                stage_tmp = matcher.group(0);
                if ( Integer.parseInt(stage_tmp) <= 9 )
                {
                    stage_tmp = "0" + stage_tmp;
                }
            } else
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到订单分期信息", null);
            }

            //获取商户信息 and 商户银嘉支付参数
            MerchantInterfaceInfo merInfo = merchantInterfaceServiceFacade.getMerchant(orderInfo.getMtsid());
            // 获取该商户银联信用卡分期支付的配置信息
            MerchantPayTypeInfo merchantPayTypeInfo  = merPayTypeServiceFacade.getOneMerPayTypeByTpid(orderInfo.getMtsid(),7, true);
            Map<String,String> paramMap = JsonUtils.toObject(merchantPayTypeInfo.getParam(),Map.class);
            //验证支付参数是否正确
            String[] keys = {"CP_MerchaNo","CP_Code","CP_Acctid","CP_Salt"};
            for ( String key : keys )
            {
                if ( !paramMap.containsKey(key) ||  paramMap.get(key) == null  )
                {
                    return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "支付参数不正确", null);
                }
            }
            //拼装商户信息数据
            Map<String,Object> signMap = new HashMap<>();
            signMap.put("service","installPay");
            signMap.put("sysMerchNo", paramMap.get("CP_MerchaNo"));
            signMap.put("outOrderNo", dataMap.get("orderid"));
            signMap.put("smsCode",request.getSmsCode());
            signMap.put("backUrl",CHINAPAY_PAYBACKURL);
            signMap.put("numberOfInstallments",stage_tmp);
            // 设置IP
            String IP = ServletUtils.getIpAddr(httpRequest);
            signMap.put("clientIp", IP);
            // 接口用到的参数
            signMap.put("CP_Salt", paramMap.get("CP_Salt"));
            String requestUrl;
            String reUri = httpRequest.getServerName();   // 返回域名

            requestUrl = CHINAPAY_URL_REQUEST + "installPay";

            YjResponseDto resultPay = chinaPayServiceFacade.IntallPay(signMap, requestUrl);
            Map<String,String> resultMap = JsonUtils.toCollection(resultPay.getInfo(), new TypeReference<Map<String, String>>(){});
            if ( resultMap.containsKey("retCode") && resultMap.get("retCode").equals("0000") )
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"支付已受理",orderInfo.getReturnUrl());
            }else
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"渠道受理有误，请重新下单支付",null);
            }
        } else
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单或者签约信息有误", null);
        }

    }

    /**
     * 银联支付回调
     * @return
     */
    @RequestMapping(value = "/Chinapayreturn")
    @ResponseBody
    public String ChinaPayReturn(YinjiaPayNotifyRequest request)
    {
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("finishTime", request.getFinishTime());
        returnMap.put("sysMerchNo", request.getSysMerchNo());
        returnMap.put("tranAmt", request.getTranAmt());
        returnMap.put("inputCharset", request.getInputCharset());
        returnMap.put("tranNo", request.getTranNo());
        returnMap.put("tranResult", request.getTranResult());
        returnMap.put("outOrderNo", request.getOutOrderNo());
        returnMap.put("tranType", request.getTranType());

        //获取订单信息
        OrderInterfaceInfo orderInfo = orderInterfaceServiceFacade.getOrder(returnMap.get("outOrderNo").toString());
        //获取商户信息 and 商户银嘉支付参数
        merchInfo = merchantInterfaceServiceFacade.getMerchant( orderInfo.getMtsid() );
        // 获取该商户银联信用卡分期支付的配置信息
        MerchantPayTypeInfo merchantPayTypeInfo  = merPayTypeServiceFacade.getOneMerPayTypeByTpid(Long.parseLong( orderInfo.getMtsid().toString() ),7, true);
        Map<String,String> paramMap = JsonUtils.toObject(merchantPayTypeInfo.getParam(),Map.class);
        //验证支付参数是否正确

        //sort
        TreeMap<String,Object> dataMap = new TreeMap<>();
        dataMap.putAll(returnMap);
        //签名
        String sortStr = ToolUtils.signData(dataMap);
        String signMd5 = Md5Encrypt.md5(sortStr + paramMap.get("CP_Salt"));

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n回调信息：" + returnMap);
        OrderInterfaceInfo orderInfoUpdate = new OrderInterfaceInfo();
        byte orderstatus;
        if ( !signMd5.equals(request.getSign()) || !request.getTranResult().equals("SUCCESS") )
        {
            sbf.append("\n支付失败：签名失败或者支付失败，订单状态更新为失败");
            sbf.append("\n回调签名：" + signMd5);
            orderstatus = 2;
        }else
        {
            orderstatus = 1;
            sbf.append("\n回调签名：" + signMd5);
            sbf.append("\n支付成功修改数据库状态完成");
        }
        //更新
        orderInfoUpdate.setOrderstatus(orderstatus);
        orderInfoUpdate.setPaytime(date);
        orderInfoUpdate.setUpdatetime(date);
        orderInfoUpdate.setOrderid(returnMap.get("outOrderNo").toString());
        orderInterfaceServiceFacade.updateOrderStatus(orderInfoUpdate);
        //日志
        SimpleDateFormat myfmt2 = new SimpleDateFormat("yyyy-MM");
        String filePath = "D:/project/jpf/log/ChinaPayReturn" + myfmt2.format(date) + ".txt";
//        String filePath = "/logs/jpf-yinjia-api/log/ChinaPayReturn" + myfmt2.format(date) + ".txt";
        LogsCustomUtils.writeIntoFile(sbf.toString(),filePath,true);

        //处理逻辑修改订单状态并返回输出success-----
        return "SUCCESS";
    }

}
