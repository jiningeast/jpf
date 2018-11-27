package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayShopCouponMoneyType;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.facade.ShopCouponMoneyTypeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/market-manager/couponMoneyTypeController")
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

    /**
     * 自定义接口
     * @param request
     * @return
     */
    @RequestMapping(value="customMoneyAdd",method = RequestMethod.POST)
    @ResponseBody
    public String customMoneyAdd(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        try {
            String money = request.getParameter("money");
            if(StringUtils.isBlank(money)){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
            }
            //验证数据库是否存在该金额
            PayShopCouponMoneyType selectMoney = new PayShopCouponMoneyType();
            selectMoney.setMoney(new BigDecimal(Base64CustomUtils.base64Decoder(money)));
            List<PayShopCouponMoneyType> byMoney = shopCouponMoneyTypeServiceFacade.getByMoney(selectMoney);
            //如果存在直接返回数据库中的值，不在自定义
            if(byMoney!=null&&byMoney.size()!=0){
                map.put("id",byMoney.get(0).getId());
                map.put("money",Base64CustomUtils.base64Decoder(money));
            }else{
                PayShopCouponMoneyType payShopCouponMoneyType = new PayShopCouponMoneyType();
                payShopCouponMoneyType.setMoney(new BigDecimal(Base64CustomUtils.base64Decoder(money)));
                payShopCouponMoneyType.setStatus((byte)2);
                String id =shopCouponMoneyTypeServiceFacade.addAndGetId(payShopCouponMoneyType);
                map.put("id",id);
                map.put("money",Base64CustomUtils.base64Decoder(money));
            }
        } catch (Exception e) {
            logger.error("自定义面值出错:"+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),map);
    }

    /*public static void main(String[] args) {
        System.out.println(Md5Encrypt.md5("11"+"1000000"+"imyHcZOzMmhukCqB","UTF-8"));
    }*/
}
