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

        //定义表头
        String[] title={"订单号","签约号","商户编号","年龄"};

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
        //写入数据
        for (int i=1;i<=10;i++){
            HSSFRow nrow=sheet.createRow(i);
            HSSFCell ncell=nrow.createCell(0);
            ncell.setCellValue(""+i);
            ncell=nrow.createCell(1);
            ncell.setCellValue("user"+i);
            ncell=nrow.createCell(2);
            ncell.setCellValue("24");
        }
        //File file=new File("E://poi.xlsx");
        //直接获取输出，直接输出excel（优先使用）
       /* OutputStream output=response.getOutputStream();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename=test.xls");
        response.setContentType("application/msexcel");
        workbook.write(output);
        output.close();*/
        /*
        //创建excel文件
        File file=new File("E://poi.xlsx");
        try {
            file.createNewFile();
            //将excel写入
            FileOutputStream stream= FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return map;
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
