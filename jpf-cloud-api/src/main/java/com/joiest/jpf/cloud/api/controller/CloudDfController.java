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
import com.joiest.jpf.entity.CloudDfTaskInterfaceInfo;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import com.joiest.jpf.facade.CloudDfTaskInterfaceServiceFacade;
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

}
