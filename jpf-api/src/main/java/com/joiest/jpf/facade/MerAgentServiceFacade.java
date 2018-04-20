package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ModifyAgentRequest;
import com.joiest.jpf.entity.MerchantAgentInfo;
import com.joiest.jpf.entity.MerchantInfo;

import java.util.List;

public interface MerAgentServiceFacade {

    /**
     * 获取商户对应的等级信息
     */
    public MerchantAgentInfo getMerchAgentInfo(Long mtsid);

    /**
     * 根据不同等级，获取不同等级商户信息
     */
    public List<MerchantInfo> getAgentInfoByTpid(String tpid);

    /**
     * 添加|修改商户代理信息
     */
    public JpfResponseDto modifyAgent(ModifyAgentRequest request);
}
