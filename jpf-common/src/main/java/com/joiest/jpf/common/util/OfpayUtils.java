package com.joiest.jpf.common.util;

import com.joiest.jpf.common.constant.ManageConstants;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.dom4j.DocumentException;

import java.text.SimpleDateFormat;
import java.util.*;

public class OfpayUtils {

    private String userid;

    private String userpws;

    private String phone_requestUrl;

    //查询接口
    private String phone_query;

    //用户信息查询接口
    private String userinfo_query;

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

    private String querycardinfo;

    private String finance_query;

    //油卡卡号查询
    private String gas_query;
    private String searchOrderInfo;

    public OfpayUtils() {
        this.userid = ConfigUtil.getValue("userid");
        this.userpws = ConfigUtil.getValue("userpws");
        this.phone_requestUrl = ConfigUtil.getValue("phone_requestUrl");
        this.phone_query = ConfigUtil.getValue("phone_query");
        this.userinfo_query = ConfigUtil.getValue("userinfo_query");
        this.gas_requestUrl = ConfigUtil.getValue("gas_requestUrl");
        this.login_requestUrl = ConfigUtil.getValue("login_requestUrl");
        this.bindCard_requestUrl = ConfigUtil.getValue("bindCard_requestUrl");
        this.cardid_fast = ConfigUtil.getValue("cardid_fast");
        this.version_phone = ConfigUtil.getValue("version_phone");
        this.gas_query = ConfigUtil.getValue("gas_query");
        this.regis_query = ConfigUtil.getValue("res_query");
        this.aesKey = ConfigUtil.getValue("AES_ECB_NOPADDING_PW");
        this.aes_ecb_nopadding_pw = ConfigUtil.getValue("AES_ECB_NOPADDING_PW");
        this.querycardinfo = ConfigUtil.getValue("querycardinfo");
        this.finance_query = ConfigUtil.getValue("finance_query");
        this.searchOrderInfo = ConfigUtil.getValue("searchOrderInfo");
    }

    /**
     * 自动对账接口_账务明细部分
     * @param queryMap
     * @return
     * @version 6.0
     */
    public Map<String, String> searchOrderInfo(Map<String,String> queryMap) throws DocumentException {
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid",userid); // 商户号
        requestMap.put("userpws",Md5Encrypt.md5(userpws));  // 商户密码
        requestMap.put("sporder_id",queryMap.get("sporder_id")); //商家下单时传入的订单号
        requestMap.put("md5_str", getStandardSign(requestMap)); //统一签名接口 参数必须按照接口顺序传入
        requestMap.put("format",queryMap.get("format")); // 版本
        requestMap.put("version","6.0"); // 版本

        String requestParam = ToolUtils.mapToUrl(requestMap); // 请求参数
        String resultXml = OkHttpUtils.postForm(searchOrderInfo,requestMap);

        StringBuilder stringBuilder = new StringBuilder();
        Date date = new Date();
        String fileName = "SearchOrderInfo";
        String path = "/logs/jpf-market-api/log/";
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stringBuilder.append("\n\nTime:" + myfmt1.format(date));
        stringBuilder.append("\n业务类型:" + "查询订单信息");
        stringBuilder.append("\n请求地址：" + searchOrderInfo);
        stringBuilder.append("\n接口参数：" + requestMap);
        stringBuilder.append("\n回调信息：" + resultXml);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("responseParam",resultXml);
        resultMap.put("requestUrl", searchOrderInfo);
        resultMap.put("requestParam", requestParam);
        return resultMap;
    }

    /**
     * 自动对账接口_账务明细部分
     * @param queryMap
     * @return
     * @version 6.0
     */
    public String financequery(Map<String,String> queryMap) throws DocumentException {
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid",userid); // 商户号
        requestMap.put("userpws",Md5Encrypt.md5(userpws));  // 商户密码
        requestMap.put("starttime",queryMap.get("starttime")); // 开始时间
        requestMap.put("endtime",queryMap.get("endtime")); // 结束时间
        requestMap.put("pagenum",queryMap.get("pagenum")); // 当前页码
        requestMap.put("pagesize",queryMap.get("pagesize")); // 每页条数
        requestMap.put("paymenttype",queryMap.get("paymenttype")); // 收支类型（0：收入 1：支出 不传默认为全部 不参与MD5验证
        requestMap.put("md5_str", getFinanceSign(requestMap));
        requestMap.put("version","6.0"); // 版本

        String requestParam = ToolUtils.mapToUrl(requestMap); // 请求参数
        String resultXml = OkHttpUtils.postForm(finance_query,requestMap);

        StringBuilder stringBuilder = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stringBuilder.append("\n\nTime:" + myfmt1.format(date));
        stringBuilder.append("\n充值类型:" + "查询财务明细信息");
        stringBuilder.append("\n请求地址：" + finance_query);
        stringBuilder.append("\n接口参数：" + requestMap);
        stringBuilder.append("\n回调信息：" + resultXml);

        //Map<String, String> resultMap = new HashMap<>();
        JSONObject resultMap = new JSONObject();
        //System.out.println("转换后的json字符串是：" + JsonUtils.xmlToJson(resultXml).toString());
        resultMap.put("responseParam",JsonUtils.xmlToJson(resultXml).toString());
        resultMap.put("requestUrl", finance_query);
        resultMap.put("requestParam", requestParam);
        return resultMap.toString();
    }

    /**
     * 根据手机号和面值查询商品信息
     * @param queryMap
     * @return
     */
    public Map<String, String> telquery(Map<String,String> queryMap){
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid", userid);       // 商户号
        requestMap.put("userpws", Md5Encrypt.md5(userpws)); // 商户密码
        //requestMap.put("userpws", Md5Encrypt.md5(userpws));     // 商户密码
        requestMap.put("phoneno", queryMap.get("phoneno") );        // 手机号码
        requestMap.put("pervalue", queryMap.get("pervalue") );      // 面值
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

        String fileName = "OfpayPhoneQuery";
        String path = "/logs/jpf-charge-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        Map<String, String> resultMap = new ReadXML().getBooksOneByStr(resultXml);
        resultMap.put("responseParam", JSONObject.fromObject(resultMap).toString());
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
     * 公共sign签名 签名键必须按照接口顺序传入
     */
    private String getStandardSign(Map<String,Object> map){
        String signStr = "";
        if( !map.isEmpty()){
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                signStr += entry.getValue().toString();
            }
            signStr += ConfigUtil.getValue("keystr");
            return Md5Encrypt.md5(signStr).toUpperCase();
        }
        return null;
    }

    /**
     * 获取对账接口_账务明细签名
     * @param map
     * @return
     */
    private String getFinanceSign(Map<String,Object> map){
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("starttime") + map.get("endtime") + map.get("pagenum") + map.get("pagesize") + ConfigUtil.getValue("keystr");
        return Md5Encrypt.md5(myPackage).toUpperCase();
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

        String fileName = "OfpayRegisQuery";
        String path = "/logs/jpf-charge-api/log/";
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

        String fileName = "OfpayLogin";
        String path = "/logs/jpf-charge-api/log/";

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

        String fileName = "OfpayLogin";
        String path = "/logs/jpf-charge-api/log/";

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

        String fileName = "OfpayGasQuery";
        String path = "/logs/jpf-charge-api/log/";
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
        if (!"".equals(chargeType)){
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

        String fileName = "OfpayGas";
        String path = "/logs/jpf-charge-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

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
     * 余额查询接口
     * */
    public Map<String, String> queryUserInfo(){

        Map<String,Object> requestMap = new LinkedHashMap<>();

        requestMap.put("userid", userid);       // 商户号
        requestMap.put("userpws", Md5Encrypt.md5(userpws));         // 商户密码
        requestMap.put("version", version_phone );

        String requestParam = ToolUtils.mapToUrl(requestMap);   //请求参数

        String resultXml = OkHttpUtils.postForm(userinfo_query,requestMap);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n充值类型:" + "查询用户信息");
        sbf.append("\n请求地址：" + userinfo_query);
        sbf.append("\n接口参数：" + requestMap);
        sbf.append("\n回调信息：" + resultXml);

        String fileName = "OfUserInfoQuery";
        String path = "/logs/jpf-charge-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        Map<String, String> resultMap = new ReadXML().getBooksOneByStr(resultXml);
        resultMap.put("requestUrl", userinfo_query);
        resultMap.put("requestParam", requestParam);
        resultMap.put("responseParam", JSONObject.fromObject(resultMap).toString());

        return resultMap;
    }

    /**
    *具体商品信息同步接口
     * */
    public Map<String,String> queryCardInfo(String cardid){

        Map<String,Object> requestMap = new LinkedHashMap<>();

        requestMap.put("userid", userid);                   // 商户号
        requestMap.put("userpws", Md5Encrypt.md5(userpws)); // 商户密码
        requestMap.put("cardid", cardid);                   // 商户密码
        requestMap.put("version", version_phone );          //版本号
        String requestParam = ToolUtils.mapToUrl(requestMap);   //请求参数

        String resultXml = OkHttpUtils.postForm(querycardinfo,requestMap);
        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n充值类型:" + "具体商品信息同步接口");
        sbf.append("\n请求地址：" + querycardinfo);
        sbf.append("\n所属商品Id：" + cardid);
        sbf.append("\n接口参数：" + requestMap);
        sbf.append("\n回调信息：" + resultXml);

        String fileName = "OfQueryCardInfo";
        String path = "/logs/jpf-charge-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        Map<String, String> resultMap = new ReadXmlByDom().getXmlByDom(resultXml);
        //Map<String, String> requestMa = MessageUtil.parseXml(request);
        resultMap.put("requestUrl", userinfo_query);
        resultMap.put("requestParam", requestParam);
        resultMap.put("responseParam", JSONObject.fromObject(resultMap).toString());

        return resultMap;
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
