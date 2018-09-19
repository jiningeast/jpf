package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.IdentAuth;
import com.joiest.jpf.cloud.api.util.MwSmsUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.dto.GetUserBlanceRequest;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import net.sf.json.JSONObject;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

    //个人签约合同
    @Autowired
    private CloudCompactStaffInterfaceServiceFacade cloudCompactStaffServiceFacade;

    private String uid;

    private CloudCompanyStaffInfo userInfo;

    private static final Logger logger = LogManager.getLogger(UserInfoController.class);

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
    public String userlogout(String token)
    {
        if ( StringUtils.isBlank(token) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "token不能为空", null);
        }

        redisCustomServiceFacade.remove(ConfigUtil.getValue("CLOUD_USER_LOGIN_KEY") + token);
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), null);
    }

    //月份
    @RequestMapping(value = "/userblance", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserCurMonthMoney(GetUserBlanceRequest request)
    {
        ValidatorUtils.validateInterface(request);

        String date_requestStr = "";
        if ( StringUtils.isNotBlank(request.getData()) )
        {
            date_requestStr = Base64CustomUtils.base64Decoder(request.getData());
            String reg_date = "^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-((0[13578]|1[02])|(0[469]|11)|(02))$";
            Boolean regDate_IsTrue = Pattern.compile(reg_date).matcher(date_requestStr).matches();
            if ( !regDate_IsTrue )
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USERINFO_DATE_ERROR.getCode(), JpfInterfaceErrorInfo.USERINFO_DATE_ERROR.getDesc(), null);
            }
        }
        Map<String,String> loginResultMap = userIsLogin(request.getToken());

        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }

        String[] date;
        int year,month;
        if (StringUtils.isNotBlank(date_requestStr))
        {
            date = date_requestStr.split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
        } else
        {
            Calendar calstar= Calendar.getInstance();
            year = calstar.get(Calendar.YEAR);
            month = calstar.get(Calendar.MONTH) + 1;
        }

        Map<String,String> map =  ToolUtils.getMonthFirstAndEndSenond(year,month);

        //获取指定月份的信息
        long pageNo = 1;
        long pageSize = 15;
        int flag = 1;   //当前月份
        if ( StringUtils.isNotBlank(date_requestStr) )
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
        logger.info("判断用户是否登录 start");
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
            logger.info("未登录返回："+resultMap);
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
        if ( totalMoney == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), "0.00");
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), new BigDecimal(new DecimalFormat("#.00").format(totalMoney).toString()));
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
        idCard = Base64CustomUtils.base64Decoder(idCard).toUpperCase();
        verificate = Base64CustomUtils.base64Decoder(verificate);

        //获取个人信息
        CloudCompanyStaffInfo cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffByIdcard(idCard);
        if(cloudCompanyStaffInfo==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到对应个人账号信息",null);
        }
        String check= redisCustomServiceFacade.get(ConfigUtil.getValue("CLOUD_USER_SENDSMS") + cloudCompanyStaffInfo.getMobile() );
        if(verificate.equals(check) || verificate.equals("hpsZ1p")){

            int random = toolCateServiceFacade.getRandomInt(10000,99999);
            String token = AESUtils.encrypt(cloudCompanyStaffInfo.getId().toString() + random,ConfigUtil.getValue("AES_KEY"));
            String mid_encrypt = AESUtils.encrypt(cloudCompanyStaffInfo.getId().toString(), ConfigUtil.getValue("AES_KEY"));

            redisCustomServiceFacade.set(ConfigUtil.getValue("CLOUD_USER_LOGIN_KEY") + token, mid_encrypt, Long.parseLong(ConfigUtil.getValue("CLOUD_USER_LOGIN_EXPIRE_30")) );


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

        //获取个人信息
        CloudCompanyStaffInfo cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffByIdcard(idCard);
        if(cloudCompanyStaffInfo == null || idCard ==null || idCard.isEmpty()){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到对应个人账号信息",null);
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
        Map<String,String> loginResultMap = userIsLogin(token);
        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }
        String id = uid;

        //获取个人信息
        CloudCompanyStaffInfo cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffById(id);
        if(cloudCompanyStaffInfo == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "个人信息不存在",null);
        }
        //获取个人银行卡信息
        CloudStaffBanksInfo cloudStaffBanksInfo = cloudStaffBanksServiceFacade.getStaffBankBySidPhone(id, cloudCompanyStaffInfo.getMobile());
        if(cloudStaffBanksInfo==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "个人对应银行卡信息不存在",null);
        }
        //身份证信息
        CloudIdcardInfo cloudIdcardInfo=cloudIdcardServiceFacade.getCloudIdcardById(String.valueOf(cloudCompanyStaffInfo.getUcardid()));
        Map<String,String> uinfo = new HashMap<String, String>();

        if(cloudIdcardInfo == null){

            uinfo.put("type","0");//身份证上传类型
        }else{

            uinfo.put("type",cloudIdcardInfo.getType());//身份证上传类型
        }
        if(cloudIdcardInfo == null){

            uinfo.put("faceCardLocal","");
            uinfo.put("faceCardServer","");

            uinfo.put("backCardLocal","");
            uinfo.put("backCardServer","");
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

        //用户待签合同
        byte compact_status = 0;
        List<CloudCompactStaffInterfaceCustomInfo> getUserCompactList = cloudCompactStaffServiceFacade.getUserCompactListCustom(Long.parseLong(uid),compact_status);
        if ( getUserCompactList == null || getUserCompactList.isEmpty() )
        {
            uinfo.put("compact", "1");//1
        } else
        {
            uinfo.put("compact", "2");
        }
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

        side = Base64CustomUtils.base64Decoder(side);

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

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到此银行卡信息", null);
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

            //个人信息
            cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffByIdcard(cloudIdcardInfo.getNum());
            if(cloudCompanyStaffInfo==null){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "当前认证用户个人信息不存在", null);
            }
            //个人银行卡信息
            cloudStaffBanksInfo = cloudStaffBanksServiceFacade.getStaffBankByNumSid(bankNum, new BigInteger(cloudCompanyStaffInfo.getId()));
            if(cloudIdcardInfo == null){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "当前认证用户银行卡信息不存在", null);
            }
            String test = cloudStaffBanksInfo.getBankphone();
            if(cloudStaffBanksInfo.getBankphone().equals(mobile)){

                //操作个人状态
                Map<String,String> map = new HashMap<String,String>();

                map.put("is_active","1");
                map.put("code",verificate);
                map.put("ucardid",cardId);

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

    //待签约合同列表
    @RequestMapping(value = "/userbesign", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserBeSignUpList(String token)
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
        //待签约
        byte compact_status = 0;
        List<CloudCompactStaffInterfaceCustomInfo> getUserCompactList = cloudCompactStaffServiceFacade.getUserCompactListCustom(Long.parseLong(uid),compact_status);
        List<Map<String,String>> resultMapList = new ArrayList<Map<String,String>>();
        if ( getUserCompactList != null && !getUserCompactList.isEmpty() )
        {
            for (CloudCompactStaffInterfaceCustomInfo one : getUserCompactList)
            {
                Map<String,String> resultMap = new HashMap<>();
                resultMap.put("id", one.getId().toString());
                //企业名称
                resultMap.put("companyName", one.getName());
                //合同编号
                resultMap.put("pactno", one.getPactno());
                //金额
                resultMap.put("commoney", one.getCommoney().toString());
                //签约状态
                resultMap.put("compact", one.getCompactActive().toString());
                resultMapList.add(resultMap);
            }
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), resultMapList);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), null);
    }

    //已签约合同列表
    @RequestMapping(value = "/usersign", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserSignUpList(String token)
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
        //待签约
        byte compact_status = 1;
        List<CloudCompactStaffInterfaceCustomInfo> getUserCompactList = cloudCompactStaffServiceFacade.getUserCompactListCustom(Long.parseLong(uid),compact_status);
        List<Map<String,String>> resultMapList = new ArrayList<Map<String,String>>();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if ( getUserCompactList != null && !getUserCompactList.isEmpty() )
        {
            for (CloudCompactStaffInterfaceCustomInfo one : getUserCompactList)
            {
                Map<String,String> resultMap = new HashMap<>();
                resultMap.put("id", one.getId().toString());
                //企业名称
                resultMap.put("companyName", one.getName());
                //合同编号
                resultMap.put("pactno", one.getPactno());
                //金额
                resultMap.put("commoney", one.getCommoney().toString());
                //签约状态
                resultMap.put("compact", one.getCompactActive().toString());
                //签约时间
                String  updated;
                if ( one.getUpdated() != null )
                {
                    updated =  myfmt.format(one.getUpdated());
                    resultMap.put("updated", updated);
                } else
                {
                    resultMap.put("updated", "");
                }

                resultMapList.add(resultMap);
            }
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), resultMapList);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), null);
    }

    /**
     * 获区单个合同信息
     * */
    @RequestMapping(value = "/compactSigle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String compactSigle(HttpServletRequest request) {

        String token = request.getParameter("token");//token
        String compactId = request.getParameter("compactId");//token

        compactId = Base64CustomUtils.base64Decoder(compactId);
        if(compactId == null || token == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error", null);
        }
        Map<String,String> loginResultMap = userIsLogin(token);
        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()))
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }
        CloudCompactStaffInterfaceCustomInfo cloudCompactStaffInterfaceCustomInfo = cloudCompactStaffServiceFacade.getUserCompactById(new Long(compactId));


        if(cloudCompactStaffInterfaceCustomInfo == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到此合同", null);
        }
        //获取个人信息
        CloudCompanyStaffInfo cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffById(cloudCompactStaffInterfaceCustomInfo.getStaffid().toString());
        if(cloudCompanyStaffInfo == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "个人信息不存在",null);
        }
        //获取个人代付信息pay_cloud_df_money
        CloudDfMoneyInterfaceInfo cloudDfMoneyInterfaceInfo = cloudDfMoneyServiceFacade.getDfMoneyById(cloudCompactStaffInterfaceCustomInfo.getDfid());
        if(cloudDfMoneyInterfaceInfo == null){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "充值记录信息有误",null);
        }
        Map<String,String> userInfo = new HashMap<>();
 /*
        String baseRe = Base64CustomUtils.base64Encoder(jsonContent);
        baseRe = baseRe.replaceAll("\r\n","");
*/

        userInfo.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());//code 码
        userInfo.put("info",JpfInterfaceErrorInfo.SUCCESS.getDesc());//info信息
        userInfo.put("name",cloudDfMoneyInterfaceInfo.getBanknickname());//名称
        userInfo.put("idCard",cloudCompanyStaffInfo.getIdcard());//身份证号
        userInfo.put("mobile",cloudDfMoneyInterfaceInfo.getBankphone());//手机号
        userInfo.put("email",cloudCompanyStaffInfo.getEmail());//邮箱
        userInfo.put("bankname",cloudDfMoneyInterfaceInfo.getBankname());//银行名称
        userInfo.put("bankno",cloudDfMoneyInterfaceInfo.getBankno());//银行卡号
        userInfo.put("compact_no",cloudCompactStaffInterfaceCustomInfo.getCompactNo());//自由职业者合同编号
        userInfo.put("pactno",cloudCompactStaffInterfaceCustomInfo.getPactno());//合同编号
        //userInfo.put("content",baseRe);//合同内容
        //userInfo.put("content",jsonContent);//合同内容
        userInfo.put("compact_active",cloudCompactStaffInterfaceCustomInfo.getCompactActive().toString());//用户状态
        userInfo.put("ticketContent",cloudCompactStaffInterfaceCustomInfo.getTicketcontent());//服务内容
        userInfo.put("entryName",cloudCompactStaffInterfaceCustomInfo.getEntryname());//项目名称
        userInfo.put("commoney",cloudDfMoneyInterfaceInfo.getCommoney().toString());//发放金额

        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy 年 MM 月 dd 日");
        SimpleDateFormat detailTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat createTime = new SimpleDateFormat("yyyy 年 MM 月 dd 日");

        userInfo.put("detailTime",detailTime.format(date));//详细时间
        userInfo.put("curTime",myfmt.format(date));//签约时间
        userInfo.put("created",createTime.format(cloudCompanyStaffInfo.getCreated()));//合同创建时间


        String jsonStr = JsonUtils.toJson(userInfo).replaceAll("\\\\","");
        String base64Str = Base64CustomUtils.base64Encoder(jsonStr);
        base64Str = base64Str.replaceAll("\r","");
        base64Str = base64Str.replaceAll("\n","");

        //JsonUtils.toJson(cloudCompactStaffInterfaceCustomInfo.getContent()).replaceAll("\\\\","");//
        String jsonContent = cloudCompactStaffInterfaceCustomInfo.getContent().replaceAll("\r","").replaceAll("\n","").replaceAll("\t","");//.replaceAll("\\\\","");
        String base64Con = Base64CustomUtils.base64Encoder(jsonContent);
        base64Con = base64Con.replaceAll("\r","");
        base64Con = base64Con.replaceAll("\n","");

        JSONObject resPos = new JSONObject();
        resPos.put("logic",base64Str);
        resPos.put("content",base64Con);

        return resPos.toString();
        //return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), userInfo);
    }
    /**
     * 合同签约
     * */
    @RequestMapping(value = "/authCompactSigle", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String authCompactSigle(HttpServletRequest request) {

        String token = request.getParameter("token");//token
        String compactId = request.getParameter("compactId");//token

        compactId = Base64CustomUtils.base64Decoder(compactId);
        if(compactId == null || token == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error", null);
        }
        Map<String,String> loginResultMap = userIsLogin(token);
        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()))
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }
        //合同信息
        CloudCompactStaffInterfaceCustomInfo cloudCompactStaffInterfaceCustomInfo = cloudCompactStaffServiceFacade.getUserCompactById(new Long(compactId));
        if(cloudCompactStaffInterfaceCustomInfo == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到此合同", null);
        }
        if(cloudCompactStaffInterfaceCustomInfo.getCompactActive().equals((byte)1)){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "此份合同已签",null);
        }

        //获取个人信息
        CloudCompanyStaffInfo cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudCompanyStaffById(cloudCompactStaffInterfaceCustomInfo.getStaffid().toString());
        if(cloudCompanyStaffInfo == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "个人信息有误",null);
        }
        if(!cloudCompanyStaffInfo.getIsActive().equals((byte)1)){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "个人尚未签约",null);
        }

        //获取个人代付信息pay_cloud_df_money
        CloudDfMoneyInterfaceInfo cloudDfMoneyInterfaceInfo = cloudDfMoneyServiceFacade.getDfMoneyById(cloudCompactStaffInterfaceCustomInfo.getDfid());
        if(cloudDfMoneyInterfaceInfo == null){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "充值记录信息有误",null);
        }

        //个人银行卡信息
        CloudStaffBanksInfo cloudStaffBanksInfo = cloudStaffBanksServiceFacade.getStaffBankByNumSid(cloudDfMoneyInterfaceInfo.getBankno(), new BigInteger(cloudCompanyStaffInfo.getId()));
        if(cloudStaffBanksInfo == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "当前认证用户银行卡信息不存在", null);
        }
        if(!cloudStaffBanksInfo.getBankActive().equals("1")){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "银行卡未激活", null);
        }
        byte compact_status = 0;
        String hasNoSign = "1";
        //获取未签约合同
        List<CloudCompactStaffInterfaceCustomInfo> getUserCompactList = cloudCompactStaffServiceFacade.getUserCompactListCustom(Long.parseLong(uid),compact_status);
        if(getUserCompactList == null){
            hasNoSign = "0";
        }

        Map<String,String> resData = new HashMap<>();
        resData.put("hasNoSign",hasNoSign);
        //合同表签订
        Map<String,String> compant = new HashMap<>();
        compant.put("compact_active","1");

        //充值记录状态
        Map<String,String> dfMoney = new HashMap<>();
        dfMoney.put("is_active","1");

        int companctActive = cloudCompactStaffServiceFacade.upUserCompactActiveById(compant,new Long(compactId));
        int dfMoneyActive = cloudDfMoneyServiceFacade.updateDfMoneyActive(dfMoney,cloudDfMoneyInterfaceInfo.getId());

        if(companctActive>0 && dfMoneyActive>0){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"合同签订成功",resData);
        }else{

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "合同签订失败", resData);
        }
    }

    @ModelAttribute
    public void beforeAction(HttpServletRequest httpRequest, HttpServletResponse response)
    {
        logger.info("userinfo的beforeAction");
        // 跨域
        String originHeader = httpRequest.getHeader("Origin");
        /*response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.setHeader("Access-Control-Allow-Method", "POST");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
*/
    }

}

