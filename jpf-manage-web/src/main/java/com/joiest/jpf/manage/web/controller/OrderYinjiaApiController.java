package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.dto.OrderYinjiaApiRequest;
import com.joiest.jpf.dto.OrderYinjiaApiResponse;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.entity.OrderCpInterfaceInfo;
import com.joiest.jpf.entity.OrderYinjiaApiInfo;
import com.joiest.jpf.facade.MerchantServiceFacade;
import com.joiest.jpf.facade.OrderCpServiceFacade;
import com.joiest.jpf.facade.OrderYinjiaApiServiceFacade;
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

        List<OrderYinjiaApiInfo> list = new ArrayList<>();
        if ( Integer.parseInt(map.get("total").toString() )  > 0 )
        {
            for (OrderYinjiaApiInfo one : orderYinjiaApiResponse.getList() )
            {
                one.setUserOperateStatus_cn(USER_OPERATE_STATUS.get(one.getUserOperateStatus().toString()));
                one.setRefundStatus_cn(REFUND_STATUS.get(one.getRefundStatus().toString()));
                list.add(one);
            }
        }
        map.put("refundStatus",REFUND_STATUS);
        map.put("rows", list);
        // 统计汇总
//        BigDecimal allOrderMoney = orderYinjiaApiResponse.getAllOrdersMoney();
//        BigDecimal allRefundMoney = orderYinjiaApiResponse.getAllRefundMoney();
//        map.put("allOrdersCount", orderYinjiaApiResponse.getAllOrdersCount());
//        map.put("allOrdersMoney", allOrderMoney);
//        map.put("allRefundMoney", allRefundMoney);
//        map.put("profitMoney", BigDecimalCalculateUtils.sub(allOrderMoney.doubleValue(), allRefundMoney.doubleValue()) );

        return map;
    }

    /**
     * 获取订单支付的银行卡信息
     */
    @RequestMapping("cpinfo")
    public ModelAndView getOrderPayBankInfo(String id, ModelMap modelMap)
    {
        OrderCpInterfaceInfo orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpByorderid(id);

        //支付回调信息


        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(orderCpInterfaceInfo.getMtsid());
        modelMap.addAttribute("orderCpInfo", orderCpInterfaceInfo);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        return new ModelAndView("orderyinjia/ordercpinfo", modelMap);

    }
}
