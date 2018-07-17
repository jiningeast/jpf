package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.ToolsUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayCloudFansource;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.SHA1;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.CloudEmployeeInfo;
import com.joiest.jpf.entity.CloudFanSourceInfo;
import com.joiest.jpf.facade.CloudEmployeeServiceFacade;
import com.joiest.jpf.facade.CloudFanSourceServiceFacade;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/companyInfo")
public class CompanyInfoController {

    @Autowired
    private CloudEmployeeServiceFacade cloudEmployeeServiceFacade;


    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private CloudFanSourceServiceFacade cloudFanSourceServiceFacade;

    private String uid;
    private CloudEmployeeInfo companyInfo;

    //判断企业是否登录
    private Map<String,String> companyIsLogin(String token)
    {

        Map<String,String> resultMap = new HashMap<>();
        String uid_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("CLOUD_EMPLOY_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(uid_encrypt)) {
            uid = AESUtils.decrypt(uid_encrypt, ConfigUtil.getValue("AES_KEY"));
            String reg_mid = "^\\d{1,10}$";
            Boolean uidIsTrue = Pattern.compile(reg_mid).matcher(uid).matches();
            if ( !uidIsTrue )
            {
                resultMap.put("0",JpfInterfaceErrorInfo.COMPANYINFO_VALID_FAIL.getCode());
                resultMap.put("1",JpfInterfaceErrorInfo.COMPANYINFO_VALID_FAIL.getDesc());
                return resultMap;
            }
            companyInfo = cloudEmployeeServiceFacade.getCompayEmployeeByUid(new Integer(uid));
            if (companyInfo == null) {
                resultMap.put("0",JpfInterfaceErrorInfo.COMPANYINFO_NOT_EXIST.getCode());
                resultMap.put("1",JpfInterfaceErrorInfo.COMPANYINFO_NOT_EXIST.getDesc());
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
     * 公司登陆
     * */
    @RequestMapping("/companyLogin")
    @ResponseBody
    public String companyLogin(HttpServletRequest request){

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        email = Base64CustomUtils.base64Decoder(email);
        password = Base64CustomUtils.base64Decoder(password);

        String  companypass= SHA1.getInstance().getMySHA1Code(password);

        CloudEmployeeInfo cloudEmployeeInfo = cloudEmployeeServiceFacade.getCompayLoginInfoByEmail(email);
        if(cloudEmployeeInfo == null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到此用户名",null);
        }
        if(cloudEmployeeInfo.getCloudloginpwd().equals(companypass)){

            int random = ToolsUtils.getRandomInt(10000,99999);
            String token = AESUtils.encrypt(cloudEmployeeInfo.getUid().toString() + random,ConfigUtil.getValue("AES_KEY"));
            String mid_encrypt = AESUtils.encrypt(cloudEmployeeInfo.getUid().toString(), ConfigUtil.getValue("AES_KEY"));

            redisCustomServiceFacade.set(ConfigUtil.getValue("CLOUD_EMPLOY_LOGIN_KEY") + token, mid_encrypt, Long.parseLong(ConfigUtil.getValue("CLOUD_EMPLOY_LOGIN_EXPIRE_30")) );

            Map<String,String> tok = new HashMap<String, String>();
            tok.put("token",token);

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "登录成功", tok);
        }else{

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "用户名密码不匹配", null);
        }
    }
    /**
     * 公司登出
     * */
    @RequestMapping("/companyLoginOut")
    @ResponseBody
    public String companyLoginOut(HttpServletRequest request){

        String token = request.getParameter("token");

        String UID =  redisCustomServiceFacade.get(ConfigUtil.getValue("CLOUD_EMPLOY_LOGIN_KEY") + token);
        if ( StringUtils.isBlank(token) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "Error", null);
        }
        redisCustomServiceFacade.remove(ConfigUtil.getValue("CLOUD_EMPLOY_LOGIN_KEY") + token);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "登出成功", null);
    }
    /**
     * 公司修改密码
     * */
    @RequestMapping("companyUpPwd")
    @ResponseBody
    public String companyUpPwd(HttpServletRequest request){

        String token = request.getParameter("token");
        String originPwd = request.getParameter("originPwd");
        String nowPwd = request.getParameter("nowPwd");

        nowPwd = Base64CustomUtils.base64Decoder(nowPwd);
        originPwd = Base64CustomUtils.base64Decoder(originPwd);


        Map<String,String> loginResultMap = companyIsLogin(token);
        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }

        String originpass= SHA1.getInstance().getMySHA1Code(originPwd);
        String nowpass= SHA1.getInstance().getMySHA1Code(nowPwd);

        String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)[0-9A-Za-z_]{6,16}$";
        Boolean regDate_IsTrue = Pattern.compile(reg).matcher(nowPwd).matches();
        if ( !regDate_IsTrue )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "密码格式有误", null);
        }
        if(companyInfo.getCloudloginpwd().equals(nowpass)){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "抱歉,不能与原密码相同", null);
        }
        if(companyInfo.getCloudloginpwd().equals(originpass)){

            Map<String,String> upCom = new HashMap<>();
            upCom.put("cloudloginpwd",nowpass);

            int isSuc = cloudEmployeeServiceFacade.upCompanyEmployeePwdByUid(upCom,new Integer(uid));
            if(isSuc>0){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "密码修改成功", null);
            }
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "密码修改失败", null);
        }else{

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "原密码有误", null);
        }
    }

    /**
     * 操作来源
     * */
    @RequestMapping("sourceAdd")
    @ResponseBody
    public String sourceAdd(HttpServletRequest request){

        String catId = request.getParameter("catId");
        String cat = request.getParameter("cat");
        String mobile = request.getParameter("mobile");
        String name = request.getParameter("name");

        catId = Base64CustomUtils.base64Decoder(catId);
        cat = Base64CustomUtils.base64Decoder(cat);
        mobile = Base64CustomUtils.base64Decoder(mobile);
        name = Base64CustomUtils.base64Decoder(name);

        String reg = "^((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0,0-9])|(19[8|9])|(16[6]))\\d{8}$";
        Boolean mobile_IsTrue = Pattern.compile(reg).matcher(mobile).matches();
        if ( !mobile_IsTrue )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请输入正确的手机号", null);
        }

        CloudFanSourceInfo cloudFanSourceInfo = cloudFanSourceServiceFacade.getFanSourceByMobile(mobile);
        if(cloudFanSourceInfo != null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "您已录入，请勿重复操作", null);
        }
        Map<String,String> map = new HashMap<>();

        map.put("catId",catId);
        map.put("cat",cat);
        map.put("mobile",mobile);
        map.put("name",name);

        int res = cloudFanSourceServiceFacade.addFanSource(map);

        if(res>0){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "SUCCESS", null);
        }else{

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "ERROR", null);
        }
    }
}
