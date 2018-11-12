package com.joiest.jpf.charge.manage.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.ToolUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


}
