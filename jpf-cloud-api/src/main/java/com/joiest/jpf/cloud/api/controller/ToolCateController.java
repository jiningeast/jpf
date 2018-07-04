package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.ToolCateRequest;
import com.joiest.jpf.dto.ToolCateResponse;
import com.joiest.jpf.entity.CloudIdcardInfo;
import com.joiest.jpf.entity.MwSmsInfo;
import com.joiest.jpf.facade.impl.CloudIdcardServiceFacadeImpl;
import com.joiest.jpf.facade.impl.RedisCustomServiceFacadeImpl;
import com.joiest.jpf.facade.impl.ToolCateServiceFacadeImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import org.apache.http.HttpResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("toolcate")
public class ToolCateController {

    @Autowired
    private ToolCateServiceFacadeImpl toolCateServiceFacade;

    @Autowired
    private CloudIdcardServiceFacadeImpl cloudIdcardServiceFacade;

    @Autowired
    private RedisCustomServiceFacadeImpl redisCustomServiceFacade;

    //自定义参与签名Header前缀（可选,默认只有"X-Ca-"开头的参与到Header签名）
    private final static List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();
    /*
     * 获取参数的json对象
     */
    public static JSONObject getParam(int type, String dataValue) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("dataType", type);
            obj.put("dataValue", dataValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 身份证OCR识别
     * */
    @RequestMapping(value = "/idCardOcr", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idCardOcr(HttpServletRequest request) throws IOException {

        String side = request.getParameter("side");
        String imgInfo = request.getParameter("img");

        String ocrRes = toolCateServiceFacade.idCardOcr(side,imgInfo);


        String dealImgInfo = imgInfo.replaceAll("^(data:\\s*image\\/(\\w+);base64,)", "");

        YjResponseDto yjResponseDto= new YjResponseDto();

        //请求path
        String host = "http://dm-51.data.aliyun.com";
        String path = "/rest/160601/ocr/ocr_idcard.json";
        String APP_KEY = "24789083";
        String APP_SECRET = "bb49c2a1f0d00d09ee83c56f23a0f5cd";

        Boolean is_old_format = true; //如果文档的输入中含有inputs字段，设置为True， 否则设置为False

        //请根据线上文档修改configure字段
        JSONObject configObj = new JSONObject();
        configObj.put("side", side);

        String config_str = configObj.toString();

        //base64转换为图片
        Map<String, String> imgUrl = toolCateServiceFacade.baseToImage(request,dealImgInfo,"OCR");

        String requestUrl = request.getRequestURL().toString();
        String resourceUrl = requestUrl.replace("/toolcate/idCardOcr",imgUrl.get("resourceUrl"));

        String imgFile = imgUrl.get("actualUrl");

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

        // 拼装请求body的json字符串
        JSONObject requestObj = new JSONObject();
        try {
            if(is_old_format==true) {

                JSONObject obj = new JSONObject();
                obj.put("image", getParam(50, imgBase64));
                if(config_str.length() > 0) {
                    obj.put("configure", getParam(50, config_str));
                }
                JSONArray inputArray = new JSONArray();
                inputArray.add(obj);
                requestObj.put("inputs", inputArray);
            }else{

                requestObj.put("image", imgBase64);
                if(config_str.length() > 0) {

                    requestObj.put("configure", config_str);
                }
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        //Body内容
        String body = requestObj.toString();

        Map<String, String> headers = new HashMap<String, String>();
        //（必填）根据期望的Response内容类型设置
        headers.put("Accept", "application/json");
        //（可选）Body MD5,服务端会校验Body内容是否被篡改,建议Body非Form表单时添加此Header
        headers.put("Content-MD5", MessageDigestUtil.base64AndMD5(body));
        //（POST/PUT请求必选）请求Body内容格式
        headers.put("Content-Type", "application/text; charset=UTF-8");

        ToolCateRequest toolCateRequest = new ToolCateRequest("POST", host, path, APP_KEY, APP_SECRET,10000);
        toolCateRequest.setHeaders(headers);
        toolCateRequest.setSignHeaderPrefixList(CUSTOM_HEADERS_TO_SIGN_PREFIX);
        toolCateRequest.setStringBody(body);

        ToolCateResponse toolCateResponse = new ToolCateResponse();
        String res=null;
        JSONObject ocrResult = null;
        JSONObject userInfo = null;
        Map<String,Object> idCard= new HashMap<String,Object>();
        String responseJson = null;

        //调用服务端
        try {

            //调用阿里云OCR请求，获取数据
            toolCateResponse = toolCateServiceFacade.convert(OkHttpUtils.httpPostOcr(
                   toolCateRequest.getHost(),
                   toolCateRequest.getPath(),
                   toolCateRequest.getTimeout(),
                   toolCateRequest.getHeaders(),
                   toolCateRequest.getQuerys(),
                   toolCateRequest.getStringBody(),
                   toolCateRequest.getSignHeaderPrefixList(),
                   toolCateRequest.getAppKey(),
                   toolCateRequest.getAppSecret()
            ));
            if(toolCateResponse.getStatusCode() != 200){

                System.out.println("Http code: " + toolCateResponse.getStatusCode());
                System.out.println("Http header error: " + toolCateResponse.getHeader("X-Ca-Error-Message"));
                System.out.println("Http body error msg: " + toolCateResponse.getBody());

                Map<String,Object> map = new HashMap<>();
                map.put("HttpCode",toolCateResponse.getHeader("X-Ca-Error-Message"));
                map.put("HttpBodyError",toolCateResponse.getBody());

                yjResponseDto.setCode("10008");
                yjResponseDto.setInfo("500 Internal Server Error");
                yjResponseDto.setData(map);

            }else{

                res = toolCateResponse.getBody();
                ocrResult = JSONObject.fromObject(res);

                JSONArray outputArray = ocrResult.getJSONArray("outputs");
                String output = outputArray.getJSONObject(0).getJSONObject("outputValue").getString("dataValue");
                userInfo = JSONObject.fromObject(output);

                if(userInfo.get("success").equals(true)){

                    if(side.equals("face")){

                        idCard.put("address",userInfo.get("address"));
                        idCard.put("birth",userInfo.get("birth"));
                        idCard.put("name",userInfo.get("name"));
                        idCard.put("nationality",userInfo.get("nationality"));
                        idCard.put("num",userInfo.get("num"));
                        idCard.put("frequest_id",userInfo.get("request_id"));
                        idCard.put("sex",userInfo.get("sex"));
                        //idCard.put("numnum",userInfo.get("num"));
                        idCard.put("side",side);
                        idCard.put("resourceUrl",resourceUrl);

                    }else{

                        idCard.put("start_date",userInfo.get("start_date"));
                        idCard.put("end_date",userInfo.get("end_date"));
                        idCard.put("issue",userInfo.get("issue"));
                        idCard.put("brequest_id",userInfo.get("request_id"));
                        idCard.put("end_date",userInfo.get("end_date"));
                        idCard.put("side",side);
                        idCard.put("resourceUrl",resourceUrl);

                    }
                    Map<String,Object> map = new HashMap<>();
                    map.put("userifno",idCard);

                    yjResponseDto.setCode("10000");
                    yjResponseDto.setInfo("SUCCESS");
                    yjResponseDto.setData(map);
                }else{

                    yjResponseDto.setCode("10008");
                    yjResponseDto.setInfo("身份证识别错误");
                }
            }
            responseJson = JsonUtils.toJson(yjResponseDto);
            String baseRe = Base64CustomUtils.base64Encoder(responseJson);
            baseRe = baseRe.replaceAll("\r\n","");
            return baseRe;
        } catch (Exception e) {

            e.printStackTrace();
        }
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

            redisCustomServiceFacade.set(ConfigUtil.getValue("CLOUD_USER_AUTH")+phone,new Integer(verificateCode).toString(),60*10);
            String content = null;
            content = "尊敬的用户，您此次的手机验证码是："+verificateCode+",10十分钟内有效";

            int result=toolCateServiceFacade.SendSms(ConfigUtil.getValue("MWMONGATESENDSUBMIT_URL")
                    ,ConfigUtil.getValue("MWUSERNAME"), ConfigUtil.getValue("MWPASSWORD"), phone, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。

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
}
