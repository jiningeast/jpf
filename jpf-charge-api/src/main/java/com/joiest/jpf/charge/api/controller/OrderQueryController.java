package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.charge.api.thread.MatchingDataThread;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargePullOrder;
import com.joiest.jpf.common.po.PayShopBargainRechargeOrder;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderResponse;
import com.joiest.jpf.dto.MarchingDataRequest;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ShopBargainRechargeOrderInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ChargePullOrderServiceFacade;
import com.joiest.jpf.facade.ShopBargainRechargeOrderServiceFacade;
import net.sf.json.JSONArray;
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
@RequestMapping("orderQuery")
public class OrderQueryController {

    @Autowired
    private ShopBargainRechargeOrderServiceFacade shopBargainRechargeOrderServiceFacade;
    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;
    @Autowired
    private ChargePullOrderServiceFacade chargePullOrderServiceFacade;

    /**
     * 订单查询列表
     * orderNo  订单号
     * startGmtCreate  开始时间
     * endGmtCreate     结束时间
     * pageSize     最大条数
     * currentPage     当前页
     * sign     签名
     * */
    @RequestMapping(value = "/orderList",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String orderList(HttpServletRequest request, HttpServletResponse response){

        //订单号
        String pullOrderNo = request.getParameter("pullOrderNo");
        //商户号
        String merchNo = request.getParameter("merchNo");

        //返回最大条数
        String pageSize = request.getParameter("pageSize");

        //当前页
        String currentPage = request.getParameter("currentPage");

        //签名串
        String sign = request.getParameter("sign");

        //接口返回参数数据
        Map<String,Object> responseMap = new HashMap<>();

        //参数不合法
        if( StringUtils.isBlank(merchNo) || StringUtils.isBlank(pageSize) || StringUtils.isBlank(currentPage) || StringUtils.isBlank(pullOrderNo) ){

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

        //查询订单信息
        GetShopBargainRechargeOrderRequest  requstRecgarge=new GetShopBargainRechargeOrderRequest();
        Map<String,Object> map = new HashMap<>();

        if( StringUtils.isNotBlank(pullOrderNo) ){

            map.put("pullOrderNo",pullOrderNo);
            requstRecgarge.setPullOrderNo(pullOrderNo);

        }
        if( StringUtils.isNotBlank(merchNo) ){

            map.put("merchNo",merchNo);
            requstRecgarge.setMerchNo(merchNo);

        }
        if( StringUtils.isNotBlank(pageSize) ){

            if(Integer.valueOf(pageSize)>500){

                responseMap.put("code",JpfInterfaceErrorInfo.IS_MAXPARAM.getCode());
                responseMap.put("info",JpfInterfaceErrorInfo.IS_MAXPARAM.getDesc());

                return JsonUtils.toJson(responseMap);
            }
            map.put("pageSize",pageSize);
            requstRecgarge.setRows(Integer.parseInt(pageSize));
        }
        if( StringUtils.isNotBlank(currentPage) ){

            map.put("currentPage",currentPage);
            requstRecgarge.setPage(Integer.parseInt(currentPage));
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


        GetShopBargainRechargeOrderResponse responseRecharge=shopBargainRechargeOrderServiceFacade.getRecords(requstRecgarge);

        if( responseRecharge.getList()==null ||responseRecharge.getList().size()<1){

            responseMap.put("code",JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getDesc());

            return JsonUtils.toJson(responseMap);
        }
        List<ShopBargainRechargeOrderInfo> listRecharge=responseRecharge.getList();

        JSONArray arrayRecharge=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        Map<String,Object> returnMap=new HashMap<>();



        for (ShopBargainRechargeOrderInfo one:listRecharge){

            jsonObject.put("id",one.getId());//序号
            jsonObject.put("orderNo",one.getOrderNo());//订单号
            jsonObject.put("orderType",one.getOrderType());//订单类型
            jsonObject.put("productName",one.getItemName());//商品名称
            jsonObject.put("price",one.getPrice());//商品单价
            jsonObject.put("facePrice",one.getFacePrice());//商品面额
            jsonObject.put("amt",one.getAmt());//商品数量
            jsonObject.put("amount",one.getAmount());//订单总金额
            jsonObject.put("chargeNo",one.getChargeNo());//充值手机号
            jsonObject.put("status",one.getStatus());//订单状态
            //时间格式转换
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String chargeTime = null;
            try {
                chargeTime = formatter.format(one.getRechargeTime());
            } catch (Exception e) {
                chargeTime="";
            }
            String addtime= null;
            try {
                addtime = formatter.format(one.getAddtime());
            } catch (Exception e) {
                addtime="";
            }
            String updatetime= null;
            try {
                updatetime = formatter.format(one.getUpdatetime());
            } catch (Exception e) {
                updatetime="";
            }
            jsonObject.put("rechargeTime",chargeTime);//充值时间
            jsonObject.put("addtime",addtime);//下单时间
            jsonObject.put("updatetime",updatetime);
            arrayRecharge.add(jsonObject);
        }
        returnMap.put("count",responseRecharge.getList().size());
        returnMap.put("list",arrayRecharge);
        //返回数据
        responseMap.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        responseMap.put("info",JpfInterfaceErrorInfo.SUCCESS.getDesc());
        responseMap.put("data",returnMap);

        return JsonUtils.toJson(responseMap);

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

    /**
     * 下订单
     * @return
     */
    @RequestMapping(value="/payOrder",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String payOrder(MarchingDataRequest marchingDataRequest, HttpServletRequest request, HttpServletResponse  response){

        //商户号
        String merchNo = request.getParameter("merchNo");
        //金额
        String money =request.getParameter("money");
        //签名串
        String sign = request.getParameter("sign");
        Map<String,Object> resultMap = CheckData(merchNo,money,sign);
        Map<String,Object> dataMap;
        Map<String,Object> responseMap =new HashMap<String,Object>();
        ChargeCompanyInfo result;
        if(!JpfInterfaceErrorInfo.SUCCESS.getCode().equals(resultMap.get("code"))){
            return  JsonUtils.toJson(resultMap);
        }else {
            //生成订单数据
            ChargeCompanyInfo record = new ChargeCompanyInfo();
            record.setMerchNo(merchNo);
            result = chargeCompanyServiceFacade.getOne(record);
            try {
                dataMap  = chargePullOrderServiceFacade.savePayOrder(merchNo,money,result);
            } catch (Exception e) {
                responseMap.put("code",JpfInterfaceErrorInfo.CREATE_ORDER_FAILED.getCode());
                responseMap.put("info",JpfInterfaceErrorInfo.CREATE_ORDER_FAILED.getDesc());
                return JsonUtils.toJson(responseMap);
            }
        }

        marchingDataRequest.setList((List<PayShopBargainRechargeOrder>) dataMap.get("matchData"));
        marchingDataRequest.setCompanyName(result.getCompanyName());
        marchingDataRequest.setMoney(result.getMoney());
        // 使用线程
        Thread thread = new Thread(new MatchingDataThread(marchingDataRequest,response));
        thread.start();

        PayChargePullOrder order = (PayChargePullOrder) dataMap.get("data");
        responseMap.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        responseMap.put("info",JpfInterfaceErrorInfo.SUCCESS.getDesc());
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("orderNo",order.getOrderNo());
        data.put("totalNum",order.getMatchRecordsAmount());
        data.put("totalMoney",order.getMatchMoney());
        responseMap.put("data",data);
        return JsonUtils.toJson(responseMap);
    }

    private Map<String,Object> CheckData(String merchNo,String money,String sign){

        //接口返回参数数据
        Map<String,Object> responseMap = new HashMap<>();
        //参数不合法
        if(StringUtils.isBlank(merchNo)){
            responseMap.put("code",JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());
            return responseMap;
        }
        //缺少签名参数
        if( sign== null || StringUtils.isBlank(sign)){
            responseMap.put("code",JpfInterfaceErrorInfo.NO_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.NO_SIGN.getDesc());
            return responseMap;
        }

        Map<String,Object> map = new HashMap<>();
        map.put("merchNo",merchNo);
        map.put("money",money);

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
            return responseMap;
        }
        //商户删除 或者  商户关闭服务
        if( result.getIsFreeze() == 1 ){
            responseMap.put("code",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.MERCH_FREEZEUP.getDesc());
            return responseMap;
        }
        String  privateKey = result.getPrivateKey();

        //校验来源数据是否合法
        String selfSign = Md5Encrypt.md5(respos+privateKey).toUpperCase();
        if(!selfSign.equals(sign)){
            responseMap.put("code",JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc());
            return responseMap;
        }


        //验证余额
        if(!ToolUtils.ValidateCode(result.getMoneyCode(),result.getId(),result.getMoney().toString(),ConfigUtil.getValue("MERCH_VALIDE_CODE"))){
            responseMap.put("code",JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getDesc());
            return responseMap;
        }

        //验证数据的存储量，够不够下单的钱
        BigDecimal moneyTotal = shopBargainRechargeOrderServiceFacade.getMoneyTotal();
        System.out.println(moneyTotal.compareTo(new BigDecimal(money))>0);
        if(moneyTotal.compareTo(new BigDecimal(money))<0){
            responseMap.put("code",JpfInterfaceErrorInfo.EXCESS_DEPOSIT.getCode());
            responseMap.put("info",JpfInterfaceErrorInfo.EXCESS_DEPOSIT.getDesc());
            return responseMap;
        }
        responseMap.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        return responseMap;
    }

    public static void main(String[] args) {
        String str = Md5Encrypt.md5("currentPage=1&merchNo=MC1541126786498921482&pageSize=10&pullOrderNo=PU1541751416887748218ZbwJbbaeSdRuqSeb").toUpperCase();
        System.out.println(str);

        String a="strsss";
        String b = a;
        a="zzzzz";
        System.out.println( a );
        System.out.println(b);

    }
}
