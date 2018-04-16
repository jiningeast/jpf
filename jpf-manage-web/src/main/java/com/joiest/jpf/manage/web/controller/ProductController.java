package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.MerchantTypeInfo;
import com.joiest.jpf.entity.ProductInfo;
import com.joiest.jpf.facade.ProductServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    private ProductServiceFacade productServiceFacade;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("product/productList");
    }

    /**
     * 产品列表页
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(Long pid, Long mtsid, String pname, Byte status, long page,long rows) {
        Map<String, Object> map = new HashMap<>();
        map.put("rows", productServiceFacade.getProductsList(pid, mtsid, pname, status, page, rows));
        map.put("total", productServiceFacade.getProductsCount(pid, mtsid, pname, status));
        return map;
    }

    /**
     * 上下架
     */
    @RequestMapping("/alertStatus")
    @ResponseBody
    public JpfResponseDto alertStatus(String pname,Byte status){
        return productServiceFacade.upStatus(pname,status);
    }

    /**
     * 产品编辑-获取单个产品信息
     */
    @RequestMapping("/modify/page")
    public ModelAndView modifyPage(String pid, ModelMap modelMap){
        //产品详情
        ProductInfo productInfo = productServiceFacade.getProduct(Long.valueOf(pid));
        modelMap.addAttribute("productInfo", productInfo);
        return new ModelAndView("product/productModify",modelMap);
    }

    /**
     * 产品编辑
     */
    @RequestMapping("/modify/action")
    @ResponseBody
    public JpfResponseDto modifyAction(@RequestBody ModifyProductRequest request){

        return productServiceFacade.modifyProduct(request);
    }

}
