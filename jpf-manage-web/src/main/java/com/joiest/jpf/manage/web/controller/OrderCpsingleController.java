package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.facade.OrderCpsingleServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
