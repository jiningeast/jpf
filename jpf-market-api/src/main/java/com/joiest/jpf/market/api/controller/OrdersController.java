package com.joiest.jpf.market.api.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.PayShopInterfaceStream;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.CreateOrderInterfaceRequest;
import com.joiest.jpf.dto.GetCouponRemainResponse;
import com.joiest.jpf.dto.OfpayRequest;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.market.api.constant.ManageConstants;
import com.joiest.jpf.market.api.util.ServletUtils;
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

    private String reg_phone = "^((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0,0-9])|(19[1|8|9])|(16[6]))\\d{8}$";

    private String res_gas = "^(100011\\d{13})|(90\\d{14})$";      //中石化：以100011开头共19位、中石油：以90开头共16位
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
            Boolean gasIsTrue = Pattern.compile(res_gas).matcher(request.getCardnumber()).matches();
            if ( !gasIsTrue )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "油卡卡号错误");
            }
            chargeNo = request.getCardnumber();
        } else if ( request.getOtype().equals("3") )
        {
            //话费充值
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
        //1.金额校验 2.订单用户校验 3.用户券列表 4.扣除相应的券 5.更新code
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

        //商品信息
        ShopProductInterfaceInfo productInfo = shopProductInterfaceServiceFacade.getShopProduct(orderInfo.getProductId());

        Map<String, Object> rechargeMap = new HashMap<>();
        Map<String, String> resultMap = new HashMap<>();
        //充值
        if ( orderInfo.getOrderType() == 3 )
        {
            //话费充值
            resultMap = this.phoneRecharge(orderInfo, productInfo);

        }else if ( orderInfo.getOrderType() == 1 || orderInfo.getOrderType() == 2 )
        {
            //油卡充值 1:中国石化 2:中国石油
            resultMap = this.gasRecharge(orderInfo, productInfo);
        }

        //添加通道流水 更新order状态
        ShopInterfaceStreamInfo stream = new ShopInterfaceStreamInfo();
        ShopOrderInterfaceInfo orderinfo = new ShopOrderInterfaceInfo();

        if ( resultMap.containsKey("retcode") && resultMap.get("retcode").equals("1") )
        {
            //充值成功
            String foreign_orderid = resultMap.getOrDefault("orderid", "");     //接口订单id
            orderinfo.setForeignOrderNo(foreign_orderid);
            orderinfo.setStatus((byte)1);
        }else
        {
            orderinfo.setStatus((byte)2);
        }
        byte rechargeType = 0;
        if ( orderInfo.getOrderType() == 1 || orderInfo.getOrderType() == 2 )
        {
            rechargeType = 6;
        } else if ( orderInfo.getOrderType() == 3 )
        {
            rechargeType = 4;
        }
        stream.setType(rechargeType);
        stream.setOrderNo(orderInfo.getOrderNo());
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
        orderinfo.setId(orderInfo.getId());
        orderInfo.setRechargeTime(new Date());
        String game_state = resultMap.getOrDefault("game_state", "");
        orderinfo.setRechargeStatus(game_state);     //0充值中 1充值成功 9充值失败
        orderinfo.setForeignRequestContent(requestUrl + "?" + requestParam);
        orderinfo.setForeignResponseContent(responseJson);
        int res_upOrder = shopOrderInterfaceServiceFacade.updateOrder(orderinfo);

        if ( resultMap.containsKey("retcode") && resultMap.get("retcode").equals("1") )
        {
            //扣减豆操作
            int res_uporder = shopCouponRemainServiceFacade.CouponHandler(userCouponList.getList(), orderInfo, userInfo);
        } else
        {
            String err_msg = resultMap.getOrDefault("err_msg", "");
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "提交失败", err_msg);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "充值成功", ManageConstants.rechargeStatusCn_map.get(game_state));
    }

    private Map<String, String> phoneRecharge(ShopOrderInterfaceInfo orderInfo, ShopProductInterfaceInfo productInfo)
    {
        Boolean phoneIsTrue = Pattern.compile(reg_phone).matcher(orderInfo.getChargeNo()).matches();
        if ( !phoneIsTrue )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "手机号码错误");
        }
        //查询
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("phoneno", orderInfo.getChargeNo());
        queryMap.put("pervalue", productInfo.getRechargeMoney().toString());
        Map<String, String> queryPhoneResponseMap = new ofpayUtils().telquery(queryMap);
        //流水
        ShopInterfaceStreamInfo stream = new ShopInterfaceStreamInfo();
        stream.setType((byte)3);
        stream.setOrderNo(orderInfo.getOrderNo());
        stream.setRequestUrl(queryPhoneResponseMap.get("requestUrl"));
        stream.setRequestContent(queryPhoneResponseMap.get("requestParam"));
        String requestUrl = queryPhoneResponseMap.get("requestUrl");
        String requestParam = queryPhoneResponseMap.get("requestParam");
        queryPhoneResponseMap.remove("requestUrl");
        queryPhoneResponseMap.remove("requestParam");
        String responseJson = JsonUtils.toJson(queryPhoneResponseMap);
        stream.setResponseContent(responseJson);
        stream.setAddtime(new Date());
        int res_addstream = ShopInterfaceStreamServiceFacade.addStream(stream);
        if ( !queryPhoneResponseMap.getOrDefault("retcode","").equals("1") )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), queryPhoneResponseMap.get("err_msg"));
        }

        //话费充值
        Map<String, Object> rechargeMap = new HashMap<>();
        Map<String, String> resultMap = new HashMap<>();

        rechargeMap.put("cardnum", productInfo.getRechargeMoney().toString());
        rechargeMap.put("sporder_id", orderInfo.getOrderNo());
        rechargeMap.put("sporder_time", orderInfo.getAddtime());
        rechargeMap.put("game_userid", orderInfo.getChargeNo());
        rechargeMap.put("buyNum", "1");     //暂定为 1
        rechargeMap.put("ret_url", ConfigUtil.getValue("notify_url"));
        resultMap = new ofpayUtils().chargePhone(rechargeMap);
        return resultMap;
    }

    private Map<String, String> gasRecharge(ShopOrderInterfaceInfo orderInfo, ShopProductInterfaceInfo productInfo)
    {
        Boolean gasIsTrue = Pattern.compile(res_gas).matcher(orderInfo.getChargeNo()).matches();
        if ( !gasIsTrue )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "油卡卡号错误");
        }

        //查询
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("game_userid", orderInfo.getChargeNo());
        String chargeType = orderInfo.getOrderType() != null ? orderInfo.getOrderType().toString() : "";
        queryMap.put("chargeType", chargeType );
        Map<String, String> queryGasResponseMap = new ofpayUtils().gasQuery(queryMap);
        //流水
        ShopInterfaceStreamInfo stream = new ShopInterfaceStreamInfo();
        stream.setType((byte)5);    //油卡充值
        stream.setOrderNo(orderInfo.getOrderNo());
        stream.setRequestUrl(queryGasResponseMap.get("requestUrl"));
        stream.setRequestContent(queryGasResponseMap.get("requestParam"));
        String requestUrl = queryGasResponseMap.get("requestUrl");
        String requestParam = queryGasResponseMap.get("requestParam");
        queryGasResponseMap.remove("requestUrl");
        queryGasResponseMap.remove("requestParam");
        String responseJson = JsonUtils.toJson(queryGasResponseMap);
        stream.setResponseContent(responseJson);
        stream.setAddtime(new Date());
        int res_addstream = ShopInterfaceStreamServiceFacade.addStream(stream);
        if ( !queryGasResponseMap.getOrDefault("retcode","").equals("1") )
        {
            throw new JpfInterfaceException( JpfInterfaceErrorInfo.FAIL.getCode(), queryGasResponseMap.get("err_msg"));
        }

        //话费充值
        Map<String, Object> rechargeMap = new HashMap<>();
        Map<String, String> resultMap = new HashMap<>();

        rechargeMap.put("cardnum", productInfo.getRechargeMoney().toString());
        rechargeMap.put("cardid", productInfo.getCardid());
        rechargeMap.put("sporder_id", orderInfo.getOrderNo());
        rechargeMap.put("sporder_time", orderInfo.getAddtime());
        rechargeMap.put("game_userid", orderInfo.getChargeNo());
        rechargeMap.put("chargeType", chargeType );
        rechargeMap.put("buyNum", "1");     //暂定为 1
        rechargeMap.put("ret_url", ConfigUtil.getValue("notify_url"));

        resultMap = new ofpayUtils().chargeGas(rechargeMap);
        return resultMap;
    }

    @RequestMapping("/ofpayNotifyUrl")
    @ResponseBody
    public String ofpayNotifyUrl(OfpayRequest request, HttpServletRequest httpRequest)
    {
//        String sign =

        //1.流水 2.订单信息 3.更新订单状态
        Map<String, Object> map = ClassUtil.requestToMap(request);
        String json = JsonUtils.toJson(map);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt1.format(date));
        sbf.append("\n充值类型:" + "充值回调");
        sbf.append("\n访问地址：" + ServletUtils.getIpAddr(httpRequest));
        sbf.append("\n访问参数：" + map);

        String fileName = "ofpayNotify";
        String path = "/logs/jpf-market-api/log/";

        ShopOrderInterfaceInfo orderInfo = shopOrderInterfaceServiceFacade.getOrder(request.getSporder_id());
        if ( orderInfo == null )
        {
            sbf.append("\n描述： 订单ID：" + request.getSporder_id() + "不存在");
            LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
            return "N";
        }

        //流水
        ShopInterfaceStreamInfo stream = new ShopInterfaceStreamInfo();
        stream.setType((byte)7);
        stream.setBatchId(orderInfo.getOrderNo());
        stream.setResponseContent(json);
        stream.setAddtime(new Date());
        int res_addstream = ShopInterfaceStreamServiceFacade.addStream(stream);
        ShopOrderInterfaceInfo orderinfo = new ShopOrderInterfaceInfo();
        String rechargeStatus = "0";
        Map<String,String> res_cancelDouMap = new HashMap<>();
        if ( request.getRet_code().equals("9") )    //1成功 9失败
        {
            //支付失败 取消豆
            res_cancelDouMap = shopOrderInterfaceServiceFacade.cancelOrderDou(orderInfo.getOrderNo());

            int count = shopCouponRemainServiceFacade.dealCustomerExpiredCoupon(orderInfo.getCustomerId());

            sbf.append("\n订单状态：充值失败");
            sbf.append("\n描述：" + res_cancelDouMap.getOrDefault("douJson","豆退还操作信息为空"));

        } else
        {
            sbf.append("\n订单状态：充值成功");
            sbf.append("\n描述：" + res_cancelDouMap.getOrDefault("douJson","更新订单成功"));
        }
        orderinfo.setId(orderInfo.getId());
        orderinfo.setRechargeStatus(request.getRet_code());     //0充值中 1充值成功 9充值失败
        orderinfo.setUpdatetime(new Date());
        int res_upOrder = shopOrderInterfaceServiceFacade.updateOrder(orderinfo);

        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
        return "Y";
    }

    /**
     * 获取用户当前余额
     */
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
        String requestURI = request.getRequestURI();
        String method_name = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        if ( !method_name.equals("ofpayNotifyUrl"))
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
