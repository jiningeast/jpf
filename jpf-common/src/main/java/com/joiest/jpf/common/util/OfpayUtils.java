package com.joiest.jpf.common.util;

import com.joiest.jpf.common.constant.ManageConstants;
import net.sf.json.JSONObject;

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

    private String cardid_fast;

    private String version_phone;

    //油卡卡号查询
    private String gas_query;

    public OfpayUtils() {
        this.userid = ConfigUtil.getValue("userid");
        this.userpws = ConfigUtil.getValue("userpws");
        this.phone_requestUrl = ConfigUtil.getValue("phone_requestUrl");
        this.phone_query = ConfigUtil.getValue("phone_query");
        this.gas_requestUrl = ConfigUtil.getValue("gas_requestUrl");
        this.cardid_fast = ConfigUtil.getValue("cardid_fast");
        this.version_phone = ConfigUtil.getValue("version_phone");
        this.gas_query = ConfigUtil.getValue("gas_query");
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
    public void oilRegister(){

    }

    /**
     * 油卡-登录
     */
    public void oilLogin(){

    }

    /**
     * 油卡-绑卡
     */
    public void oilBind(){

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
        requestMap.put("userid", userid);           // 商户号
        requestMap.put("userpws", Md5Encrypt.md5(userpws));         // 商户密码
        requestMap.put("cardid", rechargeMap.get("cardid"));                            // 商品编号以产品部门提供的为准
//        requestMap.put("cardid", "64127500");                            // 商品编号以产品部门提供的为准
        requestMap.put("cardnum",rechargeMap.get("cardnum").toString());                // 1.任意充需要待充值面值（1的整数倍) 2.卡充充值这里表示数量
        requestMap.put("sporder_id", rechargeMap.get("sporder_id").toString());
        requestMap.put("sporder_time", myfmt.format(rechargeMap.get("sporder_time")));
        requestMap.put("game_userid", rechargeMap.get("game_userid").toString());       // 手机号码
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
        map.put("requestUrl", gas_requestUrl);
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
