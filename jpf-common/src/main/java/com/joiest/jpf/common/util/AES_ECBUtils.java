package com.joiest.jpf.common.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES/ECB/NoPadding  加密
 * @author jia
 */
public class AES_ECBUtils {

    private static String hexStr = "0123456789ABCDEF";
    private static String Padding = "AES/ECB/NoPadding";

    /**
     * 数据加密
     * @param data
     * @return
     */
    public static byte[] encrypt(String data, byte[] key){
        byte[] original = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance(Padding);
            cipher.init(1, skeySpec);
            original = cipher.doFinal(fill(data.getBytes("utf-8"),key));
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
    public static String decrypt(byte[] encData, String key) {
//        byte[] decData = HexStringToBinary(encData);
        byte[] original = null;
        String decryptedStr = "";
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("utf-8"), "AES");
            Cipher cipher = Cipher.getInstance(Padding);
            cipher.init(2, skeySpec);
            original = cipher.doFinal(encData);
            decryptedStr = new String(original,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedStr;
    }

    /**
     *
     * @param bytes
     * @return 将二进制转换为十六进制字符输出
     */
    public static String BinaryToHexString(byte[] bytes) {

        StringBuilder result = new StringBuilder();
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            hex.delete(0, hex.length());
            // 字节高4位
            hex.append(String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4)));
            System.out.println(i+"-----------------"+String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4)));
            // 字节低4位
            hex.append(String.valueOf(hexStr.charAt(bytes[i] & 0x0F)));
            System.out.println(i+"-----------------"+String.valueOf(hexStr.charAt(bytes[i] & 0x0F)));
            result.append(hex);

        }
        return result.toString();
    }

    /**
     *
     * @param hexString
     * @return 将十六进制转换为字节数组
     */
    public static byte[] HexStringToBinary(String hexString) {
        // hexString的长度对2取整，作为bytes的长度
        int len = hexString.length() / 2;
        byte[] bytes = new byte[len];
        byte high = 0;// 字节高四位
        byte low = 0;// 字节低四位

        for (int i = 0; i < len; i++) {
            // 右移四位得到高位
            high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);
            low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));
            bytes[i] = (byte) (high | low);// 高地位做或运算
        }
        return bytes;
    }

    private static byte[] fill(byte[] bytes, byte[] key) {
        if(bytes == null) {
            return new byte[0];
        } else {
            int bytesLength = bytes.length;
            int factor = key.length;
            if(bytesLength % factor == 0) {
                return bytes;
            } else {
                int newBytesLength = (bytesLength / factor + 1) * factor;
                byte[] newBytes = new byte[newBytesLength];
                System.arraycopy(bytes, 0, newBytes, 0, bytesLength);

                for(int k = bytesLength; k < newBytesLength; ++k) {
                    newBytes[k] = 0;
                }

                return newBytes;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String str = "01234567891234";
        String key = "f5663bc2165b9b50";
        byte[] encrypt_data = encrypt(str, key.getBytes());
        String encrypt_data_str = Base64.encodeBase64String(encrypt_data);
        byte[] encrypt_data_decode = Base64.decodeBase64(encrypt_data_str);
        String s = decrypt(encrypt_data_decode, key);
        System.out.println("加密前： : "+str);
        System.out.println("加密内容长度： : "+str.length());
        System.out.println("密文： : "+encrypt_data_str);
        System.out.println("解密后：   "+s.trim());
    }
}