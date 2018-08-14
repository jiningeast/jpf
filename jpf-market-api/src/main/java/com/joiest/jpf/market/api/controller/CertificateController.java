package com.joiest.jpf.market.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.GetUserCouponActiveInterfaceResponse;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
import com.joiest.jpf.facade.ShopBatchCouponInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopCouponActiveInterfaceServiceFacade;
import com.joiest.jpf.facade.ShopCustomerInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("certificate")
public class CertificateController {

    private String uid;
    private String openId;
    private ShopCustomerInterfaceInfo userInfo;

    @Autowired
    private ShopBatchCouponInterfaceServiceFacade shopBatchCouponInterfaceServiceFacade;

    @Autowired
    private ShopCouponActiveInterfaceServiceFacade shopCouponActiveInterfaceServiceFacade;


    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;
    /**
     * 欣券激活冲豆操作
     */
    @RequestMapping(value = "/activation",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
   public String activation(String data)
   {

       //判断当前用户是否锁定
       if(userInfo.getStatus()==0){

           return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "您已经被冻结请联系客服", null);

       }

       if ( StringUtils.isBlank(data) || data==null  )
       {
           return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
       }

       String code= null;
       try {
           String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
           String requestStr = Base64CustomUtils.base64Decoder(dataStr);
           Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
           code = (String) requestMap.get("code");
           if(code==null){
               return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
           }
       } catch (Exception e) {
           return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
       }

       //查询当前激活码是否存在
       int infos = shopBatchCouponInterfaceServiceFacade.getCouponByCouponNo(code.trim(),uid);

        if(infos==1){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "激活成功", null);

        }else if(infos==2){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "此激活码已经使用", null);

        } else if ( infos==3 ){

           return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "激活码错误", null);

       }else if (infos==4){

           return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "批次信息有误", null);

       }else if(infos==5){

           return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "用户信息有误", null);

       }else if(infos==6){

           return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "金额校验失败", null);

       }else if(infos==7){

           return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "激活失败", null);

       }
       //存在此激活码做激活处理
       //根据token查处当前用户的信息
       return "";

   }

    /**
     * 已激活欣券兑换码
     */
    @RequestMapping(value = "/couponlist",method=RequestMethod.POST,produces = "application/json;charset=utf8")
    @ResponseBody
    public String couponlist(String data){


        if ( StringUtils.isBlank(data) || data==null  )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }

        Map<String,Object> requestMap = null;
        try {
            String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
            String requestStr = Base64CustomUtils.base64Decoder(dataStr);
            requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
            if(requestMap==null){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
            }
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }

        String pageNo= (String) requestMap.get("pageNo");
        String pageSize= (String) requestMap.get("pageSize");

        GetUserCouponActiveInterfaceResponse response = shopCouponActiveInterfaceServiceFacade.getUserCouponListAndPage(uid,pageNo,pageSize);

        if(response==null){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(), "未匹配到相关数据", null);

        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "请求数据成功", response.getList());

    }

    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {
        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            userInfo = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId).get(0);
            uid = userInfo.getId();
        }
    }
   }
