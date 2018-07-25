package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.entity.MwSmsInfo;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

            param.setUserId(ConfigUtil.getValue("MW_USERID"));//设置账号
            param.setPassword(ConfigUtil.getValue("MW_PASSWORD"));//设置密码
            param.setPszMobis(strMobiles);//设置手机号码
            param.setPszMsg(strMessage);//设置短信内容
            param.setIMobiCount(String.valueOf(strMobiles.split(",").length));//设置手机号码个数
            param.setPszSubPort(strSubPort);//设置扩展子号
            param.setMsgId(strUserMsgId);//设置流水号

            Message = OkHttpUtils.executePost(param, ConfigUtil.getValue("MW_MONGATESENDSUBMITURL"));//调用底层POST方法提交
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
        sbf.append("\n请求地址：" + ConfigUtil.getValue("MW_MONGATESENDSUBMITURL"));
        sbf.append("\n接口参数：" + JSONObject.fromObject(param));
        sbf.append("\n回调信息：" + Message);
        String fileName = "MwSmslog";

        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        return returnInt;//返回值返回
    }


    /**
     * 短信息发送接口（不同内容群发，可自定义不同流水号，自定义不同扩展号）
     * @param ip IP地址
     * @param port 端口
     * @param strUserId 账号
     * @param strPwd 密码
     */
    /*public static void SendMultixSms(String ip,String port,String strUserId,String strPwd){

        MwSmsInfo param = new MwSmsInfo();
        try{
            List<MwSmsInfo> multixMts = new ArrayList<MwSmsInfo>();
            param.setStrMobile("138XXXXXXXX");//手机号码
            param.setStrBase64Msg("短信测试");//短信内容，内容长度不大于350个汉字
            param.setStrSpNumber("*");//通道号，不需要请填*
            param.setStrUserMsgId("0");//用户自定义流水号，不带请输入0（流水号范围-（2^63）……2^63-1）
            param.add(multixMt1);//添加一条短信

            MULTIX_MT  multixMt2= new MULTIX_MT();
            multixMt2.setStrMobile("159XXXXXXXX");//手机号码
            multixMt2.setStrBase64Msg("短信测试");//短信内容，内容长度不大于350个汉字
            multixMt2.setStrSpNumber("*");//通道号，不需要请填*
            multixMt2.setStrUserMsgId("0");//用户自定义流水号，不带请输入0（流水号范围-（2^63）……2^63-1）
            multixMts.add(multixMt2);//添加一条短信
            CHttpPost sms=new CHttpPost();//短信请求业务类
            StringBuffer strPtMsgId=new StringBuffer("");//如果成功，存流水号。失败，存错误码。
            int result= sms.SendMultixSms(strPtMsgId, ip,port,strUserId, strPwd, multixMts);
            //短信息发送接口（不同内容群发，可自定义不同流水号，自定义不同扩展号）POST请求。
            if(result==0){//返回0，则提交成功
                System.out.println("发送成功："+strPtMsgId.toString());//打印流水号
            }else{//返回非0，则提交失败
                System.out.println("发送失败："+strPtMsgId.toString());//打印错误码
            }
        }catch (Exception e) {
            e.printStackTrace();//异常处理
        }

    }*/
}
