package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.AuditMerchRequest;
import com.joiest.jpf.dto.GetMerchsRequest;
import com.joiest.jpf.dto.GetMerchsResponse;
import com.joiest.jpf.dto.ModifyMerchRequest;
import com.joiest.jpf.entity.MerchantBankInfo;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.entity.MerchantShopInfo;
import com.joiest.jpf.facade.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    private static final Logger logger = LogManager.getLogger(MerchantController.class);

    @Autowired
    private MerchantServiceFacade merchantServiceFacade;

    @Autowired
    private MerShopServiceFacade merShopServiceFacade;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("merchant/merchantList");
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetMerchsRequest request) {
        GetMerchsResponse response = merchantServiceFacade.getMerchInfoList(request);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    @RequestMapping("/modify/page")
    public ModelAndView modifyPage(String id,ModelMap modelMap){
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        //商户对公帐户信息
        MerchantBankInfo merchantBankInfo = merchantServiceFacade.getMerchBank(Long.valueOf(id));
        //商户的商店信息
        MerchantShopInfo merShopInfo = merShopServiceFacade.getOneMerShopInfo(Long.parseLong(id));
        byte isHeadShop;
        if ( merShopInfo == null )
        {
            isHeadShop = 0;
        } else
        {
            isHeadShop = 1;
        }
        modelMap.put("isHeadShop", isHeadShop);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merchantBankInfo", merchantBankInfo);
        return new ModelAndView("merchant/merchantModify",modelMap);
    }

    @RequestMapping("/modify/action")
    @ResponseBody
    public JpfResponseDto modifyAction(ModifyMerchRequest request){
        return merchantServiceFacade.modifyMerchant(request);
    }

    @RequestMapping("/detail")
    public ModelAndView detail(String id,ModelMap modelMap){
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        MerchantBankInfo merchantBankInfo = merchantServiceFacade.getMerchBank(Long.valueOf(id));
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merchantBankInfo", merchantBankInfo);
        return new ModelAndView("merchant/merchantDetail",modelMap);
    }

    @RequestMapping("/audit/page")
    public ModelAndView audit(String id,ModelMap modelMap){
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        MerchantBankInfo merchantBankInfo = merchantServiceFacade.getMerchBank(Long.valueOf(id));
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merchantBankInfo", merchantBankInfo);
        return new ModelAndView("merchant/merchantAudit",modelMap);
    }

    @RequestMapping("/audit/action")
    @ResponseBody
    public JpfResponseDto auditAction(AuditMerchRequest request){
        return merchantServiceFacade.auditMerchant(request);
    }
}
