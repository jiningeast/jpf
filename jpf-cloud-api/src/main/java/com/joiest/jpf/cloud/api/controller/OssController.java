package com.joiest.jpf.cloud.api.controller;


import com.aliyun.oss.OSSClient;
import com.joiest.jpf.common.util.AliyunOSSClientUtil;
import com.joiest.jpf.common.util.OSSClientConstants;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetCloudCompanyRequest;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;


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

    @RequestMapping(value = "/PassMessage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody

    public  String PassMessage( @Validated GetCloudCompanyRequest request){


       String linkemail =request.getLinkemail();
     return linkemail;
    }

   //测试接口控制器
    @RequestMapping(value = "/resetApi",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String resetApi(){
        JSONObject resultJson = new JSONObject();
        resultJson.put("code","10000");
        resultJson.put("info","SUCCESS");
        return ToolUtils.toBase64(resultJson.toString());
    }


    //测试接口发送附件给用户
   /* @RequestMapping(value = "/sendFujian",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public  String sendFujian() throws  Exception{
        //执行发送邮件
        //发送邮件
        String Subject="测试主题";
        String sendName="欣享服务";
        String Recipients="1174355934@qq.com";
        String RecipientsName="蔡磊";
        String Filepath="/home/images/excel/1531962634202.xlsx";//全路径
        String Filename="1531962634202.xlsx";//携带文件类型。xlsx
        String html="这是我发布的测试内容";//可以使用标签拼装
        Boolean a=  SendMailUtil.sendMultipleEmail(Subject,sendName,Recipients,RecipientsName,Filepath,Filename,html);
        return "dsadsad";
    }*/
}
