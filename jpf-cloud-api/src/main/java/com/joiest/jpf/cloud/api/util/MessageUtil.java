package com.joiest.jpf.cloud.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

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
     * CLICK
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";



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


}
