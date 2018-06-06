package com.joiest.jpf.common.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    //数组转换为键值
    public static String mapToUrl(Map<String,Object> map){

        String result="";
        Set<String> keys = map.keySet();

        for(String key :keys){

            result += key + "=" + map.get(key) + "&";
        }
        return result.substring(0,result.length()-1);
    }

    /**
     * 过滤 script style html 标签
     */
    public static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签

        return htmlStr.trim(); //返回文本字符串
    }

    /**
     * urlEncode后把转码字符替换成小写
     */
    public static String urlEncodeSmallCase(String url){
        String urlEncoded = null;
        try{
            urlEncoded = URLEncoder.encode(url,"UTF-8");
        }catch (Exception e){

        }
        /*urlEncoded = urlEncoded.replaceAll("%2A","%2a");
        urlEncoded = urlEncoded.replaceAll("%2B","%2b");
        urlEncoded = urlEncoded.replaceAll("%2C","%2c");
        urlEncoded = urlEncoded.replaceAll("%2E","%2e");
        urlEncoded = urlEncoded.replaceAll("%2F","%2f");
        urlEncoded = urlEncoded.replaceAll("%3A","%3a");
        urlEncoded = urlEncoded.replaceAll("%3B","%3b");
        urlEncoded = urlEncoded.replaceAll("%3D","%3d");
        urlEncoded = urlEncoded.replaceAll("%3F","%3f");
        urlEncoded = urlEncoded.replaceAll("%5B","%5b");
        urlEncoded = urlEncoded.replaceAll("%5C","%5c");
        urlEncoded = urlEncoded.replaceAll("%5D","%5d");*/

        return urlEncoded;
    }
}
