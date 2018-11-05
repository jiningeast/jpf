package com.joiest.jpf.charge.manage.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.ClassUtil;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.ChargeProductInterfaceRequest;
import com.joiest.jpf.dto.GetChargeProductResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeProductInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ChargeProductServiceFacade;
import com.joiest.jpf.facade.RedisCustomServiceFacade;
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
@RequestMapping("/chargeProduct")
public class ChargeProductController {

    @Autowired
    private ChargeProductServiceFacade chargeProductServiceFacade;

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    private ChargeCompanyInfo chargeCompanyInfo;

    private String merchNo;

    /*
     * 商品列表页
     * */
    @RequestMapping(value = "/plist", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String plist(String data)
    {
        if ( org.apache.commons.lang3.StringUtils.isBlank(data) || data==null  )
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

        ChargeProductInterfaceRequest  request = new ChargeProductInterfaceRequest();
        try{
            request =  (ChargeProductInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误！", null);
        }

        GetChargeProductResponse response = chargeProductServiceFacade.getProductListInterface(request);
        if( response == null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "没有更多了", null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response);
    }

    /*
     * 商品详情页
     * */
    @RequestMapping(value = "/pView", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String pView(String data){

        if ( org.apache.commons.lang3.StringUtils.isBlank(data) || data==null  )
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
        String pNum=(String) requestMap.get("pNum");
        if(StringUtils.isBlank(pNum)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "产品编号不能为空", null);
        }
        //查询商品基本信息
        ChargeProductInfo pinfo=chargeProductServiceFacade.getChargeProduct(pNum);
        if(pinfo==null){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "产品信息不存在", null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), pinfo);
    }
    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {
        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("MANAGE_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByMerchNo(openId_encrypt);
            merchNo = chargeCompanyInfo.getMerchNo();
        }
    }
}
