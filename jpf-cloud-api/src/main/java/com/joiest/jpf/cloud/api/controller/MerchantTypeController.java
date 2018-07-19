package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.facade.MerTypeServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/merType")
public class MerchantTypeController {

    private static final Logger logger = LogManager.getLogger(MerchantTypeController.class);

    @Autowired
    private MerTypeServiceFacade merTypeServiceFacade;

    @RequestMapping(value = "/tree", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getMerTypeTree(int catId) {
        logger.debug("catId={}",catId);
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), merTypeServiceFacade.getMerchantTypeTree(catId));
    }

    @ModelAttribute
    public void beforeAction(HttpServletRequest httpRequest, HttpServletResponse response)
    {
        // 跨域
        String originHeader = httpRequest.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.setHeader("Access-Control-Allow-Method", "POST");
        response.setHeader("Access-Control-Allow-Origin", originHeader);

    }
}
