package com.joiest.jpf.market.api.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToolsUtils {

    /**
     * 生成一个唯一数
     * */
    public static String createOrderid(){

        int pre = getRandomInt(100,999);
        int last = getRandomInt(100,999);
        String middle = String.valueOf(System.currentTimeMillis());

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

    /*
    * 手机号格式验证
     */
    public static boolean verifyPhone(String phone) {
        String regex = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                return false;
            }
            return isMatch;
        }

    }

}
