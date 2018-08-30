package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.GetShopStockLogRequest;
import com.joiest.jpf.dto.GetShopStockLogResponse;
import com.joiest.jpf.facade.ShopStockLogServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/shopStockLog")
public class ShopStockLogController {

    @Autowired
    private ShopStockLogServiceFacade shopStockLogServiceFacade;


    @RequestMapping("/index")
    public String index(){
        return "shopStockLog/stockList";
    }

    /**
     * 进销存统计列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopStockLogRequest request){
        GetShopStockLogResponse response= shopStockLogServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }




}
