package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.MerShopRequest;
import com.joiest.jpf.dto.MerShopResponse;
import com.joiest.jpf.facade.MerShopServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/merShop")
public class MerchantShopController {

    @Autowired
    private MerShopServiceFacade merShopServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "merShop/merShopList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(MerShopRequest merShopRequest) {
        MerShopResponse merShopResponse = merShopServiceFacade.getMerShops(merShopRequest);

        Map<String, Object> map = new HashMap<>();
        map.put("total",merShopResponse.getCount() );
        map.put("rows", merShopResponse.getList());

        return map;
    }
}
