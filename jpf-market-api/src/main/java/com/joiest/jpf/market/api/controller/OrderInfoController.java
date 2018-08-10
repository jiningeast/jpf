package com.joiest.jpf.market.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.ShopCouponActiveInterfaceInfo;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.entity.ShopOrderInfoInterface;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopCouponActiveInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopOrderInfoInterfaceServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("orderInfo")
public class OrderInfoController {

    private String uid;

    private ShopCustomerInterfaceInfo userInfo;

    @Autowired
    private ShopOrderInfoInterfaceServiceFacade shopOrderInfoInterfaceServiceFacade;

    @Autowired
    private ShopCouponActiveInterfaceServiceFacade shopCouponActiveInterfaceServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    /*
    * 订单列表页
    * */
    @RequestMapping(value = "/index", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String index(String data)
    {
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
        ShopOrderInfoInterfaceRequest request = new ShopOrderInfoInterfaceRequest();
        try{
            request =  (ShopOrderInfoInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未登录", null);
        }
        request.setUid(uid); // 用户登录ID

        ShopOrderInfoInterfaceResponse response = shopOrderInfoInterfaceServiceFacade.getList(request);
        if( response == null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "未匹配到相关数据", null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response);
    }

    /*
     * 订单详情页
     * */
    @RequestMapping(value = "/view", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String view(String data){
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
        ShopOrderInfoInterfaceRequest request = new ShopOrderInfoInterfaceRequest();
        try{
            request =  (ShopOrderInfoInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未登录", null);
        }

        if ( StringUtils.isBlank(request.getOrderNo()))
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单编号不能为空", null);
        }
        request.setUid(uid); // 用户登录ID
        ShopOrderInfoInterface response = shopOrderInfoInterfaceServiceFacade.getOne(request);
        if( response == null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "未匹配到相关数据", null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response);
    }

    /*
     * 欣豆收支明细
     * */
    @RequestMapping(value = "/recordDetail", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String recordDetail(String data){
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
        GetShopCouponActiveInterfaceRequest request = new GetShopCouponActiveInterfaceRequest();
        try{
            request =  (GetShopCouponActiveInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未登录", null);
        }
        request.setUid(uid); // 用户登录ID
        request.setPageSize("10");// 默认每页显示条数

        GetUserCouponActiveInterfaceResponse response = shopCouponActiveInterfaceServiceFacade.getUserCouponActiveList(request);
        if( response == null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "未匹配到相关数据", null);
        }
        response.setDouTotal(userInfo.getDou()); //获取用户当前豆数量
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response);
    }

    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {
        //获取用户信息 Token
        String token = request.getHeader("Token");
        String uid_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("MARKET_USER_LOGIN_KEY") + token);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(uid_encrypt))
        {
            uid = AESUtils.decrypt(uid_encrypt, ConfigUtil.getValue("AES_KEY_MARKET"));
            userInfo = shopCustomerInterfaceServiceFacade.getCustomer(uid);
        }
    }

}
