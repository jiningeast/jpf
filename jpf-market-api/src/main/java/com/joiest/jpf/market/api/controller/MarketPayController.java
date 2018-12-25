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
import com.joiest.jpf.dto.GetCouponRemainResponse;
import com.joiest.jpf.facade.ShopCouponRemainServiceFacade;
import com.joiest.jpf.facade.ShopCustomerServiceFacade;
import com.joiest.jpf.market.api.util.AesShopUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiLabelUI;
import java.text.ParseException;
import java.math.BigDecimal;
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
    private static final Logger logger = LogManager.getLogger(MarketPayController.class);
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
        Map<String, Object> map = JsonUtils.toCollection(AesShopUtils.AES_Decrypt(payParam,ConfigUtil.getValue("XinShop_AES_KEY")), new TypeReference<Map<String, Object>>() {});
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
            logger.error("付费失败"+e.getMessage(),e);
            resultMap.put("code","10008");
            resultMap.put("msg","fail");
        }
        return AesShopUtils.AES_Encrypt(JsonUtils.toJson(responseMap),ConfigUtil.getValue("XinShop_AES_KEY")) ;
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
