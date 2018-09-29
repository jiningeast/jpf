package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopBargainOrderRequest;
import com.joiest.jpf.dto.GetShopBargainOrderResponse;
import com.joiest.jpf.entity.ShopBargainOrderInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.ShopBargainOrderServiceFacade;
import com.joiest.jpf.facade.ShopCouponRemainServiceFacade;
import com.joiest.jpf.facade.ShopCustomerServiceFacade;
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
@RequestMapping("/shopBargainOrder")
public class ShopBargainOrderController {

    @Autowired
    private ShopBargainOrderServiceFacade shopBargainOrderServiceFacade;

    @Autowired
    private ShopCustomerServiceFacade shopCustomerServiceFacade;

    @Autowired
    ShopCouponRemainServiceFacade shopCouponRemainServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "shopBargainOrder/List";
    }

    /**
     * 运营采购订单列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopBargainOrderRequest request) {
        //定义运营角色对应的状态值
        List<Byte> statusArr=new ArrayList<Byte>();
        statusArr.add((byte)0);  //未支付
        statusArr.add((byte)1);  //运营已审核
        statusArr.add((byte)5); //取消
        request.setStatusArr(statusArr);
        GetShopBargainOrderResponse response= shopBargainOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }


    @RequestMapping("/indexCaiwu")
    public String indexCaiwu(){
        return "shopBargainOrder/ListCaiwu";
    }


    /**
     * 财务订单列表
     */
    @RequestMapping("/listCaiwu")
    @ResponseBody
    public Map<String, Object> listCaiwu(GetShopBargainOrderRequest request) {
        //定义运营角色对应的状态值
        List<Byte> statusArr=new ArrayList<Byte>();
        statusArr.add((byte)1);  //运营已审核
        statusArr.add((byte)2);  //打款中
        statusArr.add((byte)3); //失败
        statusArr.add((byte)4); //成功
        statusArr.add((byte)5); //取消
        request.setStatusArr(statusArr);
        GetShopBargainOrderResponse response= shopBargainOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 客户详情页面
     */
    @RequestMapping("/orderInfo")
    @ResponseBody
    public ModelAndView orderInfo(String orderNo, ModelMap modelMap){
        //根据订单号查出订单详细信息
        ShopBargainOrderInfo shopBargainOrderInfo= shopBargainOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopBargainOrderInfo", shopBargainOrderInfo);
        return new ModelAndView("shopBargainOrder/orderDetail", modelMap);

    }

    /**
     * 审核页
     */
    @RequestMapping("/audit")
    @ResponseBody
    public ModelAndView audit(String orderNo,int type,ModelMap modelMap){

        ShopBargainOrderInfo shopBargainOrderInfo=shopBargainOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopBargainOrderInfo",shopBargainOrderInfo);
        if(type==1){
            modelMap.addAttribute("type",1);
        }else if(type==2){
            modelMap.addAttribute("type",2);
        }
        return new ModelAndView("shopBargainOrder/audit", modelMap);

    }

    /**
     * 审核操作
     */
    @RequestMapping("/audit/action")
    @ResponseBody

    public JpfResponseDto AuditAction(GetShopBargainOrderRequest request, HttpServletRequest httpRequest)throws Exception{

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);
        request.setOperatorId(userInfo.getId().toString());
        request.setOperatorName(userInfo.getUserName());
        return shopBargainOrderServiceFacade.AuditOrder(request);

    }
}
