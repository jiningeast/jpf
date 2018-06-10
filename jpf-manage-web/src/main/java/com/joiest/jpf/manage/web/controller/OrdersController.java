package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetOrdersRequest;
import com.joiest.jpf.dto.GetOrdersResponse;
import com.joiest.jpf.dto.OrderYinjiaApiResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static com.joiest.jpf.manage.web.constant.ManageConstants.PAY_DETAIL;
import static com.joiest.jpf.manage.web.constant.ManageConstants.REFUND_STATUS;
import static com.joiest.jpf.manage.web.constant.ManageConstants.USER_OPERATE_STATUS;

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
        //支付订单信息
        OrderYinjiaApiInfo orderYinjiaApiInfo = orderYinjiaApiServiceFacade.getOrderYinjiaApiByOrderid(orderid);
        OrderCpInterfaceInfo orderCpInterfaceInfo = new OrderCpInterfaceInfo();
        StringBuilder sbf = new StringBuilder();
        if ( orderYinjiaApiInfo.getSignOrderid() != null )
        {
            //商品名称
            Map<String,String> paramMap = ToolUtils.urlToMap(orderYinjiaApiInfo.getForeignRequest());
            if ( paramMap.containsKey("productName") )
            {
                orderYinjiaApiInfo.setProductName(paramMap.get("productName"));
            }
            orderYinjiaApiInfo.setUserOperateStatus_cn(USER_OPERATE_STATUS.get(orderYinjiaApiInfo.getUserOperateStatus().toString()));
            orderYinjiaApiInfo.setRefundStatus_cn(REFUND_STATUS.get(orderYinjiaApiInfo.getRefundStatus().toString()));

            if ( StringUtils.isNotBlank(orderYinjiaApiInfo.getPayDetail()) )
            {
                Map<String,String> payDetail_Map = JsonUtils.toCollection(orderYinjiaApiInfo.getPayDetail(), new TypeReference<Map<String, String>>() {});
                Set<String> keys = payDetail_Map.keySet();
                Iterator<String> it = keys.iterator();
                while ( it.hasNext() )
                {
                    String key = it.next();
                    if ( PAY_DETAIL.get(key) != null )
                    {
                        sbf.append( PAY_DETAIL.get(key) );
                        sbf.append(payDetail_Map.get(key));
                        sbf.append( ";<br/>" );
                    }
                }
            }
            //签约信息
            orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpByorderid(orderYinjiaApiInfo.getSignOrderid().toString());
        }

        //支付回调信息
        //同步信息
        List<OrderPayMessageInfo> payMessagetReturnList = orderPayMessageServiceFacade.getOrderPayMessageReturnListByOrderId(orderid);

        //支付回调信息
        List<OrderPayMessageInfo> payMessagetNotifyList = orderPayMessageServiceFacade.getOrderPayMessageNotifyListByOrderId(orderid);

        //支付回调给商户的信息
        List<OrderPayMerMessageInfo> payMerMessageList = orderPayMerMessageServiceFacade.getOrderPayMerMessageListByOrderId(orderid);

        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(orderYinjiaApiInfo.getMtsid());

        modelMap.addAttribute("apiInfo", orderYinjiaApiInfo);
        modelMap.addAttribute("returnList", payMessagetReturnList);
        modelMap.addAttribute("notifyList", payMessagetNotifyList);
        modelMap.addAttribute("payMerMsgList", payMerMessageList);
        modelMap.addAttribute("orderCpInfo", orderCpInterfaceInfo);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.put("payDetaiStr", sbf.toString());
        return new ModelAndView("orders/paydetail", modelMap);

    }
}
