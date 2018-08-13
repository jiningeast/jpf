package com.joiest.jpf.cloud.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.OkHttpUtils;

import com.joiest.jpf.entity.TextMessageInfo;
import com.joiest.jpf.entity.WeixinMpInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtil {


    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 返回消息类型：图片
     */
    public static final String RESP_MESSAGE_TYPE_Image = "image";

    /**
     * 返回消息类型：语音
     */
    public static final String RESP_MESSAGE_TYPE_Voice = "voice";

    /**
     * 返回消息类型：视频
     */
    public static final String RESP_MESSAGE_TYPE_Video = "video";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

    /**
     * shortvideo
     */
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 事件类型：VIEW(自定义菜单URl视图)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";

    /**
     * 事件类型：LOCATION(上报地理位置事件)
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    /**
     * 事件类型：LOCATION(上报地理位置事件)
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";

    /**
     * 固定地址
     * */
    public static final String HTTPS_URL = "https://api.weixin.qq.com/";

    public static final String HTTPS_API_URL = " https://api.weixin.qq.com/";


    public String curTime = null;
    public Date dateTime = null;
    public MessageUtil(){

        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        curTime = myfmt.format(date);
        dateTime = date;
    }
    /**
     * @Description: 解析微信发来的请求（XML）
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @author dapengniao
     * @date 2016年3月7日 上午10:04:02
     */
    public static Map<String,String> parseXml(HttpServletRequest request){

        Map<String,String> messageMap=new HashMap<>();

        InputStream inputStream=null;
        try {
            //读取request Stream信息
            inputStream=request.getInputStream();
        } catch (IOException e) {

            e.printStackTrace();
        }
        SAXReader reader = new SAXReader();
        Document document=null;
        try {
            document = reader.read(inputStream);
        } catch (DocumentException e) {

            e.printStackTrace();
        }
        Element root=document.getRootElement();
        List<Element> elementsList=root.elements();

        for(Element e:elementsList){
            messageMap.put(e.getName(),e.getText());
        }
        try {
            inputStream.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return messageMap;
    }

    /**
     * @Description: 文本消息对象转换成xml
     * @param @param textMessage
     * @param @return
     * @author dapengniao
     * @date 2016年3月8日 下午4:13:22
     */
    public static String textMessageToXml(TextMessageInfo textMessageInfo) {
        xstream.alias("xml", textMessageInfo.getClass());
        return xstream.toXML(textMessageInfo);
    }
    /**
     * 对象到xml的处理
     */
    private static XStream xstream = new XStream(new XppDriver() {

        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
    /**
     * 基础接口获取用户信息
     * */
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
    /**
     * 获取网页授权access_token
     * */
    public JSONObject getWebAccessToken(HttpServletRequest request, WeixinMpInfo weixinMpInfo){

        Map<String, Object> map = new HashMap<>();

        String code = request.getParameter("code");
        String url = HTTPS_API_URL+"sns/oauth2/access_token?appid="+weixinMpInfo.getAppid()+"&secret="+weixinMpInfo.getAppsecret()+"&code="+code+"&grant_type=authorization_code";

        JSONObject res = JSONObject.fromObject(OkHttpUtils.postForm(url,map));

        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + curTime);
        sbf.append("\n请求类型：微信公众号通过code换取网页授权access_token");
        sbf.append("\n请求地址："+url);
        sbf.append("\n获取接口参数："+res);
        String fileName = "WeixinWebAccessTokenLog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);

        if(res.containsKey("openid")){

            return res;
        }
        return null;
    }
    /**
     * 网页授权获取用户信息
     * */
    public JSONObject snsapiUserinfo(String accessToken,String openid){

        Map<String, Object> map = new HashMap<>();

        String userInfoUrl = HTTPS_API_URL+"sns/userinfo?access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";

        JSONObject res = JSONObject.fromObject(OkHttpUtils.postForm(userInfoUrl,map));

        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + curTime);
        sbf.append("\n请求类型：微信公众号授权获取用户信息snsapi_serinfo");
        sbf.append("\n请求地址："+userInfoUrl);
        sbf.append("\n获取接口参数："+res);
        String fileName = "WeixinSnsapiUserinfoLog";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);


        if(res.containsKey("openid")){

            return res;
        }
        return null;
    }
}
