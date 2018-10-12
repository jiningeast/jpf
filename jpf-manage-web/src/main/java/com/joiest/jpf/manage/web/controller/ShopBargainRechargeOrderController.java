package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.GetShopBargainRechargeOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderResponse;
import com.joiest.jpf.facade.ShopBargainRechargeOrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("shopBargainRechargeOrder")
public class ShopBargainRechargeOrderController {

    @Autowired
    private ShopBargainRechargeOrderServiceFacade shopBargainRechargeOrderServiceFacade;

    @RequestMapping("index")
    public String index(){
        return "shopBargainRechargeOrder/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(GetShopBargainRechargeOrderRequest request){
        GetShopBargainRechargeOrderResponse response = shopBargainRechargeOrderServiceFacade.getRecords(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return map;
    }
}
