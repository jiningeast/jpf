package com.joiest.jpf.common.util;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class AESEBCUtils {
    private static final Logger log = LoggerFactory.getLogger(AESEBCUtils.class);

    private AESEBCUtils() {
    }

    public static String decrypt(String sSrc, String sKey) {
        String exceptionStr = "AES加密异常:";

        try {
            if(sKey == null) {
                return null;
            } else if(sKey.length() != 16) {
                return null;
            } else {
                byte[] e = sKey.getBytes("ASCII");
                SecretKeySpec skeySpec = new SecretKeySpec(e, "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
                cipher.init(2, skeySpec, iv);
                byte[] encrypted1 = Base64.decodeBase64(sSrc);
                byte[] original = cipher.doFinal(encrypted1);
                return new String(original);
            }
        } catch (Exception var9) {
            log.error(exceptionStr, var9);
            return null;
        }
    }

    public static byte[] encrypt(String content, String key, String charset) {
        if(key == null) {
            log.info("encry message {} failer:key is null", content);
            return new byte[0];
        } else if(key.length() != 16) {
            log.info("encry message {} failer:key is not enough 16 bit", content);
            return new byte[0];
        } else {
            try {
                byte[] e = key.getBytes(charset);
                SecretKeySpec skeySpec = new SecretKeySpec(e, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECBPadding");
                cipher.init(1, skeySpec);
                return cipher.doFinal(fill(content.getBytes(charset), e));
            } catch (Exception var6) {
                log.warn("encry message {} failer", content, var6);
                return new byte[0];
            }
        }
    }

    public static String decrypt(byte[] content, String key, String charset) {
        try {
            if(key == null) {
                log.info("decrypt message {} failer:key is null", content);
                return null;
            } else if(key.length() != 16) {
                log.info("decrypt message {} failer:key is not enough 16 bit", content);
                return null;
            } else {
                byte[] e = key.getBytes(charset);
                SecretKeySpec skeySpec = new SecretKeySpec(e, "AES");
                Cipher cipher = Cipher.getInstance("AES/ECBPadding");
                cipher.init(2, skeySpec);
                byte[] original = cipher.doFinal(content);
                original = trim(original);
                return new String(original, charset);
            }
        } catch (Exception var7) {
            log.error("decrypt message {} failer", content, var7);
            return null;
        }
    }

    private static byte[] trim(byte[] bytes) {
        if(bytes == null) {
            return new byte[0];
        } else {
            int bytesLength = bytes.length;

            int i;
            for(i = bytesLength - 1; i >= 0 && bytes[i] == 0; --i) {
                ;
            }

            byte[] newBytes = new byte[i + 1];
            System.arraycopy(bytes, 0, newBytes, 0, i + 1);
            return newBytes;
        }
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

    public static String encryptCbc128(String data, String key) {
        String exceptionStr = "AES 128+CBC编码加密异常:";

        try {
            char e = 0;
            StringBuilder sb = new StringBuilder(data);

            while(sb.length() % 16 != 0) {
                sb.append(e);
            }

            String dataTemp = sb.toString();
            Cipher cipher = Cipher.getInstance("AES/ ");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(key.getBytes());
            cipher.init(1, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(dataTemp.getBytes());
            return new String(Hex.encodeHex(encrypted, false));
        } catch (Exception var10) {
            log.error(exceptionStr, var10);
            return null;
        }
    }
}
