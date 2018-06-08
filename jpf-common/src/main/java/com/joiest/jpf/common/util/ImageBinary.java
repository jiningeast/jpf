package com.joiest.jpf.common.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;

public class ImageBinary {

    public static void main(String[] args) {
        /*String fileName = "D://code//test.jpg";
        System.out.println(getImageBinary(fileName));
        saveImage(getImageBinary(fileName));*/

        saveImage("iVBORw0KGgoAAAANSUhEUgAAAK4AAACuAQMAAACVwqStAAAABlBMVEX///8AAABVwtN+AAABBUlEQVRIie2WwQ3FIAxDIzFAR2J1RmIApHzblH61veNLo4rSx8VK3ISIL95xJGJE1b7xI5sJcxlHlszSYyxiwVAEgQl2NJwPL4bAXoeUmrFAuLG+lC2dhw3LyCwXn4e/t+IrlLN4xT5MgU0aqQ0mwo9lwo3bhHdq0M7hxFJXILNXajwbngHDvNCINqNUXf7ej1moCQpP2vL3fkzvhJruWcAeLqwJUNVmNJSuom3HeNPIp4vrqqUBwz6aSE3/t7qvBa/+hoVJwjhy4TUE6N+l0YS5MFu6toTs7MKzYsAl8z8vbThVrnprMyY875VlJsyEY97j5kTKWy334mlk3aFi1s2Ev3jGD/QZUglka2qZAAAAAElFTkSuQmCC");
    }

    /*
     * 图片转换为二进制
     *
     * @param fileName
     *            本地图片路径
     * @return
     *       图片二进制流
     * */
    public static String getImageBinary(String fileName) {
        File f = new File(fileName);
        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return Base64.encodeBase64String(bytes);
            //return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将二进制转换为图片
     *
     * @param base64String
     *            图片二进制流
     *
     */
    public static void saveImage(String base64String) {
        try {
            byte[] bytes1 = Base64.decodeBase64(base64String);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File("D://code//22.jpg");// 可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}