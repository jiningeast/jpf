package com.joiest.jpf.manage.web.util;

import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.CheckBanksRequest;
import com.joiest.jpf.entity.CloudInterfaceStreamInfo;
import com.joiest.jpf.entity.CloudStaffBanksInfo;
import com.joiest.jpf.entity.CloudTaskInfo;
import com.joiest.jpf.facade.CloudInterfaceStreamServiceFacade;
import com.joiest.jpf.facade.CloudStaffBanksServiceFacade;
import com.joiest.jpf.facade.CloudTaskServiceFacade;
import com.joiest.jpf.manage.web.controller.CloudTaskController;
import com.joiest.jpf.manage.web.controller.ConfigUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.util.*;

public class CheckBanksUtils implements Runnable {

    private CloudStaffBanksServiceFacade cloudStaffBanksServiceFacade;

    private CloudInterfaceStreamServiceFacade cloudInterfaceStreamServiceFacade;

    private CloudTaskServiceFacade cloudTaskServiceFacade;

    private String cacheFileName;

    private String taskId;

    private static final Logger logger = LogManager.getLogger(CloudTaskController.class);

    public CheckBanksUtils(String cacheFileName, String taskId){
        this.cacheFileName = cacheFileName;
        this.taskId = taskId;

        ApplicationContext beanFactory= SpringContextUtil.getApplicationContext();
        cloudStaffBanksServiceFacade = beanFactory.getBean(CloudStaffBanksServiceFacade.class);
        cloudInterfaceStreamServiceFacade = beanFactory.getBean(CloudInterfaceStreamServiceFacade.class);
        cloudTaskServiceFacade = beanFactory.getBean(CloudTaskServiceFacade.class);
    }

    public void run(){
        // 输出当前线程名称
        String threadName = Thread.currentThread().getName();
        logger.info("=================线程"+threadName+"开始处理=================");

        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+cacheFileName+".txt","UTF-8");
        Map<String,List<LinkedHashMap<String,String>>> jsonMapData = JsonUtils.toObject(fileContent,HashMap.class);
        List< LinkedHashMap<String,String> > personsList = jsonMapData.get("data");
        CheckBanksRequest checkBanksRequest = new CheckBanksRequest();
        int checkedCount = 0;     // 计算成功鉴权数量，如果和总数一样说明全部鉴权成功
        for ( LinkedHashMap<String,String> singlePerson:personsList ){
            checkBanksRequest.setAccountNo(singlePerson.get("bankNo"));
            checkBanksRequest.setIdCard(singlePerson.get("idno"));
            checkBanksRequest.setMobile(singlePerson.get("phone"));
            checkBanksRequest.setName(singlePerson.get("name"));
            checkBanksRequest.setDateTime(new Date());

            // 先查询这个银行卡号和手机号是否已鉴权过
            CloudStaffBanksInfo isCheckedInfo = new CloudStaffBanksInfo();
            isCheckedInfo.setBankno(checkBanksRequest.getAccountNo());
            isCheckedInfo.setBankphone(checkBanksRequest.getMobile());
            CloudStaffBanksInfo queryRecord = cloudStaffBanksServiceFacade.getStaffBankByInfo(isCheckedInfo);
            if ( queryRecord.getBankActive().equals("1") ){
                checkedCount++;
                logger.info( "[已鉴权][" + checkBanksRequest.getStaffId() + "][" + checkBanksRequest.getName() + "][" + checkBanksRequest.getMobile() + "]");
            }else {
                // 拼接鉴权4要素参数并触发接口
                Map<String,Object> requestMap = new HashMap<>();
                requestMap.put("accountNo",checkBanksRequest.getAccountNo());
                requestMap.put("idCard",checkBanksRequest.getIdCard());
                requestMap.put("mobile",checkBanksRequest.getMobile());
                requestMap.put("name",checkBanksRequest.getName());
                requestMap.put("dateTime",checkBanksRequest.getDateTime());

                Map<String,Object> treeMap = new TreeMap<>();
                treeMap.putAll(requestMap);

                String sign = Md5Encrypt.md5(ToolUtils.mapToUrl(treeMap) + ConfigUtil.getValue("API_SECRET")).toUpperCase();
                requestMap.put("sign",sign);
                String requestUrl = ConfigUtil.getValue("CLOUD_API_URL")+"/toolcate/bankFourCheck";
                String response = OkHttpUtils.postForm(requestUrl,requestMap);
                Map<String,String> responseMap = JsonUtils.toObject(response, HashMap.class);

                // 添加流水记录
                CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();
                cloudInterfaceStreamInfo.setType((byte)1);
                cloudInterfaceStreamInfo.setRequestUrl(requestUrl);
                cloudInterfaceStreamInfo.setRequestContent(ToolUtils.mapToUrl(requestMap));
                cloudInterfaceStreamInfo.setResponseContent(response);
                cloudInterfaceStreamInfo.setTaskId(checkBanksRequest.getTaskId());
                cloudInterfaceStreamInfo.setStaffId(checkBanksRequest.getStaffId());
                cloudInterfaceStreamInfo.setStaffBanksId(checkBanksRequest.getStaffBanksId());
                cloudInterfaceStreamInfo.setAddtime(new Date());
                int streamRes = cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);
                if ( streamRes > 0 ){
                    logger.info("[新增鉴权接口流水成功][" + checkBanksRequest.getStaffId() + "][" + checkBanksRequest.getName() + "][" + checkBanksRequest.getMobile() + "]");
                }else{
                    logger.info("[新增鉴权接口流水失败][" + checkBanksRequest.getStaffId() + "][" + checkBanksRequest.getName() + "][" + checkBanksRequest.getMobile() + "]");
                }

                if ( responseMap.get("code").equals("10000") ){
                    // 鉴权成功
                    CloudStaffBanksInfo cloudStaffBanksInfo = new CloudStaffBanksInfo();
                    cloudStaffBanksInfo.setBankno(checkBanksRequest.getAccountNo());
                    cloudStaffBanksInfo.setBankphone(checkBanksRequest.getMobile());
                    cloudStaffBanksInfo.setBankActive("1");
                    int staffBanksRes = cloudStaffBanksServiceFacade.updateColumn(cloudStaffBanksInfo);
                    if ( staffBanksRes > 0 ){
                        checkedCount++;
                        logger.info("[鉴权成功][" + checkBanksRequest.getStaffId() + "][" + checkBanksRequest.getName() + "][" + checkBanksRequest.getMobile() + "]");
                    }
                }else {
                    // 鉴权失败
                    logger.info("[鉴权失败][" + checkBanksRequest.getStaffId() + "][" + checkBanksRequest.getName() + "][" + checkBanksRequest.getMobile() + "]");
                }
            }
        }

        // 判断鉴权成功了多少个人
        CloudTaskInfo cloudTaskInfo = new CloudTaskInfo();
        cloudTaskInfo.setId(taskId);
        if ( checkedCount == personsList.size() ){
            // 鉴权成功的数量和总人数一致，此订单鉴权完成
            cloudTaskInfo.setStatus((byte)3);
        }else if ( checkedCount < personsList.size() ){
            // 鉴权成功的数量小于总人数，此订单鉴权部分失败
            cloudTaskInfo.setStatus((byte)1);
        }else if ( checkedCount == 0 ){
            // 一个都没鉴权成功，此订单全部失败
            cloudTaskInfo.setStatus((byte)2);
        }
        cloudTaskInfo.setFinishtime(new Date());
        cloudTaskServiceFacade.updateColumn(cloudTaskInfo);
        logger.info("=================线程"+threadName+"处理结束=================");
    }
}
