package com.joiest.jpf.common.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class AESUtils {

    private static String src = "中华人民共和国";

    private static final Logger logger = LogManager.getLogger(AESUtils.class);

    public String jdkAES (String str, String SKey, String operation){
        String res = "";
        try {
            //生成Key 创建AES的Key生产者
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            // DES算法要求有一个可信任的随机数源
            //为随机数初始化出128位的key生产者
//            keyGenerator.init(128);
            keyGenerator.init(128, new SecureRandom(SKey.getBytes()));
            //使用上面这种初始化方法可以特定种子来生成密钥，这样加密后的密文是唯一固定的。
            //生成密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
            byte[] keyBytes = secretKey.getEncoded();

            //Key转换 转换为AES专用密钥
            Key key = new SecretKeySpec(keyBytes, "AES");
            //创建密码器
            Cipher cipher = Cipher.getInstance("AES");

            if (operation.equals("D")) {

                // 初始化为解密模式的密码器
                cipher.init(Cipher.DECRYPT_MODE, key);
                //解密
                byte[] str1 = str.getBytes();
                byte[] decodeResult = cipher.doFinal(str1);
                System.out.println("AESdecode : " + new String(decodeResult));
                res = new String(decodeResult);
            } else if (operation.equals("E")) {
                // 初始化为加密模式的密码器
                cipher.init(Cipher.ENCRYPT_MODE, key);
                //加密
                byte[] encodeResult = cipher.doFinal(str.getBytes());
//                System.out.println("AESencode : " + Hex.toHexString(encodeResult) );
                res = Hex.toHexString(encodeResult);
//                System.out.println("AESencode : " + Hex.toHexString(encodeResult) );
            }
        /*} catch (NoSuchAlgorithmException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }*/
        }catch (Exception e)
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), e.getMessage().toString());
        }
        return res;
    }


    public static void bcAES (){
        try {

            //使用BouncyCastle 的DES加密
            Security.addProvider(new BouncyCastleProvider());

            //生成Key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES","BC");
            keyGenerator.getProvider();
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            //Key转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            //加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encodeResult = cipher.doFinal(AESUtils.src.getBytes());
            System.out.println("AESencode : " + Hex.toHexString(encodeResult) );

            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodeResult = cipher.doFinal(encodeResult);
            System.out.println("AESdecode : " + new String (decodeResult));




        } catch (NoSuchAlgorithmException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }

    }

    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @param SKEY
     *            加密需要的密码
     * @return 密文
     */
    public static String encrypt(String content, String SKEY) {
        String str = "";
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

            kgen.init(128, new SecureRandom(SKEY.getBytes()));// 利用用户密码作为随机数初始化出
            // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            // null。

            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            str = parseByte2HexStr(result);
        } catch (Exception e)
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DECRYPT_FAIL.getCode(), JpfInterfaceErrorInfo.DECRYPT_FAIL.getDesc());
        }
        return str;
    }

    /**
     * 解密AES加密过的字符串
     *
     * @param content
     *            AES加密过过的内容
     * @param SKEY
     *            加密时的密码
     * @return 明文
     */
    public static String decrypt(String content, String SKEY) {
        String str = "";
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(SKEY.getBytes()));
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
            byte[] contentByte = parseHexStr2Byte(content);
            byte[] result = cipher.doFinal(contentByte);
            str = new String(result, "UTF-8");
        }catch (Exception e)
        {
//            logger.error("解密：" + e.toString() );
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DECRYPT_FAIL.getCode(), JpfInterfaceErrorInfo.DECRYPT_FAIL.getDesc());
        }

        return str;
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] arg){
        String abc = decrypt("D4D94AE0A068DE915D128518DD30DB711599A94844C828032FC0CD595C1828DAA66E2915F11EE1022CB9F4AC70D1D299881BCA74FAE5CE51E2FD3459662C0BEF2C5CC495546765D655944334CA301B4A32FB78177915211E5E4FF914D6E0C748","tioB8c6esX1Cx84Y16NFcFascZQZXiGI");
        System.out.print(abc);
    }

}