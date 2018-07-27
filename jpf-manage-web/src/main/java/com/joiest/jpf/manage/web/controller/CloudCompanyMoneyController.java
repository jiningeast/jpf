package com.joiest.jpf.manage.web.controller;


import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.dto.CloudCompanyMoneyRequest;
import com.joiest.jpf.dto.CloudCompanyMoneyResponse;
import com.joiest.jpf.dto.CloudDfMoneyRequest;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.CloudCompanyMoneyInfo;
import com.joiest.jpf.entity.CloudDfMoneyInfo;
import com.joiest.jpf.facade.CloudCompanyMoneyServiceFacade;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import com.joiest.jpf.manage.web.util.ServicePayUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cloudCompanyMoney")
public class CloudCompanyMoneyController {

    @Autowired
    private CloudCompanyMoneyServiceFacade cloudCompanyMoneyServiceFacade;

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

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
        companyMoneyRequest.setId("205");
        if(comMoneyId != null ){
            companyMoneyRequest.setId(comMoneyId); //指定批次主键
        }

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
                List<CloudDfMoneyInfo> dfRets = cloudDfMoneyServiceFacade.getAllBySective(cloudDfMoneyRequest);
                if( dfRets.size() > 0 ){

                    for (int m = 0; m < dfRets.size() ; m++) {

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

}
