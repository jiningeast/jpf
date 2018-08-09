package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.MessageUtil;
import com.joiest.jpf.common.util.DateUtils;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

            Map<String, String> requestMap = MessageUtil.parseXml(request);
            /*Map<String, String> requestMap = new HashMap<>();
            requestMap.put("CreateTime",request.getParameter("CreateTime"));
            requestMap.put("EventKey",request.getParameter("EventKey"));
            requestMap.put("Event",request.getParameter("Event"));
            requestMap.put("ToUserName",request.getParameter("ToUserName"));
            requestMap.put("FromUserName",request.getParameter("FromUserName"));
            requestMap.put("MsgType",request.getParameter("MsgType"));
            requestMap.put("signature",request.getParameter("signature"));
*/
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


        if(requestMap.get("Event").toString().contains("subscribe")){

            //获取是否有当前微信用户信息
            WeixinUserInfo weixinUserInfo = weixinUserServiceFacade.getWeixinUserByOpenid(requestMap.get("FromUserName").toString(),weixinMpInfo.getId());
            //获取access_token
            String access_token = weixinMpServiceFacade.getAccessToken(weixinMpInfo);
            //获取用户信息
            JSONObject userInfo = new MessageUtil().getUserInfo(access_token,requestMap.get("FromUserName").toString());
            //关注取消事件处理
            Map<String,String> userData = dealUserInfo(weixinMpInfo,weixinUserInfo,userInfo);
        }
       return null;
    }
    /*
    * 微信用户信息处理
    * */
    public Map<String,String> dealUserInfo(WeixinMpInfo weixinMpInfo,WeixinUserInfo weixinUserInfo,JSONObject userInfo){

        Map<String ,String> userData = new HashMap<>();

        userData.put("mpid",weixinMpInfo.getId().toString());
        userData.put("openid",userInfo.get("openid").toString());
        userData.put("subscribe",userInfo.get("subscribe").toString());

        if(userInfo.get("subscribe").toString().equals("1")){

            userData.put("nickname",userInfo.get("nickname").toString());
            String nickname = null;
            try {
                nickname = URLEncoder.encode(userInfo.get("nickname").toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            userData.put("nicknameEncode",nickname);
            userData.put("sex",userInfo.get("sex").toString());
            userData.put("language",userInfo.get("language").toString());
            userData.put("city",userInfo.get("city").toString());
            userData.put("province",userInfo.get("province").toString());
            userData.put("country",userInfo.get("country").toString());
            userData.put("headimgurl",userInfo.get("headimgurl").toString());

            String subTime = DateUtils.stampToDate(userInfo.get("subscribe_time").toString());

            userData.put("subscribeTime",subTime);

            if(userInfo.containsKey("unionid"))
                userData.put("unionid",userInfo.get("unionid").toString());
            else
                userData.put("unionid","");

            userData.put("remark",userInfo.get("remark").toString());
            userData.put("groupid",userInfo.get("groupid").toString());
            userData.put("tagid_list",userInfo.get("tagid_list").toString());
            userData.put("subscribe_scene",userInfo.get("subscribe_scene").toString());
            userData.put("qr_scene",userInfo.get("qr_scene").toString());
            userData.put("qr_scene_str",userInfo.get("qr_scene_str").toString());

            if(weixinUserInfo!=null){

                weixinUserServiceFacade.upWeixinUserById(userData,weixinUserInfo.getId());
            }else{

                weixinUserServiceFacade.addWeixinUser(userData);
            }
        }else{

            weixinUserServiceFacade.upWeixinUserPartById(userData,weixinUserInfo.getId());
        }
        return userData;
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