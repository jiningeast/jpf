package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.cloud.api.controller.ConfigUtil;
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

    private String DFPAY_URL;
    private String SERVICE;
    private String SELECT_SERVICE;
    private String signType = "MD5";
    private String inputCharset = "UTF-8";
    private String sysMerchNo;
    private String DF_KEY;

    public DfUtils() {
        this.DFPAY_URL = ConfigUtil.getValue("DFPAY_URL");
        this.SERVICE = ConfigUtil.getValue("SERVICE");
        this.SELECT_SERVICE = ConfigUtil.getValue("SELECT_SERVICE");
        this.sysMerchNo = ConfigUtil.getValue("sysMerchNo");
        this.DF_KEY = ConfigUtil.getValue("DF_KEY");
        System.out.println(this.DF_KEY);
        System.out.println(this.DFPAY_URL);
    }

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

//        String result = OkHttpUtils.postForm(requestUrl, treeMap);    //TODO 2018-10-07 实际代付时打开注释
        String result = "{\"retCode\":\"0000\",\"retMsg\":\"操作完成\",\"sign\":\"f2fa8dbf11100461e876d177b4a47284\",\"tranNo\":\"20180601154919050110009043\",\"tranAmt\":0.01,\"outOrderNo\":\"152783935800000000000046\",\"orderStatus\":\"00\"}";

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
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

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

//        String res = OkHttpUtils.postForm(requestUrl, treeMap);       //TODO  实际代付时打开注释
//        String res = "{\"retCode\" : \"1002\",\"retMsg\" : \"商户订单号错误或不存在:5034141273962184\",\"sign\" : \"5153ab9147d8a2109f87a3a4ef52ef84\"}";
        String res = "{\"retCode\" : \"0000\",\"retMsg\" : \"操作完成\",\"sign\" : \"841adf62d9dab2ad5506f122d2ce9135\",\"tranNo\" : \"20180829165001050112208765\",\"tranAmt\" : 29850.00000,\"outOrderNo\" : \"7405532313931318\",\"orderStatus\" : \"02\"}";
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
        String path = "/logs/jpf-cloud-api/log/";
        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

        return result;
    }
}
