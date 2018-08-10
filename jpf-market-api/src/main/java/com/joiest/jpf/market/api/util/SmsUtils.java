package com.joiest.jpf.market.api.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.util.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SmsUtils {

    public static Map<String,String> send(String mobile,String content,String logFileName){

        //发送短信
        Date date = new Date();
        String dateTime = date.toString();
        Map<String,Object> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("content",content);
        map.put("dateTime",dateTime);

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);


        String selfSign = Md5Encrypt.md5(respos+ ConfigUtil.getValue("API_SECRET")).toUpperCase();

        map.put("sign",selfSign);

        String requestUrl = ConfigUtil.getValue("CLOUD_API_URL")+"/toolcate/sendSmsApi";//请求Url
        String requestParam = ToolUtils.mapToUrl(map);//请求参数

        String response = OkHttpUtils.postForm(ConfigUtil.getValue("CLOUD_API_URL")+"/toolcate/sendSmsApi",map);

        //json---转换代码---
        //Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>() {});

        Map<String,String> responParan  = new HashMap<>();
        responParan.put("requestUrl",requestUrl); //请求的地址
        responParan.put("requestParam",requestParam); //请求的参数
        responParan.put("response",response);//返回的数据

        //存储日志记录
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder logContent = new StringBuilder();
        String logPath = "/logs/jpf-market-api/log/";
        String fileName = logFileName;
        logContent.append("\n\nTime:" + myfmt.format(date));
        logContent.append("\nrequestUrl:" + requestUrl);
        logContent.append("\nrequestParam:" + requestParam);
        logContent.append("\n返回数据:" + response);

        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);
        //String result=responseMap.get("code");
        //返回值解密进行json转换
        //if(!result.equals("10000")){//返回值为0，代表成功5
        return responParan;

    }

}
