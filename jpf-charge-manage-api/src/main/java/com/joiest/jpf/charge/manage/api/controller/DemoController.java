package com.joiest.jpf.charge.manage.api.controller;

import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("demo")

public class DemoController {
    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;
    /**
     * 测试函数
     * */
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        String idno = "41071119870116153x";
        idno = idno.toUpperCase();
        ChargeCompanyInfo info = chargeCompanyServiceFacade.getRecordByMerchNo("123123");
        return idno;
    }
}
