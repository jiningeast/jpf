package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.GetShopCompanyRequest;
import com.joiest.jpf.dto.GetShopCompanyResponse;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.facade.ShopCompanyServiceFacade;
import com.joiest.jpf.facade.ShopOrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/shopOrder")
public class ShopOrderController {

    @Autowired
    private ShopOrderServiceFacade shopOrderServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "shopOrder/List";
    }

    /**
     * 服务市场公司列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopOrderRequest request) {
        GetShopOrderResponse response= shopOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 客户详情页面
     */
    @RequestMapping("/orderInfo")
    @ResponseBody
    public ModelAndView orderInfo(String orderNo,ModelMap modelMap){
        modelMap.addAttribute("orderNo", orderNo);
        return new ModelAndView("shopCustomer/customerInfoList", modelMap);
    }


}
