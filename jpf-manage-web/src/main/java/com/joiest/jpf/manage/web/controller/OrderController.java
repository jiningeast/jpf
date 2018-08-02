package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.util.BigDecimalCalculateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileOutputStream;

import static com.joiest.jpf.manage.web.constant.ManageConstants.PAY_DETAIL;
import static com.joiest.jpf.manage.web.constant.ManageConstants.REFUND_STATUS;
import static com.joiest.jpf.manage.web.constant.ManageConstants.USER_OPERATE_STATUS;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceFacade orderServiceFacade;

    //支付订单信息
    @Autowired
    private OrderYinjiaApiServiceFacade orderYinjiaApiServiceFacade;

    //获取签约信息
    @Autowired
    private OrderCpServiceFacade orderCpServiceFacade;

    //费率详情
    @Autowired
    private OrdersMoneyServiceFacade ordersMoneyServiceFacade;

    //支付回调给商户的流水信息
    @Autowired
    private OrderPayMerMessageServiceFacade orderPayMerMessageServiceFacade;

    //支付回调流水信息
    @Autowired
    private OrderPayMessageServiceFacade orderPayMessageServiceFacade;

    //获取商户信息
    @Autowired
    private MerchantServiceFacade merchantServiceFacade;


    @RequestMapping("/index")
    public String index(){
        return "order/orderList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(OrderRequest orderRequest){
        OrderResponse orderResponse = orderServiceFacade.getOrders(orderRequest);
        Map<String, Object> map = new HashMap<>();
        map.put("total", orderResponse.getCount());
        map.put("rows", orderResponse.getList());
        map.put("request", orderRequest);

        // 统计汇总
        BigDecimal allOrderMoney = orderResponse.getAllOrdersMoney();
        BigDecimal allRefundMoney = orderResponse.getAllRefundMoney();
        map.put("allOrdersCount", orderResponse.getAllOrdersCount());
        map.put("allOrdersMoney", allOrderMoney);
        map.put("allRefundMoney", allRefundMoney);
        map.put("profitMoney", BigDecimalCalculateUtils.sub(allOrderMoney.doubleValue(), allRefundMoney.doubleValue()) );

        return map;
    }

    @RequestMapping("/imprtExcel")
    @ResponseBody
    public Map<String,Object> imprtExcel(OrderRequest orderRequest,HttpServletResponse response){

        OrderResponse orderResponse = orderServiceFacade.getOrders(orderRequest);

       /* Map<String, Object> map = new HashMap<>();
        map.put("total", orderResponse.getCount());
        map.put("rows", orderResponse.getList());
        map.put("request", orderRequest);

        // 统计汇总
         BigDecimal allOrderMoney = orderResponse.getAllOrdersMoney();
        BigDecimal allRefundMoney = orderResponse.getAllRefundMoney();
        map.put("allOrdersCount", orderResponse.getAllOrdersCount());
        map.put("allOrdersMoney", allOrderMoney);
        map.put("allRefundMoney", allRefundMoney);
        map.put("profitMoney", BigDecimalCalculateUtils.sub(allOrderMoney.doubleValue(), allRefundMoney.doubleValue()) );
        */
        //OrderInfo orderInfo = new OrderInfo();
        //定义表头
        String[] title={"订单号","商户编号","企业名称","产品编号","产品名称","支付方式","订单金额","支付状态","退单状态","下单时间",
                "用户名","手机号"};

        //创建excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建工作表sheet
        HSSFSheet sheet=workbook.createSheet();
        //创建第一行
        HSSFRow row=sheet.createRow(0);
        HSSFCell cell=null;
        //插入第一行数据的表头
        for(int i=0;i<title.length;i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
        int j=1;
        for(OrderInfo order:orderResponse.getList()){

            HSSFRow nrow=sheet.createRow(j);

            HSSFCell ncell=nrow.createCell(0);
            ncell.setCellValue(order.getOrderid());

            ncell=nrow.createCell(1);
            ncell.setCellValue(order.getMtsid());

            ncell=nrow.createCell(2);
            ncell.setCellValue(order.getCompanyname());

            ncell=nrow.createCell(3);
            ncell.setCellValue(order.getPid());

            ncell=nrow.createCell(4);
            ncell.setCellValue(order.getPname());

            //------支付方式start
                ncell=nrow.createCell(5);
                String paytype_cn = "";
                if(order.getPaytype()==7){

                    paytype_cn = "银联信用卡分期支付";
                }else if(order.getPaytype()==8){

                    paytype_cn ="花呗分期支付";
                }else if(order.getPaytype()==9){

                    paytype_cn ="微信全额支付";
                }else{
                }
                ncell.setCellValue(paytype_cn);
            //------支付方式end

            ncell=nrow.createCell(6);
            ncell.setCellValue(order.getOrderprice().toString());

            //------支付状态end
                String payStatus = "未支付";
                ncell=nrow.createCell(7);
                if ( order.getOrderstatus() == 0 ){
                    payStatus =  "未支付";
                }else if ( order.getOrderstatus()  == 1 ){
                    payStatus = "支付成功";
                }else if ( order.getOrderstatus()  == 2 ){
                    payStatus = "支付失败";
                }
                ncell.setCellValue(payStatus);
            //------支付状态end

            //------退单状态start
                String refundStatus = "未支付";
                ncell=nrow.createCell(8);
                if ( order.getSinglestatus() == 1 ){
                    refundStatus = "正常订单";
                }else if ( order.getSinglestatus() == 2 ){
                    refundStatus = "用户申请退单";
                }else if ( order.getSinglestatus() == 3 ){
                    refundStatus = "用户撤销退单";
                }else if ( order.getSinglestatus() == 4 ){
                    refundStatus = "运营已审核,待财务审核";
                }else if ( order.getSinglestatus() == 5 ){
                    refundStatus = "财务已审核，退款中";
                }else if ( order.getSinglestatus() == 6 ){
                    refundStatus = "审核驳回";
                }else if ( order.getSinglestatus() == 7 ){
                    refundStatus = "退款成功";
                }else if ( order.getSinglestatus() == 8 ){
                    refundStatus = "退款失败";
                }
                ncell.setCellValue(refundStatus);
            //------退单状态end

            //------添加日期start
            ncell=nrow.createCell(9);
            if(order.getAddtime()!=null){

                DateFormat  addDate= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                ncell.setCellValue(addDate.format(order.getAddtime()));
            }else{
                ncell.setCellValue("");
            }
            //------添加日期end

            //获取通道订单信息
            OrderYinjiaApiInfo orderYinjiaApiInfo = orderYinjiaApiServiceFacade.getOrderByForeignOrderid(order.getOrderid());
            if(orderYinjiaApiInfo != null && orderYinjiaApiInfo.getSignOrderid()!=null){

                //System.out.println(orderYinjiaApiInfo.getSignOrderid().toString());
                //签约信息
                OrderCpInterfaceInfo orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpByorderid(orderYinjiaApiInfo.getSignOrderid().toString());
                //if(orderCpInterfaceInfo)
                ncell=nrow.createCell(10);
                ncell.setCellValue(orderCpInterfaceInfo.getSignedname());

                ncell=nrow.createCell(11);
                ncell.setCellValue(orderCpInterfaceInfo.getMobileno());
            }
            j++;
        }
        OutputStream output= null;
        try {

            response.reset();
            output = response.getOutputStream();

            String filename = "旅游分期订单";
            response.setHeader("Content-disposition", "attachment;filename="+new String(filename.getBytes("utf-8"), "iso8859-1")+".xls");
            //response.setHeader("Content-disposition", "attachment; filename=旅游分期订单.xls");
            response.setContentType("application/vnd.ms-excel");
            workbook.write(output);
            output.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
//        return null;
            //创建excel文件
            File file=new File("E://1234abc.xlsx");
            try {
                file.createNewFile();
                //将excel写入
                FileOutputStream stream= FileUtils.openOutputStream(file);
                workbook.write(stream);
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
    }
    /**
     * 获取订单支付的银行卡信息
     */
    @RequestMapping("paydetail")
    public ModelAndView getOrderDetail(String orderid, ModelMap modelMap)
    {
        //支付订单信息
        //OrderYinjiaApiInfo orderYinjiaApiInfo = orderYinjiaApiServiceFacade.getOrderYinjiaApiByOrderid(orderid);
        OrderYinjiaApiInfo orderYinjiaApiInfo = orderYinjiaApiServiceFacade.getOrderByForeignOrderid(orderid);

        //商品名称
        Map<String,String> paramMap = ToolUtils.urlToMap(orderYinjiaApiInfo.getForeignRequest());
        if ( paramMap.containsKey("productName") )
        {
            orderYinjiaApiInfo.setProductName(paramMap.get("productName"));
        }
        orderYinjiaApiInfo.setUserOperateStatus_cn(USER_OPERATE_STATUS.get(orderYinjiaApiInfo.getUserOperateStatus().toString()));
        orderYinjiaApiInfo.setRefundStatus_cn(REFUND_STATUS.get(orderYinjiaApiInfo.getRefundStatus().toString()));

        OrderCpInterfaceInfo orderCpInterfaceInfo = new OrderCpInterfaceInfo();
        StringBuilder sbf = new StringBuilder();
        if ( orderYinjiaApiInfo.getSignOrderid() != null )
        {
            if ( StringUtils.isNotBlank(orderYinjiaApiInfo.getPayDetail()) )
            {
                Map<String,String> payDetail_Map = JsonUtils.toCollection(orderYinjiaApiInfo.getPayDetail(), new TypeReference<Map<String, String>>() {});
                Set<String> keys = payDetail_Map.keySet();
                Iterator<String> it = keys.iterator();
                while ( it.hasNext() )
                {
                    String key = it.next();
                    if ( PAY_DETAIL.get(key) != null )
                    {
                        sbf.append( PAY_DETAIL.get(key) );
                        sbf.append(payDetail_Map.get(key));
                        sbf.append( ";<br/>" );
                    }
                }
            }
            //签约信息
            orderCpInterfaceInfo = orderCpServiceFacade.getOrderCpByorderid(orderYinjiaApiInfo.getSignOrderid().toString());
        }

        //订单费率详情
        OrdersMoneyInfo ordersMoneyInfo = ordersMoneyServiceFacade.getOrdersMoneyInfo(Long.parseLong(orderYinjiaApiInfo.getOrderid()));

        //支付回调信息
        //同步信息
        List<OrderPayMessageInfo> payMessagetReturnList = orderPayMessageServiceFacade.getOrderPayMessageReturnListByOrderId(orderYinjiaApiInfo.getOrderid());

        //支付回调信息
        List<OrderPayMessageInfo> payMessagetNotifyList = orderPayMessageServiceFacade.getOrderPayMessageNotifyListByOrderId(orderYinjiaApiInfo.getOrderid());

        //支付回调给商户的信息
        List<OrderPayMerMessageInfo> payMerMessageList = orderPayMerMessageServiceFacade.getOrderPayMerMessageListByOrderId(orderYinjiaApiInfo.getOrderid());

        //商户信息
        MerchantInfo merchantInfo = merchantServiceFacade.getMerchant(orderYinjiaApiInfo.getMtsid());

        modelMap.addAttribute("apiInfo", orderYinjiaApiInfo);
        modelMap.addAttribute("returnList", payMessagetReturnList);
        modelMap.addAttribute("notifyList", payMessagetNotifyList);
        modelMap.addAttribute("payMerMsgList", payMerMessageList);
        modelMap.addAttribute("orderCpInfo", orderCpInterfaceInfo);
        modelMap.addAttribute("merchantInfo", merchantInfo);
        modelMap.addAttribute("ordersMoneyInfo", ordersMoneyInfo);
        modelMap.put("payDetaiStr", sbf.toString());
        return new ModelAndView("order/orderdetail", modelMap);

    }
}
