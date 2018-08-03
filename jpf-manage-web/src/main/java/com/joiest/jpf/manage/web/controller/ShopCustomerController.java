package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopCouponActiveRequest;
import com.joiest.jpf.dto.GetShopCouponActiveResponse;
import com.joiest.jpf.dto.GetShopCustomerRequest;
import com.joiest.jpf.dto.GetShopCustomerResponse;
import com.joiest.jpf.facade.BankServiceFacade;
import com.joiest.jpf.facade.ShopCouponActiveServiceFacade;
import com.joiest.jpf.facade.ShopCustomerServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/shopCustomer")
public class ShopCustomerController {

    @Autowired
    private ShopCustomerServiceFacade shopCustomerServiceFacade;

    @Autowired
    private ShopCouponActiveServiceFacade shopCouponActiveServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "shopCustomer/customerList";
    }

    /**
     * 服务市场客户列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopCustomerRequest request) {
        GetShopCustomerResponse response= shopCustomerServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 停用和启用公司
     */
    @RequestMapping("/delCompanyCustomer")
    @ResponseBody
    public JpfResponseDto delCompanyCustomer(String id,int type) {

        return shopCustomerServiceFacade.delCompanyCustomer(id,type);
    }

    /**
     * 客户详情页面
     */
    @RequestMapping("/info")
    @ResponseBody
    public ModelAndView info(String phone,String id,Integer dou, ModelMap modelMap){
        modelMap.addAttribute("phone", phone);
        modelMap.addAttribute("id", id);
        modelMap.addAttribute("dou", dou);
        return new ModelAndView("shopCustomer/customerInfoList", modelMap);
    }

    /**
     * 服务市场客户欣豆行为列表
     */
    @RequestMapping("/listCouponList")
    @ResponseBody
    public Map<String, Object> listCouponList(GetShopCouponActiveRequest request,String id) {
        GetShopCouponActiveResponse response= shopCouponActiveServiceFacade.getList(request,id);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

}
