package com.joiest.jpf.market.api.util;

import com.joiest.jpf.common.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WnpayUtils {

    private String account;
    private String password;
    private String url;

    public WnpayUtils(String account,String password,String url){

        this.account = account;
        this.password = password;
        this.url = url;
    }
    /**
    * @param carrier 运营商  移动:cmcc，联通:cucc，电信:ctc，为空返回所有运营商的套餐
    * */
    public String getProduct(String carrier){


        JSONObject responseParam = new JSONObject();
        JSONObject signParam = new JSONObject();

        String curDate = DateUtils.getDate2YmdhmsString(new Date());
        signParam.put("account",this.account);
        //signParam.put("md5Pass", Md5Encrypt.md5(this.password,"utf-8"));
        signParam.put("timestamp", curDate);
        signParam.put("carrier", carrier);

        String md5Pass = Md5Encrypt.md5(this.password,"utf-8");
        String sign = null;
        if (StringUtils.isNotBlank(carrier))
            sign = sign = Md5Encrypt.md5(this.account+md5Pass+curDate+carrier,"utf-8");
        else
            sign = sign = Md5Encrypt.md5(this.account+md5Pass+curDate,"utf-8");

        signParam.put("sign",sign);

        String resposePa = OkHttpUtils.postJson(this.url+"flowProduct",signParam.toString());
        JSONObject product = JSONObject.fromObject(resposePa);

        StringBuilder sbf = new StringBuilder();

        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n请求地址：" + this.url+"flowProduct");
        sbf.append("\n请求参数：" + signParam.toString());
        sbf.append("\n响应状态：" + product.get("status"));
        sbf.append("\n响应信息：" + product.get("message"));
        sbf.append("\n响应参数：" + product.toString());

        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-market-api/log/", "WnApi",true);

        if(product.get("status").toString().equals("0")){

            responseParam.put("code","10000");
            responseParam.put("info",product.get("message"));
            responseParam.put("data",product.get("data"));

        }else{

            responseParam.put("code","10008");
            responseParam.put("info",product.get("message"));
        }
        return responseParam.toString();
    }

    private String getSign(JSONObject signParam){

        String sign = null;
        for(Object str:signParam.keySet()){

            if(signParam.get(str) != null && !signParam.get(str).equals(null))
                sign+=signParam.get(str);

        }
        sign = Md5Encrypt.md5(sign,"utf-8");

        return sign;
    }
}
