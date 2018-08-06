package com.joiest.jpf.cloud.api.controller;


import com.aliyun.oss.OSSClient;

import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.joiest.jpf.common.util.AliyunOSSClientUtil;
import com.joiest.jpf.common.util.OSSClientConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.io.File;
import java.net.URL;
import java.util.Date;


@Controller
@RequestMapping("/oss")
public class OssController {

    private static final Logger logger = LogManager.getLogger(OssController.class);

    /**
     * 获取用户总金额
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String upload(String path) {
        String files  = "D:/pc-320-220-mi8.png,D:/pc-320-220-mi8.png";
        String[] filex = files.split(",");
        String md5key = "";

        OSSClient ossClient= AliyunOSSClientUtil.getOSSClient();
        for(String filename:filex){
            // 上传文件流。
            File fileOne = new File(filename);
//            InputStream inputStream = new FileInputStream(fileOne);
            md5key  = AliyunOSSClientUtil.uploadObject2OSS(ossClient, fileOne, OSSClientConstants.BACKET_NAME,OSSClientConstants.FOLDER);
            // 关闭OSSClient。
          /*  System.out.println(md5key);
            System.out.println("Object：" + OSSClientConstants.BACKET_NAME + OSSClientConstants.FOLDER + "存入OSS成功。");*/

        }
        String subMd5key="";
        //截取去掉后缀时效
        if(md5key != null && md5key.length() != 0){

             subMd5key =md5key.substring(0,md5key.lastIndexOf("?"));

        }
        return subMd5key;

    }



}
