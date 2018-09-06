package com.joiest.jpf.market.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.ClassUtil;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetShopAdInterfaceRequest;
import com.joiest.jpf.dto.GetShopAdInterfaceResponse;
import com.joiest.jpf.dto.ShopOrderInfoInterfaceResponse;
import com.joiest.jpf.facade.ShopAdInterfaceServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("shopAd")
public class ShopAdController {


    @Autowired
    private ShopAdInterfaceServiceFacade shopAdInterfaceServiceFacade;


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
        GetShopAdInterfaceRequest request = new GetShopAdInterfaceRequest();
        try{
            request =  (GetShopAdInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "数据异常", null);
        }

        GetShopAdInterfaceResponse response = shopAdInterfaceServiceFacade.getList(request);
        if( response == null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "没有更多了", null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response);
    }



}
