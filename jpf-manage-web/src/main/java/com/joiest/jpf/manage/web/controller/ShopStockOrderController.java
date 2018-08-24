package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopStockOrderRequest;
import com.joiest.jpf.dto.GetShopStockOrderResponse;
import com.joiest.jpf.entity.ShopStockOrderInfo;
import com.joiest.jpf.facade.ShopStockOrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/shopStockOrder")
public class ShopStockOrderController {

    @Autowired
    private ShopStockOrderServiceFacade shopStockOrderServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "shopStockOrder/List";
    }

    /**
     * 服务市场公司列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopStockOrderRequest request) {
        GetShopStockOrderResponse response= shopStockOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 添加
     */
    @RequestMapping("/add/page")
    @ResponseBody
    public ModelAndView addPage(ModelMap modelMap){

        return new ModelAndView("shopStockOrder/audit", modelMap);

    }

    /**
     * 审核页
     */
    @RequestMapping("/audit/page")
    @ResponseBody
    public ModelAndView addPage(String orderNo,ModelMap modelMap){
        ShopStockOrderInfo shopStockOrderInfo= shopStockOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopStockOrderInfo",shopStockOrderInfo);

        return new ModelAndView("shopStockOrder/audit", modelMap);

    }

    /**
     * 审核功能
     */
    @RequestMapping("/audit/action")
    @ResponseBody
    public JpfResponseDto auditAction(String orderNo){
        JpfResponseDto jpfResponseDto = new JpfResponseDto();

        return jpfResponseDto;

    }




}
