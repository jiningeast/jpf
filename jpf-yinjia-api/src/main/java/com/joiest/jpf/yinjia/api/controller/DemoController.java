package com.joiest.jpf.yinjia.api.controller;

import com.joiest.jpf.dto.GetValueRequest;
import com.joiest.jpf.dto.GetValueResponse;
import com.joiest.jpf.facade.DemoServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @Autowired
    private DemoServiceFacade demoServiceFacade;

    @RequestMapping(value = "/getValue")
    @ResponseBody
    public GetValueResponse getValue(@Valid GetValueRequest request){
        return demoServiceFacade.getValue(request);
    }

    @RequestMapping("/index")
    public void index(){
        System.out.println("it works");
    }
}
