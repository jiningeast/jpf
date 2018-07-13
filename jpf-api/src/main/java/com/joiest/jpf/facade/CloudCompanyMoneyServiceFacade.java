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
     * 财务审核列表
     * */
    public CloudCompanyMoneyResponse getCaiwuRecords(CloudCompanyMoneyRequest cloudCompanyMoneyRequest);

    /*
     * 代付列表
     * */
    public GetCloudMoneyDfResponse getAllByfid(String fid);

    /**
     * 根据订单号fid获取单个订单信息
     */
    public CloudCompanyMoneyInfo getRecByFid(String fid);

    /**
     * 根据订单号fid更新订单状态
     */
    public JpfResponseDto updateRecByFid(PayCloudCompanyMoney record, String fid);

    /**
     * 新增代付订单
     */
    public int addRec(CloudCompanyMoneyInfo cloudCompanyMoneyInfo);
}
