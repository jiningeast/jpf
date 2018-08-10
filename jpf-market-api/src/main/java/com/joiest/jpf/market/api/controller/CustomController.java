package com.joiest.jpf.market.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import com.joiest.jpf.market.api.util.RedisUtils;
import com.joiest.jpf.market.api.util.SmsUtils;
import com.joiest.jpf.market.api.util.ToolsUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("custom")
public class CustomController {

    private  String smsPrefix = "marketBindSend";
    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    /*
     * 绑定用户手机号
     * */
    @RequestMapping(value = "/bind", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String bind(String data){
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,String> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, String>>(){});

        //判断请求参数是否合法
        if(!requestMap.containsKey("mobile")){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请求参数错误", null);
        }

        String mobile = requestMap.get("mobile");
        String code = requestMap.get("code");//短信码
        String openId = requestMap.get("openId");//用户标识

        //openId判断是否真实？？？？？？？？？？？？

        if(StringUtils.isBlank(openId)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "openId参数错误", null);
        }

        String smsCode = redisCustomServiceFacade.get(smsPrefix+mobile); //redis存储短信码
        if( !code.equals(smsCode) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "短信验证码错误", null);
        }


        ShopCustomerInterfaceInfo customerRecrd =  shopCustomerInterfaceServiceFacade.getCustomerByOpenId(mobile);
        if( customerRecrd != null  ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "手机号已存在", null);
        }

        ShopCustomerInterfaceInfo infos =  shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId);
        if( infos == null  ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未匹配到openId", null);
        }
        //绑定用户手机号和当前openId
        PayShopCustomer record = new PayShopCustomer();
        record.setPhone(mobile);
        record.setUpdatetime(new Date());
        JpfResponseDto responseDto = shopCustomerInterfaceServiceFacade.updateCustomerByOpenId(record,openId);
        if(responseDto.getRetCode().equals("0000")){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "更新成功", null);
        }else{
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "更新失败", null);
        }

    }

    /*
     * 发送手机号
     * */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendSms(String data){
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,String> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, String>>(){});

        //判断请求参数是否合法
        if(!requestMap.containsKey("mobile")){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请求参数错误", null);
        }

        String mobile = requestMap.get("mobile");

        //手机号格式验证
        if(!ToolsUtils.verifyPhone(mobile)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "手机号格式错误", null);
        }
        Integer verifyCode = ToolsUtils.getRandomInt(100000,999999); //6位数字

        String content = "尊敬的用户，您此次线上的手机验证码是："+verifyCode+"，10分钟内有效。";
        //存储用户手机号短信验证码
        redisCustomServiceFacade.set(smsPrefix+mobile,verifyCode.toString(),Long.parseLong("600"));

        String logFileName = "customBindMobile";

        Map<String,String> responseSmsMap = SmsUtils.send(mobile,content,logFileName);

        if(responseSmsMap == null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "短信通道异常，请稍后重试", null);
        }
        String response = responseSmsMap.get("response");
        //解析返回参数
        JSONObject responseMap = JSONObject.fromObject(response);
        if( responseMap.isEmpty() || responseMap == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付接口异常");
        }

        String code = responseMap.get("code").toString(); //接口返回状态码
        if(code.equals("10008")){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "短信签名失败", null);
        }
        if(!code.equals("10000")){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "短信发送失败，请稍后重试", null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "短信发送成功", null);


    }

}
