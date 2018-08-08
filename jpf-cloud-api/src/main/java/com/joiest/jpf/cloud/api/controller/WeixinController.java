package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.MessageUtil;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.entity.WeixinMpInfo;
import com.joiest.jpf.entity.WeixinUserInfo;
import com.joiest.jpf.facade.WeixinMpServiceFacade;
import com.joiest.jpf.facade.WeixinUserServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("weixin")
public class WeixinController {

    private WeixinMpInfo weixinMpInfo;

    @Autowired
    private WeixinMpServiceFacade weixinMpServiceFacade;

    @Autowired
    private WeixinUserServiceFacade weixinUserServiceFacade;

    @RequestMapping(value = "/weiIndex", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String weiIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String echostr = request.getParameter("echostr");
        String signature = request.getParameter("signature");
        String encrypt = request.getParameter("encrypt");

        //获取对应公众号信息
        weixinMpInfo = weixinMpServiceFacade.getWeixinMpByEncrypt(encrypt);
        if(weixinMpInfo == null) return null;

        if (StringUtils.isNotBlank(echostr)) {

            String isSuc = checkSignature(request,weixinMpInfo);

            //返回echostr给微信服务器
            ServletOutputStream os=response.getOutputStream();
            os.write(URLEncoder.encode(echostr,"UTF-8").getBytes());
            os.flush();
            os.close();
        } else if (StringUtils.isNotBlank(signature)) {

            //Map<String, String> requestMap = MessageUtil.parseXml(request);
            Map<String, String> requestMap = new HashMap<>();
            requestMap.put("CreateTime",request.getParameter("CreateTime"));
            requestMap.put("EventKey",request.getParameter("EventKey"));
            requestMap.put("Event",request.getParameter("Event"));
            requestMap.put("ToUserName",request.getParameter("ToUserName"));
            requestMap.put("FromUserName",request.getParameter("FromUserName"));
            requestMap.put("MsgType",request.getParameter("MsgType"));
            requestMap.put("signature",request.getParameter("signature"));

            StringBuilder sbf = new StringBuilder();
            Date date = new Date();
            SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sbf.append("\n\nTime:" + myfmt.format(date));
            sbf.append("\n请求参数："+request.getQueryString());
            sbf.append("\n事件参数："+requestMap.toString());
            sbf.append("\nsignature："+signature);
            String fileName = "WeixinLog";
            LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);


            String res = responseMsg(request,response,requestMap);
        }else{
        }
        return null;
    }
    @RequestMapping(value = "/responseMsg", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String responseMsg(HttpServletRequest request,HttpServletResponse response,Map requestMap){

        String msgType = requestMap.get("MsgType").toString();
        switch (msgType){

            case MessageUtil.REQ_MESSAGE_TYPE_EVENT:

                reqMessageTypeEvent(request,requestMap);
                break;
            case MessageUtil.REQ_MESSAGE_TYPE_TEXT:


                break;
            default:

                break;
        }
        return null;
    }

    /**
     * 请求类型 -》事件 event
     * */
    @RequestMapping(value = "/reqMessageTypeEvent", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String reqMessageTypeEvent(HttpServletRequest request,Map requestMap){

        //关注事件处理
        if(requestMap.get("Event").equals("subscribe")){


            WeixinUserInfo weixinUserInfo = weixinUserServiceFacade.getWeixinMapByOpenid(requestMap.get("FromUserName").toString());
            //获取access_token
            String access_token = weixinMpServiceFacade.getAccessToken(weixinMpInfo);
            //获取用户信息
            JSONObject userInfo = new MessageUtil().getUserInfo(access_token,requestMap.get("FromUserName").toString());


        }else if(requestMap.get("Event").equals("unsubscribe")){//取消关注事件处理


        }else{

        }
        return null;
    }
    /**
     * 开发者认证
     * @param request
     * @param weixinMpInfo 公众号信息
     * */
    @RequestMapping(value = "/checkSignature", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkSignature(HttpServletRequest request,WeixinMpInfo weixinMpInfo){

        String echostr = request.getParameter("echostr");
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String content=request.getQueryString();

        List<String> list = new ArrayList<>();
        list.add(weixinMpInfo.getToken());
        list.add(nonce);
        list.add(timestamp);

        list.sort(Comparator.naturalOrder());

        String param = StringUtils.join(list,"");
        String sign = DigestUtils.shaHex(param);

        //日志记录
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求类型：微信公众号开发者认证");
        sbf.append("\n请求参数："+content);
        sbf.append("\n加密SIGN："+sign);
        String fileName = "WeixinAuthlog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        if(signature.equals(sign)){
            return echostr;
        }
        return null;
    }
}
