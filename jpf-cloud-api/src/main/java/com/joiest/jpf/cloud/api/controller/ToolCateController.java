package com.joiest.jpf.cloud.api.controller;

import com.aliyun.oss.OSSClient;
import com.joiest.jpf.cloud.api.util.BankCheck;
import com.joiest.jpf.cloud.api.util.ExcelDealUtils;
import com.joiest.jpf.cloud.api.util.IdentAuth;
import com.joiest.jpf.cloud.api.util.MwSmsUtils;
import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.entity.CloudBankcheckInfo;
import com.joiest.jpf.entity.CloudIdcardInfo;
import com.joiest.jpf.entity.CloudIdenauthInfo;
import com.joiest.jpf.facade.CloudBankcheckServiceFacade;
import com.joiest.jpf.facade.CloudIdcardServiceFacade;
import com.joiest.jpf.facade.CloudIdenauthServiceFacade;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Controller
@RequestMapping("toolcate")
public class ToolCateController {

    @Autowired
    private CloudIdcardServiceFacade cloudIdcardServiceFacade;

    @Autowired
    private CloudIdenauthServiceFacade cloudIdenauthServiceFacade;

    @Autowired
    private CloudBankcheckServiceFacade cloudBankcheckServiceFacade;

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

        /*if(!selfSign.equals(sign)){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","签名有误");
            return json;
        }*/

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

        map.put("sign",sign);
        JSONObject idenAuth =idenAuthDataDeal(name,idCard,map);
        return idenAuth;
    }
    /**
     * 实名认证数据处理
     * */
    @RequestMapping(value = "/idenAuthDataDeal", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject idenAuthDataDeal(String name,String idCard,Map<String,String> map){

        //获取当前姓名、身份证号对应
        CloudIdenauthInfo cloudIdenauthInfo = cloudIdenauthServiceFacade.getCloudIdenauthByNumName(name,idCard);

        JSONObject cardAuth = new JSONObject();
        if(cloudIdenauthInfo == null || !cloudIdenauthInfo.getStatus().equals((byte)1)){

            //调用实名认证接口
            cardAuth = new IdentAuth().idenAuth(name,idCard);

            int authCount = 1;
            String status = "1";
            String responseParam = null;
            String requestParam = null;

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

                JSONArray requestJsonArray = JSONArray.fromObject(cloudIdenauthInfo.getRequestparam());
                requestJsonArray.add(requestJsonArray.size(),JSONObject.fromObject(map));

                System.out.print("请求数据大小："+requestJsonArray.size());

                requestParam = requestJsonArray.toString();

                idenMap.put("requestParam",requestParam);
                idenMap.put("count",String.valueOf(authCount));
                idenMap.put("responseParam",responseParam);
                idenMap.put("apiParam",cardAuth.discard("rawdata").toString());

                cloudIdenauthServiceFacade.updateCloudIdenauthById(idenMap,cloudIdenauthInfo.getId());
            }else{

                idenMap.put("name",name);
                idenMap.put("num",idCard);
                idenMap.put("count",String.valueOf(authCount));

                responseParam = "["+cardAuth.get("rawdata").toString()+"]";
                requestParam = "["+JSONObject.fromObject(map)+"]";

                idenMap.put("requestParam",requestParam);
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
     * 银行卡四要素鉴权
     * */
    @RequestMapping(value="bankFourCheck",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject bankFourCheck(HttpServletRequest request){

        String accountNo = request.getParameter("accountNo");
        String idCard = request.getParameter("idCard");
        String mobile = request.getParameter("mobile");
        String name = request.getParameter("name");
        String dateTime = request.getParameter("dateTime");
        String sign = request.getParameter("sign");

        JSONObject json = new JSONObject();
        if(accountNo == null || idCard == "" || mobile =="" || name ==""){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","Error");
            return json;
        }
        Map<String ,String> bankFouePa = new HashMap<>();
        bankFouePa.put("accountNo", accountNo);
        bankFouePa.put("idCard", idCard);
        bankFouePa.put("mobile", mobile);
        bankFouePa.put("name", name);
        bankFouePa.put("dateTime", dateTime);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(bankFouePa);

        String respos = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();
        if(!selfSign.equals(sign)){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","签名有误");
            return json;
        }
        bankFouePa.put("type", "1");
        bankFouePa.put("sign", sign);

         JSONObject bankDeal =idenBankInfoDeal(bankFouePa);
         return bankDeal;
    }

    /**
     * 银行卡三要素鉴权
     * */
    @RequestMapping(value="bankThereCheck",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject bankThreeCheck(HttpServletRequest request){

        String accountNo = request.getParameter("accountNo");
        String idCard = request.getParameter("idCard");
        String name = request.getParameter("name");
        String dateTime = request.getParameter("dateTime");
        String sign = request.getParameter("sign");

        JSONObject json = new JSONObject();
        if(accountNo == null || idCard == "" || name ==""){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","Error");
            return json;
        }
        Map<String ,String> bankThreePa = new HashMap<>();
        bankThreePa.put("accountNo", accountNo);
        bankThreePa.put("idCard", idCard);
        bankThreePa.put("name", name);
        bankThreePa.put("dateTime", dateTime);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(bankThreePa);

        String respos = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();
        if(!selfSign.equals(sign)){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","签名有误");
            return json;
        }
        bankThreePa.put("type", "2");
        bankThreePa.put("sign", sign);
        JSONObject bankDeal =idenBankInfoDeal(bankThreePa);
        return bankDeal;
    }
    /**

     * 银行要素数据处理
     * */
    @RequestMapping(value="bankInfoDeal",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject idenBankInfoDeal(Map<String,String> bankCheck){

        JSONObject bankAuth = new JSONObject();

        CloudBankcheckInfo getBankCheck = cloudBankcheckServiceFacade.getBankcheckByAccountNo(bankCheck.get("accountNo"),bankCheck.get("type"));
        if(getBankCheck == null || !getBankCheck.getStatus().equals((byte)1)){

            int checkCount = 1;
            String responseParam = null;
            String requestParam = null;
            JSONObject bankCheckInfo = null;
            Map<String,String> bankMap = new HashMap<>();


            if(bankCheck.get("type").equals("1")){

                bankCheckInfo = new BankCheck().bankForuCheck(bankCheck);
                bankMap.put("mobile",bankCheck.get("mobile"));
            }else{

                bankCheckInfo = new BankCheck().bankThreeCheck(bankCheck);
            }
            bankMap.put("num",bankCheck.get("idCard"));
            bankMap.put("name",bankCheck.get("name"));
            bankMap.put("type",bankCheck.get("type"));
            bankMap.put("accountNo",bankCheck.get("accountNo"));

            if(bankCheckInfo.get("code").equals("10008")){
                bankMap.put("status","2");
            }else {
                bankMap.put("status","1");
            }
            //计算认证次数
            if(getBankCheck != null){

                checkCount = checkCount+getBankCheck.getCount();

                JSONArray myJsonArray = JSONArray.fromObject(getBankCheck.getResponseparam());
                myJsonArray.add(myJsonArray.size(),bankCheckInfo.get("rawdata").toString());
                responseParam = myJsonArray.toString();

                JSONArray requestJsonArray = JSONArray.fromObject(getBankCheck.getRequestparam());
                requestJsonArray.add(requestJsonArray.size(),JSONObject.fromObject(bankCheck));
                requestParam = requestJsonArray.toString();

                bankMap.put("count",String.valueOf(checkCount));
                bankMap.put("requestParam",requestParam);
                bankMap.put("responseParam",responseParam);
                bankMap.put("apiParam",bankCheckInfo.discard("rawdata").toString());

                cloudBankcheckServiceFacade.updateCloudBankcheckById(bankMap,getBankCheck.getId());

            }else{

                bankMap.put("count",String.valueOf(checkCount));

                requestParam = "["+JSONObject.fromObject(bankCheck)+"]";
                responseParam = "["+bankCheckInfo.get("rawdata").toString()+"]";

                bankMap.put("requestParam",requestParam);
                bankMap.put("responseParam",responseParam);
                bankMap.put("apiParam",bankCheckInfo.discard("rawdata").toString());

                cloudBankcheckServiceFacade.addCloudBankcheck(bankMap);

            }
            bankAuth = bankCheckInfo.discard("rawdata");
        }else{

            //验证请求数据是否正确
            if(!getBankCheck.getNum().equals(bankCheck.get("idCard")) || !getBankCheck.getName().equals(bankCheck.get("name")) || (bankCheck.get("type").equals("1") && !getBankCheck.getMobile().equals(bankCheck.get("mobile")) ) ){

                bankAuth.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
                bankAuth.put("info","请确认信息是否一致");
            }else{
                bankAuth = JSONObject.fromObject(getBankCheck.getApiparam());
            }
        }
        return bankAuth;
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

        Map<String,String> idAuth = new HashMap<>();
        idAuth.put("name",faceResult.get("name").toString());
        idAuth.put("num",faceResult.get("num").toString());

        //实名验证
        //JSONObject cardAuth = new IdentAuth().idenAuth(faceResult.get("name").toString(),faceResult.get("num").toString());
        JSONObject cardAuth = idenAuthDataDeal(faceResult.get("name").toString(),faceResult.get("num").toString(),idAuth);
        if(cardAuth.get("code").equals("10008")){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), cardAuth.get("info").toString(),null);
        }
        CloudIdcardInfo cloudIdcardInfo=cloudIdcardServiceFacade.getCloudIdcardByCardNo(faceResult.get("num").toString());

        YjResponseDto yjResponseDto= new YjResponseDto();
        if(cloudIdcardInfo == null){

            int idCard= cloudIdcardServiceFacade.addCloudIdcard(faceResult,backResult,type);
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
    /**
     * 公共上传文件接口 文件流形式
     * */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadFile(@RequestParam("file") MultipartFile file) throws UnknownHostException {

        String savePre = ConfigUtil.getValue("ROOT_PATH");
        String allpath = PhotoUtil.saveFile(file, savePre);

        String md5key = "";
        OSSClient ossClient= AliyunOSSClientUtil.getOSSClient();

        // 上传文件流。
        File fileOne = new File(allpath);
        md5key  = AliyunOSSClientUtil.uploadObject2OSS(ossClient, fileOne, OSSClientConstants.BACKET_NAME,OSSClientConstants.FOLDER);

        // 关闭OSSClient。
        System.out.println(md5key);
        System.out.println("Object：" + OSSClientConstants.BACKET_NAME + OSSClientConstants.FOLDER + "存入OSS成功。");

        JSONObject resposeData = new JSONObject();
        resposeData.put("localUrl",allpath);
        resposeData.put("serverUrl",md5key);

        return resposeData;
    }

    @ModelAttribute
    public void beforeAction(HttpServletRequest httpRequest, HttpServletResponse response)
    {
        // 跨域
        String originHeader = httpRequest.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.setHeader("Access-Control-Allow-Method", "POST");
        response.setHeader("Access-Control-Allow-Origin", originHeader);

    }

}
