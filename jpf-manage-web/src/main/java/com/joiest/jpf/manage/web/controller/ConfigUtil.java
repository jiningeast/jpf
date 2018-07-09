package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.util.PropertyUtil;

public class ConfigUtil {

	public static final String CONFIG_FILE_NAME = "config/config";

	// 获取银联接口请求地址
	public static String getValue(String key){
		PropertyUtil sysUtil = PropertyUtil.get(CONFIG_FILE_NAME);
		return sysUtil.getAsString(key);
	}
}
