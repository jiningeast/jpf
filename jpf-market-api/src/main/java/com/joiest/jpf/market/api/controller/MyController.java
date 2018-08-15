package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopOrderInterfaceServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/my")
public class MyController {

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private ShopOrderInterfaceServiceFacade shopOrderInterfaceServiceFacade;

    private String uid;

    private String openId;

    private ShopCustomerInterfaceInfo userInfo;

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        int count = shopOrderInterfaceServiceFacade.getOrdersCount(uid);

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("phone",userInfo.getPhone());
        responseMap.put("dou",userInfo.getDou());
        responseMap.put("avatar",userInfo.getAvatar());
        responseMap.put("ordersCount",count);
        responseMap.put("customerServicePhone","400-000-0000");
        responseMap.put("complainEmail","service@xinxiangfuwu.com");
        responseMap.put("servicePeriod","周一至周日 9:00-18:00");

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), responseMap);
    }

    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {
        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(com.joiest.jpf.market.api.controller.ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(openId_encrypt)) {
            openId = AESUtils.decrypt(openId_encrypt, com.joiest.jpf.market.api.controller.ConfigUtil.getValue("AES_KEY"));
            userInfo = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId).get(0);
            uid = userInfo.getId();
        }
    }
}
