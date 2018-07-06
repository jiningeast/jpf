package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.ExcelDealUtils;
import com.joiest.jpf.cloud.api.util.MwSmsUtils;
import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.entity.CloudIdcardInfo;
import com.joiest.jpf.facade.CloudIdcardServiceFacade;
import com.joiest.jpf.facade.ToolCateServiceFacade;
import com.joiest.jpf.facade.impl.RedisCustomServiceFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("toolcate")
public class ToolCateController {

    @Autowired
    private ToolCateServiceFacade toolCateServiceFacade;

    @Autowired
    private CloudIdcardServiceFacade cloudIdcardServiceFacade;

    @Autowired
    private RedisCustomServiceFacadeImpl redisCustomServiceFacade;

    /**
     * 身份证OCR识别
     * */
    @RequestMapping(value = "/idCardOcr", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idCardOcr(HttpServletRequest request) throws IOException {

        String side = request.getParameter("side");
        String imgInfo = request.getParameter("img");

        if(side == null || imgInfo==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error",null);
        }
        String ocrRes = toolCateServiceFacade.idCardOcr(request,side,imgInfo);
        String baseRe = Base64CustomUtils.base64Encoder(ocrRes);
        baseRe = baseRe.replaceAll("\r\n","");

        return baseRe;
    }
    /**
     * 身份证分析入库
     * */
    @RequestMapping(value = "/idCardAnaly", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idCardAnaly(HttpServletRequest request) throws IOException {

        String face = request.getParameter("face");
        String back = request.getParameter("back");
        String type = request.getParameter("type");
        //参数是否为空
        if(face == null || face.isEmpty() || back == null || back.isEmpty()){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error", null);
        }
        String faceBase = Base64CustomUtils.base64Decoder(face);
        String backBase = Base64CustomUtils.base64Decoder(back);
        type = Base64CustomUtils.base64Decoder(type);

        JSONObject faceResult = JSONObject.fromObject(faceBase);
        JSONObject backResult = JSONObject.fromObject(backBase);

        //实名验证
       JSONObject cardAuth = toolCateServiceFacade.idenAuth(faceResult.get("name").toString(),faceResult.get("num").toString());
       if(cardAuth.get("code").equals("10008")){

           return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), cardAuth.get("info").toString(),null);
       }
        CloudIdcardInfo cloudIdcardInfo=cloudIdcardServiceFacade.getCloudIdcardByCardNo(faceResult.get("num").toString());

        YjResponseDto yjResponseDto= new YjResponseDto();
        if(cloudIdcardInfo == null){

            int idCard= cloudIdcardServiceFacade.addCloudIdcard(faceResult,backResult,new Byte(type));
            if(idCard > 0){

                Map<String,Object> map = new HashMap<>();
                map.put("id",idCard);

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "身份证信息上传成功",map);

            }else{

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "身份证信息上传失败",null);
            }
        }else{

            Map<String,Object> map = new HashMap<>();
            map.put("id",cloudIdcardInfo.getId());

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "身份证信息上传成功",map);
        }
    }
    /*
    * 身份证号、姓名实名认证
    * */
    @RequestMapping(value = "/idenAuth", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idenAuth(HttpServletRequest request) {

        String name = request.getParameter("name");
        String idCard = request.getParameter("idCard");

        JSONObject cardAuth = toolCateServiceFacade.idenAuth(name,idCard);

        String baseRe = Base64CustomUtils.base64Encoder(cardAuth.toString());
        baseRe = baseRe.replaceAll("\r\n","");

        return baseRe;
    }
    /**
     * 短信发送
     * */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendSms(HttpServletRequest request) throws IOException {

        String phone = request.getParameter("mobile");
        phone = Base64CustomUtils.base64Decoder(phone);
        try{

            int verificateCode = toolCateServiceFacade.getRandomInt(100000,999999);//短信内容

            redisCustomServiceFacade.set(ConfigUtil.getValue("CLOUD_USER_AUTH")+phone,new Integer(verificateCode).toString(),Long.parseLong(ConfigUtil.getValue("CLOUD_USER_AUTH_EXPIRE")));
            String content = null;
            content = "尊敬的用户，您此次的手机验证码是："+verificateCode+",10十分钟内有效";

            //int result = toolCateServiceFacade.sendSms(phone, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。
            int result = new MwSmsUtils().sendSms(phone, content);//toolCateServiceFacade.sendSms(mobile, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。
            if(result==0){//返回值为0，代表成功

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "短信发送成功",null);
            }else{//返回值为非0，代表失败

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "短信发送失败",null);
            }
        }catch (Exception e) {

            e.printStackTrace();//异常处理
        }
        return null;
    }

    /**
     * 短信发送接口
     * */
    @RequestMapping(value = "/sendSmsApi", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String sendSmsApi(HttpServletRequest request) throws IOException {

        String mobile = request.getParameter("mobile");
        String content = request.getParameter("content");
        String dateTime = request.getParameter("dateTime");
        String sign = request.getParameter("sign");

        if(mobile == null || content==null || dateTime==null || sign ==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error",null);
        }
        Map<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("content",content);
        map.put("dateTime",dateTime);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();

        JSONObject json = new JSONObject();
        if(!selfSign.equals(sign)){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","签名有误");
        }
        try{

            int result = new MwSmsUtils().sendSms(mobile, content);//toolCateServiceFacade.sendSms(mobile, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。
            if(result==0){//返回值为0，代表成功

                json.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
                json.put("info","短信发送成功");
            }else{//返回值为非0，代表失败

                json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
                json.put("info","短信发送失败");
            }
        }catch (Exception e) {

            e.printStackTrace();//异常处理
        }
        return null;
    }


    /**
     * excel上传 获取excel表中数据
     * 普通form提交
     * */
    @RequestMapping(value = "/uploadEcelByForm", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String uploadEcelByForm(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws Exception {

        JSONObject jsonRes = new JSONObject();
        InputStream in = null;
        Map<Object,Object> rowoOb = new HashMap<>();

        if (file.isEmpty()) {

            jsonRes.put("code","10008");
            jsonRes.put("info","请选择文件");
        }
        in = file.getInputStream();
        rowoOb = new ExcelDealUtils().getBankListByExcel(in, file.getOriginalFilename());
        //循环行
        for (Map.Entry<Object, Object> hang : rowoOb.entrySet()) {

            //循环行中具体的列
            Map<Object,Object> cellOb = (Map<Object,Object>)hang.getValue();
            for (Map.Entry<Object, Object> lie : cellOb.entrySet()) {

                //此处执行具体的逻辑操作（如：入库）
                System.out.println(lie.getKey());
                System.out.println(lie.getValue());
            }
        }
        in.close();
        // 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        return "result";

    }
    /**
     * excel上传 获取excel表中数据
     * 普通form提交 阿加西
     * */
    @RequestMapping(value = "/uploadEcelByFile", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String uploadEcelByFile(HttpServletRequest request) throws Exception {

        File files=new File("C:\\Users\\admin\\Desktop\\test.xls");
        String aa = files.getName();
        FileInputStream file = new FileInputStream("C:\\Users\\admin\\Desktop\\test.xls");

        Map<Object,Object> rowoOb = new HashMap<>();

        rowoOb = new ExcelDealUtils().getBankListByExcel(file, files.getName());

        return null;
    }

}
