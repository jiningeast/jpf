package com.joiest.jpf.market.api.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.CreateOrderInterfaceRequest;
import com.joiest.jpf.dto.GetUserCouponActiveInterfaceResponse;
import com.joiest.jpf.entity.PayShopOrderInterfaceInfo;
import com.joiest.jpf.entity.ShopProductInterfaceInfo;
import com.joiest.jpf.facade.ShopCouponActiveInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopOrderInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopProductInterfaceServiceFacade;
import com.joiest.jpf.market.api.util.ToolsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
@RequestMapping("orders")
public class OrdersController {

    /**
     * 商品
     */
    @Autowired
    ShopProductInterfaceServiceFacade shopProductInterfaceServiceFacade;

    /**
     * 订单
     */
    @Autowired
    ShopOrderInterfaceServiceFacade shopOrderInterfaceServiceFacade;

    @Autowired
    ShopCouponActiveInterfaceServiceFacade shopCouponActiveInterfaceServiceFacade;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String createOrder(String data)
    {
        if ( StringUtils.isBlank(data) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
        CreateOrderInterfaceRequest request = new CreateOrderInterfaceRequest();
        try{
            request =  (CreateOrderInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未登录", null);
        }
        String chargeNo = "";
        //油卡充值
        if ( request.getOtype().equals("1") || request.getOtype().equals("2") )
        {
            if ( !request.getCardno().equals(request.getCardNo()) )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "油卡卡号不一致");
            }
            //TODO 油卡卡号校验

            chargeNo = request.getCardNo();
        } else if ( request.getOtype().equals("3") )
        {
            //话费充值
            String reg_phone = "^((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0,0-9])|(19[1|8|9])|(16[6]))\\d{8}$";
            Boolean phoneIsTrue = Pattern.compile(reg_phone).matcher(request.getPhone()).matches();
            if ( !phoneIsTrue )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码错误");
            }
            Boolean mobileIsTrue =  Pattern.compile(reg_phone).matcher(request.getMobile()).matches();
            if ( !mobileIsTrue )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码错误");
            }
            if ( !request.getPhone().equals(request.getMobile()) )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码不一致");
            }

            chargeNo = request.getPhone();
        }

        ValidatorUtils.validateInterface(request);
        //获取商品信息
        ShopProductInterfaceInfo productInfo = shopProductInterfaceServiceFacade.getShopProduct(request.getPid());
        if ( productInfo == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MK_PRODUCT_NOFOUND.getCode(), JpfInterfaceErrorInfo.MK_PRODUCT_NOFOUND.getDesc(), "");
        }

        if ( !request.getMoney().equals(request.getPaymoney()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MK_ORDERMONEY_DIFF.getCode(), JpfInterfaceErrorInfo.MK_ORDERMONEY_DIFF.getDesc(), "");
        }

        //创建订单
        String orderno = ToolsUtils.createOrderid();
        PayShopOrderInterfaceInfo info = new PayShopOrderInterfaceInfo();

        info.setOrderNo(orderno);
        info.setCustomerId("2");    //TODO 获取用户信息
        info.setCustomerName("测试客户");
        info.setProductId(productInfo.getId());
        info.setProductName(productInfo.getName());
        info.setProductMoney(productInfo.getMoney());
        info.setProductDou(productInfo.getDou());
        info.setProductInfoId(productInfo.getProductInfoId());
        info.setAmount(1);
        info.setTotalMoney(productInfo.getMoney());
        info.setTotalDou(productInfo.getDou());
        info.setChargeNo(chargeNo);
        info.setAddtime(new Date());
        int res = shopOrderInterfaceServiceFacade.addOrder(info);
        if ( res >= 0 )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), orderno);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), JpfInterfaceErrorInfo.FAIL.getDesc(), null);
    }

    /**
     * 支付
     * @param data
     * @return
     */
    @RequestMapping("/pay")
    @ResponseBody
    public String dopay(String data)
    {
        //1.金额校验 2.订单用户校验 3.用户券列表 4.扣除相应的券 5.更新code


        return "";
    }

    @RequestMapping("blance")
    @ResponseBody
    public String getUserBlance(String data)
    {
        GetUserCouponActiveInterfaceResponse response = shopCouponActiveInterfaceServiceFacade.getUserCouponList("1");
        if ( response == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), "");
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response.getDouTotal());
    }
}