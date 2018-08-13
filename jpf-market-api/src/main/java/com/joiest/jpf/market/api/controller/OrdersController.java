package com.joiest.jpf.market.api.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.PayShopInterfaceStream;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.CreateOrderInterfaceRequest;
import com.joiest.jpf.dto.GetCouponRemainResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.market.api.util.ToolsUtils;
import com.joiest.jpf.market.api.util.ofpayUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping("orders")
public class OrdersController {

    private String uid;
    private String openId;
    private ShopCustomerInterfaceInfo userInfo;

    String reg_phone = "^((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0,0-9])|(19[1|8|9])|(16[6]))\\d{8}$";

    /**
     * 商品
     */
    @Autowired
    ShopProductInterfaceServiceFacade shopProductInterfaceServiceFacade;

    /**
     * 订单
     */
    @Autowired
    ShopOrderInterfaceServiceFacade shopOrderInterfaceServiceFacade;

    /**
     * 用户可用券列表
     */
    @Autowired
    ShopCouponActiveInterfaceServiceFacade shopCouponActiveInterfaceServiceFacade;

    @Autowired
    ShopCouponRemainServiceFacade shopCouponRemainServiceFacade;
    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private ShopBrandServiceFacade shopBrandServiceFacade;

    @Autowired
    private ShopInterfaceStreamServiceFacade ShopInterfaceStreamServiceFacade;
    //TODO  记录请求日志  商品类别判断
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String createOrder(String data)
    {
        /*if ( StringUtils.isBlank(data) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});*/
        Map<String, Object> requestMap = new HashMap<>();
        requestMap = _filter(data);
        String requestJson = JsonUtils.toJson(requestMap);
        if ( !requestMap.get("code").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), requestMap.get("info").toString(), "");
        }

        CreateOrderInterfaceRequest request = new CreateOrderInterfaceRequest();
        try{
            request =  (CreateOrderInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未登录", null);
        }
        String chargeNo = "";
        //油卡充值
        if ( request.getOtype().equals("1") || request.getOtype().equals("2") )
        {
            if ( !request.getCardno().equals(request.getCardnumber()) )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "油卡卡号不一致");
            }
            //TODO 油卡卡号校验
            chargeNo = request.getCardnumber();
        } else if ( request.getOtype().equals("3") )
        {
            //话费充值
//            String reg_phone = "^((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0,0-9])|(19[1|8|9])|(16[6]))\\d{8}$";
            Boolean phoneIsTrue = Pattern.compile(reg_phone).matcher(request.getPhone()).matches();
            if ( !phoneIsTrue )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码错误");
            }
            Boolean mobileIsTrue =  Pattern.compile(reg_phone).matcher(request.getMobile()).matches();
            if ( !mobileIsTrue )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码错误");
            }
            if ( !request.getPhone().equals(request.getMobile()) )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码不一致");
            }

            chargeNo = request.getPhone();
        }

        ValidatorUtils.validateInterface(request);
        //获取商品信息
        ShopProductInterfaceInfo productInfo = shopProductInterfaceServiceFacade.getShopProduct(request.getPid());
        if ( productInfo == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MK_PRODUCT_NOFOUND.getCode(), JpfInterfaceErrorInfo.MK_PRODUCT_NOFOUND.getDesc(), "");
        }

        if ( !request.getMoney().equals(request.getPaymoney()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MK_ORDERMONEY_DIFF.getCode(), JpfInterfaceErrorInfo.MK_ORDERMONEY_DIFF.getDesc(), "");
        }

        //创建订单
        String orderno = ToolsUtils.createOrderid();
        ShopOrderInterfaceInfo info = new ShopOrderInterfaceInfo();

        info.setOrderNo(orderno);
        info.setCustomerId(userInfo.getId());
        info.setCustomerName(userInfo.getName());
        info.setProductId(productInfo.getId());
        info.setProductName(productInfo.getName());
        info.setProductMoney(productInfo.getMoney());
        info.setProductDou(productInfo.getDou());
        info.setProductInfoId(productInfo.getProductInfoId());
        info.setAmount(1);
        info.setTotalMoney(productInfo.getMoney());
        info.setTotalDou(productInfo.getDou());
        info.setChargeNo(chargeNo);
        info.setAddtime(new Date());
        info.setOrderType(Byte.valueOf(request.getOtype()));
        info.setRequestedContent(requestJson);
        int res = shopOrderInterfaceServiceFacade.addOrder(info);
        if ( res >= 0 )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), orderno);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), JpfInterfaceErrorInfo.FAIL.getDesc(), null);
    }

    /**
     * 支付
     * 0:欣豆支付 1:微信支付
     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
//    @Transactional
    public String dopay(String data)
    {
        String code =ToolUtils.CreateCode(String.valueOf(userInfo.getDou()),uid);
        //1.金额校验 2.订单用户校验 3.用户券列表 4.扣除相应的券 5.更新code
        //订单信息+用户 TODO 用户信息
        Map<String, Object> requestMap = new HashMap<>();
        requestMap = _filter(data);
        if ( !requestMap.get("code").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), requestMap.get("info").toString(), "");
        }
        if ( StringUtils.isBlank(requestMap.get("orderNo").toString()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "订单号不能为空", "");
        }
        if ( StringUtils.isBlank(requestMap.get("payway").toString()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "支付方式不能为空", "");
        }

        //是否冻结
        if ( userInfo.getStatus() != 1 )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_IS_FREEZE.getCode(), JpfInterfaceErrorInfo.USER_IS_FREEZE.getDesc(), "");
        }

        ShopOrderInterfaceInfo orderInfo = shopOrderInterfaceServiceFacade.getOrderOne(requestMap.get("orderNo").toString(),uid);
        if ( orderInfo == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getCode(), JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getDesc(), "");
        }

        //校验码验证
        Boolean codeIsTrue = ToolUtils.ValidateCode(userInfo.getCode(), uid, userInfo.getDou().toString());
        if ( !codeIsTrue )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_DOU_CODE_ERROR.getCode(), JpfInterfaceErrorInfo.USER_DOU_CODE_ERROR.getDesc(), "");
        }

        //过期券处理
        int count = shopCouponRemainServiceFacade.dealCustomerExpiredCoupon(uid);

        //用户可用券列表
        GetCouponRemainResponse userCouponList = shopCouponRemainServiceFacade.getCouponRemainByUidForInterface(uid);
        if ( userCouponList == null || userCouponList.getCount() == 0)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.CURR_DOU_TOTAL_ZERO.getCode(), JpfInterfaceErrorInfo.CURR_DOU_TOTAL_ZERO.getDesc(), "");
        }
        int orderDou = orderInfo.getTotalDou();
        if ( orderDou > userInfo.getDou() || orderDou > userCouponList.getDouTotal())
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getCode(), JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getDesc(), "");
        }

        //TODO 接口查询
//        if ( orderInfo.getOrderType() == 3 )
//        {
//            Map<String,String> queryMap = new HashMap<>();
//            queryMap.put("phoneno", "18618380116");
//            queryMap.put("pervalue", "50");
//            Map<String, String> queryPhoneResponseMap = new ofpayUtils().telquery(queryMap);
//            if ( !queryPhoneResponseMap.get("retcode").equals("1") )
//            {
//                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), JpfInterfaceErrorInfo.FAIL.getDesc(), "retcode:" + queryPhoneResponseMap.get("retcode") + " ;err_msg:" + queryPhoneResponseMap.get("err_msg"));
//            }
//
//        }else if ( orderInfo.getOrderType() == 1 || orderInfo.getOrderType() == 2 )
//        {
//
//        }

        //扣减豆操作
//        int res_uporder = shopCouponRemainServiceFacade.CouponHandler(userCouponList.getList(), orderInfo, userInfo);

        //商品信息
        ShopProductInterfaceInfo productInfo = shopProductInterfaceServiceFacade.getShopProduct(orderInfo.getProductId());

        Map<String, Object> rechargeMap = new HashMap<>();
        Map<String, String> resultMap = new HashMap<>();
        //充值  1.区分充值类型 2.调用接口
        //油卡充值
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if ( orderInfo.getOrderType() == 3 )
        {
            Boolean res_phoneRecharge = this.phoneRecharge(orderInfo, productInfo);
            if ( res_phoneRecharge )
            {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "充值成功", orderInfo.getOrderNo());
            }
//            //话费充值
//            Boolean phoneIsTrue = Pattern.compile(reg_phone).matcher(orderInfo.getChargeNo()).matches();
//            if ( !phoneIsTrue )
//            {
//                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码错误");
//            }
//            rechargeMap.put("cardnum", productInfo.getRechargeMoney().toString());
//            rechargeMap.put("sporder_id", orderInfo.getOrderNo());
//            rechargeMap.put("sporder_time", orderInfo.getAddtime());
//            rechargeMap.put("game_userid", orderInfo.getChargeNo());
//            rechargeMap.put("buyNum", "1");     //暂定为 1
//
//            resultMap = new ofpayUtils().chargePhone(rechargeMap);

        }else if ( orderInfo.getOrderType() == 1 || orderInfo.getOrderType() == 2 )
        {
            //油卡充值 1:中国石化 2:中国石油
        }

        //添加通道流水 更新order状态
//        if ( resultMap.containsKey("game_state") && resultMap.get("game_state").equals("1") && resultMap.containsKey("game_state") && resultMap.get("game_state").equals("1") )
//        {
//            //充值成功
//        } else
//        {
//            String err_msg = resultMap.getOrDefault("game_state", "");
//            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "充值失败：" + err_msg);
//        }
//        String foreign_orderid = resultMap.getOrDefault("orderid", "");     //接口订单id
//
//        ShopInterfaceStreamInfo stream = new ShopInterfaceStreamInfo();
//        stream.setType((byte)3);
//        stream.setBatchId(orderInfo.getOrderNo());
//        stream.setRequestUrl(resultMap.get("requestUrl"));
//        stream.setRequestContent(resultMap.get("requestParam"));
//        String requestUrl = resultMap.get("requestUrl");
//        String requestParam = resultMap.get("requestParam");
//        resultMap.remove("requestUrl");
//        resultMap.remove("requestParam");
//        String responseJson = JsonUtils.toJson(resultMap);
//        stream.setResponseContent(responseJson);
//        stream.setAddtime(new Date());
//        int res_addstream = ShopInterfaceStreamServiceFacade.addStream(stream);
//
//        //更新订单
//        ShopOrderInterfaceInfo orderinfo = new ShopOrderInterfaceInfo();
//        orderinfo.setId(orderInfo.getId());
//        orderInfo.setRechargeTime(new Date());
//        orderinfo.setRechargeStatus(resultMap.get("game_state"));     //0充值中 1充值成功 9充值失败
//        orderinfo.setForeignOrderNo(foreign_orderid);
//        orderinfo.setForeignRequestContent(requestUrl + "?" + requestParam);
//        orderinfo.setForeignResponseContent(responseJson);
//        int res_upOrder = shopOrderInterfaceServiceFacade.updateOrder(orderinfo);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "充值成功", "");
    }

    private Boolean phoneRecharge(ShopOrderInterfaceInfo orderInfo, ShopProductInterfaceInfo productInfo)
    {
        //查询
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("phoneno", orderInfo.getChargeNo());
        queryMap.put("pervalue", productInfo.getRechargeMoney().toString());
        Map<String, String> queryPhoneResponseMap = new ofpayUtils().telquery(queryMap);
        if ( !queryPhoneResponseMap.get("retcode").equals("1") )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), JpfInterfaceErrorInfo.FAIL.getDesc(), "retcode:" + queryPhoneResponseMap.get("retcode") + " ;err_msg:" + queryPhoneResponseMap.get("err_msg"));
        }

        //话费充值
        Map<String, Object> rechargeMap = new HashMap<>();
        Map<String, String> resultMap = new HashMap<>();

        Boolean phoneIsTrue = Pattern.compile(reg_phone).matcher(orderInfo.getChargeNo()).matches();
        if ( !phoneIsTrue )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码错误");
        }
        rechargeMap.put("cardnum", productInfo.getRechargeMoney().toString());
        rechargeMap.put("sporder_id", orderInfo.getOrderNo());
        rechargeMap.put("sporder_time", orderInfo.getAddtime());
        rechargeMap.put("game_userid", orderInfo.getChargeNo());
        rechargeMap.put("buyNum", "1");     //暂定为 1

        resultMap = new ofpayUtils().chargePhone(rechargeMap);
        if ( resultMap.containsKey("retcode") && resultMap.get("retcode").equals("1") )
        {
            //充值成功
            String foreign_orderid = resultMap.getOrDefault("orderid", "");     //接口订单id

            ShopInterfaceStreamInfo stream = new ShopInterfaceStreamInfo();
            stream.setType((byte)3);
            stream.setBatchId(orderInfo.getOrderNo());
            stream.setRequestUrl(resultMap.get("requestUrl"));
            stream.setRequestContent(resultMap.get("requestParam"));
            String requestUrl = resultMap.get("requestUrl");
            String requestParam = resultMap.get("requestParam");
            resultMap.remove("requestUrl");
            resultMap.remove("requestParam");
            String responseJson = JsonUtils.toJson(resultMap);
            stream.setResponseContent(responseJson);
            stream.setAddtime(new Date());
            int res_addstream = ShopInterfaceStreamServiceFacade.addStream(stream);

            //更新订单
            ShopOrderInterfaceInfo orderinfo = new ShopOrderInterfaceInfo();
            orderinfo.setId(orderInfo.getId());
            orderInfo.setRechargeTime(new Date());
            orderinfo.setRechargeStatus(resultMap.get("game_state"));     //0充值中 1充值成功 9充值失败
            orderinfo.setForeignOrderNo(foreign_orderid);
            orderinfo.setForeignRequestContent(requestUrl + "?" + requestParam);
            orderinfo.setForeignResponseContent(responseJson);
            int res_upOrder = shopOrderInterfaceServiceFacade.updateOrder(orderinfo);

            return true;

        } else
        {
            String err_msg = resultMap.getOrDefault("game_state", "");
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "充值失败：" + err_msg);
        }
    }


    @RequestMapping(value = "blance", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getUserBlance()
    {
        if ( userInfo == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), "");
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), userInfo.getDou());
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

    /**
     * 获取商品列表
     */
    @RequestMapping(value = "/getgoods", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String goodsList(String data)
    {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap = _filter(data);
        if ( !requestMap.get("code").equals(JpfInterfaceErrorInfo.SUCCESS.getCode()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), requestMap.get("info").toString(), "");
        }
        if ( StringUtils.isBlank(requestMap.get("bid").toString()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc(), "");
        }
        List<ShopProductInterfaceInfo> list = shopProductInterfaceServiceFacade.getShopProductByBrandId(requestMap.get("bid").toString());
        if ( list.isEmpty() || list == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.GOODLIST_IS_EMPTY.getCode(), JpfInterfaceErrorInfo.GOODLIST_IS_EMPTY.getDesc(), "");
        }

        JSONArray dataJson = new JSONArray();
        for ( ShopProductInterfaceInfo one: list )
        {
            JSONObject oneJson = new JSONObject();
            oneJson.put("id", one.getId());
            oneJson.put("name", one.getName());
            oneJson.put("faceValue", one.getRechargeMoney());   //面值
            oneJson.put("dou", one.getDou());
            dataJson.add(oneJson);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), dataJson);
    }


    @RequestMapping(value = "getbrand", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getBrand()
    {
        List<ShopBrandInfo> list = shopBrandServiceFacade.getShopBrandAllList();
        if ( list.isEmpty() || list == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.BRANDLIST_IS_EMPTY.getCode(), JpfInterfaceErrorInfo.BRANDLIST_IS_EMPTY.getDesc(), "");
        }
        List<Map<String,String>> resultList = new ArrayList<>();
        for ( ShopBrandInfo one : list )
        {
            Map<String,String> map = new HashMap<>();
            map.put("id", one.getId().toString());
            map.put("brandName", one.getBrandName());
            resultList.add(map);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), resultList);
    }

    private Map<String,Object> _filter(String data)
    {
        Map<String,Object> resultMap = new HashMap<>();
        if ( StringUtils.isBlank(data) )
        {
            resultMap.put("code", JpfInterfaceErrorInfo.FAIL.getCode());
            resultMap.put("info", "信息不能为空");
            return resultMap;
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        resultMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
        resultMap.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        return resultMap;
    }

}
