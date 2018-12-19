package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.facade.ChargeCompanyMoneyStreamServiceFacade;
import com.joiest.jpf.facade.ChargeOrderServiceFacade;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author zhangmeng
 * @Create 2018/12/19
 * @Description
 */
@Controller("orderCompensation")
public class OrderCompensationController {

    @Autowired
    private ChargeOrderServiceFacade chargeOrderServiceFacade;

    @Autowired
    private ChargeCompanyMoneyStreamServiceFacade chargeCompanyMoneyStreamServiceFacade;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    public void orderCompensation(HttpServletRequest request, @Param("pageNo") String pageNum){
        String pageNoStr = request.getParameter("pageNo");
        Integer pageNo = Integer.valueOf(pageNoStr);

        Integer pageSize = 10000;


        //存储日志记录
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 30);
        //Date preHalfHours = calendar.getTime();
        StringBuilder logContent = new StringBuilder();

        String logPath = "/logs/jpf-charge-api/log/";
        String fileName = "OrderCompensation" + pageNum;
        logContent.append("\n\nTime: "+myfmt.format(date));


        List<PayChargeOrder> payChargeOrderList = chargeOrderServiceFacade.getOrdersByPage(pageNo, pageSize);

        for (PayChargeOrder order : payChargeOrderList){
            List<PayChargeCompanyMoneyStream> payChargeCompanyMoneyStreamList = chargeCompanyMoneyStreamServiceFacade.getChargeCompanyMoneyStreamByOrderId(Integer.valueOf(order.getId()));
            //判断订单状态是否为1或2
            if (order.getStatus() == 1 || order.getStatus() == 2){
               //有一条流水记录
               if (payChargeCompanyMoneyStreamList.size() == 1){
                   int result = order.getTotalMoney().compareTo(payChargeCompanyMoneyStreamList.get(0).getTotalMoney());
                    if ( result > 0){
                        //订单金额大于流水金额

                    }else if(result < 0){
                        //
                    }
               //无流水记录
               }else{

               }
               //订单状态为5
            }else if(order.getStatus() == 5){
                //有一条流水记录
                if (payChargeCompanyMoneyStreamList.size() == 1){

                //有两条流水记录
                }else if(payChargeCompanyMoneyStreamList.size() == 2){


                //无流水记录
                }else {

                }

            }
        }
    }

}
