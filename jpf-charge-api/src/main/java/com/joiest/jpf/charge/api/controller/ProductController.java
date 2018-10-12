package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import com.joiest.jpf.facade.ChargeProductServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private ChargeProductServiceFacade chargeProductServiceFacade;

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;


    /**
     *商品列表
     * merchNo  商户号
     * dateTime 时间戳
     * carrier  运营商
     * sign     签名
     *
    * */
    @RequestMapping(value = "/flowProduct",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String flowProduct(HttpServletRequest request, HttpServletResponse response){

        //商户号
        String merchNo = request.getParameter("merchNo");
        //时间戳
        String dateTime = request.getParameter("dateTime");
        //运营商类型 1=移动 2=联通 3=电信 为空返回所有运营商的套餐
        int carrier = Integer.parseInt(request.getParameter("carrier"));
        //签名串
        String sign = request.getParameter("sign");

        //参数不合法
        if(StringUtils.isBlank(merchNo) || StringUtils.isBlank(dateTime)  ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc(), null);
        }
        if( carrier < 0 || carrier > 3 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc(), null);
        }

        //缺少签名参数
        if( sign== null || StringUtils.isBlank(sign)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NO_SIGN.getCode(), JpfInterfaceErrorInfo.NO_SIGN.getDesc(), null);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("merchNo",merchNo);
        map.put("dateTime",dateTime);
        map.put("carrier",carrier);

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);

        //商户密码
        ChargeCompanyInfo record = new ChargeCompanyInfo();
        record.setMerchNo(merchNo);
        ChargeCompanyInfo result = chargeCompanyServiceFacade.getOne(record);
        //商户不存在
        if(result==null || result.getIsDel() == 1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(), JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc(), null);
        }
        //商户删除 或者  商户关闭服务
        if( result.getIsFreeze() == 1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_COUPON_NOTBIND.getCode(), JpfInterfaceErrorInfo.USER_COUPON_NOTBIND.getDesc(), null);
        }
        String  privateKey = result.getPrivateKey();

        //校验来源数据是否合法
        String selfSign = Md5Encrypt.md5(respos+privateKey).toUpperCase();
        if(!selfSign.equals(sign)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode(), JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc(), null);
        }

        //查询商品列表
        PayChargeProduct chargeProduct = new PayChargeProduct();
        chargeProduct.setMobileType((byte)carrier);
        List<ChargeProductInfo> list = chargeProductServiceFacade.getList(chargeProduct);
        if( list==null ||  list.isEmpty() ){
            return  ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), JpfInterfaceErrorInfo.FAIL.getDesc(), list);
        }
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求参数："+request.getQueryString());
        sbf.append("\nsign："+sign);
        String fileName = "FlowProduct";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-charge-api/log/", fileName,true);

        return  ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), list);
    }

    /**
     * 订单查询列表
     * merchNo  商户号
     * outOrderNo  订单号
     * orderNo     平台订单号
     * sign     签名
     * */
    @RequestMapping(value = "/search",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String search(HttpServletRequest request, HttpServletResponse response){

        //商户号
        String merchNo = request.getParameter("merchNo");

        //订单号
        String outOrderNo = request.getParameter("outOrderNo");

        //平台单号
        String orderNo = request.getParameter("orderNo");

        //签名串
        String sign = request.getParameter("sign");

        //参数不合法
        if(StringUtils.isBlank(merchNo) || ( StringUtils.isBlank(outOrderNo) && StringUtils.isBlank(orderNo) ) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc(), null);
        }

        //缺少签名参数
        if( sign== null || StringUtils.isBlank(sign)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NO_SIGN.getCode(), JpfInterfaceErrorInfo.NO_SIGN.getDesc(), null);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("merchNo",merchNo);
        if( StringUtils.isNotBlank(outOrderNo) ){
            map.put("outOrderNo",outOrderNo);
        }
        if( StringUtils.isNotBlank(orderNo) ){
            map.put("orderNo",orderNo);
        }

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);

        //商户密码
        ChargeCompanyInfo record = new ChargeCompanyInfo();
        record.setMerchNo(merchNo);
        ChargeCompanyInfo result = chargeCompanyServiceFacade.getOne(record);
        //商户不存在
        if(result==null || result.getIsDel() == 1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(), JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc(), null);
        }
        //商户删除 或者  商户关闭服务
        if( result.getIsFreeze() == 1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_COUPON_NOTBIND.getCode(), JpfInterfaceErrorInfo.USER_COUPON_NOTBIND.getDesc(), null);
        }
        String  privateKey = result.getPrivateKey();

        //校验来源数据是否合法
        String selfSign = Md5Encrypt.md5(respos+privateKey).toUpperCase();
        if(!selfSign.equals(sign)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode(), JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc(), null);
        }

        //查询订单信息
        PayChargeOrder payChargeOrder = new PayChargeOrder();
        payChargeOrder.setMerchNo(merchNo);
        payChargeOrder.setForeignOrderNo(outOrderNo);
        payChargeOrder.setOrderNo(orderNo);
        ChargeOrderInfo orderInfo = chargeOrderServiceFacade.getOne(payChargeOrder);
        if( orderInfo ==null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getCode(), JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getDesc(), null);
        }


        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), orderInfo);
    }

    /**
     * 余额查询
     * merchNo  商户号
     * sign     签名
     * */
    @RequestMapping(value = "/banlance",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String banlance(HttpServletRequest request, HttpServletResponse response){
        //商户号
        String merchNo = request.getParameter("merchNo");

        //签名串
        String sign = request.getParameter("sign");

        //参数不合法
        if(StringUtils.isBlank(merchNo)  ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc(), null);
        }

        //缺少签名参数
        if( sign== null || StringUtils.isBlank(sign)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NO_SIGN.getCode(), JpfInterfaceErrorInfo.NO_SIGN.getDesc(), null);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("merchNo",merchNo);

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);

        String respos = ToolUtils.mapToUrl(treeMap);

        //商户密码
        ChargeCompanyInfo record = new ChargeCompanyInfo();
        record.setMerchNo(merchNo);
        ChargeCompanyInfo result = chargeCompanyServiceFacade.getOne(record);
        //商户不存在
        if(result==null || result.getIsDel() == 1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(), JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc(), null);
        }
        //商户删除 或者  商户关闭服务
        if( result.getIsFreeze() == 1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_COUPON_NOTBIND.getCode(), JpfInterfaceErrorInfo.USER_COUPON_NOTBIND.getDesc(), null);
        }
        String  privateKey = result.getPrivateKey();

        //校验来源数据是否合法
        String selfSign = Md5Encrypt.md5(respos+privateKey).toUpperCase();
        if(!selfSign.equals(sign)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode(), JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc(), null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), result);
    }

}
