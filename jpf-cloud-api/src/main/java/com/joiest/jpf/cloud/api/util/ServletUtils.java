package com.joiest.jpf.cloud.api.util;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServletUtils {
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null) return "";

		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getParasMap(HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String,String[]> requestParams = request.getParameterMap();
		String name=null;
		String[] values=null;
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			name =  iter.next();
			values =  requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr
						+ values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		return params;
	}

	
	public static String parseRequst(HttpServletRequest request){
		String body = "";
		try {
			ServletInputStream inputStream = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			while(true){
				String info = br.readLine();
				if(info == null){
					break;
				}
				if(body == null || "".equals(body)){
					body = info;
				}else{
					body += info;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return body;
	}

    public static  String getRequestParameter(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        @SuppressWarnings("rawtypes")
        Enumeration enu = request.getParameterNames();

        String paraName = null;
        while (enu.hasMoreElements()) {
            paraName = (String) enu.nextElement();
            sb.append(paraName).append("=")
                    .append(request.getParameter(paraName)).append("&");

        }
        if(sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
