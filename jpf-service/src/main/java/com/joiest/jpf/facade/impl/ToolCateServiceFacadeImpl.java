package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.util.MessageDigestUtil;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dto.ToolCateResponse;
import com.joiest.jpf.entity.MwSmsInfo;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ToolCateServiceFacadeImpl {

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
     * @param strPtMsgId 平台返回的流水号
     * @param strMobiles 手机号
     * @param strMessage 短信内容
     * @param strSubPort 扩展子号
     * @param strUserMsgId 用户自编流水号
     * @return 0:成功 非0:返回webservice接口返回的错误代码
     */
    public int SendSms(StringBuffer strPtMsgId, String strMobiles, String strMessage, String strSubPort, String strUserMsgId)
    {
        /*int returnInt=-200;//定义返回值变量
        int sameErrorCount = 0;
        try {
            String result = null;//存放解析后的返回值

            MwSmsInfo p = new MwSmsInfo();
            //Params p = new Params();
            p.setUserId(MWUSERNAME);//设置账号
            p.setPassword(strPwd);//设置密码
            p.setPszMobis(strMobiles);//设置手机号码
            p.setPszMsg(strMessage);//设置短信内容
            p.setIMobiCount(String.valueOf(strMobiles.split(",").length));//设置手机号码个数
            p.setPszSubPort(strSubPort);//设置扩展子号
            p.setMsgId(strUserMsgId);//设置流水号

            String Message = OkHttpUtils.executePost(p, "http://"+ip+":"+port+"/MWGate/wmgw.asmx/"+
                    "MongateSendSubmit");//调用底层POST方法提交
            //请求返回值不为空，则解析返回值
            if(Message != null&& Message != "")
            {
                Document doc= DocumentHelper.parseText(Message);
                Element el = doc.getRootElement();
                result = el.getText();//解析返回值
            }
            //处理返回结果
            if(result != null&& !"".equals(result)&&result.length()>10){
                //解析后的返回值不为空且长度大于10，则是提交成功
                returnInt=0;
                strPtMsgId.append(result);
            }else if(result==null||"".equals(result)){//解析后的返回值为空，则提交失败

                strPtMsgId.append(returnInt);
            }else{//解析后的返回值不为空且长度不大于10，则提交失败，返回错误码				                       				returnInt=Integer.parseInt(result);
                strPtMsgId.append(returnInt);
            }
        } catch (Exception e) {

            sameErrorCount=sameErrorCount+1; //发送失败,发送失败次数加1
            returnInt=-200;
            strPtMsgId.append(returnInt);
            e.printStackTrace();//异常处理
        }

        return returnInt;//返回值返回*/
        return 2;
    }
}
