package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.*;
import com.joiest.jpf.facade.ProductServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetProductRequest request) {
        GetProductResponse response = productServiceFacade.getProductList(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }


}
