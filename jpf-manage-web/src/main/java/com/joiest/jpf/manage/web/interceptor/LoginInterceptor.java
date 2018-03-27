package com.joiest.jpf.manage.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LogManager.getLogger(LoginInterceptor.class);
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("===========LoginInterceptor postHandle===========");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("===========LoginInterceptor afterCompletion===========");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===========LoginInterceptor preHandle===========");
		String contextPath = request.getContextPath();
		String requestURI =  request.getRequestURI();
		String accessLink = "";
		if (!StringUtils.isEmpty(contextPath)) {
			accessLink = requestURI.replace(
					contextPath + "/", "");
		} else {
			accessLink = requestURI.substring(1, requestURI.length());
		}

		HttpSession session = request.getSession();
//		UserInfo userInfo = (UserInfo) session.getAttribute(Constant.SESSION_OPERATORUSER);
//		if(userInfo!=null){
//			logger.info("---------访问主机地址:" + request.getRemoteHost() + "------------------访问功能链接地址:" + accessLink+",userName="+userInfo.getUserName()+",LoginName="+userInfo.getLoginName());
//		}else{
			logger.info("---------访问主机地址:" + request.getRemoteHost() + "------------------访问功能链接地址:" + accessLink);
//		}
//		String uri = request.getRequestURI();
//		// System.out.println("request path : " + uri);
//		if (uri.indexOf("index") > -1 || uri.indexOf("login") > -1) { // 不需要过滤的地址
//			// System.out.println("uri:" + uri);
//			super.preHandle(request, response, handler);
//		} else if (session != null && userInfo != null) {
//			// System.out.println("session is not null");
//			super.preHandle(request, response, handler);
//		} else {
//			// System.out.println("session is null");
//			response.sendRedirect(request.getContextPath() + "/index");
//			return false;
//		}

		return super.preHandle(request, response, handler);
	}
}