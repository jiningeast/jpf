package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.common.util.ToolUtils;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class DfUtils {

    private String DFPAY_URL = "http://vip2.7shengqian.com/trade/api/";
    private String SERVICE = "applyAgentPay";
    private String SELECT_SERVICE = "queryAgentPay";
    private String signType = "MD5";
    private String inputCharset = "UTF-8";
    private String sysMerchNo = "152018052300555";
    private String DF_KEY = "cbc4de5f150b39d0cd703dfbe08a331b";

    public JSONObject applyAgentPay(Map<String, Object> requestMap)
    {
        requestMap.put("service",this.SERVICE);
        requestMap.put("sysMerchNo",this.sysMerchNo);
        requestMap.put("inputCharset",this.inputCharset);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(requestMap);
        String sortStr = ToolUtils.mapToUrl (treeMap);
        String signStr = Md5Encrypt.md5(sortStr + this.DF_KEY);
        treeMap.put("sign", signStr);
        treeMap.put("signType", this.signType);
//        String requestParam = sortStr + "&sign=" + signStr + "&signType=" + this.signType;
        String requestParam = ToolUtils.mapToUrl(treeMap);
        String requestUrl = this.DFPAY_URL + this.SERVICE;

        String result = OkHttpUtils.postForm(requestUrl, treeMap);
//        String result = "{\"retCode\":\"0000\",\"retMsg\":\"操作完成\",\"sign\":\"f2fa8dbf11100461e876d177b4a47284\",\"tranNo\":\"20180601154919050110009043\",\"tranAmt\":0.01,\"outOrderNo\":\"152783935800000000000046\",\"orderStatus\":\"00\"}";

        JSONObject resultJosn = new JSONObject();
        resultJosn.put("resultStr", result);
        resultJosn.put("requestUrl", requestUrl);
        resultJosn.put("requestParam", requestParam);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址：" + requestUrl);
        sbf.append("\n接口参数：" + requestParam);
        sbf.append("\n回调信息：" + result);
        String fileName = "applyAgentPayLog";
        String path = "/logs/jpf-cloud-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"", fileName, true);

        return resultJosn;
    }

    /**
     * 代付结果查询
     * @param requestMap
     * @return
     */
    public JSONObject queryAgentPay(Map<String,String> requestMap){

        JSONObject result = new JSONObject();
        requestMap.put("service",SELECT_SERVICE);
        requestMap.put("inputCharset",inputCharset);
        requestMap.put("sysMerchNo",sysMerchNo);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(requestMap);

        String sortStr = ToolUtils.mapToUrl (treeMap);
        String signStr = Md5Encrypt.md5(sortStr + DF_KEY);
        treeMap.put("sign", signStr);
        treeMap.put("signType", this.signType);

        String requestParam = ToolUtils.mapToUrl(treeMap);
        String requestUrl = DFPAY_URL + SELECT_SERVICE;

        String res = OkHttpUtils.postForm(requestUrl, treeMap);

        result.put("requestParam",treeMap);
        result.put("responseParam",res);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址：" + requestUrl);
        sbf.append("\n接口参数：" + requestParam);
        sbf.append("\n回调信息：" + res);
        String fileName = "queryAgentPayLog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"", fileName, true);

        return result;
    }
}
