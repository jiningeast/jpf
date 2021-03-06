package com.joiest.jpf.market.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayShopCouponActive;
import com.joiest.jpf.common.po.PayShopCouponRemain;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.CouponNoList;
import com.joiest.jpf.entity.PayCouponInfo;
import com.joiest.jpf.entity.ShopRefundInfo;
import com.joiest.jpf.facade.ShopCouponActiveServiceFacade;
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
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
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
    @Autowired
    private ShopCouponActiveServiceFacade shopCouponActiveServiceFacade;


    @RequestMapping(value = "pay",method = RequestMethod.POST)
    @ResponseBody
    public  String  pay(HttpServletRequest request){
        logger.info("进入支付程序了");
        String payParam = request.getParameter("payParam");
        if(StringUtils.isBlank(payParam)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),"参数不能为空",null);
        }
        String ret="";
        Map<String, Object> map = JsonUtils.toCollection(AesShopUtils.AES_Decrypt(ConfigUtil.getValue("XinShop_AES_KEY"),payParam), new TypeReference<Map<String, Object>>() {});
        //验证商户信息,并且验证商户金额是否够
        Map<String,Object> responseMap = checkPayInfo(map);
        if(!"10000".equals(responseMap.get("code").toString())){
            return AesShopUtils.AES_Encrypt(ConfigUtil.getValue("XinShop_AES_KEY"),urlEncoder(JsonUtils.toJson(responseMap))) ;
        }
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = shopCustomerServiceFacade.pay(map);
            responseMap.put("code","10000");
            responseMap.put("msg","success");
            responseMap.put("data",resultMap);
        } catch (Exception e) {
            logger.error("付费失败"+e.getMessage(),e);
            responseMap.put("code","10008");
            responseMap.put("msg","fail");
        }
        logger.info("支付程序完成");
        return AesShopUtils.AES_Encrypt(ConfigUtil.getValue("XinShop_AES_KEY"),urlEncoder(JsonUtils.toJson(responseMap))) ;
    }


    @RequestMapping(value = "refund",method = RequestMethod.POST)
    @ResponseBody
    public String refund(HttpServletRequest request){
        Map<String,Object> resMap = new HashMap<>();

        String responseMap = request.getParameter("payParam");

        if(StringUtils.isBlank(responseMap)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),"参数不能为空",null);
        }

        ShopRefundInfo shopRefundInfo = JsonUtils.toObject(AesShopUtils.AES_Decrypt(responseMap,ConfigUtil.getValue("XinShop_AES_KEY")), ShopRefundInfo.class);
        Map<String,Object> map = checkRefundInfo(shopRefundInfo);

        if (!"10000".equals(map.get("code"))){
            return AesShopUtils.AES_Encrypt(ConfigUtil.getValue("XinShop_AES_KEY"),urlEncoder(JsonUtils.toJson(map)));
        }

        try {
           if (shopCouponRemainServiceFacade.refundByShopRefundInfo(shopRefundInfo)){
               resMap.put("key", "10000");
               resMap.put("msg", "退款成功");
           }
        }catch (Exception e){
            logger.error("退款失败"+e.getMessage(),e);
            resMap.put("key", "10001");
            resMap.put("msg", "退款失败");
        }

        return AesShopUtils.AES_Encrypt(ConfigUtil.getValue("XinShop_AES_KEY"),urlEncoder(JsonUtils.toJson(responseMap)));
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
        if (map.get("orderNo")==null){
            return setResult("10003", "订单号不能为空");
        }else{
            //判断订单号是否已经支付成功，防止二次支付
            PayShopCouponActive payShopCouponActive = new PayShopCouponActive();
            payShopCouponActive.setOrderNo(map.get("orderNo").toString());
            payShopCouponActive.setType("1");
            payShopCouponActive.setSource("1");
            List<PayShopCouponActive> payShopCouponActives  = shopCouponActiveServiceFacade.getCouponActive(payShopCouponActive);
            if(payShopCouponActives!=null&&payShopCouponActives.size()!=0){
                return setResult("10007", "订单已经支付成功，不能重复支付");
            }
        }
        if (map.get("money")==null){
            return setResult("10004", "金额不能为空");
        }
        if (map.get("source")==null){
            return setResult("10005", "来源不能为空");
        }
        //用户可用券列表
        GetCouponRemainResponse userCouponList = shopCouponRemainServiceFacade.getCouponRemainByUidForInterface(map.get("customerId").toString());
        if ( userCouponList == null || userCouponList.getCount() == 0) {
            return setResult("10006", "剩余豆不足");
        }
        //正常用户总豆数，应该等于总券的都输，除非bug
        if (new BigDecimal(map.get("money").toString()).compareTo(shopCustomer.getDou())>0
                ||new BigDecimal(map.get("money").toString()).compareTo(userCouponList.getDouTotal())>0){
            return setResult("10006", "剩余豆不足");
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

        if (StringUtils.isBlank(shopRefundInfo.getCustomerId())){
            return setResult("10001", "用户id不能为空");
        }else{
            PayShopCustomer shopCustomer = shopCustomerServiceFacade.getCustomerById(shopRefundInfo.getCustomerId());
            if (shopCustomer == null){
                return setResult("10002", "用户不存在");
            }
        }

        if (shopRefundInfo.getOrderNo() == null){
            return setResult("10003", "订单号不能为空");
        }else{
            //判断订单号是否已经支付成功，防止二次支付
            PayShopCouponActive payShopCouponActive = new PayShopCouponActive();
            payShopCouponActive.setOrderNo(shopRefundInfo.getOrderNo());
            payShopCouponActive.setType("2");
            payShopCouponActive.setSource("1");
            List<PayShopCouponActive> payShopCouponActives  = shopCouponActiveServiceFacade.getCouponActive(payShopCouponActive);
            if(payShopCouponActives!=null&&payShopCouponActives.size()!=0){
                return setResult("10004", "流水已存在,不能重复添加");
            }
        }

        if ("0".equals(shopRefundInfo.getTotalSaleDouYes()) && "0".equals(shopRefundInfo.getTotalSaleDouNo())){
            return setResult("10005", "欣豆信息不能都为空");
        }

        return setResult("10000", "success");
    }


    /**
     * 根据订单号查询是否扣款
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getByOrderNo",method =RequestMethod.POST)
    public String getByOrderNo(HttpServletRequest request){
        String orderNo = request.getParameter("orderNo");
        if(StringUtils.isBlank(orderNo)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),"参数不能为空",null);
        }
        Map<String,Object> responseMap = shopCustomerServiceFacade.getByOrderNo(urlDncoder(AesShopUtils.AES_Decrypt(ConfigUtil.getValue("XinShop_AES_KEY"),orderNo)));
        return  AesShopUtils.AES_Encrypt(ConfigUtil.getValue("XinShop_AES_KEY"),urlEncoder(JsonUtils.toJson(responseMap))) ;
    }


    /**
     * 加密
     * @param str
     * @return
     */
    public  String  urlEncoder (String str){
        String ret="";
        try {
            ret = URLEncoder.encode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            ret="加密异常";
        }
        return ret;
    }   /**
     * 加密
     * @param str
     * @return
     */
    public  String  urlDncoder (String str){
        String ret="";
        try {
            ret = URLEncoder.encode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            ret="解密异常";
        }
        return ret;
    }
}
