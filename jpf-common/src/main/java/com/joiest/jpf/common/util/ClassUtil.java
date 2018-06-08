package com.joiest.jpf.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ClassUtil {
    /**
     * 将请求参数的request类转换为map,并返回
     */
    public static Map<String, Object> requestToMap(Object obj){
        Map<String, Object> map = new HashMap<String, Object>();
        if(obj==null){
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields){
            String fieldName =  field.getName();
            if(getValueByFieldName(fieldName,obj)!=null)
                map.put(fieldName,  getValueByFieldName(fieldName,obj));
        }

        return map;

    }
    /**
     * 根据属性名获取该类此属性的值
     */
    private static Object getValueByFieldName(String fieldName,Object object){
        String firstLetter=fieldName.substring(0,1).toUpperCase();
        String getter = "get"+firstLetter+fieldName.substring(1);
        try {
            Method method = object.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(object, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }

    }

}