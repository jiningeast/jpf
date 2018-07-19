package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudCompanyAgentInfo;

public interface CloudCompanyAgentServiceFacade {

    /**
     * 根据商户号查找记录
     */
    public CloudCompanyAgentInfo getAgentByAgentNo(String agentNo);
}
