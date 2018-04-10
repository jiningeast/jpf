package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.facade.PcaServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/pca")
public class PcaController {

    @Autowired
    private PcaServiceFacade pcaServiceFacade;

    @RequestMapping("/index")
    public String index() {
        return "pca/pcaList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(long page, long rows){
        Map<String,Object> map = new HashMap<>();
        map.put("total", pcaServiceFacade.getPcaCount());
        map.put("rows", pcaServiceFacade.getPca(page, rows));

        return map;
    }
}
