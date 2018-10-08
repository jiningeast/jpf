package com.joiest.jpf.market.api.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.CreateOrderInterfaceRequest;
import com.joiest.jpf.dto.GetCouponRemainResponse;
import com.joiest.jpf.dto.GetShopStockCardResponse;
import com.joiest.jpf.dto.OfpayRequest;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.market.api.constant.ManageConstants;
import com.joiest.jpf.market.api.util.*;
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
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLDecoder;
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

    @Autowired
    private  ShopStockCardServiceFacade shopStockCardServiceFacade;

    @Autowired
    private ShopInterfaceStreamServiceFacade shopInterfaceStreamServiceFacade;

    //TODO  记录请求日志  商品类别判断
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String createOrder(String data)
    {
        // 获取参数
        if ( StringUtils.isBlank(data) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        Map<String, Object>requestMap = _filter(data);
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
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }

        // 判断用户是否已禁用
        if ( userInfo.getStatus() == 0 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_IS_FREEZE.getCode(), JpfInterfaceErrorInfo.USER_IS_FREEZE.getDesc(), "");
        }

        //获取商品信息
        ShopProductInterfaceInfo productInfo = shopProductInterfaceServiceFacade.getShopProduct(request.getPid());
        if ( productInfo == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MK_PRODUCT_NOFOUND.getCode(), JpfInterfaceErrorInfo.MK_PRODUCT_NOFOUND.getDesc(), "");
        }

        // 先创建一个空订单，待更新
        ShopOrderInterfaceInfo info = new ShopOrderInterfaceInfo();
        String orderno = ToolsUtils.createOrderid();
        info.setOrderNo(orderno);
        info.setCustomerId(userInfo.getId());
        info.setCustomerName(userInfo.getNickname());
        info.setProductId(productInfo.getId());
        info.setProductName(productInfo.getName());
        info.setProductMoney(productInfo.getMoney());
        info.setProductDou(productInfo.getDou());
        info.setProductInfoId(productInfo.getProductInfoId());
        info.setAddtime(new Date());
        int orderId = shopOrderInterfaceServiceFacade.addOrder(info);
        info.setId(""+orderId);
        // 获取orderid的个位数，0,1时用欧非接口，2-9用威能接口
        String lastNum = StringUtils.substring(String.valueOf(orderId),-1,String.valueOf(orderId).length());
        if ( Integer.parseInt(lastNum) <= 1 ){
            info.setInterfaceType((byte)1);     // 0=欧非 1=威能
        }else {
            info.setInterfaceType((byte)0);
        }

        // 验证传入的信息
        String source = "1";    // 1=直充 2=卡密
        String chargeNo = "";
        if(requestMap.containsKey("source")){
            source = requestMap.get("source").toString();
        }
        if(source.equals("2")){
            // 卡密订单，判断库存
            int amount = Integer.parseInt(request.getAmount());
            List<ShopStockCardInfo> list = shopStockCardServiceFacade.getShopCard(productInfo.getId(),(byte)0,amount);
            if( list == null || list.isEmpty() || productInfo.getStored()<amount || list.size()<amount ) {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PRODUCT_CARD_TYPE.getCode(), JpfInterfaceErrorInfo.PRODUCT_CARD_TYPE.getDesc(), "");
            }
            if ( list.size() <= productInfo.getStoredSafe() ){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PRODUCT_CARD_TYPE.getCode(), "实际库存量已少于安全库存，无法交易","");
            }
        }else{
            // 油卡充值
            if ( request.getOtype().equals("1") || request.getOtype().equals("2") ) {
                if ( !request.getCardno().equals(request.getCardnumber()) )
                {
                    throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "油卡卡号不一致");
                }
                Boolean gasIsTrue = Pattern.compile(res_gas).matcher(request.getCardnumber()).matches();
                if ( !gasIsTrue )
                {
                    throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "油卡卡号错误");
                }
                chargeNo = request.getCardnumber();
            } else if ( request.getOtype().equals("3") ) {
                //话费充值
                Boolean phoneIsTrue = Pattern.compile(reg_phone).matcher(request.getPhone()).matches();
                if ( !phoneIsTrue ) {
                    throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码错误");
                }
                Boolean mobileIsTrue =  Pattern.compile(reg_phone).matcher(request.getMobile()).matches();
                if ( !mobileIsTrue ) {
                    throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码错误");
                }
                if ( !request.getPhone().equals(request.getMobile()) ) {
                    throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "手机号码不一致");
                }
                if(!requestMap.containsKey("carrier") || StringUtils.isBlank(requestMap.get("carrier").toString())){
                    throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "运营商信息错误");
                }
                //移动cmcc，联通:cucc，电信:ctc，
                List<String> carrierList = new ArrayList<String>(){
                    {
                        add("cmcc");
                        add("cucc");
                        add("ctc");
                    }
                };
                if(!carrierList.contains(requestMap.get("carrier").toString()))
                {
                    throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL, "运营商信息错误");
                }

                chargeNo = request.getPhone();
            }
        }
        ValidatorUtils.validateInterface(request);
        /*
        // 充值金额与付款金额必须一致
        if ( !request.getMoney().equals(request.getPaymoney()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MK_ORDERMONEY_DIFF.getCode(), JpfInterfaceErrorInfo.MK_ORDERMONEY_DIFF.getDesc(), "");
        }
        */

        // 构建订单信息
        if( requestMap.containsKey("carrier") && request.getOtype().equals("3") && info.getInterfaceType()==1 ){
            //移动cmcc，联通:cucc，电信:ctc，
            switch(requestMap.get("carrier").toString()){
                case "cmcc":
                    if(productInfo.getCmccProductId()!=null){
                        info.setWnProductId(productInfo.getCmccProductId().toString());
                    }
                    break;
                case "cucc":
                    if(productInfo.getCuccProductId()!=null){
                        info.setWnProductId(productInfo.getCuccProductId().toString());
                    }
                    break;
                case "ctc":
                    if(productInfo.getCtcProductId()!=null){
                        info.setWnProductId(productInfo.getCtcProductId().toString());
                    }
                    break;
                default:
                    info.setInterfaceType((byte)0);
                    break;
            }
        }
        if ( request.getAmount() != null && StringUtils.isNotBlank(""+request.getAmount()) ){
            // 如果参数中有数量
            info.setTotalMoney(new BigDecimal(productInfo.getMoney().doubleValue()*Integer.parseInt(request.getAmount())));
            info.setTotalDou(productInfo.getDou()*Integer.parseInt(request.getAmount()));
            info.setAmount(Integer.parseInt(request.getAmount()));
        }else{
            // 如果参数中没有数量
            info.setTotalMoney(productInfo.getMoney());
            info.setTotalDou(productInfo.getDou());
            info.setAmount(1);
        }

        // 如果是卡密交易
        if ( source.equals("2") ){
            if ( StringUtils.isNotBlank(""+request.getReceiveType()) ){
                info.setReceiveType(Byte.valueOf(request.getReceiveType()));
            }
            if ( StringUtils.isNotBlank(""+request.getReveiveValue()) ){
                info.setReceiveValue(request.getReveiveValue());
            }
        }

        // 更新订单信息
        info.setChargeNo(chargeNo);
        info.setOrderType(Byte.valueOf(request.getOtype()));
        info.setRequestedContent(requestJson);
        info.setSource((byte)0);    // 0=自平台 1=敬恒
        info.setUpdatetime(new Date());
        int res = shopOrderInterfaceServiceFacade.updateOrder(info);
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
    public String dopay(String data,HttpServletResponse Httpresponse)
    {
         /*
         1.金额校验
         2.订单用户校验
         3.用户券列表
         4.扣除相应的券
         5.更新code
         */
        Map<String, Object> requestMap = _filter(data);
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

        // 是否冻结
        if ( userInfo.getStatus() != 1 )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_IS_FREEZE.getCode(), JpfInterfaceErrorInfo.USER_IS_FREEZE.getDesc(), "");
        }
        // 查询订单
        ShopOrderInterfaceInfo orderInfo = shopOrderInterfaceServiceFacade.getOrderOne(requestMap.get("orderNo").toString(),uid);
        if ( orderInfo == null )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getCode(), JpfInterfaceErrorInfo.MK_ORDER_NOT_EXIST.getDesc(), "");
        }
        // 校验码验证
        Boolean codeIsTrue = ToolUtils.ValidateCode(userInfo.getCode(), uid, userInfo.getDou().toString());
        if ( !codeIsTrue )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_DOU_CODE_ERROR.getCode(), JpfInterfaceErrorInfo.USER_DOU_CODE_ERROR.getDesc(), "");
        }
        //过期券处理
        int count = shopCouponRemainServiceFacade.dealCustomerExpiredCoupon(uid);

        //用户可用券列表
        GetCouponRemainResponse userCouponList = shopCouponRemainServiceFacade.getCouponRemainByUidForInterface(uid);
        if ( userCouponList == null || userCouponList.getCount() == 0) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.CURR_DOU_TOTAL_ZERO.getCode(), JpfInterfaceErrorInfo.CURR_DOU_TOTAL_ZERO.getDesc(), "");
        }
        int orderDou = orderInfo.getTotalDou();
        if ( orderDou > userInfo.getDou() || orderDou > userCouponList.getDouTotal())
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getCode(), JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getDesc(), "");
        }

        //商品信息
        ShopProductInterfaceInfo productInfo = shopProductInterfaceServiceFacade.getShopProduct(orderInfo.getProductId());

        //卡密商品信息判断
        Map<String, String> resultMap = new HashMap<>();
        if (productInfo.getType().toString().equals("2")){
            // 判断库存
            List<ShopStockCardInfo> list = shopStockCardServiceFacade.getShopCard(productInfo.getId(),(byte)0,orderInfo.getAmount());
            if( list == null || list.isEmpty() || productInfo.getStored()<orderInfo.getAmount() || list.size()<orderInfo.getAmount() ) {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PRODUCT_CARD_TYPE.getCode(), JpfInterfaceErrorInfo.PRODUCT_CARD_TYPE.getDesc(), "");
            }else if( list.size() <= productInfo.getStoredSafe() ) {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PRODUCT_CARD_TYPE.getCode(), "实际库存量已少于安全库存，无法交易","");
            } else {
                return this.cardRecharge(orderInfo,productInfo,list,userCouponList,Httpresponse);
            }
        }else{
            //充值
            if ( orderInfo.getOrderType() == 3 ) {
                if(orderInfo.getInterfaceType().equals((byte)1)){
                    // 微能话费充值
                    if ( ConfigUtil.getValue("ENVIRONMENT_TYPE").equals("0") ){
                        // 测试环境
                        resultMap.put("message","成功");
                    }else if (ConfigUtil.getValue("ENVIRONMENT_TYPE").equals("1")){
                        // 正式环境
                        resultMap = this.phoneRechargeWn(orderInfo, productInfo);
                    }
                }else{
                    // 欧非话费充值
                    if ( ConfigUtil.getValue("ENVIRONMENT_TYPE").equals("0") ){
                        // 测试环境
                        resultMap.put("retcode","1");
                    }else if ( ConfigUtil.getValue("ENVIRONMENT_TYPE").equals("1") ){
                        // 正式环境
                        resultMap = this.phoneRechargeOf(orderInfo, productInfo);
                    }
                }
            } else if ( orderInfo.getOrderType() == 1 || orderInfo.getOrderType() == 2 ) {
                // 油卡充值 1:中国石化 2:中国石油
                resultMap = this.gasRecharge(orderInfo, productInfo);
            }
        }
        //添加通道流水 更新order状态
        ShopInterfaceStreamInfo stream = new ShopInterfaceStreamInfo();
        if ( orderInfo.getInterfaceType() == 0 ){
            // 欧飞接口返回处理
            if ( resultMap.containsKey("retcode") && resultMap.get("retcode").equals("1") ) {
                //充值成功
                String foreign_orderid = resultMap.getOrDefault("orderid", "");     //接口订单id
                orderInfo.setForeignOrderNo(foreign_orderid);
                orderInfo.setStatus((byte)1);   // 已支付
            } else {
                orderInfo.setStatus((byte)2);   // 支付失败
            }
        }else if ( orderInfo.getInterfaceType() == 1 ){
            // 威能接口返回处理
            if ( resultMap.containsKey("message") && resultMap.get("message").equals("成功") ){
                orderInfo.setStatus((byte)1);   // 已支付
            }else{
                orderInfo.setStatus((byte)2);   // 支付失败
            }
        }

        byte rechargeType = 0;
        if ( orderInfo.getOrderType() == 1 || orderInfo.getOrderType() == 2 ) {
            rechargeType = 6;
        } else if ( orderInfo.getOrderType() == 3 ) {
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
        String responseJson = orderInfo.getInterfaceType().equals((byte)1)?resultMap.get("responseParam"):JsonUtils.toJson(resultMap);
        stream.setResponseContent(responseJson);
        stream.setAddtime(new Date());
        int res_addstream = ShopInterfaceStreamServiceFacade.addStream(stream);

        //更新订单
        orderInfo.setId(orderInfo.getId());
        orderInfo.setRechargeTime(new Date());
        String game_state = resultMap.getOrDefault("game_state", "");
        orderInfo.setRechargeStatus(game_state);     //0充值中 1充值成功 9充值失败
        orderInfo.setForeignRequestContent(requestUrl + "?" + requestParam);
        orderInfo.setForeignResponseContent(responseJson);
        int res_upOrder = shopOrderInterfaceServiceFacade.updateOrder(orderInfo);

        if ( resultMap.containsKey("retcode") && resultMap.get("retcode").equals("1") ) {
            //扣减豆操作
            int res_uporder = shopCouponRemainServiceFacade.CouponHandler(userCouponList.getList(), orderInfo, userInfo);
        } else {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "提交失败", null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "充值成功", ManageConstants.rechargeStatusCn_map.get(game_state));
    }

    // 发email
    public String sendCardEmail(HttpServletResponse Httpresponse,String data) throws Exception {

        String email= null;
        String orderNo = null;
        String orderId = null;
        try {
            String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
            String requestStr = Base64CustomUtils.base64Decoder(dataStr);
            Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
            email = (String) requestMap.get("email");
            orderNo = requestMap.get("orderNo").toString();
            orderId = requestMap.get("orderId").toString();

            if(email==null){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
            }
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
        }
        //判断请求参数是否合法
        String emailpattern="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
        boolean isemail = Pattern.matches(emailpattern, email);

        if(isemail==false){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "邮箱格式不正确", null);
        }
        //根据用户id查出当前用户的卡密
        String cutomId=userInfo.getId();
        if( org.apache.commons.lang.StringUtils.isBlank(cutomId)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "用户未登录", null);
        }
        GetShopStockCardResponse response=shopStockCardServiceFacade.getCardMByOrderNo(cutomId,orderNo);
        if(response.getList().size()==0){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "您还未有购买的卡密", null);
        }
        //如果有购买的卡密放到Excel
//        设置excel标题以及字段
        JSONArray res = new JSONArray();
        res.add("卡号");
        res.add("密码");
        res.add("购买时间");

        JSONArray filed = new JSONArray();
        filed.add("cardNo");//卡号
        filed.add("cardPass");
        filed.add("paytime");
        JSONObject excel;
        String filepath="";
        String filename="";

        try {
            excel = new ExcelDealUtils().exportExcelByInfo(Httpresponse,res.toString(),filed.toString(),response.getList(),2,ConfigUtil.getValue("EXCEL_PATH"));
            filepath = excel.getJSONObject("data").get("localUrl").toString();
            filename = excel.getJSONObject("data").get("fileName").toString();
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "文件信息有误", null);
        }
        if(org.apache.commons.lang.StringUtils.isBlank(filepath)|| org.apache.commons.lang.StringUtils.isBlank(filename)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "文件信息有误", null);
        }
        // 发送邮件
        String subject = "欣豆市场卡密";
        String sendName = "欣享服务";
        String recipients = email;//接收邮箱地址
        String recipientsName = URLDecoder.decode(userInfo.getNickname(), "UTF-8");
        ;//接收人姓名
        String html = "<p>尊敬的客户，您好：</p>" +
                "<p style='text-index:2em;'>附件打包文件是您的卡密。</p>" +
                "<p style='text-index:2em; color:red;'>【请您知晓，您应妥善保管卡密信息。因泄露信息导致的损失需由您自行承担】</p>" +
                "<p style='text-align:left;'>此文件为系统自动发送，无需回复。</p>" +
                "<p style='text-align:left;'>欣享爱生活</p>";
        Boolean sendStatus =SendMailUtil.sendMultipleEmail(subject,sendName,recipients,recipientsName,filepath,filename,html);

        if ( !sendStatus ){
            // 邮件发送失败
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "发送失败", null);
        }else{
            // OSS上传excel文件
            /*String savePre = ConfigUtil.getValue("EXCEL_PATH");
            String path = PhotoUtil.saveFile(file, savePre);*/
            Map<String,Object> requestMap = new HashMap<>();
            requestMap.put("path",filepath+filename);
            String url = ConfigUtil.getValue("CLOUD_API_URL")+"/oss/upload";
            //String response = "https://yifuka.oss-cn-beijing.aliyuncs.com/clouds/1533717137872.zip";
            String ossRes = OkHttpUtils.postForm(url,requestMap);

            // 添加OSS流水
            ShopInterfaceStreamInfo shopInterfaceStreamInfo = new ShopInterfaceStreamInfo();
            shopInterfaceStreamInfo.setType((byte)0);
            shopInterfaceStreamInfo.setRequestUrl(url);
            shopInterfaceStreamInfo.setRequestContent(filepath+filename);
            shopInterfaceStreamInfo.setResponseContent(ossRes);
            shopInterfaceStreamInfo.setAddtime(new Date());
            shopInterfaceStreamServiceFacade.addStream(shopInterfaceStreamInfo);

            // 更新订单号
            ShopOrderInterfaceInfo shopOrderInfo = new ShopOrderInterfaceInfo();
            shopOrderInfo.setId(orderId);
            shopOrderInfo.setOssUrl(ossRes);
            shopOrderInfo.setUpdatetime(new Date());
            shopOrderInterfaceServiceFacade.updateOrder(shopOrderInfo);

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "邮件发送成功", null);
        }
    }

    /**
    *
     * 卡密信息操作
     * @param orderInfo 订单信息
     * @param productInfo 商品信息
     * @param list 卡信息
    * */
    private String cardRecharge(ShopOrderInterfaceInfo orderInfo, ShopProductInterfaceInfo productInfo,List<ShopStockCardInfo> list,GetCouponRemainResponse userCouponList,HttpServletResponse Httpresponse){

        Date date = new Date();
        SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder smsSb = new StringBuilder();

        for ( ShopStockCardInfo shopStockCardInfo:list ){
            //更新卡密信息到具体的用户
            Map<String,String> cardInfo = new HashMap<>();
            cardInfo.put("id",shopStockCardInfo.getId());
            cardInfo.put("customer_id",userInfo.getId());
            cardInfo.put("customer_name",userInfo.getNickname());
            cardInfo.put("customer_phone",userInfo.getPhone());
            cardInfo.put("paytime","1");
            cardInfo.put("status","1");
            cardInfo.put("orderNo",orderInfo.getOrderNo());

            int isShopSuc = shopStockCardServiceFacade.upShopCardById(cardInfo);

            cardInfo.put("orderAmount",orderInfo.getAmount().toString());
            cardInfo.put("total_money",orderInfo.getTotalMoney().toString());
            //商品购买日志记录 pay_shop_stock_log
            try {
                shopStockCardServiceFacade.upProductStockByPid(cardInfo,productInfo,shopStockCardInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //添加通道流水 更新order状态
            ShopInterfaceStreamInfo stream = new ShopInterfaceStreamInfo();
            ShopOrderInterfaceInfo orderinfo = new ShopOrderInterfaceInfo();
            if ( isShopSuc >0 )
            {
                //购买成功
                orderinfo.setStatus((byte)1);
            }else
            {
                orderinfo.setStatus((byte)2);
            }
            //更新订单
            orderinfo.setId(orderInfo.getId());
            orderinfo.setPaytime(new Date());
            orderinfo.setChargeType((byte)2);
            orderinfo.setStockCardId(shopStockCardInfo.getId());

            int res_upOrder = shopOrderInterfaceServiceFacade.updateOrder(  orderinfo);
            if ( isShopSuc >0 )
            {
                smsSb.append("[卡号："+shopStockCardInfo.getCardNo()+"，密码：" +shopStockCardInfo.getCardPass()+ "]");
            } else
            {
                Map<String,String> err = new HashMap<>();
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "提交失败", null);
            }

            StringBuilder sbf = new StringBuilder();

            sbf.append("\n\nTime:" + DateUtils.getCurDate());
            sbf.append("\n产品类型:" + "卡密购买");
            sbf.append("\n用户id:" + userInfo.getId());
            sbf.append("\n用户手机号:" + userInfo.getPhone());
            sbf.append("\n订单id：" + orderInfo.getId());
            sbf.append("\n订单号：" + orderInfo.getId());
            sbf.append("\n卡密id：" + shopStockCardInfo.getId());
            String fileName = "CardPayLog";
            String path = "/logs/jpf-market-api/log/";

            LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
        }

        //扣减豆操作
        int res_uporder = shopCouponRemainServiceFacade.CouponHandler(userCouponList.getList(), orderInfo, userInfo);

        if ( orderInfo.getReceiveType() == 1 ){
            if ( StringUtils.isNotBlank(orderInfo.getReceiveValue()) ){
                String content = "您已购买中石化加油卡"+list.size()+"张，"+smsSb+"，请妥善保管。";
                Map<String,String> smsResMap = SmsUtils.send(orderInfo.getReceiveValue(),content,"CardSmsLog");

                // 添加短信流水
                ShopInterfaceStreamInfo shopInterfaceStreamInfo = new ShopInterfaceStreamInfo();
                shopInterfaceStreamInfo.setType((byte)1);
                shopInterfaceStreamInfo.setRequestUrl(smsResMap.get("requestUrl"));
                shopInterfaceStreamInfo.setRequestContent(smsResMap.get("requestParam"));
                shopInterfaceStreamInfo.setResponseContent(smsResMap.get("response"));
                shopInterfaceStreamInfo.setOrderNo(orderInfo.getOrderNo());
                shopInterfaceStreamInfo.setAddtime(new Date());
                shopInterfaceStreamServiceFacade.addStream(shopInterfaceStreamInfo);
            }else{
                String content = "您已购买中石化加油卡一张，卡号"+list.get(0).getCardNo()+"，密码"+list.get(0).getCardPass()+"，请妥善保管。";
                Map<String,String> smsResMap = SmsUtils.send(orderInfo.getReceiveValue(),content,"CardSmsLog");

                // 添加短信流水
                ShopInterfaceStreamInfo shopInterfaceStreamInfo = new ShopInterfaceStreamInfo();
                shopInterfaceStreamInfo.setType((byte)1);
                shopInterfaceStreamInfo.setRequestUrl(smsResMap.get("requestUrl"));
                shopInterfaceStreamInfo.setRequestContent(smsResMap.get("requestParam"));
                shopInterfaceStreamInfo.setResponseContent(smsResMap.get("response"));
                shopInterfaceStreamInfo.setOrderNo(orderInfo.getOrderNo());
                shopInterfaceStreamInfo.setAddtime(new Date());
                shopInterfaceStreamServiceFacade.addStream(shopInterfaceStreamInfo);
            }
        }else if ( orderInfo.getReceiveType() == 2 ){
            // 如果接收方式是邮件接收，则给用户发卡密邮件
            Map<String ,String> emailRequestMap = new HashMap<>();
            emailRequestMap.put("email",orderInfo.getReceiveValue());
            emailRequestMap.put("orderNo",orderInfo.getOrderNo());
            emailRequestMap.put("orderId",orderInfo.getId());
            String dataValue = JsonUtils.toJson(emailRequestMap);
            try {
                sendCardEmail(Httpresponse,Base64CustomUtils.base64Encoder(dataValue));

                ShopInterfaceStreamInfo shopInterfaceStreamInfo = new ShopInterfaceStreamInfo();
                shopInterfaceStreamInfo.setType((byte)2);
                shopInterfaceStreamInfo.setRequestUrl( ConfigUtil.getValue("SHOP_API_URL")+"/custom/sendCardEmail" );
                shopInterfaceStreamInfo.setRequestContent( Base64CustomUtils.base64Encoder(dataValue) );
                shopInterfaceStreamInfo.setResponseContent("");
                shopInterfaceStreamInfo.setOrderNo(orderInfo.getOrderNo());
                shopInterfaceStreamInfo.setAddtime(new Date());
                shopInterfaceStreamServiceFacade.addStream(shopInterfaceStreamInfo);
            }catch (Exception e){
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "参数错误", null);
            }

            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "购买成功", null);
        }


        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "购买成功", null);
    }

    /**
     * 话费直充 微能
     */
    private Map<String, String> phoneRechargeWn(ShopOrderInterfaceInfo orderInfo, ShopProductInterfaceInfo productInfo)
    {

        Map<String, String> resultMap = new HashMap<>();
        //充值接口
        JSONObject requestParam = new JSONObject();
        requestParam.put("mobile",orderInfo.getChargeNo());
        requestParam.put("productId",orderInfo.getWnProductId());
        requestParam.put("outOrderId",orderInfo.getOrderNo());

        WnpayUtils wnpayUtils = new WnpayUtils(ConfigUtil.getValue("account"),ConfigUtil.getValue("password"),ConfigUtil.getValue("request_url"));
        String wnProduct = wnpayUtils.flowOrder(requestParam);

        JSONObject responseDeal = JSONObject.fromObject(wnProduct);
        JSONObject actualDeal = JSONObject.fromObject(responseDeal.get("data").toString());

        resultMap.put("orderid",actualDeal.get("wnorderid").toString());
        resultMap.put("requestUrl",actualDeal.get("requestUrl").toString());
        resultMap.put("requestParam",actualDeal.get("requestParam").toString());
        resultMap.put("responseParam",actualDeal.get("responseParam").toString());

        if(responseDeal.get("code").toString().equals("10000")){

            resultMap.put("retcode","1");
            resultMap.put("game_state","0");
        }else{

            resultMap.put("retcode","0");
            resultMap.put("game_state","9");
        }
        return resultMap;
    }
    /**
     * 话费直充 欧非
     */
    private Map<String, String> phoneRechargeOf(ShopOrderInterfaceInfo orderInfo, ShopProductInterfaceInfo productInfo)
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

    /**
     * 油卡充值
     */
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

        //油卡直充
        Map<String, Object> rechargeMap = new HashMap<>();
        Map<String, String> resultMap = new HashMap<>();

        rechargeMap.put("cardnum", 1);  // 直充时这里表示数量
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
        orderinfo.setInterfaceType((byte)0);
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
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "品牌不能为空", "");
        }
        if ( StringUtils.isBlank(requestMap.get("chargeType").toString()) )
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "充值类型不能为空", "");
        }

        //充值类型 0=直冲 1=代充 2=卡密 3=混合
        List<ShopProductInterfaceInfo> list = shopProductInterfaceServiceFacade.getShopProductByBrandId(requestMap.get("bid").toString(),requestMap.get("chargeType").toString());
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
            oneJson.put("stored",one.getStored());
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


    /**
     * 微能查询余额、状态报告获取
     * */
    @RequestMapping(value = "flowbalance",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String flowBalance(HttpServletRequest request){

        String dateTime = request.getParameter("dateTime");
        String sign = request.getParameter("sign");
        String type = request.getParameter("type"); // 1 余额查询  2 状态报告
        JSONObject responsPa = new JSONObject();

        Map<String ,String> bankFouePa = new HashMap<>();
        bankFouePa.put("dateTime", dateTime);
        bankFouePa.put("type", type);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(bankFouePa);

        String respos = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();

        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n请求地址："+request.getRequestURL().toString());
        sbf.append("\n接口名称：微能查询余额参数验证");
        sbf.append("\n接口参数：" + respos+"&sign="+sign);
        sbf.append("\n生成签名：" + selfSign);
        String fileName = "WnApi";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-market-api/log/", fileName,true);

        if(!selfSign.equals(sign)){

            responsPa.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            responsPa.put("info","签名有误");
            return responsPa.toString();
        }
        WnpayUtils wnpayUtils = new WnpayUtils(ConfigUtil.getValue("account"),ConfigUtil.getValue("password"),ConfigUtil.getValue("request_url"));
        String responseP = null;
        if(type.equals("1")){

            responseP = wnpayUtils.flowBalance();
        }else if (type.equals("2")){

            responseP = wnpayUtils.flowReport();
        }

        return responseP;
    }
    /**
     * 微能获取产品信息
     * */
    @RequestMapping(value = "/wnProduct", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getWnProduct(HttpServletRequest request){

        String dateTime = request.getParameter("dateTime");
        String carrier = request.getParameter("carrier");
        String sign = request.getParameter("sign");
        JSONObject responsPa = new JSONObject();

        Map<String ,String> bankFouePa = new HashMap<>();
        bankFouePa.put("carrier", carrier);
        bankFouePa.put("dateTime", dateTime);

        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(bankFouePa);

        String respos = ToolUtils.mapToUrl(treeMap);
        String selfSign = Md5Encrypt.md5(respos+ConfigUtil.getValue("API_SECRET")).toUpperCase();

        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n请求地址："+request.getRequestURL().toString());
        sbf.append("\n接口名称：微能获取产品信息接口参数验证");
        sbf.append("\n接口参数：" + respos+"&sign="+sign);
        sbf.append("\n生成签名：" + selfSign);
        String fileName = "WnApi";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-market-api/log/", fileName,true);

        if(!selfSign.equals(sign)){

            responsPa.put("code",JpfInterfaceErrorInfo.FAIL.getCode());
            responsPa.put("info","签名有误");
            return responsPa.toString();
        }
        WnpayUtils wnpayUtils = new WnpayUtils(ConfigUtil.getValue("account"),ConfigUtil.getValue("password"),ConfigUtil.getValue("request_url"));
        String wnProduct = wnpayUtils.getProduct(carrier);

        return wnProduct;
    }
    /**
     * 微能查询余额、状态报告获取
     * */
    @RequestMapping(value = "flowReport",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String flowReport(){

        WnpayUtils wnpayUtils = new WnpayUtils(ConfigUtil.getValue("account"),ConfigUtil.getValue("password"),ConfigUtil.getValue("request_url"));
        String responseP = wnpayUtils.flowReport();

        JSONObject actualDeal = JSONObject.fromObject(responseP);
        String content = null;
        String infoErrorOrder = null;
        String sucOrder = "";
        String faildOrder = "";
        if(actualDeal.get("code").equals("10000")){

            content = "是";
            JSONArray dataDeal = JSONArray.fromObject(actualDeal.get("data"));
            if(dataDeal.size()>0){

                for(int i=0;i<dataDeal.size();i++){

                    JSONObject job = dataDeal.getJSONObject(i);
                    ShopOrderInterfaceInfo orderInfo = shopOrderInterfaceServiceFacade.getOrderByOrderNo(job.get("outOrderId").toString());
                    if (orderInfo ==null){
                        infoErrorOrder+= job.get("outOrderId").toString()+",";
                        continue;
                    }
                    // 查询订单
                    ShopOrderInterfaceInfo orderinfo = new ShopOrderInterfaceInfo();
                    orderinfo.setId(orderInfo.getId());
                    orderinfo.setUpdatetime(new Date());
                    if (job.get("reportStatus").toString().equals("0")){

                        sucOrder+=orderInfo.getOrderNo()+",";
                        orderinfo.setRechargeStatus("1");
                    }else{

                        faildOrder+=orderInfo.getOrderNo()+",";
                        orderinfo.setRechargeStatus("9");
                    }
                    shopOrderInterfaceServiceFacade.updateOrder(orderinfo);
                }
            }
        }else{
            content = "否";
        }
        StringBuilder sbf = new StringBuilder();
        sbf.append("\n\nTime:" + DateUtils.getCurDate());
        sbf.append("\n接口名称：微能获取订单状态");
        sbf.append("\n是否有待处理订单："+content);
        sbf.append("\n充值成功："+sucOrder);
        sbf.append("\n充值失败："+faildOrder);
        sbf.append("\n订单信息错误：" + infoErrorOrder);
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-market-api/log/", "WnReportApi",true);

        return "1";
    }
    /**
     * 微能接口测试
     * */
    @RequestMapping(value = "wntest",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String wnTest(HttpServletRequest request){
        /*
            //充值接口
            JSONObject requestParam = new JSONObject();
            requestParam.put("mobile","18311171705");
            requestParam.put("productId","40000010");
            requestParam.put("outOrderId",DateUtils.getDate2YmdhmsString(new Date()));

            WnpayUtils wnpayUtils = new WnpayUtils(ConfigUtil.getValue("account"),ConfigUtil.getValue("password"),ConfigUtil.getValue("request_url"));
            String wnProduct = wnpayUtils.flowOrder(requestParam);

         */

        return null;// wnProduct;
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
