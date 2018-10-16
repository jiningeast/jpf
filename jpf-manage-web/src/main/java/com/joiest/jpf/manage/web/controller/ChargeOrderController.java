package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.GetChargeOrderRequest;
import com.joiest.jpf.dto.GetChargeOrderResponse;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("chargeOrder")
public class ChargeOrderController {

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;

    @RequestMapping("index")
    public String index(){
        return "chargeOrder/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(GetChargeOrderRequest request){
        GetChargeOrderResponse response = chargeOrderServiceFacade.getRecords(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return  map;
    }
}
