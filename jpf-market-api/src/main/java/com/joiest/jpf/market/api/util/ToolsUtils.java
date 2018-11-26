package com.joiest.jpf.market.api.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

    public static boolean isInterger(String str) {

        try {
            //把字符串强制转换为数字
            int num=Integer.valueOf(str);
            //如果是数字，返回True
            return true;
        } catch (Exception e) {
            //如果抛出异常，返回False
            return false;
        }

    }

    /**
     * 检测订单是否过期方法
     * (一个检测订单是否超过24小时的方法,返回false为过期，true为未过期)
     * @param startTime 下单时间
     * @param endTime 当前时间
     * @return
     */
    public static boolean checkOrderValidition(Date startTime, Date endTime){
        long time = endTime.getTime() - startTime.getTime();
//        if(time < 0){
//            return false;
//        }
        double result = time * 1.0 /(1000 * 60 * 60);
        if(result <= 24){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 获取当前时间24小时之前的时间点(格式化输出)
     * @param hour 小时数
     * @return 格式化字符串类型
     */
    public static String getBeforeHourTimeReturnString(int hour){
        String returnstr = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        returnstr = df.format(calendar.getTime());
        return returnstr;
    }

    /**
     * 获取当前时间24小时之前的时间点(格式化输出)
     * @param hour 小时数
     * @return 时间类型
     */
    public static Date getBeforeHourTimeReturnDate(int hour){
        String returnstr = "";
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        return calendar.getTime();
    }
    
    public static void main(String[] args) {
//        String beforeHourTime = getBeforeHourTimeReturnString(24);
//        Date beforeHourTimeReturnDate = getBeforeHourTimeReturnDate(23);
//        System.out.println("24小时前执行的时间是：" + beforeHourTime);
//        System.out.println("23小时前执行的时间是：" + beforeHourTimeReturnDate);
    }
}
