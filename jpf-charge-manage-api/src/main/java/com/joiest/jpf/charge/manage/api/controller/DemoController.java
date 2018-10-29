package com.joiest.jpf.charge.manage.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("demo")

public class DemoController {

    /**
     * 测试函数
     * */
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        String idno = "41071119870116153x";
        idno = idno.toUpperCase();

        return idno;
    }
}
