package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudCompanyAgent;
import com.joiest.jpf.common.po.PayCloudCompanyAgentExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyAgentMapper;
import com.joiest.jpf.entity.CloudCompanyAgentInfo;
import com.joiest.jpf.facade.CloudCompanyAgentServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class CloudCompanyAgentServiceFacadeImpl implements CloudCompanyAgentServiceFacade {

    @Autowired
    private PayCloudCompanyAgentMapper payCloudCompanyAgentMapper;

    /**
     * 根据商户号查找记录
     */
    public CloudCompanyAgentInfo getAgentByAgentNo(String agentNo){
        PayCloudCompanyAgentExample e = new PayCloudCompanyAgentExample();
        PayCloudCompanyAgentExample.Criteria c = e.createCriteria();
        c.andAgentNoEqualTo(agentNo);
        CloudCompanyAgentInfo cloudCompanyAgentInfo = new CloudCompanyAgentInfo();
        List<PayCloudCompanyAgent> list = payCloudCompanyAgentMapper.selectByExample(e);

        if(list.isEmpty()){
            return null;
        }
        BeanCopier beanCopier = BeanCopier.create( PayCloudCompanyAgent.class, CloudCompanyAgentInfo.class, false);
        beanCopier.copy(list.get(0), cloudCompanyAgentInfo, null);

        return cloudCompanyAgentInfo;
    }
}
