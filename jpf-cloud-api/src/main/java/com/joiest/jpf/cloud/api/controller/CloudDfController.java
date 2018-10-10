package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.DfDataUtils;
import com.joiest.jpf.cloud.api.util.DfThread;
import com.joiest.jpf.cloud.api.util.DfUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.entity.CloudDfOrderInterfaceInfo;
import com.joiest.jpf.entity.CloudDfTaskInterfaceInfo;
import com.joiest.jpf.entity.CloudStaffMonthTotalInterfaceInfo;
import com.joiest.jpf.facade.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(CloudDfController.class);

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
        SimpleDateFormat fmtLog = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info(" ======== dfApi 【date】: " + fmtLog.format(new Date()) + " ; batchid:" + request.getBatchid() + "; dfid: " + request.getDfid());

        JSONObject error_resultJson = new JSONObject();
        JSONObject errData = new JSONObject();
        errData.put("batchid", request.getBatchid());

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
            logger.info(" ======== 【30001】 : 签名错误");
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_SIGN_ERROR.getCode(),JpfInterfaceErrorInfo.DF_SIGN_ERROR.getDesc());
        }

        if ( StringUtils.isBlank(request.getBatchid()) )
        {
            logger.info(" ======== 【30002】 : 批次号不能为空");
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_BATCHNO_NOTEMPTY.getCode(),JpfInterfaceErrorInfo.DF_BATCHNO_NOTEMPTY.getDesc());
        }

        //获取批次df详情
        int flag = 0;
        GetCloudMoneyDfResponse response = cloudDfMoneyServiceFacade.getDfDetailList(request.getBatchid(),request.getDfid(),0,0, flag);
        if ( response == null || response.getList().isEmpty() )
        {
            logger.info(" ======== 【30003】 : 待打款信息列表为空");
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_INFOLIST_EMPTY.getCode(),JpfInterfaceErrorInfo.DF_INFOLIST_EMPTY.getDesc());
        }

        //当月累计打款金额过滤
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");

        List<Map<String,String>> filterMonthTotalList = new ArrayList<>();
        Iterator<CloudDfMoneyInterfaceInfo> it = response.getList().iterator();
        BigDecimal max = new BigDecimal(ConfigUtil.getValue("DFAPI_MAX"));
        while (it.hasNext()) {
            CloudDfMoneyInterfaceInfo x = it.next();
            //>30000  0:相等 1:大于
            if ( x.getCommoney().compareTo(max) > 0 )
            {
                logger.info(" ======== 【30005】 : 此订单代付金额超过{}", max);
                error_resultJson.put("code", JpfInterfaceErrorInfo.DF_ONE_ORDER_EXCEED_MAXMONEY.getCode());
                error_resultJson.put("info", "订单代付金额超过限额");
                errData.put("dfid", x.getId().toString());
                errData.put("orderid", x.getOrderid());
                errData.put("money", x.getCommoney().toString());
                error_resultJson.put("data", errData);
                return ToolUtils.toBase64(error_resultJson.toString());
            }

            Calendar calstar = Calendar.getInstance();
            String currdate = fmt.format(calstar.getTime());
            //当月累计打款金额
            CloudStaffMonthTotalInterfaceInfo monthTotalInfo = cloudStaffMonthTotalServiceFacade.getStaffMonthTotal(currdate, x.getBusstaffid());
            ModifyCloudStaffMonthTotalRequest staffMonth = new ModifyCloudStaffMonthTotalRequest();
            if (monthTotalInfo != null)
            {
                if ( monthTotalInfo.getStatus() == 1 )
                {
                    logger.info(" ======== 【30006】用户该月代付已经超过限额: 打款批次" + request.getBatchid() + "; dfId: " + request.getDfid()  + " ; 用户ID: " + x.getBusstaffid() );
                    error_resultJson.put("code", JpfInterfaceErrorInfo.DF_STAFF_EXCEED_MAXMONEY.getCode());
                    error_resultJson.put("info", "用户该月代付金额已经超过限额");
                    errData.put("dfid", x.getId().toString());
                    errData.put("orderid", x.getOrderid());
                    errData.put("money", x.getCommoney().toString());
                    error_resultJson.put("data", errData);
                    return ToolUtils.toBase64(error_resultJson.toString());
                }

                BigDecimal monthTotal_old = monthTotalInfo.getMonthTotal();
                BigDecimal monthTotal_request = x.getCommoney();
                BigDecimal monthTotal = monthTotal_old.add(monthTotal_request);
                if ( monthTotal.compareTo(max) > 0 )
                {
                    logger.info(" ======== 代付订单累计金额超过限额: orderid: " + x.getOrderid() + "dfId: " + x.getId()  + " 用户ID: " + x.getBusstaffid() );
                    error_resultJson.put("code", JpfInterfaceErrorInfo.DF_STAFF_EXCEED_MAXMONEY.getCode());
                    error_resultJson.put("info", "用户该月代付合计金额已经超过限额");
                    errData.put("dfid", x.getId().toString());
                    errData.put("orderid", x.getOrderid());
                    errData.put("money", x.getCommoney().toString());
                    error_resultJson.put("data", errData);
                    return ToolUtils.toBase64(error_resultJson.toString());

//                    throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_ONE_ORDER_EXCEED_MAXMONEY.getCode(),JpfInterfaceErrorInfo.DF_ONE_ORDER_EXCEED_MAXMONEY.getDesc(), "代付订单累计金额超过限额 : orderid:" + x.getOrderid() + "dfId: " + x.getId()  + " 用户ID: " + x.getBusstaffid() );

//                    Map<String,String> filterMoney = new HashMap<>();
//                    filterMoney.put("orderid", x.getOrderid());
//                    filterMoney.put("busstaffid", x.getBusstaffid().toString());
//                    filterMoney.put("batchid", x.getCompanyMoneyId());
//                    filterMoney.put("dfid", x.getId().toString());
//                    filterMoney.put("commoney", x.getCommoney().toString());
//                    filterMonthTotalList.add(filterMoney);
//
//                    double total_old = response.getMonthTotal().doubleValue();
//                    double total_curr = x.getCommoney().doubleValue();
//                    double total = BigDecimalCalculateUtils.sub(total_old,total_curr);
//                    response.setMonthTotal(new BigDecimal(total+""));
//
//                    //删除元素
//                    it.remove();
//                    response.setCount(response.getCount() - 1);
                }

                //更新月打款总额
                if ( monthTotalInfo.getOrderids() != null )
                {
                    staffMonth.setId(monthTotalInfo.getId());
                    staffMonth.setBusstaffid(x.getBusstaffid());
                    staffMonth.setMonth(currdate);
                    staffMonth.setMonthTotal(monthTotal);
                    staffMonth.setUpdated(new Date());
                    if ( monthTotal.compareTo(max) >= 0 )
                    {
                        staffMonth.setStatus(1);
                    }
                    String orderids = monthTotalInfo.getOrderids() + x.getOrderid() + ",";
                    staffMonth.setOrderids(orderids);
                    String remark_old = monthTotalInfo.getRemarks();
                    if ( StringUtils.isBlank(remark_old) )
                    {
                        JSONObject remarkInfo = new JSONObject();
                        JSONObject remark = new JSONObject();
                        remark.put("dfmoney_orderid", x.getOrderid());
                        remark.put("money", x.getCommoney());
                        remark.put("type", "add");
                        remark.put("content", "add: 代付请求，增加月代付总额");
                        remark.put("before", "0.00");
                        remark.put("after", x.getCommoney());

                        remarkInfo.put("1", remark);
                        staffMonth.setRemarks(remarkInfo.toString());
                    }else
                    {
                        JSONObject remarkOld = JSONObject.fromObject(remark_old);
                        JSONArray remardArr = JSONArray.fromObject(remarkOld);
                        JSONObject remark_new = new JSONObject();
                        remark_new.put("dfmoney_orderid", x.getOrderid());
                        remark_new.put("money", x.getCommoney());
                        remark_new.put("type", "add");
                        remark_new.put("content", "代付请求，增加月代付总额");
                        remark_new.put("before", monthTotal_old.toString());
                        remark_new.put("after", monthTotal.toString());
                        remarkOld.put(remarkOld.size() + 1, remark_new);

                        staffMonth.setRemarks(remarkOld.toString());
                    }

                    cloudStaffMonthTotalServiceFacade.updateStaffMonthTotal(staffMonth);
                }

            } else
            {
                //添加月打款记录
                staffMonth.setBusstaffid(x.getBusstaffid());
                staffMonth.setMonth(currdate);
                staffMonth.setMonthTotal(x.getCommoney());
                staffMonth.setCreated(new Date());
                String orderids = x.getOrderid() + ",";
                staffMonth.setOrderids(orderids);

                JSONObject remarkInfo = new JSONObject();
                JSONObject remark = new JSONObject();
                remark.put("dfmoney_orderid", x.getOrderid());
                remark.put("money", x.getCommoney());
                remark.put("type", "add");
                remark.put("content", "代付请求，增加月代付总额");
                remark.put("before", "0.00");
                remark.put("after", x.getCommoney());

                remarkInfo.put("1", remark);
                staffMonth.setRemarks(remarkInfo.toString());

                if ( x.getCommoney().compareTo(max) >= 0 )
                {
                    staffMonth.setStatus(1);
                }

                cloudStaffMonthTotalServiceFacade.addStaffMonthTotal(staffMonth);
            }
        }

//        if ( response.getCount() == 0 || response.getList().isEmpty() )
//        {
//            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_LISTFILTER_EMPTY.getCode(),JpfInterfaceErrorInfo.DF_LISTFILTER_EMPTY.getDesc());
//        }

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
//        if ( !filterMonthTotalList.isEmpty() )
//        {
//            JSONObject filterMonthTotalJson = new JSONObject();
//            filterMonthTotalJson.put("data", filterMonthTotalList);
//            filterMonthTotalJson.put("count", filterMonthTotalList.size());
//            dataJson.put("filterdata",filterMonthTotalJson);
//            upBatchTaskInfo.setRemarks(filterMonthTotalJson.toString());
//        }
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
        String sign = request.getParameter("sign");
        if ( StringUtils.isBlank(orderId) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(),JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc());
        }
        if ( StringUtils.isBlank(orderId) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.NO_SIGN.getCode(),JpfInterfaceErrorInfo.NO_SIGN.getDesc());
        }
        //sign验证
        Map<String,Object> forginRequestMap = new HashMap<>();
        forginRequestMap.put("orderId", orderId);
        TreeMap<String, Object> signMap = new TreeMap<>();
        signMap.putAll(forginRequestMap);
        String sortUrlStr = ToolUtils.mapToUrl(signMap);
        String signStr = Md5Encrypt.md5(sortUrlStr + ConfigUtil.getValue("DFAPI_KEY"));
        //签名错误
        if ( !signStr.equals(sign) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_SIGN_ERROR.getCode(),JpfInterfaceErrorInfo.DF_SIGN_ERROR.getDesc());
        }

        CloudDfOrderInterfaceInfo cloudDfOrderInterfaceInfo = cloudDfOrderInterfaceServiceFacade.getDfOrderByRequestOrderid(orderId);
        if(cloudDfOrderInterfaceInfo == null){

            responseDa.put("code","10008");
            responseDa.put("info","未获取到此单信息");
            return ToolUtils.toBase64(responseDa.toString());
        }
        Map<String,String> apiInfo = new HashMap<>();

        if ( StringUtils.isNotBlank(cloudDfOrderInterfaceInfo.getTranno()) && cloudDfOrderInterfaceInfo.getTranno() != null )
        {
            apiInfo.put("tranNo",cloudDfOrderInterfaceInfo.getTranno());
        }
        apiInfo.put("outOrderNo",cloudDfOrderInterfaceInfo.getOrderid());

        //获取此单查询次数
        int count = 0;
        if(cloudDfOrderInterfaceInfo.getQuerycount() == null || cloudDfOrderInterfaceInfo.getQuerycount().equals(0)){

            count = 1;
        }else{

            count = cloudDfOrderInterfaceInfo.getQuerycount()+1;
        }
        JSONObject postApi = new DfUtils().queryAgentPay(apiInfo);

        JSONObject resJson = JSONObject.fromObject(postApi.get("responseParam"));

        //代付查询流水
        Map<String,String> map = new HashMap<>();
        //更新代付订单表信息
        CloudDfOrderInterfaceInfo cloudDfOrderInterfaceInfo1 = new CloudDfOrderInterfaceInfo();
        cloudDfOrderInterfaceInfo1.setId(cloudDfOrderInterfaceInfo.getId());
        cloudDfOrderInterfaceInfo1.setLastrespose(resJson.toString());
        cloudDfOrderInterfaceInfo1.setQuerycount(count);
        cloudDfOrderInterfaceInfo1.setUpdated(new Date());

        if(resJson.has("retCode") && resJson.get("retCode").equals("0000")){

            cloudDfOrderInterfaceInfo1.setDfstatus(resJson.get("orderStatus").toString());
            map.put("orderStatus",resJson.get("orderStatus").toString());
        }

        map.put("request_orderid",orderId);
        map.put("orderid",cloudDfOrderInterfaceInfo.getOrderid());
        map.put("tranNo",cloudDfOrderInterfaceInfo.getTranno());
        map.put("tranAmt",cloudDfOrderInterfaceInfo.getApplyamt().toString());
        map.put("requestParam",postApi.get("requestParam").toString());
        map.put("responseParam",resJson.toString());

        int isStatus = cloudDfOrderInterfaceServiceFacade.updateDfOrderStatus(cloudDfOrderInterfaceInfo1);
        int isSuc = cloudDfFqwaterServiceFacade.addCloudDfFqwater(map);

        //支付失败 扣减月代付总额
        List<String> orderStatus_successList = new ArrayList<String>(){
            {
                add("00");
                add("01");
                add("04");
                add("05");
            }
        };

        Boolean dfSuccess = true;
        String retCode_success = "0000";
        if ( resJson.has("orderStatus") && !orderStatus_successList.contains(resJson.get("orderStatus").toString()) )
        {
            //代付失败
            dfSuccess = false;
        }
        if ( resJson.has("retCode") && !resJson.get("retCode").toString().equals(retCode_success) )
        {
            //代付失败
            dfSuccess = false;
        }

        String orderid_api_remark = orderId + '-' + cloudDfOrderInterfaceInfo.getOrderid();
        if ( !dfSuccess )
        {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
            String currdate = fmt.format(cloudDfOrderInterfaceInfo.getCreated());
            CloudStaffMonthTotalInterfaceInfo monthTotalInfo = cloudStaffMonthTotalServiceFacade.getStaffMonthTotal(currdate, cloudDfOrderInterfaceInfo.getBusstaffid());
            ModifyCloudStaffMonthTotalRequest staffMonth = new ModifyCloudStaffMonthTotalRequest();
            //代付失败时，扣减订单用户的月份总额: 订单金额
            if ( monthTotalInfo != null )
            {
                //过滤多次查询
//                logger.info("monthTotalInfo.getOrderids().contains(cloudDfOrderInterfaceInfo.getOrderid()) :" + monthTotalInfo.getOrderids().contains(orderid_api_remark) );
//                logger.info(monthTotalInfo.getOrderids());
//                logger.info(cloudDfOrderInterfaceInfo.getOrderid());
                if ( monthTotalInfo.getOrderids() != null && !monthTotalInfo.getOrderids().contains(orderid_api_remark) )
                {
                    BigDecimal monthTotal_old = monthTotalInfo.getMonthTotal();
                    BigDecimal applyamt = cloudDfOrderInterfaceInfo.getApplyamt();      //订单金额
                    BigDecimal monthTotal_new = monthTotal_old.subtract(applyamt);      //扣减后的月代付总额

                    staffMonth.setId(monthTotalInfo.getId());
                    staffMonth.setBusstaffid(cloudDfOrderInterfaceInfo.getBusstaffid());
                    staffMonth.setMonth(currdate);
                    staffMonth.setMonthTotal(monthTotal_new);
                    staffMonth.setUpdated(new Date());
                    BigDecimal max = new BigDecimal(ConfigUtil.getValue("DFAPI_MAX"));
                    if ( monthTotal_new.compareTo(max) >= 0 )
                    {
                        staffMonth.setStatus(1);
                    } else
                    {
                        staffMonth.setStatus(0);
                    }
                    String orderids = monthTotalInfo.getOrderids() + orderid_api_remark + ",";
                    staffMonth.setOrderids(orderids);

                    String remark_old = monthTotalInfo.getRemarks();
                    if ( StringUtils.isBlank(remark_old) )
                    {
                        JSONObject remarkInfo = new JSONObject();
                        JSONObject remark = new JSONObject();
                        remark.put("dfmoney_orderid", cloudDfOrderInterfaceInfo.getRequestOrderid());
                        remark.put("api_orderid", cloudDfOrderInterfaceInfo.getOrderid());
                        remark.put("money", cloudDfOrderInterfaceInfo.getApplyamt());
                        remark.put("type", "subtract");
                        remark.put("content", "subtract: 代付失败，减去月代付总额");
                        remark.put("before", "0");
                        remark.put("after", monthTotal_new.toString());
                        remarkInfo.put("1", remark);
                        staffMonth.setRemarks(remarkInfo.toString());
                    }else
                    {
                        JSONObject remarkOld = JSONObject.fromObject(remark_old);
                        JSONObject remark_new = new JSONObject();
                        remark_new.put("dfmoney_orderid", cloudDfOrderInterfaceInfo.getRequestOrderid());
                        remark_new.put("api_orderid", cloudDfOrderInterfaceInfo.getOrderid());
                        remark_new.put("money", cloudDfOrderInterfaceInfo.getApplyamt());
                        remark_new.put("type", "subtract");
                        remark_new.put("content", "代付失败，减去月代付总额");
                        remark_new.put("before", monthTotal_old.toString());
                        remark_new.put("after", monthTotal_new.toString());
                        remarkOld.put(remarkOld.size() + 1, remark_new);

                        staffMonth.setRemarks(remarkOld.toString());
                    }

                    cloudStaffMonthTotalServiceFacade.updateStaffMonthTotal(staffMonth);
                }

            }
            /*if ( monthTotalInfo == null )
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
                //过滤多次查询
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
            }*/
        }
        //组装返回数据
        JSONObject actualData = new JSONObject();
        actualData.put("tranAmt",resJson.get("tranAmt"));
        actualData.put("orderid",orderId);
        actualData.put("orderStatus",resJson.get("orderStatus"));
        actualData.put("tranDesc",resJson.get("tranDesc"));
        actualData.put("retCode",resJson.get("retCode"));

        responseDa.put("info", resJson.get("retCode").toString() + resJson.get("retMsg"));
        responseDa.put("data",actualData);

        if(resJson.get("retCode").equals("0000")){

            responseDa.put("code","10000");
        }else{
            responseDa.put("code","10008");
        }
        return ToolUtils.toBase64(responseDa.toString());
    }

}
