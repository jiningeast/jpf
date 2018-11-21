package com.joiest.jpf.market.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.ClassUtil;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetShopAdInterfaceRequest;
import com.joiest.jpf.dto.GetShopAdInterfaceResponse;
import com.joiest.jpf.facade.ShopAdInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopProductInfoServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("travel")
public class TravelController {


    @Autowired
    private ShopProductInfoServiceFacade shopAdInterfaceServiceFacade;


    /**
     * 服务列表
     * */
    @RequestMapping(value = "/proInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String proInfo(String data)
    {
        return "";
    }


}
