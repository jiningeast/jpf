package com.joiest.jpf.charge.manage.api.interceptor;

import com.joiest.jpf.charge.manage.api.controller.ConfigUtil;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
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

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LogManager.getLogger(LoginInterceptor.class);

    private String openId;

    private  ChargeCompanyInfo chargeCompanyInfo;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    // 在业务处理器处理请求之前被调用
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        logger.info("===========LoginInterceptor preHandle===========");
        //过滤地址添加
        List<String> NOTLOGINURL = new ArrayList<String>() {
            {
                add("/nologin/userIndex");//判断是否登录返回
                add("/chargeCompany/loginMerch");//登录验证
                add("/chargeCompany/logout");//登录验证

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
        logger.info("========Token" + Token);
        if ( NOTLOGINURL.contains(requestUri) ) { // 不需要过滤的地址
            return super.preHandle(request, response, handler);
        } else if( !NOTLOGINURL.contains(requestUri) ) {
            Boolean isLogin = userIsLogin(Token);
            if ( !isLogin )
            {
                logger.info("========跳转到未登录处理的方法地址: /nologin/userIndex");
                request.getRequestDispatcher("/nologin/userIndex").forward(request,response);
                return false;
            }
            return super.preHandle(request, response, handler);
        } else {
            return false;
        }
    }
    // 在业务处理器处理请求完成之后，生成视图之前执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception{

        System.out.println("拦截器提交");

    }
    // 在DispatcherServlet完全处理完请求之后被调用，可用于清理资源
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception{

        System.out.println("拦截器完成");
    }

    private Boolean userIsLogin(String token)
    {
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("MANAGE_LOGIN_KEY") + token);
        logger.info("========openId_encrypt : " + openId_encrypt);
        if (StringUtils.isNotBlank(openId_encrypt)) {
        /*    logger.info("========AES_KEY : " + ConfigUtil.getValue("AES_KEY"));
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            logger.info("========openId : " + openId);*/
            chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByMerchNo(openId_encrypt);
            logger.info("========userinfoList: " + chargeCompanyInfo);
            if ( chargeCompanyInfo == null)
            {
                return false;
            }
            return true;
        } else {
            logger.info("========openId_encrypt 为空: " + openId_encrypt);
            return false;
        }
    }

}

