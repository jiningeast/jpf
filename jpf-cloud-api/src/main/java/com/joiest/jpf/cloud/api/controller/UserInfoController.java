package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.dto.GetUserBlanceRequest;
import com.joiest.jpf.entity.CloudCompanyStaffInfo;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.entity.CloudIdcardInfo;
import com.joiest.jpf.entity.CloudStaffBanksInfo;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import com.joiest.jpf.facade.CloudStaffBanksServiceFacade;
import com.joiest.jpf.facade.impl.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    @Autowired
    private CloudCompanyStaffServiceFacadeImpl cloudCompanyStaffServiceFacade;

    @Autowired
    private CloudIdcardServiceFacadeImpl cloudIdcardServiceFacade;

    @Autowired
    private CloudStaffBanksServiceFacadeImpl cloudStaffBanksServiceFacade;

    @Autowired
    private RedisCustomServiceFacadeImpl redisCustomServiceFacade;

    @Autowired
    private ToolCateServiceFacadeImpl toolCateServiceFacade;
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
        Map<Integer,String> userIsLogin = userIsLogin();
        if ( !userIsLogin.get(0).equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NOTlOGIN.getCode(), JpfInterfaceErrorInfo.NOTlOGIN.getDesc(), null);
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
        GetCloudMoneyDfResponse response = cloudDfMoneyServiceFacade.getDfMoneyList(map.get("start"),map.get("end"), userIsLogin.get(1),pageNo, pageSize, flag);
        if ( response == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "暂无数据", null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), response.getMonthTotal().toString(), response);
    }

    //判断用户是否登录
    private Map<Integer,String> userIsLogin()
    {
        Map<Integer,String> map = new HashMap<>();
        map.put(0,JpfInterfaceErrorInfo.SUCCESS.getCode());
        map.put(1,"67136");
        return map;
    }

    /**
     * 获取用户总金额
     * @return
     */
    @RequestMapping(value = "/userdf", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserTotalMoney()
    {
        Map<Integer,String> userIsLogin = userIsLogin();
        if ( !userIsLogin.get(0).equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NOTlOGIN.getCode(), JpfInterfaceErrorInfo.NOTlOGIN.getDesc(), null);
        }

        Double totalMoney = cloudDfMoneyServiceFacade.getUserDfTotalMoney(userIsLogin.get(1));

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), totalMoney);
    }

    /**
     * 月份汇总
     * @return
     */
    @RequestMapping(value = "/usermonth", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserMonthMoney()
    {
        Map<Integer,String> userIsLogin = userIsLogin();
        if ( !userIsLogin.get(0).equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NOTlOGIN.getCode(), JpfInterfaceErrorInfo.NOTlOGIN.getDesc(), null);
        }
        List<CloudDfMoneyInterfaceInfo> list = cloudDfMoneyServiceFacade.getUserMonthList(Long.parseLong(userIsLogin.get(1)));

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), list);
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

        cardId = Base64CustomUtils.base64Decoder(cardId);
        mobile = Base64CustomUtils.base64Decoder(mobile);
        bankNum = Base64CustomUtils.base64Decoder(bankNum);
        verificate = Base64CustomUtils.base64Decoder(verificate);

        CloudCompanyStaffInfo cloudCompanyStaffInfo;
        CloudIdcardInfo cloudIdcardInfo;
        CloudStaffBanksInfo cloudStaffBanksInfo;

        //短信验证
        String verificateRedis = redisCustomServiceFacade.get(mobile+"check");
        if(!verificateRedis.equals(verificate)){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "验证码错误", null);
        }
        if(cardId==null || mobile==null ||  bankNum==null || verificate==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), JpfInterfaceErrorInfo.FAIL.getDesc(), null);
        }else{

            //身份证信息
            cloudIdcardInfo=cloudIdcardServiceFacade.getCloudIdcardById(cardId);

            //员工信息
            cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudIdcardByCardNo(cloudIdcardInfo.getNum());

            //员工银行卡信息
            cloudStaffBanksInfo = cloudStaffBanksServiceFacade.getStaffBankByNumSid(bankNum, cloudCompanyStaffInfo.getId());

            if(cloudIdcardInfo == null){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "当前认证用户身份证信息不存在", null);
            }
            if(cloudCompanyStaffInfo==null){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "当前认证用户员工信息不存在", null);
            }
            if(cloudStaffBanksInfo==null){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "当前认证用户银行卡信息不存在", null);
            }
            String test = cloudStaffBanksInfo.getBankphone();
            if(cloudStaffBanksInfo.getBankphone().equals(mobile)){

                //操作员工状态
                Map<String,String> map = new HashMap<String,String>();

                map.put("is_active","1");
                map.put("code",verificate);

                int res = cloudCompanyStaffServiceFacade.upCloudCompanyStaffByIdcard(cardId,map);
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

    /**
     * 验证用户信息  身份证、银行卡、手机号信息
     * */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String userLogin(HttpServletRequest request) {


        /*String tokn = request.getParameter("token");
        String jiami = redisCustomServiceFacade.get("yun" + tokn);
        String mid_decrypt = AESUtils.decrypt(jiami, ConfigUtil.getValue("AES_KEY"));
        */
        String idCard = request.getParameter("idCard");
        String verificate = request.getParameter("verificate");
        if(idCard == null || idCard.isEmpty() || verificate==null || verificate.isEmpty()){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error",null);
        }
        idCard = Base64CustomUtils.base64Decoder(idCard);
        verificate = Base64CustomUtils.base64Decoder(verificate);

        //获取员工信息
        CloudCompanyStaffInfo cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudIdcardByCardNo(idCard);
        if(cloudCompanyStaffInfo==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到对应员工账号信息",null);
        }
        String check= redisCustomServiceFacade.get(cloudCompanyStaffInfo.getMobile()+"login");
        if(verificate.equals(check)){

            int random = toolCateServiceFacade.getRandomInt(10000,99999);
            String token = AESUtils.encrypt(cloudCompanyStaffInfo.getId().toString() + random,ConfigUtil.getValue("AES_KEY"));
            String mid_encrypt = AESUtils.encrypt(cloudCompanyStaffInfo.getId().toString(), ConfigUtil.getValue("AES_KEY"));

            redisCustomServiceFacade.set("yun" + token, mid_encrypt, 3600*24*7);


            Map<String,String> tok = new HashMap<String, String>();
            tok.put("token",token);

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "登录成功", tok);
        }else{

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "验证码错误",null);
        }
    }
    @RequestMapping(value = "/sendLoginSms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendLoginSms(HttpServletRequest request) throws IOException {

        //String aa= redisCustomServiceFacade.get("liangliang");

        String idCard = request.getParameter("idCard");
        idCard = Base64CustomUtils.base64Decoder(idCard);

        //获取员工信息
        CloudCompanyStaffInfo cloudCompanyStaffInfo = cloudCompanyStaffServiceFacade.getCloudIdcardByCardNo(idCard);
        if(cloudCompanyStaffInfo == null || idCard ==null || idCard.isEmpty()){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到对应员工账号信息",null);
        }
        try{

            String  phone = cloudCompanyStaffInfo.getMobile();
            int verificateCode = toolCateServiceFacade.getRandomInt(100000,999999);//短信内容

            redisCustomServiceFacade.set(phone+"login",new Integer(verificateCode).toString(),60*10);
            String content = null;
            content = "尊敬的用户，您此次登录的手机验证码是："+verificateCode+",10十分钟内有效";

            int result=toolCateServiceFacade.SendSms(ConfigUtil.getValue("MWMONGATESENDSUBMIT_URL")
                    ,ConfigUtil.getValue("MWUSERNAME"), ConfigUtil.getValue("MWPASSWORD"), phone, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。

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
}
