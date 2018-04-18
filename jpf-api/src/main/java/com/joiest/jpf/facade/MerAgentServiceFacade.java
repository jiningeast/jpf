package com.joiest.jpf.facade;

import com.joiest.jpf.entity.MerchantAgentInfo;
import com.joiest.jpf.entity.MerchantInfo;

import java.util.List;

public interface MerAgentServiceFacade {

    /**
     * 获取商户对应的等级信息
     */
    public MerchantAgentInfo getMerchAgentInfo(Long mtsid);

    public List<MerchantInfo> getAgentInfoByTpid(String tpid);
}
