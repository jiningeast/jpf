package com.joiest.jpf.common.util;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.IOException;

public class Base64CustomUtils {

    public static String base64Encoder(String str){
        String result = "";
        try
        {
            BASE64Encoder encode = new BASE64Encoder();
            result = encode.encode(str.getBytes("UTF-8"));

        }catch (Exception e)
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "转码失败");
        }
        return result;
    }

    public static String base64Decoder(String str){

        BASE64Decoder decode = new BASE64Decoder();
        String result = "";
        byte[] b;
        try {
            b = decode.decodeBuffer(str);
            result = new String(b,"UTF-8");
        } catch (IOException e) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "转码失败");
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "eyJzaWRlIjoiZmFjZSIsImFkZHJlc3MiOiLljJfkuqzluILlu7bluobljr/lu7bluobplYfopb/ovpvluoTmnZExMzflj7cy6ZeoIiwibmF0aW9uYWxpdHkiOiLmsYkiLCJmcmVxdWVzdF9pZCI6IjIwMTgwNjI5MTAxNTA0XzJlMGU4ZDkwMzk2OGQ3OTk4YWJiY2U2NDI0Nzg5ZmYzIiwicmVzb3VyY2VVcmwiOiJodHRwOi8vMTAuMTAuMTguMTc6ODA4MC9jbG91ZC1hcGlcXHJlc291cmNlc1xcT0NSXFxPQ1IxNTMwMjM4NTAwMjk2LmpwZyIsIm51bSI6IjExMDIyOTE5ODcwOTIzMDAzNyIsInNleCI6IueUtyIsIm5hbWUiOiLlt6bmmajlhpsiLCJiaXJ0aCI6IjE5ODcwOTIzIn0=";
        String abc = Base64CustomUtils.base64Decoder(str);
        System.out.printf(abc);
        System.out.printf(abc);
    }
}