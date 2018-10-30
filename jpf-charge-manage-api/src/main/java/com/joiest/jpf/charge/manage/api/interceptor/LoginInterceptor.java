package com.joiest.jpf.charge.manage.api.interceptor;

import com.joiest.jpf.charge.manage.api.controller.ConfigUtil;
import com.joiest.jpf.charge.manage.api.util.ServletUtils;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopCouponActiveInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
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

    // 在业务处理器处理请求之前被调用
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        logger.info("========跳转到未登录处理的方法地址: /nologin/userIndex");
        System.out.println("拦截器进入");
        return true;
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

}

