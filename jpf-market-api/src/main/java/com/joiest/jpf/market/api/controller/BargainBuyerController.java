package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bargainBuyer")
public class BargainBuyerController {

    private String uid;

    private String openId;

    private ShopCustomerInterfaceInfo userInfo;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @RequestMapping("/becomeBuyer")
    @ResponseBody
    public String becomeBuyer(){
        if ( userInfo.getIsBargainBuyer() == 1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"您已经是买家了",null);
        }
        userInfo.setIsBargainBuyer((byte)1);
        userInfo.setUpdatetime(new Date());
        int customerRes = shopCustomerInterfaceServiceFacade.updateCustomerById(userInfo);
        if ( customerRes > 0 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"操作成功",null);
        }else{
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"操作失败",null);
        }
    }

    @RequestMapping("/startBuyDou")
    @ResponseBody
    public String startBuyDou(@NotNull(message = "折扣率不能为空") String offRate, @NotNull(message = "最低限额不能为空") String minDou){


        return "";
    }

    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {
        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            List<ShopCustomerInterfaceInfo> info = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId);
            if( info != null && !info.isEmpty()  ){
                userInfo = info.get(0);
                uid = userInfo.getId();
            }
        }
    }
}
