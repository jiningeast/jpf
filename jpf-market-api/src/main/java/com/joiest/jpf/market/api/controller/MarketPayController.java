package com.joiest.jpf.market.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetCouponRemainResponse;
import com.joiest.jpf.facade.ShopCouponRemainServiceFacade;
import com.joiest.jpf.facade.ShopCustomerServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
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
        Map<String,Object> responseMap=new HashMap<>();
        String payParam = request.getParameter("payParam");
        if(StringUtils.isBlank(payParam)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),"参数不能为空",null);
        }
        Map<String, Object> map = JsonUtils.toCollection(Base64CustomUtils.base64Decoder(payParam), new TypeReference<Map<String, Object>>() {});
        //验证商户信息,并且验证商户金额是否够
        responseMap = checkPayInfo(map);
        if("10000".equals(responseMap.get("code").toString())){
            return JsonUtils.toJson(responseMap);
        }
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = shopCustomerServiceFacade.pay(map);
            resultMap.put("code","10000");
            resultMap.put("msg","success");
            responseMap.put("data",resultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonUtils.toJson(responseMap);
    }

    /**
     * 验证订单的数据参数信息
     * @param map
     * @return
     */
    private Map<String, Object> checkPayInfo(Map<String, Object> map) {
        //验证用户状态
        PayShopCustomer shopCustomer=new PayShopCustomer();
        if(map.get("customerId")!=null){
           shopCustomer = shopCustomerServiceFacade.getCustomerById(map.get("customerId").toString());
           if(shopCustomer==null){
               return setResult("10001", "客户信息异常");
           }
           if(shopCustomer.getStatus()==0){
               return setResult("10002", "该账户状态异常");
           }
        }
        if (map.get("orderNo")!=null){
            return setResult("10003", "订单号不能为空");
        }
        if (map.get("money")!=null){
            return setResult("10004", "金额不能为空");
        }
        if (map.get("source")!=null){
            return setResult("10004", "来源不能为空");
        }
        //用户可用券列表
        GetCouponRemainResponse userCouponList = shopCouponRemainServiceFacade.getCouponRemainByUidForInterface(map.get("customerId").toString());
        if ( userCouponList == null || userCouponList.getCount() == 0) {
            return setResult("10005", "剩余豆不足");
        }
        //正常用户总豆数，应该等于总券的都输，除非bug
        if (new BigDecimal(map.get("money").toString()).compareTo(shopCustomer.getDou())>0
                ||new BigDecimal(map.get("money").toString()).compareTo(userCouponList.getDouTotal())>0){
            return setResult("10005", "剩余豆不足");
        }
       return setResult("10000", "success");
    }

    private Map<String, Object> setResult( String s, String msg) {
        Map<String, Object> result =new HashMap<>();
        result.put("code", s);
        result.put("msg", msg);
        return result;
    }
}
