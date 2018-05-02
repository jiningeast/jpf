package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dto.OrderCpsingleRequest;
import com.joiest.jpf.dto.OrderCpsingleResponse;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.OrderCpsingleServiceFacade;
import com.joiest.jpf.facade.OrderServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import com.joiest.jpf.manage.web.util.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/orderCpsingle")
public class OrderCpsingleController {

    @Autowired
    private OrderCpsingleServiceFacade orderCpsingleServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "orderCpsingle/orderCpsingleList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list( OrderCpsingleRequest request ){
        OrderCpsingleResponse response = orderCpsingleServiceFacade.getCps(request);

        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());

        return map;
    }

    @RequestMapping("/checkOk")
    @ResponseBody
    public JpfResponseDto checkOk(OrderCpsingleRequest orderCpsingleRequest, HttpServletRequest request){
        // 构建请求参数
        Map<String,Object> posRequest;
        posRequest = orderCpsingleServiceFacade.getPosRequest(orderCpsingleRequest.getOrderid());

        // 请求接口地址
        String postUrl = "http://testapi.7shengqian.com/index.php?r=YinjiaStage/PurchaseRefund";
        String response = OkHttpUtils.postForm(postUrl,posRequest);
        Map<String, String> responseMap = JsonUtils.toCollection(response, new TypeReference<HashMap<String, String>>(){});

        // 获取用户信息
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        // 获取IP
        String ip = ServletUtils.getIpAddr(request);

        // 记录日志
        orderCpsingleServiceFacade.sysLog(1,userInfo,ip, "", 32, "noTable", "调用退单接口", "请求地址："+postUrl+"；返回结果："+responseMap.get("code")+','+responseMap.get("info"));

        if ( Integer.parseInt(responseMap.get("code")) != 10000 ){
            // 退款接口如果没有返回成功
           throw new JpfException(JpfErrorInfo.DAL_ERROR, "请求接口失败，返回："+responseMap.get("info")+"，请检查");
        }


        return orderCpsingleServiceFacade.checkOk(orderCpsingleRequest, userInfo);
    }

    @RequestMapping("/checkNo")
    @ResponseBody
    public JpfResponseDto checkNo(OrderCpsingleRequest orderCpsingleRequest, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        return orderCpsingleServiceFacade.checkNo(orderCpsingleRequest, userInfo);
    }
}
