package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.ExcelDealUtils;
import com.joiest.jpf.cloud.api.util.IdentAuth;
import com.joiest.jpf.cloud.api.util.MwSmsUtils;
import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.entity.CloudIdcardInfo;
import com.joiest.jpf.entity.CloudIdenauthInfo;
import com.joiest.jpf.facade.CloudIdcardServiceFacade;
import com.joiest.jpf.facade.CloudIdenauthServiceFacade;
import net.sf.json.JSONArray;
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
    private CloudIdcardServiceFacade cloudIdcardServiceFacade;

    @Autowired
    private CloudIdenauthServiceFacade cloudIdenauthServiceFacade;
    /**
     * 身份证OCR识别
     * */
    @RequestMapping(value = "/idCardOcr", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject idCardOcr(HttpServletRequest request) throws IOException {


        String side = request.getParameter("side");
        String dateTime = request.getParameter("dateTime");
        String imgInfo = request.getParameter("img");
        String sign = request.getParameter("sign");

        JSONObject json = new JSONObject();
        if(side == null || dateTime==null || imgInfo==null || sign ==null){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","Error");
            return json;
        }
        Map<String,String> map = new HashMap<>();
        map.put("side",side);
        map.put("dateTime",dateTime);
        map.put("imgInfo",imgInfo);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();

        if(!selfSign.equals(sign)){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","签名有误");
            return json;
        }

        JSONObject ocrRes = new IdentAuth().idCardOcr(request,side,imgInfo);
        return ocrRes;
    }
    /*
    * 身份证号、姓名实名认证
    * */
    @RequestMapping(value = "/idenAuth", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject idenAuth(HttpServletRequest request) {

        String name = request.getParameter("name");
        String idCard = request.getParameter("idCard");
        String dateTime = request.getParameter("dateTime");
        String sign = request.getParameter("sign");

        JSONObject json = new JSONObject();
        if(name == null || idCard==null || dateTime==null || sign ==null){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","Error");
            return json;
        }
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("idCard",idCard);
        map.put("dateTime",dateTime);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();

        if(!selfSign.equals(sign)){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","签名有误");
            return json;
        }
        JSONObject idenAuth =idenAuthDataDeal(name,idCard);
        return idenAuth;
    }
    /**
     * 实名认证数据处理
     * */
    @RequestMapping(value = "/idenAuthDataDeal", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject idenAuthDataDeal(String name,String idCard){


        //获取当前姓名、身份证号对应
        CloudIdenauthInfo cloudIdenauthInfo = cloudIdenauthServiceFacade.getCloudIdenauthByNumName(name,idCard);

        JSONObject cardAuth = new JSONObject();
        if(cloudIdenauthInfo == null || !cloudIdenauthInfo.getStatus().equals((byte)1)){

            //调用实名认证接口
            cardAuth = new IdentAuth().idenAuth(name,idCard);

            int authCount = 1;
            String status = "1";
            String responseParam = null;

            Map<String,String> idenMap = new HashMap<>();

            if(cardAuth.get("code").equals("10008")){
                idenMap.put("status","2");
            }else {
                idenMap.put("status","1");
            }
            //计算认证次数
            if(cloudIdenauthInfo != null){

                authCount = authCount+cloudIdenauthInfo.getCount();

                JSONArray myJsonArray = JSONArray.fromObject(cloudIdenauthInfo.getResponseparam());
                myJsonArray.add(myJsonArray.size(),cardAuth.get("rawdata").toString());
                responseParam = myJsonArray.toString();

                idenMap.put("count",String.valueOf(authCount));
                idenMap.put("responseParam",responseParam);
                idenMap.put("apiParam",cardAuth.discard("rawdata").toString());

                cloudIdenauthServiceFacade.updateCloudIdenauthById(idenMap,cloudIdenauthInfo.getId());
            }else{

                idenMap.put("name",name);
                idenMap.put("num",idCard);
                idenMap.put("count",String.valueOf(authCount));

                responseParam = "["+cardAuth.get("rawdata").toString()+"]";
                idenMap.put("responseParam",responseParam);
                idenMap.put("apiParam",cardAuth.discard("rawdata").toString());

                cloudIdenauthServiceFacade.addCloudIdenauth(idenMap);
            }
        }else{

            return JSONObject.fromObject(cloudIdenauthInfo.getApiparam());
        }
        return cardAuth.discard("rawdata");
    }
    /**
     * 短信发送接口
     * */
    @RequestMapping(value = "/sendSmsApi", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject sendSmsApi(HttpServletRequest request) throws IOException {

        String mobile = request.getParameter("mobile");
        String content = request.getParameter("content");
        String dateTime = request.getParameter("dateTime");
        String sign = request.getParameter("sign");

        JSONObject json = new JSONObject();
        if(mobile == null || content==null || dateTime==null || sign ==null){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","Error");
            return json;
        }
        Map<String,String> map = new HashMap<>();
        map.put("mobile",mobile);
        map.put("content",content);
        map.put("dateTime",dateTime);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();

        if(!selfSign.equals(sign)){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","签名有误");
            return json;
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
            return json;
        }catch (Exception e) {

            e.printStackTrace();//异常处理
        }
        return json;
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
        rowoOb = new ExcelDealUtils().getImportExcel(in, file.getOriginalFilename());
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

        rowoOb = new ExcelDealUtils().getImportExcel(file, files.getName());

        return null;
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
        //JSONObject cardAuth = new IdentAuth().idenAuth(faceResult.get("name").toString(),faceResult.get("num").toString());
        JSONObject cardAuth = idenAuthDataDeal(faceResult.get("name").toString(),faceResult.get("num").toString());
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

}
