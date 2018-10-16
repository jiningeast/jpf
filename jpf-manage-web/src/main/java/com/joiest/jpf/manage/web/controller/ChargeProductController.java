package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.ChargeBrandServiceFacade;
import com.joiest.jpf.facade.ChargeProductServiceFacade;
import com.joiest.jpf.facade.ChargeProductTypeServiceFacade;
import com.joiest.jpf.facade.ChargeSupplierServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chargeProduct")
public class ChargeProductController {

    @Autowired
    private ChargeProductServiceFacade chargeProductServiceFacade;

    @Autowired
    private ChargeSupplierServiceFacade chargeSupplierServiceFacade;

    @Autowired
    private ChargeProductTypeServiceFacade chargeProductTypeServiceFacade;

    @Autowired
    private ChargeBrandServiceFacade chargeBrandServiceFacade;

    @RequestMapping("index")
    public ModelAndView index()
    {
        return new ModelAndView("chargeProduct/index");
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> list(GetChargeProductRequest request)
    {
        Map<String, Object> map = new HashMap<>();
        GetChargeProductResponse response = chargeProductServiceFacade.getProductList(request);
        map.put("rows", response.getList());
        map.put("total", response.getCount());
        return map;
    }

    /**
     * 商品基础信息页面
     */
    @RequestMapping("chargeInfoPage")
    public ModelAndView pInfoPage()
    {
        return new ModelAndView("chargeProduct/pinfoAdd");
    }

    /**
     * 添加商品页面
     */
    @RequestMapping("addPage")
    public ModelAndView addView(){
        return  new ModelAndView("chargeProduct/chargeProductAdd");
    }

    /**
     * 添加商品动作
     */
    @RequestMapping("addAction")
    @ResponseBody
    public JpfResponseDto addAction(ChargeProductInfo info,HttpServletRequest httprequest )
    {
        HttpSession session = httprequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        info.setOperatorId(""+userInfo.getId());
        info.setOperatorName(userInfo.getUserName());
        info.setAddtime(new Date());

        int insertRes = chargeProductServiceFacade.addChargeProduct(info);
        if ( insertRes > 0 ){
            return new JpfResponseDto();
        }else{
            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("添加失败");

            return jpfResponseDto;
        }
    }

    /**
     * 编辑商品页面
     */
    @RequestMapping("editPage")
    public ModelAndView modifyPage(String id, ModelMap modelMap)
    {
        //产品信息
        ChargeProductInfo productInfo = chargeProductServiceFacade.getChargeProduct(id);
        modelMap.addAttribute("productOne", productInfo);

        return new ModelAndView("chargeProduct/chargeProductEdit", modelMap);
    }

    /**
     * 编辑商品动作
     */
    @RequestMapping("editAction")
    @ResponseBody
    public JpfResponseDto modifyAction(ChargeProductInfo info,HttpServletRequest httprequest )
    {
        ValidatorUtils.validate(info);

        HttpSession session = httprequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        info.setOperatorId(""+userInfo.getId());
        info.setOperatorName(userInfo.getUserName());
        info.setUpdatetime(new Date());

        return chargeProductServiceFacade.modifyChargeProduct(info);
    }

    /**
     * 获取所有charge_product_type 商品类型列表
     */
    @RequestMapping("getChargeProductType")
    @ResponseBody
    public List<ChargeProductTypeInfo> getAllChargeProductTypeList()
    {
        return chargeProductTypeServiceFacade.getAllShopProductTypeList();
    }

    /**
     * 获取所有charge_product_brand  品牌列表
     */
    @RequestMapping("getChargeBrandList")
    @ResponseBody
    public List<ChargeProductBrandInfo> getAllChargeBrandList()
    {
        return chargeBrandServiceFacade.getShopBrandAllList();
    }

    /**
     * 获取所有charge_product_brand  供应商列表
     */
    @RequestMapping("getChargeSuppliers")
    @ResponseBody
    public List<ChargeProductSupplierInfo> getAllChargeSupplierList()
    {
        return chargeSupplierServiceFacade.getShopSupplierList();
    }

    /**
     * 类型添加
     */
    @RequestMapping("producttype/add")
    @ResponseBody
    public JpfResponseDto shopProductTypeAdd(ChargeProductTypeRequest request, HttpServletRequest httpRequest)
    {
        ValidatorUtils.validate(request);

        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());

        return chargeProductTypeServiceFacade.addShopProductType(request);
    }

    /**
     * 品牌添加
     */
    @RequestMapping("brand/add")
    @ResponseBody
    public JpfResponseDto brandAdd(ChargeBrandRequest request, HttpServletRequest httpRequest)
    {
        ValidatorUtils.validate(request);
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());

        return chargeBrandServiceFacade.addBrand(request);
    }

    /**
     * 供应商添加
     */
    @RequestMapping("supplier/add")
    @ResponseBody
    public JpfResponseDto supplierAdd(ChargeSupplierRequest request, HttpServletRequest httpRequest)
    {
        ValidatorUtils.validate(request);

        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());

        return chargeSupplierServiceFacade.addShopProductSupplier(request);
    }

    /**
     * 类型获取
     */
    @RequestMapping("productType/get")
    @ResponseBody
    public ChargeProductTypeInfo getChargeProductType(String id){
        return chargeProductTypeServiceFacade.getRecord(id);
    }

}
