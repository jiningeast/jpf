package com.joiest.jpf.yinjia.api.controller;

import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.BigDecimalCalculateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.SignUtils;
import com.joiest.jpf.dto.YinjiaCreateOrderRequest;
import com.joiest.jpf.dto.YinjiaTermsRequest;
import com.joiest.jpf.entity.MerchantInterfaceInfo;
import com.joiest.jpf.entity.MerchantPayTypeInfo;
import com.joiest.jpf.entity.OrderInfo;
import com.joiest.jpf.facade.MerPayTypeServiceFacade;
import com.joiest.jpf.facade.MerchantInterfaceServiceFacade;
import com.joiest.jpf.facade.OrderServiceFacade;
import com.joiest.jpf.yinjia.api.constant.ManageConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.joiest.jpf.yinjia.api.constant.ManageConstants.AES_KEY;

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

        // 判断用户是否有资格使用传过来的分期数
        /*int catid = 0;
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
        }*/

        // 生成订单
        String orderid = createOrderid();
        /*Map<String, Object> ordernameMap = new HashMap<>();
        ordernameMap.put("payType",7);
        ordernameMap.put("stageType", request.getTerm());
        ordernameMap.put("payType_cn","银联信用卡分期支付");
        ordernameMap.put("stageType_cn",request.getTerm()+"期");
        String ordernameJson = JsonUtils.toJson(ordernameMap);*/

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
//        orderInfo.setOrdername(ordernameJson);
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

    @ModelAttribute
    @ResponseBody
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

        // 商户号&商户信息
        if ( StringUtils.isNotBlank(request.getParameter("mid")) ){
            merchInfo = merchantInterfaceServiceFacade.getMerchant(Long.parseLong(request.getParameter("mid")));
        }else{
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());
        }

        String serverName = httpServletRequest.getServerName();
        if ( serverName.contains("/yinjiastage/createOrder") ){
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

}
