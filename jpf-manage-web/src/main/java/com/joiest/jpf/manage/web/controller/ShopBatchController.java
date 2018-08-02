package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.ShopBatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/shopBatch")
public class ShopBatchController {

    @Autowired


    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("shopBatch/index");
    }

    @RequestMapping("/list")
    @ResponseBody
    public void list(ShopBatchRequest shopBatchRequest){

    }
}
