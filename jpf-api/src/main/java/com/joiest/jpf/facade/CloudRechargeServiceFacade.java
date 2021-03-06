package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.*;
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
    public CloudRechargeInfo getRecharge(Long id);

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
    public Integer rechargeCompanyMoney(Long id);

    /**
     * 获取企业需求列表
     */
    public GetRechargeNeedResponse getRechargeNeedInfo(GetRechargeNeedRequest request,String pageNo, String pageSize);

    /**
     * 发布需求接口
     */
    public JpfResponseDto rechargeNeedRelease(CloudRechargeNeedReleaseRequest request);

    /**
     * 删除发布需求接口 status=1时可以有此操作
     */
    public JpfResponseDto rechargeNeedDelete(String merchNo,String agentNo,String id,String fid);

    /**
     * 上传凭证接口 status=2时可以有此操作
     */
    public JpfResponseDto rechargeNeedVoucher(String merchNo,String agentNo,String id,String fid,String imgurl);

    /**
     * 确认验收接口 pacttime时间到了、pactstatus=1、status=0或1或2或3时可以有此操作
     */
    public JpfResponseDto rechargeNeedAffirm(String merchNo,String agentNo,String id,String fid);

    /**
     * 根据合同编号获取记录
     */
    public CloudRechargeInfo getRecByPactno(String pactno,byte status);

    /**
     * 根据合同编号获取记录
     */
    public CloudRechargeInfo getRecByPactnoList(String pactno);

}
