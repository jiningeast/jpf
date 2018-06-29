package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.CloudRechargeRequest;
import com.joiest.jpf.dto.CloudRechargeResponse;
import com.joiest.jpf.entity.CloudRechargeInfo;

public interface CloudRechargeServiceFacade {

    public Integer getCloudRechargeCount();

    //public List<CloudRechargeInfo> getCloudRecharge(long page, long rows);

    public CloudRechargeResponse getRecords(CloudRechargeRequest cloudRechargeRequest );

    public CloudRechargeInfo getRecharge(String id);

    public JpfResponseDto getAuditRecharge(CloudRechargeRequest request);

}
