package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.AddMerPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeResponse;
import com.joiest.jpf.facade.MerPayTypeServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/merchant/paytype")
public class MerchantPaytypeController {

    private static final Logger logger = LogManager.getLogger(MerchantPaytypeController.class);

    @Autowired
    private MerPayTypeServiceFacade merPayTypeServiceFacade;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("merchant/merchantPaytypeList");
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetMerchPayTypeRequest request) {
        GetMerchPayTypeResponse response = merPayTypeServiceFacade.getMerPayTypes(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", response.getCount());
        map.put("rows", response.getPayTypeInfos());
        return map;
    }

    @RequestMapping("/add/page")
    public ModelAndView addPage(String id,ModelMap modelMap){
        modelMap.put("mtsid",id);
        return new ModelAndView("merchant/merchantPaytypeAdd",modelMap);
    }

    @RequestMapping("/add/action")
    @ResponseBody
    public JpfResponseDto addAction(@RequestBody AddMerPayTypeRequest request){
        return merPayTypeServiceFacade.addMerPayType(request);

    }

    @RequestMapping("/delete/action")
    @ResponseBody
    public JpfResponseDto deleteAction(@RequestParam("id[]") List<Long> id){
        return merPayTypeServiceFacade.deleteMerPayType(id);
    }
}