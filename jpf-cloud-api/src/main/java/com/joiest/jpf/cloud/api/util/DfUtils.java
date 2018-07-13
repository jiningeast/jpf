package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.common.util.ToolUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class DfUtils {

    private String DFPAY_URL = "http://vip2.7shengqian.com/trade/api/";
    private String SERVICE = "applyAgentPay";
    private String signType = "MD5";
    private String inputCharset = "UTF-8";

    public JSONObject applyAgentPay(Map<String, Object> requestMap)
    {
        requestMap.put("service",this.SERVICE);
        requestMap.put("inputCharset",this.inputCharset);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(requestMap);
        String sortStr = ToolUtils.mapToUrl (treeMap);
        String signStr = Md5Encrypt.md5(sortStr);
        treeMap.put("sign", signStr);
        treeMap.put("signType", this.signType);
        String requestParam = sortStr + "&sign=" + signStr + "&signType=" + this.signType;
        String requestUrl = this.DFPAY_URL + this.SERVICE;

        String result = OkHttpUtils.postForm(requestUrl, treeMap);
        JSONObject resultJosn = JSONObject.fromObject(result);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址：" + requestUrl);
        sbf.append("\n接口参数：" + requestParam);
        sbf.append("\n回调信息：" + result);
        String fileName = "applyAgentPayLog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"", fileName, true);

        return resultJosn;
    }

}
