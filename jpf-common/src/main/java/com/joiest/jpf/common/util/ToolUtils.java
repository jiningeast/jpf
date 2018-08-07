package com.joiest.jpf.common.util;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
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
    public static String toJsonBase64(String code, String info, Object data )
    {
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("code",code );
        responseMap.put("info",info);
        if ( data != null )
        {
            responseMap.put("data",data);
        }
        String jsonStr = JsonUtils.toJson(responseMap).replaceAll("\\\\","");
        String base64Str = Base64CustomUtils.base64Encoder(jsonStr);
        base64Str = base64Str.replaceAll("\r","");
        base64Str = base64Str.replaceAll("\n","");
        return base64Str;

    }

    /**
     * base64编码&返回
     * @param jsonStr
     * @return
     */
    public static String toBase64(String jsonStr)
    {
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

    /**
     * 获取月份第的一天 2018-07-01 2018-07-31
     * @param year
     * @param month
     * @return
     */
    public static Map<String,String> getMonthStartAndEnd(int year, int month)
    {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,String> map = new HashMap<>();

        //获取指定年月第一天
        Calendar calstar= Calendar.getInstance();

        calstar.set(Calendar.YEAR, year);

        calstar.set(Calendar.MONTH, month-1);

        calstar.set(Calendar.DAY_OF_MONTH, 1);

        String start = fmt.format(calstar.getTime());

        map.put("start", start);

        calstar.set(Calendar.MONTH, month);

        calstar.set(Calendar.DAY_OF_MONTH, 0);//最后一天
        System.out.println(calstar.getTime());
        String end = fmt.format(calstar.getTime());

        map.put("end", end);

        return map;
    }

    /**
     * 获取月份第一天和最后一天 2018-07-01 00:00:00  2018-07-31 23:59:59
     * @param year
     * @param month
     * @return
     */
    public static Map<String,String> getMonthFirstAndEndSenond(int year, int month)
    {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);

        calendar.set(Calendar.MONTH, month-1);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND,0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        //获得当前月第一天
        Date sdate = calendar.getTime();
        //将当前月加1；
        calendar.add(Calendar.MONTH, 1);
        //在当前月的下一月基础上减去1毫秒
        calendar.add(Calendar.MILLISECOND, -1);
        //获得当前月最后一天
        Date edate = calendar.getTime();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String start = fmt.format(sdate.getTime());
        String end = fmt.format(edate.getTime());
        Map<String,String> map = new HashMap<>();
        map.put("start", start);
        map.put("end", end);
        return map;
    }

    /**
     * 从文件中读取所有内容
     */
    public static String readFromFile(String fileName, String encoding) {
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }



    /**
     * @商户编号生成
     */
    private static byte[] lock = new byte[0];

    // 位数，默认是6位
    private final static long w = 1000000;

    public static String createID() {
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
        }
        long timeStampSec = System.currentTimeMillis()/1000;
        String timestamp = String.format("%010d", timeStampSec);

        return  timestamp + String.valueOf(r).substring(1);
    }

    /**
     * @生成六位随机字母加数字
     */
    public static  String CreatSixNum(){
        String randomcode = "";
        // 用字符数组的方式随机
        String model = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] m = model.toCharArray();
        for (int j = 0; j < 6; j++) {
            char c = m[(int) (Math.random() * 36)];
            // 保证六位随机数之间没有重复的
            if (randomcode.contains(String.valueOf(c))) {
                j--;
                continue;
            }
            randomcode = randomcode + c;
        }
        return randomcode;
    }

    /**
     * 生成随机数
     * @return
     */
    public static String createOrderid(){

        int pre = getRandomInt(100,999);
        int last = getRandomInt(100,999);
        String middle = String.valueOf(System.currentTimeMillis());
        middle = middle.substring(3,middle.length());

        return ""+pre+middle+last;
    }

    /*
     *   生成指定范围内的随机整数
     **/
    public static int getRandomInt(int min, int max){

        Random random = new Random();
        int randomInt = random.nextInt(max)%(max-min+1) + min;

        return randomInt;
    }
    /**
     * 获取ip地址
     * */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) return "";

        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 生成固定长度字符串
     * str 开始内容
     * last 结尾内容
     * lenNum 长度
     */
    public static String createDfOrderid(String str,String last,int lenNum){

        String middle = "";//中间填充信息
        String  pre = str + last;
        if( pre.length() > lenNum ){
            return pre;
        }else{
            for (int i = 0; i <lenNum-pre.length() ; i++) {
                middle += "0";
            }
        }

        return str+middle+last;
    }

    /**
     * 保留小数点后N位小数，第n+1位四舍五入
     */
    public static Double halfUpDouble(Double num, int n){
        NumberFormat nf = NumberFormat.getInstance();
        nf.setRoundingMode(RoundingMode.HALF_UP);   //设置四舍五入
        nf.setMinimumFractionDigits(n); //设置最小保留几位小数
        nf.setMaximumFractionDigits(n); //设置最大保留几位小数
        nf.setGroupingUsed(false);

        return Double.parseDouble(nf.format(num));
    }
    /**
     * 金额校验
     */
    public static Boolean ValidateCode(String code,String uid,String dou){

        String newCode = Md5Encrypt.md5(uid+dou+"test","UTF-8");   //加密规则：  id+金额+key
        Boolean res = code.equals(newCode); //校验加密是否一致

        return res;
    }
    /**
     * 加密code
     */
    public static String CreateCode(String dou,String uid){

        return Md5Encrypt.md5(uid+dou+"test","UTF-8");

    }
}
