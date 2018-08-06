package com.joiest.jpf.market.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("demo")
public class DemoController {

    @RequestMapping("/test")
    @ResponseBody
   public String demo()
   {
       return "111111";
   }
}
