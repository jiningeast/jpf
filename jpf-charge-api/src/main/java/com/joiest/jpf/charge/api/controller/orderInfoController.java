package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.charge.api.constant.ManageConstants;
import com.joiest.jpf.charge.api.util.SmsUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
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


    /**
     * 查询欧飞异常订单
     * @param
     * @return
     */
    @RequestMapping(value = "/solveAbnormalOrders", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
    //@ResponseBody
    public void solveAbnormalOrders() throws DocumentException {

        //存储日志记录
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder logContent = new StringBuilder();

        String logPath = "/logs/jpf-charge-api/log/";
        String fileName = "SearchOufeiOrder";
        logContent.append("\n\nTime: "+myfmt.format(date));

        ChargeOrderInfo chargeOrderInfo = new ChargeOrderInfo();
        //上游充值成功
        chargeOrderInfo.setStatus((byte)2);
        chargeOrderInfo.setInterfaceType((byte)0);
        List<ChargeOrderInfo> list = chargeOrderServiceFacade.getAbnormalOrders(chargeOrderInfo);
        if( list !=null && list.size() >0 ){

            LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

            for (int i = 0; i < list.size(); i++) {

                logContent = new StringBuilder(); //初始化日志变量
                // 请求欧飞接口
                if( list.get(i).getInterfaceType() == 0 ){
                    String orderNo = list.get(i).getOrderNo();
                    String id = list.get(i).getId();
                    String status = list.get(i).getStatus().toString();
                    Byte type = list.get(i).getInterfaceType();
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

                                ChargeOrderInfo info = new ChargeOrderInfo();
                                info.setInterfaceOrderNo(orderid);
                                //info.setForeignRequestContent(queryPhoneResponseMap.get("requestParam"));
                                //info.setForeignResponseContent(queryPhoneResponseMap.get("responseParam"));
                                info.setUpdatetime(date);
                                info.setId(id);
                                logContent.append("\n处理结果：更新前上游订单号："+list.get(i).getInterfaceOrderNo()+"\t status:"+status+" \t 更新前状态：更新前上游订单号："+orderid+"\t status:"+status+" \t 操作结果： ");
                                // 1充值成功、0充值中、9充值失败
                                if(game_state.equals("1") ){
                                    logContent.append(" 状态不用操作  ");
                                }
                                if(game_state.equals("0") ){
                                    int upCount = chargeOrderServiceFacade.upOrderInfo(info);
                                    if( upCount != 1 ){
                                        logContent.append("\t 订单充值处理中 订单更新失败 \t");
                                    }else{
                                        logContent.append("\t 订单充值处理中 订单更新成功 \t");
                                    }
                                }
                                if(game_state.equals("9") ){
                                    //3=上游充值失败
                                    info.setStatus((byte)3);
                                    int upCount = chargeOrderServiceFacade.upOrderInfo(info);
                                    if( upCount != 1 ){
                                        logContent.append("\t 订单更新为：上游充值失败 订单更新失败 \t");
                                    }else{
                                        logContent.append("\t 订单更新为：上游充值失败 订单更新成功 \t");
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




}
