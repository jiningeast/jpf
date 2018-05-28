package com.joiest.jpf.common.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 自定义函数工具类
 */
public class ToolUtils {

    public static String toJsonBase64(String code, String info, String data )
    {
        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("code",code );
        responseMap.put("info",info);
        if ( data != null )
        {
            responseMap.put("data",data);
        }
        String jsonStr = JsonUtils.toJson(responseMap);
        return Base64CustomUtils.base64Encoder(jsonStr);
    }

    //数组转换为键值
    public static String signData(Map<String,Object> map){

        String result="";
        Set<String> keys = map.keySet();

        for(String key :keys){

            result +=key+"="+map.get(key)+"&";
        }
        return result.substring(0,result.length()-1);
    }
}
