package com.joiest.jpf.manage.web.controller;


import com.joiest.jpf.dto.CloudCompanyMoneyRequest;
import com.joiest.jpf.dto.CloudCompanyMoneyResponse;
import com.joiest.jpf.entity.CloudCompanyMoneyInfo;
import com.joiest.jpf.facade.CloudCompanyMoneyServiceFacade;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("cloudCompanyMoney")
public class CloudCompanyMoneyController {

    @Autowired
    private CloudCompanyMoneyServiceFacade cloudCompanyMoneyServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "cloudCompanyMoney/companyList";
    }

    /*
     * 充值列表页
     * */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(CloudCompanyMoneyRequest cloudCompanyMoneyRequest){
        CloudCompanyMoneyResponse cloudCompanyMoneyResponse = cloudCompanyMoneyServiceFacade.getRecords(cloudCompanyMoneyRequest);

        Map<String, Object> map = new HashMap<>();
        map.put("total", cloudCompanyMoneyResponse.getCount());
        map.put("rows", cloudCompanyMoneyResponse.getList());

        return map;
    }


}
