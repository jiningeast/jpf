package com.joiest.jpf.market.api.util;

import com.joiest.jpf.common.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class WnpayUtils {

    /**
     * 微能状态码描述
     *0：成功
     *-1：账号、密码错误
     *-2：手机号码为空或者手机号码错误
     *-3：认证错误
     *-4：产品不存在
     *-5：IP限制
     *-6：余额不足
     *-7：服务商网关能力不足或余额不足
     *-8：
     *-9：接口暂停服务
     *-100：其它异常
    */
    private String account;
    private String password;
    private String url;
    private String md5Password;

    public WnpayUtils(String account,String password,String url){

        this.account = account;
        this.password = password;
        this.url = url;
        this.md5Password = Md5Encrypt.md5(password,"utf-8");
    }
    /**
     * 获取供应商产品信息
    * @param carrier 运营商  移动:cmcc，联通:cucc，电信:ctc，为空返回所有运营商的套餐
    * */
    public String getProduct(String carrier){


        JSONObject responseParam = new JSONObject();
        JSONObject signParam = new JSONObject();

        String curDate = DateUtils.getDate2YmdhmsString(new Date());
        signParam.put("account",this.account);
        //signParam.put("md5Pass", Md5Encrypt.md5(this.password,"utf-8"));
        signParam.put("timestamp", curDate);
        signParam.put("carrier", carrier);

        String sign = null;
        if (StringUtils.isNotBlank(carrier))
            sign = sign = Md5Encrypt.md5(this.account+this.md5Password+curDate+carrier,"utf-8");
        else
            sign = sign = Md5Encrypt.md5(this.account+this.md5Password+curDate,"utf-8");

        signParam.put("sign",sign);
        String resposePa = OkHttpUtils.postJson(this.url+"flowProduct",signParam.toString());
        JSONObject product = JSONObject.fromObject(resposePa);

        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n请求地址：" + this.url+"flowProduct");
        sbf.append("\n请求参数：" + signParam.toString());
        sbf.append("\n响应状态：" + product.get("status"));
        sbf.append("\n响应信息：" + product.get("message"));
        sbf.append("\n响应参数：" + product.toString());
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-market-api/log/", "WnApi",true);

        if(product.get("status").toString().equals("0")){

            responseParam.put("code","10000");
            responseParam.put("info",product.get("message"));
            responseParam.put("data",product.get("data"));

        }else{

            responseParam.put("code","10008");
            responseParam.put("info",product.get("message"));
            responseParam.put("data",product);
        }
        return responseParam.toString();
    }

    /**
     * 微能充值接口
     * */
    public String flowOrder(JSONObject param){

        JSONObject responseParam = new JSONObject();

        String curDate = DateUtils.getDate2YmdhmsString(new Date());
        JSONObject requestParam = new JSONObject();
        requestParam.put("account",this.account);
        requestParam.put("timestamp",curDate);
        requestParam.put("mobile",param.get("mobile"));
        requestParam.put("productId",param.get("productId"));
        requestParam.put("outOrderId",param.get("outOrderId"));
        String sign = Md5Encrypt.md5(this.account+this.md5Password+curDate+param.get("mobile")+param.get("productId")+param.get("outOrderId"),"utf-8");
        requestParam.put("sign",sign);

        String resposePa = "{\"data\":\"14737109\",\"status\":\"0\",\"message\":\"成功\"}";
        //上线需打开
        //String resposePa = OkHttpUtils.postJson(this.url+"flowOrder",requestParam.toString());
        JSONObject flowOrder = JSONObject.fromObject(resposePa);

        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n请求地址：" + this.url+"flowOrder");
        sbf.append("\n请求参数：" + requestParam.toString());
        sbf.append("\n响应状态：" + flowOrder.get("status"));
        sbf.append("\n响应信息：" + flowOrder.get("message"));
        sbf.append("\n响应参数：" + flowOrder.toString());
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-market-api/log/", "WnApi",true);

        JSONObject actuParam = new JSONObject();
        actuParam.put("requestParam",requestParam.toString());
        actuParam.put("requestUrl",this.url+"flowOrder");
        actuParam.put("responseParam",resposePa);
        actuParam.put("wnorderid",flowOrder.get("data"));

        if(flowOrder.get("status").toString().equals("0")){

            responseParam.put("code","10000");
            responseParam.put("info",flowOrder.get("message"));
            responseParam.put("data",actuParam);
        }else{

            responseParam.put("code","10008");
            responseParam.put("info",flowOrder.get("message"));
            responseParam.put("data",actuParam);
        }
        return responseParam.toString();
    }
    /**
     *查询余额接口
     */
    public String flowBalance(){

        JSONObject responseParam = new JSONObject();

        String curDate = DateUtils.getDate2YmdhmsString(new Date());
        JSONObject requestParam = new JSONObject();
        requestParam.put("account",this.account);
        requestParam.put("timestamp",curDate);

        String sign = Md5Encrypt.md5(this.account+this.md5Password+curDate,"utf-8");
        requestParam.put("sign",sign);

        String resposePa = OkHttpUtils.postJson(this.url+"flowBalance",requestParam.toString());
        JSONObject balance = JSONObject.fromObject(resposePa);

        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n请求地址：" + this.url+"flowBalance");
        sbf.append("\n请求参数：" + requestParam.toString());
        sbf.append("\n响应状态：" + balance.get("status"));
        sbf.append("\n响应信息：" + balance.get("message"));
        sbf.append("\n响应参数：" + balance.toString());
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-market-api/log/", "WnApi",true);

        if(balance.get("status").toString().equals("0")){

            responseParam.put("code","10000");
            responseParam.put("info",balance.get("message"));
            responseParam.put("data",balance);
        }else{

            responseParam.put("code","10008");
            responseParam.put("info",balance.get("message"));
            responseParam.put("data",balance);
        }
        return responseParam.toString();
    }
    /**
     *状态报告获取接口
     * */
    public String flowReport(){

        JSONObject responseParam = new JSONObject();
        responseParam.put("code","10008");
        responseParam.put("info","ERROR");

        String curDate = DateUtils.getDate2YmdhmsString(new Date());
        JSONObject requestParam = new JSONObject();
        requestParam.put("account",this.account);
        requestParam.put("timestamp",curDate);

        String sign = Md5Encrypt.md5(this.account+this.md5Password+curDate,"utf-8");
        requestParam.put("sign",sign);

        //String resposePa = "[{\"id\":14737109,\"outOrderId\":\"1801538029705317286\",\"dest\":\"17600067853\",\"reportStatus\":1,\"reportDetail\":\"购买成功\"},{\"id\":14737109,\"outOrderId\":\"1021538028887285722\",\"dest\":\"17600067853\",\"reportStatus\":2,\"reportDetail\":\"购买失败\"}]";
        String resposePa = OkHttpUtils.postJson(this.url+"flowReport",requestParam.toString());

        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n请求地址：" + this.url+"flowReport");
        sbf.append("\n请求参数：" + requestParam.toString());
        sbf.append("\n响应参数：" + resposePa);
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-market-api/log/", "WnApi",true);

        Object json = new JSONTokener(resposePa).nextValue();

        if(json instanceof JSONObject){

            JSONObject jsonObject = (JSONObject)json;
            responseParam.put("info","无可查询订单");
            return responseParam.toString();
        }else if (json instanceof JSONArray){

            JSONArray jsonArray = (JSONArray)json;
            responseParam.put("code","10000");
            responseParam.put("info","SUCCESS");
            responseParam.put("data",jsonArray);
            return responseParam.toString();
        }else{
            return null;
        }
    }
}
