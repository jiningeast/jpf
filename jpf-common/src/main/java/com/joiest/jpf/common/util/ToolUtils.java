package com.joiest.jpf.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义函数工具类
 */
public class ToolUtils {

    /**
     * 将异常以json并base64的方式返回
     * @param code 异常码
     * @param info 异常信息
     * @param data 返回数据
     * @return base64加密串
     */
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
        String base64Str = Base64CustomUtils.base64Encoder(jsonStr);
        base64Str = base64Str.replaceAll("\r","");
        base64Str = base64Str.replaceAll("\n","");
        return base64Str;
    }

    /**
     * 将url中的参数转换成map
     * 场景：aaa=AAA&bbb=BBB&ccc=CCC 这样的请求参数转换成Map
     * @param urlparam 带分隔的url参数
     * @return map
     */
    public static Map<String,String> urlToMap(String urlparam){
        Map<String,String> map = new HashMap<String,String>();
        String[] param =  urlparam.split("&");
        for(String keyvalue:param){
            String[] pair = keyvalue.split("=");
            if(pair.length==2){
                map.put(pair[0], pair[1]);
            }
        }
        return map;
    }

}
