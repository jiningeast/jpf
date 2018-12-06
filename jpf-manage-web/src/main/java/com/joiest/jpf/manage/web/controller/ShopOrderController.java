package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.exportExcel;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.facade.ShopOrderServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/shopOrder")
public class ShopOrderController {

    @Autowired
    private ShopOrderServiceFacade shopOrderServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "shopOrder/List";
    }

    /**
     * 服务市场公司列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopOrderRequest request) {
        GetShopOrderResponse response= shopOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>();
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 客户详情页面
     */
    @RequestMapping("/orderInfo")
    @ResponseBody
    public ModelAndView orderInfo(String orderNo,ModelMap modelMap){
      //根据订单号查出订单详细信息
        ShopOrderInfo  shopOrderInfo= shopOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopOrderInfo", shopOrderInfo);
        return new ModelAndView("shopOrder/orderDetail", modelMap);

    }

    /**
     * 欣豆市场订单管理-Excel导出
     * @param request
     * @param response
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(GetShopOrderRequest request, HttpServletResponse response){
        Map<String,String> requestOrderSourceMap = new HashMap<>(2);
        requestOrderSourceMap.put("0","自平台");
        requestOrderSourceMap.put("1","敬恒");
        
        Map<String,String> requestOrderStatusMap = new HashMap<>(4);
        requestOrderStatusMap.put("0","待支付");
        requestOrderStatusMap.put("1","已支付");
        requestOrderStatusMap.put("2","支付失败");
        requestOrderStatusMap.put("3","已取消");

        request.setSourceParam(requestOrderSourceMap);
        request.setOrderStatusParam(requestOrderStatusMap);
        request.setPage(0);
        request.setRows(0);
        if(StringUtils.isBlank(request.getAddtimeEnd()) || StringUtils.isBlank(request.getAddtimeStart())){
            request.setAddtimeStart(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DateUtils.getBeforeDayTimeReturnDate(1)));
            request.setAddtimeEnd(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        GetShopOrderResponse shopOrderResponse= shopOrderServiceFacade.getList(request);
        if(shopOrderResponse.getList() == null || shopOrderResponse.getList().isEmpty()){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到记录");
        }
        try {
            JSONObject jsonObject = exportExcelByInfoNew(response, shopOrderResponse.getList(), 1, "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"数据导出异常");
        }
    }

    /**
     * 导出欣豆市场数据格式到Excel文件
     * @param response 响应头
     * @param data 数据
     * @param type 1 下载  2 生成文件
     * @param path
     * @return
     */
    private JSONObject exportExcelByInfoNew(HttpServletResponse response, List<ShopOrderInfo> data, int type, String path) throws UnsupportedEncodingException {
        type = type < 1 ? 1 : type;
        JSONObject res = new JSONObject();
        res.put("code","10000");
        res.put("info","SUCCESS");
        SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook();
        String amount = "" ,totalMoney = "",totalDou = "" ,addTime = "",payTime = "",status = "",interfaceType = "",source = "";
        for (int i = 0; i < 1; i++) {
            Sheet sheet = xssfWorkbook.getSheet("sheet" + (i + 1));
            if (sheet == null) {
                sheet = xssfWorkbook.createSheet("sheet" + (i + 1));
            }
            // 生成标题
            Map<Integer, Object> firstTitles = new HashMap<>();
            firstTitles.put(0, "订单号");
            firstTitles.put(1, "产品名称");
            firstTitles.put(2, "产品类型");
            firstTitles.put(3, "数量");
            firstTitles.put(4, "总金额");
            firstTitles.put(5, "总欣豆");
            firstTitles.put(6, "微信昵称");
            firstTitles.put(7, "下单时间");
            firstTitles.put(8, "支付时间");
            firstTitles.put(9, "状态");
            firstTitles.put(10, "供应商");
            firstTitles.put(11, "订单来源");
            exportExcel.genSheetHead(sheet, 0, firstTitles);
            for (int rownum = 1; rownum <= data.size(); rownum++) {
                Row row = sheet.createRow(rownum);
                int k = -1;
                exportExcel.createCell(row, ++k, data.get(rownum-1).getOrderNo());
                exportExcel.createCell(row, ++k, data.get(rownum-1).getProductName());
                exportExcel.createCell(row, ++k, data.get(rownum-1).getTypeName());
                if(data.get(rownum-1).getAmount() == null){
                    amount = "";
                }else{
                    amount = String.valueOf(data.get(rownum-1).getAmount());
                }
                exportExcel.createCell(row, ++k, amount);
                if(data.get(rownum-1).getTotalMoney() == null){
                    totalMoney = "";
                }else{
                    totalMoney = String.valueOf(data.get(rownum-1).getTotalMoney());
                }
                exportExcel.createCell(row, ++k, totalMoney);
                if(data.get(rownum-1).getTotalDou() == null){
                    totalDou = "";
                }else{
                    totalDou = String.valueOf(data.get(rownum-1).getTotalDou());
                }
                exportExcel.createCell(row, ++k, totalDou);
                exportExcel.createCell(row, ++k, URLDecoder.decode(data.get(rownum-1).getCustomerName(),"UTF-8"));
                if(data.get(rownum-1).getAddtime() == null){
                    addTime = "";
                }else{
                    addTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(rownum-1).getAddtime());
                }
                exportExcel.createCell(row, ++k, addTime);
                if(data.get(rownum-1).getPaytime() == null){
                    payTime = "";
                }else{
                    payTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(rownum-1).getPaytime());
                }
                exportExcel.createCell(row, ++k, payTime);
                if(data.get(rownum-1).getStatus() == null){
                    status = "";
                }else if(data.get(rownum-1).getStatus() == 0){
                    status = "待支付";
                }else if(data.get(rownum-1).getStatus() == 1){
                    status = "已支付";
                }else if(data.get(rownum-1).getStatus() == 2){
                    status = "支付失败";
                }else if(data.get(rownum-1).getStatus() == 3){
                    status = "已取消";
                }else if(data.get(rownum-1).getStatus() == 4){
                    status = "充值成功";
                }else if(data.get(rownum-1).getStatus() == 5){
                    status = "充值失败";
                }
                exportExcel.createCell(row, ++k, status);
                if(data.get(rownum-1).getInterfaceType() == null){
                    interfaceType = "";
                }else if(data.get(rownum-1).getInterfaceType() == 0){
                    interfaceType = "欧飞";
                }else if(data.get(rownum-1).getInterfaceType() == 1){
                    interfaceType = "威能";
                }
                exportExcel.createCell(row, ++k, interfaceType);
                if(data.get(rownum-1).getSource() == null){
                    source = "";
                }else if(data.get(rownum-1).getSource() == 0){
                    source = "自平台";
                }else if(data.get(rownum-1).getSource() == 1){
                    source = "敬恒";
                }
                exportExcel.createCell(row, ++k, source);
            }
        }
        String fileName = "欣豆市场订单列表-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
        return exportExcel.writeIntoExcel(fileName, response, xssfWorkbook, path, type, res);
    }
}
