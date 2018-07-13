package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dto.ToolCateResponse;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BankCheck {

    String Method = "GET";
    //银行卡信息鉴权 接口公共参数======start
        private String BankHost = "https://b4bankcard.market.alicloudapi.com";
        private String BankFourPath = "/bank4Check";
        private String BankThreePath = "/bank3RealCheck";
        private String BankAppKey = "24958418";
        private String BankAppSecret = "AppSecret：bb3688af36947a4375bb830d136e0a63";
        private String BankAppCode = "5171bbeb03ed45adb46e0fe8ff14640d";
    //银行卡信息鉴权 接口公共参数======end

    /**
     *阿里云银行四要素鉴权
     * */
    public JSONObject bankForuCheck(Map<String,String> bankFouePa){

        Map<String, String> bankMap = new HashMap<String, String>();

        bankMap.put("idCard",bankFouePa.get("idCard"));
        bankMap.put("mobile",bankFouePa.get("mobile"));
        bankMap.put("name",bankFouePa.get("name"));
        bankMap.put("accountNo",bankFouePa.get("accountNo"));

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + BankAppCode);
        HttpResponse response = null;
        ToolCateResponse toolCateResponse = new ToolCateResponse();//接收响应值

        String res=null;//初始化body信息
        JSONObject dealFirst = null;//定义body信息
        JSONObject ocrResult = new JSONObject();//定义返回的json数据
        JSONObject data = new JSONObject();//定义失败时错误信息
        JSONObject allParam = new JSONObject(); //定义整体返回
        try {

            response = OkHttpUtils.doGet(BankHost, BankFourPath, Method, headers, bankMap);
            toolCateResponse = AliYunUtils.convert(response);

            allParam = JSONObject.fromObject(toolCateResponse);
            res = toolCateResponse.getBody();

            if(toolCateResponse.getStatusCode() != 200){

                if(res.equals("") || res.equals(null)){
                    res = "Error";
                }
                System.out.println("Http code: " + toolCateResponse.getStatusCode());
                System.out.println("Http header error: " + toolCateResponse.getHeader("X-Ca-Error-Message"));
                System.out.println("Http body error msg: " + res);

                data.put("httpCode",toolCateResponse.getHeader("X-Ca-Error-Message"));
                data.put("HttpBodyError",res);

                ocrResult.put("code","10008");
                ocrResult.put("info","验证有误");
                ocrResult.put("data",data);
            }else {

                dealFirst = JSONObject.fromObject(res);
                dealFirst.discard("msg");

                if(dealFirst.get("status").equals("01")){

                    dealFirst.discard("status");
                    ocrResult.put("code","10000");
                    ocrResult.put("info","验证通过");
                    ocrResult.put("data",dealFirst);
                }else{

                    dealFirst.discard("status");
                    ocrResult.put("code","10008");
                    ocrResult.put("info",dealFirst.get("msg"));
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
        sbf.append("\n请求地址：" + BankHost+BankThreePath);
        sbf.append("\n接口参数headers：" + JSONObject.fromObject(headers));
        sbf.append("\n接口业务参数：" + JSONObject.fromObject(bankMap));
        sbf.append("\n回调ContentType：" + toolCateResponse.getContentType());
        sbf.append("\n回调RequestId：" + toolCateResponse.getRequestId());
        sbf.append("\n回调ErrorMessage：" + toolCateResponse.getErrorMessage());
        sbf.append("\n回调Body：" + toolCateResponse.getBody());
        String fileName = "BankFourCheckLog";

        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        return ocrResult;
    }

    /**
     *阿里云银行三要素鉴权
     * */
    public JSONObject bankThreeCheck(Map<String,String> bankFouePa){

        Map<String, String> bankMap = new HashMap<String, String>();

        bankMap.put("idCard",bankFouePa.get("idCard"));
        bankMap.put("mobile",bankFouePa.get("mobile"));
        bankMap.put("name",bankFouePa.get("name"));
        bankMap.put("accountNo",bankFouePa.get("accountNo"));

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + BankAppCode);
        HttpResponse response = null;
        ToolCateResponse toolCateResponse = new ToolCateResponse();//接收响应值

        String res=null;//初始化body信息
        JSONObject dealFirst = null;//定义body信息
        JSONObject ocrResult = new JSONObject();//定义返回的json数据
        JSONObject data = new JSONObject();//定义失败时错误信息
        JSONObject allParam = new JSONObject(); //定义整体返回
        try {

            response = OkHttpUtils.doGet(BankHost, BankThreePath, Method, headers, bankMap);
            toolCateResponse = AliYunUtils.convert(response);

            allParam = JSONObject.fromObject(toolCateResponse);
            res = toolCateResponse.getBody();

            if(toolCateResponse.getStatusCode() != 200){

                if(res.equals("") || res.equals(null)){
                    res = "Error";
                }
                System.out.println("Http code: " + toolCateResponse.getStatusCode());
                System.out.println("Http header error: " + toolCateResponse.getHeader("X-Ca-Error-Message"));
                System.out.println("Http body error msg: " + res);

                data.put("httpCode",toolCateResponse.getHeader("X-Ca-Error-Message"));
                data.put("HttpBodyError",res);

                ocrResult.put("code","10008");
                ocrResult.put("info","验证有误");
                ocrResult.put("data",data);
            }else {

                dealFirst = JSONObject.fromObject(res);
                dealFirst.discard("msg");

                if(dealFirst.get("status").equals("01")){

                    dealFirst.discard("status");
                    ocrResult.put("code","10000");
                    ocrResult.put("info","验证通过");
                    ocrResult.put("data",dealFirst);
                }else{

                    dealFirst.discard("status");
                    ocrResult.put("code","10008");
                    ocrResult.put("info",dealFirst.get("msg"));
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
        sbf.append("\n请求地址：" + BankHost+BankThreePath);
        sbf.append("\n接口参数headers：" + JSONObject.fromObject(headers));
        sbf.append("\n接口业务参数：" + JSONObject.fromObject(bankMap));
        sbf.append("\n回调ContentType：" + toolCateResponse.getContentType());
        sbf.append("\n回调RequestId：" + toolCateResponse.getRequestId());
        sbf.append("\n回调ErrorMessage：" + toolCateResponse.getErrorMessage());
        sbf.append("\n回调Body：" + toolCateResponse.getBody());
        String fileName = "BankThreeCheckLog";

        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        return ocrResult;
    }
}
