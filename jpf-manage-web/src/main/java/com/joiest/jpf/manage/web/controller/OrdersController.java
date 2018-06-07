package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.GetOrdersRequest;
import com.joiest.jpf.dto.GetOrdersResponse;
import com.joiest.jpf.dto.OrderYinjiaApiResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersServiceFacade ordersServiceFacade;

    //获取签约信息
    @Autowired
    private OrderCpServiceFacade orderCpServiceFacade;

    //获取商户信息
    @Autowired
    private MerchantServiceFacade merchantServiceFacade;

    //支付回调流水信息
    @Autowired
    private OrderPayMessageServiceFacade orderPayMessageServiceFacade;

    //支付回调给商户的流水信息
    @Autowired
    private OrderPayMerMessageServiceFacade orderPayMerMessageServiceFacade;

    //支付订单信息
    @Autowired
    private OrderYinjiaApiServiceFacade orderYinjiaApiServiceFacade;

    @RequestMapping("/index")
    public String index()
    {
        return "orders/orderslist";
    }

    //列表
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(GetOrdersRequest request)
    {
        GetOrdersResponse response = ordersServiceFacade.getOrdersList(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 获取订单支付的银行卡信息
     */
    @RequestMapping("paydetail")
    public ModelAndView getOrderPayBankInfo(String orderid, ModelMap modelMap)
    {

        OrderYinjiaApiInfo orderYinjiaApiInfo = orderYinjiaApiServiceFacade.getOrderYinjiaApiByOrderid(orderid);
        OrderCpInterfaceInfo orderCpInterfaceInfo = new OrderCpInterfaceInfo();
        if ( orderYinjiaApiInfo.getSignOrderid() != null )
        {
            orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpByorderid(orderYinjiaApiInfo.getSignOrderid().toString());
        }

        //支付回调信息
        List<OrderPayMessageInfo> payMessagetList = orderPayMessageServiceFacade.getOrderPayMessageListByOrderId(orderid);

        //支付回调给商户的信息
        List<OrderPayMerMessageInfo> payMerMessageList = orderPayMerMessageServiceFacade.getOrderPayMerMessageListByOrderId(orderid);

        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(orderYinjiaApiInfo.getMtsid());
        modelMap.addAttribute("apiInfo", orderYinjiaApiInfo);
        modelMap.addAttribute("orderCpInfo", orderCpInterfaceInfo);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        return new ModelAndView("orderyinjia/ordercpinfo", modelMap);

    }
}
