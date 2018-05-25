//package com.joiest.jpf.web.handler;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
//
///**
// * controller切面处理
// * Created by zjf1650 on 10/08/2017.
// */
//@Component
//@Aspect
//@Order(1)
//public class AspectControllerHandler {
//    private static final Logger logger = LogManager.getLogger(AspectControllerHandler.class);
//
//    @Pointcut("execution(public * com.luna.unipay.pts.web.controller.*.*(..))")
//    private void controllerAspect(){
//
//    }
//
//    @AfterReturning(returning = "o",pointcut = "controllerAspect()")
//    public void methodAfterReturing(Object o ){
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//        long startRunTime = (Long)request.getAttribute("startRunTime");
//
//        long endRunTime = System.currentTimeMillis();
//
//        long executeTime = endRunTime - startRunTime;
//        String requestURL = request.getRequestURL().toString();
//        logger.info("requestURL:{}, executeTime:{} ms, response:{}" ,requestURL , executeTime, o.toString());
//    }
//}
