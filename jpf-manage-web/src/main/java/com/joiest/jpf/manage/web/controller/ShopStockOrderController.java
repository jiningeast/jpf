package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.ShopStockOrderInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ShopProductServiceFacade;
import com.joiest.jpf.facade.ShopStockOrderProductServiceFacade;
import com.joiest.jpf.facade.ShopStockOrderServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/shopStockOrder")
public class ShopStockOrderController {

    @Autowired
    private ShopStockOrderServiceFacade shopStockOrderServiceFacade;

    @Autowired
    private ShopStockOrderProductServiceFacade shopStockOrderProductServiceFacade;

    @Autowired
    private ShopProductServiceFacade shopProductServiceFacade;
    /**
     * 运营采购订单页面
     */
    @RequestMapping("/index")
    public String index(){
        return "shopStockOrder/List";
    }
    /**
     * 运营采购订单列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopStockOrderRequest request) {
        //定义运营角色对应的状态值
        List<Byte> statusArr=new ArrayList<Byte>();
        statusArr.add((byte)0);  //取消
        statusArr.add((byte)1);  //新建
        statusArr.add((byte)2); //审批
        request.setStatusArr(statusArr);
        GetShopStockOrderResponse response= shopStockOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }


    /**
     * 财务采购订单页面
     */
    @RequestMapping("/indexCaiwu")
    public String indexCaiwu(){
        return "shopStockOrder/ListCaiwu";
    }
    /**
     * 财务采购订单列表
     */
    @RequestMapping("/listCaiwu")
    @ResponseBody
    public Map<String, Object> listCaiwu(GetShopStockOrderRequest request) {
        //定义财务角色对应的状态值
        List<Byte> statusArr=new ArrayList<Byte>();
        statusArr.add((byte)3);  //审批
        statusArr.add((byte)4);  //付款
        statusArr.add((byte)0);  //取消
        request.setStatusArr(statusArr);
        GetShopStockOrderResponse response= shopStockOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }
    /**
     * 采购商品列表
     */
    @RequestMapping("/productsList")
    @ResponseBody
    public Map<String, Object> productsList(String OrderNo) {

        GetShopStockOrderProductResponse response= shopStockOrderProductServiceFacade.getProduct(OrderNo);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }



    /**
     * 添加申请页面
     */
    @RequestMapping("/add/page")
    @ResponseBody
    public ModelAndView addPage(ModelMap modelMap){

        return new ModelAndView("shopStockOrder/add", modelMap);

    }

    @RequestMapping("/products")
    public ModelAndView products()
    {
        return new ModelAndView("shopStockOrder/products");
    }

    @RequestMapping("/plist")
    @ResponseBody
    public Map<String, Object> plist(GetShopProductRequest request)
    {
        Map<String, Object> map = new HashMap<>();
        GetShopProductResponse response = shopProductServiceFacade.getProductList(request);
        map.put("rows", response.getList());
        map.put("total", response.getCount());
        return map;
    }

    /**
     * 审核页
     */
    @RequestMapping("/audit/page")
    @ResponseBody
    public ModelAndView auditPage(String orderNo,int type,ModelMap modelMap){

        ShopStockOrderInfo shopStockOrderInfo= shopStockOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopStockOrderInfo",shopStockOrderInfo);
        if(type==1){
            modelMap.addAttribute("type",1);
        }else if(type==2){
            modelMap.addAttribute("type",2);
        }
        return new ModelAndView("shopStockOrder/audit", modelMap);

    }

    /**
     * 详情页
     */
    @RequestMapping("/view/page")
    @ResponseBody
    public ModelAndView viewPage(String orderNo,ModelMap modelMap){

        ShopStockOrderInfo shopStockOrderInfo= shopStockOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopStockOrderInfo",shopStockOrderInfo);
        return new ModelAndView("shopStockOrder/view", modelMap);

    }

    /**
     * 新增采购订单
     */
    @RequestMapping("/submitProducts")
    @ResponseBody
    public JpfResponseDto submitProducts( GetShopStockOrderRequest request,HttpServletRequest httpRequest)throws Exception{

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());
        // 存储数据
        shopStockOrderServiceFacade.addStocks(request);
        return new JpfResponseDto();
    }

    /**
     * 审核操作
     */
    @RequestMapping("/audit/action")
    @ResponseBody

    public JpfResponseDto AuditAction(GetShopStockOrderRequest request,HttpServletRequest httpRequest)throws Exception{

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());
        request.setCheckOperatorId(userInfo.getId().toString());
        request.setCheckOperatorName(userInfo.getUserName());
        return shopStockOrderServiceFacade.AuditOrder(request);

    }




}
