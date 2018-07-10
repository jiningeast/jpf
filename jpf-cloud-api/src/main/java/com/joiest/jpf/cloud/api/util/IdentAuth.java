package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.common.po.PayCloudIdenauth;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.ToolCateRequest;
import com.joiest.jpf.dto.ToolCateResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

public class IdentAuth {

    //实名认证 接口公共参数======start
        private String MethodPost = "POST";
        private String IdenHost = "https://idenauthen.market.alicloudapi.com";
        private String IdPath = "/idenAuthentication";
        private String AppCode = "92f6237c503845559542c5d26f9adab2";
    //实名认证 接口公共参数======end

    //OCR 接口公共参数======start
        private String OcrHost = "http://dm-51.data.aliyun.com";
        private String OcrPath = "/rest/160601/ocr/ocr_idcard.json";
        private String OcrAppKey = "24789083";
        private String OcrAppSecret = "bb49c2a1f0d00d09ee83c56f23a0f5cd";
    //OCR 接口公共参数======end

    /**
     *阿里云身份证、姓名实名认证
     * */
    public JSONObject idenAuth(String name, String idCard){

        Map<String, String> headers = new HashMap<String, String>();

        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + AppCode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        Map<String, String> querys = new HashMap<String, String>();

        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("idNo", idCard);
        bodys.put("name", name);

        String isSuc = "1";//默认成功
        ToolCateResponse toolCateResponse = new ToolCateResponse();//接收响应值
        String res=null;//初始化body信息
        JSONObject dealFirst = null;//定义body信息
        JSONObject ocrResult = new JSONObject();//定义返回的json数据
        JSONObject data = new JSONObject();//定义失败时错误信息
        JSONObject allParam = new JSONObject(); //定义整体返回

        try {

            HttpResponse response = OkHttpUtils.httpPostIdenAuth(IdenHost, IdPath, MethodPost, headers, querys, bodys);
            toolCateResponse = AliYunUtils.convert(response);

            allParam = JSONObject.fromObject(toolCateResponse);

            res = toolCateResponse.getBody();
            if(toolCateResponse.getStatusCode() != 200){

                isSuc = "2";
                if(res.equals("") || res.equals(null)){
                    res = "Error";
                }
                System.out.println("Http code: " + toolCateResponse.getStatusCode());
                System.out.println("Http header error: " + toolCateResponse.getHeader("X-Ca-Error-Message"));
                System.out.println("Http body error msg: " + res);

                data.put("httpCode",toolCateResponse.getHeader("X-Ca-Error-Message"));
                data.put("HttpBodyError",res);

                ocrResult.put("code","10008");
                ocrResult.put("info","实名认证有误");
                ocrResult.put("data",data);
            }else {

                dealFirst = JSONObject.fromObject(res);
                dealFirst.discard("respMessage");

                if(dealFirst.get("respCode").equals("0000")){

                    dealFirst.discard("respCode");
                    ocrResult.put("code","10000");
                    ocrResult.put("info","实名认证一致");
                    ocrResult.put("data",dealFirst);
                }else{

                    isSuc = "2";
                    dealFirst.discard("respCode");
                    ocrResult.put("code","10008");
                    ocrResult.put("info",dealFirst.get("respMessage"));
                    ocrResult.put("data",dealFirst);
                }
            }
            ocrResult.put("rawdata",allParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址：" + IdenHost+IdPath);
        sbf.append("\n接口参数headers：" + JSONObject.fromObject(headers));
        sbf.append("\n接口参数bodys：" + JSONObject.fromObject(bodys));
        sbf.append("\n回调ContentType：" + toolCateResponse.getContentType());
        sbf.append("\n回调RequestId：" + toolCateResponse.getRequestId());
        sbf.append("\n回调ErrorMessage：" + toolCateResponse.getErrorMessage());
        sbf.append("\n回调Body：" + toolCateResponse.getBody());
        String fileName = "IdenAuthlog";

        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        return ocrResult;
    }

    /**
     *阿里云OCR 身份证识别
     * */
    public JSONObject idCardOcr(HttpServletRequest request, String side, String imgInfo){

        String dealImgInfo = imgInfo.replaceAll("^(data:\\s*image\\/(\\w+);base64,)", "");

        JSONObject lastRes = new JSONObject();
        Boolean is_old_format = true; //如果文档的输入中含有inputs字段，设置为True， 否则设置为False

        //请根据线上文档修改configure字段
        JSONObject configObj = new JSONObject();
        configObj.put("side", side);
        String config_str = configObj.toString();

        //base64转换为图片
        Map<String, String> imgUrl = Base64CustomUtils.baseToImage(request,dealImgInfo,"OCR");
        //域名访问路径
        String requestUrl = request.getRequestURL().toString();
        String resourceUrl = requestUrl.replace("/toolcate/idCardOcr",imgUrl.get("resourceUrl"));

        //获取图片实际路径
        String imgFile = imgUrl.get("actualUrl");
        //根据图片地址获取base64
        String imgBase64  = Base64CustomUtils.imageToBase(imgFile);

        // 拼装请求body的json字符串
        JSONObject requestObj = new JSONObject();
        try {
            if(is_old_format==true) {

                JSONObject obj = new JSONObject();
                obj.put("image", AliYunUtils.getParam(50, imgBase64));
                if(config_str.length() > 0) {
                    obj.put("configure", AliYunUtils.getParam(50, config_str));
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
        headers.put("Accept", "application/json");
        headers.put("Content-MD5", MessageDigestUtil.base64AndMD5(body));
        headers.put("Content-Type", "application/text; charset=UTF-8");

        //自定义参与签名Header前缀（可选,默认只有"X-Ca-"开头的参与到Header签名）
        List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();

        ToolCateRequest toolCateRequest = new ToolCateRequest(MethodPost, OcrHost, OcrPath, OcrAppKey, OcrAppSecret,10000);
        toolCateRequest.setHeaders(headers);
        toolCateRequest.setSignHeaderPrefixList(CUSTOM_HEADERS_TO_SIGN_PREFIX);
        toolCateRequest.setStringBody(body);

        ToolCateResponse toolCateResponse = new ToolCateResponse();//接口响应参数设置
        String res=null;//定义body接收数据
        JSONObject ocrResult = null;//定义body接收数据转换为json
        JSONObject userInfo = null;//获取body中最后的用户信息
        Map<String,Object> idCard= new HashMap<String,Object>();//返回最终的用户信息参数
        String responseJson = null;

        //调用服务端
        try {

            //调用阿里云OCR请求，获取数据
            toolCateResponse = AliYunUtils.convert(OkHttpUtils.httpPostOcr(
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
                map.put("HttpCode",toolCateResponse.getHeader("X-Ca-Error-Message")==null?"0000":toolCateResponse.getHeader("X-Ca-Error-Message"));
                map.put("HttpBodyError",toolCateResponse.getBody());

                lastRes.put("code","10008");
                lastRes.put("info","Internal Server Error");
                lastRes.put("data",map);
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

                    lastRes.put("code","10000");
                    lastRes.put("info","SUCCESS");
                    lastRes.put("data",map);
                }else{

                    lastRes.put("code","10008");
                    lastRes.put("info","身份证识别错误");
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址：" + OcrHost+OcrPath);
        sbf.append("\n接口参数headers：" + JSONObject.fromObject(headers));
        sbf.append("\n接口参数bodys：" + body);
        sbf.append("\n回调ContentType：" + toolCateResponse.getContentType());
        sbf.append("\n回调RequestId：" + toolCateResponse.getRequestId());
        sbf.append("\n回调ErrorMessage：" + toolCateResponse.getErrorMessage());
        sbf.append("\n回调Body：" + toolCateResponse.getBody());
        String fileName = "Ocrlog";

        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        return lastRes;
    }

}
