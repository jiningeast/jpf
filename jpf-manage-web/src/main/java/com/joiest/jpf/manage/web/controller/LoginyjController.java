package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.dto.GetMerchsRequest;
import com.joiest.jpf.dto.GetMerchsResponse;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.facade.MerchantServiceFacade;
import com.joiest.jpf.manage.web.util.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.joiest.jpf.manage.web.constant.ManageConstants.HTTP_ORIGN;
import static com.joiest.jpf.manage.web.constant.ManageConstants.IPS;

@Controller
@RequestMapping("/front")
public class LoginyjController {

    private String name;

    @Autowired
    private MerchantServiceFacade merchantServiceFacade;

    public LoginyjController(){
        this.name = "张三";
    }

    @RequestMapping(value = "/checkin", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public YjResponseDto loginyj(HttpServletRequest request){
        System.out.println("name========= : " + this.name);
//        String IP = ServletUtils.getIpAddr(request);
//        System.out.println("getRequestURL=====" + request.getRequestURL());
//        System.out.println("getRequestURI=====" + request.getRequestURI());
//        System.out.println("getRemoteAddr===" + request.getRemoteAddr());
//        System.out.println("request.getRemoteHost():    "    +    request.getRemoteHost());
//        System.out.println("Referer===" + request.getHeader("Referer"));
//        String originHeader = request.getHeader("Origin");




//        if ( 1 == 1 )
//        {
//            throw new JpfInterfaceException(JpfInterfaceErrorInfo.NOTlOGIN.getCode(), JpfInterfaceErrorInfo.NOTlOGIN.getDesc());
//        }

        YjResponseDto dto = new YjResponseDto();
//        List<Object> list = new ArrayList<>();
//        Map<String,Object> map = new HashMap<>();
//        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf("116"));
//        GetMerchsRequest request1 = new GetMerchsRequest();
//        GetMerchsResponse responseMer = merchantServiceFacade.getMerchInfoList(request1);
//
//        dto.setData(responseMer.getList());
        return dto;
    }

    private void setHeader(HttpServletRequest httpRequest, HttpServletResponse response){
        System.out.println("========setHeader BEGIN========");
        String originHeader = httpRequest.getHeader("Origin");
//        String originHeader = "http://10.1.0.209";
        System.out.println(originHeader);

        String url = "http://" + httpRequest.getServerName() //服务器地址
                + ":"
                + httpRequest.getServerPort()           //端口号
                + httpRequest.getContextPath()      //项目名称
                + httpRequest.getServletPath()      //请求页面或其他地址
                + "?" + (httpRequest.getQueryString()); //参数
        System.out.println("url:=======" + url );
        List<String> http_orign = HTTP_ORIGN;
        Iterator<String> it = http_orign.iterator();
        // 获取IP
        String IP = ServletUtils.getIpAddr(httpRequest);
        String uri = httpRequest.getServerName();
        System.out.println("IP:" + IP);
        System.out.println("uri:" + uri);
//        String IP = "http://webchats.7shengqian.com";

        String[] IPs = IPS;
        if (Arrays.asList(IPs).contains(originHeader))
        {
            response .setHeader("Access-Control-Allow-Headers", "accept, content-type");
            response.setHeader("Access-Control-Allow-Method", "POST");
            response.setHeader("Access-Control-Allow-Origin", originHeader);
        }
        System.out.println("========setHeader END========");
    }

}
