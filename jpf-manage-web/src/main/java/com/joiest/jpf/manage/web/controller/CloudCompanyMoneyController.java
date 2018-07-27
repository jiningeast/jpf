package com.joiest.jpf.manage.web.controller;


import com.joiest.jpf.dto.CloudCompanyMoneyRequest;
import com.joiest.jpf.dto.CloudCompanyMoneyResponse;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.dto.OrderResponse;
import com.joiest.jpf.entity.CloudCompanyMoneyInfo;
import com.joiest.jpf.facade.CloudCompanyMoneyServiceFacade;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cloudCompanyMoney")
public class CloudCompanyMoneyController {

    @Autowired
    private CloudCompanyMoneyServiceFacade cloudCompanyMoneyServiceFacade;

    @RequestMapping("/index")
    public String index(){
        return "cloudCompanyMoney/companyList";
    }

    /**
     * 批次管理页
     * */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(CloudCompanyMoneyRequest cloudCompanyMoneyRequest){
        CloudCompanyMoneyResponse cloudCompanyMoneyResponse = cloudCompanyMoneyServiceFacade.getRecords(cloudCompanyMoneyRequest);

        Map<String, Object> map = new HashMap<>();
        map.put("total", cloudCompanyMoneyResponse.getCount());
        map.put("rows", cloudCompanyMoneyResponse.getList());

        return map;
    }

    /*
     * 财务审核订单列表
     * */
    @RequestMapping("/caiwu/index")
    public String caiwuIndex(){
        return "cloudCompanyMoney/companyCaiwuList";
    }

    /**
     * 财务审核订单列表
     * */
    @RequestMapping("/caiwu/list")
    @ResponseBody
    public Map<String, Object> caiwuList(CloudCompanyMoneyRequest cloudCompanyMoneyRequest){

        CloudCompanyMoneyResponse cloudCompanyMoneyResponse = cloudCompanyMoneyServiceFacade.getCaiwuRecords(cloudCompanyMoneyRequest);

        Map<String, Object> map = new HashMap<>();
        map.put("total", cloudCompanyMoneyResponse.getCount());
        map.put("rows", cloudCompanyMoneyResponse.getList());
        // 统计汇总
        map.put("allCount", cloudCompanyMoneyResponse.getAllCount());
        BigDecimal allMoney = cloudCompanyMoneyResponse.getAllMoney();
        allMoney = allMoney == null ? new BigDecimal(0) : allMoney;
        map.put("allMoney", allMoney);
        BigDecimal allServiceMoney = cloudCompanyMoneyResponse.getAllServiceMoney();
        allServiceMoney = allServiceMoney == null ? new BigDecimal(0) : allServiceMoney;
        map.put("allServiceMoney", allServiceMoney);
        BigDecimal addedMoney = cloudCompanyMoneyResponse.getAddedMoney();
        addedMoney = addedMoney == null ? new BigDecimal(0) : addedMoney;
        map.put("addedMoney", addedMoney);
        BigDecimal addedMoneyPay = cloudCompanyMoneyResponse.getAddedMoneyPay();
        addedMoneyPay = addedMoneyPay == null ? new BigDecimal(0) : addedMoneyPay;
        map.put("addedMoneyPay", addedMoneyPay);
        BigDecimal totalGross = cloudCompanyMoneyResponse.getTotalGross();
        totalGross = totalGross == null ? new BigDecimal(0) : totalGross;
        map.put("totalGross", totalGross);
        return map;
    }

    /**
     * 财务列表导出
     * */
    @RequestMapping("/importExcelCaiwu")
    @ResponseBody
    public Map<String,Object> importExcelCaiwu(CloudCompanyMoneyRequest cloudCompanyMoneyRequest,HttpServletResponse response){

        CloudCompanyMoneyResponse cloudCompanyMoneyResponse = cloudCompanyMoneyServiceFacade.getCaiwuRecords(cloudCompanyMoneyRequest);

        //定义表头
        String[] title={"序号","合同编号","批次号","代理商户号","企业商户号","企业名称","总金额","总比数","服务费金额","增值税金额","增值税附加金额","毛利金额",
                "状态","创建时间","修改时间"};

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
        for(CloudCompanyMoneyInfo order:cloudCompanyMoneyResponse.getList()){

            HSSFRow nrow=sheet.createRow(j);

            HSSFCell ncell=nrow.createCell(0);
            ncell.setCellValue(order.getId());

            ncell=nrow.createCell(1);
            ncell.setCellValue(order.getFid());

            ncell=nrow.createCell(2);
            ncell.setCellValue(order.getBatchno());

            ncell=nrow.createCell(3);
            ncell.setCellValue(order.getAgentNo());

            ncell=nrow.createCell(4);
            ncell.setCellValue(order.getMerchNo());

            ncell=nrow.createCell(5);
            ncell.setCellValue(order.getName());

            ncell=nrow.createCell(6);
            ncell.setCellValue(order.getCommoney().doubleValue());

            ncell=nrow.createCell(7);
            ncell.setCellValue(order.getBatchitems());

            ncell=nrow.createCell(8);
            ncell.setCellValue(order.getFeemoney().doubleValue());

            ncell=nrow.createCell(9);
            ncell.setCellValue(order.getTaxmoney().doubleValue());

            ncell=nrow.createCell(10);
            ncell.setCellValue(order.getTaxmoremoney().doubleValue());

            ncell=nrow.createCell(11);
            ncell.setCellValue(order.getProfitmoney().doubleValue());

            //发放状态判断
            ncell=nrow.createCell(12);
            String paytype_cn = "";

            if(order.getMontype()==0){
                paytype_cn="待锁定";
            }else if (order.getMontype()==-1){
                paytype_cn="已删除";
            }else if(order.getMontype()==2){
                paytype_cn="处理完成";
            }else if(order.getMontype()==3){
                paytype_cn="处理完成吗(部分失败)";
            }else{
                paytype_cn="处理失败";
            }

            ncell.setCellValue(paytype_cn);
            //------添加日期start
            ncell=nrow.createCell(13);
            if(order.getAddtime()!=null){
                DateFormat addDate= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                ncell.setCellValue(addDate.format(order.getAddtime()));
            }else{
                ncell.setCellValue("");
            }

            ncell=nrow.createCell(14);

            if(order.getUpdatetime()!=null){
                DateFormat addDate= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                ncell.setCellValue(addDate.format(order.getUpdatetime()));
            }else{
                ncell.setCellValue("");
            }
            //------添加日期end

            j++;
        }
        OutputStream output= null;
        try {

            response.reset();
            output = response.getOutputStream();

            String filename = "云账户财务代付订单";
            response.setHeader("Content-disposition", "attachment;filename="+new String(filename.getBytes("gbk"), "iso8859-1")+".xls");
            response.setContentType("application/vnd.ms-excel");
            workbook.write(output);
            output.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;

    }

    /**
     * 查询公司页
     */
    @RequestMapping("/dfdetail/page")
    public ModelAndView dfdetailPage(String companyMoneyId, ModelMap modelMap){
        modelMap.addAttribute("companyMoneyId",companyMoneyId);
        return new ModelAndView("cloudCompanyMoney/companyDfdetail",modelMap);
    }

    /**
     * 代付明细
     */
    //@RequestMapping("/dfdetail")
    @RequestMapping(value = "/dfDetail", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String ,Object> dfDetail(String companyMoneyId){

        //GetCloudMoneyDfResponse getCloudMoneyDfResponse = cloudCompanyMoneyServiceFacade.getAllByfid(fid);
        GetCloudMoneyDfResponse getCloudMoneyDfResponse = cloudCompanyMoneyServiceFacade.getAllBycompanyMoneyId(companyMoneyId);
        Map<String,Object> responseMap = new HashMap<>();

        responseMap.put("total",getCloudMoneyDfResponse.getCount());
        responseMap.put("rows",getCloudMoneyDfResponse.getList());

        return responseMap;
    }

}
