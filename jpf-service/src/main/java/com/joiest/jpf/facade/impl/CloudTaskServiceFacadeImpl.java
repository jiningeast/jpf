package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudTask;
import com.joiest.jpf.common.po.PayCloudTaskExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudTaskCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudTaskMapper;
import com.joiest.jpf.dto.CloudTaskRequest;
import com.joiest.jpf.dto.CloudTaskResponse;
import com.joiest.jpf.entity.CloudTaskInfo;
import com.joiest.jpf.facade.CloudTaskServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class CloudTaskServiceFacadeImpl implements CloudTaskServiceFacade {

    @Autowired
    private PayCloudTaskMapper payCloudTaskMapper;

    @Autowired
    private PayCloudTaskCustomMapper payCloudTaskCustomMapper;

    @Override
    public CloudTaskResponse getTasks(CloudTaskRequest request){
        CloudTaskResponse cloudTaskResponse = new CloudTaskResponse();

        PayCloudTaskExample e = new PayCloudTaskExample();
        e.setPageNo(request.getPage());
        e.setPageSize(request.getRows());
        e.setOrderByClause("id DESC");
        List<CloudTaskInfo> infos = new ArrayList<>();
        //==搜索添加==
        PayCloudTaskExample.Criteria c=e.createCriteria();
        if(StringUtils.isNotBlank(request.getId())){
            c.andIdEqualTo(request.getId());
        }
        if (StringUtils.isNotBlank(request.getOperatorName())){
            c.andOpratorNameEqualTo(request.getOperatorName());
        }
        if(StringUtils.isNotBlank(request.getCompanyName())){
            c.andCompanyNameEqualTo(request.getCompanyName());
        }
        if(StringUtils.isNotBlank(request.getAgentNo())){
            c.andAgentNoEqualTo(request.getAgentNo());
        }
        if(StringUtils.isNotBlank(request.getMerchNo())){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        if(StringUtils.isNotBlank(request.getBatchNo())){
            c.andBatchnoEqualTo(request.getBatchNo());
        }
        if(request.getStatus()!=7 && request.getStatus()!=0){
            if(request.getStatus()==4){
                c.andStatusEqualTo((byte)0);
            }else{
                c.andStatusEqualTo(request.getStatus());
            }
        }
        if(request.getIsLock()!=7 && request.getIsLock()!=0){
            if(request.getIsLock()==4){
                c.andIsLockEqualTo((byte)0);
            }else{
                c.andIsLockEqualTo(request.getIsLock());
            }
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andCreatedGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andCreatedLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }

        if (StringUtils.isNotBlank(request.getFinishStart()))
        {
            c.andFinishtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getFinishStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getFinishEnd()))
        {
            c.andFinishtimeLessThanOrEqualTo(DateUtils.getFdate(request.getFinishEnd(),DateUtils.DATEFORMATLONG));
        }
        List<PayCloudTask> list = payCloudTaskMapper.selectByExample(e);
        cloudTaskResponse.setCount(payCloudTaskMapper.countByExample(e));
        for (PayCloudTask payCloudTask:list){
            CloudTaskInfo cloudTaskInfo = new CloudTaskInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudTask.class, CloudTaskInfo.class, false);
            beanCopier.copy(payCloudTask, cloudTaskInfo, null);
            infos.add(cloudTaskInfo);
        }
        cloudTaskResponse.setList(infos);

        return cloudTaskResponse;
    }

    @Override
    public String insTask(CloudTaskInfo cloudTaskInfo){
        PayCloudTask payCloudTask = new PayCloudTask();
        BeanCopier beanCopier = BeanCopier.create(CloudTaskInfo.class, PayCloudTask.class, false);
        beanCopier.copy(cloudTaskInfo, payCloudTask, null);

        payCloudTaskCustomMapper.insert(payCloudTask);
        return payCloudTask.getId();
    }

    @Override
    public CloudTaskInfo getOneTask(String id){
        PayCloudTask payCloudTask = payCloudTaskMapper.selectByPrimaryKey(id);
        CloudTaskInfo cloudTaskInfo = new CloudTaskInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudTask.class, CloudTaskInfo.class, false);
        beanCopier.copy(payCloudTask, cloudTaskInfo, null);

        return cloudTaskInfo;
    }

    @Override
    public int updateColumn(CloudTaskInfo cloudTaskInfo){
        PayCloudTask payCloudTask = new PayCloudTask();

        BeanCopier beanCopier = BeanCopier.create(CloudTaskInfo.class, PayCloudTask.class, false);
        beanCopier.copy(cloudTaskInfo, payCloudTask, null);

        return payCloudTaskMapper.updateByPrimaryKeySelective(payCloudTask);
    }

    /**
     * 根据合同号获取任务
     */
    @Override
    public CloudTaskInfo getOneTaskByBatchNo(String batchNo){
        PayCloudTaskExample e = new PayCloudTaskExample();
        PayCloudTaskExample.Criteria c = e.createCriteria();
        c.andBatchnoEqualTo(batchNo);
        List<PayCloudTask> list = payCloudTaskMapper.selectByExample(e);

        CloudTaskInfo cloudTaskInfo = new CloudTaskInfo();
        if ( !list.isEmpty() ){
            BeanCopier beanCopier = BeanCopier.create(PayCloudTask.class, CloudTaskInfo.class, false);
            beanCopier.copy(list.get(0), cloudTaskInfo, null);
        }

        return cloudTaskInfo;
    }
}
