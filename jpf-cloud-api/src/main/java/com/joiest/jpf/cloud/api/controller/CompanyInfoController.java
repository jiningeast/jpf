package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.ToolsUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayCloudFansource;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private PcaServiceFacade pcaServiceFacade;

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;

    @Autowired
    private  CloudCompanyAgentServiceFacade cloudCompanyAgentServiceFacade;

    @Autowired
    private CloudCompanySalesServiceFacade cloudCompanySalesServiceFacade;

    @Autowired
    private CloudCompanyBankServiceFacade cloudCompanyBankServiceFacade;

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
     * 公司登录
     * */
    @RequestMapping(value = "/companyLogin", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
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
        if(cloudEmployeeInfo.getStatus().equals((byte)-1)){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "此账户已锁定",null);
        }
        //获取代理以及非代理
        CloudCompanyAgentInfo cloudCompanyAgentInfo = cloudCompanyAgentServiceFacade.getAgentByAgentNo(cloudEmployeeInfo.getMerchNo());
        if(cloudCompanyAgentInfo != null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请使用业务公司账号登录",null);
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

        String token = request.getHeader("token");
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

        String token = request.getHeader("token");

        String originPwd = request.getParameter("originPwd");//原密码
        String nowPwd = request.getParameter("nowPwd");      //现密码
        String nowComPwd = request.getParameter("nowComPwd");//确认密码

        nowPwd = Base64CustomUtils.base64Decoder(nowPwd);
        nowComPwd = Base64CustomUtils.base64Decoder(nowComPwd);
        originPwd = Base64CustomUtils.base64Decoder(originPwd);

        if(!nowComPwd.equals(nowPwd)){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "密码与确认密码不一致", null);
        }
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
     * 获取公司信息通过商户号
     * */
    @RequestMapping("getRecByMerchNo")
    @ResponseBody
    public String getRecByMerchNo(HttpServletRequest request){

        //String token = request.getParameter("token");
        String token = request.getHeader("token");

        Map<String,String> loginResultMap = companyIsLogin(token);
        if ( !loginResultMap.get("0").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(loginResultMap.get("0"), loginResultMap.get("1"), null);
        }
        CloudCompanyInfo cloudCompanyInfo = cloudCompanyServiceFacade.getRecByMerchNo(companyInfo.getMerchNo());
        Map<String, String> merchInfo = new HashMap<String, String>();
        if(cloudCompanyInfo != null){

            merchInfo.put("merchNo", cloudCompanyInfo.getMerchNo());//商户平台ID
            merchInfo.put("name", cloudCompanyInfo.getName());//企业名称
            merchInfo.put("certificate", cloudCompanyInfo.getCertificate());//营业执照注册号
            merchInfo.put("bslicense", cloudCompanyInfo.getBslicense());//营业执照影印件
            merchInfo.put("taxpayertype", cloudCompanyInfo.getTaxpayertype());//纳税人类型
            merchInfo.put("tin", cloudCompanyInfo.getTin());//纳税人识别号
            merchInfo.put("address", cloudCompanyInfo.getAddress());//单位注册地址
            merchInfo.put("serviclinkuser", cloudCompanyInfo.getServiclinkuser());//客户经理
            merchInfo.put("phone", cloudCompanyInfo.getLinkphone());//手机号
            merchInfo.put("email", cloudCompanyInfo.getLinkemail());//邮箱
            merchInfo.put("merch_name", cloudCompanyInfo.getMerchName());//别名

            merchInfo.put("linkname", companyInfo.getLinkname());//联系人姓名
            merchInfo.put("linkphone", companyInfo.getLinkphone());//联系人手机号
            merchInfo.put("linkemail", companyInfo.getLinkemail());//联系人邮箱
            /*联系地址 = 省份 + 城市 + 地址详情*/
            merchInfo.put("province", companyInfo.getProvince().toString());//省份
            merchInfo.put("city", companyInfo.getCity().toString());//城市
            merchInfo.put("linkaddress", companyInfo.getAddress());//联系地址详情

            PcaInfo pcaProInfo = pcaServiceFacade.getPcaByCatid(new Integer(companyInfo.getProvince().toString()));
            PcaInfo pcaCityInfo = pcaServiceFacade.getPcaByCatid(new Integer(companyInfo.getCity().toString()));

            merchInfo.put("province_cn", pcaProInfo.getCat());//省份
            merchInfo.put("city_cn", pcaCityInfo.getCat());//地区

            CloudCompanyBankInfo companyBankInfo = cloudCompanyBankServiceFacade.getCompanyBankInfoByMerchNo(companyInfo.getMerchNo());
            merchInfo.put("bankname", companyBankInfo.getBankname());//开户名称
            merchInfo.put("bankno", companyBankInfo.getBankno());//对公账户
            merchInfo.put("banksubname", companyBankInfo.getBanksubname());//开户银行

            SimpleDateFormat detailTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            merchInfo.put("created",detailTime.format(cloudCompanyInfo.getCreated()));

            //获取代理以及非代理
            CloudCompanyAgentInfo cloudCompanyAgentInfo = cloudCompanyAgentServiceFacade.getAgentByAgentNo(companyInfo.getMerchNo());
            if(cloudCompanyAgentInfo == null){

                CloudCompanySalesInfo cloudCompanySalesInfo = cloudCompanySalesServiceFacade.getSalesBySalesNo(companyInfo.getMerchNo());
                merchInfo.put("rate",cloudCompanySalesInfo.getSalesRate().toString());
            }else{
                merchInfo.put("rate",cloudCompanyAgentInfo.getAgentRate().toString());
            }

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "已获取", merchInfo);
        }else{

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到企业信息", null);
        }
    }
    /*
    * 获取地区
    * */
    @RequestMapping("regionInfo")
    @ResponseBody
    public String regionInfo(HttpServletRequest request){

        String pid = request.getParameter("pid");

        pid = Base64CustomUtils.base64Decoder(pid);

        if(StringUtils.isBlank(pid)){
            pid = "0";
        }
        List<PcaInfo > pcaInfo =  pcaServiceFacade.getPcasInner(pid);
        if(pcaInfo.isEmpty()){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未获取到数据", null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "SUCCESS", pcaInfo);
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

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "您的信息我们已经收到，请勿重复操作", null);
        }
        Map<String,String> map = new HashMap<>();

        map.put("catId",catId);
        map.put("cat",cat);
        map.put("mobile",mobile);
        map.put("name",name);

        int res = cloudFanSourceServiceFacade.addFanSource(map);

        if(res>0){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "您的信息我们已经收到,我们将尽快与您联系", null);
        }else{

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息错误，请重新填写", null);
        }
    }

    @ModelAttribute
    public void beforeAction(HttpServletRequest httpRequest, HttpServletResponse response)
    {
        // 跨域
        String originHeader = httpRequest.getHeader("Origin");
        //response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, accept, content-type, token,X-Auth-Token");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //response.setHeader("Access-Control-Allow-Method", "POST,OPTIONS");
        //response.setHeader("token", originHeader);

    }

}
