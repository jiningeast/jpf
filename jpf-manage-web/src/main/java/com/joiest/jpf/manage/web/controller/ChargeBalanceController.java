package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ChargeBalanceRequest;
import com.joiest.jpf.dto.ChargeBalanceResponse;
import com.joiest.jpf.entity.ChargeBalanceInfo;
import com.joiest.jpf.facade.ChargeBalanceServiceFacade;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("chargeBalance")
public class ChargeBalanceController {

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private ChargeBalanceServiceFacade chargeBalanceServiceFacade;

    @RequestMapping("index")
    public String index(){
        return "chargeBalance/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(ChargeBalanceRequest request){
        ChargeBalanceResponse response = chargeBalanceServiceFacade.getRecords(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());

        return map;
    }


    @RequestMapping("editPage")
    @ResponseBody
    public ModelAndView editPage(String id,ModelMap modelMap){
        //取出当前公司的信息
        ChargeBalanceInfo chargeBalanceInfo = chargeBalanceServiceFacade.getRecordByPrimaryKey(id);
        modelMap.addAttribute("chargeBalanceInfo", chargeBalanceInfo);

        return new ModelAndView("chargeBalance/edit");
    }

    @RequestMapping("editAction")
    @ResponseBody
    public JpfResponseDto editAction(ChargeBalanceRequest request){

        return chargeBalanceServiceFacade.edit(request);
    }

}
