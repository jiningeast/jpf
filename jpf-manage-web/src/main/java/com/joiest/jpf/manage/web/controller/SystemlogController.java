package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.facade.SystemlogServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/systemlog")
public class SystemlogController {

    @Autowired
    private SystemlogServiceFacade systemlogServiceFacade;

    @RequestMapping("index")
    public String index(){
        return "systemlog/systemlogList";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list( long page, long rows ){
        Map<String, Object> map = new HashMap();
        map.put("total", systemlogServiceFacade.getCount());
        map.put("rows", systemlogServiceFacade.getLogs(page, rows));

        return map;
    }


}
