package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.MessageDigestUtil;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dto.ToolCateResponse;
import com.joiest.jpf.entity.MwSmsInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ToolCateServiceFacadeImpl {

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


    /**
     * base64 encode 转换为图片
     * */
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
            fileDir.mkdirs();

            String filename = perfix+timeStamp+".jpg";
            String imgFilePath = urlRepj+"/"+filename;//新生成的图片

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            imgInfo.put("actualUrl",imgFilePath);
            imgInfo.put("filename",filename);
            imgInfo.put("resourceUrl",resourcesUrl+filename);

            return imgInfo;
        }catch (Exception e) {

            return null;
        }
    }
    /**
    * 流信息获取
     * **/
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
     * @param strUserId  帐号
     * @param strPwd 密码
     * @param strMobiles 手机号
     * @param strMessage 短信内容
     * @return 0:成功 非0:返回webservice接口返回的错误代码
     */
    public int SendSms(String url,String strUserId, String strPwd, String strMobiles, String strMessage)
    {

        StringBuffer strPtMsgId = new StringBuffer("");//存储日志。

        int returnInt=-200;//定义返回值变量
        String strUserMsgId = createOrderid();
        String strSubPort = "*";//扩展子号 （不带请填星号*，长度不大于6位）;

        String Message = null;
        MwSmsInfo param = new MwSmsInfo();
        try {

            String result = null;//存放解析后的返回值

            param.setUserId(strUserId);//设置账号
            param.setPassword(strPwd);//设置密码
            param.setPszMobis(strMobiles);//设置手机号码
            param.setPszMsg(strMessage);//设置短信内容
            param.setIMobiCount(String.valueOf(strMobiles.split(",").length));//设置手机号码个数
            param.setPszSubPort(strSubPort);//设置扩展子号
            param.setMsgId(strUserMsgId);//设置流水号

            Message = OkHttpUtils.executePost(param, url);//调用底层POST方法提交
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
        sbf.append("\n请求地址：" + url);
        sbf.append("\n接口参数：" + param.toString());
        sbf.append("\n回调信息：" + Message);
        String fileName = "MwSmslog";

        LogsCustomUtils.writeIntoFile(sbf.toString(),"", fileName,true);

        return returnInt;//返回值返回
    }

    /**
     *阿里云OCR 身份证识别
     * */
    public String idCardOcr(String side,String img){


        return null;
    }

    /**
     *阿里云身份证、姓名实名认证
     * */
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
    // 生成指定范围内的随机整数
    public int getRandomInt(int min, int max){

        Random random = new Random();
        int randomInt = random.nextInt(max)%(max-min+1) + min;

        return randomInt;
    }
}
