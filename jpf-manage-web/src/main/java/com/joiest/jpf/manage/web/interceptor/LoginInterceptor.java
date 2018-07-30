package com.joiest.jpf.manage.web.interceptor;

import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.manage.web.constant.ManageConstants;
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
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
		String contextPath = request.getContextPath();
        String requestURI =  request.getRequestURI();
		String accessLink = "";
		if (StringUtils.isNotBlank(contextPath)) {
			accessLink = requestURI.replace(
					contextPath + "/", "");
		} else {
			accessLink = requestURI.substring(1, requestURI.length());
		}
		logger.info("userName:{}---------访问主机地址:{}------------------访问功能链接地址:{}------------------",userInfo==null?"":userInfo.getUserName(),request.getRemoteHost(),accessLink);
		String uri = request.getRequestURI();
		logger.info("request path : {}",uri);
		if (uri.indexOf("index") > -1 || uri.indexOf("login") > -1 || uri.indexOf("unionPayRefundReturn") > -1 || uri.indexOf("cloudTask/testCheckBanks") > -1 || uri.indexOf("cloudCompanyMoney/searchWaitPay") > -1 ) { // 不需要过滤的地址
            super.preHandle(request, response, handler);
		} else if (session != null && userInfo != null) {
            super.preHandle(request, response, handler);
		} else {
			response.sendRedirect(request.getContextPath() + "/index");
			return false;
		}
        return super.preHandle(request, response, handler);
	}
}