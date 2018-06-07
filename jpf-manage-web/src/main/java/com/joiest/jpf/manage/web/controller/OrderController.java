package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.util.BigDecimalCalculateUtils;
import com.joiest.jpf.dto.OrderRequest;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.facade.OrderServiceFacade;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.io.FileOutputStream;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceFacade orderServiceFacade;

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
}
