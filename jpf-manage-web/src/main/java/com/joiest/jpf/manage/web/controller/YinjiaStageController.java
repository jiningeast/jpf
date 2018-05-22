package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.AESUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.entity.MerchantInterfaceInfo;
import com.joiest.jpf.entity.OrderCpInterfaceInfo;
import com.joiest.jpf.entity.OrderInterfaceInfo;
import com.joiest.jpf.facade.MerchantInterfaceServiceFacade;
import com.joiest.jpf.facade.MerchantServiceFacade;
import com.joiest.jpf.facade.OrderCpServiceFacade;
import com.joiest.jpf.facade.OrderInterfaceServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import com.joiest.jpf.manage.web.util.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/yinjiastage")
public class YinjiaStageController {

    @Autowired
    private MerchantInterfaceServiceFacade merchantInterfaceServiceFacade;

    @Autowired
    private OrderInterfaceServiceFacade orderInterfaceServiceFacade;

    @Autowired
    private OrderCpServiceFacade orderCpServiceFacade;

    private MerchantInterfaceInfo merchInfo;
    private String token;
    private Long mid; //商户ID

    /**
     * 联银分期 支付
     */
    @RequestMapping(value = "/installpay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public YjResponseDto InstallPay(HttpServletRequest request)
    {
        String orderid = request.getParameter("orderid");
        String signOrderid = request.getParameter("signOrderid");
        String smsCode = request.getParameter("smsCode");
        String mid = request.getParameter("mid");

        if ( StringUtils.isBlank(orderid) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "orderid不能为空");
        }
        if ( StringUtils.isBlank(signOrderid) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "signOrderid不能为空");
        }
        //获取订单信息
        OrderInterfaceInfo orderInfo = orderInterfaceServiceFacade.getOrder(orderid.trim());
        //商户签约信息
        OrderCpInterfaceInfo orderCpInfo = orderCpServiceFacade.getOrderCpByorderid(signOrderid.trim());
        YjResponseDto dtoResponse = new YjResponseDto();
        //验证
        String stage_tmp = "";
        if ( orderInfo != null && orderCpInfo != null )
        {
            YjResponseDto dto = new YjResponseDto();
            dto.setCode(JpfInterfaceErrorInfo.FAIL.getCode());
            dto.setInfo("订单或签约信息有误");
            if ( StringUtils.isBlank(smsCode) )
            {
                dto.setInfo("验证码错误");
                return dto;
            }
            if ( orderInfo.getOrderstatus().equals(1) )
            {
                dto.setInfo("订单已支付");
                return dto;
            }
            if ( !orderCpInfo.getSignstatus().equals("2") )
            {
                dto.setInfo("签约受理中，稍后再试");
                return dto;
            }
            if ( !orderInfo.getMtsid().equals(this.mid) )
            {
                dto.setInfo("订单与商户信息不匹配");
                return dto;
            }
            if ( StringUtils.isBlank(orderInfo.getOrdername()) )
            {
                dto.setInfo("支付方式所属分期有误");
                return dto;
            }
            Map<String,String> ordernameJson = new HashMap<>();
            ordernameJson = JsonUtils.toObject(orderInfo.getOrdername(), Map.class);
            String payType_cn = ordernameJson.get("stageType_cn");
            if ( StringUtils.isBlank(ordernameJson.get("stageType_cn")) )
            {
                dto.setInfo("未获取到订单分期信息，请重试");
                return dto;
            }
            Pattern pattern = Pattern.compile("^\\d");
            Matcher matcher = pattern.matcher(ordernameJson.get("stageType_cn"));

            if ( matcher.find() )
            {
                stage_tmp = matcher.group(0);
            } else
            {
                dto.setInfo("未获取到订单分期信息，请重试");
                return dto;
            }
        }
        String ip = ServletUtils.getIpAddr(request);
        System.out.println("ip : =======" + ip);
        // 获取IP
        String IP = ServletUtils.getIpAddr(request);

//        String stage = Integer.valueOf(stage_tmp) < 10 ? "0"+stage_tmp : stage_tmp;
//        Map<String,String> mapParam = new HashMap<>();
//        mapParam.put("outOrderNo", orderid);
//        mapParam.put("smsCode", smsCode);
//        mapParam.put("backUrl", ManageConstants.CHINAPAY_PAYBACKURL);
//        mapParam.put("numberOfInstallments", stage);
//
        return dtoResponse;
    }

//    public void

    @ModelAttribute
    public void getMerInfo(HttpServletRequest request)
    {
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "TOKEN不能为空");
        }
        String mid = AESUtils.decrypt(token, ManageConstants.SKEY);

        if ( !Pattern.matches("^\\d+$", mid) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.NOTlOGIN.getCode(), "请先登录");
        }
        this.mid = Long.valueOf(mid);
        //商户信息
        this.merchInfo = merchantInterfaceServiceFacade.getMerchant(this.mid);
    }


}
