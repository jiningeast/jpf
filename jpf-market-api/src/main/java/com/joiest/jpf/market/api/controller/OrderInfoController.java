package com.joiest.jpf.market.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.entity.ShopInterfaceStreamInfo;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.entity.ShopOrderInterfaceInfo;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.market.api.util.ToolsUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("orderInfo")
public class OrderInfoController {

    private String uid;

    private ShopCustomerInterfaceInfo userInfo;

    @Autowired
    private ShopOrderInfoInterfaceServiceFacade shopOrderInfoInterfaceServiceFacade;

    @Autowired
    private ShopCouponActiveInterfaceServiceFacade shopCouponActiveInterfaceServiceFacade;

    @Autowired
    private RedisCustomServiceFacade redisCustomServiceFacade;

    @Autowired
    private ShopCustomerInterfaceServiceFacade shopCustomerInterfaceServiceFacade;

    @Autowired
    private ShopInterfaceStreamServiceFacade shopInterfaceStreamServiceFacade;

    @Autowired
    ShopOrderInterfaceServiceFacade shopOrderInterfaceServiceFacade;

    /*
    * 订单列表页
    * */
    @RequestMapping(value = "/index", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String index(String data)
    {
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
        ShopOrderInfoInterfaceRequest request = new ShopOrderInfoInterfaceRequest();
        try{
            request =  (ShopOrderInfoInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未登录", null);
        }
        request.setUid(uid); // 用户登录ID
        shopOrderInfoInterfaceServiceFacade.timerDetectShopOrderAndCancel(ToolsUtils.getBeforeHourTimeReturnDate(24));
        ShopOrderInfoInterfaceResponse response = shopOrderInfoInterfaceServiceFacade.getList(request);
        if( response == null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "没有更多了", null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response);
    }

    /*
     * 订单详情页
     * */
    @RequestMapping(value = "/view", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String view(String data){
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
        ShopOrderInfoInterfaceRequest request = new ShopOrderInfoInterfaceRequest();
        try{
            request =  (ShopOrderInfoInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未登录", null);
        }

        if ( StringUtils.isBlank(request.getOrderNo()))
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单编号不能为空", null);
        }
        request.setUid(uid); // 用户登录ID
        shopOrderInfoInterfaceServiceFacade.timerDetectShopOrderAndCancel(ToolsUtils.getBeforeHourTimeReturnDate(24));
        ShopOrderInterfaceInfo response = shopOrderInfoInterfaceServiceFacade.getOne(request);
        if( response == null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "没有更多了", null);
        }
        response.setUserDou(userInfo.getDou());  //获取用户当前豆数量
        response.setCurrentSystemTime(new Date()); // 传当前系统时间
        response.setTotalDou(response.getTotalDou());
//        if ( response.getOrderType() == 1 ){
//            response.setTypeName("中石化充值");
//        }else if ( response.getOrderType() == 2 ){
//            response.setTypeName("中石油充值");
//        }else if ( response.getOrderType() == 3 ) {
//            response.setTypeName("话费充值");
//        }else if( response.getOrderType() == 4){
//            response.setTypeName("携程商品");
//        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response);
    }

    /*
     * 欣豆收支明细
     * */
    @RequestMapping(value = "/recordDetail", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String recordDetail(String data){
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
        GetShopCouponActiveInterfaceRequest request = new GetShopCouponActiveInterfaceRequest();
        try{
            request =  (GetShopCouponActiveInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未登录", null);
        }
        request.setUid(uid); // 用户登录ID
        request.setPageSize("10");// 默认每页显示条数

        GetUserCouponActiveInterfaceResponse response = shopCouponActiveInterfaceServiceFacade.getUserCouponActiveList(request);
        if( response == null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "没有更多了", null);
        }
        response.setDouTotal(userInfo.getDou()); //获取用户当前豆数量
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), response);
    }

    /*
     * 订单取消
     * */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String orderInfoCancel(String data){
        if( StringUtils.isBlank(data) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "信息不能为空", null);
        }
        String dataStr = data.replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
        String requestStr = Base64CustomUtils.base64Decoder(dataStr);
        Map<String,Object> requestMap = JsonUtils.toCollection(requestStr, new TypeReference<Map<String, Object>>(){});
        ShopOrderInfoInterfaceRequest request = new ShopOrderInfoInterfaceRequest();
        try{
            request =  (ShopOrderInfoInterfaceRequest) ClassUtil.mapToObject(requestMap, request.getClass());
        }catch (Exception e)
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "未登录", null);
        }

        if ( StringUtils.isBlank(request.getOrderNo()))
        {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), "订单编号不能为空", null);
        }
        request.setUid(uid); // 用户登录ID
        JpfResponseDto jpfResponseDto = shopOrderInfoInterfaceServiceFacade.shopOrderCancel(request);
        if( jpfResponseDto == null || !jpfResponseDto.getRetCode().equals("0000") ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), "数据操作失败", null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), null);
    }

    /**
     * 超时未支付订单自动取消定时器(随项目启动而启动)
     */
    @RequestMapping(value = "/timeoutCancelOrder", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void orderInfoCancel(){
        shopOrderInfoInterfaceServiceFacade.timerDetectShopOrderAndCancel(ToolsUtils.getBeforeHourTimeReturnDate(24));
    }

    /**
     * 处理欧飞异常订单
     * @param
     */
    @RequestMapping(value = "/solveAbnormalOrders", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void solveAbnormalOrders() throws DocumentException {

        //存储日志记录
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder logContent = new StringBuilder();

        String logPath = "/logs/jpf-market-api/log/";
        String fileName = "SearchOufeiOrder";
        logContent.append("\n\nTime:" + myfmt.format(date));

        //查询半小时前的订单
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 30);
        Date beforeTime = calendar.getTime();

        ShopOrderInterfaceInfo shopOrderInterfaceInfo = new ShopOrderInterfaceInfo();
        //已支付状态
        shopOrderInterfaceInfo.setStatus((byte)1);
        //充值中状态
        shopOrderInterfaceInfo.setRechargeStatus("0");
        shopOrderInterfaceInfo.setInterfaceType((byte)0);
        shopOrderInterfaceInfo.setPaytime(beforeTime);
        List<ShopOrderInterfaceInfo> list = shopOrderInfoInterfaceServiceFacade.getAbnormalOrders(shopOrderInterfaceInfo);
        if( list !=null && list.size() >0 ){

            LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

            for (int i = 0; i < list.size(); i++) {

                logContent = new StringBuilder(); //初始化日志变量
                // 请求欧飞接口
                if( list.get(i).getInterfaceType() == 0 ){
                    String orderNo = list.get(i).getOrderNo();
                    String id = list.get(i).getId();
                    String status = list.get(i).getStatus().toString();
                    String rechargeStatus = list.get(i).getRechargeStatus();
                    logContent.append("\n请求单号:"+orderNo+"\t ");
                    Map<String,String> queryMap = new HashMap<>();
                    queryMap.put("sporder_id", orderNo);
                    queryMap.put("format", "json");
                    Map<String,String> queryPhoneResponseMap = new OfpayUtils().searchOrderInfo(queryMap);

                    logContent.append("\n接口返回数据:"+queryPhoneResponseMap.get("responseParam")+" ");

                    // 开始处理订单状态及流水
                    if( queryPhoneResponseMap !=null ){
                        JSONObject responseParam = JSONObject.fromObject(queryPhoneResponseMap.get("responseParam"));
                        if(responseParam != null ){
                            String retcode = responseParam.get("retcode").toString();
                            String game_state = responseParam.get("game_state").toString();
                            if( retcode.equals("1") ){
                                String orderid = responseParam.get("orderid").toString(); //欧飞订单号
                                //流水
                                ShopInterfaceStreamInfo stream = new ShopInterfaceStreamInfo();
                                stream.setType((byte)4); // 4=话费充值接口
                                stream.setOrderNo(orderNo);
                                stream.setRequestUrl(queryPhoneResponseMap.get("requestUrl"));
                                stream.setRequestContent(queryPhoneResponseMap.get("requestParam"));
                                stream.setResponseContent(queryPhoneResponseMap.get("responseParam"));
                                stream.setAddtime(date);
                                int res_addstream = shopInterfaceStreamServiceFacade.addStream(stream);

                                ShopOrderInterfaceInfo info = new ShopOrderInterfaceInfo();
                                info.setForeignOrderNo(orderid);
                                //info.setForeignRequestContent(queryPhoneResponseMap.get("requestParam"));
                                //info.setForeignResponseContent(queryPhoneResponseMap.get("responseParam"));
                                info.setUpdatetime(date);
                                info.setId(id);
                                // 1充值成功、0充值中、9充值失败
                                if(game_state.equals("1") ){

                                    info.setRechargeStatus("1");
                                    info.setStatus((byte)4);
                                    int upCount = shopOrderInterfaceServiceFacade.updateOrder(info);
                                    logContent.append("\n处理结果：接口充值成功 \t 更新前状态：recharge_status:"+rechargeStatus+" status:\"+status+\"\t 更新后状态：recharge_status:1 status:4 \t  ");
                                    if( upCount != 1 ){
                                        logContent.append("\t 订单更新失败 \t");
                                    }else{
                                        logContent.append("\t  订单更新成功 \t");
                                    }
                                }
                                if(game_state.equals("0") ){

                                }
                                if(game_state.equals("9") ){
                                    info.setRechargeStatus("9");
                                    info.setStatus((byte)5);
                                    int upCount = shopOrderInterfaceServiceFacade.updateOrder(info);
                                    logContent.append("\n处理结果：接口充值失败 \t更新前状态：recharge_status:"+rechargeStatus+" status:"+status+" \t更新后状态：recharge_status:9 status=5  ");
                                    if( upCount != 1 ){
                                        logContent.append("\t 订单更新失败 \t");
                                    }else{
                                        logContent.append("\t订单更新成功 \t");
                                    }
                                }

                            }
                        }
                    }

                    LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

                }
            }



        }else{
            logContent.append("\n未匹配到订单信息 ");
            LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);
        }

    }

    
    @ModelAttribute
    public void beforAction(HttpServletRequest request)
    {
        String openId;
        String token = request.getHeader("Token");
        String openId_encrypt = redisCustomServiceFacade.get(ConfigUtil.getValue("WEIXIN_LOGIN_KEY") + token);
        if (StringUtils.isNotBlank(openId_encrypt)) {
            openId = AESUtils.decrypt(openId_encrypt, ConfigUtil.getValue("AES_KEY"));
            userInfo = shopCustomerInterfaceServiceFacade.getCustomerByOpenId(openId).get(0);
            uid = userInfo.getId();
        }
    }

}
