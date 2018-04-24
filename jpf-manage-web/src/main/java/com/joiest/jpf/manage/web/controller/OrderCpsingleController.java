package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dto.OrderCpsingleRequest;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.OrderCpsingleServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
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
    public Map<String, Object> list(long page, long rows){
        Map<String, Object> map = new HashMap<>();
        map.put("total", orderCpsingleServiceFacade.getCpsCount());
        map.put("rows", orderCpsingleServiceFacade.getCps(page, rows));

        return map;
    }

    @RequestMapping("/checkOk")
    @ResponseBody
    public JpfResponseDto checkOk(OrderCpsingleRequest orderCpsingleRequest, HttpServletRequest request){
        // 准备请求接口，传入参数orderid
        OkHttpUtils okHttpUtils = new OkHttpUtils();
        Map<String,Object> posRequest = new HashMap<>();
        posRequest.put("orderid",orderCpsingleRequest.getOrderid());
        String response = okHttpUtils.postForm("http://testapi.7shengqian.com/index.php?r=YinjiaStage/PurchaseRefund",posRequest);

        JsonUtils jsonUtils = new JsonUtils();
        Map<String, String> responseMap = jsonUtils.toCollection(response, new TypeReference<HashMap<String, String>>(){});
        if ( Integer.parseInt(responseMap.get("code")) != 10000 ){
            // 退款接口如果没有返回成功
           throw new JpfException(JpfErrorInfo.DAL_ERROR, "请求接口失败，返回："+responseMap.get("info")+"，请检查");
        }

        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

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
