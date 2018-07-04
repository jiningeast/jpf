package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.CloudRechargeRequest;
import com.joiest.jpf.dto.CloudRechargeResponse;
import com.joiest.jpf.entity.CloudRechargeInfo;

public interface CloudRechargeServiceFacade {

    /*
     * 充值列表总数
     * */
    public Integer getCloudRechargeCount();

    //public List<CloudRechargeInfo> getCloudRecharge(long page, long rows);

    /*
     * 充值列表信息
     * */
    public CloudRechargeResponse getRecords(CloudRechargeRequest cloudRechargeRequest );

    /*
     * 财务充值列表信息
     * */
    public CloudRechargeResponse getCaiwuRecords(CloudRechargeRequest cloudRechargeRequest );

    /*
     * 查询单条充值信息
     * */
    public CloudRechargeInfo getRecharge(String id);

    /*
    * 审核充值信息
    * */
    public JpfResponseDto getAuditRecharge(CloudRechargeRequest request);

    /*
     * 财务审核充值信息
     * */
    public JpfResponseDto getCaiwuAuditRecharge(CloudRechargeRequest request);

    /**
     * 充值公司账户金额
     */
    public Integer rechargeCompanyMoney(String id);

}
