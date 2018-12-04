package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.ExcelDealUtils;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamRequest;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamResponse;
import com.joiest.jpf.facade.ChargeCompanyMoneyStreamServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("chargeMoneyStream")
public class
ChargeCompanyMoneyStreamController {

    @Autowired
    private ChargeCompanyMoneyStreamServiceFacade chargeCompanyMoneyStreamServiceFacade;

    @RequestMapping("index")
    public String index(){
        return "chargeMoneyStream/index";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(ChargeCompanyMoneyStreamRequest request){
        Map<String, String> ChargeCompanyMoneyStreamStatusCn = new HashMap<>();
        ChargeCompanyMoneyStreamStatusCn.put("1","充值");
        ChargeCompanyMoneyStreamStatusCn.put("2","下单");
        ChargeCompanyMoneyStreamStatusCn.put("3","退款");

        request.setStatusCnArr(ChargeCompanyMoneyStreamStatusCn);
        ChargeCompanyMoneyStreamResponse response = chargeCompanyMoneyStreamServiceFacade.getRecords(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return  map;
    }

    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcel(ChargeCompanyMoneyStreamRequest request,HttpServletResponse response){
        Map<String, String> ChargeCompanyMoneyStreamStatusCn = new HashMap<>();
        ChargeCompanyMoneyStreamStatusCn.put("1","充值");
        ChargeCompanyMoneyStreamStatusCn.put("2","下单");
        ChargeCompanyMoneyStreamStatusCn.put("3","退款");

        request.setStatusCnArr(ChargeCompanyMoneyStreamStatusCn);
        request.setPage(0);
        request.setRows(0);
        ChargeCompanyMoneyStreamResponse streamResponse = chargeCompanyMoneyStreamServiceFacade.getRecords(request);
        if( streamResponse.getList() == null || streamResponse.getList().isEmpty() ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未匹配到记录");
        }

        String path = "";
        //如果有购买的卡密放到Excel
//        设置excel标题以及字段
        JSONArray titles = new JSONArray();
        titles.add("订单号");
        titles.add("商品");
        titles.add("代理商号");
        titles.add("收入金额|支出金额");
        titles.add("收支类型");
        titles.add("交易类型");
        titles.add("交易时间");
        titles.add("状态");

        JSONArray filed = new JSONArray();
        filed.add("orderNo");//卡号
        filed.add("productName");
        filed.add("merchNo");
        filed.add("productSalePrice");
        filed.add("streamReturn");
        filed.add("statusCn");
        filed.add("addtime");
        filed.add("isDelCn");
        try {
            JSONObject excel = new ExcelDealUtils().exportExcelByInfo(response, titles.toString(), filed.toString(), streamResponse.getList(), 1,path);
        } catch (Exception e) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "数据导出异常");
        }

    }


}
