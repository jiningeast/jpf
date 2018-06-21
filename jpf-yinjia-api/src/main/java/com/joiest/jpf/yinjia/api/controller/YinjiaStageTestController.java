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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.joiest.jpf.yinjia.api.constant.ManageConstants.*;

@Controller
@RequestMapping("yinjiastagetest")
public class YinjiaStageTestController {

    @Autowired
    private MerchantInterfaceServiceFacade merchantInterfaceServiceFacade;

    @Autowired
    private MerPayTypeServiceFacade merPayTypeServiceFacade;

    @Autowired
    private OrderServiceFacade orderServiceFacade;

    @Autowired
    private OrderCpServiceFacade orderCpServiceFacade;

    private MerchantInterfaceInfo merchInfo;


    private static final Logger logger = LogManager.getLogger(YinjiaStageTestController.class);

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

    //添加编辑支付流水
    @Autowired
    private PcaServiceFacade pcaServiceFacade;

    @Autowired
    private OrderCpMessageServiceFacade orderCpMessageServiceFacade;

    @Autowired
    private OrderYinjiaApiServiceFacade orderYinjiaApiServiceFacade;

    @Autowired
    private SmsMessageServiceFacade smsMessageServiceFacade;

    @Autowired
    private OrderRefundMessageServiceFacade orderRefundMessageServiceFacade;

    @Autowired
    private OrdersServiceFacade ordersServiceFacade;

    //获取分期费率信息
    @Autowired
    private VirtualInterfaceServiceFacade virtualInterfaceServiceFacade;

    //订单费率金额详情
    @Autowired
    private OrdersMoneyInterfaceServiceFacade ordersMoneyInterfaceServiceFacade;

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
        ValidatorUtils.validateInterface(request);
        // 商户号&商户信息
        if ( StringUtils.isNotBlank(request.getMid()) ){
            merchInfo = merchantInterfaceServiceFacade.getMerchantByMerchNo(request.getMid());
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
        Integer payType;
        // 验证传来的payType字段格式对不对
        if ( StringUtils.isNotBlank(request.getPayType()) ){
            try{
                payType = Integer.parseInt(request.getPayType());
            }catch (Exception e){
                yjResponseDto.clear();
                yjResponseDto.setCode(JpfInterfaceErrorInfo.INCORRECT_PAYTYPE.getCode());
                yjResponseDto.setInfo(JpfInterfaceErrorInfo.INCORRECT_PAYTYPE.getDesc());

                return yjResponseDto;
            }
            // 判断商户有没有资格使用传来的payType支付方式
            // 1：银联信用卡分期支付
            // 2：微信全额支付
            if ( payType != 1 && payType != 2 ){
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.INCORRECT_PAYTYPE.getCode(), JpfInterfaceErrorInfo.INCORRECT_PAYTYPE.getDesc());
            }
            switch (payType){
                case 1:
                    payType = 7;
                    break;

                case 2:
                    payType = 9;
                    break;
            }
            GetMerchPayTypeResponse response = merPayTypeServiceFacade.getOneMerPayTypes(Long.parseLong(request.getMid()));
            List<MerchantPayTypeInfo> list = response.getPayTypeInfos();
            List<Integer> payTypesList = new ArrayList();
            for ( MerchantPayTypeInfo info:list){
                payTypesList.add(info.getTpid());
            }
            if ( !payTypesList.contains(payType) ){
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.UNSUPPORT_PAYTYPE.getCode(), JpfInterfaceErrorInfo.UNSUPPORT_PAYTYPE.getDesc());
            }

            map.put("payType", payType);
        }else{
            payType = 7;
        }
        map.put("mid", request.getMid());
        map.put("productId", request.getProductId());
        map.put("productName", request.getProductName());
        map.put("productAmount", request.getProductAmount());
        map.put("productUnitPrice", request.getProductUnitPrice());
        map.put("productTotalPrice", request.getProductTotalPrice());
        String returnUrl = ToolUtils.urlEncodeSmallCase(request.getReturnUrl());
        map.put("returnUrl", returnUrl);
        String notifyUrl = ToolUtils.urlEncodeSmallCase(request.getNotifyUrl());
        map.put("notifyUrl", notifyUrl);
        String mySign = SignUtils.getSign(map, merchInfo.getPrivateKey(), "UTF-8");
        if ( !mySign.equals(request.getSign()) ){   // 判断我们计算的签名和对方传过来的签名是否一致
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());

            return yjResponseDto;
        }

        //商户费率
        String merRate = "";
        Map<String,String> paramSec = new HashMap<>();
        //银联支付
        if ( payType == 7 )
        {
            // 获取该商户银联信用卡分期支付的配置信息
            MerchantPayTypeInfo merchantPayTypeInfo  = merPayTypeServiceFacade.getOneMerPayTypeByTpid(merchInfo.getId(),7, true);
            if ( StringUtils.isNotBlank(merchantPayTypeInfo.getParamSec()) )
            {
                paramSec = JsonUtils.toCollection(merchantPayTypeInfo.getParamSec(), new TypeReference<Map<String, String>>(){});
                merRate = paramSec.get("merRate");
            }
            String reg_merRate = "^0{1}(\\.0){1}\\d{2}$";

            Boolean merRateIsTrue = Pattern.compile(reg_merRate).matcher(merRate).matches();
            if ( StringUtils.isBlank(merRate) || !merRateIsTrue )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.MERCH_RATE_ERROR.getCode(), JpfInterfaceErrorInfo.MERCH_RATE_ERROR.getDesc());
            }
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

        OrderYinjiaApiInfo orderYinjiaApiInfo = new OrderYinjiaApiInfo();
        orderYinjiaApiInfo.setOrderid(orderid);
        orderYinjiaApiInfo.setForeignOrderid(request.getOrderid());
        orderYinjiaApiInfo.setForeignRequest(request.toString());
        orderYinjiaApiInfo.setReturnUrl(returnUrl);
        orderYinjiaApiInfo.setNotifyUrl(notifyUrl);
        orderYinjiaApiInfo.setMtsid(merchInfo.getId());
        orderYinjiaApiInfo.setPaytype(payType);
        orderYinjiaApiInfo.setOrderPayPrice(new BigDecimal(request.getProductTotalPrice()));
        orderYinjiaApiInfo.setOrderStdPrice(new BigDecimal(request.getProductTotalPrice()));
        orderYinjiaApiInfo.setProductAccount(Integer.parseInt(request.getProductAmount()));
        orderYinjiaApiInfo.setPayDetail(" ");
        orderYinjiaApiInfo.setPayStatus((byte)0);
        orderYinjiaApiInfo.setRefundStatus((byte)1);
        orderYinjiaApiInfo.setUserOperateStatus((byte)0);
        orderYinjiaApiInfo.setAddtime(new Date());
        int res = orderYinjiaApiServiceFacade.insOrder(orderYinjiaApiInfo);
        if( res < 0 ){
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.CREATE_ORDER_FAILED.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.CREATE_ORDER_FAILED.getDesc());

            return yjResponseDto;
        }
        // 生成原始数据记录
        OrdersInfo ordersInfo = new OrdersInfo();
        ordersInfo.setOrderid(orderid);
        ordersInfo.setMtsid(""+merchInfo.getId());
        ordersInfo.setMoney(new BigDecimal(request.getProductTotalPrice()));
        ordersInfo.setPaytype(payType);
        ordersInfo.setProductId(request.getProductId());
        ordersInfo.setProductAmount(request.getProductAmount());
        ordersInfo.setProductName(request.getProductName());
        ordersInfo.setProductUnitPrice(new BigDecimal(request.getProductUnitPrice()));
        ordersInfo.setSelfBusiness(0);
        ordersInfo.setCreated(new Date());
        ordersServiceFacade.insRecord(ordersInfo);

        //创建费率信息
        OrdersMoneyInterfaceInfo moneyInterfaceInfo = new OrdersMoneyInterfaceInfo();
        moneyInterfaceInfo.setOrderid(Long.parseLong(orderid));
        moneyInterfaceInfo.setMoney(new BigDecimal(request.getProductTotalPrice()));
        moneyInterfaceInfo.setMtsid(merchInfo.getId());
        moneyInterfaceInfo.setMerchRate(merRate);
        Double merRateMoney = BigDecimalCalculateUtils.mul(totalPrice, new Double(merRate));
        moneyInterfaceInfo.setMerchMoney(new BigDecimal(new DecimalFormat("#.00").format(merRateMoney)));
        moneyInterfaceInfo.setAddtime(new Date());
        ordersMoneyInterfaceServiceFacade.addRecord(moneyInterfaceInfo);

        // 成功返回
        yjResponseDto.clear();
        yjResponseDto.setCode("10000");
        yjResponseDto.setInfo("创建订单成功,请跳转至signUrl");
        // 构建H5 URL后缀
        Map<String, String> tailMap = new HashMap<>();
        tailMap.put("merchNo", request.getMid());
        tailMap.put("orderid", request.getOrderid());
        tailMap.put("platformOrderid", orderid);
        String tailJson = JsonUtils.toJson(tailMap);
        String urlTail = AESUtils.encrypt(tailJson,ConfigUtil.getValue("AES_KEY"));
        // 构建返回的data
        Map<String, String> dataMap = new HashMap<>();
        // 给输出的signUrl urlEncode一下
        String signUrlUndecode = null;
        String signUrl = null;
        try{
            signUrlUndecode = ConfigUtil.getValue("TERMS_URL")+urlTail;
            signUrl = URLEncoder.encode(ConfigUtil.getValue("TERMS_URL")+urlTail, "UTF-8");
        }catch (UnsupportedEncodingException e){
            yjResponseDto.clear();
            yjResponseDto.setCode(JpfInterfaceErrorInfo.SIGNURL_ENCODING_ERROR.getCode());
            yjResponseDto.setInfo(JpfInterfaceErrorInfo.SIGNURL_ENCODING_ERROR.getDesc());

            return yjResponseDto;
        }
        // 获取signUrl的二进制流
        Map<String,Object> imageStreamRequest = new HashMap<>();
        imageStreamRequest.put("content",signUrlUndecode);
        String imageStream = OkHttpUtils.postForm(ConfigUtil.getValue("GET_IMAGE_STREAM_URL"), imageStreamRequest);

        // 返回参数
        dataMap.put("signUrl", signUrl);
        dataMap.put("imageStream", imageStream);
        dataMap.put("orderid", request.getOrderid());
        dataMap.put("platformOrderid", orderid);
        yjResponseDto.setData(dataMap);

        logger.info(dataMap);
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
        String dataJson = AESUtils.decrypt(data, ConfigUtil.getValue("AES_KEY"));
        Map<String,String> dataMap = JsonUtils.toCollection(dataJson, new TypeReference<HashMap<String,String>>(){});
        if ( dataMap.get("merchNo") == null || dataMap.get("orderid") == null || dataMap.get("platformOrderid") == null ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INCORRECT_DATA.getCode(), JpfInterfaceErrorInfo.INCORRECT_DATA.getDesc());
        }
        OrderYinjiaApiInfo orderYinjiaApiInfo = orderYinjiaApiServiceFacade.getOrderByOrderidAndForeignOrderid(dataMap.get("orderid"), dataMap.get("platformOrderid"), true);
        if ( orderYinjiaApiInfo.getPayStatus() == 1 ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.ORDER_CLOSED.getCode(), JpfInterfaceErrorInfo.ORDER_CLOSED.getDesc());
        }
        MerchantInterfaceInfo merInfo = merchantInterfaceServiceFacade.getMerchantByMerchNo(dataMap.get("merchNo"));
        MerchantPayTypeInfo merPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(merInfo.getId(), orderYinjiaApiInfo.getPaytype(), true);

        // 构建返回
        Map<String, Object> responseDataMap = new HashMap<>();
        responseDataMap.put("merchNo",dataMap.get("merchNo"));
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
        responseDataMap.put("money", orderYinjiaApiInfo.getOrderPayPrice().toString());
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

            /*case "15":
                catid = 29;
                break;

            case "24":
                catid = 30;
                break;*/
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
        OrderYinjiaApiInfo orderYinjiaApiInfo = orderYinjiaApiServiceFacade.getOrderByOrderid(request.getOrderid(),true);
        if ( orderYinjiaApiInfo.getPayStatus() == 1 ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.ORDER_CLOSED.getCode(), JpfInterfaceErrorInfo.ORDER_CLOSED.getDesc());
        }
        Long mtsid = orderYinjiaApiInfo.getMtsid();
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
        ordernameMap.put("payType",orderYinjiaApiInfo.getPaytype());
        String payType_cn = null;
        switch (orderYinjiaApiInfo.getPaytype()){
            case 6:
                payType_cn = "中银消费金融分期支付";
                break;

            case 7:
                payType_cn = "银联信用卡分期支付";
                break;

            case 8:
                payType_cn = "花呗分期支付";
                break;

            case 9:
                payType_cn = "微信全额支付";
                break;
        }
        ordernameMap.put("payType_cn",payType_cn);
        if ( StringUtils.isNotBlank(request.getTerm()) ){
            ordernameMap.put("stageType", catid);
            ordernameMap.put("stageType_cn",request.getTerm()+"期");
        }
        String ordernameJson = JsonUtils.toJson(ordernameMap);

        OrderYinjiaApiInfo orderInfoForUpdate = new OrderYinjiaApiInfo();
        orderInfoForUpdate.setOrderid(request.getOrderid());
        orderInfoForUpdate.setPayDetail(ordernameJson);
        orderInfoForUpdate.setUserOperateStatus((byte)1);
        int res = orderYinjiaApiServiceFacade.updateOrdername(orderInfoForUpdate,true);

        if ( res <= 0 ){
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("code", JpfInterfaceErrorInfo.UPDATE_ORDERNAME_FAILED.getCode());
            responseMap.put("info", JpfInterfaceErrorInfo.UPDATE_ORDERNAME_FAILED.getDesc());
            String responseJson = JsonUtils.toJson(responseMap);

            return Base64CustomUtils.base64Encoder(responseJson);
        }

        //获取分期费率
        VirtualInfo stageRateInfo = virtualInterfaceServiceFacade.getInfoByRelateId(catid);
        if ( StringUtils.isBlank(stageRateInfo.getIntro() ) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.STAGE_RATE_ERROR.getCode(), JpfInterfaceErrorInfo.STAGE_RATE_ERROR.getDesc());
        }
        //更新订单费率信息表
        OrdersMoneyInterfaceInfo moneyInterfaceInfo = new OrdersMoneyInterfaceInfo();
        moneyInterfaceInfo.setOrderid(Long.parseLong(orderYinjiaApiInfo.getOrderid()));
        moneyInterfaceInfo.setStageId(Integer.toString(catid));
        moneyInterfaceInfo.setStageName(request.getTerm()+"期");
        moneyInterfaceInfo.setStageRate(stageRateInfo.getIntro());
        Double orderMoney = new Double(orderYinjiaApiInfo.getOrderPayPrice().toString());
        Double stageRate = new Double(stageRateInfo.getIntro());
        Double stageRateMoney = BigDecimalCalculateUtils.mul(orderMoney, stageRate);
        moneyInterfaceInfo.setStageMoney(new BigDecimal(new DecimalFormat("#.00").format(stageRateMoney)));
        moneyInterfaceInfo.setUpdatetime(new Date());
        ordersMoneyInterfaceServiceFacade.modifyRecord(moneyInterfaceInfo);

        // 正确返回
        Map<String, String> responseDataMap = new HashMap<>();
        responseDataMap.put("orderid", request.getOrderid());
        responseDataMap.put("mid", ""+mtsid);
        String responseDataJson = JsonUtils.toJson(responseDataMap);
        String AESStr = AESUtils.encrypt(responseDataJson, ConfigUtil.getValue("AES_KEY"));

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

        String dataJson = AESUtils.decrypt(request.getData(), ConfigUtil.getValue("AES_KEY"));
        Map<String, String> dataMap = JsonUtils.toCollection(dataJson, new TypeReference<Map<String, String>>(){});
        String newSignOrderid = createOrderid();
        String oldSignOrderid = "";

        // 获取商户支付配置中的CP_MerchNO等参数
        MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(Long.parseLong(dataMap.get("mid")), 7, true);
        String paramJson = merchantPayTypeInfo.getParam();
        Map<String, String> paramMap = JsonUtils.toCollection(paramJson, new TypeReference<Map<String, String>>(){});

        // 判断用户有没有签约过
        OrderCpInterfaceInfo orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpBybankaccountnumber(request.getAccountNumber());
        String bankNumber = orderCpInterfaceInfo.getBankaccountnumber();
        String signStatus = orderCpInterfaceInfo.getSignstatus();
        String IP = ServletUtils.getIpAddr(httpRequest);

        BankCardInfo bankCardInfo = bankCardServiceFacade.getBankCardByCardNO(request.getAccountNumber());
        if(bankCardInfo.getType() == null || bankCardInfo.getType().equals("借记卡")){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请输入贷记卡信息",null);
        }
        // 从未签约过 || 签约过，但未成功
        if ( bankNumber == null || ( StringUtils.isNotBlank(bankNumber) && signStatus.equals("1") ) ){
            if ( bankNumber == null  ){
                // 从未签约过，准备插入一条签约记录
                OrderCpInterfaceInfo orderCpInsert = new OrderCpInterfaceInfo();
                orderCpInsert.setMerchNo(paramMap.get("CP_MerchaNo"));
                orderCpInsert.setOrderid(newSignOrderid);
                orderCpInsert.setMtsid(Long.parseLong(dataMap.get("mid")));
                orderCpInsert.setInterestmode((long)1);
                orderCpInsert.setSignedname(request.getSignedName());
                orderCpInsert.setIdtype((byte)1);
                orderCpInsert.setIdno(request.getIdNo().toUpperCase());
                orderCpInsert.setMobileno(request.getMobileNo());
                orderCpInsert.setSelectfinacode(request.getSelectFinaCode());
                orderCpInsert.setBankaccounttype((byte)2);
                orderCpInsert.setBankaccountnumber(request.getAccountNumber());
                orderCpInsert.setCvn2(Long.parseLong(request.getCvn2()));
                orderCpInsert.setValiditycard(""+request.getValidityCard()+"-01");
                // 设置过期时间
                Date validityyear = org.apache.commons.lang3.time.DateUtils.addYears(new Date(),1);
                orderCpInsert.setValidityyear(validityyear);
                // 设置IP
                orderCpInsert.setClientip("127.0.0.1");
                orderCpInsert.setSignstatus("1");
                orderCpInsert.setSysagreeno("");
                orderCpInsert.setCreated(DateUtils.getCurrentDate());

                int res = orderCpServiceFacade.insRecord(orderCpInsert);
                if ( res <= 0 ){
                    // 判断有没有成功添加新记录
                    return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode(), JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc(), null);
                }
            }else{
                // 如果不是第一次签约则将老的orderid获取到
                oldSignOrderid = orderCpInterfaceInfo.getOrderid();
            }

            // 构建返回加密串
            Map<String , Object> frontMap = new HashMap<>();
            frontMap.put("orderid",dataMap.get("orderid"));
            String AESJson = JsonUtils.toJson(frontMap);
            String frontAES = AESUtils.encrypt(AESJson,ConfigUtil.getValue("AES_KEY"));

            // 构建银联签约接口request参数
            Map<String,String> chinapayMap = new HashMap<>();
            chinapayMap.put("service","sign");
            chinapayMap.put("sysMerchNo", paramMap.get("CP_MerchaNo"));
            chinapayMap.put("inputCharset", "UTF-8");
            chinapayMap.put("interestMode", "01");
            chinapayMap.put("chnCode", paramMap.get("CP_Code"));
            chinapayMap.put("chnAcctId", paramMap.get("CP_Acctid"));
            chinapayMap.put("outOrderNo", newSignOrderid);
            chinapayMap.put("frontUrl", ConfigUtil.getValue("CHINAPAY_SIGN_RETURN_URL")+frontAES);
            logger.info("frontUrl="+ConfigUtil.getValue("CHINAPAY_SIGN_RETURN_URL")+frontAES+" length="+ConfigUtil.getValue("CHINAPAY_SIGN_RETURN_URL").length()+frontAES.length());
            chinapayMap.put("backUrl", ConfigUtil.getValue("CHINAPAY_SIGN_BACK_URL"));
            chinapayMap.put("signedName", request.getSignedName());
            chinapayMap.put("idType", "01");
            chinapayMap.put("idNo", request.getIdNo());
            chinapayMap.put("mobileNo", request.getMobileNo());
            chinapayMap.put("selectFinaCode", request.getSelectFinaCode());
            String accountType = "CREDIT";
            chinapayMap.put("accountType", accountType);
            chinapayMap.put("accountNumber", request.getAccountNumber());
            chinapayMap.put("clientIp", "127.0.0.1");
            if ( accountType.equals("CREDIT") ){
                chinapayMap.put("cvn2", request.getCvn2());
                String yearMonth[] = request.getValidityCard().split("-");
                chinapayMap.put("validityYear", StringUtils.substring(yearMonth[0],2));
                chinapayMap.put("validityMonth", yearMonth[1]);
            }
            Map<String, String> treeMap = new TreeMap<>();
            treeMap.putAll(chinapayMap);
            Iterator<String> iter = treeMap.keySet().iterator();
            StringBuilder sb = new StringBuilder();
            Map<String, Object> requestMap = new HashMap<>();
            while (iter.hasNext()){
                String k = iter.next();
                String v = treeMap.get(k);
                sb.append(k+"="+v+"&");

                requestMap.put(k,v);
            }
            String sbString = sb.toString();
            sbString = StringUtils.stripEnd(sbString,"&");
            String signMySign = Md5Encrypt.md5(sbString+paramMap.get("CP_Salt"),"UTF-8");
            requestMap.put("sign",signMySign);
            requestMap.put("signType","MD5");
            // 请求签约url
            String response = OkHttpUtils.postForm(ConfigUtil.getValue("CHINAPAY_URL_REQUEST")+"sign",requestMap);
            logger.info("签约返回："+response);

            Map<String, String> signResponseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>(){});
            String html = null;

            // 增加签约流水记录
            OrderCpMessageInfo orderCpMessageInfo = new OrderCpMessageInfo();
            orderCpMessageInfo.setOrderid(dataMap.get("orderid"));
            orderCpMessageInfo.setSignOrderid(newSignOrderid);
            orderCpMessageInfo.setReturnTranno(signResponseMap.get("tranNo"));
            orderCpMessageInfo.setRequestContent(ToolUtils.mapToUrl(requestMap));
            orderCpMessageInfo.setReturnContent(response);
            orderCpMessageServiceFacade.insRecord(orderCpMessageInfo);

            // 更新签约表
            OrderCpInterfaceInfo orderCpInfo = new OrderCpInterfaceInfo();
            String signOrderid = "";
            // 判断更新数据时取的是老orderid还是新orderid
            if ( StringUtils.isBlank(oldSignOrderid) ){
                signOrderid = newSignOrderid;
            }else{
                signOrderid = oldSignOrderid;
            }
            orderCpInfo.setOrderid(signOrderid);
            orderCpInfo.setNewSignOrderid(newSignOrderid);
            orderCpInfo.setReturnContent(response);
            logger.info(signResponseMap);
            // 获取返回参数
            if ( signResponseMap.get("retCode").equals("0000") ){
                // 签约通知成功
                orderCpInfo.setTranno(signResponseMap.get("tranNo"));
                orderCpInfo.setSignstatus("1");
                if ( signResponseMap.containsKey("sysAgreeNo") ){
                    orderCpInfo.setSysagreeno(signResponseMap.get("sysAgreeNo"));
                }
                int updateOrderCpRes = orderCpServiceFacade.updateRecord(orderCpInfo);
                if ( updateOrderCpRes <= 0 ){
                    return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode(), JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc(), null);
                }

                // 更新订单表的signOrderid
                OrderYinjiaApiInfo orderYinjiaApiInfo = new OrderYinjiaApiInfo();
                orderYinjiaApiInfo.setOrderid(dataMap.get("orderid"));
                orderYinjiaApiInfo.setSignOrderid(Long.parseLong(signOrderid));
                orderYinjiaApiInfo.setUserOperateStatus((byte)2);
                int updateOrderRes = orderYinjiaApiServiceFacade.updataSignOrderid(orderYinjiaApiInfo);
                if ( updateOrderRes <= 0 ){
                    return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode(), JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc(), null);
                }

                if ( updateOrderCpRes > 0 && updateOrderRes > 0 ){
                    // 处理返回的url
                    /*Pattern pattern = Pattern.compile("<form.*?>(.*?)</form>");
                    matcher = pattern.matcher(signResponseMap.get("autoSubmitForm"));*/

                    html = StringUtils.substringBetween(signResponseMap.get("autoSubmitForm"), "<body>","</body>");
                }
            }else{
                // 签约通知失败
                // 更新签约表的同步回调结果
                int updateOrderCpRes = orderCpServiceFacade.updateRecord(orderCpInfo);
                if ( updateOrderCpRes <= 0 ){
                    return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode(), JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc(), null);
                }
                // 更新订单表的用户状态为3：签约通知失败
                OrderYinjiaApiInfo updateOrderInfo = new OrderYinjiaApiInfo();
                updateOrderInfo.setOrderid(dataMap.get("orderid"));
                updateOrderInfo.setUserOperateStatus((byte)3);
                int updateOrderRes = orderYinjiaApiServiceFacade.updateColumnByOrderid(updateOrderInfo);
                if ( updateOrderRes <= 0 ){
                    return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode(), JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc(), null);
                }

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SIGN_FAILED.getCode(), signResponseMap.get("retMsg"), null);
            }

            // 构建返回
            Map<String, Object> responseDataMap = new HashMap<>();
            responseDataMap.put("html", html);
            String responseDataJson = JsonUtils.toJson(responseDataMap);

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_NOT_SIGNED.getCode(), JpfInterfaceErrorInfo.USER_NOT_SIGNED.getDesc(),responseDataJson);

        }

        // 已签约
        // 更新订单表的signOrderid
        OrderYinjiaApiInfo updateOrderinfo = new OrderYinjiaApiInfo();
        updateOrderinfo.setOrderid(dataMap.get("orderid"));
        updateOrderinfo.setSignOrderid(Long.parseLong(orderCpInterfaceInfo.getOrderid()));
        int updateOrderRes = orderYinjiaApiServiceFacade.updataSignOrderid(updateOrderinfo);
        if ( updateOrderRes <= 0 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getCode(), JpfInterfaceErrorInfo.UPDATE_SIGN_ORDER_ERROR.getDesc(), null);
        }

        Map<String, Object> responseDataMap = new HashMap<>();
        responseDataMap.put("orderid", dataMap.get("orderid"));
        String responseDataJson = JsonUtils.toJson(responseDataMap);
        String AESStr = AESUtils.encrypt(responseDataJson, ConfigUtil.getValue("AES_KEY"));

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
        String dataJson = AESUtils.decrypt(data, ConfigUtil.getValue("AES_KEY"));
        Map<String, String> dataMap = JsonUtils.toCollection(dataJson, new TypeReference<Map<String, String>>(){});

        // 获取订单信息
        OrderYinjiaApiInfo orderYinjiaApiInfo = orderYinjiaApiServiceFacade.getOrderByOrderid(dataMap.get("orderid"),true);
        if ( orderYinjiaApiInfo.getPayStatus() == 1 ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.ORDER_CLOSED.getCode(), JpfInterfaceErrorInfo.ORDER_CLOSED.getDesc());
        }
        String foreignRequest = orderYinjiaApiInfo.getForeignRequest();
        Map<String, String> foreignRequestMap = ToolUtils.urlToMap(foreignRequest);

        // 获取签约信息
        OrderCpInterfaceInfo orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpByorderid(orderYinjiaApiInfo.getSignOrderid().toString());

        Map<String, String> responseDataMap = new HashMap<>();
        responseDataMap.put("productName", foreignRequestMap.get("productName"));
        responseDataMap.put("orderPrice", orderYinjiaApiInfo.getOrderPayPrice().toString());
        responseDataMap.put("bankAccountNumber", orderCpInterfaceInfo.getBankaccountnumber());
        responseDataMap.put("signedName", orderCpInterfaceInfo.getSignedname());
        String responseDataJson = JsonUtils.toJson(responseDataMap);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(),responseDataJson);
    }

    /**
     * 签约回调通知地址
     */
    @RequestMapping(value = "/signNotify", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String signNotify(YinjiaSignNotifyRequest request, HttpServletRequest httpRequest){
        logger.info("签约异步回调："+httpRequest);

        // 根据签约订单号获取业务订单号
        OrderYinjiaApiInfo orderInfo = orderYinjiaApiServiceFacade.getOrderBySignOrderid(request.getOutOrderNo(),true);

        // 签约流水表增加异步回调记录
        String notifyContent = ToolUtils.mapToUrl(request.toMap());
        OrderCpMessageInfo orderCpMessageInfo = new OrderCpMessageInfo();
        orderCpMessageInfo.setOrderid(orderInfo.getOrderid());
        orderCpMessageInfo.setSignOrderid(request.getOutOrderNo());
        orderCpMessageInfo.setNotifyTranno(request.getTranNo());
        orderCpMessageInfo.setNotifyContent(notifyContent);
        orderCpMessageServiceFacade.insRecord(orderCpMessageInfo);

        // 更新订单表用户操作状态
        OrderYinjiaApiInfo orderYinjiaApiInfo = new OrderYinjiaApiInfo();
        orderYinjiaApiInfo.setSignOrderid(Long.parseLong(request.getOutOrderNo()));

        OrderCpInterfaceInfo orderCpInterfaceInfo = new OrderCpInterfaceInfo();
        orderCpInterfaceInfo.setOrderid(request.getOutOrderNo());

        if ( request.getSignStatus().equals("SUCCESS") ){
            // 更新签约状态为已签约
            orderCpInterfaceInfo.setSignstatus("2");
            orderCpServiceFacade.updateRecord(orderCpInterfaceInfo);

            // 更新用户操作状态为4：签约返回成功,待获取支付短信
            orderYinjiaApiInfo.setUserOperateStatus((byte)4);
            orderYinjiaApiServiceFacade.updateColumnBySignOrderid(orderYinjiaApiInfo);
        }else if (request.getSignStatus().equals("FAIL")){
            // 更新签约状态为签约失败
            orderCpInterfaceInfo.setSignstatus("3");
            orderCpServiceFacade.updateRecord(orderCpInterfaceInfo);

            // 更新用户操作状态为4：签约返回成功,待获取支付短信
            orderYinjiaApiInfo.setUserOperateStatus((byte)5);
            orderYinjiaApiServiceFacade.updateColumnBySignOrderid(orderYinjiaApiInfo);
        }

        return "SUCCESS";
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
        String dataStr = AESUtils.decrypt(data, ConfigUtil.getValue("AES_KEY"));
        Map<String, String> dataMap = JsonUtils.toCollection(dataStr, new TypeReference<Map<String, String>>(){});
        String orderid = dataMap.get("orderid");

        // 发送短信接口地址
        String requestUrl = ConfigUtil.getValue("CHINAPAY_URL_REQUEST")+"smsCodeSend";

        //定义银联支付方式id
        Integer tpid = 7;

        if ( StringUtils.isBlank(orderid) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "orderid不能为空", null);
        }
        //获取订单信息
        OrderYinjiaApiInfo orderInfo = orderYinjiaApiServiceFacade.getOrderByOrderid(orderid.trim(),true);
        if ( orderInfo.getPayStatus() == 1 ){
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.ORDER_CLOSED.getCode(), JpfInterfaceErrorInfo.ORDER_CLOSED.getDesc());
        }
        if ( orderInfo.getSignOrderid()==null )
        {
            //throw new JpfInterfaceException(JpfInterfaceErrorInfo.MER_SIGE_NOT.getCode(), "用户信息未签约");
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到订单相关用户信息", null);
        }
        //商户签约信息
        OrderCpInterfaceInfo orderCpInfo = orderCpServiceFacade.getOrderCpByorderid(orderInfo.getSignOrderid().toString());
        String stage="0";
        if(orderCpInfo!=null){

            if(orderInfo.getPayStatus().equals(1)){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "此单已支付", null);
            }
            if(!orderCpInfo.getSignstatus().equals("2")){

                ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_NOT_SIGNED.getCode(), JpfInterfaceErrorInfo.USER_NOT_SIGNED.getCode(), null);
            }
            Map<String,String> ordernameJson = new HashMap<>();
            ordernameJson = JsonUtils.toObject(orderInfo.getPayDetail(), Map.class);
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
        MerchantInterfaceInfo merchant = merchantInterfaceServiceFacade.getMerchantByMid(orderInfo.getMtsid());
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
            maptree.put("tranAmt",orderInfo.getOrderPayPrice().toString());
            maptree.put("privatekey",maparr.get("CP_Salt"));
            maptree.put("numberOfInstallments",stage);
            YjResponseDto yjResponseDto = new YjResponseDto();
            yjResponseDto = chinaPayServiceFacade.ChinaPaySmsCodeSend(maptree,requestUrl);

            String smeRes = yjResponseDto.getData().toString();
            chinaRe = JsonUtils.toCollection(smeRes,new TypeReference<HashMap<String, String>>(){});

            OrderYinjiaApiInfo orderYinjiaApiInfo = new OrderYinjiaApiInfo();
            orderYinjiaApiInfo.setOrderid(orderid);
            if(chinaRe.containsKey("retCode") && chinaRe.get("retCode").equals("0000")){
                // 更新用户操作状态为7：短信发送成功,待支付
                orderYinjiaApiInfo.setUserOperateStatus((byte)7);
                orderYinjiaApiServiceFacade.updateColumnByOrderid(orderYinjiaApiInfo);

                code = JpfInterfaceErrorInfo.SUCCESS.getCode();
                msg = "短信发送成功";
                //yjResponseDto.setInfo("短信发送成功");
                //yjResponseDto.clearData();
            }else{
                orderYinjiaApiInfo.setUserOperateStatus((byte)8);
                orderYinjiaApiServiceFacade.updateColumnByOrderid(orderYinjiaApiInfo);

                code = JpfInterfaceErrorInfo.FAIL.getCode();
                msg = "短信发送失败";
                /*yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("短信发送失败");
                yjResponseDto.clearData();*/
            }

            // 增加短信流水表
            SmsMessageInfo smsMessageInfo = new SmsMessageInfo();
            smsMessageInfo.setOrderid(orderid);
            smsMessageInfo.setMtsid(orderInfo.getMtsid().toString());
            smsMessageInfo.setPtype((byte)3);
            smsMessageInfo.setPtypeCn(" ");
            smsMessageInfo.setSendtype((byte)1);
            smsMessageInfo.setSendtypeCn("支付");
            String returnContent = "sysMerchNo="+chinaRe.get("sysMerchNo")+"&sign="+chinaRe.get("sign")+"&retCode="+chinaRe.get("retCode")+"&retMsg="+chinaRe.get("retMsg");
            smsMessageInfo.setReturnContent(returnContent);
            smsMessageServiceFacade.insRecord(smsMessageInfo);

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
        String mid = request.getParameter("mid");
        String orderid = request.getParameter("orderid");//退款单号
        String origOrderid = request.getParameter("platformOrderid");//原始订单号
        String backUrl = request.getParameter("backUrl");//后台回调地址
        String refundAmt = request.getParameter("refundAmt");//退款金额
        String sign = request.getParameter("sign");//签名

        String requestUrl = ConfigUtil.getValue("CHINAPAY_URL_REQUEST")+"purchaseRefund";
        YjResponseDto yjResponseDto = new YjResponseDto();

        if ( StringUtils.isBlank(orderid) || StringUtils.isBlank(origOrderid) || StringUtils.isBlank(mid) || StringUtils.isBlank(backUrl) || StringUtils.isBlank(refundAmt))
        {
            yjResponseDto.setCode("10008");
            yjResponseDto.setInfo("请求参数不合法");
            return yjResponseDto;
        }
        //获取订单信息
        OrderYinjiaApiInfo orderInfo = orderYinjiaApiServiceFacade.getOrderByOrderid(origOrderid.trim(),true);
        //获取商户信息
        MerchantInterfaceInfo merchant = merchantInterfaceServiceFacade.getMerchantByMerchNo(mid);
        Map<String,Object> signParam = new HashMap<String,Object>();

        signParam.put("mid",mid);
        signParam.put("orderid",orderid);
        signParam.put("platformOrderid",origOrderid);
        signParam.put("backUrl",backUrl);
        signParam.put("refundAmt",refundAmt);

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

        Integer tpid=7;
        BigDecimal refundPrice =new BigDecimal(refundAmt);// Long.valueOf(refundAmt).longValue();
        if(orderInfo!=null){
            if(refundPrice.compareTo(orderInfo.getOrderPayPrice())>0){

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("退款金额有误");
                return yjResponseDto;
            }
            if(!orderInfo.getPayStatus().toString().equals("1")){

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("订单状态有误，请查看是否已支付");
                return yjResponseDto;
            }
            if(!merchant.getMerchNo().equals(mid) ){

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
        //退单信息入库
        OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
        orderRefundInfo.setOrderid(origOrderid);
        orderRefundInfo.setRefundOrderid(orderid);
        orderRefundInfo.setMoney(new BigDecimal(refundAmt));
        orderRefundInfo.setStatus("1");
        orderRefundInfo.setBackurl(backUrl);
        orderRefundInfo.setCreated(new Date());

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
            maptree.put("backUrl",ConfigUtil.getValue("CHINAPAY_REFUND_BACK_URL"));
            maptree.put("privatekey",maparr.get("CP_Salt"));

            yjResponseDto = chinaPayServiceFacade.ChinaPayRefund(maptree,requestUrl);

            String smeRes = yjResponseDto.getData().toString();
            chinaRe = JsonUtils.toCollection(smeRes,new TypeReference<HashMap<String, String>>(){});

            //退单信息入库
            orderRefundInfo.setReturnContent(smeRes);
            orderRefundServiceFacade.insOrderRefund(orderRefundInfo);

            // 新增退款流水信息
            OrderRefundMessageInfo orderRefundMessageInfo = new OrderRefundMessageInfo();
            orderRefundMessageInfo.setOrderid(orderid);
            orderRefundMessageInfo.setType((byte)0);
            orderRefundMessageInfo.setRequestContent(ToolUtils.mapToUrl(maptree));
            orderRefundMessageInfo.setTranno(chinaRe.get("tranNo"));
            orderRefundMessageInfo.setResponseContent(smeRes);
            orderRefundMessageServiceFacade.insRecord(orderRefundMessageInfo);

            // 更新订单退款状态
            OrderYinjiaApiInfo orderYinjiaApiInfo = new OrderYinjiaApiInfo();
            orderYinjiaApiInfo.setOrderid(orderid);

            if(chinaRe.containsKey("retCode") && chinaRe.get("retCode").equals("0000")){

                orderYinjiaApiInfo.setRefundStatus((byte)2);
                orderYinjiaApiServiceFacade.updateColumnByOrderid(orderYinjiaApiInfo);

                yjResponseDto.setCode("10000");
                yjResponseDto.setInfo("退款已受理");
                yjResponseDto.clearData();
            }else{

                orderYinjiaApiInfo.setRefundStatus((byte)3);
                orderYinjiaApiServiceFacade.updateColumnByOrderid(orderYinjiaApiInfo);

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
        OrderYinjiaApiInfo orderInfo = orderYinjiaApiServiceFacade.getOrderByOrderid(refundCancel.get("oriOrderNo").toString(), true);
        //获取退单表信息
        OrderRefundInfo getOrderRefund = orderRefundServiceFacade.getOrderRefund(refundCancel.get("outOrderNo").toString());
        //获取商户信息
        MerchantInterfaceInfo merchant = merchantInterfaceServiceFacade.getMerchantByMid(orderInfo.getMtsid());

        //获取商户银联支付方式配置
        MerchantPayTypeInfo merchantPayTypeInfo = merPayTypeServiceFacade.getOneMerPayTypeByTpid(orderInfo.getMtsid(),orderInfo.getPaytype());
        Map<String, String> maparr = JsonUtils.toCollection(merchantPayTypeInfo.getParam(),new TypeReference<HashMap<String, String>>(){});

        Map<String,Object> treeRefundCancel = new TreeMap<String,Object>();
        treeRefundCancel.putAll(refundCancel);
        String getKeyVal = ToolUtils.mapToUrl(treeRefundCancel);
        String getSign = Md5Encrypt.md5(getKeyVal+maparr.get("CP_Salt"));

        refundCancel.put("sign",sign);
        refundCancel.put("signType",signType);

        // 增加退款异步回调流水
        OrderRefundMessageInfo orderRefundMessageInfo = new OrderRefundMessageInfo();
        orderRefundMessageInfo.setOrderid(request.getParameter("outOrderNo"));
        orderRefundMessageInfo.setType((byte)1);
        orderRefundMessageInfo.setTranno(request.getParameter("tranNo"));
        orderRefundMessageInfo.setResponseContent(ToolUtils.mapToUrl(refundCancel));
        orderRefundMessageServiceFacade.insRecord(orderRefundMessageInfo);

        OrderRefundInfo orderRefundInfo = new OrderRefundInfo();
        orderRefundInfo.setTranno(refundCancel.get("tranNo").toString());
        orderRefundInfo.setTrantype( Byte.valueOf(refundCancel.get("tranType").toString()));
        orderRefundInfo.setNotifyTime(new Date());
        orderRefundInfo.setResponsParam(JsonUtils.toJson(refundCancel));
        orderRefundInfo.setRefundOrderid(refundCancel.get("outOrderNo").toString());

        //更新退单表相关退单状态信息
        OrderYinjiaApiInfo orderStauts = new OrderYinjiaApiInfo();
//        orderStauts.setUpdatetime(new Date());
        orderStauts.setOrderid(refundCancel.get("oriOrderNo").toString());

        //日志记录
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n回调信息：" + getKeyVal+"&sign="+sign+"&signType="+signType);

        // 更新订单的用户操作状态
        OrderYinjiaApiInfo orderYinjiaApiInfo = new OrderYinjiaApiInfo();
        orderYinjiaApiInfo.setOrderid(request.getParameter("outOrderNo"));

        if(!getSign.equals(sign)){

            sbf.append("\n签名失败：回调签名sign="+sign+"，接口签名sign="+getSign);
            orderRefundInfo.setStatus("3");
        }else if(refundCancel.get("tranResult").equals("SUCCESS")){

            // 更新订单的用户操作状态为4：异步返回成功，退款成功
            orderYinjiaApiInfo.setRefundStatus((byte)4);
            orderYinjiaApiServiceFacade.updateColumnByOrderid(orderYinjiaApiInfo);

            sbf.append("\n退款成功");
            orderRefundInfo.setStatus("2");
            orderStauts.setRefundStatus(Byte.valueOf("4"));
        }else{

            // 更新订单的用户操作状态为5：异步返回失败，退款失败
            orderYinjiaApiInfo.setRefundStatus((byte)5);
            orderYinjiaApiServiceFacade.updateColumnByOrderid(orderYinjiaApiInfo);

            sbf.append("\n退款失败");
            orderRefundInfo.setStatus("3");
            orderStauts.setRefundStatus(Byte.valueOf("5"));
        }
        //修改退单表信息
        orderRefundServiceFacade.upOrderRefundByRefundOrder(orderRefundInfo);
        //修改订单表信息
        orderYinjiaApiServiceFacade.updateColumnByOrderid(orderStauts);

        //触发请求第三方
        Map<String,Object> postParam= new HashMap<String,Object>();

        postParam.put("mid",orderInfo.getMtsid());
        postParam.put("orderid",request.getParameter("outOrderNo"));
        postParam.put("platformOrderid",request.getParameter("oriOrderNo"));
        postParam.put("tranResult",request.getParameter("tranResult"));
        postParam.put("tranAmt",request.getParameter("tranAmt"));
        postParam.put("finishTime",request.getParameter("finishTime"));

        Map<String,Object> postParamTree= new TreeMap<String,Object>();
        postParamTree.putAll(postParam);

        String postSign = SignUtils.getSign(postParamTree,merchant.getPrivateKey(),"UTF-8");
        postParam.put("sign",postSign);

        String postP = ToolUtils.mapToUrl(postParam);

        sbf.append("\n请求地址："+getOrderRefund.getBackurl());
        sbf.append("\n请求参数："+postP);

        String fileName = "ChinaPurchaseCancelReturn";

        LogsCustomUtils.writeIntoFile(sbf.toString(),"", fileName,true);

        String response = OkHttpUtils.postForm(getOrderRefund.getBackurl(),postParam);

        if(response.equals("SUCCESS") || response.equals("\"SUCCESS\"")){

            return "SUCCESS";
        }
        return "NOTICE";
    }

    @RequestMapping("/checkCard")
    @ResponseBody
    /*
    * @param cardNo银行卡号
    * @param cardType银行卡类型 1 借记卡，2贷记卡
    * */
    public String checkCard(String cardNo ,String cardType){

        BankCardInfo bankCardInfo = bankCardServiceFacade.getBankCardByCardNO(cardNo);
        if(StringUtils.isBlank(cardType)){

            cardType = "2";
        }
        if(cardType.equals("2")){

            if(bankCardInfo.getType() == null || bankCardInfo.getType().equals("借记卡")){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请输入贷记卡信息",null);
            }
        }else{
            if(bankCardInfo.getType() == null || !bankCardInfo.getType().equals("借记卡")){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请输入借记卡信息",null);
            }
        }
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
        String urlTail = AESUtils.encrypt(tailJson,ConfigUtil.getValue("AES_KEY"));

        return urlTail;
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String test2(){
        String html = "<body><form id = \\\"pay_form\\\" action=\\\"https://gateway.95516.com/gateway/api/frontTransReq.do\\\" method=\\\"post\\\"><input type=\\\"hidden\\\" name=\\\"accessType\\\" id=\\\"accessType\\\" value=\\\"0\\\"/><input type=\\\"hidden\\\" name=\\\"backUrl\\\" id=\\\"backUrl\\\" value=\\\"http://vip2.7shengqian.com/unipayNoti/chn/backNotify/UNIONPAY_INSTAL/99\\\"/><input type=\\\"hidden\\\" name=\\\"bizType\\\" id=\\\"bizType\\\" value=\\\"000301\\\"/><input type=\\\"hidden\\\" name=\\\"certId\\\" id=\\\"certId\\\" value=\\\"74778248003\\\"/><input type=\\\"hidden\\\" name=\\\"channelType\\\" id=\\\"channelType\\\" value=\\\"07\\\"/><input type=\\\"hidden\\\" name=\\\"encoding\\\" id=\\\"encoding\\\" value=\\\"UTF-8\\\"/><input type=\\\"hidden\\\" name=\\\"frontUrl\\\" id=\\\"frontUrl\\\" value=\\\"http://vip2.7shengqian.com/unipayNoti/chn/frontNotify/UNIONPAY_INSTAL/99\\\"/><input type=\\\"hidden\\\" name=\\\"merId\\\" id=\\\"merId\\\" value=\\\"802310048161356\\\"/><input type=\\\"hidden\\\" name=\\\"orderId\\\" id=\\\"orderId\\\" value=\\\"1000020141\\\"/><input type=\\\"hidden\\\" name=\\\"signMethod\\\" id=\\\"signMethod\\\" value=\\\"01\\\"/><input type=\\\"hidden\\\" name=\\\"signature\\\" id=\\\"signature\\\" value=\\\"cz+XJzC8gxPkvTOGyCad6nz9GgObvT6m2KC0tA3LrdAEiHpOQ9I5ip27+Z4GoyVwKTIn5kHxe+pKOhcaYdUMgq8w+gqtypH/lEUbHzKOmXGDHb0BXguMlnlNFbTmdPlt/xmASwsd49UhwpHxu3g2AZ2ZN2HRjD+Yhs1MER/8YkX6Bg4xyPtERi7/LqUGkyEuW6ELHpBtHu4lCGMPRSI2JBLmB5bUvo1tUrYn01IzXYvsJ4j20DBbGa6yhULif+OWn2x4Upqpl7az3fPFtKiyYWClxVSjKkBN5PCaLFHgxrTENhHCCEQx93puHEveA0RUyQl7oj59xBOnEsMV+mleCw==\\\"/><input type=\\\"hidden\\\" name=\\\"txnSubType\\\" id=\\\"txnSubType\\\" value=\\\"00\\\"/><input type=\\\"hidden\\\" name=\\\"txnTime\\\" id=\\\"txnTime\\\" value=\\\"20180528112220\\\"/><input type=\\\"hidden\\\" name=\\\"txnType\\\" id=\\\"txnType\\\" value=\\\"79\\\"/><input type=\\\"hidden\\\" name=\\\"version\\\" id=\\\"version\\\" value=\\\"5.1.0\\\"/></form></body><script type=\\\"text/javascript\\\">document.all.pay_form.submit();</script>";

        Map<String , Object> frontMap = new HashMap<>();
        frontMap.put("orderid", "1447487259621113");
        String AESJson = JsonUtils.toJson(frontMap);
        String frontAES = AESUtils.encrypt(AESJson,ConfigUtil.getValue("AES_KEY"));

        return frontAES;
    }

    @ModelAttribute
    public void beforeAction(HttpServletRequest httpRequest, HttpServletResponse response)
    {
        // 兼容跨域问题
        String originHeader = httpRequest.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.setHeader("Access-Control-Allow-Method", "POST");
        response.setHeader("Access-Control-Allow-Origin", originHeader);

        // 不允许正式服访问这个测试项目文件
        if ( ConfigUtil.getValue("CHINAPAY_URL_REQUEST").equals("http://vip.7shengqian.com/trade/install/") ){
            notCorrectAddress();
        }
    }

    public YjResponseDto notCorrectAddress(){
        throw new JpfInterfaceException("999", "请访问正确的接口文件");
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
//        YjResponseDto dto = new YjResponseDto();
        if ( StringUtils.isBlank( request.getSmsCode() ) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "验证码错误", null);
        }

        String dataJson = AESUtils.decrypt(request.getData(), ConfigUtil.getValue("AES_KEY"));
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
            if ( orderInfo.getPayStatus().equals(1) )
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单已支付", null);
            }
            if ( !orderCpInfo.getSignstatus().equals("2") )
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "签约受理中，稍后再试", null);
            }
            if ( StringUtils.isBlank(orderInfo.getPayDetail()) )
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单分期信息非法", null);
            }
            Map<String,String> ordernameMap = new HashMap<>();
            ordernameMap = JsonUtils.toObject(orderInfo.getPayDetail(), Map.class);
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
            MerchantInterfaceInfo merInfo = merchantInterfaceServiceFacade.getMerchantByMid(orderInfo.getMtsid());
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
            signMap.put("backUrl",ConfigUtil.getValue("CHINAPAY_PAYBACKURL"));
            signMap.put("numberOfInstallments",stage_tmp);
            // 设置IP
            String IP = ServletUtils.getIpAddr(httpRequest);
            signMap.put("clientIp", IP);
            // 接口用到的参数
            signMap.put("CP_Salt", paramMap.get("CP_Salt"));
            String requestUrl;

            requestUrl = ConfigUtil.getValue("CHINAPAY_URL_REQUEST") + "installPay";

            YjResponseDto resultPay = chinaPayServiceFacade.IntallPay(signMap, requestUrl);

            //请求结果
//            Map<String,String> resultMap = JsonUtils.toCollection(resultPay.getInfo(), new TypeReference<Map<String, String>>(){});
            //请求参数
//            ModifyPayMessageRequest modifyPayMessageRequest = new ModifyPayMessageRequest();
//            modifyPayMessageRequest.setOrderid(dataMap.get("orderid"));
//            modifyPayMessageRequest.setReturnContent(resultPay.getInfo());
//            modifyPayMessageRequest.setAddtime(new Date());
//            modifyPayMessageRequest.setContent(requestUrl + "?" + resultPay.getData().toString());

            //更新用户操作状态
            OrderInterfaceInfo orderInfoUpdate = new OrderInterfaceInfo();
            String returnInfo;
            String returnData;
            String returnCode;
            byte userOperateStatue;
//            if ( resultMap.containsKey("retCode") && resultMap.get("retCode").equals("0000") )
//            {
                returnCode = JpfInterfaceErrorInfo.SUCCESS.getCode();
                returnInfo = "支付已受理";
                returnData = orderInfo.getReturnUrl();
                userOperateStatue = 9;
//                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"支付已受理",orderInfo.getReturnUrl());
//            }else
//            {
//                returnCode = JpfInterfaceErrorInfo.FAIL.getCode();
//                returnInfo = "渠道受理有误，请重新下单支付";
//                returnData = null;
//                userOperateStatue = 10;
////                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"渠道受理有误，请重新下单支付",null);
//            }

            //更新用户操作状态
            orderInfoUpdate.setOrderid(orderInfo.getOrderid());    //resultMap.get("outOrderNo")接口暂时没有返回
            orderInfoUpdate.setUserOperateStatus(userOperateStatue);
            orderInfoUpdate.setUpdatetime(new Date());
            orderInterfaceServiceFacade.updateOrderStatus(orderInfoUpdate);

            //添加聚合流水  ---没有添加tranNo
//            pcaServiceFacade.addPayMessage(modifyPayMessageRequest);

/*            //添加返回给商户的流水
            ModifyPayOrderPayMerRequest merPayRequest = new ModifyPayOrderPayMerRequest();
            merPayRequest.setAddtime(new Date());
            merPayRequest.setForeignOrderid(orderInfo.getForeignOrderid());
            merPayRequest.setOrderid(orderInfo.getOrderid());
            Map<String,String> merPayRequestMap = new HashMap<>();
            merPayRequestMap.put("code", returnCode);
            merPayRequestMap.put("info", returnInfo);
            merPayRequestMap.put("data", returnData);
            String merPayRequestjson = JsonUtils.toJson(merPayRequestMap);
            merPayRequest.setReturnContent(merPayRequestjson);
            pcaServiceFacade.addPayMerMessage(merPayRequest);*/
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"支付已受理",orderInfo.getReturnUrl());
//            return ToolUtils.toJsonBase64(returnCode,returnInfo, returnData);
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
        //添加回调流水
        ModifyPayMessageRequest modifyPayMessageRequest = new ModifyPayMessageRequest();
        modifyPayMessageRequest.setNotifyTranno(request.getTranNo());
        modifyPayMessageRequest.setOrderid(request.getOutOrderNo());
        Map<String,Object> requestMap = ClassUtil.requestToMap(request);
        String requestStr = ToolUtils.mapToUrl(requestMap);
        modifyPayMessageRequest.setNotifyContent(requestStr);
        modifyPayMessageRequest.setAddtime(new Date());
        modifyPayMessageRequest.setType((byte)2);
        int insertPayMsgRes = pcaServiceFacade.addPayMessage(modifyPayMessageRequest);

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
        MerchantInterfaceInfo merchInfo = merchantInterfaceServiceFacade.getMerchantByMid( orderInfo.getMtsid() );
        // 获取该商户银联信用卡分期支付的配置信息
        MerchantPayTypeInfo merchantPayTypeInfo  = merPayTypeServiceFacade.getOneMerPayTypeByTpid(Long.parseLong( orderInfo.getMtsid().toString() ),7, true);
        Map<String,String> paramMap = JsonUtils.toObject(merchantPayTypeInfo.getParam(),Map.class);
        //验证支付参数是否正确

        //sort
        TreeMap<String,Object> dataMap = new TreeMap<>();
        dataMap.putAll(returnMap);
        //签名
        String sortStr = ToolUtils.mapToUrl(dataMap);
        String signMd5 = Md5Encrypt.md5(sortStr + paramMap.get("CP_Salt"));
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n回调信息：" + returnMap);
        OrderInterfaceInfo orderInfoUpdate = new OrderInterfaceInfo();
        byte orderstatus;
        byte user_operate_status;
        if ( !signMd5.equals(request.getSign()) || !request.getTranResult().equals("SUCCESS") )
        {
            sbf.append("\n支付失败：签名失败或者支付失败，订单状态更新为失败");
            sbf.append("\n回调签名：" + signMd5);
            orderstatus = 2;
            user_operate_status = 12;
        }else
        {
            orderstatus = 1;
            sbf.append("\n回调签名：" + signMd5);
            sbf.append("\n支付成功修改数据库状态完成");
            user_operate_status = 11;
        }

        //更新订单用户操作状态
        orderInfoUpdate.setPayStatus(orderstatus);
        orderInfoUpdate.setPaytime(date);
        orderInfoUpdate.setUpdatetime(date);
        orderInfoUpdate.setOrderid(returnMap.get("outOrderNo").toString());
        orderInfoUpdate.setUserOperateStatus(user_operate_status);
        orderInterfaceServiceFacade.updateOrderStatus(orderInfoUpdate);

        //日志
        String fileName = "ChinaPayReturn";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"", fileName,true);

        //通知商户
        Map<String,Object> merPostParamMap = new HashMap<>();
        merPostParamMap.put("finishTime",request.getFinishTime());
        merPostParamMap.put("mid",orderInfo.getMtsid().toString());
        merPostParamMap.put("tranAmt",request.getTranAmt());
        merPostParamMap.put("tranResult",request.getTranResult());
        merPostParamMap.put("orderid",orderInfo.getForeignOrderid());    //商户订单号
        merPostParamMap.put("platformOrderid",request.getOutOrderNo());    //平台订单号

        Map<String,Object> postParamTree= new TreeMap<String,Object>();
        postParamTree.putAll(merPostParamMap);

        String postSign = SignUtils.getSign(postParamTree,merchInfo.getPrivateKey(),"UTF-8");
        merPostParamMap.put("sign",postSign);
        String merPostJson = JsonUtils.toJson(merPostParamMap);

        String notify_url = null;
        StringBuilder sbf_mer = new StringBuilder();
        String fileName_merNofity = "merPayNotify";

        try{
            notify_url = URLDecoder.decode(orderInfo.getNotifyUrl(), "UTF-8");
            //添加通知商户流水
            ModifyPayOrderPayMerRequest merPayRequest = new ModifyPayOrderPayMerRequest();
            merPayRequest.setAddtime(new Date());
            merPayRequest.setOrderid(orderInfo.getOrderid());
            merPayRequest.setForeignOrderid(orderInfo.getForeignOrderid());
            String merRequestStr = ToolUtils.mapToUrl(merPostParamMap);
            merPayRequest.setNotifyContent(notify_url + "?" + merRequestStr);
            int insertMerMessageRes = pcaServiceFacade.addPayMerMessage(merPayRequest);

            //更新聚合回调流水
            ModifyPayMessageRequest modifyPayMessageRequestUp = new ModifyPayMessageRequest();
            modifyPayMessageRequestUp.setId(new Long(insertPayMsgRes).longValue());
            modifyPayMessageRequestUp.setUpdatetime(new Date());
            modifyPayMessageRequestUp.setMermessageId(new Long(insertMerMessageRes).longValue());
            pcaServiceFacade.modifyPayMessage(modifyPayMessageRequestUp);

            String response = OkHttpUtils.postJson(notify_url,merPostJson);
//            String response = "SUCCESS";
            //更新通知商户流水
            ModifyPayOrderPayMerRequest merPayRequestUp = new ModifyPayOrderPayMerRequest();
            merPayRequestUp.setId(new Long(insertMerMessageRes).longValue());
            merPayRequestUp.setUpdatetime(new Date());
            merPayRequestUp.setNotifyResult(StringUtils.deleteWhitespace(ToolUtils.delHTMLTag(response)));
            int upMerMessageRes = pcaServiceFacade.modifyPayMerMessage(merPayRequestUp);

            //日志
            logger.info("支付回调--发送给商户: 请求地址：" + notify_url + "; 请求参数" + merPostParamMap);
            sbf_mer.append("\n\nTime:" + myfmt.format(date) + "支付回调--发送给商户");
            sbf_mer.append("\n请求地址：" + notify_url);
            sbf_mer.append("\n接口参数：" + merPostParamMap);
            if( response.equals("SUCCESS") )
            {
                sbf_mer.append("\n回调信息：" + "SUCCESS");
                LogsCustomUtils.writeIntoFile(sbf_mer.toString(),"", fileName_merNofity,true);

                //处理逻辑修改订单状态并返回输出success-----
                return "SUCCESS";
            }
            sbf_mer.append("\n回调信息：" + response);
            LogsCustomUtils.writeIntoFile(sbf_mer.toString(),"", fileName_merNofity,true);
            return "NOTICE";
        }catch (UnsupportedEncodingException e){
            logger.info("支付回调--发送给商户: 商户回调 notify_url decode失败! 商户原notify_url为：" + orderInfo.getNotifyUrl());
            sbf_mer.append("\n\nTime:" + myfmt.format(date) + "支付回调--发送给商户");
            sbf_mer.append("\n异常：用户notify_url 解码或发送异常: 待解码的notify_url为：" + orderInfo.getNotifyUrl());
            LogsCustomUtils.writeIntoFile(sbf.toString(),"", fileName_merNofity,true);
        }
        return "NOTICE";
    }
    /*
     * 获取未操作汇率的数据
     * */
    @RequestMapping(value = "/DuplicateRemoveByMoneyDetail", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public YjResponseDto DuplicateRemoveByMoneyDetail(OrderYinjiaApiRequest orderRequest){

        YjResponseDto yjResponseDto = new YjResponseDto();

        int allNum = 2000;//2000;
        int perNum = 500;//1500;
        double allPage = allNum>perNum?Math.ceil(allNum/perNum):1;
        int start = 0;
        int allPageToInt = (new Double(allPage)).intValue();
        OrderYinjiaApiResponse orderYinjiaApiResponse;

        int sucNum = 0;
        int failNum = 0;
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n订单汇率设置start");

        for(int i=1;i<=allPageToInt;i++){

            //int i =1;
            start = (i-1)*perNum;
            orderRequest.setPage(i);
            orderRequest.setRows(perNum);

            orderYinjiaApiResponse = orderYinjiaApiServiceFacade.getOrderYinjiaApiDuplicateRemove(orderRequest);

            List<ProductInfo> infoList = new ArrayList<>();
            OrderYinjiaApiInfo orderYinjiaApiInfo = new OrderYinjiaApiInfo();
            String stage="";
            String payType_cn = "";
            for(OrderYinjiaApiInfo lm:orderYinjiaApiResponse.getList()){

                //获取分期信息
                Map<String,String> ordernameJson = new HashMap<>();
                ordernameJson = JsonUtils.toObject(lm.getPayDetail(), Map.class);
                payType_cn = ordernameJson.get("stageType_cn");
                if ( !StringUtils.isBlank(ordernameJson.get("stageType_cn")) )
                {
                    Pattern pattern = Pattern.compile("^\\d");
                    Matcher matcher = pattern.matcher(ordernameJson.get("stageType_cn"));
                    if ( matcher.find() ){

                        stage = matcher.group(0);
                        if (Integer.parseInt(stage)<=9) {
                            stage = "0"+stage;
                        }
                    }else{
                        //支付分期信息不存在
                        failNum++;
                        System.out.println("订单:"+lm.getOrderid()+",支付分期信息不存在");
                        sbf.append("\n订单:"+lm.getOrderid()+",支付分期信息不存在");
                        continue;
                    }
                }else{
                    //支付分期信息不存在
                    failNum++;
                    System.out.println("订单:"+lm.getOrderid()+",支付分期信息不存在");
                    sbf.append("\n订单:"+lm.getOrderid()+",支付分期信息不存在");
                    continue;
                }
                //获取商户汇率信息
                String merRate = "";
                Map<String,String> paramSec = new HashMap<>();
                // 获取该商户银联信用卡分期支付的配置信息
                MerchantPayTypeInfo merchantPayTypeInfo  = merPayTypeServiceFacade.getOneMerPayTypeByTpidNull(lm.getMtsid(),7);
                if (merchantPayTypeInfo==null)
                {

                    //商户信用卡分期配置信息有误
                    failNum++;
                    System.out.println("订单:"+lm.getOrderid()+",商户信用卡分期配置信息有误pay_merchants_paytype");
                    sbf.append("\n订单:"+lm.getOrderid()+",商户信用卡分期配置信息有误pay_merchants_paytype");
                    continue;
                }else{

                    if(StringUtils.isNotBlank(merchantPayTypeInfo.getParamSec())){

                        paramSec = JsonUtils.toCollection(merchantPayTypeInfo.getParamSec(), new TypeReference<Map<String, String>>(){});
                        merRate = paramSec.get("merRate");
                    }else{
                        failNum++;
                        System.out.println("订单:"+lm.getOrderid()+",商户信用卡分期配置信息有误pay_merchants_paytype");
                        sbf.append("\n订单:"+lm.getOrderid()+",商户信用卡分期配置信息有误pay_merchants_paytype");
                        continue;
                    }
                }

                //获取分期费率
                VirtualInfo stageRateInfo = virtualInterfaceServiceFacade.getInfoByRelateId(new Long(ordernameJson.get("stageType")).intValue());
                if ( StringUtils.isBlank(stageRateInfo.getIntro() ) )
                {
                    //订单所属汇率未获取 pay_virtual
                    failNum++;
                    System.out.println("订单:"+lm.getOrderid()+",所属汇率未获取 pay_virtual");
                    sbf.append("\n订单:"+lm.getOrderid()+",所属汇率未获取 pay_virtual");
                    continue;
                }

                //创建费率信息
                OrdersMoneyInterfaceInfo moneyInterfaceInfo = new OrdersMoneyInterfaceInfo();
                moneyInterfaceInfo.setOrderid(Long.parseLong(lm.getOrderid()));
                moneyInterfaceInfo.setMoney(lm.getOrderPayPrice());//订单总金额
                moneyInterfaceInfo.setStageId(ordernameJson.get("stageType"));//分期id
                moneyInterfaceInfo.setStageName(payType_cn);//分期名称
                moneyInterfaceInfo.setStageRate(stageRateInfo.getIntro());//分期汇率pay_virtual表

                //分期费率的金额
                Double orderMoney = new Double(lm.getOrderPayPrice().toString());
                Double stageRate = new Double(stageRateInfo.getIntro());
                Double stageRateMoney = BigDecimalCalculateUtils.mul(orderMoney, stageRate);
                moneyInterfaceInfo.setStageMoney(new BigDecimal(new DecimalFormat("#.00").format(stageRateMoney)));

                moneyInterfaceInfo.setMtsid(lm.getMtsid());
                moneyInterfaceInfo.setMerchRate(merRate);

                //商户费率的金额
                Double merRateMoney = BigDecimalCalculateUtils.mul(new Double(lm.getOrderPayPrice().toString()), new Double(merRate));
                moneyInterfaceInfo.setMerchMoney(new BigDecimal(new DecimalFormat("#.00").format(merRateMoney)));
                moneyInterfaceInfo.setAddtime(new Date());
                ordersMoneyInterfaceServiceFacade.addRecord(moneyInterfaceInfo);
                sucNum++;
            }
        }
        sbf.append("\n订单汇率设置成功数："+sucNum);
        sbf.append("\n订单汇率设置失败数："+failNum);
        sbf.append("\n订单汇率设置end");
        String fileName = "ExchangeRateLog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"", fileName,true);

        return yjResponseDto;
    }
}
