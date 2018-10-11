package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.ShopProductInfo;
import com.joiest.jpf.entity.ShopProductInfoInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopproduct")
public class ChargeProductController {

    @Autowired
    private ShopProductServiceFacade shopProductServiceFacade;

    @Autowired
    private ChargeProductServiceFacade chargeProductServiceFacade;

    /**
     * 供应商
     */
    @Autowired
    private ShopSupplierServiceFacade shopSupplierServiceFacade;

    /**
     * 商品分类
     * @return
     */
    @Autowired
    private ShopProductTypeServiceFacade productTypeServiceFacade;

    @Autowired
    private ShopBrandServiceFacade shopBrandServiceFacade;

    @RequestMapping("/index")
    public ModelAndView index()
    {
        return new ModelAndView("shopproduct/index");
    }

    @RequestMapping("/list")
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
     * 上下架
     * 1: 上架; 1: 下架
     */
    @RequestMapping("/alertStatus")
    @ResponseBody
    public JpfResponseDto alertStatus(String id, Byte status,HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        return shopProductServiceFacade.upStatus(id,status,userInfo);
    }

    /**
     * 添加商品
     */
    @RequestMapping("/add/page")
    public ModelAndView addView(){
        return  new ModelAndView("shopproduct/shopproductAdd");
    }

    /**
     * 获取商品基础信息
     */
    @RequestMapping("/getProductInfo")
    @ResponseBody
    public List<ShopProductInfoInfo> getProductInfoList()
    {
        List<ShopProductInfoInfo> list = shopProductServiceFacade.getProductInfoList();
        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        return list;
    }

    /**
     * 添加商品
     */
    @RequestMapping("/add/action")
    @ResponseBody
    public JpfResponseDto addAction(ModifyShopProductRequest request,HttpServletRequest httprequest )
    {
//        ValidatorUtils.validate(request);

        HttpSession session = httprequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId());
        request.setOperatorName(userInfo.getUserName());

        return shopProductServiceFacade.addShopProduct(request);
    }

    @RequestMapping("modify/page")
    public ModelAndView modifyPage(String id, ModelMap modelMap)
    {
        //产品信息
        ShopProductInfo productInfo = shopProductServiceFacade.getShopProduct(id);
        modelMap.addAttribute("productOne", productInfo);
        return new ModelAndView("shopproduct/shopproductModify", modelMap);
    }

    /**
     * 商品信息编辑
     */
    @RequestMapping("modify/action")
    @ResponseBody
    public JpfResponseDto modifyAction(ModifyShopProductRequest request,HttpServletRequest httprequest )
    {
        ValidatorUtils.validate(request);

        HttpSession session = httprequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId());
        request.setOperatorName(userInfo.getUserName());

        return shopProductServiceFacade.modifyShopProduct(request);
    }

    /**
     * 商品基础信息添加
     */
    @RequestMapping("pInfoAdd/page")
    public ModelAndView pInfoPage(ModelMap modelMap)
    {
        return new ModelAndView("shopproduct/pinfoAdd");
    }

    @RequestMapping("pInfoAdd/action")
    @ResponseBody
    public JpfResponseDto pinfoAdd(ShopProductInfoRequest request, HttpServletRequest httpRequest)
    {
        ValidatorUtils.validate(request);

        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId());
        request.setOperatorName(userInfo.getUserName());

        return shopProductServiceFacade.addShopProductInfo(request);
    }

    @RequestMapping("supplier/add")
    @ResponseBody
    public JpfResponseDto supplierAdd(ShopSupplierRequest request, HttpServletRequest httpRequest)
    {
        ValidatorUtils.validate(request);

        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId());
        request.setOperatorName(userInfo.getUserName());

        return shopSupplierServiceFacade.addShopProductSupplier(request);
    }

    @RequestMapping("producttype/add")
    @ResponseBody
    public JpfResponseDto shopProductTypeAdd(ShopProductTypeRequest request, HttpServletRequest httpRequest)
    {
        ValidatorUtils.validate(request);

        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId());
        request.setOperatorName(userInfo.getUserName());

        return productTypeServiceFacade.addShopProductType(request);
    }

    @RequestMapping("brand/add")
    @ResponseBody
    public JpfResponseDto brandAdd(ShopBrandRequest request, HttpServletRequest httpRequest)
    {
        ValidatorUtils.validate(request);

        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId());
        request.setOperatorName(userInfo.getUserName());

        return shopBrandServiceFacade.addBrand(request);
    }

}
