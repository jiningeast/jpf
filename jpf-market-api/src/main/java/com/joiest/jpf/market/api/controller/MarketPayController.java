package com.joiest.jpf.market.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayShopCouponRemain;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.CouponListInfo;
import com.joiest.jpf.entity.PayCouponInfo;
import com.joiest.jpf.entity.ShopRefundInfo;
import com.joiest.jpf.facade.ShopCouponRemainServiceFacade;
import com.joiest.jpf.facade.ShopCustomerServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiLabelUI;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: admin
 * @Date: 2018/12/25 14:04
 * @Description:
 */
@Controller
@RequestMapping("/marketPayController")
public class MarketPayController {

    @Autowired
    private ShopCustomerServiceFacade shopCustomerServiceFacade;

    @Autowired
    private ShopCouponRemainServiceFacade shopCouponRemainServiceFacade;

    @RequestMapping(value = "pay",method = RequestMethod.POST)
    @ResponseBody
    public  String  pay(HttpServletRequest request){
        String payParam = request.getParameter("payParam");
        if(StringUtils.isBlank(payParam)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),"参数不能为空",null);
        }
        Map<String, Object> map = JsonUtils.toCollection(Base64CustomUtils.base64Decoder(payParam), new TypeReference<Map<String, Object>>() {});
        //验证商户信息,并且验证商户金额是否够
        Map<String,Object> responseMap = checkPayInfo(map);
        try {
            shopCustomerServiceFacade.pay(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    @RequestMapping(value = "refund",method = RequestMethod.POST)
    @ResponseBody
    public String refund(HttpServletRequest request){
        Map<String,Object> resMap = new HashMap<>();

        String reqParam = request.getParameter("payParam");

        if(StringUtils.isBlank(reqParam)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),"参数不能为空",null);
        }

        ShopRefundInfo shopRefundInfo = JsonUtils.toObject(Base64CustomUtils.base64Decoder(reqParam), ShopRefundInfo.class);
        Map<String,Object> responseMap = checkRefundInfo(shopRefundInfo);

        if (responseMap.size() > 0 && !"200".equals(responseMap.get("code"))){
            return JSONObject.toJSONString(responseMap);
        }

        try {
           if (shopCouponRemainServiceFacade.refundByShopRefundInfo(shopRefundInfo)){
               resMap.put("key", "200");
               resMap.put("msg", "退款成功");
           }
        }catch (Exception e){
            e.printStackTrace();
            resMap.put("key", "201");
            resMap.put("msg", "退款失败");
        }

        return JSONObject.toJSONString(resMap);
    }

    /**
     * 验证订单的数据参数信息
     * @param map
     * @return
     */
    private Map<String, Object> checkPayInfo(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();
        //验证用户状态
       if(map.get("customerId")!=null){
           PayShopCustomer shopCustomer = shopCustomerServiceFacade.getCustomerById(map.get("customerId").toString());
           if(shopCustomer==null){
               result.put("code","10001");
               result.put("msg","客户信息异常");
           }
           if(shopCustomer.getStatus()==0){
               result.put("code","10002");
               result.put("msg","该账户状态异常");
           }
       }
       if ( StringUtils.isBlank(map.get("orderNo").toString()) ){
           result.put("code","10003");
           result.put("msg","订单号不能为空");
       }
       return result;
    }

    /**
     * 验证退款信息参数
     * @param shopRefundInfo
     * @return
     */
    private Map<String,Object> checkRefundInfo(ShopRefundInfo shopRefundInfo){
        Map<String,Object> map = new HashMap<>();

        if (StringUtils.isBlank(shopRefundInfo.getCustomerId())){
            map.put("code", "201");
            map.put("msg", "用户id不能为空");
        }else{
            PayShopCustomer shopCustomer = shopCustomerServiceFacade.getCustomerById(shopRefundInfo.getCustomerId());
            if (shopCustomer == null){
                map.put("code", "202");
                map.put("msg", "用户不存在");
            }
        }

        if (shopRefundInfo.getCouponList().size() == 0){
            map.put("code", "203");
            map.put("msg", "欣券信息不能为空");
        }

        if (StringUtils.isBlank(shopRefundInfo.getTotalSaleDouNo()) || StringUtils.isBlank(shopRefundInfo.getTotalSaleDouYes())){
            map.put("code", "204");
            map.put("msg", "欣豆信息不能为空");
        }

        return map;
    }

}
