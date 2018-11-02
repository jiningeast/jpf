package com.joiest.jpf.charge.manage.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.ClassUtil;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.ChargeOrderInterfaceRequest;
import com.joiest.jpf.dto.GetChargeOrderResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
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
@RequestMapping("chargeOrder")
public class ChargeOrderController {

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    private ChargeCompanyInfo chargeCompanyInfo;

    private String merchNo;

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;

    /*
     * 订单列表页
     * */

    @RequestMapping(value = "/orderList", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String orderList(String data){

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

        ChargeOrderInterfaceRequest request = new ChargeOrderInterfaceRequest();
        try{
            request =  (ChargeOrderInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误！", null);
        }
        request.setMerchNo(merchNo);
        GetChargeOrderResponse response = chargeOrderServiceFacade.getRecordsInterface(request);
        if( response == null|| response.getList().size()<1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "没有更多了", null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response);
    }

    /*
     * 订单详情
     * */
    @RequestMapping( value = "/orderView", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String orderView(String data){

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
        String orderNum=(String) requestMap.get("orderNum");
        if(StringUtils.isBlank(orderNum)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单号不能为空", null);
        }
        PayChargeOrder order=new PayChargeOrder();
        order.setOrderNo(orderNum);
        order.setMerchNo(merchNo);
        //查询商品基本信息
        ChargeOrderInfo pinfo=chargeOrderServiceFacade.getOne(order);

        if(pinfo==null){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单信息不存在", null);
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
