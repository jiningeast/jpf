package com.joiest.jpf.common.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;
import java.util.Map.Entry;


public class SignUtils {

	private static final Logger logger = LogManager
			.getLogger(SignUtils.class);


	// 生成签名
	public static String getSign(Map<String, Object> params,String key,String charset) {
		// 过滤空值
		Map<String, Object> sParaNew = paraFilter(params);
		String content = getContent(sParaNew, key);
		logger.info("原串数据:{}", content);
		String sign = Md5Encrypt.md5(content,charset);
		logger.info("摘要数据:{} ", sign);
		return sign;

	}

	// 验签
	public static boolean verify(Map<String, Object> params,String key,String charset) {
		String mysign = getSign(params,key,charset);
		String sign = (String) params.get("sign");
		return mysign.equalsIgnoreCase(sign);
	}

	/**
	 * 功能：将安全校验码和参数排序 参数集合
	 * 
	 * @param params
	 *            安全校验码
	 * @param privateKey
	 * */
	private static String getContent(Map<String, Object> params,
			String privateKey) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder sb = new StringBuilder(300);
		String key = null;
		Object value = null;
		String valueStr = null;
		for (int i = 0; i < keys.size(); i++) {
			key = keys.get(i);
			value = params.get(key);
			valueStr = value == null ? "" : value.toString();
			if (i == keys.size() - 1) {
				//prestr = prestr + key + "=" + stringValue;
				sb.append(key).append("=").append(valueStr);
			} else {
				//prestr = prestr + key + "=" + stringValue + "&";
				sb.append(key).append("=").append(valueStr).append("&");
			}
		}
		//String signParams = prestr + privateKey;
		sb.append("&key="+privateKey);
		return sb.toString();
	}

	/**
	 * 除去数组中的空值和签名参数
	 * 
	 * @param sArray
	 *            签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, Object> paraFilter(Map<String, Object> sArray) {

		Map<String, Object> result = new HashMap<String, Object>();
		if (sArray == null || sArray.size() <= 0) {
			return result;
		}
		String value = null;
		for(Entry<String, Object> entry : sArray.entrySet()){
			value = (entry.getValue() == null ? "" : entry.getValue().toString());
			if (value == null || "".equals(value)
					|| ("sign").equalsIgnoreCase(entry.getKey())
					|| ("signType").equalsIgnoreCase(entry.getKey())) {
				continue;
			}
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
}
