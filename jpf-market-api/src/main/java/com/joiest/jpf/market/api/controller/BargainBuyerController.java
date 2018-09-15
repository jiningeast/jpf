package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayShopBargainRequest;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetShopBargainOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRequestRequest;
import com.joiest.jpf.facade.ShopBargainOrderServiceFacade;
import com.joiest.jpf.facade.ShopBargainRequestServiceFacade;
import com.joiest.jpf.market.api.util.ToolsUtils;
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
import java.util.regex.Pattern;

@Controller
@RequestMapping("/bargainBuyer")
public class BargainBuyerController {

    private String uid;

    private String openId;

    private ShopCustomerInterfaceInfo userInfo;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private ShopBargainRequestServiceFacade shopBargainRequestServiceFacade;

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

    /*
    *  买家发布页面
    * */
    @RequestMapping("/startBuyDou")
    @ResponseBody
    public String startBuyDou(@NotNull(message = "折扣率不能为空") String offRate, @NotNull(message = "最低限额不能为空") String minDou, @NotNull(message = "暂停回收按钮不能为空") String status){


        Boolean RateFlag = ToolsUtils.isInterger(offRate);// 折扣率
        Boolean minDouFlag = ToolsUtils.isInterger(minDou);//豆
        Boolean statusFlag = ToolsUtils.isInterger(status);
        if( !RateFlag  ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"折扣率需填写整数",null);
        }
        if( Integer.parseInt(offRate) <= 0 || Integer.parseInt(offRate) >= 100 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"折扣率需大于0且小于100",null);
        }
        if( !minDouFlag || Integer.valueOf(minDou) <= 0   ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"最低限额需填写整数且大于0",null);
        }
        Byte statusNew = Byte.valueOf( status); //回收状态
        if( !statusFlag || (statusNew !=0 && statusNew !=1)   ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"回收状态值填写错误",null);
        }
        GetShopBargainRequestRequest request = new GetShopBargainRequestRequest();
        request.setCustomerId(uid);
        request.setOffRate(Double.parseDouble(offRate));
        request.setMinDou(Integer.parseInt(minDou));
        request.setStatus(  statusNew );


        //先查询当前用户是否发布
        List<PayShopBargainRequest> infos = shopBargainRequestServiceFacade.getOne(request);
        if( infos!=null && infos.size() > 0  ){
            request.setId(infos.get(0).getId());
        }

        //添加 或更新 买家信息
        JpfResponseDto jpfResponseDto = shopBargainRequestServiceFacade.add(request);
        if( !jpfResponseDto.getRetCode().equals("0000") ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"操作失败",null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"操作成功",null);
    }

    /*
     *  查询买家发布
     * */
    @RequestMapping("/searchBuyDou")
    @ResponseBody
    public String searchBuyDou(){

        GetShopBargainRequestRequest request = new GetShopBargainRequestRequest();
        request.setCustomerId(uid);

        //先查询当前用户发布信息
        List<PayShopBargainRequest> infos = shopBargainRequestServiceFacade.getOne(request);
        if( infos!=null && infos.size() > 0){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"操作成功",infos);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"操作失败",null);
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
