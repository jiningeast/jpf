package com.joiest.jpf.manage.web.util;

import com.joiest.jpf.common.util.*;
import com.joiest.jpf.manage.web.constant.ManageConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ServicePayUtils {


    /**
     * companyMoneyId 批次表主键ID
     * dfIds 以逗号分隔的代付明细主键ID 例：1,2
     */
    public static Map<String,String> waitPay(String companyMoneyId,String dfIds){

        //调用代付接口
        Date date = new Date();
        String dateTime = date.toString();
        Map<String,Object> map = new HashMap<>();
        map.put("batchid",companyMoneyId);
        map.put("dfid",dfIds);
        String cloudWaitpayKeycode = ManageConstants.ClOUD_WAITPAY_KEYCODE; //校验码keycode
        //String requestUrl = ManageConstants.ClOUD_WAITPAY_URl; //请求地址
        String requestUrl = ConfigUtil.getValue("CLOUD_API_URL")+"/clouddf/dfApi";//请求地址

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        String respos = ToolUtils.mapToUrl(treeMap);
        String sign = Md5Encrypt.md5(respos+cloudWaitpayKeycode);
        map.put("sign",sign);

        String requestParam = ToolUtils.mapToUrl(map);//请求参数
        //请求接口
        String response = OkHttpUtils.postForm(requestUrl,map); //base64加密数据
        response = response.replace("\"","");
        response = Base64CustomUtils.base64Decoder(response);//解密后json串
        //json---转换代码---
        //Map<String,Object> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, Object>>() {});
        //JSONObject responseMap = JSONObject.fromObject(response);

        //返回数据重新放到map中
        Map<String,String> responParam  = new HashMap<>();
        responParam.put("requestUrl",requestUrl); //请求的地址
        responParam.put("requestParam",requestParam); //请求的参数
        responParam.put("response",response);//返回的数据

        //存储日志记录
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder logContent = new StringBuilder();
        String logPath = "/logs/jpf-manage-web/log/";
        String fileName = "dfMoneyInterfaceWaitPay";
        logContent.append("\n\nTime:" + myfmt.format(date));
        logContent.append("\n接口请求url:" + requestUrl);
        logContent.append("\n接口请求param:" + requestParam);
        logContent.append("\n接口返回信息:" + response);

        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

        return responParam;

    }

    /**
     * 查询代付明细是否成功
     */
    public static Map<String,String> searchPay(String orderId){

        //调用代付接口
        Date date = new Date();
        String dateTime = date.toString();
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);

        String cloudWaitpayKeycode = ManageConstants.ClOUD_WAITPAY_KEYCODE; //校验码keycode
        //String requestUrl = ManageConstants.ClOUD_WAITPAY_URl; //请求地址
        String requestUrl = ConfigUtil.getValue("CLOUD_API_URL")+"/clouddf/dfSelectApi";//请求地址

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        String respos = ToolUtils.mapToUrl(treeMap);
        String sign = Md5Encrypt.md5(respos+cloudWaitpayKeycode);
        map.put("sign",sign);

        String requestParam = ToolUtils.mapToUrl(map);//请求参数
        //请求接口
        String response = OkHttpUtils.postForm(requestUrl,map); //base64加密数据
        response = response.replace("\"","");
        response = Base64CustomUtils.base64Decoder(response);//解密后json串
        //json---转换代码---
        //Map<String,Object> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, Object>>() {});
        //JSONObject responseMap = JSONObject.fromObject(response);

        //返回数据重新放到map中
        Map<String,String> responParam  = new HashMap<>();
        responParam.put("requestUrl",requestUrl); //请求的地址
        responParam.put("requestParam",requestParam); //请求的参数
        responParam.put("response",response);//返回的数据

        //存储日志记录
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder logContent = new StringBuilder();
        String logPath = "/logs/jpf-manage-web/log/";
        String fileName = "dfMoneyInterfaceWaitPaySearch";
        logContent.append("\n\nTime:" + myfmt.format(date));
        logContent.append("\n接口请求url:" + requestUrl);
        logContent.append("\n接口请求param:" + requestParam);
        logContent.append("\n接口返回信息:" + response);

        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

        return responParam;

    }

}
