package com.joiest.jpf.charge.manage.api.controller;

import com.aliyun.oss.OSSClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamInterfaceRequest;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyMoneyStreamServiceFacade;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("chargeMoneyStream")
public class ChargeCompanyMoneyStreamController {

    @Autowired
    private ChargeCompanyMoneyStreamServiceFacade chargeCompanyMoneyStreamServiceFacade;

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    private ChargeCompanyInfo chargeCompanyInfo;

    private String merchNo;
    /**
     * 商户流水列表
     */
    @RequestMapping(value = "/streamlist",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String streamlist(String data){

        if ( org.apache.commons.lang3.StringUtils.isBlank(data) || data==null  )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }
        Map<String,Object> requestMap = null;
        try {
            String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
            String requestStr = Base64CustomUtils.base64Decoder(dataStr);
            requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
            if(requestMap==null){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
            }
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }

        ChargeCompanyMoneyStreamInterfaceRequest request = new ChargeCompanyMoneyStreamInterfaceRequest();
        try{
            request =  (ChargeCompanyMoneyStreamInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误！", null);
        }
        request.setMerchNo(merchNo);
        ChargeCompanyMoneyStreamResponse response = chargeCompanyMoneyStreamServiceFacade.getStreamList(request);

        if( response == null ||response.getList().size()<1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "没有更多了", null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response);
    }

    /**
     * 商户流水导出
     */

    @RequestMapping(value = "/exportExcel",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String exportExcel(String data,HttpServletResponse response){

        if ( org.apache.commons.lang3.StringUtils.isBlank(data) || data==null  )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }
        Map<String,Object> requestMap = null;
        try {
            String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
            String requestStr = Base64CustomUtils.base64Decoder(dataStr);
            requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
            if(requestMap==null){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
            }
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }

        ChargeCompanyMoneyStreamInterfaceRequest request = new ChargeCompanyMoneyStreamInterfaceRequest();
        try{
            request =  (ChargeCompanyMoneyStreamInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误！", null);
        }
        request.setMerchNo(merchNo);
        ChargeCompanyMoneyStreamResponse streamResponse = chargeCompanyMoneyStreamServiceFacade.getStreamList(request);

        if( streamResponse == null ||streamResponse.getList().size()<1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "没有更多了", null);
        }

        Map<String, String> ChargeCompanyMoneyStreamStatusCn = new HashMap<>();
        ChargeCompanyMoneyStreamStatusCn.put("1","充值");
        ChargeCompanyMoneyStreamStatusCn.put("2","下单");
        ChargeCompanyMoneyStreamStatusCn.put("3","退款");
        String path = ConfigUtil.getValue("EXCEL_PATH");
        //如果有购买的卡密放到Excel
//        设置excel标题以及字段
        JSONArray titles = new JSONArray();
        titles.add("流水号");
        titles.add("商户名称");
        titles.add("订单号");
        titles.add("商品名称");
        titles.add("商品数量");
        titles.add("收入金额|支出金额");
        titles.add("接口类型");
        titles.add("收支类型");
        titles.add("流水类型");
        titles.add("变动后的余额");
        titles.add("交易时间");

        JSONArray filed = new JSONArray();
        filed.add("streamNo");//卡号
        filed.add("companyName");
        filed.add("orderNo");
        filed.add("productName");
        filed.add("productAmount");
        filed.add("productValue");
        filed.add("interfaceReturn");
        filed.add("streamReturn");
        filed.add("statusReturn");
        filed.add("newMoney");
        filed.add("addtime");

        JSONObject excel=new JSONObject();
        try {
             excel = new ExcelDealUtils().exportExcelByInfo(response, titles.toString(), filed.toString(), streamResponse.getList(), 2,path);
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "数据导出异常！", null);
        }
        JSONObject url=JSONObject.fromObject(excel.get("data"));
        String excelPath=url.get("localUrl").toString();
        if(StringUtils.isBlank(excelPath)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "数据导出异常！", null);
        }
        //oss上传
        String files  = excelPath;
        String[] filex = files.split(",");
        String md5key = "";

        OSSClient ossClient= AliyunOSSClientUtil.getOSSClient();
        for(String filename:filex){
            // 上传文件流。
            File fileOne = new File(filename);
//            InputStream inputStream = new FileInputStream(fileOne);
            md5key  = AliyunOSSClientUtil.uploadObject2OSS(ossClient, fileOne, OSSClientConstants.BACKET_NAME,OSSClientConstants.FOLDER);
            // 关闭OSSClient。
          /*  System.out.println(md5key);
            System.out.println("Object：" + OSSClientConstants.BACKET_NAME + OSSClientConstants.FOLDER + "存入OSS成功。");*/

        }
        String subMd5key="";
        //截取去掉后缀时效
        if(md5key != null && md5key.length() != 0){

            subMd5key =md5key.substring(0,md5key.lastIndexOf("?"));

        }
        Map<String,String>returnUrl=new HashMap<>();
        returnUrl.put("url",subMd5key);
        //String excelerl=url.get(1).getClass("localUrl").toString();
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), returnUrl);
    }

    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {
        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("MANAGE_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByMerchNo(openId_encrypt);
            merchNo = chargeCompanyInfo.getMerchNo();
        }
    }

}
