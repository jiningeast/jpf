package com.joiest.jpf.manage.web.util;

import java.security.*;

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
        System.out.println(s.getMySHA1Code("111111"));
    }
}
