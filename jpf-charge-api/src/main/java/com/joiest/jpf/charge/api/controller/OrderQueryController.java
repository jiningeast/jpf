package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderResponse;
import com.joiest.jpf.entity.ShopBargainRechargeOrderInfo;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("orderQuery")
public class OrderQueryController {

    @Autowired
    private ShopBargainRechargeOrderServiceFacade shopBargainRechargeOrderServiceFacade;

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
        String orderNo = request.getParameter("orderNo");

        //开始时间
        String startGmtCreate = request.getParameter("startGmtCreate");

        //结束时间
        String endGmtCreate = request.getParameter("endGmtCreate");

        //返回最大条数
        String pageSize = request.getParameter("pageSize");

        //当前页
        String currentPage = request.getParameter("currentPage");

        //签名串
        String sign = request.getParameter("sign");

        //接口返回参数数据
        Map<String,Object> responseMap = new HashMap<>();

        //参数不合法
        if(StringUtils.isBlank(startGmtCreate) ||  StringUtils.isBlank(endGmtCreate) || StringUtils.isBlank(pageSize) || StringUtils.isBlank(currentPage) ){
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

        if( StringUtils.isNotBlank(orderNo) ){

            map.put("orderNo",orderNo);
            requstRecgarge.setOrderNo(orderNo);

        }
        if( StringUtils.isNotBlank(startGmtCreate) ){

            map.put("startGmtCreate",startGmtCreate);
            requstRecgarge.setAddtimeStart(startGmtCreate);

        }
        if( StringUtils.isNotBlank(endGmtCreate) ){

            map.put("endGmtCreate",endGmtCreate);
            requstRecgarge.setAddtimeEnd(endGmtCreate);
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


        //校验来源数据是否合法
        String selfSign = getListSign(map);
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

        //金额限制

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
    /**
     * 获取订单列表签名
     */
    private String getListSign(Map<String,Object> map){

        String aa = ConfigUtil.getValue("API_SECRET");
        String myPackage = map.get("currentPage").toString() + map.get("endGmtCreate").toString() + map.get("pageSize").toString() + map.get("startGmtCreate").toString() + aa;
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
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
