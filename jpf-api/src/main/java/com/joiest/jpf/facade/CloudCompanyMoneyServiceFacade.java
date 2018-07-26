package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayCloudCompanyMoney;
import com.joiest.jpf.dto.CloudCompanyMoneyRequest;
import com.joiest.jpf.dto.CloudCompanyMoneyResponse;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.CloudCompanyMoneyInfo;

import java.util.Map;


public interface CloudCompanyMoneyServiceFacade {

    /*
     * 批次列表总数
     * */
    public Integer getCount();

    /*
     * 批次列表
     * */
    public CloudCompanyMoneyResponse getRecords(CloudCompanyMoneyRequest cloudCompanyMoneyRequest);
    /*
     * 获取统计信息
     * */
    public Map<String, Object> getStatistics(CloudCompanyMoneyRequest cloudCompanyMoneyRequest);
    /*
     * 财务审核列表
     * */
    public CloudCompanyMoneyResponse getCaiwuRecords(CloudCompanyMoneyRequest cloudCompanyMoneyRequest);

    /*
     * 代付列表
     * */
    public GetCloudMoneyDfResponse getAllBycompanyMoneyId(String companyMoneyId);

    /**
     * 根据主键id获取单个订单信息
     */
    public CloudCompanyMoneyInfo getRecById(String id);

    /**
     * 根据主键ID更新订单状态
     */
    public JpfResponseDto updateRecById(PayCloudCompanyMoney record, String id);

    /**
     * 新增代付订单
     */
    public int addRec(CloudCompanyMoneyInfo cloudCompanyMoneyInfo);

    /**
     * 根据批次号获取订单
     */
    public CloudCompanyMoneyInfo getRecByBatchNo(String batchNo);

    /**
     * 更新记录
     */
    public int updateColumn(CloudCompanyMoneyInfo cloudCompanyMoneyInfo);

    /**
     * 根据合同编号获取记录
     */
    public CloudCompanyMoneyInfo getRecByFid(String fid);
}
