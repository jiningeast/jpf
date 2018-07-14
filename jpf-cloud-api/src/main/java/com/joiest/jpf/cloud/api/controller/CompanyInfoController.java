package com.joiest.jpf.cloud.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/companyInfo")
public class CompanyInfoController {

    @RequestMapping("/companyLogin")
    @ResponseBody
    public String companyLogin(){


        return null;
    }
}
