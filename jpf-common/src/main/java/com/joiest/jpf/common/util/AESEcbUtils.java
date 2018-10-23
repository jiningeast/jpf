package com.joiest.jpf.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESEcbUtils {
    private static String Padding = "AES/ECB/NoPadding";
    /**
     * 数据加密
     * @param data
     * @return
     */
    public static byte[] encrypt(byte[] data, byte[] key){
        byte[] original = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance(Padding);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            original = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return original;
    }

    /**
     * 数据解密
     * @param encData
     * @return
     */
    public static String decrypt(String encData, String key) {
        byte[] decodeBase64 = Base64.decodeBase64(encData);
        byte[] original = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(Padding);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            original = cipher.doFinal(decodeBase64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(original).trim();
    }

    public static void main(String[] args) throws Exception {
//        String str = "20171017095514800000000000000000";
//        String key = "f5663bc2165b9b50";
//        byte[] encrypt_data = encrypt(str.getBytes(), key.getBytes());
//        String s = decrypt(encrypt_data, key);
//        System.out.println("加密前： : "+str);
//        System.out.println("密文： : "+encrypt_data);
//        System.out.println("解密后：   "+s);
    }
}
