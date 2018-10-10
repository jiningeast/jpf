package com.joiest.jpf.manage.web.controller;


import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudCompany;
import com.joiest.jpf.common.po.PayCloudCompanyMoney;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.CloudCompanyMoneyRequest;
import com.joiest.jpf.dto.CloudCompanyMoneyResponse;
import com.joiest.jpf.dto.CloudDfMoneyRequest;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.util.ServicePayUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
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
import java.util.*;

@Controller
@RequestMapping("/cloudCompanyMoney")
public class CloudCompanyMoneyController {

    @Autowired
    private CloudCompanyMoneyServiceFacade cloudCompanyMoneyServiceFacade;

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    @Autowired
    private CloudCompanyAgentServiceFacade cloudCompanyAgentServiceFacade;

    @Autowired
    private CloudCompanySalesServiceFacade cloudCompanySalesServiceFacade;

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;

    @Autowired
    private CloudDfMoneyFreezeServiceFacade cloudDfMoneyFreezeServiceFacade;

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
    public Map<String, Object> caiwuList(CloudCompanyMoneyRequest cloudCompanyMoneyRequest) {

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

    /**
     * 查询批次记录表 更新代付明细数据
     */
    @RequestMapping(value = "/searchWaitPay", produces = "application/json;charset=utf-8")
    @ResponseBody
    public void searchWaitPayRecords(String comMoneyId){

        //存储日志记录
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder logContent = new StringBuilder();

        String logPath = "/logs/jpf-manage-web/log/";
        String fileName = "dfMoneySearch";
        logContent.append("\n\nTime:" + myfmt.format(date));

        StringBuilder logCloudContent = new StringBuilder();

        //查询所有打款申请中的批次订单 Montype = 3
        CloudCompanyMoneyRequest companyMoneyRequest = new CloudCompanyMoneyRequest();
        companyMoneyRequest.setMontype((byte)3); //打款申请中
//        companyMoneyRequest.setId("205");
        if(comMoneyId != null ){
            companyMoneyRequest.setId(comMoneyId); //指定批次主键
        }

        //支付失败 扣减月代付总额
        List<String> orderStatus_successList = new ArrayList<String>(){
            {
                add("00");
                add("01");
                add("04");
                add("05");
            }
        };

        List<CloudCompanyMoneyInfo> companyMoneyInfoList = cloudCompanyMoneyServiceFacade.searchCompanyMoneyAll(companyMoneyRequest);
        if( companyMoneyInfoList.size() > 0 ){

            logContent.append("\n================================发起批次请求========================");
            LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

            for (int i = 0; i < companyMoneyInfoList.size() ; i++) {

                logContent = new StringBuilder(); //初始化日志变量

                String companyMoneyId = companyMoneyInfoList.get(i).getId();
                byte companyMoneyType = companyMoneyInfoList.get(i).getMontype(); //状态

                int batchAllItems = Integer.parseInt(companyMoneyInfoList.get(i).getBatchitems()); //总笔数
                BigDecimal batchDealMoney = companyMoneyInfoList.get(i).getBatchdealmoney();//代付成功总金额
                BigDecimal batchFailMoney = companyMoneyInfoList.get(i).getBatchfailmoney();//代付失败总金额
                int batchDealItems = Integer.parseInt(companyMoneyInfoList.get(i).getBatchdealitems());//代付成功总笔数
                int batchFailItems = Integer.parseInt(companyMoneyInfoList.get(i).getBatchfailitems());//代付失败总笔数

                logContent.append("\n==批次主键ID:===="+companyMoneyId);
                LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

                //查询代付打款中明细数据 Montype = 4
                CloudDfMoneyRequest cloudDfMoneyRequest = new CloudDfMoneyRequest();
                cloudDfMoneyRequest.setCompanyMoneyId(companyMoneyId);
                cloudDfMoneyRequest.setMontype(4); //打款中
                cloudDfMoneyRequest.setIsFreeze((byte)1);   //未冻结
                List<CloudDfMoneyInfo> dfRets = cloudDfMoneyServiceFacade.getAllBySective(cloudDfMoneyRequest);
                if( dfRets.size() > 0 ){

                    for (int m = 0; m < dfRets.size() ; m++) {

                        //查询公司账号信息
                        CloudCompanyInfo companyInfo = cloudCompanyServiceFacade.getRecById(dfRets.get(i).getFid());
                        if( companyInfo == null ){
                            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到公司信息");
                        }
                        String companyId = dfRets.get(i).getFid(); //公司ID
                        BigDecimal cloudMoney = companyInfo.getCloudmoney(); //账户金额
                        String cloudcode = companyInfo.getCloudcode(); //金额校验码
                        BigDecimal advanceMoney = companyInfo.getAdvanceMoney() != null ? companyInfo.getAdvanceMoney() : new BigDecimal("0.00");    //预付款
                        String advanceCode = companyInfo.getAdvanceCode();
                        BigDecimal freezeMoney = companyInfo.getFreezeMoney() != null ? companyInfo.getFreezeMoney() : new BigDecimal("0.00");      //冻结款
                        String freezeCode = companyInfo.getFreezeCode();


                        Boolean checkMoneyVerify = cloudCompanyServiceFacade.checkCompanyMoneyVerify(companyId);
                        if( !checkMoneyVerify ){
                            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "金额校验失败");
                        }

                        logCloudContent = new StringBuilder(); //初始化日志变量

                        CloudDfMoneyInfo dfMoneyInfo = dfRets.get(m);
                        String orderid = dfMoneyInfo.getOrderid(); //代付单号
                        Long dfId = dfMoneyInfo.getId();
                        Integer dfMoneyType = dfMoneyInfo.getMontype();
                        String dfBankNiceName = dfMoneyInfo.getBanknickname();
                        BigDecimal dfComMoney = dfMoneyInfo.getCommoney();

                        if( orderid.equals("") || orderid == null ){
                            logCloudContent.append("\n未提交代付数据：代付明细ID:"+dfId+"\t代付单号"+orderid+"\t收款人："+dfBankNiceName+"\t金额："+dfComMoney+"\t更新前状态："+dfMoneyType+"\t");
                            LogsCustomUtils.writeIntoFile(logCloudContent.toString(),logPath,fileName,true);
                            continue;
                        }

                        //调用接口查询代付是否成功并更新代付明细数据
                        Map<String,String> responseParam = ServicePayUtils.searchPay(orderid);
                        if(responseParam.size()>0){
                            // 接口数据
                            String response = responseParam.get("response");  //接口返回数据
                            String requestUrl = responseParam.get("requestUrl");  //请求接口连接
                            String requestParam = responseParam.get("requestParam");  //请求参数

                            logCloudContent.append("\n 申请单号："+orderid+" \t 接口返回信息:" + response);
                            //解析返回参数
                            JSONObject responseMap = JSONObject.fromObject(response);
                            if( responseMap.isEmpty() || responseMap == null ){//调取接口失败
                                logCloudContent.append("接口返回异常");
                                LogsCustomUtils.writeIntoFile(logCloudContent.toString(),logPath,fileName,true);
                                continue;
                            }else{//成功
                                String code = responseMap.get("code").toString();
                                if( code.equals("10000") ){

                                    if( responseMap.has("data") && StringUtils.isNotBlank(responseMap.get("data").toString()) ){
                                        JSONObject data = JSONObject.fromObject(responseMap.getString("data"));
                                       /* 代付状态 00 提交申请，01 审核通过，02
                                        申请被拒绝，03 已打批次，04 提交到渠道，
                                        05 代付成功，06 代付失败*/
                                        String orderStatus = data.get("orderStatus").toString();
                                        Integer afterMoneyType = 4;//

                                        //订单号重复
                                        if( orderStatus.equals("03") ){
                                            logCloudContent.append("\n代付原数据：代付明细ID:"+dfId+"\t代付单号"+orderid+"\t收款人："+dfBankNiceName+"\t金额："+dfComMoney+"\t更新前状态："+dfMoneyType+"\t");
                                            LogsCustomUtils.writeIntoFile(logCloudContent.toString(),logPath,fileName,true);
                                            continue;
                                        }
                                        //代付成功
                                        if( orderStatus.equals("05") ){
                                            afterMoneyType = 2;//更新后状态
                                            batchDealMoney = batchDealMoney.add(dfComMoney);
                                            batchDealItems++;
                                        }
                                        //代付失败
                                        if( orderStatus.equals("02") || orderStatus.equals("06")  ){//
                                            afterMoneyType = 3;//更新后状态
                                            batchFailMoney = batchFailMoney.add(dfComMoney);
                                            batchFailItems++;
                                        }
                                        logCloudContent.append("\n代付原数据：代付明细ID:"+dfId+"\t代付单号"+orderid+"\t收款人："+dfBankNiceName+"\t金额："+dfComMoney+"\t更新前状态："+dfMoneyType);

                                        //开始更新代付明细
                                        CloudDfMoneyRequest cloudDfMoney = new CloudDfMoneyRequest();
                                        cloudDfMoney.setMontype(afterMoneyType);
                                        cloudDfMoney.setUpdatetime(date); //更新时间
                                        cloudDfMoney.setId(dfId);
                                        int count = cloudDfMoneyServiceFacade.updateDfMoneyActiveById(cloudDfMoney,dfId);
                                        if( count != 0 ){
                                            logCloudContent.append("\t 更新后状态："+afterMoneyType+"\t 数据更新成功");
                                        }else{
                                            logCloudContent.append("\t 更新后状态："+afterMoneyType+"\t 数据更新失败");
                                        }

                                        /*//判断批次订单表 pay_cloud_company_money 是否支付成功
                                        if( batchAllItems == (batchDealItems) || batchAllItems == (batchFailItems) ){ //代付批次全部成功
                                            byte afterCompanyMoneyType = 4; //处理失败
                                            if( batchAllItems == (batchDealItems) ){
                                                afterCompanyMoneyType = 2; //处理成功
                                            }
                                            logContent.append("\n批次订单：主键ID:"+companyMoneyId+"\t更新前状态："+companyMoneyType);
                                            CloudCompanyMoneyInfo companyMoneyInfo = new CloudCompanyMoneyInfo();
                                            companyMoneyInfo.setMontype(afterCompanyMoneyType);
                                            companyMoneyInfo.setUpdatetime(date); //更新时间
                                            companyMoneyInfo.setId(companyMoneyId);
                                            int CompaynUpCount = cloudCompanyMoneyServiceFacade.updateColumn(companyMoneyInfo);
                                            if( CompaynUpCount != 0 ){
                                                logContent.append("\t 更新后状态："+afterCompanyMoneyType+"\t 数据更新成功");
                                            }else{
                                                logContent.append("\t 更新后状态："+afterCompanyMoneyType+"\t 数据更新失败");
                                            }

                                        }*/

                                    }else{
                                        logCloudContent.append("\n接口返回异常，状态码："+code+"\t 未接收到data数据" );
                                        LogsCustomUtils.writeIntoFile(logCloudContent.toString(),logPath,fileName,true);
                                    }

                                }else{
                                    logCloudContent.append("\n接口返回异常，状态码："+code );

                                }

                                BigDecimal afterAdvanceMoney = advanceMoney.subtract(dfRets.get(i).getCommoney());  //预付款
                                String afterAdvanceCode = ToolUtils.CreateCode(afterAdvanceMoney.toString(), dfRets.get(i).getUid().toString());
                                PayCloudCompany paycloudcompnay = new PayCloudCompany();
                                paycloudcompnay.setAdvanceMoney(afterAdvanceMoney); //预付款
                                paycloudcompnay.setAdvanceCode(afterAdvanceCode);

                                if( code.equals("10000") ){
                                    if( responseMap.has("data") && StringUtils.isNotBlank(responseMap.get("data").toString()) ){
                                        JSONObject data = JSONObject.fromObject(responseMap.getString("data"));
                                       /* 代付状态 00 提交申请，01 审核通过，02 申请被拒绝，03 已打批次，04 提交到渠道，05 代付成功，06 代付失败*/
                                        String orderStatus = data.get("orderStatus").toString();
                                        String retCode = data.get("retCode").toString();
                                        Boolean dfSuccess = true;
                                        String retCode_success = "0000";
                                        if ( !orderStatus_successList.contains(orderStatus) )
                                        {
                                            //代付失败
                                            dfSuccess = false;
                                        }
                                        if ( !retCode.equals(retCode_success) )
                                        {
                                            //代付失败
                                            dfSuccess = false;
                                        }
                                        if( !dfSuccess ){
                                            //失败: 扣除预付款 至 冻结
                                            BigDecimal afterFreezeMoney = freezeMoney.add(dfRets.get(i).getCommoney()); //冻结
                                            String afterFreezeCode = ToolUtils.CreateCode(afterFreezeMoney.toString(), dfRets.get(i).getUid().toString());
                                            paycloudcompnay.setIsFreeze((byte)2);   //企业冻结
                                            paycloudcompnay.setFreezeMoney(afterFreezeMoney);
                                            paycloudcompnay.setFreezeCode(afterFreezeCode);

                                            //添加冻结记录
                                            CloudDfMoneyFreezeInfo freeInfo = new CloudDfMoneyFreezeInfo();
                                            freeInfo.setCompanyId(companyInfo.getId());
                                            freeInfo.setCompanyName(companyInfo.getName());
                                            freeInfo.setCompanyCloudmoney(companyInfo.getCloudmoney());
                                            freeInfo.setCompanyAdvanceMoney(advanceMoney);
                                            freeInfo.setCompanyFreezeMoney(freezeMoney);
                                            freeInfo.setCompanyMoneyId(dfRets.get(i).getCompanyMoneyId());
                                            freeInfo.setDfMoneyId(dfRets.get(i).getId().toString());
                                            freeInfo.setOrderid(dfRets.get(i).getOrderid());
                                            freeInfo.setFreezeMoney(dfRets.get(i).getCommoney());
                                            freeInfo.setReturnContent(response);
                                            freeInfo.setStatus(1);
                                            freeInfo.setMoneyStatus(1);
                                            freeInfo.setAddtime(new Date());
                                            cloudDfMoneyFreezeServiceFacade.add(freeInfo);

                                            //df_money is_freeze=2 冻结
                                            CloudDfMoneyRequest dfMoney = new CloudDfMoneyRequest();
                                            dfMoney.setIsFreeze((byte)2);
                                            dfMoney.setUpdatetime(date); //更新时间
                                            dfMoney.setId(dfId);
                                            int count = cloudDfMoneyServiceFacade.updateDfMoneyActiveById(dfMoney,dfId);
                                        }
                                        //企业冻结金额
                                        paycloudcompnay.setId(dfRets.get(i).getUid().toString());
                                        cloudCompanyServiceFacade.updateSetiveById(paycloudcompnay);
                                    }
                                } else if ( code.equals("10008") )
                                {
                                    //扣除预付款 至 冻结
                                    BigDecimal afterFreezeMoney = freezeMoney.add(dfRets.get(i).getCommoney()); //冻结
                                    String afterFreezeCode = ToolUtils.CreateCode(afterFreezeMoney.toString(), dfRets.get(i).getUid().toString());
                                    paycloudcompnay.setIsFreeze((byte)2);   //企业冻结
                                    paycloudcompnay.setFreezeMoney(afterFreezeMoney);
                                    paycloudcompnay.setFreezeCode(afterFreezeCode);

                                    //添加冻结记录
                                    CloudDfMoneyFreezeInfo freeInfo = new CloudDfMoneyFreezeInfo();
                                    freeInfo.setCompanyId(companyInfo.getId());
                                    freeInfo.setCompanyName(companyInfo.getName());
                                    freeInfo.setCompanyCloudmoney(companyInfo.getCloudmoney());
                                    freeInfo.setCompanyAdvanceMoney(advanceMoney);
                                    freeInfo.setCompanyFreezeMoney(freezeMoney);
                                    freeInfo.setCompanyMoneyId(dfRets.get(i).getCompanyMoneyId());
                                    freeInfo.setDfMoneyId(dfRets.get(i).getId().toString());
                                    freeInfo.setOrderid(dfRets.get(i).getOrderid());
                                    freeInfo.setFreezeMoney(dfRets.get(i).getCommoney());
                                    freeInfo.setReturnContent(response);
                                    freeInfo.setStatus(1);
                                    freeInfo.setMoneyStatus(1);
                                    freeInfo.setAddtime(new Date());
                                    int res_add = cloudDfMoneyFreezeServiceFacade.add(freeInfo);

                                    //df_money is_freeze=2 冻结
                                    CloudDfMoneyRequest dfMoney = new CloudDfMoneyRequest();
                                    dfMoney.setIsFreeze((byte)2);
                                    dfMoney.setUpdatetime(date); //更新时间
                                    dfMoney.setId(dfId);
                                    int count = cloudDfMoneyServiceFacade.updateDfMoneyActiveById(dfMoney,dfId);

                                    //企业冻结金额
                                    paycloudcompnay.setId(dfRets.get(i).getUid().toString());
                                    int res_company_count = cloudCompanyServiceFacade.updateSetiveById(paycloudcompnay);
                                }
                            }


                        }else{ //接口异常

                        }
                        LogsCustomUtils.writeIntoFile(logCloudContent.toString(),logPath,fileName,true);

                    }
                    //判断批次订单表 pay_cloud_company_money 是否支付成功
                    if( dfRets.size() > 0 && (batchFailItems >0 || batchDealItems>0) ){ //代付批次全部成功
                        byte afterCompanyMoneyType = 3; //处理失败
                        //处理失败
                        if(batchAllItems == (batchFailItems)){
                            afterCompanyMoneyType = 4;
                        }
                        //处理成功
                        if( batchAllItems == (batchDealItems) ){
                            afterCompanyMoneyType = 2;
                        }
                        logCloudContent.append("\n批次订单：主键ID:"+companyMoneyId+"\t更新前状态："+companyMoneyType);
                        CloudCompanyMoneyInfo companyMoneyInfo = new CloudCompanyMoneyInfo();
                        companyMoneyInfo.setMontype(afterCompanyMoneyType);
                        companyMoneyInfo.setUpdatetime(date); //更新时间
                        companyMoneyInfo.setId(companyMoneyId);
                        if(batchFailItems > 0){
                            companyMoneyInfo.setBatchfailitems(Integer.toString(batchFailItems));
                            companyMoneyInfo.setBatchfailmoney(batchFailMoney);
                        }
                        if(batchDealItems > 0){
                            companyMoneyInfo.setBatchdealitems(Integer.toString(batchDealItems));
                            companyMoneyInfo.setBatchdealmoney(batchDealMoney);
                        }
                        int CompaynUpCount = cloudCompanyMoneyServiceFacade.updateColumn(companyMoneyInfo);
                        if( CompaynUpCount != 0 ){
                            logCloudContent.append("\t 更新后状态："+afterCompanyMoneyType+"\t 数据更新成功");
                        }else{
                            logCloudContent.append("\t 更新后状态："+afterCompanyMoneyType+"\t 数据更新失败");
                        }

                    }

                }
                logContent.append("\n\n ================================结束批次请求========================");

                LogsCustomUtils.writeIntoFile(logContent.toString(),logPath,fileName,true);

            }

        }else{

        }

    }

    /**
     * 批处理代付订单的各金额字段
     */
    @RequestMapping("/dealDfOrders")
    public void dealDfOrders(){
        List<PayCloudCompanyMoney> list = cloudCompanyMoneyServiceFacade.getAllRecords();
        for (PayCloudCompanyMoney single:list){
            String money = single.getCommoney().toString();

            CloudCompanyMoneyInfo cloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
            CloudCompanyInfo companyInfo = cloudCompanyServiceFacade.getMerchInfoByMerchNo(single.getMerchNo());
            String agentNo = ConfigUtil.getValue("XINXIANG_AGENT_NO");
            // 查询代理商户和业务商户的费率，计算总费率
            CloudCompanyAgentInfo agentInfo = cloudCompanyAgentServiceFacade.getAgentByAgentNo(agentNo);
            CloudCompanySalesInfo salesInfo = cloudCompanySalesServiceFacade.getSalesBySalesNo(companyInfo.getMerchNo());
            Double totalRate = Double.parseDouble(agentInfo.getAgentRate().toString()) + Double.parseDouble(salesInfo.getSalesRate().toString());
            Double moneyDouble = new Double(money);
            Double feeMoney = totalRate * moneyDouble;
            cloudCompanyMoneyInfo.setFeeRate(new BigDecimal(totalRate));
            cloudCompanyMoneyInfo.setFeemoney(new BigDecimal(feeMoney).setScale(2,BigDecimal.ROUND_DOWN));   // 服务费金额：实发金额*服务费率
            // 增值税金额
            Double addedValueTax = new Double(ConfigUtil.getValue("ADDED_VALUE_TAX"));
            Double taxMoney = ( moneyDouble + feeMoney ) / ( 1 + addedValueTax ) * addedValueTax;
            cloudCompanyMoneyInfo.setTaxRate(new BigDecimal(addedValueTax));
            cloudCompanyMoneyInfo.setTaxmoney(new BigDecimal(taxMoney).setScale(2,BigDecimal.ROUND_DOWN));
            // 增值税附加金额
            Double addedValueTaxAddtion = new Double(ConfigUtil.getValue("ADDED_VALUE_TAX_ADDITION"));
            Double addedValueTaxAddtionMoney = taxMoney*addedValueTaxAddtion;
            cloudCompanyMoneyInfo.setTaxmoreTax(new BigDecimal(addedValueTaxAddtion));
            cloudCompanyMoneyInfo.setTaxmoremoney(new BigDecimal(addedValueTaxAddtionMoney).setScale(2,BigDecimal.ROUND_DOWN));
            // 毛利金额
            Double individualTax = new Double(ConfigUtil.getValue("INDIVIDUAL_TAX"));
            Double supposePay = moneyDouble / (1-individualTax);   // 应发金额
            Double profit = (moneyDouble + feeMoney) - (supposePay + taxMoney + addedValueTaxAddtionMoney);
            cloudCompanyMoneyInfo.setProfitmoney(new BigDecimal(profit).setScale(2,BigDecimal.ROUND_DOWN));      // 毛利金额
            BigDecimal shouldMoney = new BigDecimal(moneyDouble / ( 1-individualTax-0.0003)).setScale(2,BigDecimal.ROUND_DOWN);
            cloudCompanyMoneyInfo.setShouldMoney(shouldMoney);
            cloudCompanyMoneyInfo.setIndividualTax(new BigDecimal(individualTax));
            cloudCompanyMoneyInfo.setIndividualMoney(new BigDecimal(shouldMoney.doubleValue()*individualTax));
            Double yinhuaTax = 0.0003;
            cloudCompanyMoneyInfo.setYinhuaTax(new BigDecimal(yinhuaTax));
            cloudCompanyMoneyInfo.setYinhuaMoney(new BigDecimal(shouldMoney.doubleValue()*yinhuaTax).setScale(2,BigDecimal.ROUND_DOWN));

            PayCloudCompanyMoney payCloudCompanyMoney = new PayCloudCompanyMoney();
            BeanCopier beanCopier = BeanCopier.create(CloudCompanyMoneyInfo.class, PayCloudCompanyMoney.class, false);
            beanCopier.copy(cloudCompanyMoneyInfo, payCloudCompanyMoney, null);
            cloudCompanyMoneyServiceFacade.updateRecById(payCloudCompanyMoney,single.getId());
        }
    }

}
