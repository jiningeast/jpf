package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.entity.MwSmsInfo;
import com.joiest.jpf.entity.MwmultSmsInfo;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MwSmsUtils {

    /*
    //梦网短信发送公共参数====start
    private String  MwMongateSendSubmitUrl  = "http://61.145.229.26:8086/MWGate/wmgw.asmx/MongateSendSubmit";
    private String  MwUserId = "J26100";
    private String  MwPassword = "658844";

   //测试参数
    private String  MwMongateSendSubmitUrl  = "http://175.25.18.221:8087/MWGate/wmgw.asmx/MongateSendSubmit";
    private String  MwUserId = "JA1163";
    private String  MwPassword = "426210";
    //梦网短信发送公共参数====end
    */
    /**
     * 短信息发送接口
     * @param strMobiles 手机号
     * @param strMessage 短信内容
     * @return 0:成功 非0:返回webservice接口返回的错误代码
     */
    public int sendSms(String strMobiles, String strMessage)
    {
        StringBuffer strPtMsgId = new StringBuffer("");//存储日志。

        int returnInt=-200;//定义返回值变量
        String strUserMsgId = ToolsUtils.createOrderid();
        String strSubPort = "*";//扩展子号 （不带请填星号*，长度不大于6位）;

        String Message = null;
        MwSmsInfo param = new MwSmsInfo();
        try {

            String result = null;//存放解析后的返回值

            param.setUserId(ConfigUtil.getValue("MW_USERID"));//设置账号
            param.setPassword(ConfigUtil.getValue("MW_PASSWORD"));//设置密码
            param.setPszMobis(strMobiles);//设置手机号码
            param.setPszMsg(strMessage);//设置短信内容
            param.setIMobiCount(String.valueOf(strMobiles.split(",").length));//设置手机号码个数
            param.setPszSubPort(strSubPort);//设置扩展子号
            param.setMsgId(strUserMsgId);//设置流水号

            Message = OkHttpUtils.executeMwPost(param, ConfigUtil.getValue("MW_MONGATESENDSUBMITURL"));//调用底层POST方法提交
            //请求返回值不为空，则解析返回值
            if(Message != null&& Message != "") {

                Document doc= DocumentHelper.parseText(Message);
                Element el = doc.getRootElement();
                result = el.getText();//解析返回值
            }
            //处理返回结果
            if(result != null && !"".equals(result) && result.length()>10){

                //解析后的返回值不为空且长度大于10，则是提交成功
                returnInt=0;
            }else if(result==null||"".equals(result)){//解析后的返回值为空，则提交失败

            }else{//解析后的返回值不为空且长度不大于10，则提交失败，返回错误码

                returnInt=Integer.parseInt(result);
            }
        } catch (Exception e) {

            returnInt=-200;
            e.printStackTrace();//异常处理
        }
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求地址：" + ConfigUtil.getValue("MW_MONGATESENDSUBMITURL"));
        sbf.append("\n接口参数：" + JSONObject.fromObject(param));
        sbf.append("\n回调信息：" + Message);
        String fileName = "MwSmslog";

        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        return returnInt;//返回值返回
    }
    /**
     * 短信息发送接口
     * @return 0:成功 非0:返回webservice接口返回的错误代码
     */
    public int SendMultixSms(List<MwmultSmsInfo> MultixMt)
    {
        int returnInt=-200;
        try {
            String result = null;
            StringBuffer multixmt =new StringBuffer();//批量请求包字符串

            MwSmsInfo param = new MwSmsInfo();
            param.setUserId(ConfigUtil.getValue("MW_USERID"));//设置账号
            param.setPassword(ConfigUtil.getValue("MW_PASSWORD"));//设置密码

            for(int j=0;j<MultixMt.size();j++)
            {
                //循环组装批量请求包
                MwmultSmsInfo multixMt = MultixMt.get(j);
                multixmt.append(multixMt.getStrUserMsgId()).append("|");//设置流水号
                multixmt.append(multixMt.getStrSpNumber()).append("|");//设置通道号
                multixmt.append(multixMt.getStrMobile()).append("|");//设置手机号码
                String strBase64Msg = new String(Base64.encodeBase64(multixMt.getStrBase64Msg().getBytes("GBK")));//设置短信内容
                multixmt.append(strBase64Msg).append(",");
            }
            String Multixmt = multixmt.substring(0,multixmt.length()-1);//截取最后一个逗号
            param.setMultixmt(Multixmt);//设置批量请求包

            String Message = OkHttpUtils.executeMwPost(param, ConfigUtil.getValue("MW_MONGATEMULTIXSENDURL"));//调用底层POST方法提交
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
            }else if(result==null||"".equals(result)){//解析后的返回值为空，则提交失败

            }else{//解析后的返回值不为空且长度不大于10，则提交失败，返回错误码

                returnInt=Integer.parseInt(result);
            }
        } catch (Exception e) {

            returnInt=-200;
            e.printStackTrace();//异常处理
        }
        return returnInt;//返回返回值
    }
}
