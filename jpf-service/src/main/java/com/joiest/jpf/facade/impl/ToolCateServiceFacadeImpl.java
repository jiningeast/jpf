package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.ToolCateRequest;
import com.joiest.jpf.dto.ToolCateResponse;
import com.joiest.jpf.entity.MwSmsInfo;
import com.joiest.jpf.facade.ToolCateServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.text.SimpleDateFormat;
import java.util.*;

public class ToolCateServiceFacadeImpl implements ToolCateServiceFacade {

    private String MethodPost = "POST";

    //实名认证 接口公共参数======start
        private String IdenHost = "https://idenauthen.market.alicloudapi.com";
        private String IdPath = "/idenAuthentication";
        private String AppCode = "92f6237c503845559542c5d26f9adab2";;
    //实名认证 接口公共参数======end

    //OCR 接口公共参数======start
        private String OcrHost = "http://dm-51.data.aliyun.com";
        private String OcrPath = "/rest/160601/ocr/ocr_idcard.json";
        private String OcrAppKey = "24789083";
        private String OcrAppSecret = "bb49c2a1f0d00d09ee83c56f23a0f5cd";
    //OCR 接口公共参数======end

    //梦网短信发送公共参数====start
        private String  MwMongateSendSubmitUrl  = "http://61.145.229.26:8086/MWGate/wmgw.asmx/MongateSendSubmit";
        private String  MwUserId = "J26100";
        private String  MwPassword = "658844";
    //梦网短信发送公共参数====end

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
     * base64 encode 转换为图片
     * */
    @Override
    public Map<String,String> baseToImage(HttpServletRequest request, String imgStr, String perfix){

        Map<String,String> imgInfo = new HashMap<>();

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

            //生成jpeg图
            String showUrl = request.getSession().getServletContext().getRealPath("/resources");

            String resourcesUrl = "\\resources\\"+perfix+"\\";
            String urlRepj = showUrl.replace("\\target\\jpf-cloud\\resources","\\src\\main\\webapp"+resourcesUrl);

            File fileDir = new File(urlRepj);
            if (!fileDir.exists()){

                fileDir.mkdirs();
            }
            String filename = perfix+timeStamp+".jpg";
            String imgFilePath = urlRepj+"/"+filename;//新生成的图片

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            //dealImgInfo = imgInfo.replaceAll("^(data:\\s*image\\/(\\w+);base64,)", "");

            imgInfo.put("actualUrl",imgFilePath);//服务器实际路径
            imgInfo.put("filename",filename);//文件名
            imgInfo.put("resourceUrl",resourcesUrl+filename);//域名对应图片地址 如：http://xxx.com/图片存储地址

            return imgInfo;
        }catch (Exception e) {

            return null;
        }
    }
    /**
     * 图片转换为base64
     * */
    @Override
    public String imageToBase(String imgFile) {

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
        return imgBase64;
    }
    /**
    * 流信息获取
     * **/
    @Override
    public ToolCateResponse convert(HttpResponse response)  throws IOException {

        ToolCateResponse res = new ToolCateResponse();
        if (null != response) {

            res.setStatusCode(response.getStatusLine().getStatusCode());
            for (Header header : response.getAllHeaders()) {
                res.setHeader(header.getName(), MessageDigestUtil.iso88591ToUtf8(header.getValue()));
            }
            res.setContentType(res.getHeader("Content-Type"));
            res.setRequestId(res.getHeader("X-Ca-Request-Id"));
            res.setErrorMessage(res.getHeader("X-Ca-Error-Message"));
            res.setBody(readStreamAsStr(response.getEntity().getContent()));

        } else {
            //服务器无回应
            res.setStatusCode(500);
            res.setErrorMessage("No Response");
        }
        return res;
    }

    /**
     * 将流转换为字符串
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String readStreamAsStr(InputStream is) throws IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        WritableByteChannel dest = Channels.newChannel(bos);
        ReadableByteChannel src = Channels.newChannel(is);
        ByteBuffer bb = ByteBuffer.allocate(4096);

        while (src.read(bb) != -1) {
            bb.flip();
            dest.write(bb);
            bb.clear();
        }
        src.close();
        dest.close();
        return new String(bos.toByteArray(), "UTF-8");
    }
    /**
     * 短信息发送接口（相同内容群发，可自定义流水号）
     * @param strMobiles 手机号
     * @param strMessage 短信内容
     * @return 0:成功 非0:返回webservice接口返回的错误代码
     */
    @Override
    public int sendSms(String strMobiles, String strMessage)
    {

        StringBuffer strPtMsgId = new StringBuffer("");//存储日志。

        int returnInt=-200;//定义返回值变量
        String strUserMsgId = createOrderid();
        String strSubPort = "*";//扩展子号 （不带请填星号*，长度不大于6位）;

        String Message = null;
        MwSmsInfo param = new MwSmsInfo();
        try {

            String result = null;//存放解析后的返回值

            param.setUserId(MwUserId);//设置账号
            param.setPassword(MwPassword);//设置密码
            param.setPszMobis(strMobiles);//设置手机号码
            param.setPszMsg(strMessage);//设置短信内容
            param.setIMobiCount(String.valueOf(strMobiles.split(",").length));//设置手机号码个数
            param.setPszSubPort(strSubPort);//设置扩展子号
            param.setMsgId(strUserMsgId);//设置流水号

            Message = OkHttpUtils.executePost(param, MwMongateSendSubmitUrl);//调用底层POST方法提交
            //请求返回值不为空，则解析返回值
            if(Message != null&& Message != "")
            {
                Document doc= DocumentHelper.parseText(Message);
                Element el = doc.getRootElement();
                result = el.getText();//解析返回值
            }
            //处理返回结果
            if(result != null && !"".equals(result) && result.length()>10){

                //解析后的返回值不为空且长度大于10，则是提交成功
                returnInt=0;
                strPtMsgId.append(result);
            }else if(result==null||"".equals(result)){//解析后的返回值为空，则提交失败

                strPtMsgId.append(returnInt);
            }else{//解析后的返回值不为空且长度不大于10，则提交失败，返回错误码				                       				returnInt=Integer.parseInt(result);
                strPtMsgId.append(returnInt);
            }
        } catch (Exception e) {

            returnInt=-200;
            strPtMsgId.append(returnInt);
            e.printStackTrace();//异常处理
        }
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址：" + MwMongateSendSubmitUrl);
        sbf.append("\n接口参数：" + param.toString());
        sbf.append("\n回调信息：" + Message);
        String fileName = "MwSmslog";

        LogsCustomUtils.writeIntoFile(sbf.toString(),"", fileName,true);

        return returnInt;//返回值返回
    }
    /**
     *阿里云OCR 身份证识别
     * */
    public String idCardOcr(HttpServletRequest request,String side,String imgInfo){

        String dealImgInfo = imgInfo.replaceAll("^(data:\\s*image\\/(\\w+);base64,)", "");

        JSONObject lastRes = new JSONObject();
        Boolean is_old_format = true; //如果文档的输入中含有inputs字段，设置为True， 否则设置为False

        //请根据线上文档修改configure字段
        JSONObject configObj = new JSONObject();
        configObj.put("side", side);
        String config_str = configObj.toString();

        //base64转换为图片
        Map<String, String> imgUrl = baseToImage(request,dealImgInfo,"OCR");
        //域名访问路径
        String requestUrl = request.getRequestURL().toString();
        String resourceUrl = requestUrl.replace("/toolcate/idCardOcr",imgUrl.get("resourceUrl"));

        //获取图片实际路径
        String imgFile = imgUrl.get("actualUrl");
        //根据图片地址获取base64
        String imgBase64  = imageToBase(imgFile);

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
                map.put("HttpCode",toolCateResponse.getHeader("X-Ca-Error-Message"));
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
        return lastRes.toString();
    }

    /**
     *阿里云身份证、姓名实名认证
     * */
    @Override
    public JSONObject idenAuth(String name,String idCard){

        Map<String, String> headers = new HashMap<String, String>();

        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + AppCode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        Map<String, String> querys = new HashMap<String, String>();

        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("idNo", idCard);
        bodys.put("name", name);

        ToolCateResponse toolCateResponse = new ToolCateResponse();//接收响应值
        Map<String,String> respos = new HashMap<String,String>();//参数返回
        String res=null;
        JSONObject dealFirst = null;
        JSONObject ocrResult = new JSONObject();
        JSONObject data = null;

        try {

            HttpResponse response = OkHttpUtils.httpPostIdenAuth(IdenHost, IdPath, MethodPost, headers, querys, bodys);
            toolCateResponse = convert(response);

            if(toolCateResponse.getStatusCode() != 200){

                System.out.println("Http code: " + toolCateResponse.getStatusCode());
                System.out.println("Http header error: " + toolCateResponse.getHeader("X-Ca-Error-Message"));
                System.out.println("Http body error msg: " + toolCateResponse.getBody());

                data.put("httpCode",toolCateResponse.getHeader("X-Ca-Error-Message"));
                data.put("HttpBodyError",toolCateResponse.getBody());

                ocrResult.put("code","10008");
                ocrResult.put("info","实名认证有误");
                ocrResult.put("data",data);
            }else {

                res = toolCateResponse.getBody();
                dealFirst = JSONObject.fromObject(res);
                if(dealFirst.get("respCode").equals("0000")){

                    ocrResult.put("code","10000");
                    ocrResult.put("info","实名认证一致");
                    ocrResult.put("data",dealFirst);
                }else{

                    ocrResult.put("code","10008");
                    ocrResult.put("info",dealFirst.get("respMessage"));
                    ocrResult.put("data",dealFirst);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ocrResult;
    }
    /**
     * 生成一个唯一数
     * */

    public String createOrderid(){

        int pre = getRandomInt(100,999);
        int last = getRandomInt(100,999);
        String middle = String.valueOf(System.currentTimeMillis());
        middle = middle.substring(3,middle.length());

        return ""+pre+middle+last;
    }
    /*
    *   生成指定范围内的随机整数
    **/
    @Override
    public int getRandomInt(int min, int max){

        Random random = new Random();
        int randomInt = random.nextInt(max)%(max-min+1) + min;

        return randomInt;
    }
}
