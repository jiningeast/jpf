package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.CloudDfOrderInterfaceInfo;
import com.joiest.jpf.entity.CloudDfTaskInterfaceInfo;
import com.joiest.jpf.facade.CloudDfOrderInterfaceServiceFacade;
import com.joiest.jpf.facade.CloudDfTaskInterfaceServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.*;

public class DfThread extends Thread{

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 任务批次号
     */
    private String batchno;

    private boolean keepRunning = true;

    private int count;

    private CloudDfOrderInterfaceServiceFacade cloudDfOrderInterfaceServiceFacade;

    private CloudDfTaskInterfaceServiceFacade cloudDfTaskInterfaceServiceFacade;

    public DfThread(String batchno, Long taskId) {
        this.batchno = batchno;
        this.taskId = taskId;
        ApplicationContext beanFactory= SpringContextUtil.getApplicationContext();
        cloudDfOrderInterfaceServiceFacade = beanFactory.getBean(CloudDfOrderInterfaceServiceFacade.class);
        cloudDfTaskInterfaceServiceFacade = beanFactory.getBean(CloudDfTaskInterfaceServiceFacade.class);
    }

    @Override
    public void run() {

        //查询数据 batchno
        List<CloudDfOrderInterfaceInfo> list = cloudDfOrderInterfaceServiceFacade.getOrdersList(batchno);
        if ( list == null )
        {
            keepRunning = false;
        }

        StringBuilder sbf = new StringBuilder();
        java.util.Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String fileName = "doDfApi";
        String path = "/logs/jpf-cloud-api/log/";
        while ( keepRunning )
        {
            try {
                int listCount = list.size();

                //更新任务状态
                CloudDfTaskInterfaceInfo taskBeginInfo = new CloudDfTaskInterfaceInfo();
                taskBeginInfo.setId(this.taskId);
                taskBeginInfo.setStatus(1);
                taskBeginInfo.setUpdated(new Date());
                int res_upTaskBegin = cloudDfTaskInterfaceServiceFacade.updateTask(taskBeginInfo);

                for (CloudDfOrderInterfaceInfo one : list)
                {
                    //拼装数据
                    Map<String,Object> requestMap = new HashMap<>();
                    requestMap.put("outOrderNo",one.getOrderid());
                    requestMap.put("orderTime",myfmt.format(new Date()));   //yyyyMMddHHmmss
                    requestMap.put("finaCode",one.getFinacode());
                    requestMap.put("payeeAcct",one.getPayeeacct());
                    requestMap.put("payeeName",one.getPayeename());
                    requestMap.put("applyAmt",one.getApplyamt());
                    requestMap.put("payeeAcctAttr",one.getPayeeacctattr());
                    requestMap.put("bankProvince",one.getBankprovince());
                    requestMap.put("bankCity",one.getBankcity());
                    requestMap.put("phoneNo",one.getPhoneno());
                    requestMap.put("certNo",one.getCertno());

                    //调用接口
                    JSONObject resJson = new DfUtils().applyAgentPay(requestMap);
                    JSONObject resultJson = JSONObject.fromObject(resJson.get("resultStr").toString());
                    String requestUrl = resJson.get("requestUrl").toString();
                    String requestParam = resJson.get("requestParam").toString();

                    count++;

                    // 更新df_order数据
                    CloudDfOrderInterfaceInfo info = new CloudDfOrderInterfaceInfo();
                    info.setId(one.getId());
                    info.setUpdated(new Date());
                    info.setStatus(1);
                    String orderStatus = "";
                    String tranNo = "";
                    if ( resultJson.has("orderStatus") )
                    {
                        orderStatus = resultJson.get("orderStatus").toString();
                    }
                    if ( resultJson.has("tranNo") )
                    {
                        tranNo = resultJson.get("tranNo").toString();
                    }
                    info.setRequeststr(requestUrl + "?" + requestParam);
                    info.setReturncontent(resJson.get("resultStr").toString());
                    info.setDfstatus(orderStatus);
                    info.setTranno(tranNo);
                    int res_up = cloudDfOrderInterfaceServiceFacade.updateDfOrderStatus(info);
                }

                if ( count == listCount )
                {
                    keepRunning = false;
                    //更新任务状态
                    CloudDfTaskInterfaceInfo taskCompleteInfo = new CloudDfTaskInterfaceInfo();
                    taskCompleteInfo.setId(this.taskId);
                    taskCompleteInfo.setStatus(2);
                    taskCompleteInfo.setUpdated(new Date());
                    int res_upTaskComplete = cloudDfTaskInterfaceServiceFacade.updateTask(taskCompleteInfo);
                }

                sbf.append("\n\nTime:" + myfmt.format(date) + " -代付 success:");
                sbf.append("\n当前地址:" + "doDfApi");
                sbf.append("\n任务批次号:" + batchno);
                sbf.append("\n信息: success");

                LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

            }catch (Exception e)
            {
                sbf.append("\n\nTime:" + myfmt.format(date) + " -代付 error:");
                sbf.append("\n当前地址:" + "doDfApi");
                sbf.append("\n任务批次号:" + batchno);
                sbf.append("\nException: " + e.getCause().getClass() + "; " + e.getCause().getMessage());

                LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
            }
        }
        super.run();
    }
}
