package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopAd;
import com.joiest.jpf.dto.GetShopAdRequest;
import com.joiest.jpf.dto.GetShopAdResponse;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;
import com.joiest.jpf.facade.ShopAdServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shopAd")
public class ShopAdController {

    @Autowired
    private ShopAdServiceFacade shopAdServiceFacade;

    private static final Logger logger = LogManager.getLogger(ShopAdController.class);

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("shopAd/index");
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(GetShopAdRequest request){
        GetShopAdResponse shopAdResponse = shopAdServiceFacade.getList(request);

        Map<String,Object> map = new HashMap<>();
        map.put("total",shopAdResponse.getCount());
        map.put("rows",shopAdResponse.getList());

        return map;
    }

    /**
     * 添加页
     * */
    @RequestMapping("/add/page")
    public ModelAndView addPage(){
        return new ModelAndView("shopAd/add");
    }

    /**
     * 添加页处理
     * */
    @RequestMapping("/add/action")
    @ResponseBody
    public JpfResponseDto addAction(@Validated GetShopAdRequest request){

        JpfResponseDto jpfResponseDto = shopAdServiceFacade.addRecord(request);
        return jpfResponseDto;
    }

    /**
     * 修改页
     * */
    @RequestMapping("/edit/page")
    public ModelAndView editPage(String id, ModelMap modelMap){
        PayShopAd payShopAd = shopAdServiceFacade.getOneRecord(id);
        modelMap.addAttribute("payShopAd",payShopAd);

        return new ModelAndView("shopAd/edit",modelMap);
    }

    /**
     * 修改页处理
     * */
    @RequestMapping("/edit/action")
    public JpfResponseDto editAction(@Validated GetShopAdRequest request){

        JpfResponseDto jpfResponseDto = shopAdServiceFacade.addRecord(request);
        return jpfResponseDto;
    }



}
