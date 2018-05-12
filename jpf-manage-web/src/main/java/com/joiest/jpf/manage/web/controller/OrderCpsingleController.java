package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dto.OrderCpsingleRequest;
import com.joiest.jpf.dto.OrderCpsingleResponse;
import com.joiest.jpf.dto.UnionPayRefundRequest;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.OrderCpsingleServiceFacade;
import com.joiest.jpf.facade.SystemlogServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import com.joiest.jpf.manage.web.util.ServletUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.joiest.jpf.manage.web.constant.ManageConstants.*;

@Controller
@RequestMapping("/orderCpsingle")
public class OrderCpsingleController {
    private static final Logger logger = LogManager.getLogger(OrderCpsingleController.class);

    @Autowired
    private OrderCpsingleServiceFacade orderCpsingleServiceFacade;

    @Autowired
    private SystemlogServiceFacade systemlogServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "orderCpsingle/orderCpsingleList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list( OrderCpsingleRequest request, HttpServletRequest httpRequest ){

        // 获取用户信息
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        // 获取IP
        String IP = ServletUtils.getIpAddr(httpRequest);

        OrderCpsingleResponse response = orderCpsingleServiceFacade.getCps(request, userInfo, IP);

        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());

        return map;
    }

    @RequestMapping("/checkOk")
    @ResponseBody
    public JpfResponseDto checkOk(OrderCpsingleRequest orderCpsingleRequest, HttpServletRequest httpRequest){
        // 构建请求参数
        Map<String,Object> posRequest;
        posRequest = orderCpsingleServiceFacade.getPosRequest(orderCpsingleRequest.getOrderid());

        // 请求接口地址
        String uri = httpRequest.getServerName();   // 返回域名
        String postUrl;
        if ( uri.indexOf("7shengqian") > -1 ){
            postUrl = REFUND_URL_FORMAL;
        }else{
            postUrl = REFUND_URL_TEST;
        }
        logger.info("接口地址为"+postUrl);

        String response = OkHttpUtils.postForm(postUrl,posRequest);
        Map<String, String> responseMap = JsonUtils.toCollection(response, new TypeReference<HashMap<String, String>>(){});

        // 获取用户信息
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        // 获取IP
        String IP = ServletUtils.getIpAddr(httpRequest);

        // 记录日志 - 数据库
        String content = "请求地址："+postUrl+"；返回结果："+responseMap.get("code")+','+responseMap.get("info");

        // 记录日志 - 文件
        logger.info(content);

        if ( Integer.parseInt(responseMap.get("code")) != 10000 ){
            // 退款接口如果没有返回成功
           throw new JpfException(JpfErrorInfo.DAL_ERROR, "请求接口失败，返回："+responseMap.get("info")+"，请检查");
        }

        return orderCpsingleServiceFacade.checkOk(orderCpsingleRequest, userInfo, IP);
    }

    @ResponseBody
    public JpfResponseDto purchaseCancel(String orderid, HttpServletRequest httpRequest){
        // 请求接口地址
        String uri = httpRequest.getServerName();   // 返回域名
        String postUrl;
        if ( uri.indexOf("7shengqian") > -1 ){
            postUrl = CANCEL_URL_TEST;
        }else{
            postUrl = CANCEL_URL_FORMAL;
        }
        logger.info("接口地址为"+postUrl);

        Map<String, Object> posRequest = new HashMap<>();
        posRequest.put("orderid",orderid);
        String response = OkHttpUtils.postForm(postUrl,posRequest);
        Map<String, String> responseMap = JsonUtils.toCollection(response, new TypeReference<HashMap<String, String>>(){});

        // 获取用户信息
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        // 获取IP
        String IP = ServletUtils.getIpAddr(httpRequest);

        // 记录日志 - 数据库
        String content = "请求地址："+postUrl+"；返回结果："+responseMap.get("code")+','+responseMap.get("info");

        if ( Integer.parseInt(responseMap.get("code")) != 10000 ){
            // 撤单接口如果没有返回成功
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "请求接口失败，返回："+responseMap.get("info")+"，请检查");
        }

//        orderCpsingleServiceFacade.cancelOrder();
        return new JpfResponseDto();
    }

    @RequestMapping("/checkNo")
    @ResponseBody
    public JpfResponseDto checkNo(OrderCpsingleRequest orderCpsingleRequest, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        // 获取IP
        String IP = ServletUtils.getIpAddr(request);
        orderCpsingleServiceFacade.checkNo(orderCpsingleRequest, userInfo, IP);

        return new JpfResponseDto();
    }

    @RequestMapping("/unionPayRefundReturn")
    @ResponseBody
    public void unionPayRefundReturn(UnionPayRefundRequest request, HttpServletRequest httpRequest){

        // 获取IP
        String IP = ServletUtils.getIpAddr(httpRequest);
        orderCpsingleServiceFacade.unionPayRefund(request,IP);
    }
}
