package com.joiest.jpf.market.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.GetShopStockCardResponse;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.entity.WeixinUserInfo;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopStockCardServiceFacade;
import com.joiest.jpf.facade.WeixinUserServiceFacade;
import com.joiest.jpf.market.api.util.SmsUtils;
import com.joiest.jpf.market.api.util.ToolsUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("custom")
public class CustomController {

    private String uid;

    private String openId;

    private ShopCustomerInterfaceInfo userInfo;

    private  String smsPrefix = "marketBindSend";

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private WeixinUserServiceFacade weixinUserServiceFacade;

    @Autowired
    private ShopStockCardServiceFacade shopStockCardServiceFacade;

    /*
     * 绑定用户手机号
     * */
    @RequestMapping(value = "/bind", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public String bind(String data){
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,String> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, String>>(){});

        //判断请求参数是否合法
        String mobile = requestMap.get("mobile");
        String code = requestMap.get("code");//短信码
        String Token = requestMap.get("Token");
        if(StringUtils.isBlank(mobile) || StringUtils.isBlank(code)|| StringUtils.isBlank(Token)  ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请求参数错误", null);
        }

        /*Token = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY")+Token);
        if(StringUtils.isBlank(Token) || Token == null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Token参数错误", null);
        }

        //redis取openId
        String openId =  AESUtils.decrypt(Token, ConfigUtil.getValue("AES_KEY"));//redis存储短信码

        if(StringUtils.isBlank(openId)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "openId参数错误", null);
        }*/

        String smsCode = redisCustomServiceFacade.get(smsPrefix+mobile); //redis存储短信码
        if( !code.equals(smsCode) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "短信验证码错误", null);
        }


        List<ShopCustomerInterfaceInfo> customerRecrd =  shopCustomerInterfaceServiceFacade.getCustomerByPhone(mobile);
        if( customerRecrd != null  ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "手机号已存在", null);
        }

        List<ShopCustomerInterfaceInfo> infos =  shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId);
        if( infos != null  ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "openId已存在", null);
        }

        //1、根据opneId来查询微信用户信息
        WeixinUserInfo weixinUserInfo= weixinUserServiceFacade.getWeixinUserByOpenid(openId);
        if( weixinUserInfo== null){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未查询到数据", null);
        }

        //2、插入pay_shop_customer 用户信息
        Date date = new Date();
        BigDecimal dou = new BigDecimal("0.00"); //豆数量

        PayShopCustomer record = new PayShopCustomer();
        record.setName("");
        record.setCompanyId(Long.parseLong("0"));
        record.setCompanyName("");
        record.setOpenid(weixinUserInfo.getOpenid().toString());
        record.setMpid(weixinUserInfo.getMpid().toString());
        record.setNickname(weixinUserInfo.getNicknameencode());
        record.setIsVerify((byte)0); // 默认未实名认证
        record.setStatus((byte)1);
        record.setPhone(mobile);
        record.setDou(dou);
        record.setCode("");
        record.setDou(new BigDecimal("0.00"));
        record.setAddtime(date);
        record.setUpdatetime(date);
        String shopCustomId = shopCustomerInterfaceServiceFacade.addCustomer(record);
        if( shopCustomId !=null && StringUtils.isNotBlank(shopCustomId)){
            PayShopCustomer retInfo = new PayShopCustomer();
            String uid = shopCustomId;
            String customCode = ToolUtils.CreateCode(dou.toString(),uid);
            retInfo.setCode(customCode);
            JpfResponseDto responseDto =shopCustomerInterfaceServiceFacade.updateCustomerByOpenId(retInfo,weixinUserInfo.getOpenid());
            if(responseDto.getRetCode().equals("0000")){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "更新成功", null);
            }else{
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "更新失败", null);
            }
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

        Map<String,String> responseSmsMap = SmsUtils.send(mobile,content,logFileName,"xinxiang");

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

    /**
     * 首页返回用户身份识别：普通用户 特殊用户
     */
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        // 构建返回+
        Map<String, Object> responseDataMap = new HashMap<>();
        responseDataMap.put("type", userInfo.getType());
        String responseDataJson = JsonUtils.toJson(responseDataMap);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"获取用户身份成功",responseDataJson);
    }

    /*
     * 用户卡密的Email附件的发送
     * */
    @RequestMapping(value = "/sendCardEmail", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendCardEmail(HttpServletResponse Httpresponse,String data) throws Exception {

        String email= null;
        try {
            String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
            String requestStr = Base64CustomUtils.base64Decoder(dataStr);
            Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
            email = (String) requestMap.get("email");

            if(email==null){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
            }
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }
        //判断请求参数是否合法
        String emailpattern="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        boolean isemail = Pattern.matches(emailpattern, email);

        if(isemail==false){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "邮箱格式不正确", null);
        }
        //根据用户id查出当前用户的卡密
        String cutomId=userInfo.getId();
        if( StringUtils.isBlank(cutomId)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "用户未登录", null);
        }
        GetShopStockCardResponse response=shopStockCardServiceFacade.getCardM(cutomId);
        if(response.getList().size()==0){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "您还未有购买的卡密", null);
        }
       //如果有购买的卡密放到Excel
//        设置excel标题以及字段
        JSONArray res = new JSONArray();
        res.add("卡号");
        res.add("密码");
        res.add("购买时间");

        JSONArray filed = new JSONArray();
        filed.add("cardNo");//卡号
        filed.add("cardPass");
        filed.add("paytime");
        JSONObject excel;
        String filepath="";
        String filename="";

        try {
            excel = new ExcelDealUtils().exportExcelByInfo(Httpresponse,res.toString(),filed.toString(),response.getList(),2,ConfigUtil.getValue("EXCEL_PATH"));
            filepath = excel.getJSONObject("data").get("localUrl").toString();
            filename = excel.getJSONObject("data").get("fileName").toString();

        } catch (Exception e) {

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "文件信息有误", null);
        }
        if(StringUtils.isBlank(filepath)|| StringUtils.isBlank(filename)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "文件信息有误", null);
        }
       // 发送邮件
        String subject = "欣豆市场卡密";
        String sendName = "欣享服务";
        String recipients = email;//接收邮箱地址
        String recipientsName = URLDecoder.decode(userInfo.getNickname(), "UTF-8");
        ;//接收人姓名
        String html = "<p>尊敬的客户，您好：</p>" +
                "<p style='text-index:2em;'>附件打包文件是您的卡密。</p>" +
                "<p style='text-index:2em; color:red;'>【请您知晓，您应妥善保管卡密信息。因泄露信息导致的损失需由您自行承担】</p>" +
                "<p style='text-align:left;'>此文件为系统自动发送，无需回复。</p>" +
                "<p style='text-align:left;'>欣享爱生活</p>";
        Boolean sendStatus =SendMailUtil.sendMultipleEmail(subject,sendName,recipients,recipientsName,filepath,filename,html);

        if ( !sendStatus ){
            // 邮件发送失败
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "发送失败", null);
        }else{
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "邮件发送成功", null);
        }

    }
    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {
        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            List<ShopCustomerInterfaceInfo> info = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId);
            if( info != null && !info.isEmpty()  ){
                userInfo = info.get(0);
                uid = userInfo.getId();
            }

        }
    }

}
