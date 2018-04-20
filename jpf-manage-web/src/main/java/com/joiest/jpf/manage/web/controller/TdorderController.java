package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.facade.TdorderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tdorder")
public class TdorderController {

    @Autowired
    private TdorderServiceFacade tdorderServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "tdorder/tdorderList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(long page, long rows){
        Map<String, Object> map = new HashMap<>();
        map.put("total", tdorderServiceFacade.getTdordersCount());
        map.put("rows", tdorderServiceFacade.getTdorders(page, rows));

        return map;
    }
}
