package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.entity.CloudDfTaskInterfaceInfo;

import com.joiest.jpf.facade.CloudDfOrderInterfaceServiceFacade;
import com.joiest.jpf.facade.CloudDfTaskInterfaceServiceFacade;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//数据获取写入
public class DfDataUtils extends Thread{

    private String batchid;

    private String dfid;

    //df_money list
    private List<CloudDfMoneyInterfaceInfo> list;

    private String batchid_self;

    private CloudDfOrderInterfaceServiceFacade cloudDfOrderInterfaceServiceFacade;

    private CloudDfTaskInterfaceServiceFacade cloudDfTaskInterfaceServiceFacade;

    private boolean keepRunning = true;

    public DfDataUtils(String batchid, String dfid, List<CloudDfMoneyInterfaceInfo> list, String batchid_self) {

        this.batchid = batchid;
        this.dfid = dfid;
        this.list = list;
        this.batchid_self = batchid_self;

        ApplicationContext beanFactory= SpringContextUtil.getApplicationContext();
        cloudDfOrderInterfaceServiceFacade = beanFactory.getBean(CloudDfOrderInterfaceServiceFacade.class);
        cloudDfTaskInterfaceServiceFacade = beanFactory.getBean(CloudDfTaskInterfaceServiceFacade.class);
    }

    @Override
    public void run() {
        System.out.println("【" + getName() + "】 : 开始执行");
        while ( keepRunning )
        {
            StringBuilder sbf = new StringBuilder();
            java.util.Date date = new Date();
            SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fileName = "dfApi";
            String path = "/logs/jpf-cloud-api/log/";

            try {
                int res_add = 0;
                while (keepRunning)
                {
                    //添加数据
                    res_add = cloudDfOrderInterfaceServiceFacade.addOrder(batchid, dfid, list,batchid_self);
                    if ( res_add == 1)
                    {
                        keepRunning = false;
                    }
                }

                sbf.append("\n\nTime:" + myfmt.format(date) + " -数据写入 success");
                sbf.append("\n当前地址: " + "dfApi");
                sbf.append("\n任务批次号: " + batchid_self);
                sbf.append("\n接口被请求参数: " + "batchid=" + batchid + "&dfid=" + dfid);
                sbf.append("\n数据总数: " + list.size());
                sbf.append("\n数据写入:  SUCCESS");
                LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

            } catch (Exception e) {
                keepRunning = false;

                sbf.append("\n\nTime:" + myfmt.format(date) + " -数据写入 error:");
                sbf.append("\n当前地址:" + "dfApi");
                sbf.append("\n任务批次号:" + batchid_self);
                sbf.append("\n接口被请求参数:" + "batchid=" + batchid + "&dfid=" + dfid);
                sbf.append("\nException: 写入数据异常: " + e.getCause().getClass() + "; " + e.getCause().getMessage());
                LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

                //更新此任务为异常状态
                CloudDfTaskInterfaceInfo taskCompleteInfo = new CloudDfTaskInterfaceInfo();
                taskCompleteInfo.setBatchid(batchid_self);
                taskCompleteInfo.setInsertStatus(-1);
                taskCompleteInfo.setUpdated(new Date());
                int res_upTaskComplete = cloudDfTaskInterfaceServiceFacade.updateTaskByExample(taskCompleteInfo);

            }
        }
        super.run();
    }

}
