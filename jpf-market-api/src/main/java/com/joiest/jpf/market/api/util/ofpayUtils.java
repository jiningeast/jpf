package com.joiest.jpf.market.api.util;

import com.joiest.jpf.common.util.*;
import com.joiest.jpf.market.api.controller.ConfigUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ofpayUtils {
    private String userid;

    private String userpws;

    private String phone_requestUrl;

    //查询接口
    private String phone_query;

    private String oil_requestUrl;

    private String cardid_fast;

    private String version_phone;

    public ofpayUtils() {
        this.userid = ConfigUtil.getValue("userid");
        this.userpws = ConfigUtil.getValue("userpws");
        this.phone_requestUrl = ConfigUtil.getValue("phone_requestUrl");
        this.phone_query = ConfigUtil.getValue("phone_query");
        this.oil_requestUrl = ConfigUtil.getValue("oil_requestUrl");
        this.cardid_fast = ConfigUtil.getValue("cardid_fast");
        this.version_phone = ConfigUtil.getValue("version_phone");
    }

    /**
     * 根据手机号和面值查询商品信息
     * @param queryMap
     * @return
     */
    public Map<String, String> telquery(Map<String,String> queryMap){
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid", userid);       // 商户号
        requestMap.put("userpws", userpws);     // 商户密码
        requestMap.put("phoneno", queryMap.get("phoneno") );     // 商户密码
        requestMap.put("pervalue", queryMap.get("pervalue") );     // 商户密码
        requestMap.put("mctype", "" );     // 商户密码
        requestMap.put("version", version_phone );     // 商户密码

        String resultXml = OkHttpUtils.postForm(phone_query,requestMap);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n充值类型:" + "话费充值");
        sbf.append("\n请求地址：" + phone_query);
        sbf.append("\n接口参数：" + requestMap);
        sbf.append("\n回调信息：" + resultXml);

        String fileName = "ofpayPhoneQuery";
        String path = "/logs/jpf-market-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        Map<String, String> resultMap = new ReadXML().getBooksOneByStr(resultXml);
        return resultMap;
    }

    public static void main(String[] args) {
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("phoneno", "18618380116");
        queryMap.put("pervalue", "50");
        Map<String, String> resultMap = new ofpayUtils().telquery(queryMap);
        System.out.println("#333333333333");
    }
    /**
     * 手机充值
     */
    public Map<String, String> chargePhone(Map<String,Object> rechargeMap){
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyyMMddHHmmss");
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid", userid);      // 商户号
        requestMap.put("userpws", userpws);   // 商户密码
        requestMap.put("cardid", cardid_fast);      // 快充140101，慢充170101
        requestMap.put("cardnum",rechargeMap.get("cardnum").toString());         // 充值金额
        // requestMap.put("mctype","48");          // 如果是慢充商品必须传如48 表示48小时到账
        requestMap.put("sporder_id", rechargeMap.get("sporder_id").toString());
        requestMap.put("sporder_time", myfmt.format(rechargeMap.get("sporder_time")));
        requestMap.put("game_userid", rechargeMap.get("game_userid").toString());       // 手机号码
        requestMap.put("md5_str", getPhoneSign(requestMap));       // 签名串
//        requestMap.put("ret_url", );    // 返回地址
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

        String fileName = "ofpayPhone";
        String path = "/logs/jpf-market-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        Map<String,String> map = new ReadXML().getBooksOneByStr(resultXml);
        map.put("requestUrl", phone_requestUrl);
        map.put("requestParam", requestParam);
        return map;
    }

    /**
     * 获取手机充值签名
     */
    private String getPhoneSign(Map<String,Object> map){
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("cardid") + map.get("cardnum") + map.get("sporder_id") + map.get("sporder_time") + map.get("game_userid") + "OFCARD";
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }

    /**
     * 获取油卡充值签名
     */
    public String getOilSign(Map<String,Object> map){
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("cardid") + map.get("cardnum") + map.get("sporder_id") + map.get("sporder_time") + map.get("game_userid") + "OFCARD";
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }

}
