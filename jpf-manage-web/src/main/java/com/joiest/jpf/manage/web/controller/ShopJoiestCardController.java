package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopOrder;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.facade.ShopJoiestCardServiceFacade;
import com.joiest.jpf.facade.ShopOrderServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 中欣卡业务控制器
 * @author admin
 */
@Controller
@RequestMapping("/shopJoiestCard")
public class ShopJoiestCardController {

    private static Logger logger = LoggerFactory.getLogger(ShopJoiestCardController.class);

    @Autowired
    private ShopOrderServiceFacade shopOrderServiceFacade;

    @Autowired
    private ShopJoiestCardServiceFacade shopJoiestCardServiceFacade;

    /**
     * 跳转到中欣卡订单列表页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "shopOrder/orderJoiestCardList";
    }

    /**
     * 查询中欣卡所有订单数据
     * @param request 请求体
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopOrderRequest request) {
        GetShopOrderResponse response= shopJoiestCardServiceFacade.getJoiestCardList(request);
        Map<String, Object> map = new HashMap<>(2);
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 中欣卡订单详情页面跳转
     * @param orderNo 订单号
     * @param modelMap 视图
     * @return
     */
    @RequestMapping("/joiestOrderInfo")
    @ResponseBody
    public ModelAndView orderInfo(String orderNo, ModelMap modelMap) {
        ShopOrderInfo shopOrderInfo = shopOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopOrderInfo", shopOrderInfo);
        return new ModelAndView("shopOrder/joiestOrderDetail", modelMap);
    }

    /**
     * 中欣卡订单审核界面跳转
     * @param orderNo 订单号
     * @param modelMap 视图
     * @return
     */
    @RequestMapping("/joiestOrderInfo/audit")
    @ResponseBody
    public ModelAndView audit(String orderNo, ModelMap modelMap) {
        ShopOrderInfo shopOrderInfo = shopOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopOrderInfo", shopOrderInfo);
        modelMap.addAttribute("type",1);
        return new ModelAndView("shopOrder/joiestCardOrderAudit", modelMap);
    }

    /**
     * 中欣卡订单审核操作
     * @param request 请求体
     * @return
     */
    @RequestMapping("/audit/action")
    @ResponseBody
    public JpfResponseDto AuditAction(PayShopOrder request) throws Exception{
        if(request == null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单参数为空");
        }
        return shopJoiestCardServiceFacade.audit(request);
    }

    /**
     * Excel资源导出
     * @param request 请求体
     * @param response 响应体
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(GetShopOrderRequest request, HttpServletResponse response){
        // TODO 执行Excel资源导出
    }
}
