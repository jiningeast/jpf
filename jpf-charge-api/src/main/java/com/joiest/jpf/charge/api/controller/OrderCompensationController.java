package com.joiest.jpf.charge.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyMoneyStreamServiceFacade;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangmeng
 * @Create 2018/12/19
 * @Description 订单补偿
 */
@Controller
@RequestMapping("orderCompensation")
public class OrderCompensationController {

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;

    @Autowired
    private ChargeCompanyMoneyStreamServiceFacade chargeCompanyMoneyStreamServiceFacade;

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @RequestMapping(value = "/start", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String orderCompensation(HttpServletRequest request){

        Map<String,String> resultMap = new HashMap<>();

        String pageNoStr = request.getParameter("pageNo");

        //不传参默认第一页
        Long pageNo = 1L;

        if (StringUtils.isNotBlank(pageNoStr)){
            pageNo = Long.valueOf(pageNoStr);
        }

        //写到常量
        Long pageSize = 200L;

        //存储日志记录
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder logContent = new StringBuilder();

        String logPath = "/logs/jpf-charge-api/log/";
        String fileName = "OrderCompensation" + pageNo +"-";
        logContent.append("\n\n当前查询条件为第: "+ pageNo +"页,每页查询" + pageSize + "条订单");
        logContent.append("\nTime: "+ format.format(date));

        List<PayChargeOrder> payChargeOrderList = chargeOrderServiceFacade.getOrdersByPage(pageNo, pageSize);
        logContent.append("\nSize: "+payChargeOrderList.size());

        PayChargeCompanyMoneyStream payChargeCompanyMoneyStream = new PayChargeCompanyMoneyStream();

        for (PayChargeOrder order : payChargeOrderList){
            List<PayChargeCompanyMoneyStream> payChargeCompanyMoneyStreamList = chargeCompanyMoneyStreamServiceFacade.getChargeCompanyMoneyStreamByOrderId(order.getId());
            //判断订单状态是否为1或2 订单状态  1=充值中 2=上游充值成功 5=退款成功
            if (order.getStatus() == 1 || order.getStatus() == 2){
               //有一条流水记录
               if (payChargeCompanyMoneyStreamList.size() == 1){

                    if ( order.getTotalMoney().compareTo(payChargeCompanyMoneyStreamList.get(0).getTotalMoney()) > 0){
                        //订单金额大于流水金额
                        logContent.append("\n 订单金额大于流水金额,订单号为:"+order.getOrderNo()+"\t");

                        logContent.append("\t 开始更新流水记录... \t");

                        //更新流水
                        payChargeCompanyMoneyStream.setTotalMoney(order.getTotalMoney());
                        payChargeCompanyMoneyStream.setUpdatetime(new Date());

                        if (chargeCompanyMoneyStreamServiceFacade.updateRecord(payChargeCompanyMoneyStream, order.getOrderNo()) > 0){
                            logContent.append("\t 更新流水记录成功 \t");
                        }else{
                            logContent.append("\t 更新流水记录失败 \t");
                        }

                    }else if(order.getTotalMoney().compareTo(payChargeCompanyMoneyStreamList.get(0).getTotalMoney()) < 0){
                        //订单金额小于流水金额
                        logContent.append("\n 订单金额小于流水金额,订单号为:"+order.getOrderNo()+"\t");

                        //更新流水
                        payChargeCompanyMoneyStream.setTotalMoney(order.getTotalMoney());
                        payChargeCompanyMoneyStream.setUpdatetime(new Date());

                        if (chargeCompanyMoneyStreamServiceFacade.updateRecord(payChargeCompanyMoneyStream, order.getOrderNo()) > 0){
                            logContent.append("\t 更新流水记录成功 \t");
                        }else{
                            logContent.append("\t 更新流水记录失败 \t");
                        }
                    }
               }else{
                   //无流水记录
                   logContent.append("\n 无流水记录,订单号为:"+order.getOrderNo()+"\t");

                   logContent.append("\t 开始新增流水... \t");

                   payChargeCompanyMoneyStream.setStreamNo("MS" + ToolUtils.createOrderid());
                   payChargeCompanyMoneyStream.setTotalMoney(order.getTotalMoney());
                   payChargeCompanyMoneyStream.setCompanyId(order.getCompanyId());
                   payChargeCompanyMoneyStream.setCompanyName(order.getCompanyName());
                   payChargeCompanyMoneyStream.setMerchNo(order.getMerchNo());
                   payChargeCompanyMoneyStream.setOrderId(order.getId());
                   payChargeCompanyMoneyStream.setOrderNo(order.getOrderNo());
                   payChargeCompanyMoneyStream.setProductName(order.getProductName());
                   payChargeCompanyMoneyStream.setProductValue(order.getProductValue());
                   payChargeCompanyMoneyStream.setProductId(order.getProductId());
                   payChargeCompanyMoneyStream.setProductBidPrice(order.getProductBidPrice());
                   payChargeCompanyMoneyStream.setProductSalePrice(order.getProductPrice());
                   payChargeCompanyMoneyStream.setProductAmount(order.getProductAmount());
                   payChargeCompanyMoneyStream.setInterfaceType(order.getInterfaceType());
                   payChargeCompanyMoneyStream.setInterfaceOrderNo(order.getInterfaceOrderNo());
                   payChargeCompanyMoneyStream.setIsDel(new Byte("0"));
                   payChargeCompanyMoneyStream.setAddtime(new Date());

                   //查询当前用户的余额
                   ChargeCompanyInfo chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByPrimaryKey(order.getCompanyId());

                   //扣钱
                   payChargeCompanyMoneyStream.setNewMoney(chargeCompanyInfo.getMoney().subtract(order.getTotalMoney()));
                   payChargeCompanyMoneyStream.setStatus(new Byte("2"));
                   payChargeCompanyMoneyStream.setStreamType(new Byte("1"));


                   if (chargeCompanyMoneyStreamServiceFacade.create(payChargeCompanyMoneyStream) == 1){
                       logContent.append("\t 新增下单流水记录成功 \t");
                   }else{
                       logContent.append("\t 新增下单流水记录失败 \t");
                   }


               }
               //订单状态为5
            }else if(order.getStatus() == 5){

                //有一条流水记录
                if (payChargeCompanyMoneyStreamList.size() == 1){
                    logContent.append("\n 只有一条流水记录,订单号为:"+order.getOrderNo()+"\t");
                    logContent.append("\t 开始新增流水记录... \t");

                    payChargeCompanyMoneyStream.setStreamNo("MS" + ToolUtils.createOrderid());
                    payChargeCompanyMoneyStream.setTotalMoney(order.getTotalMoney());
                    payChargeCompanyMoneyStream.setCompanyId(order.getCompanyId());
                    payChargeCompanyMoneyStream.setCompanyName(order.getCompanyName());
                    payChargeCompanyMoneyStream.setMerchNo(order.getMerchNo());
                    payChargeCompanyMoneyStream.setOrderId(order.getId());
                    payChargeCompanyMoneyStream.setOrderNo(order.getOrderNo());
                    payChargeCompanyMoneyStream.setProductId(order.getProductId());
                    payChargeCompanyMoneyStream.setProductName(order.getProductName());
                    payChargeCompanyMoneyStream.setProductValue(order.getProductValue());
                    payChargeCompanyMoneyStream.setProductBidPrice(order.getProductBidPrice());
                    payChargeCompanyMoneyStream.setProductSalePrice(order.getProductPrice());
                    payChargeCompanyMoneyStream.setProductAmount(order.getProductAmount());
                    payChargeCompanyMoneyStream.setInterfaceType(order.getInterfaceType());
                    payChargeCompanyMoneyStream.setInterfaceOrderNo(order.getInterfaceOrderNo());
                    payChargeCompanyMoneyStream.setIsDel(new Byte("0"));
                    payChargeCompanyMoneyStream.setAddtime(new Date());

                    //查询当前用户的余额
                    ChargeCompanyInfo chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByPrimaryKey(order.getCompanyId());

                    //判断此条流水记录的状态 流水类型 1=充值 2=下单 3=退款
                    if (payChargeCompanyMoneyStreamList.get(0).getStatus() == 2){
                        //扣钱
                        payChargeCompanyMoneyStream.setNewMoney(chargeCompanyInfo.getMoney().subtract(order.getTotalMoney()));
                        payChargeCompanyMoneyStream.setStatus(new Byte("3"));
                        payChargeCompanyMoneyStream.setStreamType(new Byte("1"));
                    }else if (payChargeCompanyMoneyStreamList.get(0).getStatus() == 3){
                        //加钱
                        payChargeCompanyMoneyStream.setNewMoney(chargeCompanyInfo.getMoney().add(order.getTotalMoney()));
                        payChargeCompanyMoneyStream.setStatus(new Byte("2"));
                        payChargeCompanyMoneyStream.setStreamType(new Byte("0"));
                    }

                    if (chargeCompanyMoneyStreamServiceFacade.create(payChargeCompanyMoneyStream) == 1){
                        logContent.append("\t 新增流水记录成功 \t");
                    }else{
                        logContent.append("\t 新增流水记录失败 \t");
                    }


                //有两条流水记录
                }else if(payChargeCompanyMoneyStreamList.size() == 2){
//                    logContent.append("\n 有两条流水记录,订单号为:"+order.getOrderNo()+"\t");

                    for (PayChargeCompanyMoneyStream streamList : payChargeCompanyMoneyStreamList){
                        if (streamList.getTotalMoney().compareTo(order.getTotalMoney()) != 0){
                            //流水金额大于订单金额
                            logContent.append("\t 流水号 " +streamList.getStreamNo()+ "金额与订单金额不匹配 \t");
                            logContent.append("\t 开始更新流水... \t");

                            //更新流水
                            payChargeCompanyMoneyStream.setTotalMoney(order.getTotalMoney());
                            payChargeCompanyMoneyStream.setUpdatetime(new Date());

                            if (chargeCompanyMoneyStreamServiceFacade.updateRecord(payChargeCompanyMoneyStream, order.getOrderNo()) > 0){
                                logContent.append("\t 更新流水记录成功 \t");
                            }else{
                                logContent.append("\t 更新流水记录失败 \t");
                            }
                        }

                    }

                //无流水记录
                }else if(payChargeCompanyMoneyStreamList.size() == 0){
                    logContent.append("\n 无流水记录,订单号为:"+order.getOrderNo()+"\t");

                    logContent.append("\t 开始新增流水... \t");

                    payChargeCompanyMoneyStream.setStreamNo("MS" + ToolUtils.createOrderid());
                    payChargeCompanyMoneyStream.setTotalMoney(order.getTotalMoney());
                    payChargeCompanyMoneyStream.setCompanyId(order.getCompanyId());
                    payChargeCompanyMoneyStream.setCompanyName(order.getCompanyName());
                    payChargeCompanyMoneyStream.setMerchNo(order.getMerchNo());
                    payChargeCompanyMoneyStream.setOrderId(order.getId());
                    payChargeCompanyMoneyStream.setOrderNo(order.getOrderNo());
                    payChargeCompanyMoneyStream.setProductId(order.getProductId());
                    payChargeCompanyMoneyStream.setProductName(order.getProductName());
                    payChargeCompanyMoneyStream.setProductValue(order.getProductValue());
                    payChargeCompanyMoneyStream.setProductBidPrice(order.getProductBidPrice());
                    payChargeCompanyMoneyStream.setProductSalePrice(order.getProductPrice());
                    payChargeCompanyMoneyStream.setProductAmount(order.getProductAmount());
                    payChargeCompanyMoneyStream.setInterfaceType(order.getInterfaceType());
                    payChargeCompanyMoneyStream.setInterfaceOrderNo(order.getInterfaceOrderNo());
                    payChargeCompanyMoneyStream.setIsDel(new Byte("0"));
                    payChargeCompanyMoneyStream.setAddtime(new Date());

                    //查询当前用户的余额
                    ChargeCompanyInfo chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByPrimaryKey(order.getCompanyId());

                    //扣钱
                    payChargeCompanyMoneyStream.setNewMoney(chargeCompanyInfo.getMoney().subtract(order.getTotalMoney()));
                    payChargeCompanyMoneyStream.setStatus(new Byte("2"));
                    payChargeCompanyMoneyStream.setStreamType(new Byte("1"));


                    if (chargeCompanyMoneyStreamServiceFacade.create(payChargeCompanyMoneyStream) == 1){
                        logContent.append("\t 新增下单流水记录成功 \t");
                    }else{
                        logContent.append("\t 新增下单流水记录失败 \t");
                    }

                    //加钱
                    payChargeCompanyMoneyStream.setNewMoney(chargeCompanyInfo.getMoney().subtract(order.getTotalMoney()));
                    payChargeCompanyMoneyStream.setStatus(new Byte("3"));
                    payChargeCompanyMoneyStream.setStreamType(new Byte("0"));


                    if (chargeCompanyMoneyStreamServiceFacade.create(payChargeCompanyMoneyStream) == 1){
                        logContent.append("\t 新增退款流水记录成功 \t");
                    }else{
                        logContent.append("\t 新增退款流水记录失败 \t");
                    }


                }

            }
        }

        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

        resultMap.put("code", "200");
        resultMap.put("message", "订单补偿完成");

        return JSONObject.toJSONString(resultMap);
    }

}
