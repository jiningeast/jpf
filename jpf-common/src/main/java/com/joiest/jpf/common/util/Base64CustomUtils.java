package com.joiest.jpf.common.util;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * base64 encode 转换为图片
     * */
    public static Map<String,String> baseToImage(HttpServletRequest request, String imgStr, String perfix){

        Map<String,String> imgInfo = new HashMap<>();

        if(perfix == null || perfix.isEmpty()){
            perfix = "Ocr";
        }
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return null;

        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            long timeStamp = new Date().getTime();

            //生成jpeg图
            String showUrl = request.getSession().getServletContext().getRealPath("/resources");

            String resourcesUrl = "\\resources\\"+perfix+"\\";
            String urlRepj = showUrl.replace("\\target\\jpf-cloud\\resources","\\src\\main\\webapp"+resourcesUrl);

            File fileDir = new File(urlRepj);
            if (!fileDir.exists()){

                fileDir.mkdirs();
            }
            String filename = perfix+timeStamp+".jpg";
            String imgFilePath = urlRepj+"/"+filename;//新生成的图片

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            //dealImgInfo = imgInfo.replaceAll("^(data:\\s*image\\/(\\w+);base64,)", "");

            imgInfo.put("actualUrl",imgFilePath);//服务器实际路径
            imgInfo.put("filename",filename);//文件名
            imgInfo.put("resourceUrl",resourcesUrl+filename);//域名对应图片地址 如：http://xxx.com/图片存储地址

            return imgInfo;
        }catch (Exception e) {

            return null;
        }
    }

    /**
     * 图片转换为base64
     * */
    public static String imageToBase(String imgFile) {

        // 对图像进行base64编码
        String imgBase64 = null;
        try {

            File file = new File(imgFile);
            byte[] content = new byte[(int) file.length()];
            FileInputStream finputstream = new FileInputStream(file);
            finputstream.read(content);
            finputstream.close();
            imgBase64 = new String(Base64.encodeBase64(content));

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
        return imgBase64;
    }
}