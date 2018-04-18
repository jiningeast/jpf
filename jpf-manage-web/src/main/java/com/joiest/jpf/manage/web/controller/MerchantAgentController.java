package com.joiest.jpf.manage.web.controller;


import com.joiest.jpf.entity.MerchantAgentInfo;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.facade.MerAgentServiceFacade;
import com.joiest.jpf.facade.MerchantServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/merchant/agent")
public class MerchantAgentController {

    private static final Logger logger = LogManager.getLogger(MerchantAgentController.class);

    @Autowired
    private MerchantServiceFacade merchantServiceFacade;

    @Autowired
    private MerAgentServiceFacade merAgentServiceFacade;

    @RequestMapping("/modify/page")
    public ModelAndView modifyPage(String id,ModelMap modelMap){
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        MerchantAgentInfo merchantAgentInfo = merAgentServiceFacade.getMerchAgentInfo(Long.valueOf(id));
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merchantAgentInfo", merchantAgentInfo);
        return new ModelAndView("merchant/merchantAgentModify",modelMap);
    }
}
