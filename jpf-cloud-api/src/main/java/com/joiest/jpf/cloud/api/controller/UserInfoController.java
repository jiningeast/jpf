package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.IdentAuth;
import com.joiest.jpf.cloud.api.util.MwSmsUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.dto.GetUserBlanceRequest;
import com.joiest.jpf.entity.CloudCompanyStaffInfo;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.entity.CloudIdcardInfo;
import com.joiest.jpf.entity.CloudStaffBanksInfo;
import com.joiest.jpf.facade.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    @Autowired
    private CloudCompanyStaffServiceFacade cloudCompanyStaffServiceFacade;

    @Autowired
    private CloudIdcardServiceFacade cloudIdcardServiceFacade;

    @Autowired
    private CloudStaffBanksServiceFacade cloudStaffBanksServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ToolCateServiceFacade toolCateServiceFacade;

    private String uid;

    private CloudCompanyStaffInfo userInfo;
    //登录
    @RequestMapping("/login")
    @ResponseBody
    public String userlogin()
    {
        return "";
    }

    //登出
    @RequestMapping("/logout")
    @ResponseBody
    public String userlogout()
    {
        return "";
    }

    //月份
    @RequestMapping(value = "/userblance", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserCurMonthMoney(GetUserBlanceRequest request)
    {
        ValidatorUtils.validateInterface(request);

        Map<String,String> loginResultMap = userIsLogin(request.getToken());

        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }

        String[] date;
        int year,month;
        if (StringUtils.isNotBlank(request.getData()))
        {
            date = request.getData().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
        } else
        {
            Calendar calstar= Calendar.getInstance();
            year = calstar.get(Calendar.YEAR);
            month = calstar.get(Calendar.MONTH) + 1;
        }

        Map<String,String> map =  ToolUtils.getMonthStartAndEnd(year,month);

        //获取指定月份的信息
        long pageNo = 1;
        long pageSize = 15;
        int flag = 1;   //当前月份
        if ( StringUtils.isNotBlank(request.getData()) )
        {
            //指定月份
            flag = 2;
        }
        GetCloudMoneyDfResponse response = cloudDfMoneyServiceFacade.getDfMoneyList(map.get("start"),map.get("end"), userInfo.getId().toString(),pageNo, pageSize, flag);
        if ( response == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "暂无数据", null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), response.getMonthTotal().toString(), response);
    }

    //判断用户是否登录
    private Map<String,String> userIsLogin(String token)
    {

        Map<String,String> resultMap = new HashMap<>();
        String uid_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("CLOUD_USER_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(uid_encrypt)) {
            uid = AESUtils.decrypt(uid_encrypt, ConfigUtil.getValue("AES_KEY"));
            String reg_mid = "^\\d{1,10}$";
            Boolean uidIsTrue = Pattern.compile(reg_mid).matcher(uid).matches();
            if ( !uidIsTrue )
            {
                resultMap.put("0",JpfInterfaceErrorInfo.USERINFO_VALID_FAIL.getCode());
                resultMap.put("1",JpfInterfaceErrorInfo.USERINFO_VALID_FAIL.getDesc());
                return resultMap;
            }
            userInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffById(uid);
            if (userInfo == null) {
                resultMap.put("0",JpfInterfaceErrorInfo.USERINFO_NOT_EXIST.getCode());
                resultMap.put("1",JpfInterfaceErrorInfo.USERINFO_NOT_EXIST.getDesc());
                return resultMap;
            }
            resultMap.put("0",JpfInterfaceErrorInfo.SUCCESS.getCode());
            resultMap.put("1",JpfInterfaceErrorInfo.SUCCESS.getDesc());
            return resultMap;
        } else {
            resultMap.put("0",JpfInterfaceErrorInfo.NOTlOGIN.getCode());
            resultMap.put("1",JpfInterfaceErrorInfo.NOTlOGIN.getDesc());
            return resultMap;
        }

    }

    /**
     * 获取用户总金额
     */
    @RequestMapping(value = "/userdf", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserTotalMoney(String token)
    {
        if ( StringUtils.isBlank(token) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "token不能为空", null);
        }

        Map<String,String> loginResultMap = userIsLogin(token);
        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }

        Double totalMoney = cloudDfMoneyServiceFacade.getUserDfTotalMoney(uid);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), totalMoney);
    }

    @RequestMapping(value = "/usermonth", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserMonthMoney(String token)
    {
        if ( StringUtils.isBlank(token) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "token不能为空", null);
        }

        Map<String,String> loginResultMap = userIsLogin(token);
        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }

        List<CloudDfMoneyInterfaceInfo> list = cloudDfMoneyServiceFacade.getUserMonthList(Long.parseLong(uid));

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), list);
    }

    /**
     * 用户登录
     * */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String userLogin(HttpServletRequest request) {

        String idCard = request.getParameter("idCard");
        String verificate = request.getParameter("verificate");
        if(idCard == null || idCard.isEmpty() || verificate==null || verificate.isEmpty()){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error",null);
        }
        idCard = Base64CustomUtils.base64Decoder(idCard);
        verificate = Base64CustomUtils.base64Decoder(verificate);

        //获取员工信息
        CloudCompanyStaffInfo cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffByIdcard(idCard);
        if(cloudCompanyStaffInfo==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到对应员工账号信息",null);
        }
        String check= redisCustomServiceFacade.get(ConfigUtil.getValue("CLOUD_USER_SENDSMS") + cloudCompanyStaffInfo.getMobile() );
        if(verificate.equals(check)){

            int random = toolCateServiceFacade.getRandomInt(10000,99999);
            String token = AESUtils.encrypt(cloudCompanyStaffInfo.getId().toString() + random,ConfigUtil.getValue("AES_KEY"));
            String mid_encrypt = AESUtils.encrypt(cloudCompanyStaffInfo.getId().toString(), ConfigUtil.getValue("AES_KEY"));

            redisCustomServiceFacade.set(ConfigUtil.getValue("CLOUD_USER_LOGIN_KEY") + token, mid_encrypt, Long.parseLong(ConfigUtil.getValue("CLOUD_USER_LOGIN_EXPIRE")) );


            Map<String,String> tok = new HashMap<String, String>();
            tok.put("token",token);

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "登录成功", tok);
        }else{

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "验证码错误",null);
        }
    }
    /*
     * 发送登录短信
     * */
    @RequestMapping(value = "/sendLoginSms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendLoginSms(HttpServletRequest request) throws IOException {

        String idCard = request.getParameter("idCard");
        idCard = Base64CustomUtils.base64Decoder(idCard);

        //获取员工信息
        CloudCompanyStaffInfo cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffByIdcard(idCard);
        if(cloudCompanyStaffInfo == null || idCard ==null || idCard.isEmpty()){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到对应员工账号信息",null);
        }
        try{

            String  phone = cloudCompanyStaffInfo.getMobile();
            int verificateCode = toolCateServiceFacade.getRandomInt(100000,999999);//短信内容
            redisCustomServiceFacade.set(ConfigUtil.getValue("CLOUD_USER_SENDSMS") + phone, new Integer(verificateCode).toString(),Long.parseLong(ConfigUtil.getValue("CLOUD_USER_SENDSMS_EXPIRE")) );
            String content = null;
            content = "尊敬的用户，您此次登录的手机验证码是："+verificateCode+",十分钟内有效";

            //int result=toolCateServiceFacade.sendSms(phone, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。
            int result = new MwSmsUtils().sendSms(phone, content);//toolCateServiceFacade.sendSms(mobile, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。
            if(result==0){//返回值为0，代表成功

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "短信发送成功",null);
            }else{//返回值为非0，代表失败

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "短信发送失败",null);
            }
        }catch (Exception e) {

            e.printStackTrace();//异常处理
        }
        return null;
    }
    /**
     * 获取用户认证信息
     */
    @RequestMapping(value = "/userAuthInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String userAuthInfo(HttpServletRequest request) throws IOException {

        String token = request.getParameter("token");
        String getId = redisCustomServiceFacade.get(ConfigUtil.getValue("CLOUD_USER_LOGIN_KEY") + token);
        String id = AESUtils.decrypt(getId, ConfigUtil.getValue("AES_KEY"));

        if(getId.equals(null) || id.equals(null)){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error",null);
        }

        //获取员工信息
        CloudCompanyStaffInfo cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffById(id);
        if(cloudCompanyStaffInfo == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "员工信息不存在",null);
        }
        //获取员工银行卡信息
        CloudStaffBanksInfo cloudStaffBanksInfo = cloudStaffBanksServiceFacade.getStaffBankBySidPhone(id, cloudCompanyStaffInfo.getMobile());
        if(cloudStaffBanksInfo==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "员工对应银行卡信息不存在",null);
        }
        //身份证信息
        CloudIdcardInfo cloudIdcardInfo=cloudIdcardServiceFacade.getCloudIdcardById(String.valueOf(cloudCompanyStaffInfo.getUcardid()));
        Map<String,String> uinfo = new HashMap<String, String>();
        if(cloudIdcardInfo == null){

            uinfo.put("faceCardLocal",null);
            uinfo.put("faceCardServer",null);

            uinfo.put("backCardLocal",null);
            uinfo.put("backCardServer",null);
            //return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "身份证信息不存在",null);
        }else{

            uinfo.put("faceCardLocal",cloudIdcardInfo.getFaceimglocal());
            uinfo.put("faceCardServer",cloudIdcardInfo.getFaceimgserver());

            uinfo.put("backCardLocal",cloudIdcardInfo.getBackimglocal());
            uinfo.put("backCardServer",cloudIdcardInfo.getBackimgserver());
        }
        uinfo.put("name",cloudCompanyStaffInfo.getNickname());
        uinfo.put("idCard",cloudCompanyStaffInfo.getIdcard());
        uinfo.put("userAuth",cloudCompanyStaffInfo.getIsActive().toString());
        uinfo.put("banknum",cloudStaffBanksInfo.getBankno());
        uinfo.put("bankphone",cloudStaffBanksInfo.getBankphone());
        uinfo.put("bankpAuth",cloudStaffBanksInfo.getBankActive());



        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "获取成功", uinfo);
    }

    /**
     * 身份证OCR识别
     * */
    @RequestMapping(value = "/idCardOcr", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idCardOcr(HttpServletRequest request) throws IOException {

        String side = request.getParameter("side");
        String imgInfo = request.getParameter("img");
        String token = request.getParameter("token");

        if(side == null || imgInfo==null || token==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error",null);
        }
        Map<String,String> loginResultMap = userIsLogin(token);
        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }
        JSONObject ocrRes = new IdentAuth().idCardOcr(request,side,imgInfo);
        String baseRe = Base64CustomUtils.base64Encoder(ocrRes.toString());
        baseRe = baseRe.replaceAll("\r\n","");

        return baseRe;
    }

    /**
     * 认证短信发送
     * */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendSms(HttpServletRequest request) throws IOException {

        String phone = request.getParameter("mobile");
        String bankno = request.getParameter("bankno");
        String token = request.getParameter("token");

        phone = Base64CustomUtils.base64Decoder(phone);
        bankno = Base64CustomUtils.base64Decoder(bankno);

        Map<String,String> loginResultMap = userIsLogin(token);
        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()))
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }
        //检验银行卡信息银行卡信息
        CloudStaffBanksInfo cloudStaffBanksInfo = cloudStaffBanksServiceFacade.getStaffBankByNumSid(bankno, new BigInteger(uid));
        if(cloudStaffBanksInfo==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请输入正确的银行卡信息", null);
        }
        if(!cloudStaffBanksInfo.getBankphone().equals(phone)){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请确认手机号和银行卡绑定手机号一致", null);
        }
        try{
            int verificateCode = toolCateServiceFacade.getRandomInt(100000,999999);//短信内容

            redisCustomServiceFacade.set(ConfigUtil.getValue("CLOUD_USER_AUTH")+phone,new Integer(verificateCode).toString(),Long.parseLong(ConfigUtil.getValue("CLOUD_USER_AUTH_EXPIRE")));
            String content = null;
            content = "尊敬的用户，您此次的手机验证码是："+verificateCode+",十分钟内有效";

            //int result = toolCateServiceFacade.sendSms(phone, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。
            int result = new MwSmsUtils().sendSms(phone, content);//toolCateServiceFacade.sendSms(mobile, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。
            if(result==0){//返回值为0，代表成功

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "短信发送成功",null);
            }else{//返回值为非0，代表失败

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "短信发送失败",null);
            }
        }catch (Exception e) {

            e.printStackTrace();//异常处理
        }
        return null;
    }
    /**
     * 验证用户信息  身份证、银行卡、手机号信息
     * */
    @RequestMapping(value = "/authUserInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String authUserInfo(HttpServletRequest request){

        String cardId = request.getParameter("id");//身份证接口返回id
        String mobile = request.getParameter("mobile");//手机号
        String bankNum = request.getParameter("bankNum");//银行卡号
        String verificate = request.getParameter("verificate");//验证码
        String token = request.getParameter("token");//token

        cardId = Base64CustomUtils.base64Decoder(cardId);
        mobile = Base64CustomUtils.base64Decoder(mobile);
        bankNum = Base64CustomUtils.base64Decoder(bankNum);
        verificate = Base64CustomUtils.base64Decoder(verificate);

        Map<String,String> loginResultMap = userIsLogin(token);
        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()))
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }
        CloudCompanyStaffInfo cloudCompanyStaffInfo;
        CloudIdcardInfo cloudIdcardInfo;
        CloudStaffBanksInfo cloudStaffBanksInfo;
        //短信验证
        String verificateRedis = redisCustomServiceFacade.get(ConfigUtil.getValue("CLOUD_USER_AUTH")+mobile);
        if(verificateRedis == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "验证码失效，请重新发送", null);
        }
        if(!verificateRedis.equals(verificate)){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "验证码错误", null);
        }
        if(cardId==null || mobile==null ||  bankNum==null || verificate==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), JpfInterfaceErrorInfo.FAIL.getDesc(), null);
        }else{

            //身份证信息
            cloudIdcardInfo=cloudIdcardServiceFacade.getCloudIdcardById(cardId);

            //员工信息
            cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffByIdcard(cloudIdcardInfo.getNum());
            if(cloudCompanyStaffInfo==null){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "当前认证用户员工信息不存在", null);
            }
            //员工银行卡信息
            cloudStaffBanksInfo = cloudStaffBanksServiceFacade.getStaffBankByNumSid(bankNum, new BigInteger(cloudCompanyStaffInfo.getId()));
            if(cloudIdcardInfo == null){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "当前认证用户银行卡信息不存在", null);
            }
            String test = cloudStaffBanksInfo.getBankphone();
            if(cloudStaffBanksInfo.getBankphone().equals(mobile)){

                //操作员工状态
                Map<String,String> map = new HashMap<String,String>();

                map.put("is_active","1");
                map.put("code",verificate);

                int res = cloudCompanyStaffServiceFacade.upCloudCompanyStaffByIdcard(cloudCompanyStaffInfo.getIdcard(),map);
                if(res>0){

                    return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "用户认证成功", null);
                }else{
                    return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "用户认证失败，请重试", null);
                }
            }else{

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "银行卡绑定手机号有误", null);
            }
        }
    }
}

