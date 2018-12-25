package com.joiest.jpf.charge.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.joiest.jpf.charge.api.constant.ManageConstants;
import com.joiest.jpf.charge.api.util.SmsUtils;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("orderInfo")
public class orderInfoController {

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;

    @Autowired
    private ChargeInterfaceStreamFacade chargeInterfaceStreamFacade;

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @Autowired
    private ChargeCompanyMoneyStreamServiceFacade chargeCompanyMoneyStreamServiceFacade;

    @Autowired
    private ChargeProductServiceFacade chargeProductServiceFacade;

    /**
     * 查询欧飞异常订单1
     * @param
     * @return
     */
    @RequestMapping(value = "/solveAbnormalOrders", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void solveAbnormalOrders() throws DocumentException {

        //存储日志记录
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 30);
        Date preHalfHours = calendar.getTime();
        StringBuilder logContent = new StringBuilder();

        String logPath = "/logs/jpf-charge-api/log/";
        String fileName = "SearchOufeiOrder";
        logContent.append("\n\nTime: "+myfmt.format(date));

        ChargeOrderInfo chargeOrderInfo = new ChargeOrderInfo();
        //充值中
        chargeOrderInfo.setStatus((byte)1);
        chargeOrderInfo.setAddtime(preHalfHours);
        //chargeOrderInfo.setInterfaceType((byte)0); // 接口类型 0=欧非 1=威能
        List<ChargeOrderInfo> list = chargeOrderServiceFacade.getAbnormalOrders(chargeOrderInfo);
        if( list !=null && list.size() >0 ){

            LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

            for (int i = 0; i < list.size(); i++) {
                String orderNo = list.get(i).getOrderNo();
                String id = list.get(i).getId();
                String status = list.get(i).getStatus().toString();

                logContent = new StringBuilder(); //初始化日志变量
                // 请求欧飞接口 0=欧非 1=威能
                Byte type = list.get(i).getInterfaceType() == null ? (byte)0 : list.get(i).getInterfaceType();
                if( type == 0 ){

                    logContent.append("\n请求欧飞单号:"+orderNo+"\t ");
                    Map<String,String> queryMap = new HashMap<>();
                    queryMap.put("sporder_id", orderNo);
                    queryMap.put("format", "json");
                    Map<String,String> queryPhoneResponseMap = new OfpayUtils().searchOrderInfo(queryMap);

                    logContent.append("\n接口返回数据:"+queryPhoneResponseMap.get("responseParam")+" ");

                    // 开始处理订单状态及流水
                    if( queryPhoneResponseMap !=null ){
                        JSONObject responseParam = JSONObject.fromObject(queryPhoneResponseMap.get("responseParam"));
                        if(responseParam != null && responseParam.containsKey("retcode") && responseParam.get("retcode").toString().equals("1")  ){
                            String retcode = responseParam.get("retcode").toString();
                            String game_state = responseParam.get("game_state").toString();
                            if( retcode.equals("1") ){
                                String orderid = responseParam.get("orderid").toString(); //欧飞订单号
                                //流水
                                //添加流水
                                ChargeInterfaceStreamInfo chargeInterfaceStreamInfo = new ChargeInterfaceStreamInfo();
                                chargeInterfaceStreamInfo.setOrderId(""+id);
                                chargeInterfaceStreamInfo.setOrderNo(orderNo);
                                chargeInterfaceStreamInfo.setType(type);
                                chargeInterfaceStreamInfo.setRequestUrl(queryPhoneResponseMap.get("requestUrl"));
                                chargeInterfaceStreamInfo.setRequestParam(queryPhoneResponseMap.get("requestParam"));
                                chargeInterfaceStreamInfo.setResponse(queryPhoneResponseMap.get("responseParam"));
                                chargeInterfaceStreamInfo.setAddtime(new Date());
                                int res_addstream = chargeInterfaceStreamFacade.addStream(chargeInterfaceStreamInfo);

                                // 资金流水记录更新 只更新接口类型和单号
                                PayChargeCompanyMoneyStream record = new PayChargeCompanyMoneyStream();
                                record.setInterfaceOrderNo(orderid);
                                record.setInterfaceType((byte)0);
                                chargeCompanyMoneyStreamServiceFacade.updateRecord(record,orderNo);

                                ChargeOrderInfo info = new ChargeOrderInfo();
                                info.setInterfaceOrderNo(orderid);
                                //info.setForeignRequestContent(queryPhoneResponseMap.get("requestParam"));
                                //info.setForeignResponseContent(queryPhoneResponseMap.get("responseParam"));
                                info.setUpdatetime(date);
                                info.setId(id);
                                logContent.append("\n处理结果：更新前上游订单号："+list.get(i).getInterfaceOrderNo()+"\t status:"+status+" \t 更新前状态：更新前上游订单号："+orderid+"\t status:"+status+" \t 操作结果： ");
                                // 1充值成功、0充值中、9充值失败
                                if( "1".equals(game_state) ){
                                    info.setStatus((byte) 2);
                                    int upCount = chargeOrderServiceFacade.upOrderInfo(info);
                                    if( upCount != 1 ){
                                        logContent.append("\t 状态更新成功 订单更新失败 \t");
                                    }else{
                                        logContent.append("\t 状态更新失败 订单更新成功 \t");
                                    }
                                }
                                if( "0".equals(game_state) ){
                                    int upCount = chargeOrderServiceFacade.upOrderInfo(info);
                                    if( upCount != 1 ){
                                        logContent.append("\t 订单充值处理中 订单更新失败 \t");
                                    }else{
                                        logContent.append("\t 订单充值处理中 订单更新成功 \t");
                                    }
                                }
                                if( "9".equals(game_state) ){

                                    //处理退款流程
                                    String response = this.autoTuikuan(list.get(i));
                                    JSONObject result = JSONObject.fromObject(response);
                                    if( result != null && "10000".equals(result.get("code").toString()) ){
                                        logContent.append("\t 订单更新为：已退款 退款结果："+response+"\t");
                                    }else{
                                        logContent.append("\t 订单更新为：已退款 退款结果："+response +"\t");
                                    }
                                    //3=上游充值失败
//                                    info.setStatus((byte)3);
//                                    int upCount = chargeOrderServiceFacade.upOrderInfo(info);
//                                    if( upCount != 1 ){
//                                        logContent.append("\t 订单更新为：上游充值失败 订单更新失败 \t");
//                                    }else{
//                                        logContent.append("\t 订单更新为：上游充值失败 订单更新成功 \t");
//                                    }
                                }

                            }
                        }else if( responseParam != null && responseParam.containsKey("retcode") && "1010".equals(responseParam.get("retcode").toString()) ){
                            //未查询到欧飞订单 处理退款
                            logContent.append("\n处理结果：未查询到欧飞订单号 ");
                            //处理退款流程
                            String response = this.autoTuikuan(list.get(i));
                            JSONObject result = JSONObject.fromObject(response);
                            if( result != null && "10000".equals(result.get("code").toString()) ){
                                logContent.append("\t 订单更新为：已退款 退款结果："+response+"\t");
                            }else{
                                logContent.append("\t 订单更新为：已退款 退款结果："+response +"\t");
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

    /**
     * 查询所有订单与欧飞订单匹配
     * @return
     */
    @RequestMapping(value = "/balanceOfAccount", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String balanceOfAccount(HttpServletResponse response) throws DocumentException  {
        //存储日志记录
        StringBuilder logContent = new StringBuilder();
        String logPath = "/logs/jpf-charge-api/log/";
        String fileName = "SearchOufeiOrder";
        logContent.append("\n\nTime: "+DateUtils.getCurDate());
        //查询欧非的订单，并且对账
        List<BalanceOrder> balanceOrders = new ArrayList<BalanceOrder>();
        List<PayChargeOrder> list = chargeOrderServiceFacade.getAllAbnormalOrders();
        for (PayChargeOrder payChargeOrder: list) {
            LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);
            Byte type =0;
            if(payChargeOrder.getInterfaceType()!=null){
                type = payChargeOrder.getInterfaceType();
            }
            //查询欧非单号
            String orderNo = payChargeOrder.getOrderNo();
            String orderId = payChargeOrder.getId();
            String status = payChargeOrder.getStatus().toString();

            logContent.append("\n请求单号:"+orderNo+"\t ");
            Map<String,String> queryMap = new HashMap<>();
            queryMap.put("sporder_id", orderNo);
            queryMap.put("format", "json");
            Map<String,String> resetMap = new OfpayUtils().searchOrderInfo(queryMap);
            if(resetMap!=null){
                JSONObject responseParam = JSONObject.fromObject(resetMap.get("responseParam"));
                if(responseParam != null && responseParam.containsKey("retcode") &&"1".equals(responseParam.get("retcode").toString())){
                    String retcode = responseParam.get("retcode").toString();
                    String game_state = responseParam.get("game_state").toString();
                    if("0".equals(game_state)&&payChargeOrder.getStatus()!=1 ){
                        addBalanceOrder(balanceOrders, payChargeOrder, orderId, interfaceStatus(Byte.parseByte(game_state)));
                    }else if("9".equals(game_state)&&(payChargeOrder.getStatus()!=5)){
                        addBalanceOrder(balanceOrders, payChargeOrder, orderId, interfaceStatus(Byte.parseByte(game_state)));
                    }else if("1".equals(game_state)&&(payChargeOrder.getStatus()!=2)){
                        addBalanceOrder(balanceOrders, payChargeOrder, orderId, interfaceStatus(Byte.parseByte(game_state)));
                    }else{
                        logContent.append("\t正常订单");
                    }
                    logContent.append("\t欧非充值中，欣享"+getStatusInfo(payChargeOrder.getStatus()));
                    LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);
                }
            }
        }

        if(balanceOrders!=null&&balanceOrders.size()!=0){
            chargeOrderServiceFacade.sendEmailToManager(balanceOrders,response);
        }
        return "执行完毕";
    }

    /**
     * 增加不符的订单
     * @param balanceOrders
     * @param payChargeOrder
     * @param orderId
     * @param interfaceStatus
     */
    private void addBalanceOrder(List<BalanceOrder> balanceOrders, PayChargeOrder payChargeOrder, String orderId, String interfaceStatus) {
        BalanceOrder balanceOrder = new BalanceOrder();
        balanceOrder.setAddtime(DateUtils.format(payChargeOrder.getAddtime(), DateUtils.DATEFORMATLONG));
        balanceOrder.setInterfaceOrderNo(payChargeOrder.getInterfaceOrderNo());
        balanceOrder.setInterfaceOrderStatus(interfaceStatus);
        balanceOrder.setMoney(payChargeOrder.getTotalMoney().toString());
        balanceOrder.setPhone(payChargeOrder.getChargePhone());
        balanceOrder.setSelfOrderNo(payChargeOrder.getOrderNo());
        String statusInfo = getStatusInfo(payChargeOrder.getStatus());
        balanceOrder.setSelfOrderStatus(statusInfo);
        balanceOrder.setOrderId(orderId);
        balanceOrders.add(balanceOrder);
    }

    private String getStatusInfo(Byte status) {
        String statusInfo="";
        switch (status){
            case 1 : statusInfo = "充值中" ; break;
            case 2 : statusInfo = "上游充值成功" ; break;
            case 3 : statusInfo = "上游充值失败" ; break;
            case 4 : statusInfo = "申请退款" ; break;
            case 5 : statusInfo = "退款成功" ; break;
            case 6 : statusInfo = "拒绝退款" ; break;
            case 7 : statusInfo = "退款失败" ; break;
        }
        return  statusInfo;
    }

    private String interfaceStatus(Byte status) {
        String statusInfo="";
        switch (status){
            case 0 : statusInfo = "充值中" ; break;
            case 1 : statusInfo = "充值成功" ; break;
            case 9 : statusInfo = "充值失败" ; break;
        }
        return  statusInfo;
    }



    // 退款操作
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public String autoTuikuan(ChargeOrderInfo chargeOrderInfo){

        //操作标识  10008:失败  10000:成功
        JSONObject responseParam = new JSONObject();
//        responseParam.put("code","10008");
//        responseParam.put("info","退款申请流程失败");

        ChargeOrderInfo orderInfo = new ChargeOrderInfo();
        //退款成功
        orderInfo.setStatus((byte)5);
        orderInfo.setId(chargeOrderInfo.getId());
        orderInfo.setCheckId("0");
        orderInfo.setCheckName("");
        int count  = chargeOrderServiceFacade.upOrderInfo(orderInfo);
        if( count != 1 ){
            responseParam.put("code","10008");
            responseParam.put("info","订单状态更新失败");
            return responseParam.toString();
//            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
        }else{
            ChargeCompanyInfo companyInfo = new ChargeCompanyInfo();
            String companyId = chargeOrderInfo.getCompanyId();
            companyInfo.setId(companyId);
            ChargeCompanyInfo chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByPrimaryKey(companyId);
            if( chargeCompanyInfo == null ){
                responseParam.put("code","10008");
                responseParam.put("info","请求参数错误");
                return responseParam.toString();
//                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到商户信息");
            }
//            if( chargeCompanyInfo.getIsFreeze() == 1 || chargeCompanyInfo.getIsDel()==1 ){
//                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "账户冻结或关闭，请联系管理员");
//            }

            BigDecimal preMoney  = chargeCompanyInfo.getMoney();
            String preCode = chargeCompanyInfo.getMoneyCode();
            //退款金额
            BigDecimal money = chargeOrderInfo.getTotalMoney();
            String keyStr = ConfigUtil.getValue("MERCH_VALIDE_CODE");
            Boolean flag = ToolUtils.ValidateCode(preCode,companyId,preMoney.toString(),keyStr);
            if( !flag ){
                responseParam.put("code","10008");
                responseParam.put("info","金额校验失败");
                return responseParam.toString();
//                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "金额校验失败");
            }
            //开始退款
            BigDecimal afterMoney = preMoney.add(money);
            String newCode = ToolUtils.CreateCode(afterMoney.toString(),companyId,keyStr);

            PayChargeCompany chargeCompany = new PayChargeCompany();
            chargeCompany.setId(companyId);
            chargeCompany.setMoney(afterMoney);
            chargeCompany.setMoneyCode(newCode);
            JpfResponseDto jpfResponseDto = chargeCompanyServiceFacade.updateCompanyRecord(chargeCompany);
            if(jpfResponseDto == null || !jpfResponseDto.getRetCode().equals("0000") ){
                responseParam.put("code","10008");
                responseParam.put("info","pay_charge_company表操作失败");
                return responseParam.toString();
//                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
            }else{
                BigDecimal zeroNum = new BigDecimal("0");
                Date curretDate = new Date();
                PayChargeCompanyMoneyStream streamData = new PayChargeCompanyMoneyStream();
                streamData.setStreamNo("MS"+ToolUtils.createOrderid());//流水号
                streamData.setCompanyId(companyId);//商户id
                streamData.setCompanyName(chargeCompanyInfo.getCompanyName());//商户名称
                streamData.setMerchNo(chargeCompanyInfo.getMerchNo());//商户号
                streamData.setOrderId(chargeOrderInfo.getId());//订单id 可能是消费订单、充值订单、退款订单
                streamData.setOrderNo(chargeOrderInfo.getOrderNo()); // 订单号可能是消费订单、充值订单、退款订单
                streamData.setProductId(chargeOrderInfo.getProductId());//产品Id
                streamData.setProductName(chargeOrderInfo.getProductName());//产品名称

                streamData.setProductValue(chargeOrderInfo.getProductValue()); //产品面值
                streamData.setProductBidPrice(chargeOrderInfo.getProductBidPrice());//产品成本价
                streamData.setProductSalePrice(chargeOrderInfo.getProductPrice());//产品标准售价 默认null
                streamData.setProductInterfacePrice(chargeOrderInfo.getProductBidPrice());//产品接口价同成本价
                streamData.setProductAmount(chargeOrderInfo.getProductAmount());//产品数量
                streamData.setTotalMoney(chargeOrderInfo.getTotalMoney());//总价

                streamData.setInterfaceType(chargeOrderInfo.getInterfaceType());//接口类型 0=欧非 1=威能 默认null
                streamData.setInterfaceOrderNo(chargeOrderInfo.getInterfaceOrderNo());//接口订单号 默认null
                streamData.setStatus((byte)3);//流水类型 1=充值 2=下单 3=退款
                streamData.setStreamType((byte)0);//流水类型 0=收入 1=支出
                streamData.setNewMoney(afterMoney);//变动后的余额
                streamData.setMemo("");//流水备注
                streamData.setIsDel((byte)0);//删除标记 0=未删除 1=已删除
                int streamCou = chargeCompanyMoneyStreamServiceFacade.create(streamData);
                if( streamCou != 1 ){
                    responseParam.put("code","10008");
                    responseParam.put("info","流水记录失败");
                    return responseParam.toString();
//                    throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "流水记录失败");
                }else{
                    responseParam.put("code","10000");
                    responseParam.put("info","退款成功");
                    return responseParam.toString();
                }

            }
        }

    }

    /**
     * 威能异常订单重新下单
     */
    public void wnCreateOrder(){

        //存储日志记录
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder logContent = new StringBuilder();
        String logPath = "/logs/jpf-charge-api/log/";
        String fileName = "SearchAllWeinengOrder";
        logContent.append("\n\nTime: "+myfmt.format(date));

        ChargeOrderInfo chargeOrderInfo = new ChargeOrderInfo();
        //查询战远 数据ID：12 MC1542166750414621910   宁波站远科技有限公司（企鹅车务）
        chargeOrderInfo.setStatus((byte)8); //威能异常订单
        //chargeOrderInfo.setInterfaceType((byte)0); // 接口类型 0=欧非 1=威能
        List<ChargeOrderInfo> list = chargeOrderServiceFacade.getWeinengAbnormalOrders(chargeOrderInfo);
        if( list !=null && list.size() >0 ){
            for (int i = 0; i < list.size(); i++) {
                String orderNo = list.get(i).getOrderNo();
                String id = list.get(i).getId();
                String status = list.get(i).getStatus().toString();
                String productId = list.get(i).getProductId();

                logContent = new StringBuilder(); //初始化日志变量
                // 订单类型 0=欧非 1=威能
                logContent.append("\n请求单号:"+orderNo+"\t ");

                if( list.get(i).getInterfaceType() == null || list.get(i).getInterfaceType() != 1 ){
                    logContent.append("\n处理结果：非威能单号 不做处理");
                    LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);
                    continue;
                }
                // 处理威能订单并重新下单
                if( list.get(i).getInterfaceType() == 1 ){

                    //获取商品信息
                    ChargeProductInfo chargeProductInfo = chargeProductServiceFacade.getProductById(productId);
                    if(chargeProductInfo == null || (chargeProductInfo != null && StringUtils.isBlank(chargeProductInfo.getWnProductId()) )){
                        logContent.append("\n处理结果：[产品："+productId+"]未获取到商品信息或[wn_product_id：未设置] ");
                        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);
                        continue;
                    }

                    logContent.append("\n处理结果：更新前上游订单号："+list.get(i).getInterfaceOrderNo()+"\t status:"+status+" \t  操作结果： ");
                    // 请求威能下单接口
                    Map<String, String> actParam = new HashMap<>();
                    actParam.put("mobile",list.get(i).getChargePhone());
                    //威能产品ID
                    actParam.put("forProductId",chargeProductInfo.getWnProductId());
                    actParam.put("selfOrder",orderNo);
                    Map<String, String> map = chargeOrderServiceFacade.phoneRechargeWn(actParam);

                    //威能接口流水
                    //添加流水
                    ChargeInterfaceStreamInfo chargeInterfaceStreamInfo = new ChargeInterfaceStreamInfo();
                    chargeInterfaceStreamInfo.setOrderId(""+id);
                    chargeInterfaceStreamInfo.setOrderNo(orderNo);
                    chargeInterfaceStreamInfo.setType((byte) 1);
                    chargeInterfaceStreamInfo.setRequestUrl(map.get("requestUrl"));
                    chargeInterfaceStreamInfo.setRequestParam(map.get("requestParam"));
                    chargeInterfaceStreamInfo.setResponse(map.get("responseParam"));
                    chargeInterfaceStreamInfo.setAddtime(new Date());
                    chargeInterfaceStreamFacade.addStream(chargeInterfaceStreamInfo);

                    ChargeOrderInfo upOrderInfo = new ChargeOrderInfo();
                    upOrderInfo.setId(id);
                    upOrderInfo.setUpdatetime(date);
                    if("10000".equals(map.get("code"))||"10001".equals(map.get("code"))){
                        if("10000".equals(map.get("code"))){
                            upOrderInfo.setStatus((byte)1);
                            logContent.append("\n下单成功，充值中 ");
                        }else{
                            upOrderInfo.setStatus((byte)8);
                            logContent.append("\n下单异常，充值中 ");
                        }
                        //upOrderInfo.setInterfaceOrderNo(map.get("orderid"));
                        chargeOrderServiceFacade.upOrderInfo(upOrderInfo);
                    }else{
                        // 充值失败
                        if( "10008".equals(map.get("code")) ){
                            //处理退款流程
                            String response = this.autoTuikuan(list.get(i));
                            JSONObject result = JSONObject.fromObject(response);
                            if( result != null && "10000".equals(result.get("code").toString()) ){
                                logContent.append("\t 订单更新为：已退款 退款结果："+response+"\t");
                            }else{
                                logContent.append("\t 订单更新为：已退款 退款结果："+response +"\t");
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

}
