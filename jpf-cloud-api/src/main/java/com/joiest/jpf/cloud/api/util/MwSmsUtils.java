package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.entity.MwSmsInfo;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MwSmsUtils {

    //梦网短信发送公共参数====start
        private String  MwMongateSendSubmitUrl  = "http://61.145.229.26:8086/MWGate/wmgw.asmx/MongateSendSubmit";
        private String  MwUserId = "J26100";
        private String  MwPassword = "658844";
    //梦网短信发送公共参数====end

    /**
     * 短信息发送接口（相同内容群发，可自定义流水号）
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
        sbf.append("\n请求地址：" + MwMongateSendSubmitUrl);
        sbf.append("\n接口参数：" + JSONObject.fromObject(param));
        sbf.append("\n回调信息：" + Message);
        String fileName = "MwSmslog";

        LogsCustomUtils.writeIntoFile(sbf.toString(),"", fileName,true);

        return returnInt;//返回值返回
    }
}
