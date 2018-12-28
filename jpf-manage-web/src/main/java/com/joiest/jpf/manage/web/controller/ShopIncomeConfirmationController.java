package com.joiest.jpf.manage.web.controller;

import com.google.common.collect.Lists;
import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.custom.ShopIncomeConfirmationDetailResponse;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.exportExcel;
import com.joiest.jpf.dto.ShopOrderCouponConsumeDetailResponse;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.util.PoiModel;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 欣豆市场财务管理模块-确认收入模块Controller
 * @author zhouchaowei 
 */
@Controller
@RequestMapping("/shopIncomeConfirm")
public class ShopIncomeConfirmationController {
    
    private Logger logger = LoggerFactory.getLogger(ShopIncomeConfirmationController.class);
    
    @Autowired
    private ShopOrderServiceFacade shopOrderServiceFacade;

    @Autowired
    private ShopIncomeConfirmationServiceFacade shopIncomeConfirmationServiceFacade;
    
    /**
     * 跳转到页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "shopOrder/orderConfirmIncome";
    }

    /**
     * 初始化显示欣豆市场订单列表
     * @param request
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(GetShopOrderRequest request) {
        GetShopOrderResponse response= shopOrderServiceFacade.getList(request);
        Map<String, Object> map = new HashMap<>(2);
        map.put("total", response.getCount());
        map.put("rows", response.getList());
        return map;
    }

    /**
     * 跳转到收入确认订单详情页面
     * @param orderNo
     * @param modelMap
     * @return
     */
    @RequestMapping("/orderConfirmIncomeInfo")
    @ResponseBody
    public ModelAndView getOrderIncomeConfirmationDetail(String orderNo, ModelMap modelMap){
        ShopOrderInfo shopOrderInfo= shopOrderServiceFacade.getOne(orderNo);
        modelMap.addAttribute("shopOrderInfo", shopOrderInfo);
        return new ModelAndView("shopOrder/orderConfirmIncomeDetail", modelMap);
    }

    /**
     * 获取欣豆市场确认收入订单对应欣券消费详情列表接口
     * @param orderNo
     * @return
     */
    @RequestMapping("/orderConfirmIncomeCouponDetailList")
    @ResponseBody
    public Map<String, Object> productsList(String orderNo) {
        Map<String, Object> map = new HashMap<>(2);
        ShopOrderCouponConsumeDetailResponse response= shopIncomeConfirmationServiceFacade.getOrderIncomeConfirmationDetail(orderNo);
        if(response != null){
            map.put("total", response.getCount());
            map.put("rows", response.getList());
        }else{
            map.put("total", 0);
            map.put("rows", null);
        }
        return map;
    }

    /**
     * 导出详情数据
     * @param request
     * @param response
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void productsList(GetShopOrderRequest request, HttpServletResponse response) {
        Map<String,String> requestOrderStatusMap = new HashMap<>(4);
        requestOrderStatusMap.put("0","待支付");
        requestOrderStatusMap.put("1","已支付");
        requestOrderStatusMap.put("2","支付失败");
        requestOrderStatusMap.put("3","已取消");

        request.setOrderStatusParam(requestOrderStatusMap);
        try{
            List<PayShopOrderCustom> payShopOrderCustomList = shopIncomeConfirmationServiceFacade.getShopOrderList(request);
            exportDataToExcel(payShopOrderCustomList,response);
        }catch (Exception e){
            e.printStackTrace();
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"数据导出异常");
        }
    }

    /**
     * 导出到Excel方法(包含合并行的Excel导出)
     * @param payShopOrderCustomList
     * @param response
     */
    private void exportDataToExcel(List<PayShopOrderCustom> payShopOrderCustomList,HttpServletResponse response){
        List<Map<String, String>> list = new ArrayList();
        String payWay = "",status = "";
        for(int i = 0; i < payShopOrderCustomList.size(); i++){
            PayShopOrderCustom payShopOrderCustom = payShopOrderCustomList.get(i);
            List<ShopIncomeConfirmationDetailResponse> shopIncomeConfirmationDetailResponseList = payShopOrderCustom.getList();
            if(!CollectionUtils.isEmpty(shopIncomeConfirmationDetailResponseList)){
                for(ShopIncomeConfirmationDetailResponse shopIncomeConfirmationDetailResponse : shopIncomeConfirmationDetailResponseList){
                    Map<String,String> map = new HashMap<>();
                    map.put("ID",payShopOrderCustomList.get(i).getId());
                    map.put("订单号",StringUtils.isBlank(payShopOrderCustomList.get(i).getOrderNo()) ? "" :payShopOrderCustomList.get(i).getOrderNo());
                    map.put("下单时间",payShopOrderCustomList.get(i).getAddtime() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(payShopOrderCustomList.get(i).getAddtime()));
                    map.put("商品名称",StringUtils.isBlank(payShopOrderCustomList.get(i).getProductName()) ? "" : payShopOrderCustomList.get(i).getProductName());
                    map.put("售价(元)",payShopOrderCustomList.get(i).getProductMoney() == null ? "" : payShopOrderCustomList.get(i).getProductMoney() + "");
                    map.put("购买数量",payShopOrderCustomList.get(i).getAmount() == null ? "" : String.valueOf(payShopOrderCustomList.get(i).getAmount()));
                    map.put("订单金额(元)",payShopOrderCustomList.get(i).getTotalMoney() == null ? "" : payShopOrderCustomList.get(i).getTotalMoney() + "");
                    if(payShopOrderCustomList.get(i).getPayWay() == 1){
                        payWay = "微信支付";
                    }else{
                        payWay = "欣豆支付";
                    }
                    map.put("支付方式",payWay);
                    if(payShopOrderCustomList.get(i).getStatus() == 0){
                        status = "待支付";
                    }else if(payShopOrderCustomList.get(i).getStatus() == 1){
                        status = "已支付";
                    }else if(payShopOrderCustomList.get(i).getStatus() == 2){
                        status = "支付失败";
                    }else if(payShopOrderCustomList.get(i).getStatus() == 3){
                        status = "已取消";
                    }else if(payShopOrderCustomList.get(i).getStatus() == 4){
                        status = "充值成功";
                    }else if(payShopOrderCustomList.get(i).getStatus() == 5){
                        status = "充值失败";
                    }
                    map.put("订单状态",status);

                    map.put("欣豆",shopIncomeConfirmationDetailResponse.getDou() == null ? "" : shopIncomeConfirmationDetailResponse.getDou() + "");
                    map.put("欣券号", StringUtils.isBlank(shopIncomeConfirmationDetailResponse.getCouponNo()) ? "" : shopIncomeConfirmationDetailResponse.getCouponNo());
                    map.put("欣券余额",shopIncomeConfirmationDetailResponse.getCouponBanlance() == null ? "" : shopIncomeConfirmationDetailResponse.getCouponBanlance() + "");
                    map.put("合同编号",StringUtils.isBlank(shopIncomeConfirmationDetailResponse.getContractNo()) ? "" : shopIncomeConfirmationDetailResponse.getContractNo());
                    map.put("商户端欣券订单号",StringUtils.isBlank(shopIncomeConfirmationDetailResponse.getMerchCouponOrderNo()) ? "" : shopIncomeConfirmationDetailResponse.getMerchCouponOrderNo());
                    map.put("商户端欣券订单内余额",shopIncomeConfirmationDetailResponse.getMerchCouponOrderBanlance() == null ? "" : shopIncomeConfirmationDetailResponse.getMerchCouponOrderBanlance() + "");
                    map.put("公司",StringUtils.isBlank(shopIncomeConfirmationDetailResponse.getCompanyName()) ? "" : shopIncomeConfirmationDetailResponse.getCompanyName());
                    map.put("费率(%)",shopIncomeConfirmationDetailResponse.getRate() == null ? "" : shopIncomeConfirmationDetailResponse.getRate() + "");

                    list.add(map);
                }
            }
        }
        Map<String, List<Map<String,String>>> map = new HashMap<>();
        map.put("sheet1", list);
        createExcel(map,response);
    }

    /**
     * 创建Excel方法(包含合并行的Excel导出)
     * @param maps 内容集合
     * @param response 响应
     * @return
     */
    private static JSONObject createExcel(Map<String, List<Map<String, String>>> maps, HttpServletResponse response){
        String [] title = {"ID","订单号","下单时间","商品名称","售价(元)","购买数量","订单金额(元)","支付方式","订单状态",
                "欣豆","欣券号","欣券余额","合同编号","商户端欣券订单号","商户端欣券订单内余额","公司","费率(%)"};
        int [] mergeIndex = new int[]{0,1,2,3,4,5,6,7,8};
        if (title.length==0){
            return null;
        }
        /*初始化excel模板*/
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = null;
        int n = 0;
        /*循环sheet页*/
        for(Map.Entry<String, List<Map<String/*对应title的值*/, String>>> entry : maps.entrySet()){
            /*实例化sheet对象并且设置sheet名称，book对象*/
            try {
                sheet = workbook.createSheet();
                workbook.setSheetName(n, entry.getKey());
                workbook.setSelectedTab(0);
            }catch (Exception e){
                e.printStackTrace();
            }
            /*初始化head，填值标题行（第一行）*/
            Row row0 = sheet.createRow(0);
            for(int i = 0; i<title.length; i++){
                /*创建单元格，指定类型*/
                Cell cell_1 = row0.createCell(i, Cell.CELL_TYPE_STRING);
                cell_1.setCellValue(title[i]);
            }
            /*得到当前sheet下的数据集合*/
            List<Map<String, String>> list = entry.getValue();
            /*遍历该数据集合*/
            List<PoiModel> poiModels = Lists.newArrayList();
            if(null!=workbook){
                Iterator iterator = list.iterator();
                /* 这里1是从excel的第二行开始，第一行已经塞入标题了 */
                int index = 1;
                while (iterator.hasNext()){
                    Row row = sheet.createRow(index);
                    /*取得当前这行的map，该map中以key，value的形式存着这一行值*/
                    Map<String, String> map = (Map<String, String>)iterator.next();
                    /*循环列数，给当前行塞值*/
                    for(int i = 0; i<title.length; i++){
                        String old = "";
                        /*old存的是上一行统一位置的单元的值，第一行是最上一行了，所以从第二行开始记*/
                        if(index > 1){
                            old = poiModels.get(i)==null?"":poiModels.get(i).getContent();
                        }
                        /*循环需要合并的列*/
                        for(int j = 0; j < mergeIndex.length; j++){
                            if(index == 1){
                                /*记录第一行的开始行和开始列*/
                                PoiModel poiModel = new PoiModel();
                                poiModel.setOldContent(map.get(title[i]));
                                poiModel.setContent(map.get(title[i]));
                                poiModel.setRowIndex(1);
                                poiModel.setCellIndex(i);
                                poiModels.add(poiModel);
                                break;
                            }else if(i > 0 && mergeIndex[j] == i){
                                /* 这边i>0也是因为第一列已经是最前一列了，只能从第二列开始 */
                                /* 当前同一列的内容与上一行同一列不同时，把那以上的合并, 或者在当前元素一样的情况下，前一列的元素并不一样，这种情况也合并 */
                                /* 如果不需要考虑当前行与上一行内容相同，但是它们的前一列内容不一样则不合并的情况，把下面条件中||poiModels.get(i).getContent().equals(map.get(title[i])) && !poiModels.get(i - 1).getOldContent().equals(map.get(title[i-1]))去掉就行*/
                                if(!poiModels.get(i).getContent().equals(map.get(title[i])) || poiModels.get(i).getContent().equals(map.get(title[i])) && !poiModels.get(0).getOldContent().equals(map.get(title[0]))){
                                    if(poiModels.get(i).getRowIndex() == (index - 1)){
                                        /*重新记录该列的内容为当前内容，行标记改为当前行标记，列标记则为当前列*/
                                        poiModels.get(i).setContent(map.get(title[i]));
                                        poiModels.get(i).setRowIndex(index);
                                        poiModels.get(i).setCellIndex(i);
                                    }else{
                                        /*当前行的当前列与上一行的当前列的内容不一致时，则把当前行以上的合并*/
                                        CellRangeAddress cra=new CellRangeAddress(poiModels.get(i).getRowIndex(), index - 1, poiModels.get(i).getCellIndex(), poiModels.get(i).getCellIndex());
                                        //在sheet里增加合并单元格
                                        sheet.addMergedRegion(cra);
                                        /*重新记录该列的内容为当前内容，行标记改为当前行标记，列标记则为当前列*/
                                        poiModels.get(i).setContent(map.get(title[i]));
                                        poiModels.get(i).setRowIndex(index);
                                        poiModels.get(i).setCellIndex(i);
                                    }
                                }
                            }
                            /*处理第一列的情况*/
                            if(mergeIndex[j] == i && i == 0 && !poiModels.get(i).getContent().equals(map.get(title[i]))){
                                if(poiModels.get(i).getRowIndex() == (index - 1)){
                                    /*重新记录该列的内容为当前内容，行标记改为当前行标记*/
                                    poiModels.get(i).setContent(map.get(title[i]));
                                    poiModels.get(i).setRowIndex(index);
                                    poiModels.get(i).setCellIndex(i);
                                }else{
                                    /*当前行的当前列与上一行的当前列的内容不一致时，则把当前行以上的合并*/
                                    CellRangeAddress cra=new CellRangeAddress(poiModels.get(i).getRowIndex(), index - 1, poiModels.get(i).getCellIndex(), poiModels.get(i).getCellIndex());
                                    //在sheet里增加合并单元格
                                    sheet.addMergedRegion(cra);
                                    /*重新记录该列的内容为当前内容，行标记改为当前行标记*/
                                    poiModels.get(i).setContent(map.get(title[i]));
                                    poiModels.get(i).setRowIndex(index);
                                    poiModels.get(i).setCellIndex(i);
                                }
                            }

                            /*最后一行没有后续的行与之比较，所有当到最后一行时则直接合并对应列的相同内容*/
                            if(mergeIndex[j] == i && index == list.size()){
                                if(poiModels.get(i).getRowIndex() != index){
                                    CellRangeAddress cra=new CellRangeAddress(poiModels.get(i).getRowIndex(), index, poiModels.get(i).getCellIndex(), poiModels.get(i).getCellIndex());
                                    //在sheet里增加合并单元格
                                    sheet.addMergedRegion(cra);
                                }
                            }
                        }
                        Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
                        cell.setCellValue(map.get(title[i]));
                        /*在每一个单元格处理完成后，把这个单元格内容设置为old内容*/
                        poiModels.get(i).setOldContent(old);
                    }
                    index++;
                }
            }
            n++;
        }
        JSONObject res = new JSONObject();
        res.put("code","10000");
        res.put("info","SUCCESS");
        exportExcel.writeIntoExcel("欣豆市场确认收入欣券详情-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx",response,workbook,"",1,res);
        return res;
    }
}
