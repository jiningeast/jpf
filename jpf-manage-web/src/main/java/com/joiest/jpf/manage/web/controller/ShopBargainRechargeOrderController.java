package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopBargainRechargeOrder;
import com.joiest.jpf.common.util.exportExcel;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRechargeOrderResponse;
import com.joiest.jpf.facade.ShopBargainRechargeOrderServiceFacade;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("shopBargainRechargeOrder")
public class ShopBargainRechargeOrderController {

    private Logger logger = LoggerFactory.getLogger(ShopBargainRechargeOrderController.class);
    
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
        long startProgramTime = System.currentTimeMillis();
        Map<String,String> requestInfoStatusMap = new HashMap<>(2);
        requestInfoStatusMap.put("1","未绑定");
        requestInfoStatusMap.put("2","已绑定");

        request.setInfoStatusMap(requestInfoStatusMap);
        long startQueryTime = System.currentTimeMillis();
        List<PayShopBargainRechargeOrder> recordsExcel = shopBargainRechargeOrderServiceFacade.getRecordsExcel(request);
        logger.info("敬恒订单数据查询数据时间:{} 秒",(System.currentTimeMillis() - startQueryTime) / 1000);
        try {
            long startExportTime = System.currentTimeMillis();
            JSONObject jsonObject = exportExcelByInfoNew(response,recordsExcel, 1, "");
            logger.info("敬恒订单数据Excel导出时间:{} 秒",(System.currentTimeMillis() - startExportTime) / 1000);
            logger.info("敬恒订单数据Excel导出程序执行时间:{} 秒",(System.currentTimeMillis() - startProgramTime) / 1000);
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
    private JSONObject exportExcelByInfoNew(HttpServletResponse response, List<PayShopBargainRechargeOrder> data, int type, String path){
        type = type < 1 ? 1 : type;
        JSONObject res = new JSONObject();
        res.put("code","10000");
        res.put("info","SUCCESS");
        SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook();
        String orderType = "", infoStatus = "";
        SXSSFSheet sheet = xssfWorkbook.getSheet("sheet1");
        if (sheet == null) {
            sheet = xssfWorkbook.createSheet("sheet1");
        }
        exportExcel.genSheetHead(sheet, 0, generateTitle());
        if(!CollectionUtils.isEmpty(data)){
            for (int rownum = 1; rownum <= data.size(); rownum++) {
                SXSSFRow row = sheet.createRow(rownum);
                int k = -1;
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getPullOrderNo());
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
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getPrice() == null ? "" : String.valueOf(data.get(rownum-1).getPrice()));
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getFacePrice() == null ? "" : String.valueOf(data.get(rownum-1).getFacePrice()));
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getAmt() == null ? "" : String.valueOf(data.get(rownum-1).getAmt()));
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getAmount() == null ? "" : String.valueOf(data.get(rownum-1).getAmount()));
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getChargeNo());
                exportExcel.createCell(row, ++k, data.get(rownum - 1).getAddtime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(rownum - 1).getAddtime()));
                if (data.get(rownum - 1).getInfoStatus() == null) {
                    infoStatus = "";
                } else if(data.get(rownum - 1).getInfoStatus() == 1){
                    infoStatus = "未绑定";
                } else if(data.get(rownum - 1).getInfoStatus() == 2){
                    infoStatus = "已绑定";
                }
                exportExcel.createCell(row, ++k, infoStatus);
            }
        }
        String fileName = "敬恒充值订单列表-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx";
        return exportExcel.writeIntoExcel(fileName, response, xssfWorkbook, path, type, res);
    }

    /**
     * 定义标题
     * @return
     */
    private Map<Integer, Object> generateTitle(){
        Map<Integer, Object> firstTitles = new HashMap<>(13);
        firstTitles.put(0, "转让订单号");
        firstTitles.put(1, "订单类型");
        firstTitles.put(2, "敬恒订单号");
        firstTitles.put(3, "商品名称");
        firstTitles.put(4, "单价");
        firstTitles.put(5, "面值");
        firstTitles.put(6, "数量");
        firstTitles.put(7, "订单总金额");
        firstTitles.put(8, "充值号");
        firstTitles.put(9, "拉取时间");
        firstTitles.put(10, "匹配状态");
        return firstTitles;
    }
}
