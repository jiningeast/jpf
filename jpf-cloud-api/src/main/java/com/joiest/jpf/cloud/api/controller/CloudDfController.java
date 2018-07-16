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
import com.joiest.jpf.dto.AddCloudDfTaskRequest;
import com.joiest.jpf.dto.CloudDfRequest;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import com.joiest.jpf.facade.CloudDfTaskInterfaceServiceFacade;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/clouddf")
public class CloudDfController {

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    @Autowired
    private CloudDfTaskInterfaceServiceFacade cloudDfTaskInterfaceServiceFacade;
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

    @RequestMapping(value = "/dfApi", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public JSONObject dfApi(String batchid, String dfid)
    {
        if ( StringUtils.isBlank(batchid) )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_BATCHNO_NOTEMPTY.getCode(),JpfInterfaceErrorInfo.DF_BATCHNO_NOTEMPTY.getDesc());
        }

        //获取批次df详情
        int flag = 0;
        GetCloudMoneyDfResponse response = cloudDfMoneyServiceFacade.getDfDetailList(batchid,dfid,0,0, flag);
        if ( response.getList().isEmpty() || response.getList() == null )
        {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.DF_INFOLIST_EMPTY.getCode(),JpfInterfaceErrorInfo.DF_INFOLIST_EMPTY.getDesc());
        }


        //添加任务
        String batchid_self = ToolsUtils.createOrderid();
        Map<String,String> taskMap = new HashMap<>();
        AddCloudDfTaskRequest request = new AddCloudDfTaskRequest();
        request.setBatchid(batchid_self);
        request.setRequestBatchno(batchid);
        request.setRequestStr("这是请求字符串");
        request.setRequestDfId(dfid);
        request.setOrderCount(response.getCount());
        request.setOrderMoney(response.getMonthTotal());
        request.setCreated(new Date());
        cloudDfTaskInterfaceServiceFacade.addTask(request);


//        taskMap.put();

        Thread dfDataUtils = new DfDataUtils("131","0", response.getList(),batchid_self);
        dfDataUtils.setName("线程:" + batchid);
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
}
