package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.facade.ShopCouponMoneyTypeServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/couponMoneyTypeController")
public class CouponMoneyTypeController {

    private static final Logger logger = LogManager.getLogger(CouponMoneyTypeController.class);

    @Autowired
    private ShopCouponMoneyTypeServiceFacade shopCouponMoneyTypeServiceFacade;

    /**
     * 查询面值的接口
     * @return
     */
    @RequestMapping(value="/getCouponMoneyList",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getCouponMoneyList(){
        List<Map<String,Object>> data =new ArrayList<>();
        try{
           data = shopCouponMoneyTypeServiceFacade.getMoneyToMap();
        }catch (Exception e){
            logger.error("查询面值的接口失败"+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),data);
    }
}
