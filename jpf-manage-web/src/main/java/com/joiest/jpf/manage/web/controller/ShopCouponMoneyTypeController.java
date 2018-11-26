package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCouponMoneyType;
import com.joiest.jpf.facade.ShopCouponMoneyTypeServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shopCouponMoneyType")
public class ShopCouponMoneyTypeController {

    @Autowired
    private ShopCouponMoneyTypeServiceFacade shopCouponMoneyTypeServiceFacade;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("shopCouponMoneyType/couponMoneyTypeList");
    }

    /**
     * 欣券金额列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(String money,byte status,long page, long rows) {
        Map<String, Object> map = new HashMap<>();
        map.put("rows", shopCouponMoneyTypeServiceFacade.getList(money, status,page, rows));
        map.put("total", shopCouponMoneyTypeServiceFacade.getCount(money, status));
        return map;
    }

    @RequestMapping("/goAdd")
    public ModelAndView goAdd(){
        return new ModelAndView("shopCouponMoneyType/couponMoneyTypeAdd");
    }

    @RequestMapping("/add")
    @ResponseBody
    public JpfResponseDto add(PayShopCouponMoneyType payShopCouponMoneyType){
        return shopCouponMoneyTypeServiceFacade.add(payShopCouponMoneyType);
    }
}
