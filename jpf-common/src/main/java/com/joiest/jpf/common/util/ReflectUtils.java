package com.joiest.jpf.common.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ReflectUtils.class);

	public static Map<String, Object> reflect(Object obj) {
		Map<String, Object> mapping = new HashMap<String, Object>();
		Method reader = null;
		Object value = null;
		try {
			BeanInfo info = Introspector.getBeanInfo(obj.getClass());
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                reader = pd.getReadMethod();
                //内容为null的过滤掉
                if (reader == null) {
                    continue;
                }
                value = reader.invoke(obj);
                if(value == null){
                    continue;
                }
                //默认继承Object类的属性，过滤掉
                if (pd.getName().equalsIgnoreCase("class")) {
                    continue;
                }
                mapping.put(pd.getName(), value);
            }
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return mapping;
	}
}
