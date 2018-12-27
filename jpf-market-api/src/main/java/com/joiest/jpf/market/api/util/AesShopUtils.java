package com.joiest.jpf.market.api.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.utils.URLEncodedUtils;

public class AesShopUtils {

    private static final String AESTYPE ="AES/ECB/PKCS5Padding";

    public static String AES_Encrypt(String keyStr, String plainText) {
        byte[] encrypt = null;
        try{
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encrypt = cipher.doFinal(plainText.getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(encrypt));
    }

    public static String AES_Decrypt(String keyStr, String encryptData) {
        byte[] decrypt = null;
        try{
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance(AESTYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypt = cipher.doFinal(Base64.decodeBase64(encryptData));
        }catch(Exception e){
            e.printStackTrace();
        }
        return new String(decrypt).trim();
    }

    private static Key generateKey(String key)throws Exception{
        try{
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            return keySpec;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }

    public static void main(String[] args) {

        String keyStr = "B8c6esX1Cx84Y16N";

        String plainText = "{\"customerId\":\"71\",\"orderNo\":\"1232323\",\"money\":\"70\",\"source\":\"1\"}";

        String encText = "";
        String decString = "";
        try {
            encText = URLEncoder.encode(AES_Encrypt(keyStr, plainText),"UTF-8");
             decString = AES_Decrypt(keyStr, URLDecoder.decode(encText,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String s="86kgk8NxLMUOuz0i1UmRO67P6nOJtcaPS5tBEw3owPN8Tie99ZGHm9z+ac0uGw6C4Bm90V8n5DZgdbdnzZcbMjUwy83PcQ1arnQlqlNc7FGOtJktMKnJvo7IFT9BzWt8XBTFeA1UwM7jr4tUZNfeb7K8Ummhqfy5wIKoOhuK7r2EPepy+7uUWNSSoP1HhjGTttBAzMfiq7FcWHPKwXmwvNN3AE8wwbnTelOxgXhzb6YJ3abKdTlvpgK0zOt3+4gYiC3/SPbTabLHjYKVl0bPh7sdnUaQcf83D0z1CFT9pu9b1TI31dZQhNOrffrMEH9qojOJrYa37vjZTd7W/8paf0rqpe8sva4oW/J3ImOQazSsRXDrgKi7vJpVtwf9ymZO4GTUKUAe+5lKQfYBnqfToA==";
        System.out.println(encText);
        System.out.println(decString);

    }
}
