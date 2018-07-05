package com.joiest.jpf.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1
{
    private static SHA1         instance = null;
    private static final String code     = "ALL_SCORE_INIT_CODE :";

    private SHA1()
    {

    }

    public static SHA1 getInstance()
    {
        if (instance == null)
        {
            instance = new SHA1();
        }
        return instance;
    }

    public String getMySHA1Code(final String sourceString)
    {
        try
        {
            MessageDigest alga = MessageDigest
                    .getInstance("SHA-1");
            alga.update((code + sourceString).getBytes());
            byte[] digesta = alga.digest();
            return byte2hex(digesta);
        }
        catch (NoSuchAlgorithmException e)
        {
            // do nothing
        }
        return null;
    }

    private String byte2hex(byte[] b) // 二行制转字符串
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++)
        {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static void main(String[] args)
    {
        SHA1 s = new SHA1();
        System.out.println(s.getMySHA1Code("abc123,./"));
    }

    /**
     * @author Aaron·Li
     * @date
     */
    public static class IDUtils {
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


    }
}
