package com.joiest.jpf.market.api.util;

import java.util.Random;

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
}
