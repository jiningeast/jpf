package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.DfDataUtils;
import com.joiest.jpf.cloud.api.util.DfThread;
import com.joiest.jpf.cloud.api.util.DfUtils;
import com.joiest.jpf.cloud.api.util.ToolsUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.entity.CloudDfOrderInterfaceInfo;
import com.joiest.jpf.entity.CloudDfTaskInterfaceInfo;
import com.joiest.jpf.entity.CloudStaffMonthTotalInterfaceInfo;
import com.joiest.jpf.facade.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/clouddf")
public class CloudDfController {

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    @Autowired
    private CloudDfTaskInterfaceServiceFacade cloudDfTaskInterfaceServiceFacade;

    @Autowired
    private CloudDfOrderInterfaceServiceFacade cloudDfOrderInterfaceServiceFacade;

    @Autowired
    private CloudDfFqwaterServiceFacade cloudDfFqwaterServiceFacade;

    @Autowired
    private CloudStaffMonthTotalServiceFacade cloudStaffMonthTotalServiceFacade;

    /**
     * 代付接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/dfApi", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String dfApi(DfApiInterfaceRequest request)
    {
        ValidatorUtils.validateInterface(request);

        //sign验证
        Map<String,Object> forginRequestMap = new HashMap<>();
        forginRequestMap.put("batchid", request.getBatchid());
        forginRequestMap.put("dfid", request.getDfid());
        TreeMap<String, Object> signMap = new TreeMap<>();
        signMap.putAll(forginRequestMap);
        String sortUrlStr = ToolUtils.mapToUrl(signMap);
        String signStr = Md5Encrypt.md5(sortUrlStr + ConfigUtil.getValue("DFAPI_KEY"));
        if ( !signStr.equals(request.getSign()) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_SIGN_ERROR.getCode(),JpfInterfaceErrorInfo.DF_SIGN_ERROR.getDesc());
        }

        if ( StringUtils.isBlank(request.getBatchid()) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_BATCHNO_NOTEMPTY.getCode(),JpfInterfaceErrorInfo.DF_BATCHNO_NOTEMPTY.getDesc());
        }

        //获取批次df详情
        int flag = 0;
        GetCloudMoneyDfResponse response = cloudDfMoneyServiceFacade.getDfDetailList(request.getBatchid(),request.getDfid(),0,0, flag);
        if ( response == null || response.getList().isEmpty() )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_INFOLIST_EMPTY.getCode(),JpfInterfaceErrorInfo.DF_INFOLIST_EMPTY.getDesc());
        }
        //金额过滤
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");

        List<Map<String,Object>> filterMonthTotalList = new ArrayList<>();
        Iterator<CloudDfMoneyInterfaceInfo> it = response.getList().iterator();
        while (it.hasNext()) {
            CloudDfMoneyInterfaceInfo x = (CloudDfMoneyInterfaceInfo) it.next();
            Calendar calstar = Calendar.getInstance();
            String currdate = fmt.format(calstar.getTime());
            //获取信息
            CloudStaffMonthTotalInterfaceInfo monthTotalInfo = cloudStaffMonthTotalServiceFacade.getStaffMonthTotal(currdate, x.getBusstaffid());
            if (monthTotalInfo != null)
            {
                //判断
                double monthTotal_old = monthTotalInfo.getMonthTotal().doubleValue();
                double monthTotal_request = x.getCommoney().doubleValue();
                double montyTotal_sum = BigDecimalCalculateUtils.add(monthTotal_old, monthTotal_request);
                BigDecimal monthTotal = new BigDecimal(montyTotal_sum+"");
                BigDecimal max = new BigDecimal(ConfigUtil.getValue("DFAPI_MAX"));
                if ( monthTotal.compareTo(max) == 1 )
                {
                    Map<String,Object> filterMoney = new HashMap<>();
                    filterMoney.put("orderid", x.getOrderid());
                    filterMoney.put("busstaffid", x.getBusstaffid());
                    filterMoney.put("batchid", x.getCompanyMoneyId());
                    filterMoney.put("dfid", x.getId());
                    filterMoney.put("commoney", x.getCommoney());
                    filterMonthTotalList.add(filterMoney);

                    double total_old = response.getMonthTotal().doubleValue();
                    double total_curr = x.getCommoney().doubleValue();
                    double total = BigDecimalCalculateUtils.sub(total_old,total_curr);
                    response.setMonthTotal(new BigDecimal(total+""));

                    //删除元素
                    it.remove();
                    response.setCount(response.getCount() - 1);
                }
            }
        }

        if ( response.getCount() == 0 || response.getList().isEmpty() )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_LISTFILTER_EMPTY.getCode(),JpfInterfaceErrorInfo.DF_LISTFILTER_EMPTY.getDesc());
        }

        //添加任务
        AddCloudDfTaskRequest requestTask = new AddCloudDfTaskRequest();
        requestTask.setRequestBatchno(request.getBatchid());
        Map<String, Object> requestMap = ClassUtil.requestToMap(request);
        String requestStr = ToolUtils.mapToUrl(requestMap);
        requestTask.setRequestStr(requestStr);
        requestTask.setRequestDfId(request.getDfid());
        requestTask.setOrderCount(response.getCount());
        requestTask.setOrderMoney(response.getMonthTotal());
        requestTask.setCreated(new Date());
        int taskId = cloudDfTaskInterfaceServiceFacade.addTask(requestTask);
        String batchid_self = ToolUtils.createDfOrderid(String.valueOf(System.currentTimeMillis()), String.valueOf(taskId), 24);
        //添加任务批次号
        CloudDfTaskInterfaceInfo upBatchTaskInfo = new CloudDfTaskInterfaceInfo();
        upBatchTaskInfo.setId((long)taskId);
        upBatchTaskInfo.setBatchid(batchid_self);

        //返回数据
        JSONObject resultJson = new JSONObject();
        resultJson.put("code","10000");
        resultJson.put("info","SUCCESS");
        JSONObject dataJson = new JSONObject();
        dataJson.put("count", response.getCount());
        dataJson.put("totalMoney", response.getMonthTotal());
        dataJson.put("batchno", batchid_self);
        if ( !filterMonthTotalList.isEmpty() )
        {
            JSONObject filterMonthTotalJson = new JSONObject();
            filterMonthTotalJson.put("data", filterMonthTotalList);
            filterMonthTotalJson.put("count", filterMonthTotalList.size());
            dataJson.put("filterdata",filterMonthTotalJson);
            upBatchTaskInfo.setRemarks(filterMonthTotalJson.toString());
        }
        resultJson.put("data", dataJson);

        cloudDfTaskInterfaceServiceFacade.updateTask(upBatchTaskInfo);

        Thread dfDataUtils = new DfDataUtils(request.getBatchid(), request.getDfid(), response.getList(),batchid_self);
        dfDataUtils.setName("线程:" + request.getBatchid());
        dfDataUtils.start();

        return ToolUtils.toBase64(resultJson.toString());
    }

    @ModelAttribute
    public void beforeAction(HttpServletRequest httpRequest, HttpServletResponse response)
    {
        // 跨域
        String originHeader = httpRequest.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Headers", "accept, content-type");
        response.setHeader("Access-Control-Allow-Method", "POST");
        response.setHeader("Access-Control-Allow-Origin", originHeader);
    }

    /**
     * 执行代付任务
     */
    @RequestMapping("/doDfApi")
    @ResponseBody
    public String doDfApi()
    {
        //获取信息
        GetCloudDfTaskInterfaceResponse taskResponse = cloudDfTaskInterfaceServiceFacade.getCanableTaskList();
        if ( taskResponse == null || taskResponse.getList().isEmpty() || taskResponse.getCount() == 0 )
        {
            return "无可执行任务";
        }
        for ( CloudDfTaskInterfaceInfo one : taskResponse.getList() )
        {
            //执行
            Thread dfDataUtils = new DfThread(one.getBatchid(),one.getId());
            dfDataUtils.setName("【线程:" + one.getBatchid() + "】");
            dfDataUtils.start();
        }
        return "正在执行.....";
    }
    /**
     * 代付查询接口
     * */
    @RequestMapping(value = "/dfSelectApi",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String dfSelectApi(HttpServletRequest request){

        JSONObject responseDa = new JSONObject();

        String orderId = request.getParameter("orderId");

        CloudDfOrderInterfaceInfo cloudDfOrderInterfaceInfo = cloudDfOrderInterfaceServiceFacade.getDfOrderByRequestOrderid(orderId);
        if(cloudDfOrderInterfaceInfo == null){

            responseDa.put("code","10088");
            responseDa.put("info","未获取到此单信息");
            return ToolUtils.toBase64(responseDa.toString());
        }
        Map<String,String> apiInfo = new HashMap<>();

        apiInfo.put("tranNo",cloudDfOrderInterfaceInfo.getTranno());
        apiInfo.put("outOrderNo",cloudDfOrderInterfaceInfo.getOrderid());

        int count = 0;
        if(cloudDfOrderInterfaceInfo.getQuerycount() == null || cloudDfOrderInterfaceInfo.getQuerycount().equals(0)){

            count = 1;
        }else{

            count = cloudDfOrderInterfaceInfo.getQuerycount()+1;
        }
        JSONObject postApi = new DfUtils().queryAgentPay(apiInfo);

        JSONObject resJson = JSONObject.fromObject(postApi.get("responseParam"));

        //更新代付订单表信息
        CloudDfOrderInterfaceInfo cloudDfOrderInterfaceInfo1 = new CloudDfOrderInterfaceInfo();
        cloudDfOrderInterfaceInfo1.setId(cloudDfOrderInterfaceInfo.getId());
        cloudDfOrderInterfaceInfo1.setLastrespose(resJson.toString());
        cloudDfOrderInterfaceInfo1.setQuerycount(count);
        cloudDfOrderInterfaceInfo1.setDfstatus(resJson.get("orderStatus").toString());
        cloudDfOrderInterfaceInfo1.setUpdated(new Date());

        int isStatus = cloudDfOrderInterfaceServiceFacade.updateDfOrderStatus(cloudDfOrderInterfaceInfo1);

        Map<String,String> map = new HashMap<>();
        map.put("request_orderid",orderId);
        map.put("orderid",cloudDfOrderInterfaceInfo.getOrderid());
        map.put("tranNo",cloudDfOrderInterfaceInfo.getTranno());
        map.put("tranAmt",cloudDfOrderInterfaceInfo.getApplyamt().toString());
        map.put("orderStatus",resJson.get("orderStatus").toString());
        map.put("requestParam",postApi.get("requestParam").toString());
        map.put("responseParam",resJson.toString());

        int isSuc = cloudDfFqwaterServiceFacade.addCloudDfFqwater(map);

        //支付成功

        if ( resJson.has("orderStatus") && resJson.get("orderStatus").equals("05") )
        {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
            String currdate = fmt.format(cloudDfOrderInterfaceInfo.getCreated());
            CloudStaffMonthTotalInterfaceInfo monthTotalInfo = cloudStaffMonthTotalServiceFacade.getStaffMonthTotal(currdate, cloudDfOrderInterfaceInfo.getBusstaffid());
            ModifyCloudStaffMonthTotalRequest staffMonth = new ModifyCloudStaffMonthTotalRequest();
            if ( monthTotalInfo == null )
            {
                staffMonth.setBusstaffid(cloudDfOrderInterfaceInfo.getBusstaffid());
                staffMonth.setMonth(currdate);
                staffMonth.setMonthTotal(cloudDfOrderInterfaceInfo.getApplyamt());
                staffMonth.setCreated(new Date());
                String orderids = cloudDfOrderInterfaceInfo.getOrderid() + ",";
                staffMonth.setOrderids(orderids);
                cloudStaffMonthTotalServiceFacade.addStaffMonthTotal(staffMonth);
            }else
            {
                if ( monthTotalInfo.getOrderids() != null && !monthTotalInfo.getOrderids().contains(cloudDfOrderInterfaceInfo.getOrderid()) )
                {
                    //更新
                    double monthTotal_old = monthTotalInfo.getMonthTotal().doubleValue();
                    double monthTotal_curr = cloudDfOrderInterfaceInfo.getApplyamt().doubleValue();
                    double montyTotal_new = BigDecimalCalculateUtils.add(monthTotal_old, monthTotal_curr);
                    BigDecimal monthTotal = new BigDecimal(montyTotal_new+"");

                    staffMonth.setId(monthTotalInfo.getId());
                    staffMonth.setBusstaffid(cloudDfOrderInterfaceInfo.getBusstaffid());
                    staffMonth.setMonth(currdate);
                    staffMonth.setMonthTotal(monthTotal);
                    staffMonth.setUpdated(new Date());
                    BigDecimal max = new BigDecimal(ConfigUtil.getValue("DFAPI_MAX"));
                    if ( monthTotal.compareTo(max) == 1 || monthTotal.compareTo(max) == 0 )
                    {
                        staffMonth.setStatus(1);
                    }
                    String orderids = monthTotalInfo.getOrderids() + cloudDfOrderInterfaceInfo.getOrderid() + ",";
                    staffMonth.setOrderids(orderids);
                    cloudStaffMonthTotalServiceFacade.updateStaffMonthTotal(staffMonth);
                }
            }
        }
        //组装返回数据
        JSONObject actualData = new JSONObject();
        actualData.put("tranAmt",resJson.get("tranAmt"));
        actualData.put("orderid",orderId);
        actualData.put("orderStatus",resJson.get("orderStatus"));
        actualData.put("tranDesc",resJson.get("tranDesc"));

        responseDa.put("info",resJson.get("retMsg"));
        responseDa.put("data",actualData);

        if(resJson.get("retCode").equals("0000")){

            responseDa.put("code","10000");
        }else{
            responseDa.put("code","10008");
        }
        return ToolUtils.toBase64(responseDa.toString());
    }

}
