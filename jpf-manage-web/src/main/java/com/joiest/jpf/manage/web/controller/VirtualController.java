package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.facade.VirtualServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统虚拟类管理
 */
@Controller
@RequestMapping("/virtual")
public class VirtualController {

    @Autowired
    private VirtualServiceFacade virtualServiceFacade;

    /**
     * 虚拟类列表管理
     * @returnz
     */
    @RequestMapping("/index")
    public String index() {
        return "virtual/vitualList";
    }

    /**
     * 虚拟类列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(String cat, String intro,long page,long rows){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", virtualServiceFacade.getVirtualCount(cat));
        map.put("rows", virtualServiceFacade.getVirtual(cat,intro,page,rows));
        return map;
    }
    /*
    * 虚拟类功能添加
    */
    @RequestMapping("/add")
    @ResponseBody
    public JpfResponseDto add(String cat,String intro){
        return virtualServiceFacade.addVirtual(cat,intro);

    }
}
