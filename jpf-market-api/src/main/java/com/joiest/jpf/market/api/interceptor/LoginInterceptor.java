package com.joiest.jpf.market.api.interceptor;

import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.dto.GetUserCouponActiveInterfaceResponse;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopCouponActiveInterfaceServiceFacade;
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

    private ShopCustomerInterfaceInfo userInfo;

    private String openId;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private ShopCouponActiveInterfaceServiceFacade shopCouponActiveInterfaceServiceFacade;

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
                add("/custom/bind");                //绑定手机号
                add("/custom/sendSms");             //绑定手机号
                add("/nologin/userIndex");
                add("/nologin/register");
                add("/nologin/userNotBindCoupon");
                add("/orders/ofpayNotifyUrl");
                add("/certificate/activation");     //用户激活券url
                add("/shopAd/index");     //广告位图片调取
                add("/orders/wnProduct");     //获取微能产品信息
                add("/orders/flowReport");     //微能状态报告获取
                add("/orders/flowbalance");      // 查询微能余额
                add("/orders/weinengNotifyUrl");      // 查询微能余额
                add("/demo/test");              // 测试函数
                add("/travel/proList");              // 旅游生活商品列表页
                add("/travel/proInfo"); // 获取商品服务列表
                add("/orderInfo/timeoutCancelOrder");// 定时取消超时未支付订单接口
                add("/orderInfo/solveAbnormalOrders");// 处理异常订单
            }
        };
        List<String> marageLoginUrl = new ArrayList<String>() {
            {
                add("/market-manager/managerLogin/loginIn");                //绑定手机号
                add("/market-manager/managerLogin/loginFail");
            }
        };
        System.out.println(ServletUtils.getIpAddr(request));
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        String login_type =request.getParameter("login_type");
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
        logger.info("========Token：" + Token);
        if(NOTLOGINURL.contains(requestUri)){ // 不需要过滤的地址
            return super.preHandle(request, response, handler);
        }else if(!NOTLOGINURL.contains(requestUri)&&StringUtils.isBlank(login_type)){//微信端的验证
            String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + Token);
            if(openId_encrypt == null){
                logger.info("========redis未获取到token: "+openId_encrypt);
                //WEIXIN_LOGIN_D5D160B52E1AAE2AD843ACEEBA3B69197BD047F7C6B8CE432F416ADF77D282A3924C802B52D7D65A26FED1F08BB6B700
                //Token = "D5D160B52E1AAE2AD843ACEEBA3B69197BD047F7C6B8CE432F416ADF77D282A3924C802B52D7D65A26FED1F08BB6B700";
                String tokenDe = AESUtils.decrypt(Token,ConfigUtil.getValue("AES_KEY"));
                String tokenVal = AESUtils.encrypt(StringUtils.substring(tokenDe,18), ConfigUtil.getValue("AES_KEY"));
                logger.info("========动态存储redis Token: "+tokenDe);
                logger.info("========动态存储redis TokenValue: "+tokenVal);
                redisCustomServiceFacade.set(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + Token, tokenVal, Long.parseLong(ConfigUtil.getValue("MARKET_USER_LOGIN_EXPIRE_30")) );
            }
            if (!userIsLogin(Token)){
                logger.info("========跳转到未登录处理的方法地址: /nologin/userIndex");
                request.getRequestDispatcher("/nologin/userIndex").forward(request,response);
                return false;
            }
            if (!userIsBindCoupon()){
                logger.info("========跳转到未绑定券的处理的方法地址: /nologin/userNotBindCoupon");
                request.getRequestDispatcher("/nologin/userNotBindCoupon").forward(request,response);
                return false;
            }
            return super.preHandle(request, response, handler);
        } else  if(!marageLoginUrl.contains(requestUri)&&StringUtils.equals("manager",login_type)){
            //验证redis
            System.out.println(ConfigUtil.getValue("MARKETMANGER_LOGIN_KEY")+Token);
            String marketManagerLoginKey=redisCustomServiceFacade.get(ConfigUtil.getValue("MARKETMANGER_LOGIN_KEY")+Token);
            if(StringUtils.isNotBlank(marketManagerLoginKey)){
               return true;
            }else{
                request.getRequestDispatcher("/market-manager/managerLogin/loginFail").forward(request,response);
                return false;
            }
        }else{
            return true;
        }
    }

    private Boolean userIsLogin(String token)
    {
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        logger.info("========openId_encrypt : " + openId_encrypt);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            logger.info("========AES_KEY : " + ConfigUtil.getValue("AES_KEY"));
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            logger.info("========openId : " + openId);
            List<ShopCustomerInterfaceInfo> userinfoList = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId);
            logger.info("========userinfoList: " + userinfoList);
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
            logger.info("========openId_encrypt 为空: " + openId_encrypt);
            return false;
        }
    }

    private Boolean userIsBindCoupon()
    {
        if ( userInfo == null )
        {
            return false;
        }
        GetUserCouponActiveInterfaceResponse response = shopCouponActiveInterfaceServiceFacade.getUserCouponList(userInfo.getId());
        if ( response == null || response.getList() == null)
        {
            return false;
        }
        return true;
    }

}

