package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/param")
public class ParamController {

    private static final Logger logger = LogManager.getLogger(ParamController.class);

    @Autowired
    private PcaServiceFacade pcaServiceFacade;

    @Autowired
    private MerTypeServiceFacade merTypeServiceFacade;

    @Autowired
    private MerAgentServiceFacade merAgentServiceFacade;

    @Autowired
    private BankServiceFacade bankServiceFacade;

    /**
     * shop_product_type
     */
    @Autowired
    private ShopProductTypeServiceFacade shopProductTypeServiceFacade;

    /**
     * shop_supplier
     */
    @Autowired
    private ShopSupplierServiceFacade shopSupplierServiceFacade;
    /**
     * shop_brand
     */
    @Autowired
    private ShopBrandServiceFacade shopBrandServiceFacade;

    private List<PcaInfo> pcaInfoList;

    @RequestMapping("/getPca")
    @ResponseBody
    public List<PcaInfo> getPca(String pid){
        pcaInfoList = pcaServiceFacade.getPcas(StringUtils.isBlank(pid)?"0":pid);
        logger.info("pcaInfoList="+pcaInfoList);
        return pcaServiceFacade.getPcas(StringUtils.isBlank(pid)?"0":pid);
    }

    @RequestMapping("/getType")
    @ResponseBody
    public List<MerchantTypeInfo> getType(String pid) {
        return merTypeServiceFacade.getMerTypes(pid);
    }

    /**
     * 根据不同等级，获取不同等级商户信息
     */
    @RequestMapping("/getAgentInfo")
    @ResponseBody
    public List<MerchantInfo> getAgentInfo(String tpid)
    {
        return merAgentServiceFacade.getAgentInfoByTpid(tpid);
    }

    /**
     * 获取单条信息 pay_merchants_type
     */
    @RequestMapping("/getTypeOne")
    @ResponseBody
    public MerchantTypeInfo getTypeByCatid(String catid){
        return merTypeServiceFacade.getOneTypeByCatid(catid);
    }

    //获取单条信息 pay_merchants_type
    @RequestMapping("/getOneType")
    @ResponseBody
    public List<MerchantTypeInfo> getOneTypeByCatId(String catid) {
        return merTypeServiceFacade.getOneTypeByCatId(catid);
    }

    //获取所有的银行信息 pay_bank
    @RequestMapping("/getBankAll")
    @ResponseBody
    public List<BankInfo> getBankAll(){
        return bankServiceFacade.getBankAll();
    }

    /**
     * 获取所有shop_product_type 商品类型列表
     */
    @RequestMapping("/getShopProductType")
    @ResponseBody
    public List<ShopProductTypeInfo> getAllShopProductTypeList()
    {
        return shopProductTypeServiceFacade.getAllShopProductTypeList();
    }

    /**
     * 获取所有shop_product_brand  品牌列表
     */
    @RequestMapping("/getShopBrandList")
    @ResponseBody
    public List<ShopBrandInfo> getAllShopBrandList()
    {
        return shopBrandServiceFacade.getShopBrandAllList();
    }

    @RequestMapping("/getShopSuppliers")
    @ResponseBody
    public List<ShopSupplierInfo> getAllShopSupplierList()
    {
        return shopSupplierServiceFacade.getShopSupplierList();
    }



}
