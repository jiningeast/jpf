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
@RequestMapping("/shopproductinfo")
public class ShopProductInfoController {

    @Autowired
    private ShopProductServiceFacade shopProductServiceFacade;

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

    @Autowired
    private ShopProductInfoServiceFacade shopProductInfoServiceFacade;

    @RequestMapping("/index")
    public ModelAndView index()
    {
        return new ModelAndView("shopproductinfo/index");
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopProductInfoRequest request)
    {
        Map<String, Object> map = new HashMap<>();
        GetShopProductInfoResponse response = shopProductInfoServiceFacade.getRecords(request);
        map.put("rows", response.getList());
        map.put("total", response.getCount());
        return map;
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


    @RequestMapping("modify/page")
    public ModelAndView modifyPage(String id, ModelMap modelMap)
    {
        //产品信息
        ShopProductInfoInfo productInfo = shopProductInfoServiceFacade.getOne(id);
        modelMap.addAttribute("productOne", productInfo);
        return new ModelAndView("shopproductinfo/shopproductModify", modelMap);
    }

    /**
     * 商品信息编辑
     */
    @RequestMapping("modify/action")
    @ResponseBody
    public JpfResponseDto modifyAction(ShopProductInfoRequest request,HttpServletRequest httprequest )
    {
        ValidatorUtils.validate(request);

        HttpSession session = httprequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId());
        request.setOperatorName(userInfo.getUserName());

        return shopProductInfoServiceFacade.editShopProductInfo(request);
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

    @RequestMapping("supplier/edit")
    @ResponseBody
    public JpfResponseDto supplierEdit(ShopSupplierRequest request, HttpServletRequest httpRequest)
    {
        ValidatorUtils.validate(request);

        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId());
        request.setOperatorName(userInfo.getUserName());

        return shopSupplierServiceFacade.editShopProductSupplier(request);
    }

    @RequestMapping("producttype/edit")
    @ResponseBody
    public JpfResponseDto shopProductTypeEdit(ShopProductTypeRequest request, HttpServletRequest httpRequest)
    {
        ValidatorUtils.validate(request);

        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId());
        request.setOperatorName(userInfo.getUserName());

        return productTypeServiceFacade.editShopProductType(request);
    }

    @RequestMapping("brand/edit")
    @ResponseBody
    public JpfResponseDto brandEdit(ShopBrandRequest request, HttpServletRequest httpRequest)
    {
        ValidatorUtils.validate(request);

        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId());
        request.setOperatorName(userInfo.getUserName());

        return shopBrandServiceFacade.editBrand(request);
    }

}
