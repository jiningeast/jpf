package com.joiest.jpf.common.util;

import com.joiest.jpf.common.constant.ManageConstants;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class OfpayUtils {

    private String userid;

    private String userpws;

    private String phone_requestUrl;

    //查询接口
    private String phone_query;

    private String gas_requestUrl;

    //登录地址
    private String login_requestUrl;

    //绑卡接口
    private String bindCard_requestUrl;

    private String cardid_fast;

    private String version_phone;

    private String regis_query;

    private String aesKey;

    private String aes_ecb_nopadding_pw;


    //油卡卡号查询
    private String gas_query;

    public OfpayUtils() {
        this.userid = ConfigUtil.getValue("userid");
        this.userpws = ConfigUtil.getValue("userpws");
        this.phone_requestUrl = ConfigUtil.getValue("phone_requestUrl");
        this.phone_query = ConfigUtil.getValue("phone_query");
        this.gas_requestUrl = ConfigUtil.getValue("gas_requestUrl");
        this.login_requestUrl = ConfigUtil.getValue("login_requestUrl");
        this.bindCard_requestUrl = ConfigUtil.getValue("bindCard_requestUrl");
        this.cardid_fast = ConfigUtil.getValue("cardid_fast");
        this.version_phone = ConfigUtil.getValue("version_phone");
        this.gas_query = ConfigUtil.getValue("gas_query");
        this.regis_query = ConfigUtil.getValue("res_query");
        this.aesKey = ConfigUtil.getValue("AES_ECB_NOPADDING_PW");
        this.aes_ecb_nopadding_pw = ConfigUtil.getValue("AES_ECB_NOPADDING_PW");
    }

    /**
     * 根据手机号和面值查询商品信息
     * @param queryMap
     * @return
     */
    public Map<String, String> telquery(Map<String,String> queryMap){
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid", userid);       // 商户号
        requestMap.put("userpws",userpws);     // 商户密码
        //requestMap.put("userpws", Md5Encrypt.md5(userpws));     // 商户密码
        requestMap.put("phoneno", queryMap.get("phoneno") );        // 手机号码
        requestMap.put("pervalue", queryMap.get("pervalue") );      // 面值
//        requestMap.put("mctype", "" );
        requestMap.put("version", version_phone );

        String requestParam = ToolUtils.mapToUrl(requestMap);   //请求参数

        String resultXml = OkHttpUtils.postForm(phone_query,requestMap);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n充值类型:" + "手机号和面值查询商品信息");
        sbf.append("\n请求地址：" + phone_query);
        sbf.append("\n接口参数：" + requestMap);
        sbf.append("\n回调信息：" + resultXml);

        String fileName = "ofpayPhoneQuery";
        String path = "/logs/jpf-charge-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        Map<String, String> resultMap = new ReadXML().getBooksOneByStr(resultXml);
        resultMap.put("requestUrl", phone_query);
        resultMap.put("requestParam", requestParam);
        return resultMap;
    }
    /**
     * 手机充值
     */
    public Map<String, String> chargePhone(Map<String,Object> rechargeMap){

        SimpleDateFormat myfmt = new SimpleDateFormat("yyyyMMddHHmmss");
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid", userid);                   // 商户号
        //requestMap.put("userpws", userpws); // 商户密码
        requestMap.put("userpws", Md5Encrypt.md5(userpws)); // 商户密码
        requestMap.put("cardid", cardid_fast);              // 快充140101，慢充170101
        requestMap.put("cardnum",rechargeMap.get("cardnum").toString());         // 充值金额
        // requestMap.put("mctype","48");          // 如果是慢充商品必须传如48 表示48小时到账
        requestMap.put("sporder_id", rechargeMap.get("sporder_id").toString());
        requestMap.put("sporder_time", myfmt.format(rechargeMap.get("sporder_time")));
        requestMap.put("game_userid", rechargeMap.get("game_userid").toString());       // 手机号码
        requestMap.put("md5_str", getPhoneSign(requestMap));        // 签名串
        requestMap.put("ret_url", rechargeMap.get("ret_url"));      // 返回地址
        requestMap.put("version", version_phone);
        requestMap.put("buyNum", rechargeMap.get("buyNum").toString());

        String requestParam = ToolUtils.mapToUrl(requestMap);   //请求参数
        String resultXml = OkHttpUtils.postForm(phone_requestUrl,requestMap);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n充值类型:" + "话费充值");
        sbf.append("\n请求地址：" + phone_requestUrl);
        sbf.append("\n接口参数：" + requestMap);
        sbf.append("\n回调信息：" + resultXml);

        String fileName = "OfpayPhone";
        String path = "/logs/jpf-charge-api/log/";

        Map<String,String> map = new ReadXML().getBooksOneByStr(resultXml);
        String orderStatus = map.getOrDefault("retcode","");
        String orderStatus_cn = orderStatus.equals("1") ? "提交成功" : "提交失败";
        String rechargeStatus = map.getOrDefault("game_state","");
        String rechargeStatus_cn = "";   //如果成功将为1，澈消(充值失败)为9，充值中为0,只能当状态为9时，商户才可以退款给用户。
        if ( orderStatus.equals("1") )
        {
            rechargeStatus_cn = ManageConstants.rechargeStatusCn_map.get(rechargeStatus);
        } else
        {
            rechargeStatus_cn = map.getOrDefault("err_msg","");
        }
        sbf.append("\n提交状态：" + orderStatus_cn + ";充值状态:" + rechargeStatus_cn);
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        map.put("responseParam", JSONObject.fromObject(map).toString());
        map.put("requestUrl", phone_requestUrl);
        map.put("requestParam", requestParam);
        return map;
    }

    /**
     * 获取手机充值签名
     */
    private String getPhoneSign(Map<String,Object> map){

        String aa = ConfigUtil.getValue("keystr");
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("cardid") + map.get("cardnum") + map.get("sporder_id") + map.get("sporder_time") + map.get("game_userid") + ConfigUtil.getValue("keystr");
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }

    /**
     * 油卡-注册
     */
    public Map<String, String> oilRegister(Map<String,String> resMap){

        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid", userid);       // 商户号
        requestMap.put("userpws", Md5Encrypt.md5(userpws));     // 商户密码
        requestMap.put("version", version_phone );
        requestMap.put("login_name", resMap.get("login_name") );    // 注册账号
        String password=resMap.getOrDefault("login_pwd","");//密码
        if(!password.equals("")){
           String loginspwd= Base64.encodeBase64String(AES_ECBUtils.encrypt(password, aesKey.getBytes()));
           requestMap.put("login_pwd", loginspwd );
        }

        String email=resMap.getOrDefault("email","");//电子邮件

        if ( !email.equals("") )
        {
            String emailaes=Base64.encodeBase64String(AES_ECBUtils.encrypt(email, aesKey.getBytes()));
            requestMap.put("email", emailaes);
        }

        String phone=resMap.getOrDefault("phone_no","");//手机号
        if(!phone.equals("")){
            String phoneaes=Base64.encodeBase64String(AES_ECBUtils.encrypt(phone, aesKey.getBytes()));
            requestMap.put("phone_no",phoneaes);
        }
        String chargeType = resMap.getOrDefault("charge_type","");
        if ( !chargeType.equals("") )
        {

            requestMap.put("charge_type", resMap.get("charge_type") );      // 加油卡类型 （1:中石化、2:中石油；默认为1，不参与MD5校验）
        }

        requestMap.put("md5_str", getResQuerySign(requestMap));         //签名串
        String requestParam = ToolUtils.mapToUrl(requestMap);   //请求参数
        String resultXml = OkHttpUtils.postForm(regis_query,requestMap);
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n注册油卡:"+  "油卡注册");
        sbf.append("\n请求地址：" + regis_query);
        sbf.append("\n接口参数：" + requestMap);
        sbf.append("\n回调信息：" + resultXml);

        String fileName = "ofpayRegisQuery";
        String path = "/logs/jpf-market-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        Map<String, String> resultMap = new ReadXML().getBooksOneByStr(resultXml);
        resultMap.put("retcode", resultMap.get("retcode"));
        resultMap.put("err_msg", resultMap.get("err_msg"));
        resultMap.put("event_id", resultMap.get("event_id"));
        return resultMap;
    }

    /**
     * 油卡注册MD5
     */
    public String getResQuerySign(Map<String,Object> map){
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("login_name")+ map.get("login_pwd")+map.get("email")+ map.get("phone_no")+map.get("charge_type")+ ConfigUtil.getValue("keystr");
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }
    /**
     * 油卡-登录
     */
    public Map<String, String> oilLogin(Map<String,Object> rechargeMap){
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyyMMddHHmmss");
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid", userid);           // 商户号
        requestMap.put("userpws", Md5Encrypt.md5(userpws));         // 商户密码
        requestMap.put("login_name", rechargeMap.get("login_name"));                            // 商品编号以产品部门提供的为准

        byte[] encrypt_data = AES_ECBUtils.encrypt(rechargeMap.get("login_pwd").toString(), aes_ecb_nopadding_pw.getBytes());
        String loginPwdStr = Base64.encodeBase64String(encrypt_data);
        requestMap.put("login_pwd",loginPwdStr);

        String charge_type = rechargeMap.getOrDefault("charge_type","").toString();
        if ( !charge_type.equals("") )
        {
            requestMap.put("charge_type", rechargeMap.get("charge_type") );               // 加油卡类型 （1:中石化、2:中石油；默认为1，不参与MD5校验）
        }
        requestMap.put("version", version_phone);
        requestMap.put("md5_str", getOilLoginSign(requestMap));          // 签名串

        String requestParam = ToolUtils.mapToUrl(requestMap);       //请求参数

        String resultXml =  OkHttpUtils.postForm(login_requestUrl, requestMap);

        requestMap.put("ret_url", login_requestUrl);// 返回地址

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n充值类型:" + "油卡登录");
        sbf.append("\n请求地址：" + login_requestUrl);
        sbf.append("\n接口参数：" + requestMap);
        sbf.append("\n回调信息：" + resultXml);

        String fileName = "ofpayLogin";
        String path = "/logs/jpf-market-api/log/";

        Map<String,String> map = new ReadXML().getBooksOneByStr(resultXml);
        String orderStatus = map.getOrDefault("retcode","");
        String orderStatus_cn = orderStatus.equals("1") ? "提交成功" : "提交失败";

        String rechargeStatus_cn = "";   //如果成功将为1，澈消(充值失败)为9，充值中为0,只能当状态为9时，商户才可以退款给用户。
        if ( orderStatus.equals("1") )
        {
            rechargeStatus_cn = map.getOrDefault("cardNo","");
        } else
        {
            rechargeStatus_cn = map.getOrDefault("err_msg","");
        }

        sbf.append("\n提交状态码：" + orderStatus+"\t"+orderStatus_cn+"\t" + "接口返回信息:" + rechargeStatus_cn);
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
        map.put("requestUrl", login_requestUrl);
        map.put("requestParam", requestParam);
        return map;
    }

    /**
     * 油卡-绑卡
     */
    public Map<String, String> oilBindCard(Map<String,Object> rechargeMap){
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyyMMddHHmmss");
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid", userid);           // 商户号
        requestMap.put("userpws", Md5Encrypt.md5(userpws));         // 商户密码
        requestMap.put("login_name", rechargeMap.get("login_name"));                            // 商品编号以产品部门提供的为准

        byte[] encrypt_data = AES_ECBUtils.encrypt(rechargeMap.get("login_pwd").toString(), aes_ecb_nopadding_pw.getBytes());
        String loginPwdStr = Base64.encodeBase64String(encrypt_data);
        requestMap.put("login_pwd",loginPwdStr);
        byte[] name_data = AES_ECBUtils.encrypt(rechargeMap.get("name").toString(), aes_ecb_nopadding_pw.getBytes());
        String nameStr = Base64.encodeBase64String(name_data);
        requestMap.put("name",nameStr); //用户真实姓名
        requestMap.put("cert_type", rechargeMap.get("cert_type") ); //证件类型，1：身份证
        byte[] cert_no_data = AES_ECBUtils.encrypt(rechargeMap.get("name").toString(), aes_ecb_nopadding_pw.getBytes());
        String cert_noStr = Base64.encodeBase64String(cert_no_data);
        requestMap.put("cert_no", cert_noStr ); //证件号码
        requestMap.put("gas_card_no", rechargeMap.get("gas_card_no") );//加油卡卡号
        byte[] email_data = AES_ECBUtils.encrypt(rechargeMap.get("name").toString(), aes_ecb_nopadding_pw.getBytes());
        String emailStr = Base64.encodeBase64String(email_data);
        requestMap.put("email", emailStr );//邮箱
        byte[] phone_no_data = AES_ECBUtils.encrypt(rechargeMap.get("name").toString(), aes_ecb_nopadding_pw.getBytes());
        String phone_noStr = Base64.encodeBase64String(phone_no_data);
        requestMap.put("phone_no", phone_noStr );//手机号码
        requestMap.put("charge_type", rechargeMap.get("charge_type") );//加油卡类型1:中石化、2:中石油

        requestMap.put("version", version_phone);
        requestMap.put("md5_str", getOilBindSign(requestMap));          // 签名串

        String requestParam = ToolUtils.mapToUrl(requestMap);       //请求参数

        String resultXml =  OkHttpUtils.postForm(bindCard_requestUrl, requestMap);

        requestMap.put("ret_url", bindCard_requestUrl);// 返回地址

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n充值类型:" + "油卡登录");
        sbf.append("\n请求地址：" + bindCard_requestUrl);
        sbf.append("\n接口参数：" + requestMap);
        sbf.append("\n回调信息：" + resultXml);

        String fileName = "ofpayLogin";
        String path = "/logs/jpf-market-api/log/";

        Map<String,String> map = new ReadXML().getBooksOneByStr(resultXml);
        String orderStatus = map.getOrDefault("retcode","");
        String orderStatus_cn = orderStatus.equals("1") ? "提交成功" : "提交失败";

        String rechargeStatus_cn = "";   //如果成功将为1，澈消(充值失败)为9，充值中为0,只能当状态为9时，商户才可以退款给用户。
        if ( orderStatus.equals("1") )
        {
            rechargeStatus_cn = map.getOrDefault("cardNo","");
        } else
        {
            rechargeStatus_cn = map.getOrDefault("err_msg","");
        }

        sbf.append("\n提交状态码：" + orderStatus+"\t"+orderStatus_cn+"\t" + "接口返回信息:" + rechargeStatus_cn);
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
        map.put("requestUrl", bindCard_requestUrl);
        map.put("requestParam", requestParam);
        return map;
    }

    /**
     * 加油卡卡号信息查询
     */
    public Map<String, String> gasQuery(Map<String,String> queryMap){
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid", userid);       // 商户号
        requestMap.put("userpws", Md5Encrypt.md5(userpws));     // 商户密码
        requestMap.put("game_userid", queryMap.get("game_userid") );    // 加油卡号
        requestMap.put("version", version_phone );
        requestMap.put("md5_str", getGasQuerySign(requestMap));         //签名串
        String chargeType = queryMap.getOrDefault("chargeType","");
        if ( !chargeType.equals("") )
        {
            requestMap.put("chargeType", queryMap.get("chargeType") );      // 加油卡类型 （1:中石化、2:中石油；默认为1，不参与MD5校验）
        }

        String requestParam = ToolUtils.mapToUrl(requestMap);   //请求参数

        String resultXml = OkHttpUtils.postForm(gas_query,requestMap);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n充值类型:" + "加油卡卡号信息查询");
        sbf.append("\n请求地址：" + gas_query);
        sbf.append("\n接口参数：" + requestMap);
        sbf.append("\n回调信息：" + resultXml);

        String fileName = "ofpayGasQuery";
        String path = "/logs/jpf-market-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        Map<String, String> resultMap = new ReadXML().getBooksOneByStr(resultXml);
        resultMap.put("requestUrl", gas_query);
        resultMap.put("requestParam", requestParam);
        return resultMap;
    }

    /**
     * 加油卡充值
     */
    public Map<String, String> chargeGas(Map<String,Object> rechargeMap){

        SimpleDateFormat myfmt = new SimpleDateFormat("yyyyMMddHHmmss");
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid", userid);                           // 商户号
        requestMap.put("userpws", Md5Encrypt.md5(userpws));         // 商户密码
        requestMap.put("cardid", rechargeMap.get("cardid"));        // 商品编号以产品部门提供的为准
        //requestMap.put("cardid", "64127500");                     // 商品编号以产品部门提供的为准
        requestMap.put("cardnum",rechargeMap.get("cardnum").toString());     // 1.任意充需要待充值面值（1的整数倍) 2.卡充充值这里表示数量
        requestMap.put("sporder_id", rechargeMap.get("sporder_id").toString());
        requestMap.put("sporder_time", myfmt.format(rechargeMap.get("sporder_time")));
        requestMap.put("game_userid", rechargeMap.get("game_userid"));       // 手机号码
        String chargeType = rechargeMap.getOrDefault("chargeType","").toString();
        if ( !chargeType.equals("") )
        {
            requestMap.put("chargeType", rechargeMap.get("chargeType") );               // 加油卡类型 （1:中石化、2:中石油；默认为1，不参与MD5校验）
        }
        requestMap.put("md5_str", getOilSign(requestMap));          // 签名串
        requestMap.put("ret_url", rechargeMap.get("ret_url"));         // 返回地址
        requestMap.put("version", version_phone);

        String requestParam = ToolUtils.mapToUrl(requestMap);       //请求参数

        String resultXml =  OkHttpUtils.postForm(gas_requestUrl, requestMap);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n充值类型:" + "油卡充值");
        sbf.append("\n请求地址：" + gas_requestUrl);
        sbf.append("\n接口参数：" + requestMap);
        sbf.append("\n回调信息：" + resultXml);

        String fileName = "ofpayGas";
        String path = "/logs/jpf-market-api/log/";

        Map<String,String> map = new ReadXML().getBooksOneByStr(resultXml);
        String orderStatus = map.getOrDefault("retcode","");
        String orderStatus_cn = orderStatus.equals("1") ? "提交成功" : "提交失败";
        String rechargeStatus = map.getOrDefault("game_state","");
        String rechargeStatus_cn = "";   //如果成功将为1，澈消(充值失败)为9，充值中为0,只能当状态为9时，商户才可以退款给用户。
        if ( orderStatus.equals("1") )
        {
            rechargeStatus_cn = ManageConstants.rechargeStatusCn_map.get(rechargeStatus);
        } else
        {
            rechargeStatus_cn = map.getOrDefault("err_msg","");
        }
        sbf.append("\n提交状态：" + orderStatus_cn + ";充值状态:" + rechargeStatus_cn);
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        map.put("responseParam", JSONObject.fromObject(map).toString());
        map.put("requestUrl", phone_requestUrl);
        map.put("requestParam", requestParam);

        return map;
    }



    /**
     * 获取油卡充值签名
     */
    public String getOilSign(Map<String,Object> map){
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("cardid") + map.get("cardnum") + map.get("sporder_id") + map.get("sporder_time") + map.get("game_userid") + ConfigUtil.getValue("keystr");
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }

    /**
     * 获取油卡登录签名
     */
    public String getOilLoginSign(Map<String,Object> map){
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("login_name") + map.get("login_pwd") + map.get("charge_type") + ConfigUtil.getValue("keystr");
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }

    /**
     * 获取油卡登录签名
     */
    public String getOilBindSign(Map<String,Object> map){
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("login_name") + map.get("login_pwd") + map.get("name") + map.get("cert_type") + map.get("cert_no") + map.get("gas_card_no") + map.get("email") + map.get("phone_no") + map.get("charge_type") + ConfigUtil.getValue("keystr");
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }

    /**
     * 获取加油卡卡号信息查询
     */
    public String getGasQuerySign(Map<String,Object> map){
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("game_userid") + ConfigUtil.getValue("keystr");
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }

//    public String getNotifySign(String ret_Code, String sporder_id)
//    {
//        String str = userid + ret_Code + userpws +
//    }
}
