package com.joiest.jpf.market.api.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.CreateOrderInterfaceRequest;
import com.joiest.jpf.dto.GetUserCouponActiveInterfaceResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.market.api.util.ToolsUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

@Controller
@RequestMapping("orders")
public class OrdersController {

    private String uid;

    private ShopCustomerInterfaceInfo userInfo;
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
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private ShopBrandServiceFacade shopBrandServiceFacade;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String createOrder(String data)
    {
        if ( StringUtils.isBlank(data) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
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
            if ( !request.getCardno().equals(request.getCardNo()) )
            {
                throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "油卡卡号不一致");
            }
            //TODO 油卡卡号校验

            chargeNo = request.getCardNo();
        } else if ( request.getOtype().equals("3") )
        {
            //话费充值
            String reg_phone = "^((13[0-9])|(14[5|7|9])|(15([0-3]|[5-9]))|(17[0-8])|(18[0,0-9])|(19[1|8|9])|(16[6]))\\d{8}$";
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
        info.setCustomerId("2");    //TODO 获取用户信息
        info.setCustomerName("测试客户");
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
    public String dopay(String data)
    {
        //1.金额校验 2.订单用户校验 3.用户券列表 4.扣除相应的券 5.更新code
        //订单信息+用户 TODO 用户信息
        String orderNo = "7781533611264464114";
        String uid = "2";
        ShopOrderInterfaceInfo orderInfo = shopOrderInterfaceServiceFacade.getOrderOne(orderNo,uid);
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

        //用户可用券列表
        GetUserCouponActiveInterfaceResponse response = shopCouponActiveInterfaceServiceFacade.getUserCouponList("1");
        if ( response == null || response.getCount() == 0)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.CURR_DOU_TOTAL_ZERO.getCode(), JpfInterfaceErrorInfo.CURR_DOU_TOTAL_ZERO.getDesc(), "");
        }
        int orderDou = orderInfo.getTotalDou();


        for ( ShopCouponActiveInterfaceInfo one : response.getList())
        {
            if ( orderDou < one.getDou() )
            {

            }

        }
        return "";
    }

    @RequestMapping(value = "blance", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getUserBlance(String data)
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
//        String uid = "1";
//        int random = ToolUtils.getRandomInt(10000,99999);
//        String token1 = AESUtils.encrypt(uid + random,ConfigUtil.getValue("AES_KEY_MARKET"));
//        String uid_encrypt1 = AESUtils.encrypt(uid, ConfigUtil.getValue("AES_KEY_MARKET"));
//        redisCustomServiceFacade.set(ConfigUtil.getValue("MARKET_USER_LOGIN_KEY") + token1, uid_encrypt1, Long.parseLong(ConfigUtil.getValue("MARKET_USER_LOGIN_EXPIRE_7")) );

        //获取用户信息
        String token = request.getHeader("Token");
        String uid_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("MARKET_USER_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(uid_encrypt))
        {
            uid = AESUtils.decrypt(uid_encrypt, ConfigUtil.getValue("AES_KEY_MARKET"));
            userInfo = shopCustomerInterfaceServiceFacade.getCustomer(uid);
        }
    }

    @RequestMapping(value = "/getgoods", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String goodsList(String data)
    {
        if ( StringUtils.isBlank(data) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});

        List<ShopProductInterfaceInfo> list = shopProductInterfaceServiceFacade.getShopProductByBrandId(requestMap.get("brandId").toString());
        if ( list.isEmpty() || list == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.GOODLIST_IS_EMPTY.getCode(), JpfInterfaceErrorInfo.GOODLIST_IS_EMPTY.getDesc(), "");
        }

        JSONArray dataJson = new JSONArray();
        for ( ShopProductInterfaceInfo one: list )
        {
            JSONObject oneJson = new JSONObject();
            oneJson.put("id", one.getId());
            oneJson.put("money", one.getDou());
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


}
