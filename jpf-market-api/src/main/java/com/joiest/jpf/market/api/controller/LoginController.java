package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.OfpayUtils;
import com.joiest.jpf.common.util.ToolUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/nologin")
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);

    /**
     * 个人登录判断
     */
    @RequestMapping(value = "userIndex", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String userIndex(HttpServletResponse response, HttpServletRequest request)
    {
        logger.info("===============未登录处理的方法 start===============");
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NOTlOGIN.getCode(),JpfInterfaceErrorInfo.NOTlOGIN.getDesc(),null);
    }

    /**
     * 个人登录判断
     */
    @RequestMapping(value = "userNotBindCoupon", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String userNotBindCoupon(HttpServletResponse response, HttpServletRequest request)
    {
        logger.info("===============未绑定券处理的方法 start===============");
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_COUPON_NOTBIND.getCode(),JpfInterfaceErrorInfo.USER_COUPON_NOTBIND.getDesc(),null);
    }

    /**
     * 油卡注册
     */
    @RequestMapping(value = "register", produces = "application/json;charset=utf-8")
    @ResponseBody
    public  String register(HttpServletResponse response, HttpServletRequest request){

        Map<String,String> param=new HashMap<>();
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        String type=request.getParameter("type");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        //验证参数是否正确
        String pattern = "^[1][3,4,5,7,8][0-9]{9}$";
        boolean isphone = Pattern.matches(pattern, phone);

        if(isphone==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "");
        }
        String emailpattern="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        boolean isemail = Pattern.matches(emailpattern, email);
        if(isemail==false){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "邮箱不正确");
        }
        param.put("login_name",name);
        param.put("login_pwd",password);
        param.put("charge_type",type);
        param.put("email",email);
        param.put("phone_no",phone);
        Map<String,String> map= new OfpayUtils().oilRegister(param);
        return JsonUtils.toJson(map);

    }
}
