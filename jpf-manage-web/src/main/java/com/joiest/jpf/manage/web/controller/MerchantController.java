package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.GetMerchsRequest;
import com.joiest.jpf.dto.GetMerchsResponse;
import com.joiest.jpf.entity.MerchantBankInfo;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.facade.MerTypeServiceFacade;
import com.joiest.jpf.facade.MerchantServiceFacade;
import com.joiest.jpf.facade.PcaServiceFacade;
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
    private PcaServiceFacade pcaServiceFacade;

    @Autowired
    private MerTypeServiceFacade merTypeServiceFacade;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("merchant/merchantList");
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetMerchsRequest request) {
        GetMerchsResponse response = merchantServiceFacade.getMerchInfoList(request);

//        List<MerchantInfo> merchantInfoList = new ArrayList<>();
//        MerchantInfo merchantInfo = new MerchantInfo();
//        merchantInfo.setId((long) 1);
//        merchantInfo.setMerchNo("11111");
//        merchantInfo.setMerchName("11111测试");
//        merchantInfo.setUsername("mzq");
//        merchantInfo.setCompanyname("企业名称");
//        merchantInfo.setProvince((long) 1);
//        merchantInfo.setCity((long) 1);
//        merchantInfo.setLinkname("马泽强");
//        merchantInfo.setLinkphone("18310231619");
//        merchantInfo.setAddtime(new Date());
//        merchantInfo.setRegisterip("192.168.11.111");
//        merchantInfo.setLastloginip("192.168.222.222");
//        merchantInfo.setStatus((byte) 0);
//        merchantInfo.setBslicense("132465797987");
//        merchantInfo.setAptitude("1111");
//        merchantInfo.setLogo("aaaa");
//        merchantInfo.setAttestation(true);
//        merchantInfo.setMuserid(1);
//        merchantInfoList.add(merchantInfo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    @RequestMapping("/modify/page")
    public ModelAndView modifyPage(ModelMap modelMap){
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant((long) 1);
        MerchantBankInfo merchantBankInfo = merchantServiceFacade.getMerchBank((long) 1);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merchantBankInfo", merchantBankInfo);
        return new ModelAndView("merchant/merchantModify",modelMap);
    }
}
