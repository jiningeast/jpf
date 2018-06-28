package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.MessageDigestUtil;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dto.ToolCateRequest;
import com.joiest.jpf.dto.ToolCateResponse;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("toolcate")
public class ToolCateController {


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

       //String paths = this.getServletContext().getRealPath();
        /*System.out.println(this.getClass()); //this.getClass().getResourceAsStream("/kafka-http.properties")
        System.out.println(this.getClass().getResource("/")); //this.getClass().getResourceAsStream("/kafka-http.properties")
        System.out.println(this.getClass().getResource("images")); //this.getClass().getResourceAsStream("/kafka-http.properties")
*/
        String side = request.getParameter("side");
        String imgInfo = request.getParameter("img");

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
        String imgFile = baseToImage(dealImgInfo,"OCR");

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
            toolCateResponse = convert(OkHttpUtils.httpPostOcr(
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
                map.put("Http code",toolCateResponse.getHeader("X-Ca-Error-Message"));
                map.put("Http body error msg",toolCateResponse.getBody());

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
                        idCard.put("numnum",userInfo.get("num"));
                        idCard.put("side",side);

                    }else{

                        idCard.put("start_date",userInfo.get("start_date"));
                        idCard.put("end_date",userInfo.get("end_date"));
                        idCard.put("issue",userInfo.get("issue"));
                        idCard.put("brequest_id",userInfo.get("request_id"));
                        idCard.put("end_date",userInfo.get("end_date"));
                        idCard.put("side",side);
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
            return Base64CustomUtils.base64Encoder(responseJson);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
    /**
     * 对数据流进行处理
     * */
    private static ToolCateResponse convert(HttpResponse response) throws IOException {

        ToolCateResponse res = new ToolCateResponse();
        if (null != response) {

            res.setStatusCode(response.getStatusLine().getStatusCode());
            for (Header header : response.getAllHeaders()) {
                res.setHeader(header.getName(), MessageDigestUtil.iso88591ToUtf8(header.getValue()));
            }
            res.setContentType(res.getHeader("Content-Type"));
            res.setRequestId(res.getHeader("X-Ca-Request-Id"));
            res.setErrorMessage(res.getHeader("X-Ca-Error-Message"));
            res.setBody(OkHttpUtils.readStreamAsStr(response.getEntity().getContent()));

        } else {
            //服务器无回应
            res.setStatusCode(500);
            res.setErrorMessage("No Response");
        }
        return res;
    }

    //base64字符串转化成图片
    @RequestMapping(value = "/baseToImage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public static String baseToImage(String imgStr,String perfix)
    {

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

            //生成jpeg图片
            String imgFilePath = "D:\\"+perfix+timeStamp+".jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return imgFilePath;

        }catch (Exception e) {

            return null;
        }
    }

}
