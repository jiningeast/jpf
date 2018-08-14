package com.joiest.jpf.market.api.interceptor;

import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import com.joiest.jpf.market.api.controller.ConfigUtil;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.entity.CloudCompanyStaffInfo;
import com.joiest.jpf.entity.CloudEmployeeInfo;
import com.joiest.jpf.facade.CloudCompanyStaffServiceFacade;
import com.joiest.jpf.facade.CloudEmployeeServiceFacade;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(LoginInterceptor.class);

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

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
        List<String> NOTLOGINURL = new ArrayList<String>() {
            {
                add("/custom/bind");        //绑定手机号
                add("/custom/sendSms");     //绑定手机号
                add("/nologin/userIndex");
                add("/orders/ofpayNotifyUrl");
            }
        };

        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        String accessLink = "";
        if (StringUtils.isNotBlank(contextPath)) {
            accessLink = requestURI.replace(
                    contextPath + "/", "");
        } else {
            accessLink = requestURI.substring(1, requestURI.length());
        }
        logger.info("userName:{}---------访问主机地址:{}------------------访问功能链接地址:{}------------------", request.getRemoteHost(), accessLink);
        String uri = request.getRequestURI();
        logger.info("request path : {}", uri);
        String requestUri = uri.replace( contextPath, "");
        String Token = request.getHeader("Token");
        if ( NOTLOGINURL.contains(requestUri) ) { // 不需要过滤的地址
            return super.preHandle(request, response, handler);
        } else if( !NOTLOGINURL.contains(requestUri) ) {
            Boolean isLogin = userIsLogin(Token);
            if ( isLogin )
            {
                return super.preHandle(request, response, handler);
            }
            response.sendRedirect(request.getContextPath() + "/nologin/userIndex");
            return false;
        } else {
            return false;
        }
    }

    private Boolean userIsLogin(String token)
    {
        String openId;
        ShopCustomerInterfaceInfo userInfo;
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            List<ShopCustomerInterfaceInfo> userinfoList = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId);
            if ( userinfoList.isEmpty() || userinfoList == null )
            {
                return false;
            }
            userInfo = userinfoList.get(0);
            if (userInfo == null) {
                return  false;
            }
            return true;
        } else {
            return false;
        }
    }


}

