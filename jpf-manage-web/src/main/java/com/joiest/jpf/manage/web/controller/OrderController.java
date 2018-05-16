package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.util.BigDecimalCalculateUtils;
import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.facade.OrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceFacade orderServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "order/orderList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(OrderRequest orderRequest){
        OrderResponse orderResponse = orderServiceFacade.getOrders(orderRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("total", orderResponse.getCount());
        map.put("rows", orderResponse.getList());
        map.put("request", orderRequest);

        // 统计汇总
        BigDecimal allOrderMoney = orderResponse.getAllOrdersMoney();
        BigDecimal allRefundMoney = orderResponse.getAllRefundMoney();
        map.put("allOrdersCount", orderResponse.getAllOrdersCount());
        map.put("allOrdersMoney", allOrderMoney);
        map.put("allRefundMoney", allRefundMoney);
        map.put("profitMoney", BigDecimalCalculateUtils.sub(allOrderMoney.doubleValue(), allRefundMoney.doubleValue()) );

        return map;
    }
}
