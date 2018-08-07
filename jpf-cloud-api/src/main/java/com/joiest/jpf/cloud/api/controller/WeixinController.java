package com.joiest.jpf.cloud.api.controller;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.joiest.jpf.cloud.api.util.MessageUtil;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.SHA1;
import com.joiest.jpf.entity.WeixinMapInfo;
import com.joiest.jpf.facade.WeixinMapServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.portable.OutputStream;
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

    @Autowired
    private WeixinMapServiceFacade weixinMapServiceFacade;

    @RequestMapping(value = "/weiIndex", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String weiIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String echostr = request.getParameter("echostr");
        String signature = request.getParameter("signature");
        String content=request.getQueryString();

        Map<String, String> requestMap = MessageUtil.parseXml(request);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求类型：事件操作");
        sbf.append("\n请求参数："+requestMap.toString());
        sbf.append("\nechostr："+echostr);
        sbf.append("\nsignature："+signature);
        String fileName = "Weixinlog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);


        if (StringUtils.isNotBlank(echostr)) {

            String isSuc = checkSignature(request);

            //返回echostr给微信服务器
            ServletOutputStream os=response.getOutputStream();
            os.write(URLEncoder.encode(echostr,"UTF-8").getBytes());
            os.flush();
            os.close();
        } else if (StringUtils.isNotBlank(signature)) {

            String res = responseMsg(request,response);
        }else{

        }



        return null;
    }

    @RequestMapping(value = "/responseMsg", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String responseMsg(HttpServletRequest request,HttpServletResponse response){

        String content=request.getQueryString();

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求类型：事件操作");
        sbf.append("\n请求参数："+content);
        String fileName = "WeixinEventlog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        return null;
    }
    @RequestMapping(value = "/checkSignature", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkSignature(HttpServletRequest request){

        String encrypt = request.getParameter("encrypt");
        String type = request.getParameter("type");
        String echostr = request.getParameter("echostr");
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String content=request.getQueryString();

        //获取对应公众号信息
        WeixinMapInfo weixinMapInfo = weixinMapServiceFacade.getWeixinMpByEncrypt(encrypt);
        if(weixinMapInfo == null) return null;

        List<String> list = new ArrayList<>();
        list.add(weixinMapInfo.getToken());
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
