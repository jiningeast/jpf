package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetShopProductRequest;
import com.joiest.jpf.dto.GetShopProductResponse;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopProductInfoServiceFacade;
import com.joiest.jpf.facade.ShopProductServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("exchange")
public class exchangeController {

    private String uid;
    private String openId;
    private ShopCustomerInterfaceInfo userInfo;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private ShopProductInfoServiceFacade shopProductInfoServiceFacade;

    @Autowired
    private ShopProductServiceFacade shopProductServiceFacade;

    /**
     * 去兑换页
     * */
    @RequestMapping(value = "/goExchange", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String goExchange(String data)
    {
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);

        JSONObject response = JSONObject.fromObject(requestStr);

        if( response != null && response.containsKey("productInfoId") ){
            String productInfoId = response.get("productInfoId").toString();
            if( StringUtils.isBlank(productInfoId) ){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "数据非法", null);
            }
            GetShopProductRequest productRequest = new GetShopProductRequest();
            productRequest.setProductInfoId(productInfoId);
            GetShopProductResponse productResponse = shopProductServiceFacade.getList(productRequest);
            if( productResponse == null ){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "数据为空", null);
            }
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), productResponse);
        }else{
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }

    }

    /**
     * 确认兑换页
     * */
    @RequestMapping(value = "/pay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String pay(String data)
    {
        return "";
    }

    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(openId_encrypt)) {
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            userInfo = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId).get(0);
            uid = userInfo.getId();
        }
    }

}
