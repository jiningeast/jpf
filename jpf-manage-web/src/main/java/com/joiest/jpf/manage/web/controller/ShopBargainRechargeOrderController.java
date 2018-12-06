package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.exportExcel;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderResponse;
import com.joiest.jpf.entity.ShopBargainRechargeOrderInfo;
import com.joiest.jpf.facade.ShopBargainRechargeOrderServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("shopBargainRechargeOrder")
public class ShopBargainRechargeOrderController {

    @Autowired
    private ShopBargainRechargeOrderServiceFacade shopBargainRechargeOrderServiceFacade;

    @RequestMapping("index")
    public String index(){
        return "shopBargainRechargeOrder/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(GetShopBargainRechargeOrderRequest request){
        GetShopBargainRechargeOrderResponse response = shopBargainRechargeOrderServiceFacade.getRecords(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return map;
    }

    /**
     * 敬恒充值订单管理-Excel导出
     * @param request
     * @param response
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(GetShopBargainRechargeOrderRequest request, HttpServletResponse response){
        Map<String,String> requestInfoStatusMap = new HashMap<>(2);
        requestInfoStatusMap.put("1","未绑定");
        requestInfoStatusMap.put("2","已绑定");

        request.setInfoStatusMap(requestInfoStatusMap);
        request.setPage(0);
        request.setRows(0);
        
        GetShopBargainRechargeOrderResponse shopBargainRechargeOrderResponse = shopBargainRechargeOrderServiceFacade.getRecords(request);
        List<ShopBargainRechargeOrderInfo> list = shopBargainRechargeOrderResponse.getList();
        if(list == null || list.isEmpty()){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到记录");
        }
        try {
            JSONObject jsonObject = exportExcelByInfoNew(response,list, 1, "");
        } catch (Exception e) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "数据导出异常");
        }
    }

    /**
     * 导出敬恒充值订单数据格式到Excel文件
     * @param response 响应头
     * @param data 数据
     * @param type 1 下载  2 生成文件
     * @param path
     * @return
     */
    private JSONObject exportExcelByInfoNew(HttpServletResponse response, List<ShopBargainRechargeOrderInfo> data, int type, String path){
        type = type < 1 ? 1 : type;
        JSONObject res = new JSONObject();
        res.put("code","10000");
        res.put("info","SUCCESS");
        SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook();
        String boid = "",orderType = "",price = "",facePrice = "",amt = "",amount = "",addTime = "",infoStatus = "";
        for (int i = 0; i < 1; i++) {
            Sheet sheet = xssfWorkbook.getSheet("sheet" + (i + 1));
            if (sheet == null) {
                sheet = xssfWorkbook.createSheet("sheet" + (i + 1));
            }
            // 生成标题
            Map<Integer, Object> firstTitles = new HashMap<>();
            firstTitles.put(0, "转让订单id");
            firstTitles.put(1, "转让订单号");
            firstTitles.put(2, "订单类型");
            firstTitles.put(3, "敬恒订单号");
            firstTitles.put(4, "商品名称");
            firstTitles.put(5, "单价");
            firstTitles.put(6, "面值");
            firstTitles.put(7, "数量");
            firstTitles.put(8, "订单总金额");
            firstTitles.put(9, "充值号");
            firstTitles.put(10, "拉取时间");
            firstTitles.put(11, "绑定状态");
            firstTitles.put(12, "敬恒返回信息");
            exportExcel.genSheetHead(sheet, 0, firstTitles);
            for (int rownum = 1; rownum <= data.size(); rownum++) {
                Row row = sheet.createRow(rownum);
                int k = -1;
                if(data.get(rownum - 1).getBoid() == null){
                    boid = "";
                }else{
                    boid = String.valueOf(data.get(rownum-1).getBoid());
                }
                exportExcel.createCell(row, ++k, boid);
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getOrderNo());
                if(data.get(rownum - 1).getOrderType() == null){
                    orderType = "";
                }else if(data.get(rownum - 1).getOrderType() == 1){
                    orderType = "中国石化";
                }else if(data.get(rownum - 1).getOrderType() == 2){
                    orderType = "中国石油";
                }else if(data.get(rownum - 1).getOrderType() == 3){
                    orderType = "话费充值";
                }
                exportExcel.createCell(row, ++k, orderType);
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getForeignOrderNo());
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getItemName());
                if(data.get(rownum - 1).getPrice() == null){
                    price = "";
                }else{
                    price = String.valueOf(data.get(rownum-1).getPrice());
                }
                exportExcel.createCell(row, ++k, price);
                if(data.get(rownum - 1).getFacePrice() == null){
                    facePrice = "";
                }else{
                    facePrice = String.valueOf(data.get(rownum-1).getFacePrice());
                }
                exportExcel.createCell(row, ++k, facePrice);
                if(data.get(rownum - 1).getAmt() == null){
                    amt = "";
                }else{
                    amt = String.valueOf(data.get(rownum-1).getAmt());
                }
                exportExcel.createCell(row, ++k, amt);
                if(data.get(rownum - 1).getAmount() == null){
                    amount = "";
                }else{
                    amount = String.valueOf(data.get(rownum-1).getAmount());
                }
                exportExcel.createCell(row, ++k, amount);
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getChargeNo());
                if (data.get(rownum - 1).getAddtime() == null) {
                    addTime = "";
                } else {
                    addTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(rownum - 1).getAddtime());
                }
                exportExcel.createCell(row, ++k, addTime);
                if (data.get(rownum - 1).getInfoStatus() == null) {
                    infoStatus = "";
                } else if(data.get(rownum - 1).getInfoStatus() == 1){
                    infoStatus = "未绑定";
                } else if(data.get(rownum - 1).getInfoStatus() == 2){
                    infoStatus = "已绑定";
                }
                exportExcel.createCell(row, ++k, infoStatus);
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getModule());
            }
        }
        String fileName = "敬恒充值订单列表-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
        return exportExcel.writeIntoExcel(fileName, response, xssfWorkbook, path, type, res);
    }
}
