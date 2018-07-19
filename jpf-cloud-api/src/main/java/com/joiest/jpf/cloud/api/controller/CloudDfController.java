package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.cloud.api.util.DfDataUtils;
import com.joiest.jpf.cloud.api.util.DfUtils;
import com.joiest.jpf.cloud.api.util.ToolsUtils;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.util.ClassUtil;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.CloudDfOrderInterfaceInfo;
import com.joiest.jpf.entity.CloudDfTaskInterfaceInfo;
import com.joiest.jpf.facade.CloudDfFqwaterServiceFacade;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import com.joiest.jpf.facade.CloudDfOrderInterfaceServiceFacade;
import com.joiest.jpf.facade.CloudDfTaskInterfaceServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//    @RequestMapping(value = "/dfApi", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public JSONObject dfApi(CloudDfRequest request)
//    {
//        ValidatorUtils.validateInterface(request);
//
//        Map<String,Object> requestMap = ClassUtil.requestToMap(request);
//        Map<String,Object> requestMapNew = new HashMap<String,Object>();
//        requestMapNew.putAll(requestMap);
//        String requestSign = requestMap.get("sign").toString();
//        requestMap.remove("sign");
//        Map<String,Object> treeMap = new TreeMap<>();
//        treeMap.putAll(requestMap);
//
//        String requestUrl = ToolUtils.mapToUrl(treeMap);
//        String selfSign = Md5Encrypt.md5(requestUrl + ConfigUtil.getValue("DF_KEY")).toUpperCase();
//        if ( !selfSign.equals(requestMapNew.get("sign").toString()) )
//        {
//            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_SIGN_ERROR.getCode(),JpfInterfaceErrorInfo.DF_SIGN_ERROR.getDesc());
//        }
//        JSONObject result = new DfUtils().applyAgentPay(requestMap);
//        return result;
//    }

    /**
     * 代付接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/dfApi", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject dfApi(DfApiInterfaceRequest request)
    {
        ValidatorUtils.validateInterface(request);

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

        //添加任务
        String batchid_self = ToolsUtils.createOrderid();
        AddCloudDfTaskRequest requestTask = new AddCloudDfTaskRequest();
        requestTask.setBatchid(batchid_self);
        requestTask.setRequestBatchno(request.getBatchid());
        requestTask.setRequestStr("这是请求字符串");
        requestTask.setRequestDfId(request.getDfid());
        requestTask.setOrderCount(response.getCount());
        requestTask.setOrderMoney(response.getMonthTotal());
        requestTask.setCreated(new Date());
        cloudDfTaskInterfaceServiceFacade.addTask(requestTask);

        Thread dfDataUtils = new DfDataUtils("131","0", response.getList(),batchid_self);
        dfDataUtils.setName("线程:" + request.getBatchid());
        dfDataUtils.start();

        JSONObject resultJson = new JSONObject();
        resultJson.put("code","10000");
        resultJson.put("info","SUCCESS");
        JSONObject dataJson = new JSONObject();
        dataJson.put("count", response.getCount());
        dataJson.put("totalMoney", response.getMonthTotal());
        resultJson.put("data", dataJson);

        return resultJson;

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

    @RequestMapping("/doDfApi")
    @ResponseBody
    public String doDfApi()
    {
        //获取信息
        GetCloudDfTaskInterfaceResponse taskResponse = cloudDfTaskInterfaceServiceFacade.getCanableTaskList();
        if ( taskResponse == null )
        {
            System.exit(0);
        }
        for ( CloudDfTaskInterfaceInfo one : taskResponse.getList() )
        {
            //执行
        }
        return "";
    }
    /**
     * 代付查询接口
     * */
    @RequestMapping(value = "/dfSelectApi",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject dfSelectApi(HttpServletRequest request){

        JSONObject responseDa = new JSONObject();

        String orderId = request.getParameter("orderId");

        CloudDfOrderInterfaceInfo cloudDfOrderInterfaceInfo = cloudDfOrderInterfaceServiceFacade.getDfOrderByRequestOrderid(orderId);
        if(cloudDfOrderInterfaceInfo == null){

            responseDa.put("code","10088");
            responseDa.put("info","未获取到此单信息");
            return responseDa;
        }
        Map<String,String> apiInfo = new HashMap<>();

        apiInfo.put("tranNo",cloudDfOrderInterfaceInfo.getTranno());
        apiInfo.put("outOrderNo",cloudDfOrderInterfaceInfo.getOrderid().toString());

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
        map.put("orderid",cloudDfOrderInterfaceInfo.getOrderid().toString());
        map.put("tranNo",cloudDfOrderInterfaceInfo.getTranno());
        map.put("tranAmt",cloudDfOrderInterfaceInfo.getApplyamt().toString());
        map.put("orderStatus",resJson.get("orderStatus").toString());
        map.put("requestParam",postApi.get("requestParam").toString());
        map.put("responseParam",resJson.toString());

        int isSuc = cloudDfFqwaterServiceFacade.addCloudDfFqwater(map);

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
        return responseDa;
    }

}
