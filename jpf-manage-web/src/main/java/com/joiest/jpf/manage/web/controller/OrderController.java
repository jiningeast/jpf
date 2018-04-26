package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.facade.OrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceFacade orderServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "order/orderList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(OrderRequest orderRequest){
        OrderResponse orderResponse = orderServiceFacade.getOrders(orderRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("total", orderResponse.getCount());
        map.put("rows", orderResponse.getList());
        map.put("request", orderRequest);

        return map;
    }
}
