package com.joiest.jpf.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.MalformedURLException;
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
		sb.append("&privatekey="+privateKey);
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

	/**
	 * OCR 计算签名
	 *
	 * @param secret APP密钥
	 * @param method HttpMethod
	 * @param path
	 * @param headers
	 * @param querys
	 * @param bodys
	 * @param signHeaderPrefixList 自定义参与签名Header前缀
	 * @return 签名后的字符串
	 */
	public static String OcrSign(String secret, String method, String path,
							  Map<String, String> headers,
							  Map<String, String> querys,
							  Map<String, String> bodys,
							  List<String> signHeaderPrefixList) {
		try {
			Mac hmacSha256 = Mac.getInstance("HmacSHA256");
			byte[] keyBytes = secret.getBytes("UTF-8");
			hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));

			return new String(Base64.encodeBase64(
					hmacSha256.doFinal(buildStringToSign(method, path, headers, querys, bodys, signHeaderPrefixList)
							.getBytes("UTF-8"))),
					"UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Ocr 构建待签名字符串
	 * @param method
	 * @param path
	 * @param headers
	 * @param querys
	 * @param bodys
	 * @param signHeaderPrefixList
	 * @return
	 */
	private static String buildStringToSign(String method, String path,
											Map<String, String> headers,
											Map<String, String> querys,
											Map<String, String> bodys,
											List<String> signHeaderPrefixList) {
		StringBuilder sb = new StringBuilder();

		sb.append(method.toUpperCase()).append("\n");
		if (null != headers) {
			if (null != headers.get("Accept")) {
				sb.append(headers.get("Accept"));
			}
			sb.append("\n");
			if (null != headers.get("Content-MD5")) {
				sb.append(headers.get("Content-MD5"));
			}
			sb.append("\n");
			if (null != headers.get("Content-Type")) {
				sb.append(headers.get("Content-Type"));
			}
			sb.append("\n");
			if (null != headers.get("Date")) {
				sb.append(headers.get("Date"));
			}
		}
		sb.append("\n");
		sb.append(buildHeaders(headers, signHeaderPrefixList));
		sb.append(buildResource(path, querys, bodys));

		return sb.toString();
	}

	/**
	 * OCR 构建待签名Http头
	 *
	 * @param headers 请求中所有的Http头
	 * @param signHeaderPrefixList 自定义参与签名Header前缀
	 * @return 待签名Http头
	 */
	private static String buildHeaders(Map<String, String> headers, List<String> signHeaderPrefixList) {
		StringBuilder sb = new StringBuilder();

		if (null != signHeaderPrefixList) {

			signHeaderPrefixList.remove("X-Ca-Signature");
			signHeaderPrefixList.remove("Accept");
			signHeaderPrefixList.remove("Content-MD5");
			signHeaderPrefixList.remove("Content-Type");
			signHeaderPrefixList.remove("Date");
			Collections.sort(signHeaderPrefixList);
			if (null != headers) {
				Map<String, String> sortMap = new TreeMap<String, String>();
				sortMap.putAll(headers);
				StringBuilder signHeadersStringBuilder = new StringBuilder();
				for (Map.Entry<String, String> header : sortMap.entrySet()) {
					if (isHeaderToSign(header.getKey(), signHeaderPrefixList)) {
						sb.append(header.getKey());
						sb.append(":");
						if (!StringUtils.isBlank(header.getValue())) {
							sb.append(header.getValue());
						}
						sb.append("\n");
						if (0 < signHeadersStringBuilder.length()) {
							signHeadersStringBuilder.append(",");
						}
						signHeadersStringBuilder.append(header.getKey());
					}
				}
				headers.put("X-Ca-Signature-Headers", signHeadersStringBuilder.toString());
			}
		}

		return sb.toString();
	}
	/**
	 * OCR 构建待签名Path+Query+BODY
	 *
	 * @param path
	 * @param querys
	 * @param bodys
	 * @return 待签名
	 */
	private static String buildResource(String path, Map<String, String> querys, Map<String, String> bodys) {
		StringBuilder sb = new StringBuilder();

		if (!StringUtils.isBlank(path)) {
			sb.append(path);
		}
		Map<String, String> sortMap = new TreeMap<String, String>();
		if (null != querys) {
			for (Map.Entry<String, String> query : querys.entrySet()) {
				if (!StringUtils.isBlank(query.getKey())) {
					sortMap.put(query.getKey(), query.getValue());
				}
			}
		}
		if (null != bodys) {
			for (Map.Entry<String, String> body : bodys.entrySet()) {
				if (!StringUtils.isBlank(body.getKey())) {
					sortMap.put(body.getKey(), body.getValue());
				}
			}
		}
		StringBuilder sbParam = new StringBuilder();
		for (Map.Entry<String, String> item : sortMap.entrySet()) {
			if (!StringUtils.isBlank(item.getKey())) {
				if (0 < sbParam.length()) {
					sbParam.append("&");
				}
				sbParam.append(item.getKey());
				if (!StringUtils.isBlank(item.getValue())) {
					sbParam.append("=").append(item.getValue());
				}
			}
		}
		if (0 < sbParam.length()) {
			sb.append("?");
			sb.append(sbParam);
		}

		return sb.toString();
	}
	/**
	 *OCR Http头是否参与签名 return
	 */
	private static boolean isHeaderToSign(String headerName, List<String> signHeaderPrefixList) {
		if (StringUtils.isBlank(headerName)) {
			return false;
		}

		if (headerName.startsWith("X-Ca-")) {
			return true;
		}

		if (null != signHeaderPrefixList) {
			for (String signHeaderPrefix : signHeaderPrefixList) {
				if (headerName.equalsIgnoreCase(signHeaderPrefix)) {
					return true;
				}
			}
		}

		return false;
	}
}
