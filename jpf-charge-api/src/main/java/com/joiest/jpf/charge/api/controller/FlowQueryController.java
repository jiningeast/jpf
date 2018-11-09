package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.charge.api.constant.ManageConstants;
import com.joiest.jpf.charge.api.util.SmsUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.entity.ChargeBalanceInfo;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.entity.ChargeProductInfo;
import com.joiest.jpf.facade.ChargeBalanceServiceFacade;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import com.joiest.jpf.facade.ChargeProductServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("flowQuery")
public class FlowQueryController {

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private ChargeProductServiceFacade chargeProductServiceFacade;

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;

    @Autowired
    private ChargeBalanceServiceFacade chargeBalanceServiceFacade;

    /**
     * 自动对账接口_账务明细部分
     * @param request
     * @return
     */
    @RequestMapping(value = "/financeQuery",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String financeQuery(HttpServletRequest request){
        String startTime = request.getParameter("starttime");
        String endTime = request.getParameter("endtime");
        String pageNum = request.getParameter("pagenum");
        String pageSize = request.getParameter("pagesize");
        String paymentType = request.getParameter("paymenttype");

        Map<String,String> mapParam = new HashMap<String, String>();
        mapParam.put("starttime",startTime);
        mapParam.put("endtime",endTime);
        mapParam.put("pagenum",pageNum);
        mapParam.put("pagesize",pageSize);
        mapParam.put("paymenttype",paymentType);

        Map<String,String> responseMap = new OfpayUtils().financequery(mapParam);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\nTime:" + DateUtils.getCurDate());
        stringBuilder.append("\n接口名称：自动对账接口_账务明细部分");
        stringBuilder.append("\n请求地址：" + request.getQueryString());
        if(responseMap.get("retcode").equals("1")){
            stringBuilder.append("\n欧非账务明细查询状态：SUCCESS");
            stringBuilder.append("\n\t接口返回信息："+JSONObject.fromObject(responseMap).toString());
        }else{
            stringBuilder.append("\n欧非账务明细查询状态：ERROR");
            stringBuilder.append("\n\t接口返回信息："+JSONObject.fromObject(responseMap).toString());
        }
        return JsonUtils.toJson(responseMap);
    }

    /**
     *商品列表
     * merchNo  商户号
     * dateTime 时间戳
     * carrier  运营商
     * sign     签名
     *
    * */
    @RequestMapping(value = "/flowProduct",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String flowProduct(HttpServletRequest request, HttpServletResponse response){

        //接口返回参数数据
        Map<String,Object> responseMap = new HashMap<>();

        //商户号
        String merchNo = request.getParameter("merchNo");
        //时间戳
        String dateTime = request.getParameter("dateTime");
        //运营商类型 1=移动 2=联通 3=电信 为空返回所有运营商的套餐
        String carrier = request.getParameter("carrier");
        if ( request.getParameter("carrier") == null ) {
            responseMap.put("code",JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());
            return JsonUtils.toJson(responseMap);
        }

        if( carrier.equals("") || !carrier.equals("null") || ( Integer.parseInt(carrier) >=0 && Integer.parseInt(carrier) <= 5 ) ){
            if(carrier.equals("")){
                carrier = request.getParameter("carrier");
            }else{
                carrier = request.getParameter("carrier");
            }
        }else{
            responseMap.put("code",JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());
            return JsonUtils.toJson(responseMap);
        }

        //签名串
        String sign = request.getParameter("sign");

        //参数不合法
        if(StringUtils.isBlank(merchNo) || StringUtils.isBlank(dateTime)  ){
            responseMap.put("code",JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        //缺少签名参数
        if( sign== null || StringUtils.isBlank(sign)){
            responseMap.put("code",JpfInterfaceErrorInfo.NO_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.NO_SIGN.getDesc());

            return JsonUtils.toJson(responseMap);
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
            responseMap.put("code",JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());

            return JsonUtils.toJson(responseMap);
        }
        //商户删除 或者  商户关闭服务
        if( result.getIsFreeze() == 1 ){
            responseMap.put("code",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getDesc());

            return JsonUtils.toJson(responseMap);
        }
        String  privateKey = result.getPrivateKey();

        //校验来源数据是否合法
        String selfSign = Md5Encrypt.md5(respos+privateKey).toUpperCase();
        if(!selfSign.equals(sign)){
            responseMap.put("code",JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        //查询商品列表
        PayChargeProduct chargeProduct = new PayChargeProduct();
        if( carrier.equals("") ){
            chargeProduct.setMobileType((byte)0);
        }else{
            chargeProduct.setMobileType(Byte.parseByte(carrier));
        }

        List<ChargeProductInfo> list = chargeProductServiceFacade.getList(chargeProduct);
        if( list==null ||  list.isEmpty() ){
            responseMap.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.FAIL.getDesc());

            return JsonUtils.toJson(responseMap);
        }
        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        //sbf.append("\n请求参数："+request.getQueryString());
        sbf.append("\nsign："+sign);
        String fileName = "FlowProduct";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-charge-api/log/", fileName,true);

        //返回指定字段信息J
        List<Map<String, Object>> responData = new ArrayList<Map<String, Object>>();

        for (int i = 0; i <list.size() ; i++) {
            Map<String,Object> tmpMap = new HashMap<>();
            tmpMap.put("productId",list.get(i).getId());
            tmpMap.put("name",list.get(i).getName());
            tmpMap.put("value",list.get(i).getValue());
            tmpMap.put("salePrice",list.get(i).getSalePrice());
            tmpMap.put("productType", ManageConstants.CHARGEPRODUCTTYPE.get(list.get(i).getMobileType().toString()));
            responData.add(tmpMap);
        }

        responseMap.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        responseMap.put("info",JpfInterfaceErrorInfo.SUCCESS.getDesc());
        responseMap.put("data",responData);

        return JsonUtils.toJson(responseMap);
    }

    /**
     * 订单查询列表
     * merchNo  商户号
     * outOrderNo  订单号
     * orderNo     平台订单号
     * sign     签名
     * */
    @RequestMapping(value = "/search",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
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

        //接口返回参数数据
        Map<String,Object> responseMap = new HashMap<>();

        //参数不合法
        if(StringUtils.isBlank(merchNo) || ( StringUtils.isBlank(outOrderNo) && StringUtils.isBlank(orderNo) ) ){
            responseMap.put("code",JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        //缺少签名参数
        if( sign== null || StringUtils.isBlank(sign)){
            responseMap.put("code",JpfInterfaceErrorInfo.NO_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.NO_SIGN.getDesc());

            return JsonUtils.toJson(responseMap);
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
            responseMap.put("code",JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());

            return JsonUtils.toJson(responseMap);
        }
        //商户删除 或者  商户关闭服务
        if( result.getIsFreeze() == 1 ){
            responseMap.put("code",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getDesc());

            return JsonUtils.toJson(responseMap);
        }
        String  privateKey = result.getPrivateKey();

        //校验来源数据是否合法
        String selfSign = Md5Encrypt.md5(respos+privateKey).toUpperCase();
        if(!selfSign.equals(sign)){
            responseMap.put("code",JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        //查询订单信息
        PayChargeOrder payChargeOrder = new PayChargeOrder();
        payChargeOrder.setMerchNo(merchNo);
        payChargeOrder.setForeignOrderNo(outOrderNo);
        payChargeOrder.setOrderNo(orderNo);
        ChargeOrderInfo orderInfo = chargeOrderServiceFacade.getOne(payChargeOrder);
        if( orderInfo ==null ){
            responseMap.put("code",JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        //查询商品信息
        ChargeProductInfo chargeProductInfo = chargeProductServiceFacade.getProductById(orderInfo.getProductId());

        //返回指定字段信息
        Map<String,String> responData = new HashMap<>();
        responData.put("OrderNo",orderInfo.getOrderNo());
        responData.put("outOrderNo",orderInfo.getForeignOrderNo());
        responData.put("phone",orderInfo.getChargePhone());
        responData.put("value",chargeProductInfo.getValue().toString());
        responData.put("salePrice",orderInfo.getProductPrice().toString());
        responData.put("productId",orderInfo.getProductId());
        String statusCn = "";
        switch (orderInfo.getStatus()){
            case 0:
                statusCn = "下单成功";
                break;
            case 1:
                statusCn = "充值中";
                break;
            case 2:
                statusCn = "充值成功";
                break;
            case 3:
                statusCn = "充值失败";
                break;
            case 4:
                statusCn = "申请退款";
                break;
            case 5:
                statusCn = "退款成功";
                break;
            case 6:
                statusCn = "拒绝退款";
                break;
        }
        responData.put("status",""+orderInfo.getStatus());
        responData.put("statusCn",statusCn);
        responseMap.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        responseMap.put("info",JpfInterfaceErrorInfo.SUCCESS.getDesc());
        responseMap.put("data",responData);

        return JsonUtils.toJson(responseMap);
    }

    /**
     * 余额查询
     * merchNo  商户号
     * dateTime  时间
     * sign     签名
     * */
    @RequestMapping(value = "/banlance",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String banlance(HttpServletRequest request, HttpServletResponse response){
        //商户号
        String merchNo = request.getParameter("merchNo");

        //签名串
        String sign = request.getParameter("sign");

        //时间戳
        String dateTime = request.getParameter("dateTime");

        //接口返回参数数据
        Map<String,Object> responseMap = new HashMap<>();

        //参数不合法
        if(StringUtils.isBlank(merchNo) || StringUtils.isBlank(dateTime) ){
            responseMap.put("code",JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        //缺少签名参数
        if( sign== null || StringUtils.isBlank(sign)){
            responseMap.put("code",JpfInterfaceErrorInfo.NO_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.NO_SIGN.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("merchNo",merchNo);
        map.put("dateTime",dateTime);

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
            responseMap.put("code",JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc());

            return JsonUtils.toJson(responseMap);
        }
        //商户删除 或者  商户关闭服务
        if( result.getIsFreeze() == 1 ){
            responseMap.put("code",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getDesc());

            return JsonUtils.toJson(responseMap);
        }
        String  privateKey = result.getPrivateKey();

        //校验来源数据是否合法
        String selfSign = Md5Encrypt.md5(respos+privateKey).toUpperCase();
        if(!selfSign.equals(sign)){
            responseMap.put("code",JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());

            return JsonUtils.toJson(responseMap);
        }

        //返回指定字段信息
        Map<String,String> responData = new HashMap<>();
        responData.put("companyName",result.getCompanyName());
        responData.put("money",result.getMoney().toString());

        responseMap.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        responseMap.put("info",JpfInterfaceErrorInfo.SUCCESS.getDesc());
        responseMap.put("data",responData);

        return JsonUtils.toJson(responseMap);
    }
    /**
     * 微能余额查询接口
     * */
    @RequestMapping("/wnBalanceDeal")
    @ResponseBody
    public void wnBalanceDeal(HttpServletRequest request){

        JSONObject queryParam = new JSONObject();

        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n接口名称：充值余额查询处理");
        sbf.append("\n请求地址：" + request.getQueryString());

        //=============微能余额查询及处理 start=================
        WnpayUtils wnpayUtils = new WnpayUtils(ConfigUtil.getValue("account"),ConfigUtil.getValue("password"),ConfigUtil.getValue("request_url"));
        JSONObject responseP = JSONObject.fromObject(wnpayUtils.flowBalance());
        JSONObject wnBalance = JSONObject.fromObject(responseP.get("data"));
        if(responseP.get("code").toString().equals("10000")){

            sbf.append("\n微能余额查询状态：SUCCESS");
            sbf.append("\n==接口返回信息："+wnBalance.toString());
            ChargeBalanceInfo wnUpInfo = new ChargeBalanceInfo();
            queryParam.put("type","1");
            ChargeBalanceInfo wnInfo = chargeBalanceServiceFacade.getChargeBalanceOne(queryParam);
            if(wnInfo != null){

                sbf.append("\n\t余额信息获取状态：SUCCESS");
                BigDecimal wnBa = new BigDecimal(wnBalance.get("data").toString()).divide(new BigDecimal("10000"));
                wnUpInfo.setId(wnInfo.getId());
                wnUpInfo.setBalance(wnBa);
                int aa = wnInfo.getAlertLimit().compareTo(new BigDecimal(wnBalance.get("data").toString()));
                if(wnInfo.getAlertLimit().compareTo(wnBa) >= 0 && wnInfo.getAlertSwitch().equals((byte)1)){

                    sbf.append("\n\t余额警示提示是否发送：已发送");
                    wnUpInfo.setAlertSwitch((byte)0);
                    String content = "警告！微能余额已不足"+wnInfo.getAlertLimit()+"元，请及时充值。";
                    SmsUtils.send(wnInfo.getAlertPhone().toString(),content);
                }
                chargeBalanceServiceFacade.updateBalanceById(wnUpInfo);
            }else{
                sbf.append("\n\t余额信息获取状态：未查询到相关余额信息");
            }
        }else{
            sbf.append("\n微能余额查询状态：ERROR");
            sbf.append("\n\t接口返回信息："+wnBalance.toString());
        }
        //=============微能余额查询及处理 end=================


        //=============欧非余额查询及处理 start=================
        Map<String,String> responseMap = new OfpayUtils().queryUserInfo();
        if(responseMap.get("retcode").equals("1")){

            sbf.append("\n欧非余额查询状态：SUCCESS");
            sbf.append("\n==接口返回信息："+JSONObject.fromObject(responseMap).toString());
            ChargeBalanceInfo ofUpInfo = new ChargeBalanceInfo();
            queryParam.put("type","0");
            ChargeBalanceInfo ofInfo = chargeBalanceServiceFacade.getChargeBalanceOne(queryParam);
            if(ofInfo != null){
                sbf.append("\n\t余额信息获取状态：SUCCESS");
                ofUpInfo.setId(ofInfo.getId());
                ofUpInfo.setBalance(new BigDecimal(responseMap.get("totalBalance")));

                if(ofInfo.getAlertLimit().compareTo(new BigDecimal(responseMap.get("totalBalance"))) >= 0 && ofInfo.getAlertSwitch().equals((byte)1)){

                    ofUpInfo.setAlertSwitch((byte)0);
                    String content = "警告！欧非余额已不足"+ofInfo.getAlertLimit()+"元，请及时充值。";

                    SmsUtils.send(ofInfo.getAlertPhone().toString(),content);
                    sbf.append("\n\t余额警示短信是否发送：已发送");
                }
                chargeBalanceServiceFacade.updateBalanceById(ofUpInfo);
            }else{
                sbf.append("\n\t余额信息获取状态：未查询到相关余额信息");
            }
        }else{
            sbf.append("\n欧非余额查询状态：ERROR");
            sbf.append("\n\t接口返回信息："+JSONObject.fromObject(responseMap).toString());
        }
        //=============欧非余额查询及处理 end=================

        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-charge-api/log/", "WnOfBalance",true);
    }
    //参数拼接
    @RequestMapping(value = "/testDemo",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void testDemo(HttpServletRequest request){
        try {
            Enumeration enu= request.getParameterNames();
            Map<String,String> param = new ManagedMap<>();
            while(enu.hasMoreElements()){
                String paraName=(String)enu.nextElement();
                param.put(paraName,request.getParameter(paraName));
            }
            String sign = "";
            if( param.containsKey("sign") ){
                sign = param.get("sign");
                param.remove("sign");
            }
            Map<String,Object> treeMap = new TreeMap<>();
            treeMap.putAll(param);

            String respos = ToolUtils.mapToUrl(treeMap);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //return xxx;
    }


}
