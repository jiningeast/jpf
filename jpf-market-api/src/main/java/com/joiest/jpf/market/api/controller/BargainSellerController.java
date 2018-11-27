package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;

import com.joiest.jpf.common.po.PayShopCouponActive;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.DouTransferRequest;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@Controller
@RequestMapping("bargainSeller")
public class BargainSellerController {

    private String uid;
    private String openId;
    private ShopCustomerInterfaceInfo userInfo;

    @Autowired
    private BankCardServiceFacade bankCardServiceFacade;

    @Autowired
    private ShopBargainRequestServiceFacade shopBargainRequestServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private ShopBargainOrderServiceFacade shopBargainOrderServiceFacade;

    @Autowired
    private ShopCustomerServiceFacade shopCustomerServiceFacade;

    @Autowired
    private ShopCouponActiveServiceFacade shopCouponActiveServiceFacade;
    /**
     * 银行卡信息检查
     * **/
    @RequestMapping(value = "/bankInfo",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkBankInfo(HttpServletRequest request){

        JSONObject requestParam = _filter(request.getParameter("data"));
        JSONObject responseParam = new JSONObject();
        responseParam.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
        responseParam.put("code","参数信息不能为空");

        if(!requestParam.containsKey("bankNo") || requestParam.get("bankNo").toString().isEmpty())
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数信息不能为空", null);

        BankCardInfo bankCardInfo = bankCardServiceFacade.cloudBankCardByCardNO(requestParam.get("bankNo").toString());
        if(bankCardInfo==null)
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.BANK_NO_CHECK.getCode(), JpfInterfaceErrorInfo.BANK_NO_CHECK.getDesc(), null);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), bankCardInfo);
    }

    /**
     *转让确认
     * */
    @RequestMapping(value = "/transfer",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String conTransfer(HttpServletRequest request){

        JSONObject requestParam = _filter(request.getParameter("data"));
        if (requestParam.get("code").toString().equals("10008"))
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), requestParam.get("info").toString(), null);

        if(userInfo.getStatus().equals("0"))
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "抱歉，您的账号已冻结", null);

        DouTransferRequest douTransferRequest = (DouTransferRequest) JSONObject.toBean(requestParam, DouTransferRequest.class);
        //参数验证
        ValidatorUtils.validateInterface(douTransferRequest);

        Integer dou = Integer.valueOf(requestParam.get("dou").toString());

       /* if(dou>userInfo.getDou())
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "抱歉，您的欣豆不足", null);*/
        //2018-11-22改
        if(dou>userInfo.getSaleDou()){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "抱歉，您的可转让欣豆不足", null);
        }
        //获取服务转让订单
        ShopBargainRequestInfo shopBargainRequestInfo = shopBargainRequestServiceFacade.getBargainById(requestParam.get("bargainRequestId").toString());
        if(shopBargainRequestInfo==null)
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "买家发布信息有误", null);

        if(shopBargainRequestInfo.getMinDou() > dou){

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "最低转让"+shopBargainRequestInfo.getMinDou(), null);
        }
        //转让价 即 豆转换成的钱
        Double allDou = dou*shopBargainRequestInfo.getOffRate();
        BigDecimal selfTranferPrice = new BigDecimal(allDou).divide(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_DOWN);

        if(!selfTranferPrice.equals(new BigDecimal(requestParam.get("transferPrice").toString())))
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "转让价有误", null);

        //获取买家个人信息
        PayShopCustomer payShopCustomer = shopCustomerServiceFacade.getCustomerById(shopBargainRequestInfo.getCustomerId());

        //用户信息验证 四要素鉴权
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("accountNo",douTransferRequest.getBankNo());
        requestMap.put("idCard",douTransferRequest.getIdno());
        requestMap.put("mobile",douTransferRequest.getPhone());
        requestMap.put("name",douTransferRequest.getRealName());
        requestMap.put("dateTime",DateUtils.getCurDate());
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(requestMap);

        String sign = Md5Encrypt.md5(ToolUtils.mapToUrl(treeMap) + ConfigUtil.getValue("API_SECRET")).toUpperCase();
        requestMap.put("sign",sign);
        String requestUrl = ConfigUtil.getValue("CLOUD_API_URL")+"/toolcate/bankFourCheck";
        String response = OkHttpUtils.postForm(requestUrl,requestMap);
        JSONObject bankCheck = JSONObject.fromObject(response);
        //JSONObject userInfo = JSONObject.fromObject(bankCheck.get("data"));
        if(bankCheck.get("code").equals("10008"))
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "请确认个人信息是否一致", null);


        //用户转让单操作  下单
        String orderNo = "BO"+System.currentTimeMillis()+ToolUtils.getRandomInt(100000,999999);
        ShopBargainOrderInfo orderInfo = new ShopBargainOrderInfo();
        orderInfo.setOrderNo(orderNo);
        orderInfo.setBargainRequestId(shopBargainRequestInfo.getId());
        orderInfo.setBuyerCustomerId(shopBargainRequestInfo.getCustomerId());
        orderInfo.setBuyerCustomerNickname(payShopCustomer.getNickname());//买家昵称
        orderInfo.setSellerCustomerId(uid);
        orderInfo.setSellerCustomerNickname(userInfo.getNickname());//卖家昵称
        orderInfo.setOffRate(shopBargainRequestInfo.getOffRate());
        orderInfo.setMinDou(shopBargainRequestInfo.getMinDou());
        orderInfo.setDou(dou);
        orderInfo.setTransferPrice(selfTranferPrice);
        orderInfo.setRealName(douTransferRequest.getRealName());
        orderInfo.setIdno(douTransferRequest.getIdno());
        orderInfo.setPhone(douTransferRequest.getPhone());
        orderInfo.setBankId(requestParam.get("bankId").toString());
        orderInfo.setBankBrank(requestParam.get("bankBrank").toString());
        orderInfo.setBankNo(douTransferRequest.getBankNo());
        orderInfo.setFindcode(requestParam.get("findCode").toString());
        //orderInfo.setStatus();
        orderInfo.setPaytime(new Date());
        orderInfo.setAddtime(new Date());

        int isPlaceOrderSuc = shopBargainOrderServiceFacade.sellerPlaceOrder(orderInfo);

        //更新用户豆  冻结豆
        ShopCustomerInfo shopCustomerInfo = new ShopCustomerInfo();
        Integer douCount=userInfo.getDou()-dou;
        Integer over = userInfo.getSaleDou()-dou;
        shopCustomerInfo.setSaleDou(over);
        shopCustomerInfo.setDou(douCount);
        String code = ToolUtils.CreateCode(String.valueOf(douCount),userInfo.getId());
        shopCustomerInfo.setCode(code);
        shopCustomerInfo.setId(userInfo.getId());
        //shopCustomerInfo.setCode(ToolUtils.CreateCode(over.toString(),uid));
        if(userInfo.getFreezeDou() != null && userInfo.getFreezeDou()>0){

            shopCustomerInfo.setFreezeDou(userInfo.getFreezeDou()+dou);
        }else{

            shopCustomerInfo.setFreezeDou(dou);
        }
        shopCustomerInfo.setUpdatetime(new Date());
        int isUpSuc = shopCustomerServiceFacade.upCustomerInfo(shopCustomerInfo);

        //操作欣豆交易日志  pay_shop_coupon_active
        String content = "行为：转让欣豆冻结；豆数量："+dou+";转让价："+selfTranferPrice+";用户id:"+uid;
        PayShopCouponActive active = new PayShopCouponActive();
        active.setCustomerId(uid);
        active.setCompanyId(0);
        active.setCouponNo("");
        active.setActiveCode("");
        active.setPayWay((byte)0);
        active.setMoney(selfTranferPrice);
        active.setDou(dou);
        active.setContent(content);
        active.setType("4");
        active.setExpireTime(new Date());
        active.setBargainOrderId(String.valueOf(isPlaceOrderSuc));
        active.setBargainOrderNo(orderNo);

        int isAddSuc = shopCouponActiveServiceFacade.addShopCouponActive(active);

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "转让成功", null);
    }

    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {

        String requestURI = request.getRequestURI();
        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            userInfo = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId).get(0);
            uid = userInfo.getId();
        }
    }

    /**
     * 用户支付
     * */
    private JSONObject _filter(String data)
    {

        JSONObject res = new JSONObject();
        res.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
        res.put("info","信息不能为空");

        if (StringUtils.isBlank(data)) return res;

        String baseDecode = Base64CustomUtils.base64Decoder(data);
        JSONObject requestParam = JSONObject.fromObject(baseDecode);

        res.putAll(requestParam);
        res.put("code",JpfInterfaceErrorInfo.SUCCESS.getCode());
        res.put("info",JpfInterfaceErrorInfo.SUCCESS.getDesc());

        return res;
    }
}
