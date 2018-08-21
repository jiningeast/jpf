package com.joiest.jpf.market.api.interceptor;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import com.joiest.jpf.market.api.controller.ConfigUtil;
import com.joiest.jpf.market.api.util.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

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
    @ResponseBody
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
        System.out.println(ServletUtils.getIpAddr(request));
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
        logger.info("token==========" + Token);
        if ( NOTLOGINURL.contains(requestUri) ) { // 不需要过滤的地址
            return super.preHandle(request, response, handler);
        } else if( !NOTLOGINURL.contains(requestUri) ) {
            Boolean isLogin = userIsLogin(Token);
            if ( isLogin )
            {
                return super.preHandle(request, response, handler);
            }
            logger.info(ConfigUtil.getValue("SHOP_API_URL") + "/nologin/userIndex");
            /*response.setStatus(302);
            response.setHeader("location",ConfigUtil.getValue("SHOP_API_URL") + "/nologin/userIndex");*/
//            response.sendRedirect(ConfigUtil.getValue("SHOP_API_URL") + "/nologin/userIndex");
            System.out.println(request.getContextPath() + "/nologin/userIndex");
            request.getRequestDispatcher("/nologin/userIndex").forward(request,response);
            return false;
        } else {
            return false;
        }
    }

    private String noLogin(){
        throw new JpfInterfaceException(JpfInterfaceErrorInfo.NOTlOGIN.getCode(),JpfInterfaceErrorInfo.NOTlOGIN.getDesc());
//        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NOTlOGIN.getCode(),JpfInterfaceErrorInfo.NOTlOGIN.getDesc(),null);
    }

    private Boolean userIsLogin(String token)
    {
        String openId;
        ShopCustomerInterfaceInfo userInfo;
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        logger.info("openId_encrypt : " + openId_encrypt);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            logger.info("AES_KEY : " + ConfigUtil.getValue("AES_KEY"));
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            logger.info("openId : " + openId);
            List<ShopCustomerInterfaceInfo> userinfoList = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId);
            logger.info("userinfoList=================== : " + userinfoList);
            if ( userinfoList == null || userinfoList.isEmpty() )
            {
                return false;
            }
            userInfo = userinfoList.get(0);
            if (userInfo == null) {
                return  false;
            }
            return true;
        } else {
            logger.info("openId_encrypt 22222222222: " + openId_encrypt);
            return false;
        }
    }


}

