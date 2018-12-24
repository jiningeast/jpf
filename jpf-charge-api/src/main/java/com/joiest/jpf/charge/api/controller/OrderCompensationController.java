package com.joiest.jpf.charge.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyChargeServiceFacade;
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
import java.util.*;

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

    @Autowired
    private ChargeCompanyChargeServiceFacade chargeCompanyChargeServiceFacade;

    @RequestMapping(value = "/compensation", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String orderCompensation(HttpServletRequest request){

        Map<String,String> resultMap = new HashMap<>();

        String pageNoStr = request.getParameter("pageNo");

        //不传参默认第一页
        Long pageNo = 1L;

        if (StringUtils.isNotBlank(pageNoStr)){
            pageNo = Long.valueOf(pageNoStr);
        }

        //每次查询订单的条数
        Long pageSize = Long.parseLong(ConfigUtil.getValue("PAGE_SIZE"));

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
            //判断订单状态是否为1或2 3 5 7 订单状态 目前只有12357 1=充值中 2=上游充值成功 5=退款成功
            if (order.getStatus() == 1 || order.getStatus() == 2 || order.getStatus() == 7){
               //有一条流水记录
               if (payChargeCompanyMoneyStreamList.size() == 1){

                    if ( order.getTotalMoney().compareTo(payChargeCompanyMoneyStreamList.get(0).getTotalMoney()) > 0){
                        //订单金额大于流水金额
                        logContent.append("\n 订单金额大于流水金额,订单号为:"+order.getOrderNo()+"\t");

                        logContent.append("\t 开始更新流水记录... \t");

                        //更新流水
                        payChargeCompanyMoneyStream.setTotalMoney(order.getTotalMoney());

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

                        if (chargeCompanyMoneyStreamServiceFacade.updateRecord(payChargeCompanyMoneyStream, order.getOrderNo()) > 0){
                            logContent.append("\t 更新流水记录成功 \t");
                        }else{
                            logContent.append("\t 更新流水记录失败 \t");
                        }
                    }
               }else{
                   //无流水记录
                   logContent.append("\n 无流水记录,订单号为:"+order.getOrderNo()+"\t");
                   addStream(payChargeCompanyMoneyStream, order, logContent, "2", "1");
               }
               //订单状态为 3 5 给商户退钱
            }else if(order.getStatus() == 3 || order.getStatus() == 5){

                //有一条流水记录
                if (payChargeCompanyMoneyStreamList.size() == 1){
                    logContent.append("\n 只有一条流水记录,订单号为:"+order.getOrderNo()+"\t");


                    //判断此条流水记录的状态 流水类型 1=充值 2=下单 3=退款
                    if (payChargeCompanyMoneyStreamList.get(0).getStatus() == 2){
                        addStream(payChargeCompanyMoneyStream, order, logContent, "3", "0");
                    }else if (payChargeCompanyMoneyStreamList.get(0).getStatus() == 3){
                        addStream(payChargeCompanyMoneyStream, order, logContent, "2", "1");
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
                    addStream(payChargeCompanyMoneyStream,order,logContent,"2","1");
                    addStream(payChargeCompanyMoneyStream,order,logContent,"3","0");
                }

            }
        }

        //生成日志
        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

        resultMap.put("code", "200");
        resultMap.put("message", "订单补偿完成");

        return JSONObject.toJSONString(resultMap);
    }


    @RequestMapping(value = "/revise", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String  reviseCompanyCharge(){
        Map<String,String> resultMap = new HashMap<>();

        //存储日志记录
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder logContent = new StringBuilder();

        String logPath = "/logs/jpf-charge-api/log/";
        String fileName = "OrderCompensationRevise";
        logContent.append("\nTime: "+ format.format(date) + "开始校正...");

        //获取所有商户列表
        List<PayChargeCompany> payChargeCompanyList = chargeCompanyServiceFacade.getCompanyList();

        //遍历商户
        for (PayChargeCompany company:payChargeCompanyList){
            chargeCompanyServiceFacade.reviseCompanyCharge(company);
        }

        logContent.append("\t 校正完成...");

        //生成日志
        LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

        resultMap.put("code", "200");
        resultMap.put("message", "余额校正完成");

        return JSONObject.toJSONString(resultMap);
    }




    public void addStream(PayChargeCompanyMoneyStream payChargeCompanyMoneyStream,PayChargeOrder order,
                            StringBuilder logContent,String status,String streamType){

        logContent.append("\t 开始新增流水记录... \t");

        String streamNo = "MS" + ToolUtils.createOrderid();

        payChargeCompanyMoneyStream.setStreamNo(streamNo);
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
        payChargeCompanyMoneyStream.setIsDel(Byte.valueOf("0"));
        payChargeCompanyMoneyStream.setStatus(Byte.valueOf(status));
        payChargeCompanyMoneyStream.setStreamType(Byte.valueOf(streamType));

        //以下部分可注释掉,如果正式库数据正常的话,不会改变,如果不正常,在余额校正时会更新new_money

        //查询当前用户的余额
        ChargeCompanyInfo chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByPrimaryKey(order.getCompanyId());

        //根据状态判断是收入还是支出 先支出才会有收入 支出时创建时间取订单的创建时间 收入时间取订单的更新时间
        if ("0".equals(streamType)){
            //收入
            payChargeCompanyMoneyStream.setNewMoney(chargeCompanyInfo.getMoney().add(order.getTotalMoney()));
            //*************测试专用***************
            if (order.getUpdatetime() != null){
                Date date = order.getUpdatetime();
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY,calendar.get(calendar.HOUR_OF_DAY)+1);

                payChargeCompanyMoneyStream.setAddtime(order.getUpdatetime());
                payChargeCompanyMoneyStream.setUpdatetime(calendar.getTime());
            }else{
                //如果更新时间为null
                Date date = order.getAddtime();
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(date);
                calendar.set(Calendar.HOUR_OF_DAY,calendar.get(calendar.HOUR_OF_DAY)+2);

                payChargeCompanyMoneyStream.setAddtime(calendar.getTime());
                payChargeCompanyMoneyStream.setUpdatetime(calendar.getTime());
            }

        }else if ("1".equals(streamType)){
            //支出
            payChargeCompanyMoneyStream.setNewMoney(chargeCompanyInfo.getMoney().subtract(order.getTotalMoney()));
            //*************测试专用***************
            if (order.getUpdatetime() != null){
                payChargeCompanyMoneyStream.setAddtime(order.getUpdatetime());
                payChargeCompanyMoneyStream.setUpdatetime(order.getUpdatetime());
            }else{
                //如果更新时间为null
                Date date = order.getAddtime();
//                Calendar calendar=Calendar.getInstance();
//                calendar.setTime(date);
//                calendar.set(Calendar.HOUR_OF_DAY,calendar.get(calendar.HOUR_OF_DAY));
                payChargeCompanyMoneyStream.setAddtime(date);
                payChargeCompanyMoneyStream.setUpdatetime(date);
            }
        }

        //以上若注释,放开下面的注释


//        if (order.getUpdatetime() != null){
//            payChargeCompanyMoneyStream.setAddtime(order.getUpdatetime());
//        }else{
//            //如果更新时间为null
//            Date date = order.getAddtime();
//            System.out.println("获取的addTime:" + date);
//            date.setHours(order.getAddtime().getHours() + 1);
//            System.out.println("+1后的addTime:" + date);
//            payChargeCompanyMoneyStream.setAddtime(date);
//        }

        if (chargeCompanyMoneyStreamServiceFacade.addStream(payChargeCompanyMoneyStream) == 1){
            if ("3".equals(status)){
                logContent.append("\t 新增退款流水记录成功 流水号为:"+ streamNo +"\t");
            }else if ("2".equals(status)){
                logContent.append("\t 新增下单流水记录成功 流水号为:"+ streamNo +"\t");
            }
//            else if ("1".equals(status)){
//                logContent.append("\t 新增充值流水记录成功 流水号为:"+ streamNo +"\t");
//            }

        }else{
            logContent.append("\t 新增流水记录失败 \t");
        }

    }

}
