package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayShopProductInfo;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.dto.ShopProductInfoResponse;
import com.joiest.jpf.facade.ShopProductInfoServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 商品旅游服务Controller
 * @author admin 
 */
@Controller
@RequestMapping("travel")
public class TravelController {

    @Autowired
    private ShopProductInfoServiceFacade shopAdInterfaceServiceFacade;

    /**
     * 获取旅游商品服务列表
     * @return
     */
    @RequestMapping(value = "/proInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String proInfo() {
        String decoderPayShopSupplierId = ConfigUtil.getValue("travel_supplier_id");
        System.out.println("获取的供应商Id为：" + decoderPayShopSupplierId);
        List<PayShopProductInfo> shopProductInfoList = shopAdInterfaceServiceFacade.getProductInfoList(decoderPayShopSupplierId);
        ShopProductInfoResponse response = new ShopProductInfoResponse();
        List<ShopProductInfoResponse> responseList = new ArrayList<>();
        for(PayShopProductInfo payShopProductInfo:shopProductInfoList){
            response.setImgUrl(payShopProductInfo.getImgurl());
            response.setTitle(payShopProductInfo.getTitle());
            response.setMoneyscope(payShopProductInfo.getMoneyscope());
            responseList.add(response);
        }
        int shopProductInfoCount = shopAdInterfaceServiceFacade.getProductInfoCount(decoderPayShopSupplierId);
        return toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),responseList,shopProductInfoCount);
    }

    /**
     * 将异常及数据以json并base64的方式返回
     * @param code 异常状态码
     * @param info 异常信息
     * @param data 返回数据
     * @param count (如果data是个集合,这个表示集合中元素的条数)
     * @return base64加密串
     */
    private static String toJsonBase64(String code, String info, Object data ,int count){
        Map<String,Object> responseMap = new HashMap<>(4);
        responseMap.put("code",code );
        responseMap.put("info",info);
        if(data != null){
            responseMap.put("data",data);
        }
        responseMap.put("count",count);
        String jsonStr = JsonUtils.toJson(responseMap);
        String base64Str = Base64CustomUtils.base64Encoder(jsonStr);
        base64Str = base64Str.replaceAll("\r","").replaceAll("\n","");
        return base64Str;
    }
}
