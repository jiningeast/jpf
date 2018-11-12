package com.joiest.jpf.common.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class JsonUtils {
    private static Logger logger = LogManager.getLogger(JsonUtils.class);

    private static final ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        //去掉默认的时间戳格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //设置为中国上海时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        //空值不序列化
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        //反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //序列化时，日期的统一格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //单引号处理
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> String toJson(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonGenerationException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static <T> T toCollection(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonParseException e) {
            logger.error(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * xml字符串转换成json对象
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static JSONObject xmlToJson(String xml) throws DocumentException {
        JSONObject jsonObject = new JSONObject();
        Document document = DocumentHelper.parseText(xml);
        Element rootElement = document.getRootElement();
        iterator(rootElement,jsonObject);
        return jsonObject;
    }

    /**
     * 递归调用遍历dom树上的每一个节点
     * @param node
     * @param json
     */
    public static void iterator(Element node, JSONObject json){
        String nodeName = node.getName();
        if(json.containsKey(nodeName)){
            //该元素在同级下有多个
            Object Object = json.get(nodeName);
            JSONArray array = null;
            if(Object instanceof JSONArray){
                array = (JSONArray) Object;
            }else{
                array = new JSONArray();
                array.add(Object);
            }
            List<Element> listElement = node.elements();
            if(listElement.isEmpty()){
                String nodeValue = node.getTextTrim();
                array.add(nodeValue);
                json.put(nodeName, array);
                return;
            }
            JSONObject newJson = new JSONObject();
            for (Element e:listElement) {
                iterator(e,newJson);
            }
            array.add(newJson);
            json.put(nodeName,array);
            return;
        }
        List<Element> listElement = node.elements();
        if(listElement.isEmpty()){
            String nodeValue = node.getTextTrim();
            json.put(nodeName, nodeValue);
            return ;
        }
        JSONObject object = new JSONObject();
        for (Element e:listElement) {
            iterator(e,object);
        }
        json.put(nodeName,object);
        return;
    }
    
    public static void main(String[] args){
    	Map<String,Object> map = new HashMap<String ,Object>();
    	map.put("zjf", "hehe");
    	map.put("nyt", "hehe");
    	System.out.println(	JsonUtils.toJson(map));

    	String ss = "{\"code\":10000,\"info\":\"SUCCESS\"}";
        Map<String, String> map1 = JsonUtils.toCollection(ss, new TypeReference<HashMap<String, String>>(){});
        System.out.println(	map1.get("code"));
        System.out.println(	map1.get("info"));
    
    }
}