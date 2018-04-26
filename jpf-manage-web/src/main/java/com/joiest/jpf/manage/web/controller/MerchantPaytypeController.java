package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.AddMerPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeResponse;
import com.joiest.jpf.dto.ModifyMerPayTypeRequest;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.entity.MerchantPayTypeInfo;
import com.joiest.jpf.entity.MerchantTypeInfo;
import com.joiest.jpf.facade.MerPayTypeServiceFacade;
import com.joiest.jpf.facade.MerTypeServiceFacade;
import com.joiest.jpf.facade.MerchantServiceFacade;
import com.joiest.jpf.facade.impl.MerTypeServiceFacadeImpl;
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

    @Autowired
    private MerchantServiceFacade merchantServiceFacade;

    @Autowired
    private MerTypeServiceFacade merTypeServiceFacade;
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
        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        //当前已有支付类型
        GetMerchPayTypeResponse payTypes = merPayTypeServiceFacade.getOneMerPayTypes(Long.valueOf(id));
        modelMap.put("mtsid",id);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("payTypeList", payTypes.getPayTypeInfos());
        return new ModelAndView("merchant/merchantPaytypeAdd",modelMap);
    }

    /**
     * 实际添加页面
     */
    @RequestMapping("add/realpage")
    public ModelAndView addRealPage(String catid, String id, ModelMap modelMap){
        //获取单个支付类型信息
        MerchantTypeInfo merchantTypeInfo = merTypeServiceFacade.getOneTypeByCatid(catid);
        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(id));
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("merchantTypeInfo", merchantTypeInfo);
        return new ModelAndView("merchant/merchantPaytypeRealAdd", modelMap);
    }

    @RequestMapping("/add/action")
    @ResponseBody
    public JpfResponseDto addAction(AddMerPayTypeRequest request){
        return merPayTypeServiceFacade.addMerPayType(request);

    }

    @RequestMapping("/add/addMerPayTypeOne")
    @ResponseBody
    public JpfResponseDto addMerPayTypeOne(ModifyMerPayTypeRequest request){
        return merPayTypeServiceFacade.addMerPayTypeOne(request);

    }

    /**
     * 实际编辑页面
     * @param  id pay_merchants_paytype.id
     * @param tpid  pay_merchant_paytype.tpid 支付类型ID
     */
    @RequestMapping("modify/realpage")
    public ModelAndView modifyRealPage(String mtsid, String id, String tpid, ModelMap modelMap){
        //获取某个商户的单个支付类型
        MerchantPayTypeInfo merpayTypeInfoOne = merPayTypeServiceFacade.getMerOnePayTypes(Long.valueOf(id));
        //获取单个支付类型信息
        MerchantTypeInfo TypeInfoOne = merTypeServiceFacade.getOneTypeByCatid(tpid);
        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(Long.valueOf(mtsid));
        modelMap.addAttribute("merpayTypeInfoOne", merpayTypeInfoOne);
        modelMap.addAttribute("TypeInfoOne", TypeInfoOne);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        return new ModelAndView("merchant/merchantPaytypeRealModify", modelMap);
    }

    @RequestMapping("/modify/modifyMerPayTypeOne")
    @ResponseBody
    public JpfResponseDto modifyMerPayTypeOne(ModifyMerPayTypeRequest request){
        return merPayTypeServiceFacade.modifyMerPayTypeOne(request);

    }

    @RequestMapping("/delete/action")
    @ResponseBody
    public JpfResponseDto deleteAction(@RequestParam("id[]") List<Long> id){
        return merPayTypeServiceFacade.deleteMerPayType(id);
    }
}
