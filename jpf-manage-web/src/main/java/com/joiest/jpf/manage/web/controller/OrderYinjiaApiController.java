package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.OrderYinjiaApiRequest;
import com.joiest.jpf.dto.OrderYinjiaApiResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.management.modelmbean.ModelMBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.joiest.jpf.manage.web.constant.ManageConstants.REFUND_STATUS;
import static com.joiest.jpf.manage.web.constant.ManageConstants.USER_OPERATE_STATUS;

@Controller
@RequestMapping("/orderyinjia")
public class OrderYinjiaApiController {

    @Autowired
    private OrderYinjiaApiServiceFacade orderYinjiaApiServiceFacade;

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

    @RequestMapping("/index")
    public String index(){
        return "orderyinjia/orderyinjiaList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(OrderYinjiaApiRequest orderRequest){
        OrderYinjiaApiResponse orderYinjiaApiResponse = orderYinjiaApiServiceFacade.getOrderYinjiaApi(orderRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("total", orderYinjiaApiResponse.getCount());
        map.put("request", orderRequest);

        return map;
    }

    /**
     * 获取订单支付的银行卡信息
     */
    @RequestMapping("cpinfo")
    public ModelAndView getOrderPayBankInfo(String signOrderid, String orderid, ModelMap modelMap)
    {
        OrderCpInterfaceInfo orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpByorderid(signOrderid);

        //支付回调信息
        List<OrderPayMessageInfo> payMessagetList = orderPayMessageServiceFacade.getOrderPayMessageListByOrderId(orderid);

        //支付回调给商户的信息
        List<OrderPayMerMessageInfo> payMerMessageList = orderPayMerMessageServiceFacade.getOrderPayMerMessageListByOrderId(orderid);

        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(orderCpInterfaceInfo.getMtsid());
        modelMap.addAttribute("orderCpInfo", orderCpInterfaceInfo);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        return new ModelAndView("orderyinjia/ordercpinfo", modelMap);

    }
}
