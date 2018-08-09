package com.joiest.jpf.cloud.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.OkHttpUtils;

import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtil {


    /**
     * text
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * music
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * news
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * text
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * image
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * link
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * location
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * voice
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * video
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

    /**
     * shortvideo
     */
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

    /**
     * event
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * subscribe
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * unsubscribe
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 固定地址
     * */
    public static final String HTTPS_URL = "https://api.weixin.qq.com/";

    /**
     * CLICK
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    public String curTime = null;
    public Date dateTime = null;
    public MessageUtil(){

        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        curTime = myfmt.format(date);
        dateTime = date;
    }

    public static Map<String,String> parseXml(HttpServletRequest request){

        Map<String,String> messageMap=new HashMap<String, String>();

        InputStream inputStream=null;
        try {
            //读取request Stream信息
            inputStream=request.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        SAXReader reader = new SAXReader();
        Document document=null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Element root=document.getRootElement();
        List<Element> elementsList=root.elements();

        for(Element e:elementsList){
            messageMap.put(e.getName(),e.getText());
        }
        try {
            inputStream.close();
            inputStream=null;
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return messageMap;
    }

    public JSONObject getUserInfo(String access_token,String openid){

        Map<String, Object> map = new HashMap<>();
        String getUserInfoUrl = HTTPS_URL+"cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";

        JSONObject res = JSONObject.fromObject(OkHttpUtils.postForm(getUserInfoUrl,map));


        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + curTime);
        sbf.append("\n请求类型：微信公众号获取用户基本信息");
        sbf.append("\n请求地址："+getUserInfoUrl);
        sbf.append("\n获取用户信息接口参数："+res);
        String fileName = "WeixinUserInfoLog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        return res;
    }
}