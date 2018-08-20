package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.BankCheck;
import com.joiest.jpf.cloud.api.util.IdentAuth;
import com.joiest.jpf.cloud.api.util.MwSmsUtils;
import com.joiest.jpf.cloud.api.util.XinxiangMwSmsUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.entity.CloudBankcheckInfo;
import com.joiest.jpf.entity.CloudIdcardInfo;
import com.joiest.jpf.entity.CloudIdenauthInfo;
import com.joiest.jpf.entity.MwmultSmsInfo;
import com.joiest.jpf.facade.CloudBankcheckServiceFacade;
import com.joiest.jpf.facade.CloudIdcardServiceFacade;
import com.joiest.jpf.facade.CloudIdenauthServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("toolcate")
public class ToolCateController {

    @Autowired
    private CloudIdcardServiceFacade cloudIdcardServiceFacade;

    @Autowired
    private CloudIdenauthServiceFacade cloudIdenauthServiceFacade;

    @Autowired
    private CloudBankcheckServiceFacade cloudBankcheckServiceFacade;

    private static final Logger logger = LogManager.getLogger(ToolCateController.class);

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

        if(!selfSign.equals(sign)){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","签名有误");
            return json;
        }

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


        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址："+request.getRequestURL().toString());
        sbf.append("\n接口参数：" + respos+"&sign="+sign);
        sbf.append("\n生成签名：" + selfSign);
        String fileName = "IdenAuthlog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);


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

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址："+request.getRequestURL().toString());
        sbf.append("\n接口参数：" + respos+"&sign="+sign);
        sbf.append("\n生成签名：" + selfSign);
        String fileName = "BankFourCheckLog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

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

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址："+request.getRequestURL().toString());
        sbf.append("\n接口参数：" + respos+"&sign="+sign);
        sbf.append("\n生成签名：" + selfSign);
        String fileName = "BankThreeCheckLog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

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
     * 短信发送接口 不同手机相同内容
     * */
    @RequestMapping(value = "/sendSmsApi", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject sendSmsApi(HttpServletRequest request) throws IOException {

        String mobile = request.getParameter("mobile");
        String content = request.getParameter("content");
        String dateTime = request.getParameter("dateTime");
        String accountType = "";
        if ( StringUtils.isNotBlank(request.getParameter("accountType")) ){
            accountType = request.getParameter("accountType");
        }
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
        map.put("accountType",accountType);
        logger.info("==================================================="+map);
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址："+request.getRequestURL().toString());
        sbf.append("\n接口参数：" + respos+"&sign="+sign);
        sbf.append("\n生成签名：" + selfSign);
        String fileName = "MwSmslog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        if(!selfSign.equals(sign)){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","签名有误");
            return json;
        }
        try{
            int result;
            if ( accountType.equals("xinxiang") ){
                result = new XinxiangMwSmsUtils().sendSms(mobile, content);//toolCateServiceFacade.sendSms(mobile, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。
            }else{
                result = new MwSmsUtils().sendSms(mobile, content);//toolCateServiceFacade.sendSms(mobile, content);//短信息发送接口（相同内容群发，可自定义流水号）POST请求。
            }

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
     * 短信发送接口(不同手机，不同内容)
     * */
    @RequestMapping(value = "/sendMultixSms", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject sendMultixSms(HttpServletRequest request){

        String smsInfo = request.getParameter("smsInfo");
        String dateTime = request.getParameter("dateTime");
        String sign = request.getParameter("sign");

        JSONObject json = new JSONObject();
        if(smsInfo == null || dateTime==null || sign ==null){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","Error");
            return json;
        }
        Map<String,String> map = new HashMap<>();
        map.put("smsInfo",smsInfo);
        map.put("dateTime",dateTime);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();



        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址："+request.getRequestURL().toString());
        sbf.append("\n接口参数：" + respos+"&sign="+sign);
        sbf.append("\n生成签名：" + selfSign);
        String fileName = "MwSmslog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        if(!selfSign.equals(sign)){

            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","签名有误");
            return json;
        }
        JSONArray jsonArray = JSONArray.fromObject(smsInfo);
        List<MwmultSmsInfo> multixMts = new ArrayList<MwmultSmsInfo>();
        try{

            if(jsonArray.size()>0) {

                for (int i = 0; i < jsonArray.size(); i++) {

                    MwmultSmsInfo param = new MwmultSmsInfo();
                    JSONObject job = jsonArray.getJSONObject(i);

                    param.setStrMobile(job.get("mobile").toString());//手机号码
                    param.setStrBase64Msg(job.get("msg").toString());//短信内容，内容长度不大于350个汉字
                    param.setStrSpNumber("*");//通道号，不需要请填*
                    param.setStrUserMsgId(String.valueOf(ToolUtils.getRandomInt(1000,9999))+job.get("mobile")+ToolUtils.getRandomInt(1000,9999));//用户自定义流水号，不带请输入0（流水号范围-（2^63）……2^63-1）
                    multixMts.add(param);//添加一条短信
                }
            }
            int result= new MwSmsUtils().SendMultixSms(multixMts);
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
     * 公共上传文件接口 文件流形式
     * */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws UnknownHostException {

        JSONObject resposeData = new JSONObject();

        String isOss = request.getParameter("isOss");//1上传至oss  2不上传
        if(StringUtils.isBlank(isOss)) isOss = "1";

        String savePre = ConfigUtil.getValue("ROOT_PATH");
        String allpath = PhotoUtil.saveFile(file, savePre);
        if(StringUtils.isBlank(allpath)){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "文件上传失败",null);
        }
        //Oss阿里云上传
        if(isOss.equals("1")){

            //调用oss上传
            JSONObject imgRes = AliyunOSSClientUtil.initUploadPath(allpath);
            if(imgRes.isEmpty()){

                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "文件上传失败",null);
            }
            resposeData.put("serverUrl",imgRes.get("imgUrl"));
            resposeData.put("serverExpireUrl",imgRes.get("expireUrl"));
        }
        String fileName = allpath.substring(allpath.lastIndexOf("/")+1);

        resposeData.put("fileName",fileName);


        resposeData.put("localUrl",allpath);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "文件上传成功",resposeData);
    }
    /**
     * 公共上传文件接口 base64格式
     * */
    @RequestMapping(value = "/uploadFileByBase", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadFileByBase(HttpServletRequest request) throws UnknownHostException {

        JSONObject result = new JSONObject();
        JSONObject json = new JSONObject();

        String source = request.getParameter("source");//来源 1 web  2 api
        String isOss = request.getParameter("isOss");//1上传至oss  2不上传

        String baseInfo = request.getParameter("baseInfo");//base64数据
        String savePre = request.getParameter("savePre");//上传路径
        String dateTime = request.getParameter("dateTime");//访问时间
        String sign = request.getParameter("sign");//签名

        if (source.isEmpty() || StringUtils.isBlank(baseInfo) || StringUtils.isBlank(isOss)){
            json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            json.put("info","Error");
            return json;
        }
        if(StringUtils.isBlank(savePre)) savePre = ConfigUtil.getValue("ROOT_PATH");

        Pattern pattern = Pattern.compile("^\\d");
        Matcher matcher = pattern.matcher(source);
        //存在为接口调用
        if ( matcher.find() ){

            //数据判断
            Map<String ,String> imgPa = new HashMap<>();
            imgPa.put("source", source);
            imgPa.put("isOss", isOss);
            imgPa.put("baseInfo", baseInfo);
            imgPa.put("dateTime", dateTime);
            if(StringUtils.isNotBlank(savePre)){

                imgPa.put("savePre", savePre);
            }
            Map<String,Object> treeMap = new TreeMap<>();
            treeMap.putAll(imgPa);

            String respos = ToolUtils.mapToUrl(treeMap);
            String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();
            if(!selfSign.equals(sign)){

                json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
                json.put("info","签名有误");
                return json;
            }
        }else{
            isOss = Base64CustomUtils.base64Decoder(isOss);
        }
        if(StringUtils.isBlank(savePre)) savePre = ConfigUtil.getValue("ROOT_PATH");

        //base64数据处理
        Map<String,String> imgInfo = Base64CustomUtils.baseToImageFinal(baseInfo,savePre);
        String allpath = imgInfo.get("localUrl");

        //Oss阿里云上传
        if(isOss.equals("1")){

            //调用oss上传
            JSONObject imgRes = AliyunOSSClientUtil.initUploadPath(allpath);
            if(imgRes.isEmpty()){

                json.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
                json.put("info","OSS文件上传失败");
                return json;
            }
            json.put("serverUrl",imgRes.get("imgUrl"));
            json.put("serverExpireUrl",imgRes.get("expireUrl"));
        }
        String fileName = allpath.substring(allpath.lastIndexOf("/")+1);
        json.put("fileName",fileName);
        json.put("localUrl",allpath);

        result.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        result.put("info","上传成功");
        result.put("data",json);
        return result;//ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "文件上传成功",resposeData);
    }
    /**
     * 身份证分析入库
     * */
    @RequestMapping(value = "/idCardAnaly", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String idCardAnaly(HttpServletRequest request) throws IOException {

        String faceBase = request.getParameter("face");
        String backBase = request.getParameter("back");
        String type = request.getParameter("type");
        String isOss = request.getParameter("isSer");//1上传至oss  2不上传

        if(StringUtils.isBlank(isOss)) isOss = "MQ==";

        //参数是否为空
        if(faceBase == null || faceBase.isEmpty() || backBase == null || backBase.isEmpty()){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "Error", null);
        }
        faceBase = Base64CustomUtils.base64Decoder(faceBase);
        backBase = Base64CustomUtils.base64Decoder(backBase);
        type = Base64CustomUtils.base64Decoder(type);
        isOss = Base64CustomUtils.base64Decoder(isOss);

        JSONObject faceResult = JSONObject.fromObject(faceBase);
        JSONObject backResult = JSONObject.fromObject(backBase);

        Map<String,String> idAuth = new HashMap<>();
        idAuth.put("name",faceResult.get("name").toString());
        idAuth.put("num",faceResult.get("num").toString());

        //实名验证
        JSONObject cardAuth = idenAuthDataDeal(faceResult.get("name").toString(),faceResult.get("num").toString(),idAuth);
        if(cardAuth.get("code").equals("10008")){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), cardAuth.get("info").toString(),null);
        }
        //获取身份证信息 pay_cloud_idcard
        CloudIdcardInfo cloudIdcardInfo=cloudIdcardServiceFacade.getCloudIdcardByCardNo(faceResult.get("num").toString());

        if(cloudIdcardInfo == null){

            //Oss阿里云上传
            if(isOss.equals("1")){

                //正面图片
                if(StringUtils.isNotBlank(faceResult.get("localUrl").toString())){

                    JSONObject imgRes = AliyunOSSClientUtil.initUploadPath(faceResult.get("localUrl").toString());
                    if(imgRes != null){

                        faceResult.put("serverUrl",imgRes.get("imgUrl"));
                    }
                }
                //反面图片
                if(StringUtils.isNotBlank(backResult.get("localUrl").toString())){

                    JSONObject imgRes = AliyunOSSClientUtil.initUploadPath(backResult.get("localUrl").toString());
                    if(imgRes != null){

                        backResult.put("serverUrl",imgRes.get("imgUrl"));
                    }
                }
            }
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
    @ModelAttribute
    public void beforeAction(HttpServletRequest httpRequest, HttpServletResponse response)
    {


    }

}
